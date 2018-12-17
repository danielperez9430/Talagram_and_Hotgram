package org.linphone.mediastream.video.capture;

import android.hardware.Camera$Parameters;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera;
import android.view.SurfaceView;
import java.util.Iterator;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration$AndroidCamera$Size;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration$AndroidCamera;
import org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration;

public class AndroidVideoApi5JniWrapper {
    public static boolean isRecording = false;

    static {
    }

    public AndroidVideoApi5JniWrapper() {
        super();
    }

    public static void activateAutoFocus(Object arg3) {
        Object[] v0 = new Object[2];
        v0[0] = "mediastreamer";
        v0[1] = "Turning on autofocus on camera " + arg3;
        Log.d(v0);
        if(arg3 != null && (((Camera)arg3).getParameters().getFocusMode() == "auto" || ((Camera)arg3).getParameters().getFocusMode() == "macro")) {
            ((Camera)arg3).autoFocus(null);
        }
    }

    protected static void applyCameraParameters(Camera arg3, int arg4, int arg5, int arg6) {
        Camera$Parameters v0 = arg3.getParameters();
        v0.setPreviewSize(arg4, arg5);
        List v4 = v0.getSupportedPreviewFrameRates();
        if(v4 != null) {
            arg5 = 2147483647;
            Iterator v4_1 = v4.iterator();
            while(v4_1.hasNext()) {
                Object v1 = v4_1.next();
                int v2 = Math.abs(((Integer)v1).intValue() - arg6);
                if(v2 >= arg5) {
                    continue;
                }

                v0.setPreviewFrameRate(((Integer)v1).intValue());
                arg5 = v2;
            }

            Object[] v4_2 = new Object[2];
            v4_2[0] = "mediastreamer";
            v4_2[1] = "Preview framerate set:" + v0.getPreviewFrameRate();
            Log.d(v4_2);
        }

        arg3.setParameters(v0);
    }

    public static int detectCameras(int[] arg8, int[] arg9, int[] arg10) {
        Log.d(new Object[]{"detectCameras\n"});
        AndroidCamera[] v1 = AndroidCameraConfiguration.retrieveCameras();
        int v2 = v1.length;
        int v4 = 0;
        int v5 = 0;
        while(v4 < v2) {
            AndroidCamera v6 = v1[v4];
            if(v5 == 2) {
                Log.w(new Object[]{"Returning only the 2 first cameras (increase buffer size to retrieve all)"});
            }
            else {
                arg8[v5] = v6.id;
                arg9[v5] = v6.frontFacing;
                arg10[v5] = v6.orientation;
                ++v5;
                ++v4;
                continue;
            }

            return v5;
        }

        return v5;
    }

    public static native void putImage(long arg0, byte[] arg1) {
    }

    public static int[] selectNearestResolutionAvailable(int arg3, int arg4, int arg5) {
        Object[] v0 = new Object[2];
        v0[0] = "mediastreamer";
        v0[1] = "selectNearestResolutionAvailable: " + arg3 + ", " + arg4 + "x" + arg5;
        Log.d(v0);
        return AndroidVideoApi5JniWrapper.selectNearestResolutionAvailableForCamera(arg3, arg4, arg5);
    }

    protected static int[] selectNearestResolutionAvailableForCamera(int arg13, int arg14, int arg15) {
        Object v9;
        Object v4_2;
        if(arg15 > arg14) {
            int v12 = arg15;
            arg15 = arg14;
            arg14 = v12;
        }

        AndroidCamera[] v0 = AndroidCameraConfiguration.retrieveCameras();
        int v1 = v0.length;
        int[] v2 = null;
        List v5 = ((List)v2);
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            AndroidCamera v6 = v0[v4];
            if(v6.id == arg13) {
                v5 = v6.resolutions;
            }
        }

        int v0_1 = 2;
        if(v5 == null) {
            Object[] v14 = new Object[v0_1];
            v14[0] = "mediastreamer";
            v14[1] = "Failed to retrieve supported resolutions.";
            Log.e(v14);
            return v2;
        }

        Object[] v1_1 = new Object[v0_1];
        v1_1[0] = "mediastreamer";
        v1_1[1] = v5.size() + " supported resolutions :";
        Log.d(v1_1);
        Iterator v1_2 = v5.iterator();
        while(v1_2.hasNext()) {
            v4_2 = v1_2.next();
            Object[] v6_1 = new Object[v0_1];
            v6_1[0] = "mediastreamer";
            v6_1[1] = "\t" + ((Size)v4_2).width + "x" + ((Size)v4_2).height;
            Log.d(v6_1);
        }

