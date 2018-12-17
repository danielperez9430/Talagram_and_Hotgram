package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera$Parameters;
import android.hardware.Camera$PictureCallback;
import android.hardware.Camera$PreviewCallback;
import android.hardware.Camera$ShutterCallback;
import android.hardware.Camera$Size;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class CameraSource {
    public class Builder {
        private final Detector zzr;
        private CameraSource zzs;

        public Builder(Context arg3, Detector arg4) {
            super();
            this.zzs = new CameraSource(null);
            if(arg3 != null) {
                if(arg4 != null) {
                    this.zzr = arg4;
                    CameraSource.zza(this.zzs, arg3);
                    return;
                }

                throw new IllegalArgumentException("No detector supplied.");
            }

            throw new IllegalArgumentException("No context supplied.");
        }

        public CameraSource build() {
            CameraSource v0 = this.zzs;
            CameraSource v2 = this.zzs;
            v2.getClass();
            CameraSource.zza(v0, new zzb(v2, this.zzr));
            return this.zzs;
        }

        public Builder setAutoFocusEnabled(boolean arg2) {
            CameraSource.zza(this.zzs, arg2);
            return this;
        }

        public Builder setFacing(int arg4) {
            if(arg4 != 0) {
                if(arg4 == 1) {
                }
                else {
                    StringBuilder v2 = new StringBuilder(27);
                    v2.append("Invalid camera: ");
                    v2.append(arg4);
                    throw new IllegalArgumentException(v2.toString());
                }
            }

            CameraSource.zzc(this.zzs, arg4);
            return this;
        }

        public Builder setRequestedFps(float arg4) {
            if(arg4 > 0f) {
                CameraSource.zza(this.zzs, arg4);
                return this;
            }

            StringBuilder v2 = new StringBuilder(28);
            v2.append("Invalid fps: ");
            v2.append(arg4);
            throw new IllegalArgumentException(v2.toString());
        }

        public Builder setRequestedPreviewSize(int arg4, int arg5) {
            if(arg4 > 0) {
                int v0 = 1000000;
                if(arg4 <= v0 && arg5 > 0 && arg5 <= v0) {
                    CameraSource.zza(this.zzs, arg4);
                    CameraSource.zzb(this.zzs, arg5);
                    return this;
                }
            }

            StringBuilder v2 = new StringBuilder(45);
            v2.append("Invalid preview size: ");
            v2.append(arg4);
            v2.append("x");
            v2.append(arg5);
            throw new IllegalArgumentException(v2.toString());
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] arg1);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    final class zza implements Camera$PreviewCallback {
        zza(CameraSource arg1, com.google.android.gms.vision.zza arg2) {
            this(arg1);
        }

        private zza(CameraSource arg1) {
            this.zzt = arg1;
            super();
        }

        public final void onPreviewFrame(byte[] arg2, Camera arg3) {
            CameraSource.zzc(this.zzt).zza(arg2, arg3);
        }
    }

    final class zzb implements Runnable {
        private final Object lock;
        private Detector zzr;
        private long zzu;
        private boolean zzv;
        private long zzw;
        private int zzx;
        private ByteBuffer zzy;

        zzb(CameraSource arg3, Detector arg4) {
            this.zzt = arg3;
            super();
            this.zzu = SystemClock.elapsedRealtime();
            this.lock = new Object();
            this.zzv = true;
            this.zzx = 0;
            this.zzr = arg4;
        }

        @SuppressLint(value={"Assert"}) final void release() {
            this.zzr.release();
            this.zzr = null;
        }

        @SuppressLint(value={"InlinedApi"}) public final void run() {
            ByteBuffer v2;
            Frame v1_2;
            Object v0;
            while(true) {
                v0 = this.lock;
                __monitor_enter(v0);
                try {
                    while(true) {
                        if((this.zzv) && this.zzy == null) {
                            try {
                                this.lock.wait();
                                continue;
                            }
                            catch(InterruptedException v1_1) {
                                try {
                                    Log.d("CameraSource", "Frame processing loop terminated.", ((Throwable)v1_1));
                                    __monitor_exit(v0);
                                    return;
                                label_15:
                                    if(!this.zzv) {
                                        __monitor_exit(v0);
                                        return;
                                    }

                                    v1_2 = new com.google.android.gms.vision.Frame$Builder().setImageData(this.zzy, CameraSource.zzf(this.zzt).getWidth(), CameraSource.zzf(this.zzt).getHeight(), 17).setId(this.zzx).setTimestampMillis(this.zzw).setRotation(CameraSource.zze(this.zzt)).build();
                                    v2 = this.zzy;
                                    this.zzy = null;
                                    __monitor_exit(v0);
                                    break;
                                }
                                catch(Throwable v1) {
                                    goto label_62;
                                }
                            }
                        }

                        goto label_15;
                    }
                }
                catch(Throwable v1) {
                    break;
                }

                try {
                    this.zzr.receiveFrame(v1_2);
                }
                catch(Throwable v0_1) {
                }
                catch(Exception v0_2) {
                    try {
                        Log.e("CameraSource", "Exception thrown from receiver.", ((Throwable)v0_2));
                    }
                    catch(Throwable v0_1) {
                        CameraSource.zzb(this.zzt).addCallbackBuffer(v2.array());
                        throw v0_1;
                    }
                }

                CameraSource.zzb(this.zzt).addCallbackBuffer(v2.array());
            }

            try {
            label_62:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_62;
            }

            throw v1;
        }

        final void setActive(boolean arg2) {
            Object v0 = this.lock;
            __monitor_enter(v0);
            try {
                this.zzv = arg2;
                this.lock.notifyAll();
                __monitor_exit(v0);
                return;
            label_8:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_8;
            }

            throw v2;
        }

        final void zza(byte[] arg6, Camera arg7) {
            Object v0 = this.lock;
            __monitor_enter(v0);
            try {
                if(this.zzy != null) {
                    arg7.addCallbackBuffer(this.zzy.array());
                    this.zzy = null;
                }

                if(!CameraSource.zzd(this.zzt).containsKey(arg6)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    __monitor_exit(v0);
                    return;
                }

                this.zzw = SystemClock.elapsedRealtime() - this.zzu;
                ++this.zzx;
                this.zzy = CameraSource.zzd(this.zzt).get(arg6);
                this.lock.notifyAll();
                __monitor_exit(v0);
                return;
            label_35:
                __monitor_exit(v0);
            }
            catch(Throwable v6) {
                goto label_35;
            }

            throw v6;
        }
    }

    final class zzc implements Camera$PictureCallback {
        private PictureCallback zzz;

        zzc(CameraSource arg1, com.google.android.gms.vision.zza arg2) {
            this(arg1);
        }

        private zzc(CameraSource arg1) {
            this.zzt = arg1;
            super();
        }

        public final void onPictureTaken(byte[] arg1, Camera arg2) {
            if(this.zzz != null) {
                this.zzz.onPictureTaken(arg1);
            }

            Object v1 = CameraSource.zza(this.zzt);
            __monitor_enter(v1);
            try {
                if(CameraSource.zzb(this.zzt) != null) {
                    CameraSource.zzb(this.zzt).startPreview();
                }

                __monitor_exit(v1);
                return;
            label_16:
                __monitor_exit(v1);
            }
            catch(Throwable v2) {
                goto label_16;
            }

            throw v2;
        }

        static PictureCallback zza(zzc arg0, PictureCallback arg1) {
            arg0.zzz = arg1;
            return arg1;
        }
    }

    final class zzd implements Camera$ShutterCallback {
        private ShutterCallback zzaa;

        zzd(com.google.android.gms.vision.zza arg1) {
            this();
        }

        private zzd() {
            super();
        }

        public final void onShutter() {
            if(this.zzaa != null) {
                this.zzaa.onShutter();
            }
        }

        static ShutterCallback zza(zzd arg0, ShutterCallback arg1) {
            arg0.zzaa = arg1;
            return arg1;
        }
    }

    @VisibleForTesting final class zze {
        private Size zzab;
        private Size zzac;

        public zze(Camera$Size arg3, @Nullable Camera$Size arg4) {
            super();
            this.zzab = new Size(arg3.width, arg3.height);
            if(arg4 != null) {
                this.zzac = new Size(arg4.width, arg4.height);
            }
        }

        public final Size zzb() {
            return this.zzab;
        }

        @Nullable public final Size zzc() {
            return this.zzac;
        }
    }

    @SuppressLint(value={"InlinedApi"}) public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint(value={"InlinedApi"}) public static final int CAMERA_FACING_FRONT = 1;
    private int facing;
    private int rotation;
    private Context zze;
    private final Object zzf;
    @GuardedBy(value="cameraLock") private Camera zzg;
    private Size zzh;
    private float zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;
    private SurfaceTexture zzm;
    private boolean zzn;
    private Thread zzo;
    private zzb zzp;
    private Map zzq;

    private CameraSource() {
        super();
        this.zzf = new Object();
        this.facing = 0;
        this.zzi = 30f;
        this.zzj = 1024;
        this.zzk = 768;
        this.zzl = false;
        this.zzq = new HashMap();
    }

    CameraSource(com.google.android.gms.vision.zza arg1) {
        this();
    }

    public int getCameraFacing() {
        return this.facing;
    }

    public Size getPreviewSize() {
        return this.zzh;
    }

    public void release() {
        Object v0 = this.zzf;
        __monitor_enter(v0);
        try {
            this.stop();
            this.zzp.release();
            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    public CameraSource start() {
        Object v0 = this.zzf;
        __monitor_enter(v0);
        try {
            if(this.zzg != null) {
                __monitor_exit(v0);
                return this;
            }

            this.zzg = this.zza();
            this.zzm = new SurfaceTexture(100);
            this.zzg.setPreviewTexture(this.zzm);
            this.zzn = true;
            this.zzg.startPreview();
            this.zzo = new Thread(this.zzp);
            this.zzp.setActive(true);
            this.zzo.start();
            __monitor_exit(v0);
            return this;
        label_30:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_30;
        }

        throw v1;
    }

    public CameraSource start(SurfaceHolder arg3) {
        Object v0 = this.zzf;
        __monitor_enter(v0);
        try {
            if(this.zzg != null) {
                __monitor_exit(v0);
                return this;
            }

            this.zzg = this.zza();
            this.zzg.setPreviewDisplay(arg3);
            this.zzg.startPreview();
            this.zzo = new Thread(this.zzp);
            this.zzp.setActive(true);
            this.zzo.start();
            this.zzn = false;
            __monitor_exit(v0);
            return this;
        label_26:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_26;
        }

        throw v3;
    }

    public void stop() {
        Object v0 = this.zzf;
        __monitor_enter(v0);
        try {
            this.zzp.setActive(false);
            Thread v2 = null;
            if(this.zzo != null) {
                try {
                    this.zzo.join();
                }
                catch(InterruptedException ) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }

                this.zzo = v2;
            }

            if(this.zzg == null) {
                goto label_45;
            }

            this.zzg.stopPreview();
            this.zzg.setPreviewCallbackWithBuffer(((Camera$PreviewCallback)v2));
            try {
                if(this.zzn) {
                    this.zzg.setPreviewTexture(((SurfaceTexture)v2));
                    goto label_42;
                }

                this.zzg.setPreviewDisplay(((SurfaceHolder)v2));
                goto label_42;
            }
            catch(Exception v1_1) {
                try {
                    String v1_2 = String.valueOf(v1_1);
                    StringBuilder v5 = new StringBuilder(String.valueOf(v1_2).length() + 32);
                    v5.append("Failed to clear camera preview: ");
                    v5.append(v1_2);
                    Log.e("CameraSource", v5.toString());
                label_42:
                    this.zzg.release();
                    this.zzg = ((Camera)v2);
                label_45:
                    this.zzq.clear();
                    __monitor_exit(v0);
                    return;
                label_50:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_50;
                }
            }
        }
        catch(Throwable v1) {
            goto label_50;
        }

        throw v1;
    }

    public void takePicture(ShutterCallback arg4, PictureCallback arg5) {
        Object v0 = this.zzf;
        __monitor_enter(v0);
        try {
            if(this.zzg != null) {
                zzd v1 = new zzd(null);
                zzd.zza(v1, arg4);
                zzc v4_1 = new zzc(this, null);
                zzc.zza(v4_1, arg5);
                this.zzg.takePicture(((Camera$ShutterCallback)v1), null, null, ((Camera$PictureCallback)v4_1));
            }

            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_16;
        }

        throw v4;
    }

    static float zza(CameraSource arg0, float arg1) {
        arg0.zzi = arg1;
        return arg1;
    }

    static int zza(CameraSource arg0, int arg1) {
        arg0.zzj = arg1;
        return arg1;
    }

    static Context zza(CameraSource arg0, Context arg1) {
        arg0.zze = arg1;
        return arg1;
    }

    @SuppressLint(value={"InlinedApi"}) private final Camera zza() {
        Object v7_1;
        Size v1_2;
        Iterator v5_3;
        Object v12;
        Object v9;
        Iterator v8;
        ArrayList v7;
        List v5_2;
        List v6;
        int v4;
        int v1_1;
        Camera v0_1;
        int v5;
        int v0 = this.facing;
        Camera$CameraInfo v1 = new Camera$CameraInfo();
        int v2 = 0;
        int v3 = 0;
        while(true) {
            v5 = -1;
            if(v3 < Camera.getNumberOfCameras()) {
                Camera.getCameraInfo(v3, v1);
                if(v1.facing == v0) {
                }
                else {
                    ++v3;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_15;
        }

        v3 = -1;
    label_15:
        if(v3 != v5) {
            v0_1 = Camera.open(v3);
            v1_1 = this.zzj;
            v4 = this.zzk;
            Camera$Parameters v5_1 = v0_1.getParameters();
            v6 = v5_1.getSupportedPreviewSizes();
            v5_2 = v5_1.getSupportedPictureSizes();
            v7 = new ArrayList();
            v8 = v6.iterator();
        }
        else {
            throw new IOException("Could not find requested camera.");
        }

    label_25:
        while(v8.hasNext()) {
            v9 = v8.next();
            float v10 = (((float)((Camera$Size)v9).width)) / (((float)((Camera$Size)v9).height));
            Iterator v11 = v5_2.iterator();
            do {
                if(!v11.hasNext()) {
                    goto label_25;
                }

                v12 = v11.next();
            }
            while(Math.abs(v10 - (((float)((Camera$Size)v12).width)) / (((float)((Camera$Size)v12).height))) >= 0.01f);

            ((List)v7).add(new zze(((Camera$Size)v9), ((Camera$Size)v12)));
        }

        Camera$Size v8_1 = null;
        if(((List)v7).size() == 0) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            v5_3 = v6.iterator();
            while(v5_3.hasNext()) {
                ((List)v7).add(new zze(v5_3.next(), v8_1));
            }
        }

        v5 = v7.size();
        int v6_1 = 2147483647;
        Object v11_1 = v8_1;
        int v9_1 = 0;
        int v10_1;
        for(v10_1 = 2147483647; v9_1 < v5; v10_1 = v14) {
            v12 = v7.get(v9_1);
            ++v9_1;
            Size v13 = ((zze)v12).zzb();
            int v14 = Math.abs(v13.getWidth() - v1_1) + Math.abs(v13.getHeight() - v4);
            if(v14 >= v10_1) {
                continue;
            }

            v11_1 = v12;
        }

        if(v11_1 != null) {
            v1_2 = ((zze)v11_1).zzc();
            this.zzh = ((zze)v11_1).zzb();
            v4 = ((int)(this.zzi * 1000f));
            v5_3 = v0_1.getParameters().getSupportedPreviewFpsRange().iterator();
            v7_1 = v8_1;
        }
        else {
            throw new IOException("Could not find suitable preview size.");
        }

        while(v5_3.hasNext()) {
            v9 = v5_3.next();
            int v11_2 = Math.abs(v4 - v9[0]) + Math.abs(v4 - v9[1]);
            if(v11_2 >= v6_1) {
                continue;
            }

            v7_1 = v9;
            v6_1 = v11_2;
        }

        if(v7_1 != null) {
            Camera$Parameters v4_1 = v0_1.getParameters();
            if(v1_2 != null) {
                v4_1.setPictureSize(v1_2.getWidth(), v1_2.getHeight());
            }

            v4_1.setPreviewSize(this.zzh.getWidth(), this.zzh.getHeight());
            v4_1.setPreviewFpsRange(v7_1[0], v7_1[1]);
            v4_1.setPreviewFormat(17);
            v1_1 = this.zze.getSystemService("window").getDefaultDisplay().getRotation();
            switch(v1_1) {
                case 0: {
                    break;
                }
                case 1: {
                    v2 = 90;
                    break;
                }
                case 2: {
                    v2 = 180;
                    break;
                }
                case 3: {
                    v2 = 270;
                    break;
                }
                default: {
                    StringBuilder v7_2 = new StringBuilder(31);
                    v7_2.append("Bad rotation value: ");
                    v7_2.append(v1_1);
                    Log.e("CameraSource", v7_2.toString());
                    break;
                }
            }

            v1 = new Camera$CameraInfo();
            Camera.getCameraInfo(v3, v1);
            if(v1.facing == 1) {
                v1_1 = (v1.orientation + v2) % 360;
                v2 = (360 - v1_1) % 360;
            }
            else {
                v1_1 = (v1.orientation - v2 + 360) % 360;
                v2 = v1_1;
            }

            this.rotation = v1_1 / 90;
            v0_1.setDisplayOrientation(v2);
            v4_1.setRotation(v1_1);
            if(this.zzl) {
                if(v4_1.getSupportedFocusModes().contains("continuous-video")) {
                    v4_1.setFocusMode("continuous-video");
                }
                else {
                    Log.i("CameraSource", "Camera auto focus is not supported on this device.");
                }
            }

            v0_1.setParameters(v4_1);
            v0_1.setPreviewCallbackWithBuffer(new zza(this, ((com.google.android.gms.vision.zza)v8_1)));
            v0_1.addCallbackBuffer(this.zza(this.zzh));
            v0_1.addCallbackBuffer(this.zza(this.zzh));
            v0_1.addCallbackBuffer(this.zza(this.zzh));
            v0_1.addCallbackBuffer(this.zza(this.zzh));
            return v0_1;
        }

        throw new IOException("Could not find suitable preview frames per second range.");
    }

    @SuppressLint(value={"InlinedApi"}) private final byte[] zza(Size arg5) {
        double v0 = ((double)(((long)(arg5.getHeight() * arg5.getWidth() * ImageFormat.getBitsPerPixel(17)))));
        Double.isNaN(v0);
        byte[] v5 = new byte[(((int)Math.ceil(v0 / 8))) + 1];
        ByteBuffer v0_1 = ByteBuffer.wrap(v5);
        if((v0_1.hasArray()) && v0_1.array() == v5) {
            this.zzq.put(v5, v0_1);
            return v5;
        }

        throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }

    static zzb zza(CameraSource arg0, zzb arg1) {
        arg0.zzp = arg1;
        return arg1;
    }

    static Object zza(CameraSource arg0) {
        return arg0.zzf;
    }

    static boolean zza(CameraSource arg0, boolean arg1) {
        arg0.zzl = arg1;
        return arg1;
    }

    static int zzb(CameraSource arg0, int arg1) {
        arg0.zzk = arg1;
        return arg1;
    }

    static Camera zzb(CameraSource arg0) {
        return arg0.zzg;
    }

    static int zzc(CameraSource arg0, int arg1) {
        arg0.facing = arg1;
        return arg1;
    }

    static zzb zzc(CameraSource arg0) {
        return arg0.zzp;
    }

    static Map zzd(CameraSource arg0) {
        return arg0.zzq;
    }

    static int zze(CameraSource arg0) {
        return arg0.rotation;
    }

    static Size zzf(CameraSource arg0) {
        return arg0.zzh;
    }
}

