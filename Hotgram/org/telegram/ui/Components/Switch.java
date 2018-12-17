package org.telegram.ui.Components;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region$Op;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.annotation.Keep;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.CompoundButton;
import org.telegram.customization.util.c;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;

public class Switch extends CompoundButton {
    public class Insets {
        public static final Insets NONE;
        public final int bottom;
        public final int left;
        public final int right;
        public final int top;

        static {
            Insets.NONE = new Insets(AndroidUtilities.dp(4f), 0, AndroidUtilities.dp(4f), 0);
        }

        private Insets(int arg1, int arg2, int arg3, int arg4) {
            super();
            this.left = arg1;
            this.top = arg2;
            this.right = arg3;
            this.bottom = arg4;
        }
    }

    private static final int THUMB_ANIMATION_DURATION = 250;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE;
    private boolean attachedToWindow;
    private int mMinFlingVelocity;
    private ObjectAnimator mPositionAnimator;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private Drawable mThumbDrawable;
    private int mThumbTextPadding;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private VelocityTracker mVelocityTracker;
    private float thumbPosition;
    private boolean wasLayout;

    public Switch(Context arg3) {
        // Method was not decompiled
    }

    private void animateThumbToCheckedState(boolean arg4) {
        float v4 = arg4 ? 1f : 0f;
        this.mPositionAnimator = ObjectAnimator.ofFloat(this, "thumbPosition", new float[]{v4});
        this.mPositionAnimator.setDuration(250);
        this.mPositionAnimator.start();
    }

    private void cancelPositionAnimator() {
        if(this.mPositionAnimator != null) {
            this.mPositionAnimator.cancel();
        }
    }

    private void cancelSuperTouch(MotionEvent arg2) {
        arg2 = MotionEvent.obtain(arg2);
        arg2.setAction(3);
        super.onTouchEvent(arg2);
        arg2.recycle();
    }

    public void checkColorFilters() {
        int v2_1;
        String v2;
        Drawable v0;
        if(this.mTrackDrawable != null) {
            v0 = this.mTrackDrawable;
            v2 = this.isChecked() ? "switchTrackChecked" : "switchTrack";
            v2_1 = Theme.getColor(v2);
            v0.setColorFilter(new PorterDuffColorFilter(v2_1, PorterDuff$Mode.MULTIPLY));
        }

        if(this.mThumbDrawable != null) {
            v0 = this.mThumbDrawable;
            v2 = this.isChecked() ? "switchThumbChecked" : "switchThumb";
            v2_1 = Theme.getColor(v2);
            v0.setColorFilter(new PorterDuffColorFilter(v2_1, PorterDuff$Mode.MULTIPLY));
        }
    }

    public static float constrain(float arg1, float arg2, float arg3) {
        if(arg1 < arg2) {
            arg1 = arg2;
        }
        else if(arg1 > arg3) {
            arg1 = arg3;
        }

        return arg1;
    }

    public void draw(Canvas arg11) {
        int v6_1;
        int v7;
        Rect v0 = this.mTempRect;
        int v1 = this.mSwitchLeft;
        int v2 = this.mSwitchTop;
        int v3 = this.mSwitchRight;
        int v4 = this.mSwitchBottom;
        int v5 = this.getThumbOffset() + v1;
        Insets v6 = Insets.NONE;
        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.getPadding(v0);
            v5 += v0.left;
            if(v6 != Insets.NONE) {
                if(v6.left > v0.left) {
                    v1 += v6.left - v0.left;
                }

                v7 = v6.top > v0.top ? v6.top - v0.top + v2 : v2;
                if(v6.right > v0.right) {
                    v3 -= v6.right - v0.right;
                }

                if(v6.bottom <= v0.bottom) {
                    goto label_49;
                }

                v6_1 = v4 - (v6.bottom - v0.bottom);
            }
            else {
                v7 = v2;
            label_49:
                v6_1 = v4;
            }

            this.mTrackDrawable.setBounds(v1, v7, v3, v6_1);
        }

        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.getPadding(v0);
            v1 = v5 - v0.left;
            v5 = v5 + this.mThumbWidth + v0.right;
            int v0_1 = AndroidUtilities.density == 1.5f ? AndroidUtilities.dp(1f) : 0;
            this.mThumbDrawable.setBounds(v1, v2 + v0_1, v5, v0_1 + v4);
            Drawable v0_2 = this.getBackground();
            if(v0_2 == null) {
                goto label_79;
            }

            if(Build$VERSION.SDK_INT < 21) {
                goto label_79;
            }

