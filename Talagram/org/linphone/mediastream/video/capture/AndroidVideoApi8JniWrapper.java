package org.linphone.mediastream.video.capture;

import android.graphics.ImageFormat;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera;
import org.linphone.mediastream.Log;

public class AndroidVideoApi8JniWrapper {
    public AndroidVideoApi8JniWrapper() {
        super();
    }

    public static int detectCameras(int[] arg0, int[] arg1, int[] arg2) {
        return AndroidVideoApi5JniWrapper.detectCameras(arg0, arg1, arg2);
    }

    public static int[] selectNearestResolutionAvailable(int arg0, int arg1, int arg2) {
        return AndroidVideoApi5JniWrapper.selectNearestResolutionAvailable(arg0, arg1, arg2);
    }

    public static void setPreviewDisplaySurface(Object arg0, Object arg1) {
        AndroidVideoApi5JniWrapper.setPreviewDisplaySurface(arg0, arg1);
    }

    public static Object startRecording(int arg4, int arg5, int arg6, int arg7, int arg8, long arg9) {
        Object[] v1 = new Object[1];
        v1[0] = "startRecording(" + arg4 + ", " + arg5 + ", " + arg6 + ", " + arg7 + ", " + arg8 + ", " + arg9 + ")";
        Log.d(v1);
        Camera v4 = Camera.open();
        AndroidVideoApi5JniWrapper.applyCameraParameters(v4, arg5, arg6, arg7);
        arg5 = arg5 * arg6 * ImageFormat.getBitsPerPixel(v4.getParameters().getPreviewFormat()) / 8;
        v4.addCallbackBuffer(new byte[arg5]);
        v4.addCallbackBuffer(new byte[arg5]);
        v4.setPreviewCallbackWithBuffer(new Camera$PreviewCallback(arg9) {
            public void onPreviewFrame(byte[] arg3, Camera arg4) {
                if(AndroidVideoApi5JniWrapper.isRecording) {
                    AndroidVideoApi5JniWrapper.putImage(this.val$nativePtr, arg3);
                    arg4.addCallbackBuffer(arg3);
                }
            }
        });
        v4.startPreview();
        AndroidVideoApi5JniWrapper.isRecording = true;
        Object[] v5 = new Object[1];
        v5[0] = "Returning camera object: " + v4;
        Log.d(v5);
        return v4;
    }

    public static void stopRecording(Object arg5) {
        AndroidVideoApi5JniWrapper.isRecording = false;
        Object[] v2 = new Object[1];
        v2[0] = "stopRecording(" + arg5 + ")";
        Log.d(v2);
        if(arg5 != null) {
            ((Camera)arg5).setPreviewCallbackWithBuffer(null);
            ((Camera)arg5).stopPreview();
            ((Camera)arg5).release();
        }
        else {
            Log.i(new Object[]{"Cannot stop recording (\'camera\' is null)"});
        }
    }
}

