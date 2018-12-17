package org.telegram.ui.Components;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class Scroller {
    private static float DECELERATION_RATE = 0f;
    private static final int DEFAULT_DURATION = 250;
    private static float END_TENSION = 0f;
    private static final int FLING_MODE = 1;
    private static final int NB_SAMPLES = 100;
    private static final int SCROLL_MODE;
    private static final float[] SPLINE;
    private static float START_TENSION;
    private int mCurrX;
    private int mCurrY;
    private float mDeceleration;
    private float mDeltaX;
    private float mDeltaY;
    private int mDuration;
    private float mDurationReciprocal;
    private int mFinalX;
    private int mFinalY;
    private boolean mFinished;
    private boolean mFlywheel;
    private Interpolator mInterpolator;
    private int mMaxX;
    private int mMaxY;
    private int mMinX;
    private int mMinY;
    private int mMode;
    private final float mPpi;
    private long mStartTime;
    private int mStartX;
    private int mStartY;
    private float mVelocity;
    private static float sViscousFluidNormalize;
    private static float sViscousFluidScale;

    static {
        float v8;
        float v6;
        int v3;
        Scroller.DECELERATION_RATE = ((float)(Math.log(0.75) / Math.log(0.9)));
        Scroller.START_TENSION = 0.4f;
        float v1 = 1f;
        Scroller.END_TENSION = v1 - Scroller.START_TENSION;
        Scroller.SPLINE = new float[101];
        float v0 = 0f;
        int v2;
        for(v2 = 0; true; ++v2) {
            v3 = 100;
            if(v2 > v3) {
                break;
            }

            float v4 = (((float)v2)) / 100f;
            float v3_1 = 1f;
            while(true) {
                float v5 = (v3_1 - v0) / 2f + v0;
                float v7 = v1 - v5;
                v6 = 3f * v5 * v7;
                v8 = v5 * v5 * v5;
                v7 = (v7 * Scroller.START_TENSION + Scroller.END_TENSION * v5) * v6 + v8;
                if((((double)Math.abs(v7 - v4))) < 0.00001) {
                    break;
                }

                if(v7 > v4) {
                    v3_1 = v5;
                    continue;
                }

                v0 = v5;
            }

            Scroller.SPLINE[v2] = v6 + v8;
        }

        Scroller.SPLINE[v3] = v1;
        Scroller.sViscousFluidScale = 8f;
        Scroller.sViscousFluidNormalize = v1;
        Scroller.sViscousFluidNormalize = v1 / Scroller.viscousFluid(v1);
    }

    public Scroller(Context arg2, Interpolator arg3, boolean arg4) {
        super();
        this.mFinished = true;
        this.mInterpolator = arg3;
        this.mPpi = arg2.getResources().getDisplayMetrics().density * 160f;
        this.mDeceleration = this.computeDeceleration(ViewConfiguration.getScrollFriction());
        this.mFlywheel = arg4;
    }

    public Scroller(Context arg2, Interpolator arg3) {
        this(arg2, arg3, true);
    }

    public Scroller(Context arg2) {
        this(arg2, null);
    }

    public void abortAnimation() {
        this.mCurrX = this.mFinalX;
        this.mCurrY = this.mFinalY;
        this.mFinished = true;
    }

    private float computeDeceleration(float arg3) {
        return this.mPpi * 386.087799f * arg3;
    }

    public boolean computeScrollOffset() {
        if(this.mFinished) {
            return 0;
        }

        int v0 = ((int)(AnimationUtils.currentAnimationTimeMillis() - this.mStartTime));
        if(v0 < this.mDuration) {
            switch(this.mMode) {
                case 0: {
                    goto label_77;
                }
                case 1: {
                    goto label_14;
                }
            }

            return 1;
        label_77:
            float v0_1 = (((float)v0)) * this.mDurationReciprocal;
            v0_1 = this.mInterpolator == null ? Scroller.viscousFluid(v0_1) : this.mInterpolator.getInterpolation(v0_1);
            this.mCurrX = this.mStartX + Math.round(this.mDeltaX * v0_1);
            this.mCurrY = this.mStartY + Math.round(v0_1 * this.mDeltaY);
            return 1;
        label_14:
            v0_1 = (((float)v0)) / (((float)this.mDuration));
            int v3 = ((int)(v0_1 * 100f));
            float v4 = (((float)v3)) / 100f;
            int v5 = v3 + 1;
            float v1 = Scroller.SPLINE[v3] + (v0_1 - v4) / ((((float)v5)) / 100f - v4) * (Scroller.SPLINE[v5] - Scroller.SPLINE[v3]);
            this.mCurrX = this.mStartX + Math.round((((float)(this.mFinalX - this.mStartX))) * v1);
            this.mCurrX = Math.min(this.mCurrX, this.mMaxX);
            this.mCurrX = Math.max(this.mCurrX, this.mMinX);
            this.mCurrY = this.mStartY + Math.round(v1 * (((float)(this.mFinalY - this.mStartY))));
            this.mCurrY = Math.min(this.mCurrY, this.mMaxY);
            this.mCurrY = Math.max(this.mCurrY, this.mMinY);
            if(this.mCurrX != this.mFinalX) {
                return 1;
            }

            if(this.mCurrY != this.mFinalY) {
                return 1;
            }

            goto label_103;
        }
        else {
            this.mCurrX = this.mFinalX;
            this.mCurrY = this.mFinalY;
        label_103:
            this.mFinished = true;
        }

        return 1;
    }

    public void extendDuration(int arg2) {
        this.mDuration = this.timePassed() + arg2;
        this.mDurationReciprocal = 1f / (((float)this.mDuration));
        this.mFinished = false;
    }

    public void fling(int arg17, int arg18, int arg19, int arg20, int arg21, int arg22, int arg23, int arg24) {
        // Method was not decompiled
    }

    public final void forceFinished(boolean arg1) {
        this.mFinished = arg1;
    }

    public float getCurrVelocity() {
        return this.mVelocity - this.mDeceleration * (((float)this.timePassed())) / 2000f;
    }

    public final int getCurrX() {
        return this.mCurrX;
    }

    public final int getCurrY() {
        return this.mCurrY;
    }

    public final int getDuration() {
        return this.mDuration;
    }

    public final int getFinalX() {
        return this.mFinalX;
    }

    public final int getFinalY() {
        return this.mFinalY;
    }

    public final int getStartX() {
        return this.mStartX;
    }

    public final int getStartY() {
        return this.mStartY;
    }

    public final boolean isFinished() {
        return this.mFinished;
    }

    public boolean isScrollingInDirection(float arg3, float arg4) {
        boolean v3 = (this.mFinished) || Math.signum(arg3) != Math.signum(((float)(this.mFinalX - this.mStartX))) || Math.signum(arg4) != Math.signum(((float)(this.mFinalY - this.mStartY))) ? false : true;
        return v3;
    }

    public void setFinalX(int arg2) {
        this.mFinalX = arg2;
        this.mDeltaX = ((float)(this.mFinalX - this.mStartX));
        this.mFinished = false;
    }

    public void setFinalY(int arg2) {
        this.mFinalY = arg2;
        this.mDeltaY = ((float)(this.mFinalY - this.mStartY));
        this.mFinished = false;
    }

    public final void setFriction(float arg1) {
        this.mDeceleration = this.computeDeceleration(arg1);
    }

    public void startScroll(int arg3, int arg4, int arg5, int arg6, int arg7) {
        this.mMode = 0;
        this.mFinished = false;
        this.mDuration = arg7;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStartX = arg3;
        this.mStartY = arg4;
        this.mFinalX = arg3 + arg5;
        this.mFinalY = arg4 + arg6;
        this.mDeltaX = ((float)arg5);
        this.mDeltaY = ((float)arg6);
        this.mDurationReciprocal = 1f / (((float)this.mDuration));
    }

    public void startScroll(int arg7, int arg8, int arg9, int arg10) {
        this.startScroll(arg7, arg8, arg9, arg10, 250);
    }

    public int timePassed() {
        return ((int)(AnimationUtils.currentAnimationTimeMillis() - this.mStartTime));
    }

    static float viscousFluid(float arg4) {
        arg4 *= Scroller.sViscousFluidScale;
        float v0 = 1f;
        if(arg4 < v0) {
            arg4 -= v0 - (((float)Math.exp(((double)(-arg4)))));
        }
        else {
            arg4 = (v0 - (((float)Math.exp(((double)(v0 - arg4)))))) * 0.632121f + 0.367879f;
        }

        return arg4 * Scroller.sViscousFluidNormalize;
    }
}

