package com.google.android.gms.vision;

import android.util.SparseArray;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MultiProcessor implements Processor {
    public class Builder {
        private MultiProcessor zzbb;

        public Builder(Factory arg3) {
            super();
            this.zzbb = new MultiProcessor(null);
            if(arg3 != null) {
                MultiProcessor.zza(this.zzbb, arg3);
                return;
            }

            throw new IllegalArgumentException("No factory supplied.");
        }

        public MultiProcessor build() {
            return this.zzbb;
        }

        public Builder setMaxGapFrames(int arg4) {
            if(arg4 >= 0) {
                MultiProcessor.zza(this.zzbb, arg4);
                return this;
            }

            StringBuilder v2 = new StringBuilder(28);
            v2.append("Invalid max gap: ");
            v2.append(arg4);
            throw new IllegalArgumentException(v2.toString());
        }
    }

    public interface Factory {
        Tracker create(Object arg1);
    }

    final class zza {
        private Tracker zzak;
        private int zzao;

        zza(MultiProcessor arg1, zze arg2) {
            this(arg1);
        }

        private zza(MultiProcessor arg1) {
            super();
            this.zzao = 0;
        }

        static Tracker zza(zza arg0, Tracker arg1) {
            arg0.zzak = arg1;
            return arg1;
        }

        static Tracker zza(zza arg0) {
            return arg0.zzak;
        }

        static int zza(zza arg0, int arg1) {
            arg0.zzao = 0;
            return 0;
        }

        static int zzb(zza arg2) {
            int v0 = arg2.zzao;
            arg2.zzao = v0 + 1;
            return v0;
        }

        static int zzc(zza arg0) {
            return arg0.zzao;
        }
    }

    private int zzal;
    private Factory zzaz;
    private SparseArray zzba;

    private MultiProcessor() {
        super();
        this.zzba = new SparseArray();
        this.zzal = 3;
    }

    MultiProcessor(zze arg1) {
        this();
    }

    public void receiveDetections(Detections arg9) {
        Object v4;
        int v3;
        SparseArray v0 = arg9.getDetectedItems();
        int v2;
        for(v2 = 0; v2 < v0.size(); ++v2) {
            v3 = v0.keyAt(v2);
            v4 = v0.valueAt(v2);
            if(this.zzba.get(v3) == null) {
                zza v5 = new zza(this, null);
                zza.zza(v5, this.zzaz.create(v4));
                zza.zza(v5).onNewItem(v3, v4);
                this.zzba.append(v3, v5);
            }
        }

        v0 = arg9.getDetectedItems();
        HashSet v2_1 = new HashSet();
        for(v3 = 0; v3 < this.zzba.size(); ++v3) {
            int v4_1 = this.zzba.keyAt(v3);
            if(v0.get(v4_1) == null) {
                Object v5_1 = this.zzba.valueAt(v3);
                zza.zzb(((zza)v5_1));
                if(zza.zzc(((zza)v5_1)) >= this.zzal) {
                    zza.zza(((zza)v5_1)).onDone();
                    ((Set)v2_1).add(Integer.valueOf(v4_1));
                }
                else {
                    zza.zza(((zza)v5_1)).onMissing(arg9);
                }
            }
        }

        Iterator v0_1 = ((Set)v2_1).iterator();
        while(v0_1.hasNext()) {
            this.zzba.delete(v0_1.next().intValue());
        }

        v0 = arg9.getDetectedItems();
        for(v2 = 0; v2 < v0.size(); ++v2) {
            v3 = v0.keyAt(v2);
            v4 = v0.valueAt(v2);
            Object v3_1 = this.zzba.get(v3);
            zza.zza(((zza)v3_1), 0);
            zza.zza(((zza)v3_1)).onUpdate(arg9, v4);
        }
    }

    public void release() {
        int v0;
        for(v0 = 0; v0 < this.zzba.size(); ++v0) {
            zza.zza(this.zzba.valueAt(v0)).onDone();
        }

        this.zzba.clear();
    }

    static int zza(MultiProcessor arg0, int arg1) {
        arg0.zzal = arg1;
        return arg1;
    }

    static Factory zza(MultiProcessor arg0, Factory arg1) {
        arg0.zzaz = arg1;
        return arg1;
    }
}

