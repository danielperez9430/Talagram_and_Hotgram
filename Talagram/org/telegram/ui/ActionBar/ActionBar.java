package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$FontMetricsInt;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collection;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.Components.FireworksEffect;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SnowflakesEffect;

public class ActionBar extends FrameLayout {
    public class ActionBarMenuOnItemClick {
        public ActionBarMenuOnItemClick() {
            super();
        }

        public boolean canOpenMenu() {
            return 1;
        }

        public void onItemClick(int arg1) {
        }
    }

    public int actionBarFontSize;
    public ActionBarMenuOnItemClick actionBarMenuOnItemClick;
    private ActionBarMenu actionMode;
    private AnimatorSet actionModeAnimation;
    private View actionModeTop;
    private boolean actionModeVisible;
    private boolean addToContainer;
    private boolean allowOverlayTitle;
    private ImageView backButtonImageView;
    private boolean castShadows;
    private int extraHeight;
    private FireworksEffect fireworksEffect;
    private Paint$FontMetricsInt fontMetricsInt;
    private boolean interceptTouches;
    private boolean isBackOverlayVisible;
    protected boolean isSearchFieldVisible;
    protected int itemsActionModeBackgroundColor;
    protected int itemsActionModeColor;
    protected int itemsBackgroundColor;
    protected int itemsColor;
    private Runnable lastRunnable;
    private CharSequence lastSubtitle;
    private CharSequence lastTitle;
    private boolean manualStart;
    private ActionBarMenu menu;
    private boolean occupyStatusBar;
    protected BaseFragment parentFragment;
    private Rect rect;
    public String searchTabTitle;
    private SnowflakesEffect snowflakesEffect;
    private SimpleTextView subtitleTextView;
    private boolean supportsHolidayImage;
    private Runnable titleActionRunnable;
    private boolean titleOverlayShown;
    private int titleRightMargin;
    private SimpleTextView titleTextView;

    public ActionBar(Context arg3) {
        super(arg3);
        boolean v3 = Build$VERSION.SDK_INT >= 21 ? true : false;
        this.occupyStatusBar = v3;
        this.addToContainer = true;
        this.interceptTouches = true;
        this.castShadows = true;
        this.actionBarFontSize = -1;
        this.searchTabTitle = "جستجو";
        this.setOnClickListener(new -$$Lambda$ActionBar$ipLNSE-u7HuyPKHoD94Vr_WlQ-U(this));
    }

    static ActionBarMenu access$000(ActionBar arg0) {
        return arg0.actionMode;
    }

    static boolean access$100(ActionBar arg0) {
        return arg0.occupyStatusBar;
    }

    static View access$200(ActionBar arg0) {
        return arg0.actionModeTop;
    }

    static AnimatorSet access$300(ActionBar arg0) {
        return arg0.actionModeAnimation;
    }

    static AnimatorSet access$302(ActionBar arg0, AnimatorSet arg1) {
        arg0.actionModeAnimation = arg1;
        return arg1;
    }

    static SimpleTextView access$400(ActionBar arg0) {
        return arg0.titleTextView;
    }

    static SimpleTextView access$500(ActionBar arg0) {
        return arg0.subtitleTextView;
    }

    static ActionBarMenu access$600(ActionBar arg0) {
        return arg0.menu;
    }

    public void closeSearchField() {
        this.closeSearchField(true);
    }

    public void closeSearchField(boolean arg2) {
        if(this.isSearchFieldVisible) {
            if(this.menu == null) {
            }
            else {
                this.menu.closeSearchField(arg2);
            }
        }
    }

    public ActionBarMenu createActionMode() {
        return this.createActionMode(true);
    }

    public ActionBarMenu createActionMode(boolean arg4) {
        if(this.actionMode != null) {
            return this.actionMode;
        }

        this.actionMode = new ActionBarMenu(this.getContext(), this);
        this.actionMode.isActionMode = true;
        this.actionMode.setBackgroundColor(Theme.getColor("actionBarActionModeDefault"));
        this.addView(this.actionMode, this.indexOfChild(this.backButtonImageView));
        ActionBarMenu v0 = this.actionMode;
        int v1 = this.occupyStatusBar ? AndroidUtilities.statusBarHeight : 0;
        v0.setPadding(0, v1, 0, 0);
        ViewGroup$LayoutParams v0_1 = this.actionMode.getLayoutParams();
        v1 = -1;
        ((FrameLayout$LayoutParams)v0_1).height = v1;
        ((FrameLayout$LayoutParams)v0_1).width = v1;
        ((FrameLayout$LayoutParams)v0_1).bottomMargin = this.extraHeight;
        ((FrameLayout$LayoutParams)v0_1).gravity = 5;
        this.actionMode.setLayoutParams(v0_1);
        int v2 = 4;
        this.actionMode.setVisibility(v2);
        if((this.occupyStatusBar) && (arg4) && this.actionModeTop == null) {
            this.actionModeTop = new View(this.getContext());
            this.actionModeTop.setBackgroundColor(Theme.getColor("actionBarActionModeDefaultTop"));
            this.addView(this.actionModeTop);
            ViewGroup$LayoutParams v4 = this.actionModeTop.getLayoutParams();
            ((FrameLayout$LayoutParams)v4).height = AndroidUtilities.statusBarHeight;
            ((FrameLayout$LayoutParams)v4).width = v1;
            ((FrameLayout$LayoutParams)v4).gravity = 51;
            this.actionModeTop.setLayoutParams(v4);
            this.actionModeTop.setVisibility(v2);
        }

        return this.actionMode;
    }

