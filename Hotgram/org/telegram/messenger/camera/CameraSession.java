package org.telegram.messenger.camera;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.hardware.Camera$Area;
import android.hardware.Camera$AutoFocusCallback;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera$Parameters;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.view.OrientationEventListener;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class CameraSession {
    class org.telegram.messenger.camera.CameraSession$1 implements Camera$AutoFocusCallback {
        org.telegram.messenger.camera.CameraSession$1(CameraSession arg1) {
            CameraSession.this = arg1;
            super();
        }

        public void onAutoFocus(boolean arg1, Camera arg2) {
        }
    }

    public static final int ORIENTATION_HYSTERESIS = 5;
    private Camera$AutoFocusCallback autoFocusCallback;
    protected CameraInfo cameraInfo;
    private String currentFlashMode;
    private int currentOrientation;
    private int diffOrientation;
    private boolean flipFront;
    private boolean initied;
    private boolean isVideo;
    private int jpegOrientation;
    private int lastDisplayOrientation;
    private int lastOrientation;
    private boolean meteringAreaSupported;
    private OrientationEventListener orientationEventListener;
    private final int pictureFormat;
    private final Size pictureSize;
    private final Size previewSize;
    private boolean sameTakePictureOrientation;

    public CameraSession(CameraInfo arg2, Size arg3, Size arg4, int arg5) {
        super();
        this.lastOrientation = -1;
        this.lastDisplayOrientation = -1;
        this.flipFront = true;
        this.autoFocusCallback = new org.telegram.messenger.camera.CameraSession$1(this);
        this.previewSize = arg3;
        this.pictureSize = arg4;
        this.pictureFormat = arg5;
        this.cameraInfo = arg2;
        SharedPreferences v2 = ApplicationLoader.applicationContext.getSharedPreferences("camera", 0);
        String v3 = this.cameraInfo.frontCamera != 0 ? "flashMode_front" : "flashMode";
        this.currentFlashMode = v2.getString(v3, "off");
        this.orientationEventListener = new OrientationEventListener(ApplicationLoader.applicationContext) {
            public void onOrientationChanged(int arg4) {
                if(CameraSession.access$000(CameraSession.this) != null && (CameraSession.access$100(CameraSession.this))) {
                    if(arg4 == -1) {
                    }
                    else {
                        CameraSession.access$202(CameraSession.this, CameraSession.access$300(CameraSession.this, arg4, CameraSession.access$200(CameraSession.this)));
                        arg4 = ApplicationLoader.applicationContext.getSystemService("window").getDefaultDisplay().getRotation();
                        if(CameraSession.access$400(CameraSession.this) == CameraSession.access$200(CameraSession.this) && arg4 == CameraSession.access$500(CameraSession.this)) {
                            return;
                        }

                        if(!CameraSession.access$600(CameraSession.this)) {
                            CameraSession.this.configurePhotoCamera();
                        }

                        CameraSession.access$502(CameraSession.this, arg4);
                        CameraSession.access$402(CameraSession.this, CameraSession.access$200(CameraSession.this));
                    }
                }
            }
        };
        if(this.orientationEventListener.canDetectOrientation()) {
            this.orientationEventListener.enable();
        }
        else {
            this.orientationEventListener.disable();
            this.orientationEventListener = null;
        }
    }

    static OrientationEventListener access$000(CameraSession arg0) {
        return arg0.orientationEventListener;
    }

    static boolean access$100(CameraSession arg0) {
        return arg0.initied;
    }

    static int access$200(CameraSession arg0) {
        return arg0.jpegOrientation;
    }

    static int access$202(CameraSession arg0, int arg1) {
        arg0.jpegOrientation = arg1;
        return arg1;
    }

    static int access$300(CameraSession arg0, int arg1, int arg2) {
        return arg0.roundOrientation(arg1, arg2);
    }

    static int access$400(CameraSession arg0) {
        return arg0.lastOrientation;
    }

    static int access$402(CameraSession arg0, int arg1) {
        arg0.lastOrientation = arg1;
        return arg1;
    }

    static int access$500(CameraSession arg0) {
        return arg0.lastDisplayOrientation;
    }

    static int access$502(CameraSession arg0, int arg1) {
        arg0.lastDisplayOrientation = arg1;
        return arg1;
    }

    static boolean access$600(CameraSession arg0) {
        return arg0.isVideo;
    }

    public void checkFlashMode(String arg4) {
        if(CameraController.getInstance().availableFlashModes.contains(this.currentFlashMode)) {
            return;
        }

        this.currentFlashMode = arg4;
        this.configurePhotoCamera();
        SharedPreferences$Editor v0 = ApplicationLoader.applicationContext.getSharedPreferences("camera", 0).edit();
        String v1 = this.cameraInfo.frontCamera != 0 ? "flashMode_front" : "flashMode";
        v0.putString(v1, arg4).commit();
    }

    protected void configurePhotoCamera() {
        int v5;
        int v7;
        boolean v6;
        Camera$Parameters v2;
        Camera v0_1;
        try {
            v0_1 = this.cameraInfo.camera;
            if(v0_1 == null) {
                return;
            }

            Camera$CameraInfo v1 = new Camera$CameraInfo();
            try {
                v2 = v0_1.getParameters();
                goto label_11;
            }
            catch(Exception v3) {
                try {
                    FileLog.e(((Throwable)v3));
                label_11:
                    Camera.getCameraInfo(this.cameraInfo.getCameraId(), v1);
                    int v4 = this.getDisplayOrientation(v1, true);
                    v6 = false;
                    if(!"samsung".equals(Build.MANUFACTURER) || !"sf2wifixx".equals(Build.PRODUCT)) {
                        v5 = 90;
                        switch(v4) {
                            case 1: {
                                v7 = 90;
                                break;
                            }
                            case 2: {
                                v7 = 180;
                                break;
                            }
                            case 3: {
                                v7 = 270;
                                break;
                            }
                            default: {
                                v7 = 0;
                                break;
                            }
                        }

                        if(v1.orientation % v5 != 0) {
                            v1.orientation = 0;
                        }

                        if(v1.facing == 1) {
                            v5 = (360 - (v1.orientation + v7) % 360) % 360;
                            goto label_52;
                        }

                        v5 = (v1.orientation - v7 + 360) % 360;
                    }
                    else {
                        v5 = 0;
                    }

                label_52:
                    this.currentOrientation = v5;
                    v0_1.setDisplayOrientation(v5);
                    if(v2 == null) {
                        return;
                    }

                    v2.setPreviewSize(this.previewSize.getWidth(), this.previewSize.getHeight());
                    v2.setPictureSize(this.pictureSize.getWidth(), this.pictureSize.getHeight());
                    v2.setPictureFormat(this.pictureFormat);
                    String v5_1 = "continuous-picture";
                    if(v2.getSupportedFocusModes().contains(v5_1)) {
                        v2.setFocusMode(v5_1);
                    }

                    if(this.jpegOrientation == -1) {
                        goto label_88;
                    }
                    else if(v1.facing == 1) {
                        v5 = (v1.orientation - this.jpegOrientation + 360) % 360;
                    }
                    else {
                        v5 = (v1.orientation + this.jpegOrientation) % 360;
                    }

                    goto label_89;
                }
                catch(Throwable v0) {
                    goto label_109;
                }
            }
        }
        catch(Throwable v0) {
            goto label_109;
        }

    label_88:
        v5 = 0;
        try {
        label_89:
            v2.setRotation(v5);
            if(v1.facing != 1) {
                goto label_98;
            }
            else if((360 - v4) % 360 == v5) {
                v6 = true;
            }

        label_96:
            this.sameTakePictureOrientation = v6;
            goto label_101;
        }
        catch(Exception ) {
            goto label_101;
        }
        catch(Throwable v0) {
            goto label_109;
        }

    label_98:
        if(v4 != v5) {
            goto label_96;
        }

        v6 = true;
        goto label_96;
        try {
        label_101:
            v2.setFlashMode(this.currentFlashMode);
            try {
                v0_1.setParameters(v2);
                goto label_104;
            }
            catch(Exception ) {
            label_104:
                if(v2.getMaxNumMeteringAreas() <= 0) {
                    return;
                }

                this.meteringAreaSupported = true;
            }
        }
        catch(Throwable v0) {
        label_109:
            FileLog.e(v0);
        }
    }

    protected void configureRecorder(int arg6, MediaRecorder arg7) {
        CamcorderProfile v6;
        int v0_1;
        Camera$CameraInfo v0 = new Camera$CameraInfo();
        Camera.getCameraInfo(this.cameraInfo.cameraId, v0);
        this.getDisplayOrientation(v0, false);
        if(this.jpegOrientation != -1) {
            v0_1 = v0.facing == 1 ? v0.orientation - this.jpegOrientation + 360 : v0.orientation + this.jpegOrientation;
            v0_1 %= 360;
        }
        else {
            v0_1 = 0;
        }

        arg7.setOrientationHint(v0_1);
        v0_1 = this.getHigh();
        boolean v2 = CamcorderProfile.hasProfile(this.cameraInfo.cameraId, v0_1);
        boolean v4 = CamcorderProfile.hasProfile(this.cameraInfo.cameraId, 0);
        if(v2) {
            if(arg6 != 1 && (v4)) {
                goto label_40;
            }

            v6 = CamcorderProfile.get(this.cameraInfo.cameraId, v0_1);
        }
        else {
        label_40:
            if(!v4) {
                goto label_47;
            }

            v6 = CamcorderProfile.get(this.cameraInfo.cameraId, 0);
        }

        arg7.setProfile(v6);
        this.isVideo = true;
        return;
    label_47:
        throw new IllegalStateException("cannot find valid CamcorderProfile");
    }

    protected void configureRoundCamera() {
        int v5;
        int v7;
        boolean v6;
        Camera$Parameters v3;
        Camera v1;
        try {
            this.isVideo = true;
            v1 = this.cameraInfo.camera;
            if(v1 == null) {
                return;
            }

            Camera$CameraInfo v2 = new Camera$CameraInfo();
            try {
                v3 = v1.getParameters();
                goto label_13;
            }
            catch(Exception v4) {
                try {
                    FileLog.e(((Throwable)v4));
                label_13:
                    Camera.getCameraInfo(this.cameraInfo.getCameraId(), v2);
                    int v4_1 = this.getDisplayOrientation(v2, true);
                    v6 = false;
                    if(!"samsung".equals(Build.MANUFACTURER) || !"sf2wifixx".equals(Build.PRODUCT)) {
                        v5 = 90;
                        switch(v4_1) {
                            case 1: {
                                v7 = 90;
                                break;
                            }
                            case 2: {
                                v7 = 180;
                                break;
                            }
                            case 3: {
                                v7 = 270;
                                break;
                            }
                            default: {
                                v7 = 0;
                                break;
                            }
                        }

                        if(v2.orientation % v5 != 0) {
                            v2.orientation = 0;
                        }

                        if(v2.facing == 1) {
                            v5 = (360 - (v2.orientation + v7) % 360) % 360;
                            goto label_53;
                        }

                        v5 = (v2.orientation - v7 + 360) % 360;
                    }
                    else {
                        v5 = 0;
                    }

                label_53:
                    this.currentOrientation = v5;
                    v1.setDisplayOrientation(v5);
                    this.diffOrientation = this.currentOrientation - v4_1;
                    if(v3 == null) {
                        return;
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("set preview size = " + this.previewSize.getWidth() + " " + this.previewSize.getHeight());
                    }

                    v3.setPreviewSize(this.previewSize.getWidth(), this.previewSize.getHeight());
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("set picture size = " + this.pictureSize.getWidth() + " " + this.pictureSize.getHeight());
                    }

                    v3.setPictureSize(this.pictureSize.getWidth(), this.pictureSize.getHeight());
                    v3.setPictureFormat(this.pictureFormat);
                    v3.setRecordingHint(true);
                    String v5_2 = "continuous-video";
                    if(!v3.getSupportedFocusModes().contains(v5_2)) {
                        v5_2 = "auto";
                        if(v3.getSupportedFocusModes().contains(v5_2)) {
                            goto label_108;
                        }
                    }
                    else {
                    label_108:
                        v3.setFocusMode(v5_2);
                    }

                    if(this.jpegOrientation == -1) {
                        goto label_131;
                    }
                    else if(v2.facing == 1) {
                        v5 = (v2.orientation - this.jpegOrientation + 360) % 360;
                    }
                    else {
                        v5 = (v2.orientation + this.jpegOrientation) % 360;
                    }

                    goto label_132;
                }
                catch(Throwable v0) {
                    goto label_152;
                }
            }
        }
        catch(Throwable v0) {
            goto label_152;
        }

    label_131:
        v5 = 0;
        try {
        label_132:
            v3.setRotation(v5);
            if(v2.facing != 1) {
                goto label_141;
            }
            else if((360 - v4_1) % 360 == v5) {
                v6 = true;
            }

        label_139:
            this.sameTakePictureOrientation = v6;
            goto label_144;
        }
        catch(Exception ) {
            goto label_144;
        }
        catch(Throwable v0) {
            goto label_152;
        }

    label_141:
        if(v4_1 != v5) {
            goto label_139;
        }

        v6 = true;
        goto label_139;
        try {
        label_144:
            v3.setFlashMode("off");
            try {
                v1.setParameters(v3);
                goto label_147;
            }
            catch(Exception ) {
            label_147:
                if(v3.getMaxNumMeteringAreas() <= 0) {
                    return;
                }

                this.meteringAreaSupported = true;
            }
        }
        catch(Throwable v0) {
        label_152:
            FileLog.e(v0);
        }
    }

    public void destroy() {
        this.initied = false;
        if(this.orientationEventListener != null) {
            this.orientationEventListener.disable();
            this.orientationEventListener = null;
        }
    }

    protected void focusToRect(Rect arg6, Rect arg7) {
        Camera$Parameters v1;
        Camera v0;
        try {
            v0 = this.cameraInfo.camera;
            if(v0 == null) {
                return;
            }

            v0.cancelAutoFocus();
        }
        catch(Exception v6) {
            goto label_36;
        }

        try {
            v1 = v0.getParameters();
            goto label_10;
        }
        catch(Exception v2) {
            try {
                FileLog.e(((Throwable)v2));
            label_10:
                if(v1 == null) {
                    return;
                }

                v1.setFocusMode("auto");
                ArrayList v2_1 = new ArrayList();
                int v4 = 1000;
                v2_1.add(new Camera$Area(arg6, v4));
                v1.setFocusAreas(((List)v2_1));
                if(this.meteringAreaSupported) {
                    ArrayList v6_1 = new ArrayList();
                    v6_1.add(new Camera$Area(arg7, v4));
                    v1.setMeteringAreas(((List)v6_1));
                }
            }
            catch(Exception v6) {
                goto label_36;
            }
        }

        try {
            v0.setParameters(v1);
            v0.autoFocus(this.autoFocusCallback);
        }
        catch(Exception v6) {
            try {
                FileLog.e(((Throwable)v6));
            }
            catch(Exception v6) {
            label_36:
                FileLog.e(((Throwable)v6));
            }
        }
    }

    public String getCurrentFlashMode() {
        return this.currentFlashMode;
    }

    public int getCurrentOrientation() {
        return this.currentOrientation;
    }

    private int getDisplayOrientation(Camera$CameraInfo arg6, boolean arg7) {
        int v1 = 0;
        int v2 = 270;
        int v3 = 90;
        switch(ApplicationLoader.applicationContext.getSystemService("window").getDefaultDisplay().getRotation()) {
            case 1: {
                v1 = 90;
                break;
            }
            case 2: {
                v1 = 180;
                break;
            }
            case 3: {
                v1 = 270;
                break;
            }
            default: {
                break;
            }
        }

        if(arg6.facing == 1) {
            int v6 = (360 - (arg6.orientation + v1) % 360) % 360;
            if(!arg7 && v6 == v3) {
                v6 = 270;
            }

            if(!arg7 && ("Huawei".equals(Build.MANUFACTURER)) && ("angler".equals(Build.PRODUCT)) && v6 == v2) {
                return v3;
            }

            v3 = v6;
        }
        else {
            v3 = (arg6.orientation - v1 + 360) % 360;
        }

        return v3;
    }

    public int getDisplayOrientation() {
        try {
            Camera$CameraInfo v0_1 = new Camera$CameraInfo();
            Camera.getCameraInfo(this.cameraInfo.getCameraId(), v0_1);
            return this.getDisplayOrientation(v0_1, true);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            return 0;
        }
    }

    private int getHigh() {
        if(("LGE".equals(Build.MANUFACTURER)) && ("g3_tmo_us".equals(Build.PRODUCT))) {
            return 4;
        }

        return 1;
    }

    public String getNextFlashMode() {
        ArrayList v0 = CameraController.getInstance().availableFlashModes;
        int v2;
        for(v2 = 0; v2 < v0.size(); ++v2) {
            if(v0.get(v2).equals(this.currentFlashMode)) {
                Object v0_1 = v2 < v0.size() - 1 ? v0.get(v2 + 1) : v0.get(0);
                return ((String)v0_1);
            }
        }

        return this.currentFlashMode;
    }

    public int getWorldAngle() {
        return this.diffOrientation;
    }

    public boolean isFlipFront() {
        return this.flipFront;
    }

    public boolean isInitied() {
        return this.initied;
    }

    public boolean isSameTakePictureOrientation() {
        return this.sameTakePictureOrientation;
    }

    private int roundOrientation(int arg4, int arg5) {
        int v0 = 1;
        if(arg5 == -1) {
        }
        else {
            int v1 = Math.abs(arg4 - arg5);
            if(Math.min(v1, 360 - v1) >= 50) {
            }
            else {
                v0 = 0;
            }
        }

        if(v0 != 0) {
            return (arg4 + 45) / 90 * 90 % 360;
        }

        return arg5;
    }

    public void setCurrentFlashMode(String arg4) {
        this.currentFlashMode = arg4;
        this.configurePhotoCamera();
        SharedPreferences$Editor v0 = ApplicationLoader.applicationContext.getSharedPreferences("camera", 0).edit();
        String v1 = this.cameraInfo.frontCamera != 0 ? "flashMode_front" : "flashMode";
        v0.putString(v1, arg4).commit();
    }

    public void setFlipFront(boolean arg1) {
        this.flipFront = arg1;
    }

    public void setInitied() {
        this.initied = true;
    }

    public void setOneShotPreviewCallback(Camera$PreviewCallback arg2) {
        if(this.cameraInfo != null && this.cameraInfo.camera != null) {
            this.cameraInfo.camera.setOneShotPreviewCallback(arg2);
        }
    }

    public void setPreviewCallback(Camera$PreviewCallback arg2) {
        this.cameraInfo.camera.setPreviewCallback(arg2);
    }

    protected void stopVideoRecording() {
        this.isVideo = false;
        this.configurePhotoCamera();
    }
}

