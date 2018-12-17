package org.telegram.messenger.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.AndroidUtilities;

@SuppressLint(value={"NewApi"}) public class CameraView extends FrameLayout implements TextureView$SurfaceTextureListener {
    public interface CameraViewDelegate {
        void onCameraCreated(Camera arg1);

        void onCameraInit();
    }

    private CameraSession cameraSession;
    private int clipLeft;
    private int clipTop;
    private int cx;
    private int cy;
    private CameraViewDelegate delegate;
    private int focusAreaSize;
    private float focusProgress;
    private boolean initialFrontface;
    private boolean initied;
    private float innerAlpha;
    private Paint innerPaint;
    private DecelerateInterpolator interpolator;
    private boolean isFrontface;
    private long lastDrawTime;
    private Matrix matrix;
    private boolean mirror;
    private float outerAlpha;
    private Paint outerPaint;
    private Size previewSize;
    private TextureView textureView;
    private Matrix txform;

    public CameraView(Context arg3, boolean arg4) {
        super(arg3, null);
        this.txform = new Matrix();
        this.matrix = new Matrix();
        this.focusProgress = 1f;
        this.outerPaint = new Paint(1);
        this.innerPaint = new Paint(1);
        this.interpolator = new DecelerateInterpolator();
        this.isFrontface = arg4;
        this.initialFrontface = arg4;
        this.textureView = new TextureView(arg3);
        this.textureView.setSurfaceTextureListener(((TextureView$SurfaceTextureListener)this));
        this.addView(this.textureView);
        this.focusAreaSize = AndroidUtilities.dp(96f);
        this.outerPaint.setColor(-1);
        this.outerPaint.setStyle(Paint$Style.STROKE);
        this.outerPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.innerPaint.setColor(2147483647);
    }

    static CameraSession access$000(CameraView arg0) {
        return arg0.cameraSession;
    }

    static void access$100(CameraView arg0) {
        arg0.checkPreviewMatrix();
    }

    static CameraViewDelegate access$200(CameraView arg0) {
        return arg0.delegate;
    }

    private void adjustAspectRatio(int arg9, int arg10, int arg11) {
        float v10;
        Matrix v9;
        float v7;
        float v6;
        float v5;
        this.txform.reset();
        int v0 = this.getWidth();
        int v1 = this.getHeight();
        float v2 = ((float)(v0 / 2));
        float v3 = ((float)(v1 / 2));
        int v4 = 2;
        if(arg11 == 0 || arg11 == v4) {
            v5 = (((float)(this.clipTop + v1))) / (((float)arg9));
            v6 = ((float)(this.clipLeft + v0));
            v7 = ((float)arg10);
        }
        else {
            v5 = (((float)(this.clipTop + v1))) / (((float)arg10));
            v6 = ((float)(this.clipLeft + v0));
            v7 = ((float)arg9);
        }

        v5 = Math.max(v5, v6 / v7);
        float v0_1 = ((float)v0);
        float v1_1 = ((float)v1);
        this.txform.postScale((((float)arg10)) * v5 / v0_1, (((float)arg9)) * v5 / v1_1, v2, v3);
        if(1 == arg11 || 3 == arg11) {
            v9 = this.txform;
            v10 = ((float)((arg11 - v4) * 90));
        label_56:
            v9.postRotate(v10, v2, v3);
        }
        else if(v4 == arg11) {
            v9 = this.txform;
            v10 = 180f;
            goto label_56;
        }

        if(this.mirror) {
            this.txform.postScale(-1f, 1f, v2, v3);
        }

        if(this.clipTop != 0 || this.clipLeft != 0) {
            this.txform.postTranslate(((float)(-this.clipLeft / v4)), ((float)(-this.clipTop / v4)));
        }

        this.textureView.setTransform(this.txform);
        v9 = new Matrix();
        v9.postRotate(((float)this.cameraSession.getDisplayOrientation()));
        v9.postScale(v0_1 / 2000f, v1_1 / 2000f);
        v9.postTranslate(v0_1 / 2f, v1_1 / 2f);
        v9.invert(this.matrix);
    }

    private Rect calculateTapArea(float arg4, float arg5, float arg6) {
        int v6 = Float.valueOf((((float)this.focusAreaSize)) * arg6).intValue();
        int v0 = v6 / 2;
        int v4 = this.clamp((((int)arg4)) - v0, 0, this.getWidth() - v6);
        int v5 = this.clamp((((int)arg5)) - v0, 0, this.getHeight() - v6);
        RectF v0_1 = new RectF(((float)v4), ((float)v5), ((float)(v4 + v6)), ((float)(v5 + v6)));
        this.matrix.mapRect(v0_1);
        return new Rect(Math.round(v0_1.left), Math.round(v0_1.top), Math.round(v0_1.right), Math.round(v0_1.bottom));
    }

    private void checkPreviewMatrix() {
        if(this.previewSize == null) {
            return;
        }

        this.adjustAspectRatio(this.previewSize.getWidth(), this.previewSize.getHeight(), this.getContext().getWindowManager().getDefaultDisplay().getRotation());
    }

    private int clamp(int arg1, int arg2, int arg3) {
        if(arg1 > arg3) {
            return arg3;
        }

        if(arg1 < arg2) {
            return arg2;
        }

        return arg1;
    }

    public void destroy(boolean arg4, Runnable arg5) {
        if(this.cameraSession != null) {
            this.cameraSession.destroy();
            CameraController v0 = CameraController.getInstance();
            CameraSession v1 = this.cameraSession;
            CountDownLatch v4 = !arg4 ? new CountDownLatch(1) : null;
            v0.close(v1, v4, arg5);
        }
    }