    private void createBackButtonImage() {
        if(this.backButtonImageView != null) {
            return;
        }

        this.backButtonImageView = new ImageView(this.getContext());
        this.backButtonImageView.setScaleType(ImageView$ScaleType.CENTER);
        this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsBackgroundColor));
        if(this.itemsColor != 0) {
            this.backButtonImageView.setColorFilter(new PorterDuffColorFilter(this.itemsColor, PorterDuff$Mode.MULTIPLY));
        }

        this.backButtonImageView.setPadding(AndroidUtilities.dp(1f), 0, 0, 0);
        this.addView(this.backButtonImageView, LayoutHelper.createFrame(54, 54, 51));
        this.backButtonImageView.setOnClickListener(new -$$Lambda$ActionBar$VS8eczH_GWXmmp1KP0J3EUbIWcg(this));
    }

    public ActionBarMenu createMenu() {
        if(this.menu != null) {
            return this.menu;
        }

        this.menu = new ActionBarMenu(this.getContext(), this);
        this.addView(this.menu, 0, LayoutHelper.createFrame(-2, -1, 5));
        return this.menu;
    }

    private void createSubtitleTextView() {
        if(this.subtitleTextView != null) {
            return;
        }

        this.subtitleTextView = new SimpleTextView(this.getContext());
        this.subtitleTextView.setGravity(3);
        this.subtitleTextView.setVisibility(8);
        this.subtitleTextView.setTextColor(Theme.getColor("actionBarDefaultSubtitle"));
        this.addView(this.subtitleTextView, 0, LayoutHelper.createFrame(-2, -2, 51));
    }

    private void createTitleTextView() {
        if(this.titleTextView != null) {
            return;
        }

        this.titleTextView = new SimpleTextView(this.getContext());
        this.titleTextView.setGravity(3);
        this.titleTextView.setTextColor(Theme.getColor("actionBarDefaultTitle"));
        this.titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.addView(this.titleTextView, 0, LayoutHelper.createFrame(-2, -2, 51));
    }

    protected boolean drawChild(Canvas arg5, View arg6, long arg7) {
        SnowflakesEffect v6_1;
        boolean v7 = super.drawChild(arg5, arg6, arg7);
        if((this.supportsHolidayImage) && !this.titleOverlayShown && !LocaleController.isRTL && arg6 == this.titleTextView) {
            Drawable v6 = Theme.getCurrentHolidayDrawable();
            if(v6 != null) {
                TextPaint v8 = this.titleTextView.getTextPaint();
                v8.getFontMetricsInt(this.fontMetricsInt);
                v8.getTextBounds(this.titleTextView.getText(), 0, 1, this.rect);
                int v8_1 = this.titleTextView.getTextStartX() + Theme.getCurrentHolidayDrawableXOffset() + (this.rect.width() - (v6.getIntrinsicWidth() + Theme.getCurrentHolidayDrawableXOffset())) / 2;
                int v0 = this.titleTextView.getTextStartY() + Theme.getCurrentHolidayDrawableYOffset() + (((int)Math.ceil(((double)((((float)(this.titleTextView.getTextHeight() - this.rect.height()))) / 2f)))));
                v6.setBounds(v8_1, v0 - v6.getIntrinsicHeight(), v6.getIntrinsicWidth() + v8_1, v0);
                v6.draw(arg5);
                if(Theme.canStartHolidayAnimation()) {
                    if(this.snowflakesEffect == null) {
                        v6_1 = new SnowflakesEffect();
                        goto label_61;
                    }
                }
                else if(!this.manualStart && this.snowflakesEffect != null) {
                    v6_1 = null;
                label_61:
                    this.snowflakesEffect = v6_1;
                }

                if(this.snowflakesEffect != null) {
                    this.snowflakesEffect.onDraw(((View)this), arg5);
                    return v7;
                }

                if(this.fireworksEffect == null) {
                    return v7;
                }

                this.fireworksEffect.onDraw(((View)this), arg5);
            }
        }

        return v7;
    }

    public ActionBarMenuOnItemClick getActionBarMenuOnItemClick() {
        return this.actionBarMenuOnItemClick;
    }

    public boolean getAddToContainer() {
        return this.addToContainer;
    }

    public View getBackButton() {
        return this.backButtonImageView;
    }

    public boolean getCastShadows() {
        return this.castShadows;
    }

    public static int getCurrentActionBarHeight() {
        float v0;
        if(AndroidUtilities.isTablet()) {
            v0 = 64f;
        }
        else if(AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y) {
            v0 = 48f;
        }
        else {
            v0 = 56f;
        }

        return AndroidUtilities.dp(v0);
    }

    public boolean getOccupyStatusBar() {
        return this.occupyStatusBar;
    }

    public String getSubtitle() {
        if(this.subtitleTextView == null) {
            return null;
        }

        return this.subtitleTextView.getText().toString();
    }

    public SimpleTextView getSubtitleTextView() {
        return this.subtitleTextView;
    }

    public String getTitle() {
        if(this.titleTextView == null) {
            return null;
        }

        return this.titleTextView.getText().toString();
    }

    public SimpleTextView getTitleTextView() {
        return this.titleTextView;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public void hideActionMode() {
        if(this.actionMode != null) {
            if(!this.actionModeVisible) {
            }
            else {
                this.actionModeVisible = false;
                ArrayList v1 = new ArrayList();
                v1.add(ObjectAnimator.ofFloat(this.actionMode, "alpha", new float[]{0f}));
                if((this.occupyStatusBar) && this.actionModeTop != null) {
                    v1.add(ObjectAnimator.ofFloat(this.actionModeTop, "alpha", new float[]{0f}));
                }

                if(this.actionModeAnimation != null) {
                    this.actionModeAnimation.cancel();
                }

                this.actionModeAnimation = new AnimatorSet();
                this.actionModeAnimation.playTogether(((Collection)v1));
                this.actionModeAnimation.setDuration(200);
                this.actionModeAnimation.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator arg2) {
                        if(ActionBar.this.actionModeAnimation != null && (ActionBar.this.actionModeAnimation.equals(arg2))) {
                            ActionBar.this.actionModeAnimation = null;
                        }
                    }

                    public void onAnimationEnd(Animator arg2) {
                        if(ActionBar.this.actionModeAnimation != null && (ActionBar.this.actionModeAnimation.equals(arg2))) {
                            ActionBar.this.actionModeAnimation = null;
                            int v0 = 4;
                            ActionBar.this.actionMode.setVisibility(v0);
                            if((ActionBar.this.occupyStatusBar) && ActionBar.this.actionModeTop != null) {
                                ActionBar.this.actionModeTop.setVisibility(v0);
                            }
                        }
                    }
                });
                this.actionModeAnimation.start();
                if(this.titleTextView != null) {
                    this.titleTextView.setVisibility(0);
                }

                if(this.subtitleTextView != null && !TextUtils.isEmpty(this.subtitleTextView.getText())) {
                    this.subtitleTextView.setVisibility(0);
                }

                if(this.menu != null) {
                    this.menu.setVisibility(0);
                }

                if(this.backButtonImageView == null) {
                    return;
                }

                Drawable v0 = this.backButtonImageView.getDrawable();
                if((v0 instanceof BackDrawable)) {
                    ((BackDrawable)v0).setRotation(0f, true);
                }

                this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsBackgroundColor));
            }
        }
    }

    public boolean isActionModeShowed() {
        boolean v0 = this.actionMode == null || !this.actionModeVisible ? false : true;
        return v0;
    }

    public boolean isSearchFieldVisible() {
        return this.isSearchFieldVisible;
    }

    public static void lambda$createBackButtonImage$1(ActionBar arg1, View arg2) {
        if(!arg1.actionModeVisible && (arg1.isSearchFieldVisible)) {
            arg1.closeSearchField();
            return;
        }

        if(arg1.actionBarMenuOnItemClick != null) {
            arg1.actionBarMenuOnItemClick.onItemClick(-1);
        }
    }

    public static void lambda$new$0(ActionBar arg0, View arg1) {
        if(arg0.titleActionRunnable != null) {
            arg0.titleActionRunnable.run();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent arg4) {
        if((this.supportsHolidayImage) && !this.titleOverlayShown && !LocaleController.isRTL && arg4.getAction() == 0) {
            Drawable v0 = Theme.getCurrentHolidayDrawable();
            if(v0 != null && (v0.getBounds().contains(((int)arg4.getX()), ((int)arg4.getY())))) {
                this.manualStart = true;
                FireworksEffect v1 = null;
                if(this.snowflakesEffect == null) {
                    this.fireworksEffect = v1;
                    this.snowflakesEffect = new SnowflakesEffect();
                }
                else if(BuildVars.DEBUG_PRIVATE_VERSION) {
                    this.snowflakesEffect = ((SnowflakesEffect)v1);
                    this.fireworksEffect = new FireworksEffect();
                }
                else {
                    goto label_37;
                }

                this.titleTextView.invalidate();
                this.invalidate();
            }
        }

    label_37:
        return super.onInterceptTouchEvent(arg4);
    }

    protected void onLayout(boolean arg11, int arg12, int arg13, int arg14, int arg15) {
        int v3_1;
        float v1;
        int v0 = 0;
        int v11 = this.occupyStatusBar ? AndroidUtilities.statusBarHeight : 0;
        int v2 = 8;
        if(this.backButtonImageView != null && this.backButtonImageView.getVisibility() != v2) {
            this.backButtonImageView.layout(0, v11, this.backButtonImageView.getMeasuredWidth(), this.backButtonImageView.getMeasuredHeight() + v11);
            v1 = AndroidUtilities.isTablet() ? 80f : 72f;
        }
        else if(AndroidUtilities.isTablet()) {
            v1 = 26f;
        }
        else {
            v1 = 18f;
        }

        int v1_1 = AndroidUtilities.dp(v1);
        if(this.menu != null && this.menu.getVisibility() != v2) {
            if(this.isSearchFieldVisible) {
                float v3 = AndroidUtilities.isTablet() ? 74f : 66f;
                v3_1 = AndroidUtilities.dp(v3);
            }
            else {
                v3_1 = arg14 - arg12 - this.menu.getMeasuredWidth();
            }

            this.menu.layout(v3_1, v11, this.menu.getMeasuredWidth() + v3_1, this.menu.getMeasuredHeight() + v11);
        }

        int v4 = 2;
        if(this.titleTextView != null && this.titleTextView.getVisibility() != v2) {
            if(this.subtitleTextView == null || this.subtitleTextView.getVisibility() == v2) {
                v3_1 = (ActionBar.getCurrentActionBarHeight() - this.titleTextView.getTextHeight()) / v4;
            }
            else {
                v3_1 = (ActionBar.getCurrentActionBarHeight() / v4 - this.titleTextView.getTextHeight()) / v4;
                float v5 = (AndroidUtilities.isTablet()) || this.getResources().getConfiguration().orientation != v4 ? 3f : 2f;
                v3_1 += AndroidUtilities.dp(v5);
            }

            v3_1 += v11;
            this.titleTextView.layout(v1_1, v3_1, this.titleTextView.getMeasuredWidth() + v1_1, this.titleTextView.getTextHeight() + v3_1);
        }

        if(this.subtitleTextView != null && this.subtitleTextView.getVisibility() != v2) {
            v3_1 = ActionBar.getCurrentActionBarHeight() / v4 + (ActionBar.getCurrentActionBarHeight() / v4 - this.subtitleTextView.getTextHeight()) / v4;
            if(!AndroidUtilities.isTablet()) {
                this.getResources().getConfiguration();
            }

            v11 += v3_1 - AndroidUtilities.dp(1f);
            this.subtitleTextView.layout(v1_1, v11, this.subtitleTextView.getMeasuredWidth() + v1_1, this.subtitleTextView.getTextHeight() + v11);
        }

        v11 = this.getChildCount();
        while(v0 < v11) {
            View v1_2 = this.getChildAt(v0);
            if(v1_2.getVisibility() != v2 && v1_2 != this.titleTextView && v1_2 != this.subtitleTextView && v1_2 != this.menu) {
                if(v1_2 == this.backButtonImageView) {
                }
                else {
                    ViewGroup$LayoutParams v3_2 = v1_2.getLayoutParams();
                    int v5_1 = v1_2.getMeasuredWidth();
                    int v6 = v1_2.getMeasuredHeight();
                    int v7 = ((FrameLayout$LayoutParams)v3_2).gravity;
                    if(v7 == -1) {
                        v7 = 51;
                    }

                    int v8 = v7 & 7;
                    v7 &= 112;
                    v8 &= 7;
                    if(v8 == 1) {
                        v8 = (arg14 - arg12 - v5_1) / v4 + ((FrameLayout$LayoutParams)v3_2).leftMargin;
                    label_168:
                        v8 -= ((FrameLayout$LayoutParams)v3_2).rightMargin;
                    }
                    else if(v8 != 5) {
                        v8 = ((FrameLayout$LayoutParams)v3_2).leftMargin;
                    }
                    else {
                        v8 = arg14 - v5_1;
                        goto label_168;
                    }

                    if(v7 != 16) {
                        if(v7 != 48 && v7 == 80) {
                            v7 = arg15 - arg13 - v6;
                            goto label_186;
                        }

                        v3_1 = ((FrameLayout$LayoutParams)v3_2).topMargin;
                    }
                    else {
                        v7 = (arg15 - arg13 - v6) / v4 + ((FrameLayout$LayoutParams)v3_2).topMargin;
                    label_186:
                        v3_1 = v7 - ((FrameLayout$LayoutParams)v3_2).bottomMargin;
                    }

                    v1_2.layout(v8, v3_1, v5_1 + v8, v6 + v3_1);
                }
            }

            ++v0;
        }
    }

    protected void onMeasure(int arg12, int arg13) {
        int v7;
        SimpleTextView v13_1;
        int v5_1;
        float v13;
        int v0 = View$MeasureSpec.getSize(arg12);
        View$MeasureSpec.getSize(arg13);
        arg13 = ActionBar.getCurrentActionBarHeight();
        int v1 = 1073741824;
        int v2 = View$MeasureSpec.makeMeasureSpec(arg13, v1);
        int v4 = 0;
        int v3 = this.occupyStatusBar ? AndroidUtilities.statusBarHeight : 0;
        this.setMeasuredDimension(v0, arg13 + v3 + this.extraHeight);
        v3 = 8;
        if(this.backButtonImageView != null && this.backButtonImageView.getVisibility() != v3) {
            this.backButtonImageView.measure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(54f), v1), v2);
            v13 = AndroidUtilities.isTablet() ? 80f : 72f;
        }
        else if(AndroidUtilities.isTablet()) {
            v13 = 26f;
        }
        else {
            v13 = 18f;
        }

        arg13 = AndroidUtilities.dp(v13);
        int v6 = -2147483648;
        if(this.menu != null && this.menu.getVisibility() != v3) {
            if(this.isSearchFieldVisible) {
                float v5 = AndroidUtilities.isTablet() ? 74f : 66f;
                v5_1 = View$MeasureSpec.makeMeasureSpec(v0 - AndroidUtilities.dp(v5), v1);
            }
            else {
                v5_1 = View$MeasureSpec.makeMeasureSpec(v0, v6);
            }

            this.menu.measure(v5_1, v2);
        }

        if(this.titleTextView != null && this.titleTextView.getVisibility() != v3 || this.subtitleTextView != null && this.subtitleTextView.getVisibility() != v3) {
            v2 = this.menu != null ? this.menu.getMeasuredWidth() : 0;
            v0 = v0 - v2 - AndroidUtilities.dp(16f) - arg13 - this.titleRightMargin;
            v2 = 16;
            v5_1 = 2;
            if(this.titleTextView != null && this.titleTextView.getVisibility() != v3) {
                if(this.actionBarFontSize > v5_1) {
                    v13_1 = this.titleTextView;
                    if(!AndroidUtilities.isTablet() && this.getResources().getConfiguration().orientation == v5_1) {
                        v7 = this.actionBarFontSize - v5_1;
                        goto label_101;
                    }

                    v7 = this.actionBarFontSize;
                }
                else {
                    v13_1 = this.titleTextView;
                    if(!AndroidUtilities.isTablet() && this.getResources().getConfiguration().orientation == v5_1) {
                        v7 = 16;
                        goto label_101;
                    }

                    v7 = 18;
                }

            label_101:
                v13_1.setTextSize(v7);
                this.titleTextView.measure(View$MeasureSpec.makeMeasureSpec(v0, v6), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24f), v6));
            }

            if(this.subtitleTextView == null) {
                goto label_140;
            }

            if(this.subtitleTextView.getVisibility() == v3) {
                goto label_140;
            }

            v13_1 = this.subtitleTextView;
            if(!AndroidUtilities.isTablet() && this.getResources().getConfiguration().orientation == v5_1) {
                v2 = 14;
            }

            v13_1.setTextSize(v2);
            this.subtitleTextView.measure(View$MeasureSpec.makeMeasureSpec(v0, v6), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20f), v6));
        }

    label_140:
        arg13 = this.getChildCount();
        while(v4 < arg13) {
            View v6_1 = this.getChildAt(v4);
            if(v6_1.getVisibility() != v3 && v6_1 != this.titleTextView && v6_1 != this.subtitleTextView && v6_1 != this.menu) {
                if(v6_1 == this.backButtonImageView) {
                }
                else {
                    this.measureChildWithMargins(v6_1, arg12, 0, View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), v1), 0);
                }
            }

            ++v4;
        }
    }

    public void onMenuButtonPressed() {
        if(this.menu != null) {
            this.menu.onMenuButtonPressed();
        }
    }

    protected void onPause() {
        if(this.menu != null) {
            this.menu.hideAllPopupMenus();
        }
    }

    protected void onSearchFieldVisibilityChanged(boolean arg5) {
        SimpleTextView v0;
        this.isSearchFieldVisible = arg5;
        int v1 = 0;
        if(this.titleTextView != null) {
            v0 = this.titleTextView;
            int v3 = arg5 ? 4 : 0;
            v0.setVisibility(v3);
        }

        if(this.subtitleTextView != null && !TextUtils.isEmpty(this.subtitleTextView.getText())) {
            v0 = this.subtitleTextView;
            if(arg5) {
                v1 = 4;
            }

            v0.setVisibility(v1);
        }

        Drawable v0_1 = this.backButtonImageView.getDrawable();
        if(v0_1 != null && ((v0_1 instanceof MenuDrawable))) {
            float v5 = arg5 ? 1f : 0f;
            ((MenuDrawable)v0_1).setRotation(v5, true);
        }
    }

    public boolean onTouchEvent(MotionEvent arg1) {
        boolean v1 = (super.onTouchEvent(arg1)) || (this.interceptTouches) ? true : false;
        return v1;
    }

    public void openSearchField(String arg3) {
        if(this.menu != null) {
            if(arg3 == null) {
            }
            else {
                this.menu.openSearchField(this.isSearchFieldVisible ^ 1, arg3);
            }
        }
    }

    public void setActionBarMenuOnItemClick(ActionBarMenuOnItemClick arg1) {
        this.actionBarMenuOnItemClick = arg1;
    }

    public void setActionModeColor(int arg2) {
        if(this.actionMode != null) {
            this.actionMode.setBackgroundColor(arg2);
        }
    }

    public void setActionModeTopColor(int arg2) {
        if(this.actionModeTop != null) {
            this.actionModeTop.setBackgroundColor(arg2);
        }
    }

    public void setAddToContainer(boolean arg1) {
        this.addToContainer = arg1;
    }

    public void setAllowOverlayTitle(boolean arg1) {
        this.allowOverlayTitle = arg1;
    }

    public void setBackButtonDrawable(Drawable arg4) {
        if(this.backButtonImageView == null) {
            this.createBackButtonImage();
        }

        ImageView v0 = this.backButtonImageView;
        int v2 = arg4 == null ? 8 : 0;
        v0.setVisibility(v2);
        this.backButtonImageView.setImageDrawable(arg4);
        if((arg4 instanceof BackDrawable)) {
            float v0_1 = this.isActionModeShowed() ? 1f : 0f;
            ((BackDrawable)arg4).setRotation(v0_1, false);
            ((BackDrawable)arg4).setRotatedColor(this.itemsActionModeColor);
            ((BackDrawable)arg4).setColor(this.itemsColor);
        }
    }

    public void setBackButtonImage(int arg3) {
        if(this.backButtonImageView == null) {
            this.createBackButtonImage();
        }

        ImageView v0 = this.backButtonImageView;
        int v1 = arg3 == 0 ? 8 : 0;
        v0.setVisibility(v1);
        this.backButtonImageView.setImageResource(arg3);
    }

    public void setCastShadows(boolean arg1) {
        this.castShadows = arg1;
    }

    public void setEnabled(boolean arg2) {
        super.setEnabled(arg2);
        if(this.backButtonImageView != null) {
            this.backButtonImageView.setEnabled(arg2);
        }

        if(this.menu != null) {
            this.menu.setEnabled(arg2);
        }

        if(this.actionMode != null) {
            this.actionMode.setEnabled(arg2);
        }
    }

    public void setExtraHeight(int arg2) {
        this.extraHeight = arg2;
        if(this.actionMode != null) {
            ViewGroup$LayoutParams v2 = this.actionMode.getLayoutParams();
            ((FrameLayout$LayoutParams)v2).bottomMargin = this.extraHeight;
            this.actionMode.setLayoutParams(v2);
        }
    }

    public void setInterceptTouches(boolean arg1) {
        this.interceptTouches = arg1;
    }

    public void setItemsBackgroundColor(int arg1, boolean arg2) {
        ActionBarMenu v1;
        if(arg2) {
            this.itemsActionModeBackgroundColor = arg1;
            if((this.actionModeVisible) && this.backButtonImageView != null) {
                this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsActionModeBackgroundColor));
            }

            if(this.actionMode == null) {
                return;
            }

            v1 = this.actionMode;
            goto label_13;
        }
        else {
            this.itemsBackgroundColor = arg1;
            if(this.backButtonImageView != null) {
                this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsBackgroundColor));
            }

            if(this.menu == null) {
                return;
            }

            v1 = this.menu;
        label_13:
            v1.updateItemsBackgroundColor();
        }
    }

    public void setItemsColor(int arg4, boolean arg5) {
        Drawable v5;
        if(arg5) {
            this.itemsActionModeColor = arg4;
            if(this.actionMode != null) {
                this.actionMode.updateItemsColor();
            }

            if(this.backButtonImageView == null) {
                return;
            }

            v5 = this.backButtonImageView.getDrawable();
            if(!(v5 instanceof BackDrawable)) {
                return;
            }

            ((BackDrawable)v5).setRotatedColor(arg4);
        }
        else {
            this.itemsColor = arg4;
            if(this.backButtonImageView != null && this.itemsColor != 0) {
                this.backButtonImageView.setColorFilter(new PorterDuffColorFilter(this.itemsColor, PorterDuff$Mode.MULTIPLY));
                v5 = this.backButtonImageView.getDrawable();
                if((v5 instanceof BackDrawable)) {
                    ((BackDrawable)v5).setColor(arg4);
                }
            }

            if(this.menu == null) {
                return;
            }

            this.menu.updateItemsColor();
        }
    }

    public void setOccupyStatusBar(boolean arg3) {
        this.occupyStatusBar = arg3;
        if(this.actionMode != null) {
            ActionBarMenu v3 = this.actionMode;
            int v0 = this.occupyStatusBar ? AndroidUtilities.statusBarHeight : 0;
            v3.setPadding(0, v0, 0, 0);
        }
    }

    public void setPopupBackgroundColor(int arg2) {
        if(this.menu != null) {
            this.menu.redrawPopup(arg2);
        }
    }

    public void setPopupItemsColor(int arg2) {
        if(this.menu != null) {
            this.menu.setPopupItemsColor(arg2);
        }
    }

    public void setSearchTextColor(int arg2, boolean arg3) {
        if(this.menu != null) {
            this.menu.setSearchTextColor(arg2, arg3);
        }
    }

    public void setSubtitle(CharSequence arg3) {
        if(arg3 != null && this.subtitleTextView == null) {
            this.createSubtitleTextView();
        }

        if(this.subtitleTextView != null) {
            this.lastSubtitle = arg3;
            SimpleTextView v0 = this.subtitleTextView;
            int v1 = (TextUtils.isEmpty(arg3)) || (this.isSearchFieldVisible) ? 8 : 0;
            v0.setVisibility(v1);
            this.subtitleTextView.setText(arg3);
        }
    }

    public void setSubtitleColor(int arg2) {
        if(this.subtitleTextView == null) {
            this.createSubtitleTextView();
        }

        this.subtitleTextView.setTextColor(arg2);
    }

    public void setSupportsHolidayImage(boolean arg1) {
        this.supportsHolidayImage = arg1;
        if(this.supportsHolidayImage) {
            this.fontMetricsInt = new Paint$FontMetricsInt();
            this.rect = new Rect();
        }

        this.invalidate();
    }

    public void setTitle(CharSequence arg3) {
        if(arg3 != null && this.titleTextView == null) {
            this.createTitleTextView();
        }

        if(this.titleTextView != null) {
            this.lastTitle = arg3;
            SimpleTextView v0 = this.titleTextView;
            int v1 = arg3 == null || (this.isSearchFieldVisible) ? 4 : 0;
            v0.setVisibility(v1);
            this.titleTextView.setText(arg3);
        }
    }

    public void setTitleActionRunnable(Runnable arg1) {
        this.titleActionRunnable = arg1;
        this.lastRunnable = arg1;
    }

    public void setTitleColor(int arg2) {
        if(this.titleTextView == null) {
            this.createTitleTextView();
        }

        this.titleTextView.setTextColor(arg2);
    }

    public void setTitleOverlayText(String arg4, String arg5, Runnable arg6) {
        CharSequence v5;
        SimpleTextView v4_1;
        String v0;
        if(this.allowOverlayTitle) {
            if(this.parentFragment.parentLayout == null) {
            }
            else {
                if(arg4 != null) {
                    v0 = arg4;
                }
                else {
                    CharSequence v0_1 = this.lastTitle;
                }

                if(v0 != null && this.titleTextView == null) {
                    this.createTitleTextView();
                }

                int v2 = 0;
                if(this.titleTextView != null) {
                    boolean v4 = arg4 != null ? true : false;
                    this.titleOverlayShown = v4;
                    if(this.supportsHolidayImage) {
                        this.titleTextView.invalidate();
                        this.invalidate();
                    }

                    v4_1 = this.titleTextView;
                    int v1 = v0 == null || (this.isSearchFieldVisible) ? 4 : 0;
                    v4_1.setVisibility(v1);
                    this.titleTextView.setText(((CharSequence)v0));
                }

                if(arg5 != null) {
                }
                else {
                    v5 = this.lastSubtitle;
                }

                if((((String)v5)) != null && this.subtitleTextView == null) {
                    this.createSubtitleTextView();
                }

                if(this.subtitleTextView != null) {
                    v4_1 = this.subtitleTextView;
                    if((TextUtils.isEmpty(v5)) || (this.isSearchFieldVisible)) {
                        v2 = 8;
                    }
                    else {
                    }

                    v4_1.setVisibility(v2);
                    this.subtitleTextView.setText(v5);
                }

                if(arg6 != null) {
                }
                else {
                    arg6 = this.lastRunnable;
                }

                this.titleActionRunnable = arg6;
            }
        }
    }

    public void setTitleRightMargin(int arg1) {
        this.titleRightMargin = arg1;
    }

    public void showActionMode() {
        if(this.actionMode != null) {
            if(this.actionModeVisible) {
            }
            else {
                this.actionModeVisible = true;
                ArrayList v1 = new ArrayList();
                int v4 = 2;
                v1.add(ObjectAnimator.ofFloat(this.actionMode, "alpha", new float[]{0f, 1f}));
                if((this.occupyStatusBar) && this.actionModeTop != null) {
                    v1.add(ObjectAnimator.ofFloat(this.actionModeTop, "alpha", new float[]{0f, 1f}));
                }

                if(this.actionModeAnimation != null) {
                    this.actionModeAnimation.cancel();
                }

                this.actionModeAnimation = new AnimatorSet();
                this.actionModeAnimation.playTogether(((Collection)v1));
                this.actionModeAnimation.setDuration(200);
                this.actionModeAnimation.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator arg2) {
                        if(ActionBar.this.actionModeAnimation != null && (ActionBar.this.actionModeAnimation.equals(arg2))) {
                            ActionBar.this.actionModeAnimation = null;
                        }
                    }

                    public void onAnimationEnd(Animator arg2) {
                        if(ActionBar.this.actionModeAnimation != null && (ActionBar.this.actionModeAnimation.equals(arg2))) {
                            ActionBar.this.actionModeAnimation = null;
                            int v0 = 4;
                            if(ActionBar.this.titleTextView != null) {
                                ActionBar.this.titleTextView.setVisibility(v0);
                            }

                            if(ActionBar.this.subtitleTextView != null && !TextUtils.isEmpty(ActionBar.this.subtitleTextView.getText())) {
                                ActionBar.this.subtitleTextView.setVisibility(v0);
                            }

                            if(ActionBar.this.menu == null) {
                                return;
                            }

                            ActionBar.this.menu.setVisibility(v0);
                        }
                    }

                    public void onAnimationStart(Animator arg2) {
                        ActionBar.this.actionMode.setVisibility(0);
                        if((ActionBar.this.occupyStatusBar) && ActionBar.this.actionModeTop != null) {
                            ActionBar.this.actionModeTop.setVisibility(0);
                        }
                    }
                });
                this.actionModeAnimation.start();
                if(this.backButtonImageView == null) {
                    return;
                }

                Drawable v1_1 = this.backButtonImageView.getDrawable();
                if((v1_1 instanceof BackDrawable)) {
                    ((BackDrawable)v1_1).setRotation(1f, true);
                }

                this.backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(this.itemsActionModeBackgroundColor));
            }
        }
    }

    public void showActionModeTop() {
        if((this.occupyStatusBar) && this.actionModeTop == null) {
            this.actionModeTop = new View(this.getContext());
            this.actionModeTop.setBackgroundColor(Theme.getColor("actionBarActionModeDefaultTop"));
            this.addView(this.actionModeTop);
            ViewGroup$LayoutParams v0 = this.actionModeTop.getLayoutParams();
            ((FrameLayout$LayoutParams)v0).height = AndroidUtilities.statusBarHeight;
            ((FrameLayout$LayoutParams)v0).width = -1;
            ((FrameLayout$LayoutParams)v0).gravity = 51;
            this.actionModeTop.setLayoutParams(v0);
        }
    }
}

