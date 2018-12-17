package com.google.android.gms.vision;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiDetector extends Detector {
    public class Builder {
        private MultiDetector zzay;

        public Builder() {
            super();
            this.zzay = new MultiDetector(null);
        }

        public Builder add(Detector arg2) {
            MultiDetector.zza(this.zzay).add(arg2);
            return this;
        }

        public MultiDetector build() {
            if(MultiDetector.zza(this.zzay).size() != 0) {
                return this.zzay;
            }

            throw new RuntimeException("No underlying detectors added to MultiDetector.");
        }
    }

    private List zzax;

    private MultiDetector() {
        super();
        this.zzax = new ArrayList();
    }

    MultiDetector(zzd arg1) {
        this();
    }

    public SparseArray detect(Frame arg7) {
        int v4;
        SparseArray v0 = new SparseArray();
        Iterator v1 = this.zzax.iterator();
    label_4:
        if(v1.hasNext()) {
            SparseArray v2 = v1.next().detect(arg7);
            int v3;
            for(v3 = 0; true; ++v3) {
                if(v3 >= v2.size()) {
                    goto label_4;
                }

                v4 = v2.keyAt(v3);
                if(v0.get(v4) != null) {
                    break;
                }

                v0.append(v4, v2.valueAt(v3));
            }

            StringBuilder v1_1 = new StringBuilder(104);
            v1_1.append("Detection ID overlap for id = ");
            v1_1.append(v4);
            v1_1.append("  This means that one of the detectors is not using global IDs.");
            throw new IllegalStateException(v1_1.toString());
        }

        return v0;
    }

    public boolean isOperational() {
        Iterator v0 = this.zzax.iterator();
        do {
            if(!v0.hasNext()) {
                return 1;
            }
        }
        while(v0.next().isOperational());

        return 0;
    }

    public void receiveFrame(Frame arg3) {
        Iterator v0 = this.zzax.iterator();
        while(v0.hasNext()) {
            v0.next().receiveFrame(arg3);
        }
    }

    public void release() {
        Iterator v0 = this.zzax.iterator();
        while(v0.hasNext()) {
            v0.next().release();
        }

        this.zzax.clear();
    }

    public void setProcessor(Processor arg2) {
        throw new UnsupportedOperationException("MultiDetector.setProcessor is not supported.  You should set a processor instance on each underlying detector instead.");
    }

    static List zza(MultiDetector arg0) {
        return arg0.zzax;
    }
}

