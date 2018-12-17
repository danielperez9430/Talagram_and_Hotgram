package org.telegram.messenger.camera;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera$Parameters;
import android.hardware.Camera$PictureCallback;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera$Size;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder$OnInfoListener;
import android.media.MediaRecorder;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.SerializedData;

public class CameraController implements MediaRecorder$OnInfoListener {
    class CompareSizesByArea implements Comparator {
        CompareSizesByArea() {
            super();
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Size)arg1), ((Size)arg2));
        }

        public int compare(Size arg5, Size arg6) {
            return Long.signum((((long)arg5.getWidth())) * (((long)arg5.getHeight())) - (((long)arg6.getWidth())) * (((long)arg6.getHeight())));
        }
    }

    public interface VideoTakeCallback {
        void onFinishVideoRecording(String arg1, long arg2);
    }

    private static final int CORE_POOL_SIZE = 1;
    private static volatile CameraController Instance = null;
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final int MAX_POOL_SIZE = 1;
    protected ArrayList availableFlashModes;
    protected ArrayList cameraInfos;
    private boolean cameraInitied;
    private boolean loadingCameras;
    private ArrayList onFinishCameraInitRunnables;
    private VideoTakeCallback onVideoTakeCallback;
    private String recordedFile;
    private MediaRecorder recorder;
    private ThreadPoolExecutor threadPool;

    static {
    }

    public CameraController() {
        super();
        this.availableFlashModes = new ArrayList();
        this.onFinishCameraInitRunnables = new ArrayList();
        this.threadPool = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    static boolean access$002(CameraController arg0, boolean arg1) {
        arg0.loadingCameras = arg1;
        return arg1;
    }

    static boolean access$102(CameraController arg0, boolean arg1) {
        arg0.cameraInitied = arg1;
        return arg1;
    }

    static ArrayList access$200(CameraController arg0) {
        return arg0.onFinishCameraInitRunnables;
    }

    static int access$300(byte[] arg0) {
        return CameraController.getOrientation(arg0);
    }

    static MediaRecorder access$400(CameraController arg0) {
        return arg0.recorder;
    }

    static MediaRecorder access$402(CameraController arg0, MediaRecorder arg1) {
        arg0.recorder = arg1;
        return arg1;
    }

    static VideoTakeCallback access$500(CameraController arg0) {
        return arg0.onVideoTakeCallback;
    }

    static VideoTakeCallback access$502(CameraController arg0, VideoTakeCallback arg1) {
        arg0.onVideoTakeCallback = arg1;
        return arg1;
    }

    static String access$602(CameraController arg0, String arg1) {
        arg0.recordedFile = arg1;
        return arg1;
    }

    static ThreadPoolExecutor access$700(CameraController arg0) {
        return arg0.threadPool;
    }

    static void access$800(CameraController arg0) {
        arg0.finishRecordingVideo();
    }

    public void cancelOnInitRunnable(Runnable arg2) {
        this.onFinishCameraInitRunnables.remove(arg2);
    }

    public static Size chooseOptimalSize(List arg6, int arg7, int arg8, Size arg9) {
        ArrayList v0 = new ArrayList();
        int v1 = arg9.getWidth();
        int v9 = arg9.getHeight();
        int v2;
        for(v2 = 0; v2 < arg6.size(); ++v2) {
            Object v3 = arg6.get(v2);
            if(((Size)v3).getHeight() == ((Size)v3).getWidth() * v9 / v1 && ((Size)v3).getWidth() >= arg7 && ((Size)v3).getHeight() >= arg8) {
                ((List)v0).add(v3);
            }
        }

        Object v6 = ((List)v0).size() > 0 ? Collections.min(((Collection)v0), new CompareSizesByArea()) : Collections.max(((Collection)arg6), new CompareSizesByArea());
        return ((Size)v6);
    }

    public void close(CameraSession arg3, CountDownLatch arg4, Runnable arg5) {
        arg3.destroy();
        this.threadPool.execute(new Runnable(arg5, arg3, arg4) {
            public void run() {
                if(this.val$beforeDestroyRunnable != null) {
                    this.val$beforeDestroyRunnable.run();
                }

                if(this.val$session.cameraInfo.camera == null) {
                    return;
                }

                Camera$PreviewCallback v0 = null;
                try {
                    this.val$session.cameraInfo.camera.stopPreview();
                    this.val$session.cameraInfo.camera.setPreviewCallbackWithBuffer(v0);
                }
                catch(Exception v1) {
                    FileLog.e(((Throwable)v1));
                }

                try {
                    this.val$session.cameraInfo.camera.release();
                }
                catch(Exception v1) {
                    FileLog.e(((Throwable)v1));
                }

                this.val$session.cameraInfo.camera = ((Camera)v0);
                if(this.val$countDownLatch != null) {
                    this.val$countDownLatch.countDown();
                }
            }
        });
        if(arg4 != null) {
            try {
                arg4.await();
            }
            catch(Exception v3) {
                FileLog.e(((Throwable)v3));
            }
        }
    }

    private void finishRecordingVideo() {
        Exception v0_1;
        Throwable v0_2;
        MediaMetadataRetriever v3_1;
        MediaMetadataRetriever v0 = null;
        long v1 = 0;
        try {
            v3_1 = new MediaMetadataRetriever();
        }
        catch(Throwable v1_1) {
            v3_1 = v0;
            v0_2 = v1_1;
            goto label_72;
        }
        catch(Exception v3) {
            Exception v9 = v3;
            v3_1 = v0;
            v0_1 = v9;
            goto label_32;
        }

        try {
            v3_1.setDataSource(this.recordedFile);
            String v0_3 = v3_1.extractMetadata(9);
            if(v0_3 != null) {
                v1 = ((long)(((int)Math.ceil(((double)((((float)Long.parseLong(v0_3))) / 1000f))))));
            }

            goto label_18;
        }
        catch(Throwable v0_2) {
        }
        catch(Exception v0_1) {
            try {
            label_32:
                FileLog.e(((Throwable)v0_1));
                if(v3_1 != null) {
                }
                else {
                    goto label_38;
                }
            }
            catch(Throwable v0_2) {
                goto label_72;
            }

            try {
                v3_1.release();
                goto label_38;
            }
            catch(Exception v0_1) {
                goto label_37;
            }
        }

    label_72:
        if(v3_1 != null) {
            try {
                v3_1.release();
            }
            catch(Exception v1_2) {
                FileLog.e(((Throwable)v1_2));
            }

            goto label_77;
        }
        else {
        label_77:
            throw v0_2;
        }

        try {
        label_18:
            v3_1.release();
            goto label_38;
        }
        catch(Exception v0_1) {
        }

    label_37:
        FileLog.e(((Throwable)v0_1));
    label_38:
        long v7 = v1;
        Bitmap v6 = ThumbnailUtils.createVideoThumbnail(this.recordedFile, 1);
        StringBuilder v0_4 = new StringBuilder();
        v0_4.append("-2147483648_");
        v0_4.append(SharedConfig.getLastLocalId());
        v0_4.append(".jpg");
        File v5 = new File(FileLoader.getDirectory(4), v0_4.toString());
        try {
            v6.compress(Bitmap$CompressFormat.JPEG, 55, new FileOutputStream(v5));
        }
        catch(Throwable v0_2) {
            FileLog.e(v0_2);
        }

        SharedConfig.saveConfig();
        AndroidUtilities.runOnUIThread(new Runnable(v5, v6, v7) {
            public void run() {
                if(CameraController.this.onVideoTakeCallback != null) {
                    String v0 = this.val$cacheFile.getAbsolutePath();
                    if(this.val$bitmap != null) {
                        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(this.val$bitmap), Utilities.MD5(v0));
                    }

                    CameraController.this.onVideoTakeCallback.onFinishVideoRecording(v0, this.val$durationFinal);
                    CameraController.this.onVideoTakeCallback = null;
                }
            }
        });
    }

    public ArrayList getCameras() {
        return this.cameraInfos;
    }

    public static CameraController getInstance() {
        CameraController v0 = CameraController.Instance;
        if(v0 == null) {
            Class v1 = CameraController.class;
            __monitor_enter(v1);
            try {
                v0 = CameraController.Instance;
                if(v0 == null) {
                    v0 = new CameraController();
                    CameraController.Instance = v0;
                }

                __monitor_exit(v1);
                return v0;
            label_12:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_12;
            }

            throw v0_1;
        }

        return v0;
    }

    private static int getOrientation(byte[] arg10) {
        int v8;
        int v3;
        int v2;
        int v7;
        int v5;
        int v4;
        if(arg10 == null) {
            return 0;
        }

        int v1 = 0;
        while(true) {
            v4 = 4;
            v5 = 8;
            v7 = 2;
            if(v1 + 3 < arg10.length) {
                v2 = v1 + 1;
                v3 = 255;
                if((arg10[v1] & v3) == v3) {
                    v1 = arg10[v2] & v3;
                    if(v1 != v3) {
                        ++v2;
                        if(v1 != 216) {
                            if(v1 == 1) {
                            }
                            else if(v1 == 217) {
                                break;
                            }
                            else if(v1 == 218) {
                                break;
                            }
                            else {
                                v3 = CameraController.pack(arg10, v2, v7, false);
                                if(v3 >= v7) {
                                    v8 = v2 + v3;
                                    if(v8 > arg10.length) {
                                    }
                                    else {
                                        if(v1 == 225 && v3 >= v5 && CameraController.pack(arg10, v2 + 2, v4, false) == 1165519206 && CameraController.pack(arg10, v2 + 6, v7, false) == 0) {
                                            v1 = v2 + 8;
                                            v2 = v3 - 8;
                                            goto label_55;
                                        }

                                        v1 = v8;
                                        continue;
                                    }
                                }

                                return 0;
                            }
                        }
                    }

                    v1 = v2;
                    continue;
                }

                break;
            }
            else {
                goto label_54;
            }
        }

        v1 = v2;
    label_54:
        v2 = 0;
    label_55:
        if(v2 > v5) {
            v3 = CameraController.pack(arg10, v1, v4, false);
            v8 = 1229531648;
            if(v3 != v8 && v3 != 1296891946) {
                return 0;
            }

            boolean v3_1 = v3 == v8 ? true : false;
            v4 = CameraController.pack(arg10, v1 + 4, v4, v3_1) + v7;
            if(v4 < 10) {
                return 0;
            }

            if(v4 > v2) {
                return 0;
            }

            v1 += v4;
            v2 -= v4;
            for(v4 = CameraController.pack(arg10, v1 - 2, v7, v3_1); true; v4 = v8) {
                v8 = v4 - 1;
                if(v4 <= 0) {
                    return 0;
                }

                if(v2 < 12) {
                    return 0;
                }

                if(CameraController.pack(arg10, v1, v7, v3_1) == 274) {
                    int v10 = CameraController.pack(arg10, v1 + v5, v7, v3_1);
                    if(v10 != 1) {
                        if(v10 != 3) {
                            if(v10 != 6) {
                                if(v10 != v5) {
                                    return 0;
                                }

                                return 270;
                            }

                            return 90;
                        }

                        return 180;
                    }

                    return 0;
                }

                v1 += 12;
                v2 += -12;
            }
        }

        return 0;
    }

    public void initCamera(Runnable arg2) {
        if(arg2 != null && !this.onFinishCameraInitRunnables.contains(arg2)) {
            this.onFinishCameraInitRunnables.add(arg2);
        }

        if(!this.loadingCameras) {
            if(this.cameraInitied) {
            }
            else {
                this.loadingCameras = true;
                this.threadPool.execute(new Runnable() {
                    public void run() {
                        Object v9_2;
                        List v17;
                        Camera$CameraInfo v16;
                        int v5;
                        int v8;
                        int v2_1;
                        org.telegram.messenger.camera.CameraController$1 v0 = this;
                        try {
                            if(v0.this$0.cameraInfos == null) {
                                SharedPreferences v1 = MessagesController.getGlobalMainSettings();
                                String v2 = v1.getString("cameraCache", null);
                                org.telegram.messenger.camera.CameraController$1$1 v3 = new Comparator() {
                                    public int compare(Object arg1, Object arg2) {
                                        return this.compare(((Size)arg1), ((Size)arg2));
                                    }

                                    public int compare(Size arg5, Size arg6) {
                                        if(arg5.mWidth < arg6.mWidth) {
                                            return 1;
                                        }

                                        int v3 = -1;
                                        if(arg5.mWidth > arg6.mWidth) {
                                            return v3;
                                        }

                                        if(arg5.mHeight < arg6.mHeight) {
                                            return 1;
                                        }

                                        if(arg5.mHeight > arg6.mHeight) {
                                            return v3;
                                        }

                                        return 0;
                                    }
                                };
                                ArrayList v4 = new ArrayList();
                                if(v2 != null) {
                                    SerializedData v1_1 = new SerializedData(Base64.decode(v2, 0));
                                    v2_1 = v1_1.readInt32(false);
                                    int v6;
                                    for(v6 = 0; v6 < v2_1; ++v6) {
                                        CameraInfo v7 = new CameraInfo(v1_1.readInt32(false), v1_1.readInt32(false));
                                        v8 = v1_1.readInt32(false);
                                        int v9;
                                        for(v9 = 0; v9 < v8; ++v9) {
                                            v7.previewSizes.add(new Size(v1_1.readInt32(false), v1_1.readInt32(false)));
                                        }

                                        v8 = v1_1.readInt32(false);
                                        for(v9 = 0; v9 < v8; ++v9) {
                                            v7.pictureSizes.add(new Size(v1_1.readInt32(false), v1_1.readInt32(false)));
                                        }

                                        v4.add(v7);
                                        Collections.sort(v7.previewSizes, ((Comparator)v3));
                                        Collections.sort(v7.pictureSizes, ((Comparator)v3));
                                    }

                                    v1_1.cleanup();
                                }
                                else {
                                    v2_1 = Camera.getNumberOfCameras();
                                    Camera$CameraInfo v6_1 = new Camera$CameraInfo();
                                    int v7_1 = 0;
                                    v8 = 4;
                                    while(v7_1 < v2_1) {
                                        Camera.getCameraInfo(v7_1, v6_1);
                                        CameraInfo v9_1 = new CameraInfo(v7_1, v6_1.facing);
                                        if(ApplicationLoader.mainInterfacePaused) {
                                            if(!ApplicationLoader.externalInterfacePaused) {
                                            }
                                            else {
                                                throw new RuntimeException("app paused");
                                            }
                                        }

                                        Camera v10 = Camera.open(v9_1.getCameraId());
                                        Camera$Parameters v11 = v10.getParameters();
                                        List v12 = v11.getSupportedPreviewSizes();
                                        int v13 = 0;
                                        while(true) {
                                            v5 = 1280;
                                            if(v13 >= v12.size()) {
                                                break;
                                            }

                                            Object v14 = v12.get(v13);
                                            if(((Camera$Size)v14).width != v5 || ((Camera$Size)v14).height == 720) {
                                                int v15 = 2160;
                                                if(((Camera$Size)v14).height >= v15 || ((Camera$Size)v14).width >= v15) {
                                                label_118:
                                                    v16 = v6_1;
                                                    v17 = v12;
                                                }
                                                else {
                                                    v16 = v6_1;
                                                    v17 = v12;
                                                    v9_1.previewSizes.add(new Size(((Camera$Size)v14).width, ((Camera$Size)v14).height));
                                                    if(BuildVars.LOGS_ENABLED) {
                                                        FileLog.d("preview size = " + ((Camera$Size)v14).width + " " + ((Camera$Size)v14).height);
                                                    }
                                                }
                                            }
                                            else {
                                                goto label_118;
                                            }

                                            ++v13;
                                            v6_1 = v16;
                                            v12 = v17;
                                        }

                                        v16 = v6_1;
                                        List v6_2 = v11.getSupportedPictureSizes();
                                        int v11_1 = 0;
                                        while(v11_1 < v6_2.size()) {
                                            Object v12_1 = v6_2.get(v11_1);
                                            if(((Camera$Size)v12_1).width != v5 || ((Camera$Size)v12_1).height == 720) {
                                                if(("samsung".equals(Build.MANUFACTURER)) && ("jflteuc".equals(Build.PRODUCT)) && ((Camera$Size)v12_1).width >= 2048) {
                                                    goto label_169;
                                                }

                                                v9_1.pictureSizes.add(new Size(((Camera$Size)v12_1).width, ((Camera$Size)v12_1).height));
                                                if(!BuildVars.LOGS_ENABLED) {
                                                    goto label_169;
                                                }

                                                FileLog.d("picture size = " + ((Camera$Size)v12_1).width + " " + ((Camera$Size)v12_1).height);
                                            }
                                            else {
                                            }

                                        label_169:
                                            ++v11_1;
                                            v5 = 1280;
                                        }

                                        v10.release();
                                        v4.add(v9_1);
                                        Collections.sort(v9_1.previewSizes, ((Comparator)v3));
                                        Collections.sort(v9_1.pictureSizes, ((Comparator)v3));
                                        v8 += (v9_1.previewSizes.size() + v9_1.pictureSizes.size()) * 8 + 8;
                                        ++v7_1;
                                        v6_1 = v16;
                                    }

                                    SerializedData v3_1 = new SerializedData(v8);
                                    v3_1.writeInt32(v4.size());
                                    for(v5 = 0; v5 < v2_1; ++v5) {
                                        Object v6_3 = v4.get(v5);
                                        v3_1.writeInt32(((CameraInfo)v6_3).cameraId);
                                        v3_1.writeInt32(((CameraInfo)v6_3).frontCamera);
                                        v7_1 = ((CameraInfo)v6_3).previewSizes.size();
                                        v3_1.writeInt32(v7_1);
                                        for(v8 = 0; v8 < v7_1; ++v8) {
                                            v9_2 = ((CameraInfo)v6_3).previewSizes.get(v8);
                                            v3_1.writeInt32(((Size)v9_2).mWidth);
                                            v3_1.writeInt32(((Size)v9_2).mHeight);
                                        }

                                        v7_1 = ((CameraInfo)v6_3).pictureSizes.size();
                                        v3_1.writeInt32(v7_1);
                                        for(v8 = 0; v8 < v7_1; ++v8) {
                                            v9_2 = ((CameraInfo)v6_3).pictureSizes.get(v8);
                                            v3_1.writeInt32(((Size)v9_2).mWidth);
                                            v3_1.writeInt32(((Size)v9_2).mHeight);
                                        }
                                    }

                                    v1.edit().putString("cameraCache", Base64.encodeToString(v3_1.toByteArray(), 0)).commit();
                                    v3_1.cleanup();
                                }

                                v0.this$0.cameraInfos = v4;
                            }

                            AndroidUtilities.runOnUIThread(new Runnable() {
                                public void run() {
                                    this.this$1.this$0.loadingCameras = false;
                                    this.this$1.this$0.cameraInitied = true;
                                    if(!this.this$1.this$0.onFinishCameraInitRunnables.isEmpty()) {
                                        int v0;
                                        for(v0 = 0; v0 < this.this$1.this$0.onFinishCameraInitRunnables.size(); ++v0) {
                                            this.this$1.this$0.onFinishCameraInitRunnables.get(v0).run();
                                        }

                                        this.this$1.this$0.onFinishCameraInitRunnables.clear();
                                    }

                                    NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.cameraInitied, new Object[0]);
                                }
                            });
                        }
                        catch(Exception ) {
                            AndroidUtilities.runOnUIThread(new Runnable() {
                                public void run() {
                                    this.this$1.this$0.onFinishCameraInitRunnables.clear();
                                    this.this$1.this$0.loadingCameras = false;
                                    this.this$1.this$0.cameraInitied = false;
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    public boolean isCameraInitied() {
        boolean v0 = !this.cameraInitied || this.cameraInfos == null || (this.cameraInfos.isEmpty()) ? false : true;
        return v0;
    }

    public void onInfo(MediaRecorder arg1, int arg2, int arg3) {
        if(arg2 == 800 || arg2 == 801 || arg2 == 1) {
            arg1 = this.recorder;
            this.recorder = null;
            if(arg1 != null) {
                arg1.stop();
                arg1.release();
            }

            if(this.onVideoTakeCallback == null) {
                return;
            }

            this.finishRecordingVideo();
        }
    }

    public void open(CameraSession arg9, SurfaceTexture arg10, Runnable arg11, Runnable arg12) {
        if(arg9 != null) {
            if(arg10 == null) {
            }
            else {
                this.threadPool.execute(new Runnable(arg9, arg12, arg10, arg11) {
                    @SuppressLint(value={"NewApi"}) public void run() {
                        Camera v0 = this.val$session.cameraInfo.camera;
                        if(v0 == null) {
                            try {
                                CameraInfo v1_1 = this.val$session.cameraInfo;
                                Camera v2 = Camera.open(this.val$session.cameraInfo.cameraId);
                                v1_1.camera = v2;
                                v0 = v2;
                            label_15:
                                List v1_2 = v0.getParameters().getSupportedFlashModes();
                                CameraController.this.availableFlashModes.clear();
                                if(v1_2 != null) {
                                    int v3;
                                    for(v3 = 0; v3 < v1_2.size(); ++v3) {
                                        Object v4 = v1_2.get(v3);
                                        if((((String)v4).equals("off")) || (((String)v4).equals("on")) || (((String)v4).equals("auto"))) {
                                            CameraController.this.availableFlashModes.add(v4);
                                        }
                                    }

                                    this.val$session.checkFlashMode(CameraController.this.availableFlashModes.get(0));
                                }

                                if(this.val$prestartCallback != null) {
                                    this.val$prestartCallback.run();
                                }

                                this.val$session.configurePhotoCamera();
                                v0.setPreviewTexture(this.val$texture);
                                v0.startPreview();
                                if(this.val$callback == null) {
                                    return;
                                }

                                AndroidUtilities.runOnUIThread(this.val$callback);
                                return;
                            label_14:
                                goto label_59;
                            }
                            catch(Exception v1) {
                                goto label_14;
                            }
                        }

                        goto label_15;
                    label_59:
                        this.val$session.cameraInfo.camera = null;
                        if(v0 != null) {
                            v0.release();
                        }

                        FileLog.e(((Throwable)v1));
                    }
                });
            }
        }
    }

    public void openRound(CameraSession arg9, SurfaceTexture arg10, Runnable arg11, Runnable arg12) {
        if(arg9 != null) {
            if(arg10 == null) {
            }
            else {
                this.threadPool.execute(new Runnable(arg9, arg12, arg10, arg11) {
                    @SuppressLint(value={"NewApi"}) public void run() {
                        Camera v0 = this.val$session.cameraInfo.camera;
                        try {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.d("start creating round camera session");
                            }

                            if(v0 == null) {
                                CameraInfo v1_1 = this.val$session.cameraInfo;
                                Camera v2 = Camera.open(this.val$session.cameraInfo.cameraId);
                                v1_1.camera = v2;
                                v0 = v2;
                            }

                            v0.getParameters();
                            this.val$session.configureRoundCamera();
                            if(this.val$configureCallback != null) {
                                this.val$configureCallback.run();
                            }

                            v0.setPreviewTexture(this.val$texture);
                            v0.startPreview();
                            if(this.val$callback != null) {
                                AndroidUtilities.runOnUIThread(this.val$callback);
                            }

                            if(!BuildVars.LOGS_ENABLED) {
                                return;
                            }

                            FileLog.d("round camera session created");
                        }
                        catch(Exception v1) {
                            this.val$session.cameraInfo.camera = null;
                            if(v0 != null) {
                                v0.release();
                            }

                            FileLog.e(((Throwable)v1));
                        }
                    }
                });
                return;
            }
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("failed to open round " + arg9 + " tex = " + arg10);
        }
    }

    private static int pack(byte[] arg2, int arg3, int arg4, boolean arg5) {
        int v5;
        if(arg5) {
            arg3 += arg4 - 1;
            v5 = -1;
        }
        else {
            v5 = 1;
        }

        int v0 = 0;
        while(true) {
            int v1 = arg4 - 1;
            if(arg4 <= 0) {
                return v0;
            }

            v0 = arg2[arg3] & 255 | v0 << 8;
            arg3 += v5;
            arg4 = v1;
        }

        return v0;
    }

    public void recordVideo(CameraSession arg11, File arg12, VideoTakeCallback arg13, Runnable arg14) {
        if(arg11 == null) {
            return;
        }

        this.threadPool.execute(new Runnable(arg11.cameraInfo.camera, arg11, arg12, arg11.cameraInfo, arg13, arg14) {
            public void run() {
                try {
                    if(this.val$camera == null) {
                        return;
                    }
                }
                catch(Exception v0) {
                    goto label_107;
                }

                try {
                    Camera$Parameters v0_1 = this.val$camera.getParameters();
                    String v1 = this.val$session.getCurrentFlashMode().equals("on") ? "torch" : "off";
                    v0_1.setFlashMode(v1);
                    this.val$camera.setParameters(v0_1);
                    goto label_18;
                }
                catch(Exception v0) {
                    try {
                        FileLog.e(((Throwable)v0));
                    label_18:
                        this.val$camera.unlock();
                    }
                    catch(Exception v0) {
                        goto label_107;
                    }
                }

                try {
                    CameraController.this.recorder = new MediaRecorder();
                    CameraController.this.recorder.setCamera(this.val$camera);
                    CameraController.this.recorder.setVideoSource(1);
                    CameraController.this.recorder.setAudioSource(5);
                    this.val$session.configureRecorder(1, CameraController.this.recorder);
                    CameraController.this.recorder.setOutputFile(this.val$path.getAbsolutePath());
                    CameraController.this.recorder.setMaxFileSize(1073741824);
                    CameraController.this.recorder.setVideoFrameRate(30);
                    CameraController.this.recorder.setMaxDuration(0);
                    Size v0_2 = CameraController.chooseOptimalSize(this.val$info.getPictureSizes(), 720, 480, new Size(16, 9));
                    CameraController.this.recorder.setVideoEncodingBitRate(1800000);
                    CameraController.this.recorder.setVideoSize(v0_2.getWidth(), v0_2.getHeight());
                    CameraController.this.recorder.setOnInfoListener(CameraController.this);
                    CameraController.this.recorder.prepare();
                    CameraController.this.recorder.start();
                    CameraController.this.onVideoTakeCallback = this.val$callback;
                    CameraController.this.recordedFile = this.val$path.getAbsolutePath();
                    if(this.val$onVideoStartRecord == null) {
                        return;
                    }

                    AndroidUtilities.runOnUIThread(this.val$onVideoStartRecord);
                }
                catch(Exception v0) {
                    try {
                        CameraController.this.recorder.release();
                        CameraController.this.recorder = null;
                        FileLog.e(((Throwable)v0));
                    }
                    catch(Exception v0) {
                    label_107:
                        FileLog.e(((Throwable)v0));
                    }
                }
            }
        });
    }

    public void startPreview(CameraSession arg3) {
        if(arg3 == null) {
            return;
        }

        this.threadPool.execute(new Runnable(arg3) {
            @SuppressLint(value={"NewApi"}) public void run() {
                Camera v0 = this.val$session.cameraInfo.camera;
                if(v0 == null) {
                    try {
                        CameraInfo v1_1 = this.val$session.cameraInfo;
                        Camera v2 = Camera.open(this.val$session.cameraInfo.cameraId);
                        v1_1.camera = v2;
                        v0 = v2;
                    label_15:
                        v0.startPreview();
                        return;
                    label_14:
                        goto label_17;
                    }
                    catch(Exception v1) {
                        goto label_14;
                    }
                }

                goto label_15;
            label_17:
                this.val$session.cameraInfo.camera = null;
                if(v0 != null) {
                    v0.release();
                }

                FileLog.e(((Throwable)v1));
            }
        });
    }

    public void stopPreview(CameraSession arg3) {
        if(arg3 == null) {
            return;
        }

        this.threadPool.execute(new Runnable(arg3) {
            @SuppressLint(value={"NewApi"}) public void run() {
                Camera v0 = this.val$session.cameraInfo.camera;
                if(v0 == null) {
                    try {
                        CameraInfo v1_1 = this.val$session.cameraInfo;
                        Camera v2 = Camera.open(this.val$session.cameraInfo.cameraId);
                        v1_1.camera = v2;
                        v0 = v2;
                    label_15:
                        v0.stopPreview();
                        return;
                    label_14:
                        goto label_17;
                    }
                    catch(Exception v1) {
                        goto label_14;
                    }
                }

                goto label_15;
            label_17:
                this.val$session.cameraInfo.camera = null;
                if(v0 != null) {
                    v0.release();
                }

                FileLog.e(((Throwable)v1));
            }
        });
    }

    public void stopVideoRecording(CameraSession arg3, boolean arg4) {
        this.threadPool.execute(new Runnable(arg3, arg4) {
            public void run() {
                MediaRecorder v2;
                MediaRecorder v1;
                Camera v0;
                try {
                    v0 = this.val$session.cameraInfo.camera;
                    v1 = null;
                    if(v0 == null) {
                        goto label_30;
                    }

                    if(CameraController.this.recorder == null) {
                        goto label_30;
                    }

                    v2 = CameraController.this.recorder;
                    CameraController.this.recorder = v1;
                }
                catch(Exception ) {
                    return;
                }

                try {
                    v2.stop();
                }
                catch(Exception v3) {
                    try {
                        FileLog.e(((Throwable)v3));
                    }
                    catch(Exception ) {
                        return;
                    }
                }

                try {
                    v2.release();
                }
                catch(Exception v2_1) {
                    try {
                        FileLog.e(((Throwable)v2_1));
                    }
                    catch(Exception ) {
                        return;
                    }
                }

                try {
                    v0.reconnect();
                    v0.startPreview();
                }
                catch(Exception v2_1) {
                    try {
                        FileLog.e(((Throwable)v2_1));
                    }
                    catch(Exception ) {
                        return;
                    }
                }

                try {
                    this.val$session.stopVideoRecording();
                }
                catch(Exception v2_1) {
                    try {
                        FileLog.e(((Throwable)v2_1));
                    }
                    catch(Exception ) {
                        return;
                    }
                }

                try {
                label_30:
                    Camera$Parameters v2_2 = v0.getParameters();
                    v2_2.setFlashMode("off");
                    v0.setParameters(v2_2);
                    goto label_37;
                }
                catch(Exception v2_1) {
                    try {
                        FileLog.e(((Throwable)v2_1));
                    label_37:
                        CameraController.this.threadPool.execute(new Runnable(v0) {
                            public void run() {
                                try {
                                    Camera$Parameters v0_1 = this.val$camera.getParameters();
                                    v0_1.setFlashMode(this.this$1.val$session.getCurrentFlashMode());
                                    this.val$camera.setParameters(v0_1);
                                }
                                catch(Exception v0) {
                                    FileLog.e(((Throwable)v0));
                                }
                            }
                        });
                        if((this.val$abandon) || CameraController.this.onVideoTakeCallback == null) {
                            CameraController.this.onVideoTakeCallback = ((VideoTakeCallback)v1);
                        }
                        else {
                            CameraController.this.finishRecordingVideo();
                        }

                        return;
                    }
                    catch(Exception ) {
                        return;
                    }
                }
            }
        });
    }

    public boolean takePicture(File arg9, CameraSession arg10, Runnable arg11) {
        if(arg10 == null) {
            return 0;
        }

        CameraInfo v4 = arg10.cameraInfo;
        boolean v5 = arg10.isFlipFront();
        Camera v10 = v4.camera;
        try {
            v10.takePicture(null, null, new Camera$PictureCallback(arg9, v4, v5, arg11) {
                public void onPictureTaken(byte[] arg10, Camera arg11) {
                    Bitmap v1_2;
                    int v11 = ((int)((((float)AndroidUtilities.getPhotoSize())) / AndroidUtilities.density));
                    String v11_1 = String.format(Locale.US, "%s@%d_%d", Utilities.MD5(this.val$path.getAbsolutePath()), Integer.valueOf(v11), Integer.valueOf(v11));
                    float v0 = 1f;
                    try {
                        BitmapFactory$Options v1_1 = new BitmapFactory$Options();
                        v1_1.inJustDecodeBounds = true;
                        BitmapFactory.decodeByteArray(arg10, 0, arg10.length, v1_1);
                        float v2 = Math.max((((float)v1_1.outWidth)) / (((float)AndroidUtilities.getPhotoSize())), (((float)v1_1.outHeight)) / (((float)AndroidUtilities.getPhotoSize())));
                        if(v2 < v0) {
                            v2 = 1f;
                        }

                        v1_1.inJustDecodeBounds = false;
                        v1_1.inSampleSize = ((int)v2);
                        v1_1.inPurgeable = true;
                        v1_2 = BitmapFactory.decodeByteArray(arg10, 0, arg10.length, v1_1);
                    }
                    catch(Throwable v1) {
                        FileLog.e(v1);
                        v1_2 = null;
                    }

                    try {
                        if(this.val$info.frontCamera == 0) {
                            goto label_93;
                        }

                        if(!this.val$flipFront) {
                            goto label_93;
                        }

                        try {
                            Matrix v7 = new Matrix();
                            v7.setRotate(((float)CameraController.getOrientation(arg10)));
                            v7.postScale(-1f, v0);
                            Bitmap v0_2 = Bitmaps.createBitmap(v1_2, 0, 0, v1_2.getWidth(), v1_2.getHeight(), v7, false);
                            if(v0_2 != v1_2) {
                                v1_2.recycle();
                            }

                            FileOutputStream v2_1 = new FileOutputStream(this.val$path);
                            v0_2.compress(Bitmap$CompressFormat.JPEG, 80, ((OutputStream)v2_1));
                            v2_1.flush();
                            v2_1.getFD().sync();
                            v2_1.close();
                            if(v0_2 != null) {
                                ImageLoader.getInstance().putImageToCache(new BitmapDrawable(v0_2), v11_1);
                            }

                            if(this.val$callback != null) {
                                this.val$callback.run();
                            }
                        }
                        catch(Throwable v0_1) {
                            try {
                                FileLog.e(v0_1);
                                goto label_93;
                            }
                            catch(Exception v10) {
                                goto label_108;
                            }
                        }
                    }
                    catch(Exception v10) {
                        goto label_108;
                    }

                    return;
                    try {
                    label_93:
                        FileOutputStream v0_3 = new FileOutputStream(this.val$path);
                        v0_3.write(arg10);
                        v0_3.flush();
                        v0_3.getFD().sync();
                        v0_3.close();
                        if(v1_2 == null) {
                            goto label_109;
                        }

                        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(v1_2), v11_1);
                    }
                    catch(Exception v10) {
                    label_108:
                        FileLog.e(((Throwable)v10));
                    }

                label_109:
                    if(this.val$callback != null) {
                        this.val$callback.run();
                    }
                }
            });
            return 1;
        }
        catch(Exception v9) {
            FileLog.e(((Throwable)v9));
            return 0;
        }
    }
}