    protected boolean drawChild(Canvas arg9, View arg10, long arg11) {
        // Method was not decompiled
    }

    public void focusToPoint(int arg6, int arg7) {
        float v0 = ((float)arg6);
        float v1 = ((float)arg7);
        float v2 = 1f;
        Rect v3 = this.calculateTapArea(v0, v1, v2);
        Rect v0_1 = this.calculateTapArea(v0, v1, 1.5f);
        if(this.cameraSession != null) {
            this.cameraSession.focusToRect(v3, v0_1);
        }

        this.focusProgress = 0f;
        this.innerAlpha = v2;
        this.outerAlpha = v2;
        this.cx = arg6;
        this.cy = arg7;
        this.lastDrawTime = System.currentTimeMillis();
        this.invalidate();
    }

    public CameraSession getCameraSession() {
        return this.cameraSession;
    }

    public Size getPreviewSize() {
        return this.previewSize;
    }

    public boolean hasFrontFaceCamera() {
        ArrayList v0 = CameraController.getInstance().getCameras();
        int v2;
        for(v2 = 0; v2 < v0.size(); ++v2) {
            if(v0.get(v2).frontCamera != 0) {
                return 1;
            }
        }

        return 0;
    }

    private void initCamera() {
        Size v0_2;
        int v11;
        int v10;
        Size v3;
        Object v2;
        ArrayList v0 = CameraController.getInstance().getCameras();
        if(v0 == null) {
            return;
        }

        int v1;
        for(v1 = 0; v1 < v0.size(); ++v1) {
            v2 = v0.get(v1);
            if((this.isFrontface) && ((CameraInfo)v2).frontCamera != 0) {
                goto label_20;
            }

            if(!this.isFrontface && ((CameraInfo)v2).frontCamera == 0) {
                goto label_20;
            }
        }

        v2 = null;
    label_20:
        if(v2 == null) {
            return;
        }

        float v0_1 = 1.333333f;
        float v1_1 = (((float)Math.max(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y))) / (((float)Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y)));
        int v4 = 3;
        int v5 = 4;
        float v6 = 0.1f;
        int v7 = 9;
        int v8 = 16;
        int v9 = 1280;
        if(this.initialFrontface) {
            v3 = new Size(v8, v7);
            v10 = 480;
            v11 = 270;
        }
        else {
            if(Math.abs(v1_1 - v0_1) < v6) {
                v3 = new Size(v5, v4);
                v11 = 960;
            }
            else {
                v3 = new Size(v8, v7);
                v11 = 720;
            }

            v10 = 1280;
        }

        if(this.textureView.getWidth() > 0 && this.textureView.getHeight() > 0) {
            int v12 = Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
            this.previewSize = CameraController.chooseOptimalSize(((CameraInfo)v2).getPreviewSizes(), v12, v3.getHeight() * v12 / v3.getWidth(), v3);
        }

        v3 = CameraController.chooseOptimalSize(((CameraInfo)v2).getPictureSizes(), v10, v11, v3);
        if(v3.getWidth() < v9 || v3.getHeight() < v9) {
        label_100:
            v0_2 = v3;
        }
        else {
            v0_2 = Math.abs(v1_1 - v0_1) < v6 ? new Size(v4, v5) : new Size(v7, v8);
            v0_2 = CameraController.chooseOptimalSize(((CameraInfo)v2).getPictureSizes(), v11, v10, v0_2);
            if(v0_2.getWidth() < v9) {
                goto label_101;
            }

            if(v0_2.getHeight() >= v9) {
                goto label_100;
            }
        }

    label_101:
        SurfaceTexture v1_2 = this.textureView.getSurfaceTexture();
        if(this.previewSize != null && v1_2 != null) {
            v1_2.setDefaultBufferSize(this.previewSize.getWidth(), this.previewSize.getHeight());
            this.cameraSession = new CameraSession(((CameraInfo)v2), this.previewSize, v0_2, 256);
            CameraController.getInstance().open(this.cameraSession, v1_2, new Runnable() {
                public void run() {
                    if(CameraView.this.cameraSession != null) {
                        CameraView.this.cameraSession.setInitied();
                    }

                    CameraView.this.checkPreviewMatrix();
                }
            }, new Runnable() {
                public void run() {
                    if(CameraView.this.delegate != null) {
                        CameraView.this.delegate.onCameraCreated(CameraView.this.cameraSession.cameraInfo.camera);
                    }
                }
            });
        }
    }

    public boolean isFrontface() {
        return this.isFrontface;
    }

    public boolean isInitied() {
        return this.initied;
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.checkPreviewMatrix();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture arg1, int arg2, int arg3) {
        this.initCamera();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg3) {
        if(this.cameraSession != null) {
            CameraController.getInstance().close(this.cameraSession, null, null);
        }

        return 0;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture arg1, int arg2, int arg3) {
        this.checkPreviewMatrix();
    }

    public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
        if(!this.initied && this.cameraSession != null && (this.cameraSession.isInitied())) {
            if(this.delegate != null) {
                this.delegate.onCameraInit();
            }

            this.initied = true;
        }
    }

    public void setClipLeft(int arg1) {
        this.clipLeft = arg1;
    }

    public void setClipTop(int arg1) {
        this.clipTop = arg1;
    }

    public void setDelegate(CameraViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void setMirror(boolean arg1) {
        this.mirror = arg1;
    }

    public void switchCamera() {
        if(this.cameraSession != null) {
            CameraController.getInstance().close(this.cameraSession, null, null);
            this.cameraSession = null;
        }

        this.initied = false;
        this.isFrontface ^= 1;
        this.initCamera();
    }
}

