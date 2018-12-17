package com.google.android.gms.vision;

import android.util.SparseArray;
import javax.annotation.concurrent.GuardedBy;

public abstract class Detector {
    public class Detections {
        private final SparseArray zzaf;
        private final Metadata zzag;
        private final boolean zzah;

        public Detections(SparseArray arg1, Metadata arg2, boolean arg3) {
            super();
            this.zzaf = arg1;
            this.zzag = arg2;
            this.zzah = arg3;
        }

        public boolean detectorIsOperational() {
            return this.zzah;
        }

        public SparseArray getDetectedItems() {
            return this.zzaf;
        }

        public Metadata getFrameMetadata() {
            return this.zzag;
        }
    }

    public interface Processor {
        void receiveDetections(Detections arg1);

        void release();
    }

    private final Object zzad;
    @GuardedBy(value="processorLock") private Processor zzae;

    public Detector() {
        super();
        this.zzad = new Object();
    }

    public abstract SparseArray detect(Frame arg1);

    public boolean isOperational() {
        return 1;
    }

    public void receiveFrame(Frame arg4) {
        Metadata v0 = new Metadata(arg4.getMetadata());
        v0.zzd();
        Detections v2 = new Detections(this.detect(arg4), v0, this.isOperational());
        Object v4 = this.zzad;
        __monitor_enter(v4);
        try {
            if(this.zzae != null) {
                this.zzae.receiveDetections(v2);
                __monitor_exit(v4);
                return;
            }

            throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
        label_21:
            __monitor_exit(v4);
        }
        catch(Throwable v0_1) {
            goto label_21;
        }

        throw v0_1;
    }

    public void release() {
        Object v0 = this.zzad;
        __monitor_enter(v0);
        try {
            if(this.zzae != null) {
                this.zzae.release();
                this.zzae = null;
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }

    public boolean setFocus(int arg1) {
        return 1;
    }

    public void setProcessor(Processor arg3) {
        Object v0 = this.zzad;
        __monitor_enter(v0);
        try {
            if(this.zzae != null) {
                this.zzae.release();
            }

            this.zzae = arg3;
            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_10;
        }

        throw v3;
    }
}