            v0_2.setHotspotBounds(v1, v2, v5, v4);
        }

    label_79:
        super.draw(arg11);
    }

    @SuppressLint(value={"NewApi"}) public void drawableHotspotChanged(float arg2, float arg3) {
        super.drawableHotspotChanged(arg2, arg3);
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.setHotspot(arg2, arg3);
        }

        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.setHotspot(arg2, arg3);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] v0 = this.getDrawableState();
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.setState(v0);
        }

        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.setState(v0);
        }

        this.invalidate();
    }

    public int getCompoundPaddingLeft() {
        if(!LocaleController.isRTL) {
            return super.getCompoundPaddingLeft();
        }

        return super.getCompoundPaddingLeft() + this.mSwitchWidth;
    }

    public int getCompoundPaddingRight() {
        if(LocaleController.isRTL) {
            return super.getCompoundPaddingRight();
        }

        return super.getCompoundPaddingRight() + this.mSwitchWidth;
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    private boolean getTargetCheckedState() {
        boolean v0 = this.thumbPosition > 0.5f ? true : false;
        return v0;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    private int getThumbOffset() {
        float v0 = LocaleController.isRTL ? 1f - this.thumbPosition : this.thumbPosition;
        return ((int)(v0 * (((float)this.getThumbScrollRange())) + 0.5f));
    }

    public float getThumbPosition() {
        return this.thumbPosition;
    }

    private int getThumbScrollRange() {
        if(this.mTrackDrawable != null) {
            Rect v0 = this.mTempRect;
            this.mTrackDrawable.getPadding(v0);
            return this.mSwitchWidth - this.mThumbWidth - v0.left - v0.right - Insets.NONE.left - Insets.NONE.right;
        }

        return 0;
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    private boolean hitThumb(float arg6, float arg7) {
        int v0 = this.getThumbOffset();
        this.mThumbDrawable.getPadding(this.mTempRect);
        int v1 = this.mSwitchTop - this.mTouchSlop;
        int v2 = this.mSwitchLeft + v0 - this.mTouchSlop;
        v0 = this.mThumbWidth + v2 + this.mTempRect.left + this.mTempRect.right + this.mTouchSlop;
        int v3 = this.mSwitchBottom + this.mTouchSlop;
        boolean v6 = arg6 <= (((float)v2)) || arg6 >= (((float)v0)) || arg7 <= (((float)v1)) || arg7 >= (((float)v3)) ? false : true;
        return v6;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.jumpToCurrentState();
        }

        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.jumpToCurrentState();
        }

        if(this.mPositionAnimator != null && (this.mPositionAnimator.isRunning())) {
            this.mPositionAnimator.end();
            this.mPositionAnimator = null;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        this.requestLayout();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        this.wasLayout = false;
    }

    protected void onDraw(Canvas arg7) {
        super.onDraw(arg7);
        Rect v0 = this.mTempRect;
        Drawable v1 = this.mTrackDrawable;
        if(v1 != null) {
            v1.getPadding(v0);
        }
        else {
            v0.setEmpty();
        }

        Drawable v2 = this.mThumbDrawable;
        if(v1 != null) {
            if((this.mSplitTrack) && v2 != null) {
                Insets v3 = Insets.NONE;
                v2.copyBounds(v0);
                v0.left += v3.left;
                v0.right -= v3.right;
                int v3_1 = arg7.save();
                arg7.clipRect(v0, Region$Op.DIFFERENCE);
                v1.draw(arg7);
                arg7.restoreToCount(v3_1);
                goto label_31;
            }

            v1.draw(arg7);
        }

    label_31:
        int v0_1 = arg7.save();
        if(v2 != null) {
            v2.draw(arg7);
        }

        arg7.restoreToCount(v0_1);
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        int v1_1;
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.wasLayout = true;
        arg2 = 0;
        if(this.mThumbDrawable != null) {
            Rect v1 = this.mTempRect;
            if(this.mTrackDrawable != null) {
                this.mTrackDrawable.getPadding(v1);
            }
            else {
                v1.setEmpty();
            }

            Insets v3 = Insets.NONE;
            arg4 = Math.max(0, v3.left - v1.left);
            v1_1 = Math.max(0, v3.right - v1.right);
            arg2 = arg4;
        }
        else {
            v1_1 = 0;
        }

        if(LocaleController.isRTL) {
            arg3 = this.getPaddingLeft() + arg2;
            arg4 = this.mSwitchWidth + arg3 - arg2 - v1_1;
        }
        else {
            arg4 = this.getWidth() - this.getPaddingRight() - v1_1;
            arg3 = arg4 - this.mSwitchWidth + arg2 + v1_1;
        }

        v1_1 = this.getGravity() & 112;
        if(v1_1 == 16) {
            v1_1 = (this.getPaddingTop() + this.getHeight() - this.getPaddingBottom()) / 2 - this.mSwitchHeight / 2;
        label_50:
            arg2 = this.mSwitchHeight + v1_1;
        }
        else if(v1_1 != 80) {
            v1_1 = this.getPaddingTop();
            goto label_50;
        }
        else {
            arg2 = this.getHeight() - this.getPaddingBottom();
            v1_1 = arg2 - this.mSwitchHeight;
        }

        this.mSwitchLeft = arg3;
        this.mSwitchTop = v1_1;
        this.mSwitchBottom = arg2;
        this.mSwitchRight = arg4;
    }

    public void onMeasure(int arg7, int arg8) {
        int v3;
        int v1;
        Rect v0 = this.mTempRect;
        int v2 = 0;
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.getPadding(v0);
            v1 = this.mThumbDrawable.getIntrinsicWidth() - v0.left - v0.right;
            v3 = this.mThumbDrawable.getIntrinsicHeight();
        }
        else {
            v1 = 0;
            v3 = 0;
        }

        this.mThumbWidth = v1;
        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.getPadding(v0);
            v2 = this.mTrackDrawable.getIntrinsicHeight();
        }
        else {
            v0.setEmpty();
        }

        v1 = v0.left;
        int v0_1 = v0.right;
        if(this.mThumbDrawable != null) {
            Insets v4 = Insets.NONE;
            v1 = Math.max(v1, v4.left);
            v0_1 = Math.max(v0_1, v4.right);
        }

        v0_1 = Math.max(this.mSwitchMinWidth, this.mThumbWidth * 2 + v1 + v0_1);
        v1 = Math.max(v2, v3);
        this.mSwitchWidth = v0_1;
        this.mSwitchHeight = v1;
        super.onMeasure(arg7, arg8);
        if(this.getMeasuredHeight() < v1) {
            this.setMeasuredDimension(v0_1, v1);
        }
    }

    public boolean onTouchEvent(MotionEvent arg7) {
        float v3;
        float v1_1;
        float v0;
        this.mVelocityTracker.addMovement(arg7);
        int v1 = 2;
        switch(arg7.getActionMasked()) {
            case 0: {
                v0 = arg7.getX();
                v1_1 = arg7.getY();
                if(!this.isEnabled()) {
                    goto label_75;
                }

                if(!this.hitThumb(v0, v1_1)) {
                    goto label_75;
                }

                this.mTouchMode = 1;
                this.mTouchX = v0;
                this.mTouchY = v1_1;
                break;
            }
            case 2: {
                switch(this.mTouchMode) {
                    case 1: {
                        v0 = arg7.getX();
                        v3 = arg7.getY();
                        if(Math.abs(v0 - this.mTouchX) <= (((float)this.mTouchSlop)) && Math.abs(v3 - this.mTouchY) <= (((float)this.mTouchSlop))) {
                            goto label_75;
                        }

                        this.mTouchMode = v1;
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        this.mTouchX = v0;
                        this.mTouchY = v3;
                        return 1;
                    }
                    case 2: {
                        float v7 = arg7.getX();
                        int v0_1 = this.getThumbScrollRange();
                        v1_1 = v7 - this.mTouchX;
                        v3 = 1f;
                        if(v0_1 != 0) {
                            v1_1 /= ((float)v0_1);
                        }
                        else if(v1_1 > 0f) {
                            v1_1 = 1f;
                        }
                        else {
                            v1_1 = -1f;
                        }

                        if(LocaleController.isRTL) {
                            v1_1 = -v1_1;
                        }

                        v0 = Switch.constrain(this.thumbPosition + v1_1, 0f, v3);
                        if(v0 != this.thumbPosition) {
                            this.mTouchX = v7;
                            this.setThumbPosition(v0);
                        }

                        return 1;
                    label_56:
                        if(this.mTouchMode == v1) {
                            this.stopDrag(arg7);
                            super.onTouchEvent(arg7);
                            return 1;
                        }

                        this.mTouchMode = 0;
                        this.mVelocityTracker.clear();
                        goto label_75;
                    }
                    default: {
                        goto label_75;
                    }
                }
            }
            case 1: 
            case 3: {
                goto label_56;
            }
            default: {
                break;
            }
        }

    label_75:
        return super.onTouchEvent(arg7);
    }

    public void resetLayout() {
        this.wasLayout = false;
    }

    public void setChecked(boolean arg5) {
        Drawable v0_1;
        super.setChecked(arg5);
        arg5 = this.isChecked();
        if(!this.attachedToWindow || !this.wasLayout) {
            this.cancelPositionAnimator();
            float v0 = arg5 ? 1f : 0f;
            this.setThumbPosition(v0);
        }
        else {
            this.animateThumbToCheckedState(arg5);
        }

        if(this.mTrackDrawable != null) {
            v0_1 = this.mTrackDrawable;
            String v2 = arg5 ? "switchTrackChecked" : "switchTrack";
            int v2_1 = Theme.getColor(v2);
            v0_1.setColorFilter(new PorterDuffColorFilter(v2_1, PorterDuff$Mode.MULTIPLY));
        }

        if(this.mThumbDrawable != null) {
            v0_1 = this.mThumbDrawable;
            String v5 = arg5 ? "switchThumbChecked" : "switchThumb";
            int v5_1 = Theme.getColor(v5);
            v0_1.setColorFilter(new PorterDuffColorFilter(v5_1, PorterDuff$Mode.MULTIPLY));
        }
    }

    public void setColor(int arg6) {
        boolean v0 = this.isChecked();
        int v1 = ApplicationLoader.applicationContext.getSharedPreferences("theme", 0).getInt("themeColor", c.b);
        int v2 = c.a("themeColor", c.b, 0.5f);
        int v3 = c.a(arg6, 127);
        if(arg6 == v1) {
        }
        else {
            v2 = v3;
        }

        if(this.mTrackDrawable != null) {
            Drawable v1_1 = this.mTrackDrawable;
            if(v0) {
            }
            else {
                arg6 = -3684409;
            }

            v1_1.setColorFilter(new PorterDuffColorFilter(arg6, PorterDuff$Mode.MULTIPLY));
        }

        if(this.mThumbDrawable != null) {
            Drawable v6 = this.mThumbDrawable;
            if(v0) {
            }
            else {
                v2 = -1184275;
            }

            v6.setColorFilter(new PorterDuffColorFilter(v2, PorterDuff$Mode.MULTIPLY));
        }
    }

    public void setSplitTrack(boolean arg1) {
        this.mSplitTrack = arg1;
        this.invalidate();
    }

    public void setSwitchMinWidth(int arg1) {
        this.mSwitchMinWidth = arg1;
        this.requestLayout();
    }

    public void setSwitchPadding(int arg1) {
        this.mSwitchPadding = arg1;
        this.requestLayout();
    }

    public void setThumbDrawable(Drawable arg3) {
        if(this.mThumbDrawable != null) {
            this.mThumbDrawable.setCallback(null);
        }

        this.mThumbDrawable = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
        }

        this.requestLayout();
    }

    @Keep private void setThumbPosition(float arg1) {
        this.thumbPosition = arg1;
        this.invalidate();
    }

    public void setThumbTextPadding(int arg1) {
        this.mThumbTextPadding = arg1;
        this.requestLayout();
    }

    public void setTrackDrawable(Drawable arg3) {
        if(this.mTrackDrawable != null) {
            this.mTrackDrawable.setCallback(null);
        }

        this.mTrackDrawable = arg3;
        if(arg3 != null) {
            arg3.setCallback(((Drawable$Callback)this));
        }

        this.requestLayout();
    }

    private void stopDrag(MotionEvent arg6) {
        boolean v0 = false;
        this.mTouchMode = 0;
        int v1 = arg6.getAction() != 1 || !this.isEnabled() ? 0 : 1;
        if(v1 != 0) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float v1_1 = this.mVelocityTracker.getXVelocity();
            if(Math.abs(v1_1) > (((float)this.mMinFlingVelocity))) {
                if(LocaleController.isRTL) {
                    if(v1_1 >= 0f) {
                        goto label_31;
                    }
                }
                else if(v1_1 > 0f) {
                }
                else {
                    goto label_31;
                }

                v0 = true;
            }
            else {
                v0 = this.getTargetCheckedState();
            }
        }
        else {
            v0 = this.isChecked();
        }

    label_31:
        this.setChecked(v0);
        this.cancelSuperTouch(arg6);
    }

    public void toggle() {
        this.setChecked(this.isChecked() ^ 1);
    }

    protected boolean verifyDrawable(Drawable arg2) {
        boolean v2 = (super.verifyDrawable(arg2)) || arg2 == this.mThumbDrawable || arg2 == this.mTrackDrawable ? true : false;
        return v2;
    }
}

