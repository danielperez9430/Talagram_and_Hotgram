package com.google.android.gms.vision.face;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.internal.client.zza;
import com.google.android.gms.vision.face.internal.client.zzc;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;

public final class FaceDetector extends Detector {
    public class Builder {
        private int mode;
        private int zzby;
        private boolean zzbz;
        private int zzca;
        private boolean zzcb;
        private float zzcc;
        private final Context zze;

        public Builder(Context arg3) {
            super();
            this.zzby = 0;
            this.zzbz = false;
            this.zzca = 0;
            this.zzcb = true;
            this.mode = 0;
            this.zzcc = -1f;
            this.zze = arg3;
        }

        public FaceDetector build() {
            zzc v0 = new zzc();
            v0.mode = this.mode;
            v0.zzby = this.zzby;
            v0.zzca = this.zzca;
            v0.zzbz = this.zzbz;
            v0.zzcb = this.zzcb;
            v0.zzcc = this.zzcc;
            return new FaceDetector(new zza(this.zze, v0), null);
        }

        public Builder setClassificationType(int arg4) {
            if(arg4 != 0) {
                if(arg4 == 1) {
                }
                else {
                    StringBuilder v2 = new StringBuilder(40);
                    v2.append("Invalid classification type: ");
                    v2.append(arg4);
                    throw new IllegalArgumentException(v2.toString());
                }
            }

            this.zzca = arg4;
            return this;
        }

        public Builder setLandmarkType(int arg4) {
            if(arg4 != 0) {
                if(arg4 == 1) {
                }
                else {
                    StringBuilder v2 = new StringBuilder(34);
                    v2.append("Invalid landmark type: ");
                    v2.append(arg4);
                    throw new IllegalArgumentException(v2.toString());
                }
            }

            this.zzby = arg4;
            return this;
        }

        public Builder setMinFaceSize(float arg4) {
            if(arg4 >= 0f && arg4 <= 1f) {
                this.zzcc = arg4;
                return this;
            }

            StringBuilder v2 = new StringBuilder(47);
            v2.append("Invalid proportional face size: ");
            v2.append(arg4);
            throw new IllegalArgumentException(v2.toString());
        }

        public Builder setMode(int arg4) {
            switch(arg4) {
                case 0: 
                case 1: {
                    goto label_11;
                }
            }

            StringBuilder v2 = new StringBuilder(25);
            v2.append("Invalid mode: ");
            v2.append(arg4);
            throw new IllegalArgumentException(v2.toString());
        label_11:
            this.mode = arg4;
            return this;
        }

        public Builder setProminentFaceOnly(boolean arg1) {
            this.zzbz = arg1;
            return this;
        }

        public Builder setTrackingEnabled(boolean arg1) {
            this.zzcb = arg1;
            return this;
        }
    }

    public static final int ACCURATE_MODE = 1;
    public static final int ALL_CLASSIFICATIONS = 1;
    public static final int ALL_LANDMARKS = 1;
    public static final int FAST_MODE;
    public static final int NO_CLASSIFICATIONS;
    public static final int NO_LANDMARKS;
    private final Object lock;
    private final com.google.android.gms.vision.zzc zzbv;
    @GuardedBy(value="lock") private final zza zzbw;
    @GuardedBy(value="lock") private boolean zzbx;

    private FaceDetector() {
        super();
        this.zzbv = new com.google.android.gms.vision.zzc();
        this.lock = new Object();
        this.zzbx = true;
        throw new IllegalStateException("Default constructor called");
    }

    private FaceDetector(zza arg2) {
        super();
        this.zzbv = new com.google.android.gms.vision.zzc();
        this.lock = new Object();
        this.zzbx = true;
        this.zzbw = arg2;
    }

    FaceDetector(zza arg1, com.google.android.gms.vision.face.zza arg2) {
        this(arg1);
    }

    public final SparseArray detect(Frame arg9) {
        Face[] v9_1;
        if(arg9 == null) {
            goto label_42;
        }

        ByteBuffer v0 = arg9.getGrayscaleImageData();
        Object v1 = this.lock;
        __monitor_enter(v1);
        try {
            if(!this.zzbx) {
                goto label_35;
            }

            v9_1 = this.zzbw.zzb(v0, zzm.zzc(arg9));
            __monitor_exit(v1);
        }
        catch(Throwable v9) {
            goto label_40;
        }

        HashSet v0_1 = new HashSet();
        SparseArray v1_1 = new SparseArray(v9_1.length);
        int v2 = v9_1.length;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v2) {
            Face v5 = v9_1[v3];
            int v6 = v5.getId();
            v4 = Math.max(v4, v6);
            if(((Set)v0_1).contains(Integer.valueOf(v6))) {
                v6 = v4 + 1;
                v4 = v6;
            }

            ((Set)v0_1).add(Integer.valueOf(v6));
            v1_1.append(this.zzbv.zzb(v6), v5);
            ++v3;
        }

        return v1_1;
        try {
        label_35:
            throw new RuntimeException("Cannot use detector after release()");
        label_40:
            __monitor_exit(v1);
        }
        catch(Throwable v9) {
            goto label_40;
        }

        throw v9;
    label_42:
        throw new IllegalArgumentException("No frame supplied.");
    }

    protected final void finalize() {
        Object v0_1;
        try {
            v0_1 = this.lock;
            __monitor_enter(v0_1);
        }
        catch(Throwable v0) {
            goto label_15;
        }

        try {
            if(this.zzbx) {
                Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                ((Detector)this).release();
            }

            __monitor_exit(v0_1);
        }
        catch(Throwable v1) {
            try {
            label_12:
                __monitor_exit(v0_1);
            }
            catch(Throwable v1) {
                goto label_12;
            }

            try {
                throw v1;
            }
            catch(Throwable v0) {
            label_15:
                super.finalize();
                throw v0;
            }
        }

        super.finalize();
    }

    public final boolean isOperational() {
        return this.zzbw.isOperational();
    }

    public final void release() {
        super.release();
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            if(!this.zzbx) {
                __monitor_exit(v0);
                return;
            }

            this.zzbw.zzo();
            this.zzbx = false;
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_14;
        }

        throw v1;
    }

    public final boolean setFocus(int arg3) {
        arg3 = this.zzbv.zzc(arg3);
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            if(this.zzbx) {
                __monitor_exit(v0);
                return this.zzbw.zzd(arg3);
            }

            throw new RuntimeException("Cannot use detector after release()");
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_15;
        }

        throw v3;
    }
}

