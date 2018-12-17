package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.Keep;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

public class ActionBarLayout extends FrameLayout {
    public interface ActionBarLayoutDelegate {
        boolean needAddFragmentToStack(BaseFragment arg1, ActionBarLayout arg2);

        boolean needCloseLastFragment(ActionBarLayout arg1);

        boolean needPresentFragment(BaseFragment arg1, boolean arg2, boolean arg3, ActionBarLayout arg4);

        boolean onPreIme();

        void onRebuildAllFragments(ActionBarLayout arg1, boolean arg2);
    }

    public class LinearLayoutContainer extends LinearLayout {
        private boolean isKeyboardVisible;
        private Rect rect;

        public LinearLayoutContainer(ActionBarLayout arg1, Context arg2) {
            ActionBarLayout.this = arg1;
            super(arg2);
            this.rect = new Rect();
            this.setOrientation(1);
        }

        static boolean access$1500(LinearLayoutContainer arg0) {
            return arg0.isKeyboardVisible;
        }

        public boolean dispatchTouchEvent(MotionEvent arg4) {
            boolean v1 = false;
            if(!ActionBarLayout.this.inPreviewMode && !ActionBarLayout.this.transitionAnimationPreviewMode) {
                goto label_13;
            }
            else if(arg4.getActionMasked() != 0) {
                if(arg4.getActionMasked() == 5) {
                }
                else {
                    try {
                    label_13:
                        if((ActionBarLayout.this.inPreviewMode) && this == ActionBarLayout.this.containerView) {
                            return v1;
                        }

                        if(!super.dispatchTouchEvent(arg4)) {
                            return v1;
                        }
                    }
                    catch(Throwable ) {
                        return v1;
                    }

                    v1 = true;
                }
            }

            return v1;
        }

        protected boolean drawChild(Canvas arg6, View arg7, long arg8) {
            if((arg7 instanceof ActionBar)) {
                return super.drawChild(arg6, arg7, arg8);
            }

            int v0 = this.getChildCount();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                View v3 = this.getChildAt(v2);
                if(v3 == arg7) {
                }
                else if(((v3 instanceof ActionBar)) && v3.getVisibility() == 0) {
                    if(v3.getCastShadows()) {
                        v0 = v3.getMeasuredHeight();
                        goto label_23;
                    }
                    else {
                        break;
                    }
                }
            }

            v0 = 0;
        label_23:
            boolean v7 = super.drawChild(arg6, arg7, arg8);
            if(v0 != 0 && ActionBarLayout.headerShadowDrawable != null && (ActionBarLayout.this.isShowShadow())) {
                ActionBarLayout.headerShadowDrawable.setBounds(0, v0, this.getMeasuredWidth(), ActionBarLayout.headerShadowDrawable.getIntrinsicHeight() + v0);
                ActionBarLayout.headerShadowDrawable.draw(arg6);
            }

            return v7;
        }

        public boolean hasOverlappingRendering() {
            if(Build$VERSION.SDK_INT >= 28) {
                return 1;
            }

            return 0;
        }

        protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
            super.onLayout(arg1, arg2, arg3, arg4, arg5);
            View v1 = this.getRootView();
            this.getWindowVisibleDisplayFrame(this.rect);
            arg2 = v1.getHeight();
            boolean v4 = false;
            arg3 = this.rect.top != 0 ? AndroidUtilities.statusBarHeight : 0;
            if(arg2 - arg3 - AndroidUtilities.getViewInset(v1) - (this.rect.bottom - this.rect.top) > 0) {
                v4 = true;
            }

