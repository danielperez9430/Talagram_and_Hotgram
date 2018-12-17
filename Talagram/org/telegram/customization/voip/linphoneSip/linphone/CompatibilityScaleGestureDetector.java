package org.telegram.customization.voip.linphoneSip.linphone;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector$OnScaleGestureListener;
import android.view.ScaleGestureDetector$SimpleOnScaleGestureListener;
import android.view.ScaleGestureDetector;

@TargetApi(value=8) public class CompatibilityScaleGestureDetector extends ScaleGestureDetector$SimpleOnScaleGestureListener {
    private ScaleGestureDetector detector;
    private CompatibilityScaleGestureListener listener;

    public CompatibilityScaleGestureDetector(Context arg2) {
        super();
        this.detector = new ScaleGestureDetector(arg2, ((ScaleGestureDetector$OnScaleGestureListener)this));
    }

    public void destroy() {
        this.listener = null;
        this.detector = null;
    }

    public float getScaleFactor() {
        return this.detector.getScaleFactor();
    }

    public boolean onScale(ScaleGestureDetector arg1) {
        if(this.listener == null) {
            return 0;
        }

        return this.listener.onScale(this);
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        return this.detector.onTouchEvent(arg2);
    }

    public void setOnScaleListener(CompatibilityScaleGestureListener arg1) {
        this.listener = arg1;
    }
}

