package org.telegram.ui.Components.Crop;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import org.telegram.messenger.AndroidUtilities;

public class CropGestureDetector {
    public interface CropGestureListener {
        void onDrag(float arg1, float arg2);

        void onFling(float arg1, float arg2, float arg3, float arg4);

        void onScale(float arg1, float arg2, float arg3);
    }

    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId;
    private int mActivePointerIndex;
    private ScaleGestureDetector mDetector;
    private boolean mIsDragging;
    float mLastTouchX;
    float mLastTouchY;
    private CropGestureListener mListener;
    final float mMinimumVelocity;
    final float mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private boolean started;

    public CropGestureDetector(Context arg3) {
        super();
        this.mMinimumVelocity = ((float)ViewConfiguration.get(arg3).getScaledMinimumFlingVelocity());
        this.mTouchSlop = ((float)AndroidUtilities.dp(1f));
        this.mActivePointerId = -1;
        this.mActivePointerIndex = 0;
        this.mDetector = new ScaleGestureDetector(arg3, new ScaleGestureDetector$OnScaleGestureListener() {
            public boolean onScale(ScaleGestureDetector arg4) {
                float v0 = arg4.getScaleFactor();
                if(!Float.isNaN(v0)) {
                    if(Float.isInfinite(v0)) {
                    }
                    else {
                        CropGestureDetector.access$000(CropGestureDetector.this).onScale(v0, arg4.getFocusX(), arg4.getFocusY());
                        return 1;
                    }
                }

                return 0;
            }

            public boolean onScaleBegin(ScaleGestureDetector arg1) {
                return 1;
            }

            public void onScaleEnd(ScaleGestureDetector arg1) {
            }
        });
    }

    static CropGestureListener access$000(CropGestureDetector arg0) {
        return arg0.mListener;
    }

    float getActiveX(MotionEvent arg2) {
        try {
            return arg2.getX(this.mActivePointerIndex);
        }
        catch(Exception ) {
            return arg2.getX();
        }
    }

    float getActiveY(MotionEvent arg2) {
        try {
            return arg2.getY(this.mActivePointerIndex);
        }
        catch(Exception ) {
            return arg2.getY();
        }
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public boolean isScaling() {
        return this.mDetector.isInProgress();
    }

    public boolean onTouchEvent(MotionEvent arg9) {
        float v0_1;
        this.mDetector.onTouchEvent(arg9);
        int v0 = arg9.getAction() & 255;
        int v2 = -1;
        boolean v4 = false;
        if(v0 == 3) {
        label_34:
            this.mActivePointerId = v2;
        }
        else if(v0 != 6) {
            switch(v0) {
                case 0: {
                    goto label_13;
                }
                case 1: {
                    goto label_34;
                }
            }

            goto label_35;
        label_13:
            this.mActivePointerId = arg9.getPointerId(0);
        }
        else {
            v0 = (65280 & arg9.getAction()) >> 8;
            if(arg9.getPointerId(v0) == this.mActivePointerId) {
                v0 = v0 == 0 ? 1 : 0;
                this.mActivePointerId = arg9.getPointerId(v0);
                this.mLastTouchX = arg9.getX(v0);
                this.mLastTouchY = arg9.getY(v0);
            }
        }

    label_35:
        v0 = this.mActivePointerId != v2 ? this.mActivePointerId : 0;
        this.mActivePointerIndex = arg9.findPointerIndex(v0);
        VelocityTracker v1 = null;
        switch(arg9.getAction()) {
            case 1: {
                if(this.mIsDragging) {
                    if(this.mVelocityTracker != null) {
                        this.mLastTouchX = this.getActiveX(arg9);
                        this.mLastTouchY = this.getActiveY(arg9);
                        this.mVelocityTracker.addMovement(arg9);
                        this.mVelocityTracker.computeCurrentVelocity(1000);
                        float v9 = this.mVelocityTracker.getXVelocity();
                        v0_1 = this.mVelocityTracker.getYVelocity();
                        if(Math.max(Math.abs(v9), Math.abs(v0_1)) >= this.mMinimumVelocity) {
                            this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -v9, -v0_1);
                        }
                    }

                    this.mIsDragging = false;
                }

                if(this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = v1;
                }

                this.started = false;
                break;
            }
            case 0: 
            case 2: {
                if(!this.started) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                    if(this.mVelocityTracker != null) {
                        this.mVelocityTracker.addMovement(arg9);
                    }

                    this.mLastTouchX = this.getActiveX(arg9);
                    this.mLastTouchY = this.getActiveY(arg9);
                    this.mIsDragging = false;
                    this.started = true;
                    return 1;
                }

                v0_1 = this.getActiveX(arg9);
                float v1_1 = this.getActiveY(arg9);
                float v2_1 = v0_1 - this.mLastTouchX;
                float v5 = v1_1 - this.mLastTouchY;
                if(!this.mIsDragging) {
                    if((((float)Math.sqrt(((double)(v2_1 * v2_1 + v5 * v5))))) >= this.mTouchSlop) {
                        v4 = true;
                    }

                    this.mIsDragging = v4;
                }

                if(!this.mIsDragging) {
                    return 1;
                }

                this.mListener.onDrag(v2_1, v5);
                this.mLastTouchX = v0_1;
                this.mLastTouchY = v1_1;
                if(this.mVelocityTracker == null) {
                    return 1;
                }

                this.mVelocityTracker.addMovement(arg9);
                break;
            }
            case 3: {
                if(this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = v1;
                }

                this.started = false;
                this.mIsDragging = false;
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }

    public void setOnGestureListener(CropGestureListener arg1) {
        this.mListener = arg1;
    }
}

