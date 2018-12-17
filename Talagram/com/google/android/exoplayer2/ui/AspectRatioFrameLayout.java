package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AspectRatioFrameLayout extends FrameLayout {
    class com.google.android.exoplayer2.ui.AspectRatioFrameLayout$1 {
    }

    public interface AspectRatioListener {
        void onAspectRatioUpdated(float arg1, float arg2, boolean arg3);
    }

    final class AspectRatioUpdateDispatcher implements Runnable {
        private boolean aspectRatioMismatch;
        private boolean isScheduled;
        private float naturalAspectRatio;
        private float targetAspectRatio;

        AspectRatioUpdateDispatcher(AspectRatioFrameLayout arg1, com.google.android.exoplayer2.ui.AspectRatioFrameLayout$1 arg2) {
            this(arg1);
        }

        private AspectRatioUpdateDispatcher(AspectRatioFrameLayout arg1) {
            AspectRatioFrameLayout.this = arg1;
            super();
        }

        public void run() {
            this.isScheduled = false;
            if(AspectRatioFrameLayout.this.aspectRatioListener == null) {
                return;
            }

            AspectRatioFrameLayout.this.aspectRatioListener.onAspectRatioUpdated(this.targetAspectRatio, this.naturalAspectRatio, this.aspectRatioMismatch);
        }

        public void scheduleUpdate(float arg1, float arg2, boolean arg3) {
            this.targetAspectRatio = arg1;
            this.naturalAspectRatio = arg2;
            this.aspectRatioMismatch = arg3;
            if(!this.isScheduled) {
                this.isScheduled = true;
                AspectRatioFrameLayout.this.post(((Runnable)this));
            }
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface ResizeMode {
    }

    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;
    public static final int RESIZE_MODE_FILL = 3;
    public static final int RESIZE_MODE_FIT = 0;
    public static final int RESIZE_MODE_FIXED_HEIGHT = 2;
    public static final int RESIZE_MODE_FIXED_WIDTH = 1;
    public static final int RESIZE_MODE_ZOOM = 4;
    private AspectRatioListener aspectRatioListener;
    private final AspectRatioUpdateDispatcher aspectRatioUpdateDispatcher;
    private boolean drawingReady;
    private Matrix matrix;
    private int resizeMode;
    private int rotation;
    private float videoAspectRatio;

    public AspectRatioFrameLayout(Context arg2) {
        super(arg2);
        this.matrix = new Matrix();
        this.resizeMode = 0;
        this.aspectRatioUpdateDispatcher = new AspectRatioUpdateDispatcher(this, null);
    }

    static AspectRatioListener access$100(AspectRatioFrameLayout arg0) {
        return arg0.aspectRatioListener;
    }

    public float getAspectRatio() {
        return this.videoAspectRatio;
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public int getVideoRotation() {
        return this.rotation;
    }

    public boolean isDrawingReady() {
        return this.drawingReady;
    }

    protected void onMeasure(int arg10, int arg11) {
        // Method was not decompiled
    }

    public void setAspectRatio(float arg2, int arg3) {
        if(this.videoAspectRatio != arg2) {
            this.videoAspectRatio = arg2;
            this.rotation = arg3;
            this.requestLayout();
        }
    }

    public void setAspectRatioListener(AspectRatioListener arg1) {
        this.aspectRatioListener = arg1;
    }

    public void setDrawingReady(boolean arg2) {
        if(this.drawingReady == arg2) {
            return;
        }

        this.drawingReady = arg2;
    }

    public void setResizeMode(int arg2) {
        if(this.resizeMode != arg2) {
            this.resizeMode = arg2;
            this.requestLayout();
        }
    }
}