        v1 = Math.max(arg14, arg15);
        arg14 = Math.min(arg14, arg15);
        arg15 = 3;
        try {
            v4_2 = v5.get(0);
            int v6_2 = v1 * arg14;
            int v7_1 = 2147483647;
            Iterator v5_1 = v5.iterator();
            int v8 = 0;
            do {
                if(v5_1.hasNext()) {
                    v9 = v5_1.next();
                    int v10 = (v6_2 - ((Size)v9).width * ((Size)v9).height) * -1;
                    if((((Size)v9).width >= v1 && ((Size)v9).height >= arg14 || ((Size)v9).width >= arg14 && ((Size)v9).height >= v1) && v10 < v7_1) {
                        v4_2 = v9;
                        v7_1 = v10;
                        v8 = 0;
                    }

                    v10 = (v6_2 - ((Size)v9).width * ((Size)v9).height / 4) * -1;
                    if((((Size)v9).width / v0_1 >= v1 && ((Size)v9).height / v0_1 >= arg14 || ((Size)v9).width / v0_1 >= arg14 && ((Size)v9).height / v0_1 >= v1) && v10 < v7_1) {
                        if(Version.hasFastCpuWithAsmOptim()) {
                            v4_2 = v9;
                            v7_1 = v10;
                            v8 = 1;
                        }
                        else {
                            v4_2 = v9;
                            v8 = 0;
                        }
                    }

                    if(((Size)v9).width != v1) {
                        continue;
                    }

                    if(((Size)v9).height != arg14) {
                        continue;
                    }

                    break;
                }

                goto label_121;
            }
            while(true);

            v4_2 = v9;
            v8 = 0;
        label_121:
            int[] v14_2 = new int[arg15];
            v14_2[0] = ((Size)v4_2).width;
            v14_2[1] = ((Size)v4_2).height;
            v14_2[v0_1] = v8;
            v1_1 = new Object[v0_1];
            v1_1[0] = "mediastreamer";
            v1_1[1] = "resolution selection done (" + v14_2[0] + ", " + v14_2[1] + ", " + v14_2[v0_1] + ")";
            Log.d(v1_1);
            return v14_2;
        }
        catch(Exception v14_1) {
            Object[] v15 = new Object[arg15];
            v15[0] = v14_1;
            v15[1] = "mediastreamer";
            v15[v0_1] = " resolution selection failed";
            Log.e(v15);
            return v2;
        }
    }

    public static void setPreviewDisplaySurface(Object arg3, Object arg4) {
        Object[] v0 = new Object[2];
        v0[0] = "mediastreamer";
        v0[1] = "setPreviewDisplaySurface(" + arg3 + ", " + arg4 + ")";
        Log.d(v0);
        try {
            ((Camera)arg3).setPreviewDisplay(((SurfaceView)arg4).getHolder());
        }
        catch(Exception v3) {
            v3.printStackTrace();
        }
    }

    public static Object startRecording(int arg5, int arg6, int arg7, int arg8, int arg9, long arg10) {
        Object[] v1 = new Object[2];
        v1[0] = "mediastreamer";
        v1[1] = "startRecording(" + arg5 + ", " + arg6 + ", " + arg7 + ", " + arg8 + ", " + arg9 + ", " + arg10 + ")";
        Log.d(v1);
        Camera v5 = Camera.open();
        AndroidVideoApi5JniWrapper.applyCameraParameters(v5, arg6, arg7, arg8);
        v5.setPreviewCallback(new Camera$PreviewCallback(arg10) {
            public void onPreviewFrame(byte[] arg3, Camera arg4) {
                if(AndroidVideoApi5JniWrapper.isRecording) {
                    AndroidVideoApi5JniWrapper.putImage(this.val$nativePtr, arg3);
                }
            }
        });
        v5.startPreview();
        AndroidVideoApi5JniWrapper.isRecording = true;
        Object[] v6 = new Object[2];
        v6[0] = "mediastreamer";
        v6[1] = "Returning camera object: " + v5;
        Log.d(v6);
        return v5;
    }

    public static void stopRecording(Object arg5) {
        AndroidVideoApi5JniWrapper.isRecording = false;
        int v1 = 2;
        Object[] v2 = new Object[v1];
        v2[0] = "mediastreamer";
        v2[1] = "stopRecording(" + arg5 + ")";
        Log.d(v2);
        if(arg5 != null) {
            ((Camera)arg5).setPreviewCallback(null);
            ((Camera)arg5).stopPreview();
            ((Camera)arg5).release();
        }
        else {
            Object[] v5 = new Object[v1];
            v5[0] = "mediastreamer";
            v5[1] = "Cannot stop recording (\'camera\' is null)";
            Log.i(v5);
        }
    }
}

