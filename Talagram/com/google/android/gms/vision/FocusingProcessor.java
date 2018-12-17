package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;

public abstract class FocusingProcessor implements Processor {
    private Tracker zzak;
    private int zzal;
    private boolean zzam;
    private int zzan;
    private int zzao;
    private Detector zzr;

    public FocusingProcessor(Detector arg2, Tracker arg3) {
        super();
        this.zzal = 3;
        this.zzam = false;
        this.zzao = 0;
        this.zzr = arg2;
        this.zzak = arg3;
    }

    public void receiveDetections(Detections arg5) {
        SparseArray v0 = arg5.getDetectedItems();
        if(v0.size() == 0) {
            if(this.zzao == this.zzal) {
                this.zzak.onDone();
                this.zzam = false;
            }
            else {
                this.zzak.onMissing(arg5);
            }

            ++this.zzao;
            return;
        }

        this.zzao = 0;
        if(this.zzam) {
            Object v1 = v0.get(this.zzan);
            if(v1 != null) {
                this.zzak.onUpdate(arg5, v1);
                return;
            }
            else {
                this.zzak.onDone();
                this.zzam = false;
            }
        }

        int v1_1 = this.selectFocus(arg5);
        Object v0_1 = v0.get(v1_1);
        if(v0_1 == null) {
            StringBuilder v2 = new StringBuilder(35);
            v2.append("Invalid focus selected: ");
            v2.append(v1_1);
            Log.w("FocusingProcessor", v2.toString());
            return;
        }

        this.zzam = true;
        this.zzan = v1_1;
        this.zzr.setFocus(this.zzan);
        this.zzak.onNewItem(this.zzan, v0_1);
        this.zzak.onUpdate(arg5, v0_1);
    }

    public void release() {
        this.zzak.onDone();
    }

    public abstract int selectFocus(Detections arg1);

    protected final void zza(int arg4) {
        if(arg4 >= 0) {
            this.zzal = arg4;
            return;
        }

        StringBuilder v2 = new StringBuilder(28);
        v2.append("Invalid max gap: ");
        v2.append(arg4);
        throw new IllegalArgumentException(v2.toString());
    }
}

