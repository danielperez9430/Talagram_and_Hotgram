package org.telegram.ui.Components.Paint.Views;

import android.view.MotionEvent;

public class RotationGestureDetector {
    public interface OnRotationGestureListener {
        void onRotation(RotationGestureDetector arg1);

        void onRotationBegin(RotationGestureDetector arg1);

        void onRotationEnd(RotationGestureDetector arg1);
    }

    private float angle;
    private float fX;
    private float fY;
    private OnRotationGestureListener mListener;
    private float sX;
    private float sY;
    private float startAngle;

    public RotationGestureDetector(OnRotationGestureListener arg1) {
        super();
        this.mListener = arg1;
    }

    private float angleBetweenLines(float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10) {
        arg3 = ((float)Math.atan2(((double)(arg4 - arg6)), ((double)(arg3 - arg5))));
        arg4 = 360f;
        arg3 = (((float)Math.toDegrees(((double)(arg3 - (((float)Math.atan2(((double)(arg8 - arg10)), ((double)(arg7 - arg9)))))))))) % arg4;
        if(arg3 < -180f) {
            arg3 += arg4;
        }

        if(arg3 > 180f) {
            arg3 -= arg4;
        }

        return arg3;
    }

    public float getAngle() {
        return this.angle;
    }

    public float getStartAngle() {
        return this.startAngle;
    }

    public boolean onTouchEvent(MotionEvent arg14) {
        if(arg14.getPointerCount() != 2) {
            return 0;
        }

        float v2 = NaNf;
        switch(arg14.getActionMasked()) {
            case 2: {
                this.angle = this.angleBetweenLines(this.fX, this.fY, this.sX, this.sY, arg14.getX(1), arg14.getY(1), arg14.getX(0), arg14.getY(0));
                if(this.mListener == null) {
                    return 1;
                }

                if(Float.isNaN(this.startAngle)) {
                    this.startAngle = this.angle;
                    this.mListener.onRotationBegin(this);
                    return 1;
                }

                this.mListener.onRotation(this);
                break;
            }
            case 1: 
            case 3: {
                this.startAngle = v2;
                break;
            }
            case 0: 
            case 5: {
                this.sX = arg14.getX(0);
                this.sY = arg14.getY(0);
                this.fX = arg14.getX(1);
                this.fY = arg14.getY(1);
                break;
            }
            case 6: {
                this.startAngle = v2;
                if(this.mListener == null) {
                    return 1;
                }

                this.mListener.onRotationEnd(this);
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }
}