            this.isKeyboardVisible = v4;
            if(ActionBarLayout.this.waitingForKeyboardCloseRunnable != null && !ActionBarLayout.this.containerView.isKeyboardVisible && !ActionBarLayout.this.containerViewBack.isKeyboardVisible) {
                AndroidUtilities.cancelRunOnUIThread(ActionBarLayout.this.waitingForKeyboardCloseRunnable);
                ActionBarLayout.this.waitingForKeyboardCloseRunnable.run();
                ActionBarLayout.this.waitingForKeyboardCloseRunnable = null;
            }
        }
    }

    private AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;
    private int[][] animateEndColors;
    private ThemeInfo animateSetThemeAfterAnimation;
    private int[][] animateStartColors;
    private boolean animateThemeAfterAnimation;
    protected boolean animationInProgress;
    private float animationProgress;
    private Runnable animationRunnable;
    private View backgroundView;
    private boolean beginTrackingSent;
    private LinearLayoutContainer containerView;
    private LinearLayoutContainer containerViewBack;
    private ActionBar currentActionBar;
    private AnimatorSet currentAnimation;
    private DecelerateInterpolator decelerateInterpolator;
    private Runnable delayedOpenAnimationRunnable;
    private ActionBarLayoutDelegate delegate;
    private DrawerLayoutContainer drawerLayoutContainer;
    public ArrayList fragmentsStack;
    private static Drawable headerShadowDrawable;
    private boolean inActionMode;
    private boolean inPreviewMode;
    private boolean inSelectMode;
    public float innerTranslationX;
    private long lastFrameTime;
    private static Drawable layerShadowDrawable;
    private boolean maybeStartTracking;
    private Runnable onCloseAnimationEndRunnable;
    private Runnable onOpenAnimationEndRunnable;
    private Runnable overlayAction;
    protected Activity parentActivity;
    private ColorDrawable previewBackgroundDrawable;
    private boolean rebuildAfterAnimation;
    private boolean rebuildLastAfterAnimation;
    private boolean removeActionBarExtraHeight;
    private static Paint scrimPaint;
    private boolean showLastAfterAnimation;
    private boolean showShadow;
    protected boolean startedTracking;
    private int startedTrackingPointerId;
    private int startedTrackingX;
    private int startedTrackingY;
    private String subtitleOverlayText;
    private float themeAnimationValue;
    private ThemeDescriptionDelegate[] themeAnimatorDelegate;
    private ThemeDescription[][] themeAnimatorDescriptions;
    private AnimatorSet themeAnimatorSet;
    private String titleOverlayText;
    private boolean transitionAnimationInProgress;
    private boolean transitionAnimationPreviewMode;
    private long transitionAnimationStartTime;
    private boolean useAlphaAnimations;
    private VelocityTracker velocityTracker;
    private Runnable waitingForKeyboardCloseRunnable;

    public ActionBarLayout(Context arg3) {
        super(arg3);
        this.decelerateInterpolator = new DecelerateInterpolator(1.5f);
        this.accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        this.animateStartColors = new int[2][];
        this.animateEndColors = new int[2][];
        this.themeAnimatorDescriptions = new ThemeDescription[2][];
        this.themeAnimatorDelegate = new ThemeDescriptionDelegate[2];
        this.showShadow = true;
        this.parentActivity = ((Activity)arg3);
        if(ActionBarLayout.layerShadowDrawable == null) {
            ActionBarLayout.layerShadowDrawable = this.getResources().getDrawable(2131231299);
            ActionBarLayout.headerShadowDrawable = this.getResources().getDrawable(2131231098).mutate();
            ActionBarLayout.scrimPaint = new Paint();
        }
    }

    static Drawable access$000() {
        return ActionBarLayout.headerShadowDrawable;
    }

    static Runnable access$100(ActionBarLayout arg0) {
        return arg0.waitingForKeyboardCloseRunnable;
    }

    static float access$1000(ActionBarLayout arg0) {
        return arg0.animationProgress;
    }

    static float access$1002(ActionBarLayout arg0, float arg1) {
        arg0.animationProgress = arg1;
        return arg1;
    }

    static Runnable access$102(ActionBarLayout arg0, Runnable arg1) {
        arg0.waitingForKeyboardCloseRunnable = arg1;
        return arg1;
    }

    static DecelerateInterpolator access$1100(ActionBarLayout arg0) {
        return arg0.decelerateInterpolator;
    }

    static ColorDrawable access$1200(ActionBarLayout arg0) {
        return arg0.previewBackgroundDrawable;
    }

    static void access$1300(ActionBarLayout arg0, boolean arg1, boolean arg2, boolean arg3) {
        arg0.startLayoutAnimation(arg1, arg2, arg3);
    }

    static void access$1400(ActionBarLayout arg0, boolean arg1) {
        arg0.onAnimationEndCheck(arg1);
    }

    static Runnable access$1600(ActionBarLayout arg0) {
        return arg0.delayedOpenAnimationRunnable;
    }

    static Runnable access$1602(ActionBarLayout arg0, Runnable arg1) {
        arg0.delayedOpenAnimationRunnable = arg1;
        return arg1;
    }

    static AnimatorSet access$1700(ActionBarLayout arg0) {
        return arg0.themeAnimatorSet;
    }

    static AnimatorSet access$1702(ActionBarLayout arg0, AnimatorSet arg1) {
        arg0.themeAnimatorSet = arg1;
        return arg1;
    }

    static ThemeDescription[][] access$1800(ActionBarLayout arg0) {
        return arg0.themeAnimatorDescriptions;
    }

    static int[][] access$1900(ActionBarLayout arg0) {
        return arg0.animateStartColors;
    }

    static LinearLayoutContainer access$200(ActionBarLayout arg0) {
        return arg0.containerView;
    }

    static int[][] access$2000(ActionBarLayout arg0) {
        return arg0.animateEndColors;
    }

    static ThemeDescriptionDelegate[] access$2100(ActionBarLayout arg0) {
        return arg0.themeAnimatorDelegate;
    }

    static LinearLayoutContainer access$300(ActionBarLayout arg0) {
        return arg0.containerViewBack;
    }

    static boolean access$400(ActionBarLayout arg0) {
        return arg0.inPreviewMode;
    }

    static boolean access$500(ActionBarLayout arg0) {
        return arg0.transitionAnimationPreviewMode;
    }

    static void access$600(ActionBarLayout arg0, boolean arg1) {
        arg0.onSlideAnimationEnd(arg1);
    }

    static Runnable access$700(ActionBarLayout arg0) {
        return arg0.animationRunnable;
    }

    static Runnable access$702(ActionBarLayout arg0, Runnable arg1) {
        arg0.animationRunnable = arg1;
        return arg1;
    }

    static long access$802(ActionBarLayout arg0, long arg1) {
        arg0.transitionAnimationStartTime = arg1;
        return arg1;
    }

    static long access$900(ActionBarLayout arg2) {
        return arg2.lastFrameTime;
    }

    static long access$902(ActionBarLayout arg0, long arg1) {
        arg0.lastFrameTime = arg1;
        return arg1;
    }

    public boolean addFragmentToStack(BaseFragment arg2) {
        return this.addFragmentToStack(arg2, -1);
    }

    public boolean addFragmentToStack(BaseFragment arg4, int arg5) {
        ViewParent v0;
        if(this.delegate != null && !this.delegate.needAddFragmentToStack(arg4, this) || !arg4.onFragmentCreate()) {
            return 0;
        }

        arg4.setParentLayout(this);
        if(arg5 == -1) {
            if(!this.fragmentsStack.isEmpty()) {
                Object v5 = this.fragmentsStack.get(this.fragmentsStack.size() - 1);
                ((BaseFragment)v5).onPause();
                if(((BaseFragment)v5).actionBar != null && (((BaseFragment)v5).actionBar.getAddToContainer())) {
                    v0 = ((BaseFragment)v5).actionBar.getParent();
                    if(v0 != null) {
                        ((ViewGroup)v0).removeView(((BaseFragment)v5).actionBar);
                    }
                }

                if(((BaseFragment)v5).fragmentView == null) {
                    goto label_40;
                }

                v0 = ((BaseFragment)v5).fragmentView.getParent();
                if(v0 == null) {
                    goto label_40;
                }

                ((BaseFragment)v5).onRemoveFromParent();
                ((ViewGroup)v0).removeView(((BaseFragment)v5).fragmentView);
            }

        label_40:
            this.fragmentsStack.add(arg4);
        }
        else {
            this.fragmentsStack.add(arg5, arg4);
        }

        return 1;
    }

    public void animateThemedValues(ThemeInfo arg9) {
        BaseFragment v5_1;
        int v1 = 1;
        if(!this.transitionAnimationInProgress) {
            if(this.startedTracking) {
            }
            else {
                AnimatorSet v2 = null;
                if(this.themeAnimatorSet != null) {
                    this.themeAnimatorSet.cancel();
                    this.themeAnimatorSet = v2;
                }

                int v0 = 0;
                int v3 = 0;
                int v4 = 0;
                while(true) {
                    int v5 = 2;
                    if(v3 >= v5) {
                        break;
                    }

                    if(v3 == 0) {
                        v5_1 = this.getLastFragment();
                        goto label_41;
                    }
                    else {
                        if(!this.inPreviewMode && !this.transitionAnimationPreviewMode && this.fragmentsStack.size() <= 1) {
                            this.themeAnimatorDescriptions[v3] = ((ThemeDescription[])v2);
                            this.animateStartColors[v3] = ((int[])v2);
                            this.animateEndColors[v3] = ((int[])v2);
                            this.themeAnimatorDelegate[v3] = ((ThemeDescriptionDelegate)v2);
                            goto label_98;
                        }

                        Object v5_2 = this.fragmentsStack.get(this.fragmentsStack.size() - v5);
                    label_41:
                        if(v5_1 == null) {
                            goto label_98;
                        }

                        this.themeAnimatorDescriptions[v3] = v5_1.getThemeDescriptions();
                        this.animateStartColors[v3] = new int[this.themeAnimatorDescriptions[v3].length];
                        for(v4 = 0; v4 < this.themeAnimatorDescriptions[v3].length; ++v4) {
                            this.animateStartColors[v3][v4] = this.themeAnimatorDescriptions[v3][v4].getSetColor();
                            ThemeDescriptionDelegate v5_3 = this.themeAnimatorDescriptions[v3][v4].setDelegateDisabled();
                            if(this.themeAnimatorDelegate[v3] == null && v5_3 != null) {
                                this.themeAnimatorDelegate[v3] = v5_3;
                            }
                        }

                        if(v3 == 0) {
                            Theme.applyTheme(arg9, true);
                        }

                        this.animateEndColors[v3] = new int[this.themeAnimatorDescriptions[v3].length];
                        for(v4 = 0; v4 < this.themeAnimatorDescriptions[v3].length; ++v4) {
                            this.animateEndColors[v3][v4] = this.themeAnimatorDescriptions[v3][v4].getSetColor();
                        }

                        v4 = 1;
                    }

                label_98:
                    ++v3;
                }

                if(v4 != 0) {
                    this.themeAnimatorSet = new AnimatorSet();
                    this.themeAnimatorSet.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationCancel(Animator arg3) {
                            ThemeDescription[] v1;
                            if(arg3.equals(ActionBarLayout.this.themeAnimatorSet)) {
                                int v3;
                                for(v3 = 0; true; ++v3) {
                                    v1 = null;
                                    if(v3 >= 2) {
                                        break;
                                    }

                                    ActionBarLayout.this.themeAnimatorDescriptions[v3] = v1;
                                    ActionBarLayout.this.animateStartColors[v3] = ((int[])v1);
                                    ActionBarLayout.this.animateEndColors[v3] = ((int[])v1);
                                    ActionBarLayout.this.themeAnimatorDelegate[v3] = ((ThemeDescriptionDelegate)v1);
                                }

                                ActionBarLayout.this.themeAnimatorSet = ((AnimatorSet)v1);
                            }
                        }

                        public void onAnimationEnd(Animator arg3) {
                            ThemeDescription[] v1;
                            if(arg3.equals(ActionBarLayout.this.themeAnimatorSet)) {
                                int v3;
                                for(v3 = 0; true; ++v3) {
                                    v1 = null;
                                    if(v3 >= 2) {
                                        break;
                                    }

                                    ActionBarLayout.this.themeAnimatorDescriptions[v3] = v1;
                                    ActionBarLayout.this.animateStartColors[v3] = ((int[])v1);
                                    ActionBarLayout.this.animateEndColors[v3] = ((int[])v1);
                                    ActionBarLayout.this.themeAnimatorDelegate[v3] = ((ThemeDescriptionDelegate)v1);
                                }

                                ActionBarLayout.this.themeAnimatorSet = ((AnimatorSet)v1);
                            }
                        }
                    });
                    this.themeAnimatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "themeAnimationValue", new float[]{0f, 1f})});
                    this.themeAnimatorSet.setDuration(200);
                    this.themeAnimatorSet.start();
                    int v9 = this.fragmentsStack.size();
                    if((this.inPreviewMode) || (this.transitionAnimationPreviewMode)) {
                        v1 = 2;
                    }

                    v9 -= v1;
                    while(v0 < v9) {
                        Object v1_1 = this.fragmentsStack.get(v0);
                        ((BaseFragment)v1_1).clearViews();
                        ((BaseFragment)v1_1).setParentLayout(this);
                        ++v0;
                    }
                }

                return;
            }
        }

        this.animateThemeAfterAnimation = true;
        this.animateSetThemeAfterAnimation = arg9;
    }

    private void checkNeedRebuild() {
        if(this.rebuildAfterAnimation) {
            this.rebuildAllFragmentViews(this.rebuildLastAfterAnimation, this.showLastAfterAnimation);
            this.rebuildAfterAnimation = false;
        }
        else if(this.animateThemeAfterAnimation) {
            this.animateThemedValues(this.animateSetThemeAfterAnimation);
            this.animateSetThemeAfterAnimation = null;
            this.animateThemeAfterAnimation = false;
        }
    }

    public boolean checkTransitionAnimation() {
        if(this.transitionAnimationPreviewMode) {
            return 0;
        }

        if((this.transitionAnimationInProgress) && this.transitionAnimationStartTime < System.currentTimeMillis() - 1500) {
            this.onAnimationEndCheck(true);
        }

        return this.transitionAnimationInProgress;
    }

    public void closeLastFragment(boolean arg13) {
        ViewParent v8;
        int v13;
        if(this.isInSelectMode()) {
            MessagesController.getInstance(UserConfig.selectedAccount).unSelectAllDialogs();
        }

        this.setInSelectMode(false);
        if((this.delegate == null || (this.delegate.needCloseLastFragment(this))) && !this.checkTransitionAnimation()) {
            if(this.fragmentsStack.isEmpty()) {
            }
            else {
                if(this.parentActivity.getCurrentFocus() != null) {
                    AndroidUtilities.hideKeyboard(this.parentActivity.getCurrentFocus());
                }

                this.setInnerTranslationX(0f);
                if((this.inPreviewMode) || (this.transitionAnimationPreviewMode)) {
                label_39:
                    v13 = 1;
                }
                else {
                    if((arg13) && (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true))) {
                        goto label_39;
                    }

                    v13 = 0;
                }

                Object v1 = this.fragmentsStack.get(this.fragmentsStack.size() - 1);
                AnimatorSet v4 = null;
                int v5 = 2;
                Object v3 = this.fragmentsStack.size() > 1 ? this.fragmentsStack.get(this.fragmentsStack.size() - v5) : v4;
                long v6 = 200;
                if(v3 != null) {
                    LinearLayoutContainer v5_1 = this.containerView;
                    this.containerView = this.containerViewBack;
                    this.containerViewBack = v5_1;
                    this.containerView.setVisibility(0);
                    ((BaseFragment)v3).setParentLayout(this);
                    View v5_2 = ((BaseFragment)v3).fragmentView;
                    if(v5_2 == null) {
                        v5_2 = ((BaseFragment)v3).createView(this.parentActivity);
                    }
                    else {
                        v8 = v5_2.getParent();
                        if(v8 != null) {
                            ((BaseFragment)v3).onRemoveFromParent();
                            ((ViewGroup)v8).removeView(v5_2);
                        }
                    }

                    if(((BaseFragment)v3).actionBar != null && (((BaseFragment)v3).actionBar.getAddToContainer())) {
                        if(this.removeActionBarExtraHeight) {
                            ((BaseFragment)v3).actionBar.setOccupyStatusBar(false);
                        }

                        v8 = ((BaseFragment)v3).actionBar.getParent();
                        if(v8 != null) {
                            ((ViewGroup)v8).removeView(((BaseFragment)v3).actionBar);
                        }

                        this.containerView.addView(((BaseFragment)v3).actionBar);
                        ((BaseFragment)v3).actionBar.setTitleOverlayText(this.titleOverlayText, this.subtitleOverlayText, this.overlayAction);
                    }

                    this.containerView.addView(v5_2);
                    ViewGroup$LayoutParams v8_1 = v5_2.getLayoutParams();
                    ((LinearLayout$LayoutParams)v8_1).width = -1;
                    ((LinearLayout$LayoutParams)v8_1).height = -1;
                    ((LinearLayout$LayoutParams)v8_1).leftMargin = 0;
                    ((LinearLayout$LayoutParams)v8_1).rightMargin = 0;
                    ((LinearLayout$LayoutParams)v8_1).bottomMargin = 0;
                    ((LinearLayout$LayoutParams)v8_1).topMargin = 0;
                    v5_2.setLayoutParams(v8_1);
                    ((BaseFragment)v3).onTransitionAnimationStart(true, true);
                    ((BaseFragment)v1).onTransitionAnimationStart(false, false);
                    ((BaseFragment)v3).onResume();
                    this.currentActionBar = ((BaseFragment)v3).actionBar;
                    if(!((BaseFragment)v3).hasOwnBackground && v5_2.getBackground() == null) {
                        v5_2.setBackgroundColor(Theme.getColor("windowBackgroundWhite"));
                    }

                    if(v13 == 0) {
                        this.closeLastFragmentInternalRemoveOld(((BaseFragment)v1));
                    }

                    if(v13 != 0) {
                        this.transitionAnimationStartTime = System.currentTimeMillis();
                        this.transitionAnimationInProgress = true;
                        this.onCloseAnimationEndRunnable = new -$$Lambda$ActionBarLayout$pBlWjaMhbocc2CQiKAJuI1kS-Ds(this, ((BaseFragment)v1), ((BaseFragment)v3));
                        if(!this.inPreviewMode && !this.transitionAnimationPreviewMode) {
                            v4 = ((BaseFragment)v1).onCustomTransitionAnimation(false, new -$$Lambda$ActionBarLayout$r7PgXBy38d_b4XaDN8FoveXk1BE(this));
                        }

                        if(v4 == null) {
                            if(!LinearLayoutContainer.access$1500(this.containerView)) {
                                if(LinearLayoutContainer.access$1500(this.containerViewBack)) {
                                }
                                else {
                                    arg13 = (this.inPreviewMode) || (this.transitionAnimationPreviewMode) ? true : false;
                                    this.startLayoutAnimation(false, true, arg13);
                                    return;
                                }
                            }

                            this.waitingForKeyboardCloseRunnable = new Runnable() {
                                public void run() {
                                    if(ActionBarLayout.this.waitingForKeyboardCloseRunnable != this) {
                                        return;
                                    }

                                    ActionBarLayout.this.waitingForKeyboardCloseRunnable = null;
                                    ActionBarLayout.this.startLayoutAnimation(false, true, false);
                                }
                            };
                            AndroidUtilities.runOnUIThread(this.waitingForKeyboardCloseRunnable, v6);
                            return;
                        }

                        this.currentAnimation = v4;
                        return;
                    }

                    ((BaseFragment)v1).onTransitionAnimationEnd(false, false);
                    ((BaseFragment)v3).onTransitionAnimationEnd(true, true);
                    ((BaseFragment)v3).onBecomeFullyVisible();
                    return;
                }

                if(this.useAlphaAnimations) {
                    this.transitionAnimationStartTime = System.currentTimeMillis();
                    this.transitionAnimationInProgress = true;
                    this.onCloseAnimationEndRunnable = new -$$Lambda$ActionBarLayout$S9HTTIgsI9OBg6Q7_NNccIiX628(this, ((BaseFragment)v1));
                    ArrayList v13_1 = new ArrayList();
                    v13_1.add(ObjectAnimator.ofFloat(this, "alpha", new float[]{1f, 0f}));
                    if(this.backgroundView != null) {
                        v13_1.add(ObjectAnimator.ofFloat(this.backgroundView, "alpha", new float[]{1f, 0f}));
                    }

                    this.currentAnimation = new AnimatorSet();
                    this.currentAnimation.playTogether(((Collection)v13_1));
                    this.currentAnimation.setInterpolator(this.accelerateDecelerateInterpolator);
                    this.currentAnimation.setDuration(v6);
                    this.currentAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator arg2) {
                            ActionBarLayout.this.onAnimationEndCheck(false);
                        }

                        public void onAnimationStart(Animator arg3) {
                            ActionBarLayout.this.transitionAnimationStartTime = System.currentTimeMillis();
                        }
                    });
                    this.currentAnimation.start();
                    return;
                }

                this.removeFragmentFromStackInternal(((BaseFragment)v1));
                v13 = 8;
                this.setVisibility(v13);
                if(this.backgroundView == null) {
                    return;
                }

                this.backgroundView.setVisibility(v13);
            }
        }
    }

    private void closeLastFragmentInternalRemoveOld(BaseFragment arg2) {
        arg2.onPause();
        arg2.onFragmentDestroy();
        arg2.setParentLayout(null);
        this.fragmentsStack.remove(arg2);
        this.containerViewBack.setVisibility(8);
        this.bringChildToFront(this.containerView);
    }

    public void dismissDialogs() {
        if(!this.fragmentsStack.isEmpty()) {
            this.fragmentsStack.get(this.fragmentsStack.size() - 1).dismissCurrentDialig();
        }
    }

    public boolean dispatchKeyEventPreIme(KeyEvent arg3) {
        if(arg3 != null && arg3.getKeyCode() == 4) {
            boolean v1 = true;
            if(arg3.getAction() == 1) {
                if(this.delegate == null || !this.delegate.onPreIme()) {
                    if(super.dispatchKeyEventPreIme(arg3)) {
                    }
                    else {
                        v1 = false;
                    }
                }

                return v1;
            }
        }

        return super.dispatchKeyEventPreIme(arg3);
    }

    protected boolean drawChild(Canvas arg12, View arg13, long arg14) {
        int v0 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        int v1 = (((int)this.innerTranslationX)) + this.getPaddingRight();
        int v2 = this.getPaddingLeft();
        int v3 = this.getPaddingLeft() + v0;
        if(arg13 == this.containerViewBack) {
            v3 = v1;
        }
        else if(arg13 == this.containerView) {
            v2 = v1;
        }

        int v4 = arg12.save();
        int v6 = 0;
        if(!this.transitionAnimationInProgress && !this.inPreviewMode) {
            arg12.clipRect(v2, 0, v3, this.getHeight());
        }

        if(((this.inPreviewMode) || (this.transitionAnimationPreviewMode)) && arg13 == this.containerView) {
            View v5 = this.containerView.getChildAt(0);
            if(v5 != null) {
                this.previewBackgroundDrawable.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
                this.previewBackgroundDrawable.draw(arg12);
                float v8 = 24f;
                int v7 = (this.getMeasuredWidth() - AndroidUtilities.dp(v8)) / 2;
                float v5_1 = (((float)v5.getTop())) + this.containerView.getTranslationY();
                if(Build$VERSION.SDK_INT < 21) {
                    v6 = 20;
                }

                int v5_2 = ((int)(v5_1 - (((float)AndroidUtilities.dp(((float)(v6 + 12)))))));
                Theme.moveUpDrawable.setBounds(v7, v5_2, AndroidUtilities.dp(v8) + v7, AndroidUtilities.dp(v8) + v5_2);
                Theme.moveUpDrawable.draw(arg12);
            }
        }

        boolean v14 = super.drawChild(arg12, arg13, arg14);
        arg12.restoreToCount(v4);
        if(v1 != 0) {
            if(arg13 == this.containerView) {
                float v15 = Math.max(0f, Math.min((((float)(v0 - v1))) / (((float)AndroidUtilities.dp(20f))), 1f));
                ActionBarLayout.layerShadowDrawable.setBounds(v1 - ActionBarLayout.layerShadowDrawable.getIntrinsicWidth(), arg13.getTop(), v1, arg13.getBottom());
                ActionBarLayout.layerShadowDrawable.setAlpha(((int)(v15 * 255f)));
                ActionBarLayout.layerShadowDrawable.draw(arg12);
            }
            else if(arg13 == this.containerViewBack) {
                float v13 = Math.min(0.8f, (((float)(v0 - v1))) / (((float)v0)));
                if(v13 < 0f) {
                    v13 = 0f;
                }

                ActionBarLayout.scrimPaint.setColor((((int)(v13 * 153f))) << 24);
                arg12.drawRect(((float)v2), 0f, ((float)v3), ((float)this.getHeight()), ActionBarLayout.scrimPaint);
            }
        }

        return v14;
    }

    public void drawHeaderShadow(Canvas arg5, int arg6) {
        if(ActionBarLayout.headerShadowDrawable != null) {
            ActionBarLayout.headerShadowDrawable.setBounds(0, arg6, this.getMeasuredWidth(), ActionBarLayout.headerShadowDrawable.getIntrinsicHeight() + arg6);
            ActionBarLayout.headerShadowDrawable.draw(arg5);
        }
    }

    public boolean extendActionMode(Menu arg4) {
        boolean v1 = true;
        if((this.fragmentsStack.isEmpty()) || !this.fragmentsStack.get(this.fragmentsStack.size() - 1).extendActionMode(arg4)) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    public void finishPreviewFragment() {
        if(!this.inPreviewMode && !this.transitionAnimationPreviewMode) {
            return;
        }

        this.closeLastFragment(true);
    }

    public DrawerLayoutContainer getDrawerLayoutContainer() {
        return this.drawerLayoutContainer;
    }

    @Keep public float getInnerTranslationX() {
        return this.innerTranslationX;
    }

    public BaseFragment getLastFragment() {
        if(this.fragmentsStack.isEmpty()) {
            return null;
        }

        return this.fragmentsStack.get(this.fragmentsStack.size() - 1);
    }

    @Keep public float getThemeAnimationValue() {
        return this.themeAnimationValue;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public void init(ArrayList arg4) {
        this.fragmentsStack = arg4;
        this.containerViewBack = new LinearLayoutContainer(this, this.parentActivity);
        this.addView(this.containerViewBack);
        ViewGroup$LayoutParams v4 = this.containerViewBack.getLayoutParams();
        ((FrameLayout$LayoutParams)v4).width = -1;
        ((FrameLayout$LayoutParams)v4).height = -1;
        ((FrameLayout$LayoutParams)v4).gravity = 51;
        this.containerViewBack.setLayoutParams(v4);
        this.containerView = new LinearLayoutContainer(this, this.parentActivity);
        this.addView(this.containerView);
        v4 = this.containerView.getLayoutParams();
        ((FrameLayout$LayoutParams)v4).width = -1;
        ((FrameLayout$LayoutParams)v4).height = -1;
        ((FrameLayout$LayoutParams)v4).gravity = 51;
        this.containerView.setLayoutParams(v4);
        Iterator v4_1 = this.fragmentsStack.iterator();
        while(v4_1.hasNext()) {
            v4_1.next().setParentLayout(this);
        }
    }

    public boolean isInSelectMode() {
        return this.inSelectMode;
    }

    public boolean isShowShadow() {
        return this.showShadow;
    }

    public static void lambda$closeLastFragment$3(ActionBarLayout arg3, BaseFragment arg4, BaseFragment arg5) {
        if((arg3.inPreviewMode) || (arg3.transitionAnimationPreviewMode)) {
            arg3.containerViewBack.setScaleX(1f);
            arg3.containerViewBack.setScaleY(1f);
            arg3.inPreviewMode = false;
            arg3.transitionAnimationPreviewMode = false;
        }
        else {
            arg3.containerViewBack.setTranslationX(0f);
        }

        arg3.closeLastFragmentInternalRemoveOld(arg4);
        arg4.onTransitionAnimationEnd(false, false);
        arg5.onTransitionAnimationEnd(true, true);
        arg5.onBecomeFullyVisible();
    }

    public static void lambda$closeLastFragment$4(ActionBarLayout arg1) {
        arg1.onAnimationEndCheck(false);
    }

    public static void lambda$closeLastFragment$5(ActionBarLayout arg2, BaseFragment arg3) {
        arg2.removeFragmentFromStackInternal(arg3);
        int v3 = 8;
        arg2.setVisibility(v3);
        if(arg2.backgroundView != null) {
            arg2.backgroundView.setVisibility(v3);
        }

        if(arg2.drawerLayoutContainer != null) {
            arg2.drawerLayoutContainer.setAllowOpenDrawer(true, false);
        }
    }

    static void lambda$presentFragment$0(BaseFragment arg2) {
        arg2.onTransitionAnimationEnd(true, false);
        arg2.onBecomeFullyVisible();
    }

    public static void lambda$presentFragment$1(ActionBarLayout arg2, boolean arg3, boolean arg4, BaseFragment arg5, BaseFragment arg6) {
        if(arg3) {
            arg2.inPreviewMode = true;
            arg2.transitionAnimationPreviewMode = false;
            arg2.containerView.setScaleX(1f);
            arg2.containerView.setScaleY(1f);
        }
        else {
            arg2.presentFragmentInternalRemoveOld(arg4, arg5);
            arg2.containerView.setTranslationX(0f);
        }

        arg6.onTransitionAnimationEnd(true, false);
        arg6.onBecomeFullyVisible();
    }

    public static void lambda$presentFragment$2(ActionBarLayout arg1) {
        arg1.onAnimationEndCheck(false);
    }

    public void movePreviewFragment(float arg21) {
        ActionBarLayout v0 = this;
        if(v0.inPreviewMode) {
            if(v0.transitionAnimationPreviewMode) {
            }
            else {
                float v1 = v0.containerView.getTranslationY();
                float v2 = -arg21;
                float v3 = 0f;
                if(v2 > 0f) {
                }
                else if(v2 < (((float)(-AndroidUtilities.dp(60f))))) {
                    v0.inPreviewMode = false;
                    int v6 = 2;
                    Object v4 = v0.fragmentsStack.get(v0.fragmentsStack.size() - v6);
                    Object v5 = v0.fragmentsStack.get(v0.fragmentsStack.size() - 1);
                    if(Build$VERSION.SDK_INT >= 21) {
                        ((BaseFragment)v5).fragmentView.setOutlineProvider(null);
                        ((BaseFragment)v5).fragmentView.setClipToOutline(false);
                    }

                    ViewGroup$LayoutParams v7 = ((BaseFragment)v5).fragmentView.getLayoutParams();
                    ((LinearLayout$LayoutParams)v7).leftMargin = 0;
                    ((LinearLayout$LayoutParams)v7).rightMargin = 0;
                    ((LinearLayout$LayoutParams)v7).bottomMargin = 0;
                    ((LinearLayout$LayoutParams)v7).topMargin = 0;
                    ((BaseFragment)v5).fragmentView.setLayoutParams(v7);
                    v0.presentFragmentInternalRemoveOld(false, ((BaseFragment)v4));
                    AnimatorSet v4_1 = new AnimatorSet();
                    Animator[] v6_1 = new Animator[v6];
                    v6_1[0] = ObjectAnimator.ofFloat(((BaseFragment)v5).fragmentView, "scaleX", new float[]{1f, 1.05f, 1f});
                    v6_1[1] = ObjectAnimator.ofFloat(((BaseFragment)v5).fragmentView, "scaleY", new float[]{1f, 1.05f, 1f});
                    v4_1.playTogether(v6_1);
                    v4_1.setDuration(200);
                    v4_1.setInterpolator(new CubicBezierInterpolator(0.42, 0, 0.58, 1));
                    v4_1.start();
                    v0.performHapticFeedback(3);
                    ((BaseFragment)v5).setInPreviewMode(false);
                }
                else {
                    v3 = v2;
                }

                if(v1 == v3) {
                    return;
                }

                v0.containerView.setTranslationY(v3);
                this.invalidate();
            }
        }
    }

    public void onActionModeFinished(Object arg2) {
        if(this.currentActionBar != null) {
            this.currentActionBar.setVisibility(0);
        }

        this.inActionMode = false;
    }

    public void onActionModeStarted(Object arg2) {
        if(this.currentActionBar != null) {
            this.currentActionBar.setVisibility(8);
        }

        this.inActionMode = true;
    }

    private void onAnimationEndCheck(boolean arg3) {
        this.onCloseAnimationEnd();
        this.onOpenAnimationEnd();
        Runnable v1 = null;
        if(this.waitingForKeyboardCloseRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.waitingForKeyboardCloseRunnable);
            this.waitingForKeyboardCloseRunnable = v1;
        }

        if(this.currentAnimation != null) {
            if(arg3) {
                this.currentAnimation.cancel();
            }

            this.currentAnimation = ((AnimatorSet)v1);
        }

        if(this.animationRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.animationRunnable);
            this.animationRunnable = v1;
        }

        this.setAlpha(1f);
        this.containerView.setAlpha(1f);
        this.containerView.setScaleX(1f);
        this.containerView.setScaleY(1f);
        this.containerViewBack.setAlpha(1f);
        this.containerViewBack.setScaleX(1f);
        this.containerViewBack.setScaleY(1f);
    }

    public void onBackPressed() {
        if(!this.transitionAnimationPreviewMode && !this.startedTracking && !this.checkTransitionAnimation()) {
            if(this.fragmentsStack.isEmpty()) {
            }
            else {
                if(this.currentActionBar != null && (this.currentActionBar.isSearchFieldVisible)) {
                    this.currentActionBar.closeSearchField();
                    return;
                }

                if(!this.fragmentsStack.get(this.fragmentsStack.size() - 1).onBackPressed()) {
                    return;
                }

                if(this.fragmentsStack.isEmpty()) {
                    return;
                }

                this.closeLastFragment(true);
            }
        }
    }

    private void onCloseAnimationEnd() {
        if((this.transitionAnimationInProgress) && this.onCloseAnimationEndRunnable != null) {
            this.transitionAnimationInProgress = false;
            this.transitionAnimationPreviewMode = false;
            this.transitionAnimationStartTime = 0;
            this.onCloseAnimationEndRunnable.run();
            this.onCloseAnimationEndRunnable = null;
            this.checkNeedRebuild();
        }
    }

    public void onConfigurationChanged(Configuration arg3) {
        super.onConfigurationChanged(arg3);
        if(!this.fragmentsStack.isEmpty()) {
            Object v0 = this.fragmentsStack.get(this.fragmentsStack.size() - 1);
            ((BaseFragment)v0).onConfigurationChanged(arg3);
            if((((BaseFragment)v0).visibleDialog instanceof BottomSheet)) {
                ((BaseFragment)v0).visibleDialog.onConfigurationChanged(arg3);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent arg2) {
        boolean v2 = (this.animationInProgress) || (this.checkTransitionAnimation()) || (this.onTouchEvent(arg2)) ? true : false;
        return v2;
    }

    public boolean onKeyUp(int arg2, KeyEvent arg3) {
        if(arg2 == 82 && !this.checkTransitionAnimation() && !this.startedTracking && this.currentActionBar != null) {
            this.currentActionBar.onMenuButtonPressed();
        }

        return super.onKeyUp(arg2, arg3);
    }

    public void onLowMemory() {
        Iterator v0 = this.fragmentsStack.iterator();
        while(v0.hasNext()) {
            v0.next().onLowMemory();
        }
    }

    private void onOpenAnimationEnd() {
        if((this.transitionAnimationInProgress) && this.onOpenAnimationEndRunnable != null) {
            this.transitionAnimationInProgress = false;
            this.transitionAnimationPreviewMode = false;
            this.transitionAnimationStartTime = 0;
            this.onOpenAnimationEndRunnable.run();
            this.onOpenAnimationEndRunnable = null;
            this.checkNeedRebuild();
        }
    }

    public void onPause() {
        if(!this.fragmentsStack.isEmpty()) {
            this.fragmentsStack.get(this.fragmentsStack.size() - 1).onPause();
        }
    }

    public void onResume() {
        if(this.transitionAnimationInProgress) {
            if(this.currentAnimation != null) {
                this.currentAnimation.cancel();
                this.currentAnimation = null;
            }

            if(this.onCloseAnimationEndRunnable != null) {
                this.onCloseAnimationEnd();
                goto label_15;
            }

            if(this.onOpenAnimationEndRunnable == null) {
                goto label_15;
            }

            this.onOpenAnimationEnd();
        }

    label_15:
        if(!this.fragmentsStack.isEmpty()) {
            this.fragmentsStack.get(this.fragmentsStack.size() - 1).onResume();
        }
    }

    private void onSlideAnimationEnd(boolean arg3) {
        ViewParent v0_1;
        Object v3;
        if(!arg3) {
            v3 = this.fragmentsStack.get(this.fragmentsStack.size() - 1);
            ((BaseFragment)v3).onPause();
            ((BaseFragment)v3).onFragmentDestroy();
            ((BaseFragment)v3).setParentLayout(null);
            this.fragmentsStack.remove(this.fragmentsStack.size() - 1);
            LinearLayoutContainer v3_1 = this.containerView;
            this.containerView = this.containerViewBack;
            this.containerViewBack = v3_1;
            this.bringChildToFront(this.containerView);
            v3 = this.fragmentsStack.get(this.fragmentsStack.size() - 1);
            this.currentActionBar = ((BaseFragment)v3).actionBar;
            ((BaseFragment)v3).onResume();
            ((BaseFragment)v3).onBecomeFullyVisible();
        }
        else {
            int v0 = 2;
            if(this.fragmentsStack.size() >= v0) {
                v3 = this.fragmentsStack.get(this.fragmentsStack.size() - v0);
                ((BaseFragment)v3).onPause();
                if(((BaseFragment)v3).fragmentView != null) {
                    v0_1 = ((BaseFragment)v3).fragmentView.getParent();
                    if(v0_1 != null) {
                        ((BaseFragment)v3).onRemoveFromParent();
                        ((ViewGroup)v0_1).removeView(((BaseFragment)v3).fragmentView);
                    }
                }

                if(((BaseFragment)v3).actionBar == null) {
                    goto label_59;
                }

                if(!((BaseFragment)v3).actionBar.getAddToContainer()) {
                    goto label_59;
                }

                v0_1 = ((BaseFragment)v3).actionBar.getParent();
                if(v0_1 == null) {
                    goto label_59;
                }

                ((ViewGroup)v0_1).removeView(((BaseFragment)v3).actionBar);
            }
        }

    label_59:
        this.containerViewBack.setVisibility(8);
        this.startedTracking = false;
        this.animationInProgress = false;
        this.containerView.setTranslationX(0f);
        this.containerViewBack.setTranslationX(0f);
        this.setInnerTranslationX(0f);
    }

    public boolean onTouchEvent(MotionEvent arg11) {
        Animator[] v3_1;
        boolean v5_1;
        float v6;
        float v0_1;
        if(!this.checkTransitionAnimation() && !this.inActionMode && !this.animationInProgress) {
            if(this.fragmentsStack.size() > 1) {
                if(arg11 != null && arg11.getAction() == 0 && !this.startedTracking && !this.maybeStartTracking) {
                    if(!this.fragmentsStack.get(this.fragmentsStack.size() - 1).swipeBackEnabled) {
                        return 0;
                    }
                    else {
                        this.startedTrackingPointerId = arg11.getPointerId(0);
                        this.maybeStartTracking = true;
                        this.startedTrackingX = ((int)arg11.getX());
                        this.startedTrackingY = ((int)arg11.getY());
                        if(this.velocityTracker != null) {
                            this.velocityTracker.clear();
                        }
                        else {
                        }

                        goto label_240;
                    }
                }

                int v0 = 3;
                int v3 = 2;
                if(arg11 != null && arg11.getAction() == v3 && arg11.getPointerId(0) == this.startedTrackingPointerId) {
                    if(this.velocityTracker == null) {
                        this.velocityTracker = VelocityTracker.obtain();
                    }

                    int v1 = Math.max(0, ((int)(arg11.getX() - (((float)this.startedTrackingX)))));
                    v3 = Math.abs((((int)arg11.getY())) - this.startedTrackingY);
                    this.velocityTracker.addMovement(arg11);
                    if((this.maybeStartTracking) && !this.startedTracking && (((float)v1)) >= AndroidUtilities.getPixelsInCM(0.4f, true) && Math.abs(v1) / v0 > v3) {
                        this.prepareForMoving(arg11);
                        goto label_240;
                    }

                    if(!this.startedTracking) {
                        goto label_240;
                    }

                    if(!this.beginTrackingSent) {
                        if(this.parentActivity.getCurrentFocus() != null) {
                            AndroidUtilities.hideKeyboard(this.parentActivity.getCurrentFocus());
                        }

                        this.fragmentsStack.get(this.fragmentsStack.size() - 1).onBeginSlide();
                        this.beginTrackingSent = true;
                    }

                    v0_1 = ((float)v1);
                    this.containerView.setTranslationX(v0_1);
                    this.setInnerTranslationX(v0_1);
                    goto label_240;
                }

                VelocityTracker v4 = null;
                if(arg11 == null || arg11.getPointerId(0) != this.startedTrackingPointerId) {
                label_232:
                    if(arg11 != null) {
                        goto label_240;
                    }

                    this.maybeStartTracking = false;
                    this.startedTracking = false;
                    if(this.velocityTracker == null) {
                        goto label_240;
                    }
                }
                else {
                    if(arg11.getAction() != v0 && arg11.getAction() != 1 && arg11.getAction() != 6) {
                        goto label_232;
                    }

                    if(this.velocityTracker == null) {
                        this.velocityTracker = VelocityTracker.obtain();
                    }

                    this.velocityTracker.computeCurrentVelocity(1000);
                    float v5 = 3500f;
                    if(!this.inPreviewMode && !this.transitionAnimationPreviewMode && !this.startedTracking && (this.fragmentsStack.get(this.fragmentsStack.size() - 1).swipeBackEnabled)) {
                        v0_1 = this.velocityTracker.getXVelocity();
                        v6 = this.velocityTracker.getYVelocity();
                        if(v0_1 >= v5 && v0_1 > Math.abs(v6)) {
                            this.prepareForMoving(arg11);
                            if(!this.beginTrackingSent) {
                                if(this.getContext().getCurrentFocus() != null) {
                                    AndroidUtilities.hideKeyboard(this.getContext().getCurrentFocus());
                                }

                                this.beginTrackingSent = true;
                            }
                        }
                    }

                    if(this.startedTracking) {
                        float v11 = this.containerView.getX();
                        AnimatorSet v0_2 = new AnimatorSet();
                        v6 = this.velocityTracker.getXVelocity();
                        float v7 = this.velocityTracker.getYVelocity();
                        if(v11 < (((float)this.containerView.getMeasuredWidth())) / 3f) {
                            if(v6 >= v5 && v6 >= v7) {
                                goto label_170;
                            }

                            v5_1 = true;
                        }
                        else {
                        label_170:
                            v5_1 = false;
                        }

                        if(!v5_1) {
                            v11 = (((float)this.containerView.getMeasuredWidth())) - v11;
                            v3_1 = new Animator[v3];
                            v3_1[0] = ObjectAnimator.ofFloat(this.containerView, "translationX", new float[]{((float)this.containerView.getMeasuredWidth())});
                            v3_1[1] = ObjectAnimator.ofFloat(this, "innerTranslationX", new float[]{((float)this.containerView.getMeasuredWidth())});
                        }
                        else {
                            v3_1 = new Animator[v3];
                            v3_1[0] = ObjectAnimator.ofFloat(this.containerView, "translationX", new float[]{0f});
                            v3_1[1] = ObjectAnimator.ofFloat(this, "innerTranslationX", new float[]{0f});
                        }

                        v0_2.playTogether(v3_1);
                        v0_2.setDuration(((long)Math.max(((int)(200f / (((float)this.containerView.getMeasuredWidth())) * v11)), 50)));
                        v0_2.addListener(new AnimatorListenerAdapter(v5_1) {
                            public void onAnimationEnd(Animator arg2) {
                                ActionBarLayout.this.onSlideAnimationEnd(this.val$backAnimation);
                            }
                        });
                        v0_2.start();
                        this.animationInProgress = true;
                    }
                    else {
                        this.maybeStartTracking = false;
                        this.startedTracking = false;
                    }

                    if(this.velocityTracker == null) {
                        goto label_240;
                    }
                }

                this.velocityTracker.recycle();
                this.velocityTracker = v4;
            }

        label_240:
            return this.startedTracking;
        }

        return 0;
    }

    private void prepareForMoving(MotionEvent arg7) {
        this.maybeStartTracking = false;
        this.startedTracking = true;
        this.startedTrackingX = ((int)arg7.getX());
        this.containerViewBack.setVisibility(0);
        this.beginTrackingSent = false;
        Object v7 = this.fragmentsStack.get(this.fragmentsStack.size() - 2);
        View v1 = ((BaseFragment)v7).fragmentView;
        if(v1 == null) {
            v1 = ((BaseFragment)v7).createView(this.parentActivity);
        }

        ViewParent v2 = v1.getParent();
        if(v2 != null) {
            ((BaseFragment)v7).onRemoveFromParent();
            ((ViewGroup)v2).removeView(v1);
        }

        if(((BaseFragment)v7).actionBar != null && (((BaseFragment)v7).actionBar.getAddToContainer())) {
            v2 = ((BaseFragment)v7).actionBar.getParent();
            if(v2 != null) {
                ((ViewGroup)v2).removeView(((BaseFragment)v7).actionBar);
            }

            if(this.removeActionBarExtraHeight) {
                ((BaseFragment)v7).actionBar.setOccupyStatusBar(false);
            }

            this.containerViewBack.addView(((BaseFragment)v7).actionBar);
            ((BaseFragment)v7).actionBar.setTitleOverlayText(this.titleOverlayText, this.subtitleOverlayText, this.overlayAction);
        }

        this.containerViewBack.addView(v1);
        ViewGroup$LayoutParams v2_1 = v1.getLayoutParams();
        ((LinearLayout$LayoutParams)v2_1).width = -1;
        ((LinearLayout$LayoutParams)v2_1).height = -1;
        ((LinearLayout$LayoutParams)v2_1).leftMargin = 0;
        ((LinearLayout$LayoutParams)v2_1).rightMargin = 0;
        ((LinearLayout$LayoutParams)v2_1).bottomMargin = 0;
        ((LinearLayout$LayoutParams)v2_1).topMargin = 0;
        v1.setLayoutParams(v2_1);
        if(!((BaseFragment)v7).hasOwnBackground && v1.getBackground() == null) {
            v1.setBackgroundColor(Theme.getColor("windowBackgroundWhite"));
        }

        ((BaseFragment)v7).onResume();
    }

    public boolean presentFragment(BaseFragment arg7) {
        return this.presentFragment(arg7, false, false, true, false);
    }

    public boolean presentFragment(BaseFragment arg17, boolean arg18, boolean arg19, boolean arg20, boolean arg21) {
        Runnable v0_3;
        ViewParent v2;
        BaseFragment v4_1;
        int v0_1;
        ActionBarLayout v6 = this;
        BaseFragment v7 = arg17;
        boolean v3 = arg18;
        boolean v0 = arg19;
        boolean v8 = arg21;
        if(!this.checkTransitionAnimation() && (v6.delegate == null || !arg20 || (v6.delegate.needPresentFragment(v7, v3, v0, v6))) && (arg17.onFragmentCreate())) {
            v7.setInPreviewMode(v8);
            if(v6.parentActivity.getCurrentFocus() != null) {
                AndroidUtilities.hideKeyboard(v6.parentActivity.getCurrentFocus());
            }

            if(!v8) {
                if(!v0 && (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true))) {
                    goto label_34;
                }

                v0_1 = 0;
            }
            else {
            label_34:
                v0_1 = 1;
            }

            if(!v6.fragmentsStack.isEmpty()) {
                Object v4 = v6.fragmentsStack.get(v6.fragmentsStack.size() - 1);
            }
            else {
                v4_1 = null;
            }

            v7.setParentLayout(v6);
            View v1 = v7.fragmentView;
            if(v1 == null) {
                v1 = v7.createView(v6.parentActivity);
            }
            else {
                v2 = v1.getParent();
                if(v2 != null) {
                    arg17.onRemoveFromParent();
                    ((ViewGroup)v2).removeView(v1);
                }
            }

            if(v7.actionBar != null && (v7.actionBar.getAddToContainer())) {
                if(v6.removeActionBarExtraHeight) {
                    v7.actionBar.setOccupyStatusBar(false);
                }

                v2 = v7.actionBar.getParent();
                if(v2 != null) {
                    ((ViewGroup)v2).removeView(v7.actionBar);
                }

                v6.containerViewBack.addView(v7.actionBar);
                v7.actionBar.setTitleOverlayText(v6.titleOverlayText, v6.subtitleOverlayText, v6.overlayAction);
            }

            v6.containerViewBack.addView(v1);
            ViewGroup$LayoutParams v2_1 = v1.getLayoutParams();
            ((LinearLayout$LayoutParams)v2_1).width = -1;
            ((LinearLayout$LayoutParams)v2_1).height = -1;
            if(v8) {
                int v5 = AndroidUtilities.dp(8f);
                ((LinearLayout$LayoutParams)v2_1).leftMargin = v5;
                ((LinearLayout$LayoutParams)v2_1).rightMargin = v5;
                v5 = AndroidUtilities.dp(46f);
                ((LinearLayout$LayoutParams)v2_1).bottomMargin = v5;
                ((LinearLayout$LayoutParams)v2_1).topMargin = v5;
                ((LinearLayout$LayoutParams)v2_1).topMargin += AndroidUtilities.statusBarHeight;
            }
            else {
                ((LinearLayout$LayoutParams)v2_1).leftMargin = 0;
                ((LinearLayout$LayoutParams)v2_1).rightMargin = 0;
                ((LinearLayout$LayoutParams)v2_1).bottomMargin = 0;
                ((LinearLayout$LayoutParams)v2_1).topMargin = 0;
            }

            v1.setLayoutParams(v2_1);
            v6.fragmentsStack.add(v7);
            arg17.onResume();
            v6.currentActionBar = v7.actionBar;
            if(!v7.hasOwnBackground && v1.getBackground() == null) {
                v1.setBackgroundColor(Theme.getColor("windowBackgroundWhite"));
            }

            LinearLayoutContainer v2_2 = v6.containerView;
            v6.containerView = v6.containerViewBack;
            v6.containerViewBack = v2_2;
            v6.containerView.setVisibility(0);
            v6.setInnerTranslationX(0f);
            v6.containerView.setTranslationY(0f);
            if(v8) {
                if(Build$VERSION.SDK_INT >= 21) {
                    v1.setOutlineProvider(new ViewOutlineProvider() {
                        @TargetApi(value=21) public void getOutline(View arg7, Outline arg8) {
                            arg8.setRoundRect(0, AndroidUtilities.statusBarHeight, arg7.getMeasuredWidth(), arg7.getMeasuredHeight(), ((float)AndroidUtilities.dp(6f)));
                        }
                    });
                    v1.setClipToOutline(true);
                    v1.setElevation(((float)AndroidUtilities.dp(4f)));
                }

                if(v6.previewBackgroundDrawable == null) {
                    v6.previewBackgroundDrawable = new ColorDrawable(-2147483648);
                }

                v6.previewBackgroundDrawable.setAlpha(0);
                Theme.moveUpDrawable.setAlpha(0);
            }

            v6.bringChildToFront(v6.containerView);
            if(v0_1 == 0) {
                v6.presentFragmentInternalRemoveOld(v3, v4_1);
                if(v6.backgroundView != null) {
                    v6.backgroundView.setVisibility(0);
                }
            }

            float v13 = 1f;
            if(v0_1 != 0 || (v8)) {
                long v14 = 200;
                if((v6.useAlphaAnimations) && v6.fragmentsStack.size() == 1) {
                    v6.presentFragmentInternalRemoveOld(v3, v4_1);
                    v6.transitionAnimationStartTime = System.currentTimeMillis();
                    v6.transitionAnimationInProgress = true;
                    v6.onOpenAnimationEndRunnable = new -$$Lambda$ActionBarLayout$pYh6HgDiwfydlsh9Xn7223Bd-IA(v7);
                    ArrayList v0_2 = new ArrayList();
                    int v2_3 = 2;
                    v0_2.add(ObjectAnimator.ofFloat(v6, "alpha", new float[]{0f, 1f}));
                    if(v6.backgroundView != null) {
                        v6.backgroundView.setVisibility(0);
                        v0_2.add(ObjectAnimator.ofFloat(v6.backgroundView, "alpha", new float[]{0f, 1f}));
                    }

                    v7.onTransitionAnimationStart(true, false);
                    v6.currentAnimation = new AnimatorSet();
                    v6.currentAnimation.playTogether(((Collection)v0_2));
                    v6.currentAnimation.setInterpolator(v6.accelerateDecelerateInterpolator);
                    v6.currentAnimation.setDuration(v14);
                    v6.currentAnimation.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator arg2) {
                            ActionBarLayout.this.onAnimationEndCheck(false);
                        }
                    });
                    v6.currentAnimation.start();
                    return 1;
                }

                v6.transitionAnimationPreviewMode = v8;
                v6.transitionAnimationStartTime = System.currentTimeMillis();
                v6.transitionAnimationInProgress = true;
                v6.onOpenAnimationEndRunnable = new -$$Lambda$ActionBarLayout$gS41pICx_migujhqTs-lczeRv1Y(this, arg21, arg18, v4_1, arg17);
                v7.onTransitionAnimationStart(true, false);
                AnimatorSet v11 = !v8 ? v7.onCustomTransitionAnimation(true, new -$$Lambda$ActionBarLayout$ZVBs3Yp413UBaNwwGzpjbW7oZTc(v6)) : null;
                if(v11 == null) {
                    v6.containerView.setAlpha(0f);
                    if(v8) {
                        v6.containerView.setTranslationX(0f);
                        v6.containerView.setScaleX(0.9f);
                        v6.containerView.setScaleY(0.9f);
                    }
                    else {
                        v6.containerView.setTranslationX(48f);
                        v6.containerView.setScaleX(v13);
                        v6.containerView.setScaleY(v13);
                    }

                    if((LinearLayoutContainer.access$1500(v6.containerView)) || (LinearLayoutContainer.access$1500(v6.containerViewBack))) {
                        v6.waitingForKeyboardCloseRunnable = new Runnable(v8) {
                            public void run() {
                                if(ActionBarLayout.this.waitingForKeyboardCloseRunnable != this) {
                                    return;
                                }

                                ActionBarLayout.this.waitingForKeyboardCloseRunnable = null;
                                ActionBarLayout.this.startLayoutAnimation(true, true, this.val$preview);
                            }
                        };
                        v0_3 = v6.waitingForKeyboardCloseRunnable;
                    }
                    else if(arg17.needDelayOpenAnimation()) {
                        v6.delayedOpenAnimationRunnable = new Runnable(v8) {
                            public void run() {
                                if(ActionBarLayout.this.delayedOpenAnimationRunnable != this) {
                                    return;
                                }

                                ActionBarLayout.this.delayedOpenAnimationRunnable = null;
                                ActionBarLayout.this.startLayoutAnimation(true, true, this.val$preview);
                            }
                        };
                        v0_3 = v6.delayedOpenAnimationRunnable;
                    }
                    else {
                        v6.startLayoutAnimation(true, true, v8);
                        return 1;
                    }

                    AndroidUtilities.runOnUIThread(v0_3, v14);
                    return 1;
                }

                v6.containerView.setAlpha(v13);
                v6.containerView.setTranslationX(0f);
                v6.currentAnimation = v11;
            }
            else {
                if(v6.backgroundView != null) {
                    v6.backgroundView.setAlpha(v13);
                    v6.backgroundView.setVisibility(0);
                }

                v7.onTransitionAnimationStart(true, false);
                v7.onTransitionAnimationEnd(true, false);
                arg17.onBecomeFullyVisible();
            }

            return 1;
        }

        return 0;
    }

    public boolean presentFragment(BaseFragment arg7, boolean arg8) {
        return this.presentFragment(arg7, arg8, false, true, false);
    }

    public boolean presentFragmentAsPreview(BaseFragment arg7) {
        return this.presentFragment(arg7, false, false, true, true);
    }

    private void presentFragmentInternalRemoveOld(boolean arg2, BaseFragment arg3) {
        ViewParent v2;
        if(arg3 == null) {
            return;
        }

        arg3.onPause();
        if(arg2) {
            arg3.onFragmentDestroy();
            arg3.setParentLayout(null);
            this.fragmentsStack.remove(arg3);
        }
        else {
            if(arg3.fragmentView != null) {
                v2 = arg3.fragmentView.getParent();
                if(v2 != null) {
                    arg3.onRemoveFromParent();
                    ((ViewGroup)v2).removeView(arg3.fragmentView);
                }
            }

            if(arg3.actionBar == null) {
                goto label_28;
            }

            if(!arg3.actionBar.getAddToContainer()) {
                goto label_28;
            }

            v2 = arg3.actionBar.getParent();
            if(v2 == null) {
                goto label_28;
            }

            ((ViewGroup)v2).removeView(arg3.actionBar);
        }

    label_28:
        this.containerViewBack.setVisibility(8);
    }

    public void rebuildAllFragmentViews(boolean arg4, boolean arg5) {
        if(!this.transitionAnimationInProgress) {
            if(this.startedTracking) {
            }
            else {
                int v0 = this.fragmentsStack.size();
                if(!arg4) {
                    --v0;
                }

                if(this.inPreviewMode) {
                    --v0;
                }

                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.fragmentsStack.get(v1).clearViews();
                    this.fragmentsStack.get(v1).setParentLayout(this);
                }

                if(this.delegate != null) {
                    this.delegate.onRebuildAllFragments(this, arg4);
                }

                if(arg5) {
                    this.showLastFragment();
                }

                return;
            }
        }

        this.rebuildAfterAnimation = true;
        this.rebuildLastAfterAnimation = arg4;
        this.showLastAfterAnimation = arg5;
    }

    public void removeAllFragments() {
        while(this.fragmentsStack.size() > 0) {
            this.removeFragmentFromStackInternal(this.fragmentsStack.get(0));
        }
    }

    public void removeFragmentFromStack(int arg2) {
        if(arg2 >= this.fragmentsStack.size()) {
            return;
        }

        this.removeFragmentFromStackInternal(this.fragmentsStack.get(arg2));
    }

    public void removeFragmentFromStack(BaseFragment arg3) {
        if(!this.useAlphaAnimations || this.fragmentsStack.size() != 1 || !AndroidUtilities.isTablet()) {
            this.removeFragmentFromStackInternal(arg3);
        }
        else {
            this.closeLastFragment(true);
        }
    }

    private void removeFragmentFromStackInternal(BaseFragment arg2) {
        arg2.onPause();
        arg2.onFragmentDestroy();
        arg2.setParentLayout(null);
        this.fragmentsStack.remove(arg2);
    }

    public void requestDisallowInterceptTouchEvent(boolean arg2) {
        this.onTouchEvent(null);
        super.requestDisallowInterceptTouchEvent(arg2);
    }

    public void resumeDelayedFragmentAnimation() {
        if(this.delayedOpenAnimationRunnable == null) {
            return;
        }

        AndroidUtilities.cancelRunOnUIThread(this.delayedOpenAnimationRunnable);
        this.delayedOpenAnimationRunnable.run();
        this.delayedOpenAnimationRunnable = null;
    }

    public void setBackgroundView(View arg1) {
        this.backgroundView = arg1;
    }

    public void setDelegate(ActionBarLayoutDelegate arg1) {
        this.delegate = arg1;
    }

    public void setDrawerLayoutContainer(DrawerLayoutContainer arg1) {
        this.drawerLayoutContainer = arg1;
    }

    public void setInSelectMode(boolean arg1) {
        this.inSelectMode = arg1;
    }

    @Keep public void setInnerTranslationX(float arg1) {
        this.innerTranslationX = arg1;
        this.invalidate();
    }

    public void setRemoveActionBarExtraHeight(boolean arg1) {
        this.removeActionBarExtraHeight = arg1;
    }

    public void setShowShadow(boolean arg1) {
        this.showShadow = arg1;
    }

    @Keep public void setThemeAnimationValue(float arg13) {
        this.themeAnimationValue = arg13;
        int v1;
        for(v1 = 0; v1 < 2; ++v1) {
            if(this.themeAnimatorDescriptions[v1] != null) {
                int v2;
                for(v2 = 0; v2 < this.themeAnimatorDescriptions[v1].length; ++v2) {
                    int v3 = Color.red(this.animateEndColors[v1][v2]);
                    int v4 = Color.green(this.animateEndColors[v1][v2]);
                    int v5 = Color.blue(this.animateEndColors[v1][v2]);
                    int v6 = Color.alpha(this.animateEndColors[v1][v2]);
                    int v7 = Color.red(this.animateStartColors[v1][v2]);
                    int v8 = Color.green(this.animateStartColors[v1][v2]);
                    int v9 = Color.blue(this.animateStartColors[v1][v2]);
                    int v10 = Color.alpha(this.animateStartColors[v1][v2]);
                    this.themeAnimatorDescriptions[v1][v2].setColor(Color.argb(Math.min(255, ((int)((((float)v10)) + (((float)(v6 - v10))) * arg13))), Math.min(255, ((int)((((float)v7)) + (((float)(v3 - v7))) * arg13))), Math.min(255, ((int)((((float)v8)) + (((float)(v4 - v8))) * arg13))), Math.min(255, ((int)((((float)v9)) + (((float)(v5 - v9))) * arg13)))), false, false);
                }

                if(this.themeAnimatorDelegate[v1] != null) {
                    this.themeAnimatorDelegate[v1].didSetColor();
                }
            }
        }
    }

    public void setTitleOverlayText(String arg3, String arg4, Runnable arg5) {
        this.titleOverlayText = arg3;
        this.subtitleOverlayText = arg4;
        this.overlayAction = arg5;
        int v3;
        for(v3 = 0; v3 < this.fragmentsStack.size(); ++v3) {
            Object v4 = this.fragmentsStack.get(v3);
            if(((BaseFragment)v4).actionBar != null) {
                ((BaseFragment)v4).actionBar.setTitleOverlayText(this.titleOverlayText, this.subtitleOverlayText, arg5);
            }
        }
    }

    public void setUseAlphaAnimations(boolean arg1) {
        this.useAlphaAnimations = arg1;
    }

    public void showLastFragment() {
        ViewParent v3;
        if(this.fragmentsStack.isEmpty()) {
            return;
        }

        int v1;
        for(v1 = 0; v1 < this.fragmentsStack.size() - 1; ++v1) {
            Object v2 = this.fragmentsStack.get(v1);
            if(((BaseFragment)v2).actionBar != null && (((BaseFragment)v2).actionBar.getAddToContainer())) {
                v3 = ((BaseFragment)v2).actionBar.getParent();
                if(v3 != null) {
                    ((ViewGroup)v3).removeView(((BaseFragment)v2).actionBar);
                }
            }

            if(((BaseFragment)v2).fragmentView != null) {
                v3 = ((BaseFragment)v2).fragmentView.getParent();
                if(v3 != null) {
                    ((BaseFragment)v2).onPause();
                    ((BaseFragment)v2).onRemoveFromParent();
                    ((ViewGroup)v3).removeView(((BaseFragment)v2).fragmentView);
                }
            }
        }

        Object v1_1 = this.fragmentsStack.get(this.fragmentsStack.size() - 1);
        ((BaseFragment)v1_1).setParentLayout(this);
        View v2_1 = ((BaseFragment)v1_1).fragmentView;
        if(v2_1 == null) {
            v2_1 = ((BaseFragment)v1_1).createView(this.parentActivity);
        }
        else {
            v3 = v2_1.getParent();
            if(v3 != null) {
                ((BaseFragment)v1_1).onRemoveFromParent();
                ((ViewGroup)v3).removeView(v2_1);
            }
        }

        if(((BaseFragment)v1_1).actionBar != null && (((BaseFragment)v1_1).actionBar.getAddToContainer())) {
            if(this.removeActionBarExtraHeight) {
                ((BaseFragment)v1_1).actionBar.setOccupyStatusBar(false);
            }

            ViewParent v0 = ((BaseFragment)v1_1).actionBar.getParent();
            if(v0 != null) {
                ((ViewGroup)v0).removeView(((BaseFragment)v1_1).actionBar);
            }

            this.containerView.addView(((BaseFragment)v1_1).actionBar);
            ((BaseFragment)v1_1).actionBar.setTitleOverlayText(this.titleOverlayText, this.subtitleOverlayText, this.overlayAction);
        }

        this.containerView.addView(v2_1, LayoutHelper.createLinear(-1, -1));
        ((BaseFragment)v1_1).onResume();
        this.currentActionBar = ((BaseFragment)v1_1).actionBar;
        if(!((BaseFragment)v1_1).hasOwnBackground && v2_1.getBackground() == null) {
            v2_1.setBackgroundColor(Theme.getColor("windowBackgroundWhite"));
        }
    }

    public void startActivityForResult(Intent arg2, int arg3) {
        if(this.parentActivity == null) {
            return;
        }

        if(this.transitionAnimationInProgress) {
            if(this.currentAnimation != null) {
                this.currentAnimation.cancel();
                this.currentAnimation = null;
            }

            if(this.onCloseAnimationEndRunnable != null) {
                this.onCloseAnimationEnd();
            }
            else if(this.onOpenAnimationEndRunnable != null) {
                this.onOpenAnimationEnd();
            }

            this.containerView.invalidate();
            if(arg2 == null) {
                return;
            }

            goto label_23;
        }
        else {
            if(arg2 == null) {
                return;
            }

        label_23:
            this.parentActivity.startActivityForResult(arg2, arg3);
        }
    }

    private void startLayoutAnimation(boolean arg5, boolean arg6, boolean arg7) {
        if(arg6) {
            this.animationProgress = 0f;
            this.lastFrameTime = System.nanoTime() / 1000000;
        }

        org.telegram.ui.ActionBar.ActionBarLayout$2 v0 = new Runnable(arg6, arg5, arg7) {
            public void run() {
                // Method was not decompiled
            }
        };
        this.animationRunnable = ((Runnable)v0);
        AndroidUtilities.runOnUIThread(((Runnable)v0));
    }
}

