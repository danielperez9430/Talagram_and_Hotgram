package org.telegram.ui.ActionBar;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.Keep;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.LayoutHelper;

public class ActionBarPopupWindow extends PopupWindow {
    public class ActionBarPopupWindowLayout extends FrameLayout {
        private boolean animationEnabled;
        private int backAlpha;
        private float backScaleX;
        private float backScaleY;
        protected Drawable backgroundDrawable;
        private int lastStartedChild;
        protected LinearLayout linearLayout;
        private OnDispatchKeyEventListener mOnDispatchKeyEventListener;
        private HashMap positions;
        private ScrollView scrollView;
        private boolean showedFromBotton;

        public ActionBarPopupWindowLayout(Context arg6) {
            super(arg6);
            this.backScaleX = 1f;
            this.backScaleY = 1f;
            this.backAlpha = 255;
            this.lastStartedChild = 0;
            this.animationEnabled = ActionBarPopupWindow.allowAnimation;
            this.positions = new HashMap();
            this.backgroundDrawable = this.getResources().getDrawable(2131231500).mutate();
            this.backgroundDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor("actionBarDefaultSubmenuBackground"), PorterDuff$Mode.MULTIPLY));
            this.setPadding(AndroidUtilities.dp(8f), AndroidUtilities.dp(8f), AndroidUtilities.dp(8f), AndroidUtilities.dp(8f));
            this.setWillNotDraw(false);
            float v1 = -2f;
            int v2 = -2;
            try {
                this.scrollView = new ScrollView(arg6);
                this.scrollView.setVerticalScrollBarEnabled(false);
                this.addView(this.scrollView, LayoutHelper.createFrame(v2, v1));
            }
            catch(Throwable v0) {
                FileLog.e(v0);
            }

