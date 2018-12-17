package org.telegram.customization.util.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path$Direction;
import android.graphics.Path;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera$Parameters;
import android.hardware.Camera$Size;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.telegram.customization.Activities.VideoCaptureActivity;

public class CameraPreview extends SurfaceView implements SurfaceHolder$Callback {
    Context a;
    private SurfaceHolder b;
    private VideoCaptureActivity c;
    private Camera d;
    private Path e;

    public CameraPreview(Context arg2) {
        super(arg2);
        this.b = this.getHolder();
        this.b.addCallback(((SurfaceHolder$Callback)this));
        this.a = arg2;
        this.b.setType(3);
        this.a();
    }

    public CameraPreview(Context arg1, Camera arg2) {
        super(arg1);
        this.d = arg2;
        this.b = this.getHolder();
        this.b.addCallback(((SurfaceHolder$Callback)this));
        this.a = arg1;
        this.b.setType(3);
        this.a();
    }

    public CameraPreview(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.b = this.getHolder();
        this.b.addCallback(((SurfaceHolder$Callback)this));
        this.a = arg1;
        this.b.setType(3);
        this.a();
    }

    public CameraPreview(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.b = this.getHolder();
        this.b.addCallback(((SurfaceHolder$Callback)this));
        this.a = arg1;
        this.b.setType(3);
        this.a();
    }

    public void a(Camera arg11) {
        if(this.b.getSurface() == null) {
            return;
        }

        try {
            this.d.stopPreview();
            goto label_6;
        }
        catch(Exception ) {
        label_6:
            this.setCamera(arg11);
            try {
                Camera$CameraInfo v0 = new Camera$CameraInfo();
                Camera.getCameraInfo(1, v0);
                int v0_1 = v0.orientation;
                Camera$Parameters v1 = arg11.getParameters();
                List v2 = v1.getSupportedPreviewSizes();
                Object v3 = null;
                float v4 = 340282346638528860000000000000000000000f;
                float v5 = (((float)this.getHeight())) / (((float)this.getWidth()));
                Iterator v2_1 = v2.iterator();
                while(v2_1.hasNext()) {
                    Object v6 = v2_1.next();
                    float v7 = (((float)((Camera$Size)v6).width)) / (((float)((Camera$Size)v6).height));
                    if(v3 != null && Math.abs(v5 - v7) >= Math.abs(v5 - v4)) {
                        continue;
                    }

                    v3 = v6;
                    v4 = v7;
                }

                int v4_1 = 0;
                switch(this.c.getWindowManager().getDefaultDisplay().getRotation()) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        v4_1 = 90;
                        break;
                    }
                    case 2: {
                        v4_1 = 180;
                        break;
                    }
                    case 3: {
                        v4_1 = 270;
                        break;
                    }
                    default: {
                        break;
                    }
                }

                this.d.setDisplayOrientation((360 - (v0_1 + v4_1) % 360) % 360);
                v1.setPreviewSize(((Camera$Size)v3).width, ((Camera$Size)v3).height);
                v1.setRotation((v0_1 + 360 + v4_1) % 360);
                arg11.setParameters(v1);
                this.d.setPreviewDisplay(this.b);
                this.d.startPreview();
            }
            catch(Exception v11) {
                Log.d("View", "Error starting camera preview: " + v11.getMessage());
            }

            return;
        }
    }

    private void a() {
        this.e = new Path();
        this.e.addCircle(310f, 330f, 250f, Path$Direction.CW);
    }

    protected void dispatchDraw(Canvas arg2) {
        arg2.clipPath(this.e);
        super.dispatchDraw(arg2);
    }

    public Camera getmCamera() {
        return this.d;
    }

    public void setActivity(VideoCaptureActivity arg1) {
        this.c = arg1;
    }

    public void setCamera(Camera arg1) {
        this.d = arg1;
    }

    public void setmCamera(Camera arg1) {
        this.d = arg1;
    }

    public void surfaceChanged(SurfaceHolder arg1, int arg2, int arg3, int arg4) {
        this.a(this.d);
    }

    public void surfaceCreated(SurfaceHolder arg4) {
        try {
            if(this.d != null) {
                return;
            }

            this.d.setPreviewDisplay(arg4);
            this.d.startPreview();
        }
        catch(IOException v4) {
            Log.d("View", "Error setting camera preview: " + v4.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder arg1) {
    }
}

