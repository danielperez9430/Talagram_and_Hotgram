package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class DrawerLayoutContainer extends FrameLayout {
    private static final int MIN_DRAWER_MARGIN = 64;
    private boolean allowDrawContent;
    private boolean allowOpenDrawer;
    private boolean beginTrackingSent;
    private AnimatorSet currentAnimation;
    private ViewGroup drawerLayout;
    private boolean drawerOpened;
    private float drawerPosition;
    private boolean inLayout;
    private Object lastInsets;
    private boolean maybeStartTracking;
    private int minDrawerMargin;
    private int paddingTop;
    private ActionBarLayout parentActionBarLayout;
    private Rect rect;
    private float scrimOpacity;
    private Paint scrimPaint;
    private Drawable shadowLeft;
    private boolean startedTracking;
    private int startedTrackingPointerId;
    private int startedTrackingX;
    private int startedTrackingY;
    private VelocityTracker velocityTracker;

    public DrawerLayoutContainer(Context arg3) {
        super(arg3);
        this.rect = new Rect();
        this.scrimPaint = new Paint();
        this.allowDrawContent = true;
        this.minDrawerMargin = ((int)(AndroidUtilities.density * 64f + 0.5f));
        this.setDescendantFocusability(262144);
        this.setFocusableInTouchMode(true);
        if(Build$VERSION.SDK_INT >= 21) {
            this.setFitsSystemWindows(true);
            this.setOnApplyWindowInsetsListener(new -$$Lambda$DrawerLayoutContainer$dOsUXLZuN_il_QGSmGuPct7OsoA(this));
            this.setSystemUiVisibility(1280);
        }

        this.shadowLeft = this.getResources().getDrawable(2131231360);
    }

    static void access$000(DrawerLayoutContainer arg0, boolean arg1) {
        arg0.onDrawerAnimationEnd(arg1);
    }

    @SuppressLint(value={"NewApi"}) private void applyMarginInsets(ViewGroup$MarginLayoutParams arg4, Object arg5, int arg6, boolean arg7) {
        WindowInsets v5;
        int v0 = 0;
        if(arg6 == 3) {
            v5 = ((WindowInsets)arg5).replaceSystemWindowInsets(((WindowInsets)arg5).getSystemWindowInsetLeft(), ((WindowInsets)arg5).getSystemWindowInsetTop(), 0, ((WindowInsets)arg5).getSystemWindowInsetBottom());
        }
        else if(arg6 == 5) {
            v5 = ((WindowInsets)arg5).replaceSystemWindowInsets(0, ((WindowInsets)arg5).getSystemWindowInsetTop(), ((WindowInsets)arg5).getSystemWindowInsetRight(), ((WindowInsets)arg5).getSystemWindowInsetBottom());
        }

        arg4.leftMargin = v5.getSystemWindowInsetLeft();
        if(arg7) {
        }
        else {
            v0 = v5.getSystemWindowInsetTop();
        }

        arg4.topMargin = v0;
        arg4.rightMargin = v5.getSystemWindowInsetRight();
        arg4.bottomMargin = v5.getSystemWindowInsetBottom();
    }

    public void cancelCurrentAnimation() {
        if(this.currentAnimation != null) {
            this.currentAnimation.cancel();
            this.currentAnimation = null;
        }
    }

    public void closeDrawer(boolean arg7) {
        this.cancelCurrentAnimation();
        AnimatorSet v0 = new AnimatorSet();
        v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "drawerPosition", new float[]{0f})});
        v0.setInterpolator(new DecelerateInterpolator());
        long v1 = arg7 ? ((long)Math.max(((int)(200f / (((float)this.drawerLayout.getMeasuredWidth())) * this.drawerPosition)), 50)) : 300;
        v0.setDuration(v1);
        v0.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                DrawerLayoutContainer.this.onDrawerAnimationEnd(false);
            }
        });
        v0.start();
    }

    @SuppressLint(value={"NewApi"}) private void dispatchChildInsets(View arg4, Object arg5, int arg6) {
        WindowInsets v5;
        if(arg6 == 3) {
            v5 = ((WindowInsets)arg5).replaceSystemWindowInsets(((WindowInsets)arg5).getSystemWindowInsetLeft(), ((WindowInsets)arg5).getSystemWindowInsetTop(), 0, ((WindowInsets)arg5).getSystemWindowInsetBottom());
        }
        else if(arg6 == 5) {
            v5 = ((WindowInsets)arg5).replaceSystemWindowInsets(0, ((WindowInsets)arg5).getSystemWindowInsetTop(), ((WindowInsets)arg5).getSystemWindowInsetRight(), ((WindowInsets)arg5).getSystemWindowInsetBottom());
        }

        arg4.dispatchApplyWindowInsets(v5);
    }

    protected boolean drawChild(Canvas arg15, View arg16, long arg17) {
        int v11;
        DrawerLayoutContainer v0 = this;
        View v2 = arg16;
        int v4 = 0;
        if(!v0.allowDrawContent) {
            return 0;
        }

        int v3 = this.getHeight();
        int v5 = v2 != v0.drawerLayout ? 1 : 0;
        int v6 = this.getWidth();
        int v7 = arg15.save();
        if(v5 != 0) {
            int v8 = this.getChildCount();
            int v9 = 0;
            int v10 = 0;
            v11 = 0;
            while(v9 < v8) {
                View v12 = this.getChildAt(v9);
                if(v12.getVisibility() == 0 && v12 != v0.drawerLayout) {
                    v11 = v9;
                }

                if(v12 != v2 && v12.getVisibility() == 0 && v12 == v0.drawerLayout) {
                    if(v12.getHeight() < v3) {
                    }
                    else {
                        int v12_1 = v12.getRight();
                        if(v12_1 > v10) {
                            v10 = v12_1;
                        }
                    }
                }

                ++v9;
            }

            if(v10 != 0) {
                arg15.clipRect(v10, 0, v6, this.getHeight());
            }

            v4 = v10;
        }
        else {
            v11 = 0;
        }

        boolean v8_1 = super.drawChild(arg15, arg16, arg17);
        arg15.restoreToCount(v7);
        if(v0.scrimOpacity <= 0f || v5 == 0) {
            if(v0.shadowLeft != null) {
                float v3_1 = Math.max(0f, Math.min(v0.drawerPosition / (((float)AndroidUtilities.dp(20f))), 1f));
                if(v3_1 != 0f) {
                    v0.shadowLeft.setBounds(((int)v0.drawerPosition), arg16.getTop(), (((int)v0.drawerPosition)) + v0.shadowLeft.getIntrinsicWidth(), arg16.getBottom());
                    v0.shadowLeft.setAlpha(((int)(v3_1 * 255f)));
                    v0.shadowLeft.draw(arg15);
                }
            }
        }
        else if(this.indexOfChild(v2) == v11) {
            v0.scrimPaint.setColor((((int)(v0.scrimOpacity * 153f))) << 24);
            arg15.drawRect(((float)v4), 0f, ((float)v6), ((float)this.getHeight()), v0.scrimPaint);
        }

        return v8_1;
    }

    public View getDrawerLayout() {
        return this.drawerLayout;
    }

    public float getDrawerPosition() {
        return this.drawerPosition;
    }

    private float getScrimOpacity() {
        return this.scrimOpacity;
    }

    private int getTopInset(Object arg4) {
        int v1 = 0;
        if(Build$VERSION.SDK_INT >= 21 && arg4 != null) {
            v1 = ((WindowInsets)arg4).getSystemWindowInsetTop();
        }

        return v1;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public boolean isDrawerOpened() {
        return this.drawerOpened;
    }

    public static WindowInsets lambda$new$0(DrawerLayoutContainer arg1, View arg2, WindowInsets arg3) {
        AndroidUtilities.statusBarHeight = arg3.getSystemWindowInsetTop();
        arg1.lastInsets = arg3;
        boolean v0 = arg3.getSystemWindowInsetTop() > 0 || arg1.getBackground() != null ? false : true;
        ((DrawerLayoutContainer)arg2).setWillNotDraw(v0);
        ((DrawerLayoutContainer)arg2).requestLayout();
        return arg3.consumeSystemWindowInsets();
    }

    public void moveDrawerByX(float arg2) {
        this.setDrawerPosition(this.drawerPosition + arg2);
    }

    private void onDrawerAnimationEnd(boolean arg3) {
        this.startedTracking = false;
        this.currentAnimation = null;
        this.drawerOpened = arg3;
        if(!arg3 && ((this.drawerLayout instanceof ListView))) {
            this.drawerLayout.setSelectionFromTop(0, 0);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent arg2) {
        boolean v2 = (this.parentActionBarLayout.checkTransitionAnimation()) || (this.onTouchEvent(arg2)) ? true : false;
        return v2;
    }

    protected void onLayout(boolean arg5, int arg6, int arg7, int arg8, int arg9) {
        this.inLayout = true;
        int v5 = this.getChildCount();
        for(arg7 = 0; arg7 < v5; ++arg7) {
            View v8 = this.getChildAt(arg7);
            if(v8.getVisibility() != 8) {
                ViewGroup$LayoutParams v9 = v8.getLayoutParams();
                if(BuildVars.DEBUG_VERSION) {
                    if(this.drawerLayout != v8) {
                        v8.layout(((FrameLayout$LayoutParams)v9).leftMargin, ((FrameLayout$LayoutParams)v9).topMargin + this.getPaddingTop(), ((FrameLayout$LayoutParams)v9).leftMargin + v8.getMeasuredWidth(), ((FrameLayout$LayoutParams)v9).topMargin + v8.getMeasuredHeight() + this.getPaddingTop());
                        goto label_72;
                    }

                    v8.layout(-v8.getMeasuredWidth(), ((FrameLayout$LayoutParams)v9).topMargin + this.getPaddingTop(), 0, ((FrameLayout$LayoutParams)v9).topMargin + v8.getMeasuredHeight() + this.getPaddingTop());
                    goto label_72;
                }

                try {
                    if(this.drawerLayout != v8) {
                        v8.layout(((FrameLayout$LayoutParams)v9).leftMargin, ((FrameLayout$LayoutParams)v9).topMargin + this.getPaddingTop(), ((FrameLayout$LayoutParams)v9).leftMargin + v8.getMeasuredWidth(), ((FrameLayout$LayoutParams)v9).topMargin + v8.getMeasuredHeight() + this.getPaddingTop());
                        goto label_72;
                    }

                    v8.layout(-v8.getMeasuredWidth(), ((FrameLayout$LayoutParams)v9).topMargin + this.getPaddingTop(), 0, ((FrameLayout$LayoutParams)v9).topMargin + v8.getMeasuredHeight() + this.getPaddingTop());
                }
                catch(Exception v8_1) {
                    FileLog.e(((Throwable)v8_1));
                }
            }

        label_72:
        }

        this.inLayout = false;
    }

    @SuppressLint(value={"NewApi"}) protected void onMeasure(int arg14, int arg15) {
        int v9_1;
        int v10_1;
        int v0 = View$MeasureSpec.getSize(arg14);
        int v1 = View$MeasureSpec.getSize(arg15);
        this.setMeasuredDimension(v0, v1);
        int v3 = 21;
        if(Build$VERSION.SDK_INT < v3) {
            this.inLayout = true;
            if(v1 == AndroidUtilities.displaySize.y + AndroidUtilities.statusBarHeight) {
                if((this.getLayoutParams() instanceof ViewGroup$MarginLayoutParams)) {
                    this.setPadding(0, AndroidUtilities.statusBarHeight, 0, 0);
                }

                v1 = AndroidUtilities.displaySize.y;
            }
            else {
                if(!(this.getLayoutParams() instanceof ViewGroup$MarginLayoutParams)) {
                    goto label_26;
                }

                this.setPadding(0, 0, 0, 0);
            }

        label_26:
            this.inLayout = false;
        }

        int v2 = this.lastInsets == null || Build$VERSION.SDK_INT < v3 ? 0 : 1;
        int v6 = this.getChildCount();
        int v7;
        for(v7 = 0; v7 < v6; ++v7) {
            View v8 = this.getChildAt(v7);
            if(v8.getVisibility() == 8) {
            }
            else {
                ViewGroup$LayoutParams v9 = v8.getLayoutParams();
                if(v2 != 0) {
                    if(v8.getFitsSystemWindows()) {
                        this.dispatchChildInsets(v8, this.lastInsets, ((FrameLayout$LayoutParams)v9).gravity);
                    }
                    else if(v8.getTag() == null) {
                        Object v10 = this.lastInsets;
                        int v11 = ((FrameLayout$LayoutParams)v9).gravity;
                        boolean v12 = Build$VERSION.SDK_INT >= v3 ? true : false;
                        this.applyMarginInsets(((ViewGroup$MarginLayoutParams)v9), v10, v11, v12);
                    }
                }

                if(this.drawerLayout != v8) {
                    v10_1 = View$MeasureSpec.makeMeasureSpec(v0 - ((FrameLayout$LayoutParams)v9).leftMargin - ((FrameLayout$LayoutParams)v9).rightMargin, 1073741824);
                    v9_1 = View$MeasureSpec.makeMeasureSpec(v1 - ((FrameLayout$LayoutParams)v9).topMargin - ((FrameLayout$LayoutParams)v9).bottomMargin, 1073741824);
                }
                else {
                    v8.setPadding(0, 0, 0, 0);
                    v10_1 = DrawerLayoutContainer.getChildMeasureSpec(arg14, this.minDrawerMargin + ((FrameLayout$LayoutParams)v9).leftMargin + ((FrameLayout$LayoutParams)v9).rightMargin, ((FrameLayout$LayoutParams)v9).width);
                    v9_1 = DrawerLayoutContainer.getChildMeasureSpec(arg15, ((FrameLayout$LayoutParams)v9).topMargin + ((FrameLayout$LayoutParams)v9).bottomMargin, ((FrameLayout$LayoutParams)v9).height);
                }

                v8.measure(v10_1, v9_1);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent arg8) {
        // Method was not decompiled
    }

    public void openDrawer(boolean arg7) {
        if(!this.allowOpenDrawer) {
            return;
        }

        if((AndroidUtilities.isTablet()) && this.parentActionBarLayout != null && this.parentActionBarLayout.parentActivity != null) {
            AndroidUtilities.hideKeyboard(this.parentActionBarLayout.parentActivity.getCurrentFocus());
        }

        this.cancelCurrentAnimation();
        AnimatorSet v0 = new AnimatorSet();
        v0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "drawerPosition", new float[]{((float)this.drawerLayout.getMeasuredWidth())})});
        v0.setInterpolator(new DecelerateInterpolator());
        long v1 = arg7 ? ((long)Math.max(((int)(200f / (((float)this.drawerLayout.getMeasuredWidth())) * ((((float)this.drawerLayout.getMeasuredWidth())) - this.drawerPosition))), 50)) : 300;
        v0.setDuration(v1);
        v0.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                DrawerLayoutContainer.this.onDrawerAnimationEnd(true);
            }
        });
        v0.start();
        this.currentAnimation = v0;
    }

    private void prepareForDrawerOpen(MotionEvent arg3) {
        this.maybeStartTracking = false;
        this.startedTracking = true;
        if(arg3 != null) {
            this.startedTrackingX = ((int)arg3.getX());
        }

        this.beginTrackingSent = false;
    }

    public void requestDisallowInterceptTouchEvent(boolean arg2) {
        if((this.maybeStartTracking) && !this.startedTracking) {
            this.onTouchEvent(null);
        }

        super.requestDisallowInterceptTouchEvent(arg2);
    }

    public void requestLayout() {
        if(!this.inLayout) {
            super.requestLayout();
        }
    }

    public void setAllowDrawContent(boolean arg2) {
        if(this.allowDrawContent != arg2) {
            this.allowDrawContent = arg2;
            this.invalidate();
        }
    }

    public void setAllowOpenDrawer(boolean arg2, boolean arg3) {
        this.allowOpenDrawer = arg2;
        if(!this.allowOpenDrawer && this.drawerPosition != 0f) {
            if(!arg3) {
                this.setDrawerPosition(0f);
                this.onDrawerAnimationEnd(false);
            }
            else {
                this.closeDrawer(true);
            }
        }
    }

    public void setDrawerLayout(ViewGroup arg2) {
        this.drawerLayout = arg2;
        this.addView(this.drawerLayout);
        if(Build$VERSION.SDK_INT >= 21) {
            this.drawerLayout.setFitsSystemWindows(true);
        }
    }

    public void setDrawerPosition(float arg3) {
        // Method was not decompiled
    }

    public void setParentActionBarLayout(ActionBarLayout arg1) {
        this.parentActionBarLayout = arg1;
    }

    private void setScrimOpacity(float arg1) {
        this.scrimOpacity = arg1;
        this.invalidate();
    }
}

