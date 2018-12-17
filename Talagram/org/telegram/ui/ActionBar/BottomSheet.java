package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.v4.view.m;
import android.support.v4.view.o;
import android.text.TextUtils$TruncateAt;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager$LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.Components.LayoutHelper;

public class BottomSheet extends Dialog {
    public class BottomSheetCell extends FrameLayout {
        private ImageView imageView;
        private TextView textView;

        public BottomSheetCell(Context arg7, int arg8) {
            FrameLayout$LayoutParams v8;
            TextView v7;
            super(arg7);
            this.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            float v1 = 16f;
            this.setPadding(AndroidUtilities.dp(v1), 0, AndroidUtilities.dp(v1), 0);
            this.imageView = new ImageView(arg7);
            this.imageView.setScaleType(ImageView$ScaleType.CENTER);
            this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("dialogIcon"), PorterDuff$Mode.MULTIPLY));
            ImageView v0 = this.imageView;
            int v3 = 3;
            int v2 = LocaleController.isRTL ? 5 : 3;
            this.addView(((View)v0), LayoutHelper.createFrame(24, 24, v2 | 16));
            this.textView = new TextView(arg7);
            this.textView.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils$TruncateAt.END);
            if(arg8 == 0) {
                this.textView.setTextColor(Theme.getColor("dialogTextBlack"));
                this.textView.setTextSize(1, v1);
                v7 = this.textView;
                if(LocaleController.isRTL) {
                    v3 = 5;
                }

                v8 = LayoutHelper.createFrame(-2, -2, v3 | 16);
                goto label_80;
            }
            else {
                if(arg8 != 1) {
                    return;
                }

                this.textView.setGravity(17);
                this.textView.setTextColor(Theme.getColor("dialogTextBlack"));
                this.textView.setTextSize(1, 14f);
                this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                v7 = this.textView;
                v8 = LayoutHelper.createFrame(-1, -1f);
            label_80:
                this.addView(((View)v7), ((ViewGroup$LayoutParams)v8));
            }
        }

        static TextView access$1300(BottomSheetCell arg0) {
            return arg0.textView;
        }

        protected void onMeasure(int arg2, int arg3) {
            super.onMeasure(arg2, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f), 1073741824));
        }

        public void setGravity(int arg2) {
            this.textView.setGravity(arg2);
        }

        public void setTextAndIcon(CharSequence arg4, int arg5) {
            this.textView.setText(arg4);
            if(arg5 != 0) {
                this.imageView.setImageResource(arg5);
                this.imageView.setVisibility(0);
                TextView v5 = this.textView;
                float v1 = 56f;
                int v0 = LocaleController.isRTL ? 0 : AndroidUtilities.dp(v1);
                int v1_1 = LocaleController.isRTL ? AndroidUtilities.dp(v1) : 0;
                v5.setPadding(v0, 0, v1_1, 0);
            }
            else {
                this.imageView.setVisibility(4);
                this.textView.setPadding(0, 0, 0, 0);
            }
        }

        public void setTextColor(int arg2) {
            this.textView.setTextColor(arg2);
        }
    }

    public class BottomSheetDelegate implements BottomSheetDelegateInterface {
        public BottomSheetDelegate() {
            super();
        }

        public boolean canDismiss() {
            return 1;
        }

        public void onOpenAnimationEnd() {
        }

        public void onOpenAnimationStart() {
        }
    }

    public interface BottomSheetDelegateInterface {
        boolean canDismiss();

        void onOpenAnimationEnd();

        void onOpenAnimationStart();
    }

    public class Builder {
        private BottomSheet bottomSheet;

        public Builder(Context arg3) {
            super();
            this.bottomSheet = new BottomSheet(arg3, false);
        }

        public Builder(Context arg2, boolean arg3) {
            super();
            this.bottomSheet = new BottomSheet(arg2, arg3);
        }

        public BottomSheet create() {
            return this.bottomSheet;
        }

        public Runnable getDismissRunnable() {
            return this.bottomSheet.dismissRunnable;
        }

        public Builder setApplyBottomPadding(boolean arg2) {
            this.bottomSheet.applyBottomPadding = arg2;
            return this;
        }

        public Builder setApplyTopPadding(boolean arg2) {
            this.bottomSheet.applyTopPadding = arg2;
            return this;
        }

        public Builder setCustomView(View arg2) {
            this.bottomSheet.customView = arg2;
            return this;
        }

        public Builder setDelegate(BottomSheetDelegate arg2) {
            this.bottomSheet.setDelegate(((BottomSheetDelegateInterface)arg2));
            return this;
        }

        public Builder setItems(CharSequence[] arg2, int[] arg3, DialogInterface$OnClickListener arg4) {
            this.bottomSheet.items = arg2;
            this.bottomSheet.itemIcons = arg3;
            this.bottomSheet.onClickListener = arg4;
            return this;
        }

        public Builder setItems(CharSequence[] arg2, DialogInterface$OnClickListener arg3) {
            this.bottomSheet.items = arg2;
            this.bottomSheet.onClickListener = arg3;
            return this;
        }

        public Builder setTag(int arg2) {
            this.bottomSheet.tag = arg2;
            return this;
        }

        public Builder setTitle(CharSequence arg2) {
            this.bottomSheet.title = arg2;
            return this;
        }

        public BottomSheet setUseFullWidth(boolean arg2) {
            this.bottomSheet.fullWidth = arg2;
            return this.bottomSheet;
        }

        public Builder setUseHardwareLayer(boolean arg2) {
            this.bottomSheet.useHardwareLayer = arg2;
            return this;
        }

        public BottomSheet show() {
            this.bottomSheet.show();
            return this.bottomSheet;
        }
    }

    public class ContainerView extends FrameLayout implements m {
        private AnimatorSet currentAnimation;
        private boolean maybeStartTracking;
        private o nestedScrollingParentHelper;
        private boolean startedTracking;
        private int startedTrackingPointerId;
        private int startedTrackingX;
        private int startedTrackingY;
        private VelocityTracker velocityTracker;

        public ContainerView(BottomSheet arg1, Context arg2) {
            BottomSheet.this = arg1;
            super(arg2);
            this.velocityTracker = null;
            this.startedTrackingPointerId = -1;
            this.maybeStartTracking = false;
            this.startedTracking = false;
            this.currentAnimation = null;
            this.nestedScrollingParentHelper = new o(((ViewGroup)this));
        }

        static AnimatorSet access$400(ContainerView arg0) {
            return arg0.currentAnimation;
        }

        static AnimatorSet access$402(ContainerView arg0, AnimatorSet arg1) {
            arg0.currentAnimation = arg1;
            return arg1;
        }

        private void cancelCurrentAnimation() {
            if(this.currentAnimation != null) {
                this.currentAnimation.cancel();
                this.currentAnimation = null;
            }
        }

        private void checkDismiss(float arg8, float arg9) {
            // Method was not decompiled
        }

        public int getNestedScrollAxes() {
            return this.nestedScrollingParentHelper.a();
        }

        public boolean hasOverlappingRendering() {
            return 0;
        }

        protected void onDraw(Canvas arg2) {
            super.onDraw(arg2);
            BottomSheet.this.onContainerDraw(arg2);
        }

        public boolean onInterceptTouchEvent(MotionEvent arg2) {
            if(BottomSheet.this.canDismissWithSwipe()) {
                return this.processTouchEvent(arg2, true);
            }

            return super.onInterceptTouchEvent(arg2);
        }

        protected void onLayout(boolean arg14, int arg15, int arg16, int arg17, int arg18) {
            int v9;
            int v8;
            int v4;
            int v3;
            int v2;
            int v1;
            ContainerView v0 = this;
            BottomSheet.access$710(v0.this$0);
            int v7 = 21;
            if(v0.this$0.containerView != null) {
                if(v0.this$0.lastInsets == null || Build$VERSION.SDK_INT < v7) {
                    v1 = arg15;
                    v2 = arg17;
                }
                else {
                    v1 = v0.this$0.lastInsets.getSystemWindowInsetLeft() + arg15;
                    v2 = arg17 - v0.this$0.lastInsets.getSystemWindowInsetRight();
                }

                v3 = arg18 - arg16 - v0.this$0.containerView.getMeasuredHeight();
                v4 = (v2 - v1 - v0.this$0.containerView.getMeasuredWidth()) / 2;
                if(v0.this$0.lastInsets != null && Build$VERSION.SDK_INT >= v7) {
                    v4 += v0.this$0.lastInsets.getSystemWindowInsetLeft();
                }

                v0.this$0.containerView.layout(v4, v3, v0.this$0.containerView.getMeasuredWidth() + v4, v0.this$0.containerView.getMeasuredHeight() + v3);
                v8 = v1;
                v9 = v2;
            }
            else {
                v8 = arg15;
                v9 = arg17;
            }

            int v10 = this.getChildCount();
            int v11;
            for(v11 = 0; v11 < v10; ++v11) {
                View v12 = this.getChildAt(v11);
                if(v12.getVisibility() != 8) {
                    if(v12 == v0.this$0.containerView) {
                    }
                    else if(!v0.this$0.onCustomLayout(v12, v8, arg16, v9, arg18)) {
                        ViewGroup$LayoutParams v1_1 = v12.getLayoutParams();
                        v2 = v12.getMeasuredWidth();
                        v3 = v12.getMeasuredHeight();
                        v4 = ((FrameLayout$LayoutParams)v1_1).gravity;
                        if(v4 == -1) {
                            v4 = 51;
                        }

                        int v5 = v4 & 7;
                        v4 &= 112;
                        v5 &= 7;
                        if(v5 == 1) {
                            v5 = (v9 - v8 - v2) / 2 + ((FrameLayout$LayoutParams)v1_1).leftMargin;
                        label_102:
                            v5 -= ((FrameLayout$LayoutParams)v1_1).rightMargin;
                        }
                        else if(v5 != 5) {
                            v5 = ((FrameLayout$LayoutParams)v1_1).leftMargin;
                        }
                        else {
                            v5 = v9 - v2;
                            goto label_102;
                        }

                        if(v4 != 16) {
                            if(v4 != 48 && v4 == 80) {
                                v4 = arg18 - arg16 - v3;
                                goto label_120;
                            }

                            v1 = ((FrameLayout$LayoutParams)v1_1).topMargin;
                        }
                        else {
                            v4 = (arg18 - arg16 - v3) / 2 + ((FrameLayout$LayoutParams)v1_1).topMargin;
                        label_120:
                            v1 = v4 - ((FrameLayout$LayoutParams)v1_1).bottomMargin;
                        }

                        if(v0.this$0.lastInsets != null && Build$VERSION.SDK_INT >= v7) {
                            v5 += v0.this$0.lastInsets.getSystemWindowInsetLeft();
                        }

                        v12.layout(v5, v1, v2 + v5, v3 + v1);
                    }
                }
            }

            if(v0.this$0.layoutCount == 0 && v0.this$0.startAnimationRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(v0.this$0.startAnimationRunnable);
                v0.this$0.startAnimationRunnable.run();
                v0.this$0.startAnimationRunnable = null;
            }
        }

        protected void onMeasure(int arg11, int arg12) {
            float v1_1;
            arg11 = View$MeasureSpec.getSize(arg11);
            arg12 = View$MeasureSpec.getSize(arg12);
            int v1 = 21;
            if(BottomSheet.this.lastInsets != null && Build$VERSION.SDK_INT >= v1) {
                arg12 -= BottomSheet.this.lastInsets.getSystemWindowInsetBottom();
            }

            this.setMeasuredDimension(arg11, arg12);
            if(BottomSheet.this.lastInsets != null && Build$VERSION.SDK_INT >= v1) {
                arg11 -= BottomSheet.this.lastInsets.getSystemWindowInsetRight() + BottomSheet.this.lastInsets.getSystemWindowInsetLeft();
            }

            int v0 = 0;
            v1 = arg11 < arg12 ? 1 : 0;
            int v3 = 1073741824;
            if(BottomSheet.this.containerView != null) {
                int v4 = -2147483648;
                if(!BottomSheet.this.fullWidth) {
                    float v5 = 0.8f;
                    if(AndroidUtilities.isTablet()) {
                        v1_1 = (((float)Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y))) * v5;
                        goto label_49;
                    }
                    else if(v1 != 0) {
                        v1 = BottomSheet.backgroundPaddingLeft * 2 + arg11;
                    }
                    else {
                        v1_1 = Math.max((((float)arg11)) * v5, ((float)Math.min(AndroidUtilities.dp(480f), arg11)));
                    label_49:
                        v1 = (((int)v1_1)) + BottomSheet.backgroundPaddingLeft * 2;
                    }

                    v1 = View$MeasureSpec.makeMeasureSpec(v1, v3);
                    BottomSheet.this.containerView.measure(v1, View$MeasureSpec.makeMeasureSpec(arg12, v4));
                }
                else {
                    BottomSheet.this.containerView.measure(View$MeasureSpec.makeMeasureSpec(BottomSheet.backgroundPaddingLeft * 2 + arg11, v3), View$MeasureSpec.makeMeasureSpec(arg12, v4));
                }
            }

            v1 = this.getChildCount();
            while(v0 < v1) {
                View v5_1 = this.getChildAt(v0);
                if(v5_1.getVisibility() != 8) {
                    if(v5_1 == BottomSheet.this.containerView) {
                    }
                    else if(!BottomSheet.this.onCustomMeasure(v5_1, arg11, arg12)) {
                        this.measureChildWithMargins(v5_1, View$MeasureSpec.makeMeasureSpec(arg11, v3), 0, View$MeasureSpec.makeMeasureSpec(arg12, v3), 0);
                    }
                }

                ++v0;
            }
        }

        public boolean onNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
            return 0;
        }

        public boolean onNestedPreFling(View arg1, float arg2, float arg3) {
            return 0;
        }

        public void onNestedPreScroll(View arg2, int arg3, int arg4, int[] arg5) {
            if(!BottomSheet.this.dismissed) {
                if(!BottomSheet.this.allowNestedScroll) {
                }
                else {
                    this.cancelCurrentAnimation();
                    float v2 = BottomSheet.this.containerView.getTranslationY();
                    if(v2 > 0f && arg4 > 0) {
                        v2 -= ((float)arg4);
                        arg5[1] = arg4;
                        if(v2 < 0f) {
                            v2 = 0f;
                        }

                        BottomSheet.this.containerView.setTranslationY(v2);
                    }
                }
            }
        }

        public void onNestedScroll(View arg1, int arg2, int arg3, int arg4, int arg5) {
            if(!BottomSheet.this.dismissed) {
                if(!BottomSheet.this.allowNestedScroll) {
                }
                else {
                    this.cancelCurrentAnimation();
                    if(arg5 != 0) {
                        float v1 = BottomSheet.this.containerView.getTranslationY() - (((float)arg5));
                        if(v1 < 0f) {
                            v1 = 0f;
                        }

                        BottomSheet.this.containerView.setTranslationY(v1);
                    }
                }
            }
        }

        public void onNestedScrollAccepted(View arg2, View arg3, int arg4) {
            this.nestedScrollingParentHelper.a(arg2, arg3, arg4);
            if(!BottomSheet.this.dismissed) {
                if(!BottomSheet.this.allowNestedScroll) {
                }
                else {
                    this.cancelCurrentAnimation();
                }
            }
        }

        public boolean onStartNestedScroll(View arg1, View arg2, int arg3) {
            boolean v1 = BottomSheet.this.nestedScrollChild != null && arg1 != BottomSheet.this.nestedScrollChild || ((BottomSheet.this.dismissed) || !BottomSheet.this.allowNestedScroll || arg3 != 2 || (BottomSheet.this.canDismissWithSwipe())) ? false : true;
            return v1;
        }

        public void onStopNestedScroll(View arg2) {
            this.nestedScrollingParentHelper.a(arg2);
            if(!BottomSheet.this.dismissed) {
                if(!BottomSheet.this.allowNestedScroll) {
                }
                else {
                    BottomSheet.this.containerView.getTranslationY();
                    this.checkDismiss(0f, 0f);
                }
            }
        }

        public boolean onTouchEvent(MotionEvent arg2) {
            return this.processTouchEvent(arg2, false);
        }

        boolean processTouchEvent(MotionEvent arg7, boolean arg8) {
            boolean v1 = false;
            if(BottomSheet.this.dismissed) {
                return 0;
            }

            if(BottomSheet.this.onContainerTouchEvent(arg7)) {
                return 1;
            }

            int v3 = 2;
            if(!BottomSheet.this.canDismissWithTouchOutside() || arg7 == null) {
            label_59:
                float v0 = 0f;
                if(arg7 != null && arg7.getAction() == v3 && arg7.getPointerId(0) == this.startedTrackingPointerId) {
                    if(this.velocityTracker == null) {
                        this.velocityTracker = VelocityTracker.obtain();
                    }

                    float v3_1 = ((float)Math.abs(((int)(arg7.getX() - (((float)this.startedTrackingX))))));
                    float v4 = ((float)((((int)arg7.getY())) - this.startedTrackingY));
                    this.velocityTracker.addMovement(arg7);
                    if((this.maybeStartTracking) && !this.startedTracking && v4 > 0f && v4 / 3f > Math.abs(v3_1) && Math.abs(v4) >= (((float)BottomSheet.this.touchSlop))) {
                        this.startedTrackingY = ((int)arg7.getY());
                        this.maybeStartTracking = false;
                        this.startedTracking = true;
                        this.requestDisallowInterceptTouchEvent(true);
                        goto label_164;
                    }

                    if(!this.startedTracking) {
                        goto label_164;
                    }

                    v3_1 = BottomSheet.this.containerView.getTranslationY() + v4;
                    if(v3_1 < 0f) {
                    }
                    else {
                        v0 = v3_1;
                    }

                    BottomSheet.this.containerView.setTranslationY(v0);
                    this.startedTrackingY = ((int)arg7.getY());
                    goto label_164;
                }

                if(arg7 != null) {
                    if(arg7 == null) {
                        goto label_164;
                    }
                    else if(arg7.getPointerId(0) != this.startedTrackingPointerId) {
                        goto label_164;
                    }
                    else if(arg7.getAction() != 3 && arg7.getAction() != 1) {
                        if(arg7.getAction() == 6) {
                            goto label_134;
                        }

                        goto label_164;
                    }
                }

            label_134:
                if(this.velocityTracker == null) {
                    this.velocityTracker = VelocityTracker.obtain();
                }

                this.velocityTracker.computeCurrentVelocity(1000);
                float v7 = BottomSheet.this.containerView.getTranslationY();
                if((this.startedTracking) || v7 != 0f) {
                    this.checkDismiss(this.velocityTracker.getXVelocity(), this.velocityTracker.getYVelocity());
                }
                else {
                    this.maybeStartTracking = false;
                }

                this.startedTracking = false;
                if(this.velocityTracker != null) {
                    this.velocityTracker.recycle();
                    this.velocityTracker = null;
                }

                this.startedTrackingPointerId = -1;
            }
            else {
                if(arg7.getAction() != 0 && arg7.getAction() != v3) {
                    goto label_59;
                }

                if(this.startedTracking) {
                    goto label_59;
                }

                if(this.maybeStartTracking) {
                    goto label_59;
                }

                if(arg7.getPointerCount() != 1) {
                    goto label_59;
                }

                this.startedTrackingX = ((int)arg7.getX());
                this.startedTrackingY = ((int)arg7.getY());
                if(this.startedTrackingY >= BottomSheet.this.containerView.getTop() && this.startedTrackingX >= BottomSheet.this.containerView.getLeft()) {
                    if(this.startedTrackingX > BottomSheet.this.containerView.getRight()) {
                    }
                    else {
                        this.startedTrackingPointerId = arg7.getPointerId(0);
                        this.maybeStartTracking = true;
                        this.cancelCurrentAnimation();
                        if(this.velocityTracker != null) {
                            this.velocityTracker.clear();
                        }
                        else {
                        }

                        goto label_164;
                    }
                }

                BottomSheet.this.dismiss();
                return 1;
            }

        label_164:
            if(!arg8 && (this.maybeStartTracking) || ((this.startedTracking) || !BottomSheet.this.canDismissWithSwipe())) {
                v1 = true;
            }

            return v1;
        }

        public void requestDisallowInterceptTouchEvent(boolean arg2) {
            if((this.maybeStartTracking) && !this.startedTracking) {
                this.onTouchEvent(null);
            }

            super.requestDisallowInterceptTouchEvent(arg2);
        }
    }

    private AccelerateInterpolator accelerateInterpolator;
    private boolean allowCustomAnimation;
    private boolean allowDrawContent;
    private boolean allowNestedScroll;
    private boolean applyBottomPadding;
    private boolean applyTopPadding;
    protected ColorDrawable backDrawable;
    protected static int backgroundPaddingLeft;
    protected static int backgroundPaddingTop;
    protected ContainerView container;
    protected ViewGroup containerView;
    protected int currentAccount;
    protected AnimatorSet currentSheetAnimation;
    private View customView;
    private DecelerateInterpolator decelerateInterpolator;
    private BottomSheetDelegateInterface delegate;
    private Runnable dismissRunnable;
    private boolean dismissed;
    private boolean focusable;
    protected boolean fullWidth;
    private int[] itemIcons;
    private ArrayList itemViews;
    private CharSequence[] items;
    private WindowInsets lastInsets;
    private int layoutCount;
    protected View nestedScrollChild;
    private DialogInterface$OnClickListener onClickListener;
    private Drawable shadowDrawable;
    private boolean showWithoutAnimation;
    private Runnable startAnimationRunnable;
    private int tag;
    private CharSequence title;
    private int touchSlop;
    private boolean useFastDismiss;
    private boolean useHardwareLayer;

    public BottomSheet(Context arg7, boolean arg8) {
        super(arg7, 2131689885);
        this.currentAccount = UserConfig.selectedAccount;
        this.allowDrawContent = true;
        this.useHardwareLayer = true;
        this.backDrawable = new ColorDrawable(-16777216);
        this.allowCustomAnimation = true;
        this.allowNestedScroll = true;
        this.applyTopPadding = true;
        this.applyBottomPadding = true;
        this.decelerateInterpolator = new DecelerateInterpolator();
        this.accelerateInterpolator = new AccelerateInterpolator();
        this.itemViews = new ArrayList();
        this.dismissRunnable = new -$$Lambda$wKJSb77Iz9CSKJu9VMkyxGvOd-c(this);
        int v2 = 21;
        if(Build$VERSION.SDK_INT >= v2) {
            this.getWindow().addFlags(-2147417856);
        }

        this.touchSlop = ViewConfiguration.get(arg7).getScaledTouchSlop();
        Rect v1 = new Rect();
        this.shadowDrawable = arg7.getResources().getDrawable(2131231559).mutate();
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor("dialogBackground"), PorterDuff$Mode.MULTIPLY));
        this.shadowDrawable.getPadding(v1);
        BottomSheet.backgroundPaddingLeft = v1.left;
        BottomSheet.backgroundPaddingTop = v1.top;
        this.container = new ContainerView(this.getContext()) {
            public boolean drawChild(Canvas arg3, View arg4, long arg5) {
                boolean v0 = true;
                try {
                    if(!BottomSheet.access$900(BottomSheet.this)) {
                        return false;
                    }
                    else if(!super.drawChild(arg3, arg4, arg5)) {
                        return false;
                    }
                }
                catch(Exception v3) {
                    FileLog.e(((Throwable)v3));
                    return 1;
                }

                return v0;
            }
        };
        this.container.setBackgroundDrawable(this.backDrawable);
        this.focusable = arg8;
        if(Build$VERSION.SDK_INT >= v2) {
            this.container.setFitsSystemWindows(true);
            this.container.setOnApplyWindowInsetsListener(new -$$Lambda$BottomSheet$IjDyKTRWpdCwBFc4MNcspRHUp7w(this));
            this.container.setSystemUiVisibility(1280);
        }

        this.backDrawable.setAlpha(0);
    }

    static boolean access$000(BottomSheet arg0) {
        return arg0.dismissed;
    }

    static boolean access$100(BottomSheet arg0) {
        return arg0.allowNestedScroll;
    }

    static void access$1000(BottomSheet arg0) {
        arg0.startOpenAnimation();
    }

    static BottomSheetDelegateInterface access$1100(BottomSheet arg0) {
        return arg0.delegate;
    }

    static boolean access$1200(BottomSheet arg0) {
        return arg0.useHardwareLayer;
    }

    static boolean access$1202(BottomSheet arg0, boolean arg1) {
        arg0.useHardwareLayer = arg1;
        return arg1;
    }

    static DialogInterface$OnClickListener access$1400(BottomSheet arg0) {
        return arg0.onClickListener;
    }

    static DialogInterface$OnClickListener access$1402(BottomSheet arg0, DialogInterface$OnClickListener arg1) {
        arg0.onClickListener = arg1;
        return arg1;
    }

    static void access$1501(BottomSheet arg0) {
        super.dismiss();
    }

    static CharSequence[] access$1602(BottomSheet arg0, CharSequence[] arg1) {
        arg0.items = arg1;
        return arg1;
    }

    static int[] access$1702(BottomSheet arg0, int[] arg1) {
        arg0.itemIcons = arg1;
        return arg1;
    }

    static View access$1802(BottomSheet arg0, View arg1) {
        arg0.customView = arg1;
        return arg1;
    }

    static CharSequence access$1902(BottomSheet arg0, CharSequence arg1) {
        arg0.title = arg1;
        return arg1;
    }

    static boolean access$200(BottomSheet arg0) {
        return arg0.allowCustomAnimation;
    }

    static int access$2002(BottomSheet arg0, int arg1) {
        arg0.tag = arg1;
        return arg1;
    }

    static boolean access$202(BottomSheet arg0, boolean arg1) {
        arg0.allowCustomAnimation = arg1;
        return arg1;
    }

    static boolean access$2102(BottomSheet arg0, boolean arg1) {
        arg0.applyTopPadding = arg1;
        return arg1;
    }

    static boolean access$2202(BottomSheet arg0, boolean arg1) {
        arg0.applyBottomPadding = arg1;
        return arg1;
    }

    static Runnable access$2300(BottomSheet arg0) {
        return arg0.dismissRunnable;
    }

    static boolean access$302(BottomSheet arg0, boolean arg1) {
        arg0.useFastDismiss = arg1;
        return arg1;
    }

    static int access$500(BottomSheet arg0) {
        return arg0.touchSlop;
    }

    static WindowInsets access$600(BottomSheet arg0) {
        return arg0.lastInsets;
    }

    static int access$700(BottomSheet arg0) {
        return arg0.layoutCount;
    }

    static int access$710(BottomSheet arg2) {
        int v0 = arg2.layoutCount;
        arg2.layoutCount = v0 - 1;
        return v0;
    }

    static Runnable access$800(BottomSheet arg0) {
        return arg0.startAnimationRunnable;
    }

    static Runnable access$802(BottomSheet arg0, Runnable arg1) {
        arg0.startAnimationRunnable = arg1;
        return arg1;
    }

    static boolean access$900(BottomSheet arg0) {
        return arg0.allowDrawContent;
    }

    protected boolean canDismissWithSwipe() {
        return 1;
    }

    protected boolean canDismissWithTouchOutside() {
        return 1;
    }

    private void cancelSheetAnimation() {
        if(this.currentSheetAnimation != null) {
            this.currentSheetAnimation.cancel();
            this.currentSheetAnimation = null;
        }
    }

    public void dismiss() {
        // Method was not decompiled
    }

    public void dismissInternal() {
        try {
            super.dismiss();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public void dismissWithButtonClick(int arg9) {
        // Method was not decompiled
    }

    public FrameLayout getContainer() {
        return this.container;
    }

    protected int getLeftInset() {
        if(this.lastInsets != null && Build$VERSION.SDK_INT >= 21) {
            return this.lastInsets.getSystemWindowInsetLeft();
        }

        return 0;
    }

    public ViewGroup getSheetContainer() {
        return this.containerView;
    }

    public int getTag() {
        return this.tag;
    }

    public boolean isDismissed() {
        return this.dismissed;
    }

    public static WindowInsets lambda$new$0(BottomSheet arg0, View arg1, WindowInsets arg2) {
        arg0.lastInsets = arg2;
        arg1.requestLayout();
        return arg2.consumeSystemWindowInsets();
    }

    static boolean lambda$onCreate$1(View arg0, MotionEvent arg1) {
        return 1;
    }

    public static void lambda$onCreate$2(BottomSheet arg0, View arg1) {
        arg0.dismissWithButtonClick(arg1.getTag().intValue());
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void onConfigurationChanged(Configuration arg1) {
    }

    public void onContainerDraw(Canvas arg1) {
    }

    protected boolean onContainerTouchEvent(MotionEvent arg1) {
        return 0;
    }

    protected void onContainerTranslationYChanged(float arg1) {
    }

    protected void onCreate(Bundle arg14) {
        int v0_2;
        int v6;
        super.onCreate(arg14);
        Window v14 = this.getWindow();
        v14.setWindowAnimations(2131689683);
        int v2 = -1;
        this.setContentView(this.container, new ViewGroup$LayoutParams(v2, v2));
        float v1 = 8f;
        if(this.containerView == null) {
            this.containerView = new FrameLayout(this.getContext()) {
                public boolean hasOverlappingRendering() {
                    return 0;
                }

                public void setTranslationY(float arg2) {
                    super.setTranslationY(arg2);
                    BottomSheet.this.onContainerTranslationYChanged(arg2);
                }
            };
            this.containerView.setBackgroundDrawable(this.shadowDrawable);
            ViewGroup v0 = this.containerView;
            int v5 = BottomSheet.backgroundPaddingLeft;
            v6 = this.applyTopPadding ? AndroidUtilities.dp(v1) : 0;
            v6 = v6 + BottomSheet.backgroundPaddingTop - 1;
            int v7 = BottomSheet.backgroundPaddingLeft;
            int v8 = this.applyBottomPadding ? AndroidUtilities.dp(v1) : 0;
            v0.setPadding(v5, v6, v7, v8);
        }

        if(Build$VERSION.SDK_INT >= 21) {
            this.containerView.setFitsSystemWindows(true);
        }

        this.containerView.setVisibility(4);
        this.container.addView(this.containerView, 0, LayoutHelper.createFrame(v2, -2, 80));
        if(this.title != null) {
            TextView v0_1 = new TextView(this.getContext());
            v0_1.setLines(1);
            v0_1.setSingleLine(true);
            v0_1.setText(this.title);
            v0_1.setTextColor(Theme.getColor("dialogTextGray2"));
            v0_1.setTextSize(1, 16f);
            v0_1.setEllipsize(TextUtils$TruncateAt.MIDDLE);
            v0_1.setPadding(AndroidUtilities.dp(16f), 0, AndroidUtilities.dp(16f), AndroidUtilities.dp(v1));
            v0_1.setGravity(16);
            this.containerView.addView(((View)v0_1), LayoutHelper.createFrame(v2, 48f));
            v0_1.setOnTouchListener(-$$Lambda$BottomSheet$bysjO3P7kPXgYfq-9zd4-H2r0_8.INSTANCE);
            v0_2 = 48;
        }
        else {
            v0_2 = 0;
        }

        if(this.customView != null) {
            if(this.customView.getParent() != null) {
                this.customView.getParent().removeView(this.customView);
            }

            this.containerView.addView(this.customView, LayoutHelper.createFrame(-1, -2f, 51, 0f, ((float)v0_2), 0f, 0f));
        }
        else {
            if(this.items == null) {
                goto label_147;
            }

            int v1_1 = v0_2;
            for(v0_2 = 0; v0_2 < this.items.length; ++v0_2) {
                if(this.items[v0_2] == null) {
                }
                else {
                    BottomSheetCell v3 = new BottomSheetCell(this.getContext(), 0);
                    CharSequence v5_1 = this.items[v0_2];
                    v6 = this.itemIcons != null ? this.itemIcons[v0_2] : 0;
                    v3.setTextAndIcon(v5_1, v6);
                    this.containerView.addView(((View)v3), LayoutHelper.createFrame(-1, 48f, 51, 0f, ((float)v1_1), 0f, 0f));
                    v1_1 += 48;
                    v3.setTag(Integer.valueOf(v0_2));
                    v3.setOnClickListener(new -$$Lambda$BottomSheet$6IWrsZfWA7fvlM9-8brqhUJi-uM(this));
                    this.itemViews.add(v3);
                }
            }
        }

    label_147:
        WindowManager$LayoutParams v0_3 = v14.getAttributes();
        v0_3.width = v2;
        v0_3.gravity = 51;
        v0_3.dimAmount = 0f;
        ((WindowManager$LayoutParams)v0_2).flags &= -3;
        if(!this.focusable) {
            v0_3.flags |= 131072;
        }

        v0_3.height = v2;
        v14.setAttributes(v0_3);
    }

    protected boolean onCustomCloseAnimation() {
        return 0;
    }

    protected boolean onCustomLayout(View arg1, int arg2, int arg3, int arg4, int arg5) {
        return 0;
    }

    protected boolean onCustomMeasure(View arg1, int arg2, int arg3) {
        return 0;
    }

    protected boolean onCustomOpenAnimation() {
        return 0;
    }

    public void setAllowDrawContent(boolean arg2) {
        ColorDrawable v0;
        if(this.allowDrawContent != arg2) {
            this.allowDrawContent = arg2;
            ContainerView v2 = this.container;
            if(this.allowDrawContent) {
                v0 = this.backDrawable;
            }
            else {
                Drawable v0_1 = null;
            }

            v2.setBackgroundDrawable(((Drawable)v0));
            this.container.invalidate();
        }
    }

    public void setAllowNestedScroll(boolean arg2) {
        this.allowNestedScroll = arg2;
        if(!this.allowNestedScroll) {
            this.containerView.setTranslationY(0f);
        }
    }

    public void setApplyBottomPadding(boolean arg1) {
        this.applyBottomPadding = arg1;
    }

    public void setApplyTopPadding(boolean arg1) {
        this.applyTopPadding = arg1;
    }

    public void setBackgroundColor(int arg3) {
        this.shadowDrawable.setColorFilter(arg3, PorterDuff$Mode.MULTIPLY);
    }

    public void setCustomView(View arg1) {
        this.customView = arg1;
    }

    public void setDelegate(BottomSheetDelegateInterface arg1) {
        this.delegate = arg1;
    }

    public void setItemText(int arg2, CharSequence arg3) {
        if(arg2 >= 0) {
            if(arg2 >= this.itemViews.size()) {
            }
            else {
                BottomSheetCell.access$1300(this.itemViews.get(arg2)).setText(arg3);
            }
        }
    }

    public void setShowWithoutAnimation(boolean arg1) {
        this.showWithoutAnimation = arg1;
    }

    public void setTitle(CharSequence arg1) {
        this.title = arg1;
    }

    public void show() {
        super.show();
        if(this.focusable) {
            this.getWindow().setSoftInputMode(16);
        }

        this.dismissed = false;
        this.cancelSheetAnimation();
        int v4 = 2;
        this.containerView.measure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x + BottomSheet.backgroundPaddingLeft * 2, -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, -2147483648));
        if(this.showWithoutAnimation) {
            this.backDrawable.setAlpha(51);
            this.containerView.setTranslationY(0f);
            return;
        }

        this.backDrawable.setAlpha(0);
        if(Build$VERSION.SDK_INT >= 18) {
            this.layoutCount = v4;
            this.containerView.setTranslationY(((float)this.containerView.getMeasuredHeight()));
            org.telegram.ui.ActionBar.BottomSheet$3 v0 = new Runnable() {
                public void run() {
                    if(BottomSheet.this.startAnimationRunnable == this) {
                        if(BottomSheet.this.dismissed) {
                        }
                        else {
                            BottomSheet.this.startAnimationRunnable = null;
                            BottomSheet.this.startOpenAnimation();
                        }
                    }
                }
            };
            this.startAnimationRunnable = ((Runnable)v0);
            AndroidUtilities.runOnUIThread(((Runnable)v0), 150);
        }
        else {
            this.startOpenAnimation();
        }
    }

    private void startOpenAnimation() {
        if(this.dismissed) {
            return;
        }

        this.containerView.setVisibility(0);
        if(!this.onCustomOpenAnimation()) {
            int v3 = 2;
            if(Build$VERSION.SDK_INT >= 20 && (this.useHardwareLayer)) {
                this.container.setLayerType(v3, null);
            }

            this.containerView.setTranslationY(((float)this.containerView.getMeasuredHeight()));
            AnimatorSet v0 = new AnimatorSet();
            Animator[] v2 = new Animator[v3];
            v2[0] = ObjectAnimator.ofFloat(this.containerView, "translationY", new float[]{0f});
            v2[1] = ObjectAnimator.ofInt(this.backDrawable, "alpha", new int[]{51});
            v0.playTogether(v2);
            v0.setDuration(200);
            v0.setStartDelay(20);
            v0.setInterpolator(new DecelerateInterpolator());
            v0.addListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator arg2) {
                    if(BottomSheet.this.currentSheetAnimation != null && (BottomSheet.this.currentSheetAnimation.equals(arg2))) {
                        BottomSheet.this.currentSheetAnimation = null;
                    }
                }

                public void onAnimationEnd(Animator arg3) {
                    if(BottomSheet.this.currentSheetAnimation != null && (BottomSheet.this.currentSheetAnimation.equals(arg3))) {
                        AnimatorSet v0 = null;
                        BottomSheet.this.currentSheetAnimation = v0;
                        if(BottomSheet.this.delegate != null) {
                            BottomSheet.this.delegate.onOpenAnimationEnd();
                        }

                        if(!BottomSheet.this.useHardwareLayer) {
                            return;
                        }

                        BottomSheet.this.container.setLayerType(0, ((Paint)v0));
                    }
                }
            });
            v0.start();
            this.currentSheetAnimation = v0;
        }
    }
}