            this.linearLayout = new LinearLayout(arg6);
            this.linearLayout.setOrientation(1);
            if(this.scrollView != null) {
                this.scrollView.addView(this.linearLayout, new FrameLayout$LayoutParams(v2, v2));
            }
            else {
                this.addView(this.linearLayout, LayoutHelper.createFrame(v2, v1));
            }
        }

        static HashMap access$200(ActionBarPopupWindowLayout arg0) {
            return arg0.positions;
        }

        static boolean access$300(ActionBarPopupWindowLayout arg0) {
            return arg0.showedFromBotton;
        }

        static int access$402(ActionBarPopupWindowLayout arg0, int arg1) {
            arg0.lastStartedChild = arg1;
            return arg1;
        }

        public void addView(View arg2) {
            this.linearLayout.addView(arg2);
        }

        public boolean dispatchKeyEvent(KeyEvent arg2) {
            if(this.mOnDispatchKeyEventListener != null) {
                this.mOnDispatchKeyEventListener.onDispatchKeyEvent(arg2);
            }

            return super.dispatchKeyEvent(arg2);
        }

        @Keep public int getBackAlpha() {
            return this.backAlpha;
        }

        public float getBackScaleX() {
            return this.backScaleX;
        }

        public float getBackScaleY() {
            return this.backScaleY;
        }

        public View getItemAt(int arg2) {
            return this.linearLayout.getChildAt(arg2);
        }

        public int getItemsCount() {
            return this.linearLayout.getChildCount();
        }

        protected void onDraw(Canvas arg6) {
            if(this.backgroundDrawable != null) {
                this.backgroundDrawable.setAlpha(this.backAlpha);
                this.getMeasuredHeight();
                if(this.showedFromBotton) {
                    this.backgroundDrawable.setBounds(0, ((int)((((float)this.getMeasuredHeight())) * (1f - this.backScaleY))), ((int)((((float)this.getMeasuredWidth())) * this.backScaleX)), this.getMeasuredHeight());
                }
                else {
                    this.backgroundDrawable.setBounds(0, 0, ((int)((((float)this.getMeasuredWidth())) * this.backScaleX)), ((int)((((float)this.getMeasuredHeight())) * this.backScaleY)));
                }

                this.backgroundDrawable.draw(arg6);
            }
        }

        public void removeInnerViews() {
            this.linearLayout.removeAllViews();
        }

        public void scrollToTop() {
            if(this.scrollView != null) {
                this.scrollView.scrollTo(0, 0);
            }
        }

        public void setAnimationEnabled(boolean arg1) {
            this.animationEnabled = arg1;
        }

        @Keep public void setBackAlpha(int arg1) {
            this.backAlpha = arg1;
        }

        @Keep public void setBackScaleX(float arg1) {
            this.backScaleX = arg1;
            this.invalidate();
        }

        @Keep public void setBackScaleY(float arg8) {
            this.backScaleY = arg8;
            if(this.animationEnabled) {
                int v0 = this.getItemsCount();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    this.getItemAt(v1).getVisibility();
                }

                v1 = this.getMeasuredHeight() - AndroidUtilities.dp(16f);
                float v3 = 48f;
                if(this.showedFromBotton) {
                    v0 = this.lastStartedChild;
                    goto label_18;
                }
                else {
                    int v2 = this.lastStartedChild;
                    while(true) {
                        if(v2 < v0) {
                            View v4 = this.getItemAt(v2);
                            if(v4.getVisibility() != 0) {
                            }
                            else {
                                Object v5 = this.positions.get(v4);
                                if(v5 != null && (((float)((((Integer)v5).intValue() + 1) * AndroidUtilities.dp(v3) - AndroidUtilities.dp(24f)))) > (((float)v1)) * arg8) {
                                    goto label_69;
                                }

                                this.lastStartedChild = v2 + 1;
                                this.startChildAnimation(v4);
                            }

                            ++v2;
                            continue;
                        }

                        goto label_69;
                    }

                label_18:
                    while(v0 >= 0) {
                        View v2_1 = this.getItemAt(v0);
                        if(v2_1.getVisibility() != 0) {
                        }
                        else {
                            Object v4_1 = this.positions.get(v2_1);
                            if(v4_1 != null && (((float)(v1 - (((Integer)v4_1).intValue() * AndroidUtilities.dp(v3) + AndroidUtilities.dp(32f))))) > (((float)v1)) * arg8) {
                                break;
                            }

                            this.lastStartedChild = v0 - 1;
                            this.startChildAnimation(v2_1);
                        }

                        --v0;
                    }
                }
            }

        label_69:
            this.invalidate();
        }

        public void setBackgroundDrawable(Drawable arg1) {
            this.backgroundDrawable = arg1;
        }

        public void setDispatchKeyEventListener(OnDispatchKeyEventListener arg1) {
            this.mOnDispatchKeyEventListener = arg1;
        }

        public void setShowedFromBotton(boolean arg1) {
            this.showedFromBotton = arg1;
        }

        private void startChildAnimation(View arg7) {
            if(this.animationEnabled) {
                AnimatorSet v0 = new AnimatorSet();
                Animator[] v2 = new Animator[2];
                v2[0] = ObjectAnimator.ofFloat(arg7, "alpha", new float[]{0f, 1f});
                String v3 = "translationY";
                float[] v1 = new float[2];
                float v5 = this.showedFromBotton ? 6f : -6f;
                v1[0] = ((float)AndroidUtilities.dp(v5));
                v1[1] = 0f;
                v2[1] = ObjectAnimator.ofFloat(arg7, v3, v1);
                v0.playTogether(v2);
                v0.setDuration(180);
                v0.setInterpolator(ActionBarPopupWindow.decelerateInterpolator);
                v0.start();
            }
        }
    }

    public interface OnDispatchKeyEventListener {
        void onDispatchKeyEvent(KeyEvent arg1);
    }

    private static final ViewTreeObserver$OnScrollChangedListener NOP;
    private static final boolean allowAnimation;
    private boolean animationEnabled;
    private static DecelerateInterpolator decelerateInterpolator;
    private ViewTreeObserver$OnScrollChangedListener mSuperScrollListener;
    private ViewTreeObserver mViewTreeObserver;
    private static final Field superListenerField;
    private AnimatorSet windowAnimatorSet;

    static {
        Field v2;
        boolean v0 = Build$VERSION.SDK_INT >= 18 ? true : false;
        ActionBarPopupWindow.allowAnimation = v0;
        ActionBarPopupWindow.decelerateInterpolator = new DecelerateInterpolator();
        Field v0_1 = null;
        try {
            v2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
        }
        catch(NoSuchFieldException ) {
            v2 = v0_1;
            goto label_18;
        }

        try {
            v2.setAccessible(true);
            goto label_18;
        }
        catch(NoSuchFieldException ) {
        label_18:
            ActionBarPopupWindow.superListenerField = v2;
            ActionBarPopupWindow.NOP = -$$Lambda$ActionBarPopupWindow$u1KuFqdl4RQdFf-yVDBUWk_fHAc.INSTANCE;
            return;
        }
    }

    public ActionBarPopupWindow(View arg1, int arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.animationEnabled = ActionBarPopupWindow.allowAnimation;
        this.init();
    }

    public ActionBarPopupWindow() {
        super();
        this.animationEnabled = ActionBarPopupWindow.allowAnimation;
        this.init();
    }

    public ActionBarPopupWindow(int arg1, int arg2) {
        super(arg1, arg2);
        this.animationEnabled = ActionBarPopupWindow.allowAnimation;
        this.init();
    }

    public ActionBarPopupWindow(Context arg1) {
        super(arg1);
        this.animationEnabled = ActionBarPopupWindow.allowAnimation;
        this.init();
    }

    public ActionBarPopupWindow(View arg1) {
        super(arg1);
        this.animationEnabled = ActionBarPopupWindow.allowAnimation;
        this.init();
    }

    public ActionBarPopupWindow(View arg1, int arg2, int arg3, boolean arg4) {
        super(arg1, arg2, arg3, arg4);
        this.animationEnabled = ActionBarPopupWindow.allowAnimation;
        this.init();
    }

    static boolean access$000() {
        return ActionBarPopupWindow.allowAnimation;
    }

    static DecelerateInterpolator access$100() {
        return ActionBarPopupWindow.decelerateInterpolator;
    }

    static AnimatorSet access$502(ActionBarPopupWindow arg0, AnimatorSet arg1) {
        arg0.windowAnimatorSet = arg1;
        return arg1;
    }

    static void access$601(ActionBarPopupWindow arg0) {
        super.dismiss();
    }

    static void access$700(ActionBarPopupWindow arg0) {
        arg0.unregisterListener();
    }

    public void dismiss(boolean arg8) {
        this.setFocusable(false);
        if(!this.animationEnabled || !arg8) {
            try {
                super.dismiss();
                goto label_47;
            }
            catch(Exception ) {
            label_47:
                this.unregisterListener();
            }
        }
        else {
            if(this.windowAnimatorSet != null) {
                this.windowAnimatorSet.cancel();
            }

            View v8 = this.getContentView();
            this.windowAnimatorSet = new AnimatorSet();
            AnimatorSet v1 = this.windowAnimatorSet;
            Animator[] v2 = new Animator[2];
            String v3 = "translationY";
            float[] v5 = new float[1];
            float v6 = ActionBarPopupWindowLayout.access$300(((ActionBarPopupWindowLayout)v8)) ? 5f : -5f;
            v5[0] = ((float)AndroidUtilities.dp(v6));
            v2[0] = ObjectAnimator.ofFloat(v8, v3, v5);
            v2[1] = ObjectAnimator.ofFloat(v8, "alpha", new float[]{0f});
            v1.playTogether(v2);
            this.windowAnimatorSet.setDuration(150);
            this.windowAnimatorSet.addListener(new Animator$AnimatorListener() {
                public void onAnimationCancel(Animator arg1) {
                    this.onAnimationEnd(arg1);
                }

                public void onAnimationEnd(Animator arg2) {
                    ActionBarPopupWindow.this.windowAnimatorSet = null;
                    ActionBarPopupWindow.this.setFocusable(false);
                    try {
                        ActionBarPopupWindow.access$601(ActionBarPopupWindow.this);
                        goto label_8;
                    }
                    catch(Exception ) {
                    label_8:
                        ActionBarPopupWindow.this.unregisterListener();
                        return;
                    }
                }

                public void onAnimationRepeat(Animator arg1) {
                }

                public void onAnimationStart(Animator arg1) {
                }
            });
            this.windowAnimatorSet.start();
        }
    }

    public void dismiss() {
        this.dismiss(true);
    }

    private void init() {
        if(ActionBarPopupWindow.superListenerField != null) {
            try {
                this.mSuperScrollListener = ActionBarPopupWindow.superListenerField.get(this);
                ActionBarPopupWindow.superListenerField.set(this, ActionBarPopupWindow.NOP);
            }
            catch(Exception ) {
                this.mSuperScrollListener = null;
            }
        }
    }

    static void lambda$static$0() {
    }

    private void registerListener(View arg3) {
        if(this.mSuperScrollListener != null) {
            ViewTreeObserver v3 = arg3.getWindowToken() != null ? arg3.getViewTreeObserver() : null;
            if(v3 == this.mViewTreeObserver) {
                return;
            }

            if(this.mViewTreeObserver != null && (this.mViewTreeObserver.isAlive())) {
                this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
            }

            this.mViewTreeObserver = v3;
            if(v3 == null) {
                return;
            }

            v3.addOnScrollChangedListener(this.mSuperScrollListener);
        }
    }

    public void setAnimationEnabled(boolean arg1) {
        this.animationEnabled = arg1;
    }

    public void showAsDropDown(View arg1, int arg2, int arg3) {
        try {
            super.showAsDropDown(arg1, arg2, arg3);
            this.registerListener(arg1);
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }
    }

    public void showAtLocation(View arg1, int arg2, int arg3, int arg4) {
        super.showAtLocation(arg1, arg2, arg3, arg4);
        this.unregisterListener();
    }

    public void startAnimation() {
        if(this.animationEnabled) {
            if(this.windowAnimatorSet != null) {
                return;
            }
            else {
                View v0 = this.getContentView();
                ((ActionBarPopupWindowLayout)v0).setTranslationY(0f);
                ((ActionBarPopupWindowLayout)v0).setAlpha(1f);
                ((ActionBarPopupWindowLayout)v0).setPivotX(((float)((ActionBarPopupWindowLayout)v0).getMeasuredWidth()));
                ((ActionBarPopupWindowLayout)v0).setPivotY(0f);
                int v2 = ((ActionBarPopupWindowLayout)v0).getItemsCount();
                ActionBarPopupWindowLayout.access$200(((ActionBarPopupWindowLayout)v0)).clear();
                int v4 = 0;
                int v5 = 0;
                while(v4 < v2) {
                    View v6 = ((ActionBarPopupWindowLayout)v0).getItemAt(v4);
                    if(v6.getVisibility() != 0) {
                    }
                    else {
                        ActionBarPopupWindowLayout.access$200(((ActionBarPopupWindowLayout)v0)).put(v6, Integer.valueOf(v5));
                        v6.setAlpha(0f);
                        ++v5;
                    }

                    ++v4;
                }

                if(ActionBarPopupWindowLayout.access$300(((ActionBarPopupWindowLayout)v0))) {
                    ActionBarPopupWindowLayout.access$402(((ActionBarPopupWindowLayout)v0), v2 - 1);
                }
                else {
                    ActionBarPopupWindowLayout.access$402(((ActionBarPopupWindowLayout)v0), 0);
                }

                this.windowAnimatorSet = new AnimatorSet();
                this.windowAnimatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(v0, "backScaleY", new float[]{0f, 1f}), ObjectAnimator.ofInt(v0, "backAlpha", new int[]{0, 255})});
                this.windowAnimatorSet.setDuration(((long)(v5 * 16 + 150)));
                this.windowAnimatorSet.addListener(new Animator$AnimatorListener() {
                    public void onAnimationCancel(Animator arg1) {
                        this.onAnimationEnd(arg1);
                    }

                    public void onAnimationEnd(Animator arg2) {
                        ActionBarPopupWindow.this.windowAnimatorSet = null;
                    }

                    public void onAnimationRepeat(Animator arg1) {
                    }

                    public void onAnimationStart(Animator arg1) {
                    }
                });
                this.windowAnimatorSet.start();
            }
        }
    }

    private void unregisterListener() {
        if(this.mSuperScrollListener != null && this.mViewTreeObserver != null) {
            if(this.mViewTreeObserver.isAlive()) {
                this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
            }

            this.mViewTreeObserver = null;
        }
    }

    public void update(View arg1, int arg2, int arg3, int arg4, int arg5) {
        super.update(arg1, arg2, arg3, arg4, arg5);
        this.registerListener(arg1);
    }

    public void update(View arg1, int arg2, int arg3) {
        super.update(arg1, arg2, arg3);
        this.registerListener(arg1);
    }
}

