package org.linphone.mediastream.video.capture;

import android.annotation.TargetApi;
import android.graphics.ImageFormat;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera$Parameters;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera$Size;
import android.hardware.Camera;
import java.util.Iterator;
import java.util.List;
import org.linphone.mediastream.Log;

@TargetApi(value=9) public class AndroidVideoApi9JniWrapper {
    public AndroidVideoApi9JniWrapper() {
        super();
    }

    public static int detectCameras(int[] arg0, int[] arg1, int[] arg2) {
        return AndroidVideoApi5JniWrapper.detectCameras(arg0, arg1, arg2);
    }

    private static int[] findClosestEnclosingFpsRange(int arg7, List arg8) {
        Object[] v1 = new Object[1];
        v1[0] = "Searching for closest fps range from " + arg7;
        Log.d(v1);
        if(arg8 != null) {
            if(arg8.size() == 0) {
            }
            else {
                Object v1_1 = arg8.get(0);
                int v2_1 = Math.abs(v1_1[0] - arg7) + Math.abs(v1_1[1] - arg7);
                Iterator v8 = arg8.iterator();
                while(v8.hasNext()) {
                    Object v4 = v8.next();
                    if(v4[0] > arg7) {
                        continue;
                    }

                    if(v4[1] < arg7) {
                    }
                    else {
                        int v5 = Math.abs(v4[0] - arg7) + Math.abs(v4[1] - arg7);
                        if(v5 >= v2_1) {
                            continue;
                        }

                        v1 = new Object[1];
                        v1[0] = "a better range has been found: w=" + v4[0] + ",h=" + v4[1];
                        Log.d(v1);
                        v1_1 = v4;
                        v2_1 = v5;
                    }
                }

                Object[] v7 = new Object[1];
                v7[0] = "The closest fps range is w=" + v1_1[0] + ",h=" + v1_1[1];
                Log.d(v7);
                return ((int[])v1_1);
            }
        }

        return new int[]{0, 0};
    }

    public static int[] selectNearestResolutionAvailable(int arg3, int arg4, int arg5) {
        Object[] v0 = new Object[1];
        v0[0] = "selectNearestResolutionAvailable: " + arg3 + ", " + arg4 + "x" + arg5;
        Log.d(v0);
        return AndroidVideoApi5JniWrapper.selectNearestResolutionAvailableForCamera(arg3, arg4, arg5);
    }

    private static void setCameraDisplayOrientation(int arg4, int arg5, Camera arg6) {
        Camera$CameraInfo v0 = new Camera$CameraInfo();
        Camera.getCameraInfo(arg5, v0);
        arg4 = v0.facing == 1 ? (360 - (v0.orientation + arg4) % 360) % 360 : (v0.orientation - arg4 + 360) % 360;
        Object[] v5 = new Object[1];
        v5[0] = "Camera preview orientation: " + arg4;
        Log.w(v5);
        try {
            arg6.setDisplayOrientation(arg4);
        }
        catch(Exception v5_1) {
            Object[] v0_2 = new Object[1];
            v0_2[0] = "Failed to execute: camera[" + arg6 + "].setDisplayOrientation(" + arg4 + ")";
            Log.e(v0_2);
            v5_1.printStackTrace();
        }
    }

    public static void setPreviewDisplaySurface(Object arg0, Object arg1) {
        AndroidVideoApi5JniWrapper.setPreviewDisplaySurface(arg0, arg1);
    }

    public static Object startRecording(int arg5, int arg6, int arg7, int arg8, int arg9, long arg10) {
        Object[] v1 = new Object[1];
        v1[0] = "startRecording(" + arg5 + ", " + arg6 + ", " + arg7 + ", " + arg8 + ", " + arg9 + ", " + arg10 + ")";
        Log.d(v1);
        try {
            Camera v1_1 = Camera.open(arg5);
            Camera$Parameters v2_1 = v1_1.getParameters();
            v2_1.setPreviewSize(arg6, arg7);
            int[] v8 = AndroidVideoApi9JniWrapper.findClosestEnclosingFpsRange(arg8 * 1000, v2_1.getSupportedPreviewFpsRange());
            v2_1.setPreviewFpsRange(v8[0], v8[1]);
            v1_1.setParameters(v2_1);
            arg6 = arg6 * arg7 * ImageFormat.getBitsPerPixel(v2_1.getPreviewFormat()) / 8;
            v1_1.addCallbackBuffer(new byte[arg6]);
            v1_1.addCallbackBuffer(new byte[arg6]);
            v1_1.setPreviewCallbackWithBuffer(new Camera$PreviewCallback(arg10) {
                public void onPreviewFrame(byte[] arg3, Camera arg4) {
                    if(arg3 == null) {
                        Camera$Parameters v3 = arg4.getParameters();
                        Camera$Size v0 = v3.getPreviewSize();
                        int v1 = v0.width * v0.height * ImageFormat.getBitsPerPixel(v3.getPreviewFormat()) / 8;
                        arg3 = new byte[v1 + v1 / 20];
                        goto label_13;
                    }
                    else if(AndroidVideoApi5JniWrapper.isRecording) {
                        AndroidVideoApi5JniWrapper.putImage(this.val$nativePtr, arg3);
                    label_13:
                        arg4.addCallbackBuffer(arg3);
                    }
                }
            });
            AndroidVideoApi9JniWrapper.setCameraDisplayOrientation(arg9, arg5, v1_1);
            v1_1.startPreview();
            AndroidVideoApi5JniWrapper.isRecording = true;
            Object[] v5_1 = new Object[1];
            v5_1[0] = "Returning camera object: " + v1_1;
            Log.d(v5_1);
            return v1_1;
        }
        catch(Exception v5) {
            v5.printStackTrace();
            return null;
        }
    }

    public static void stopRecording(Object arg1) {
        AndroidVideoApi5JniWrapper.isRecording = false;
        AndroidVideoApi8JniWrapper.stopRecording(arg1);
    }
}

