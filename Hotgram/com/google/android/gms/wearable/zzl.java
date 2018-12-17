package com.google.android.gms.wearable;

import com.google.android.gms.common.data.DataHolder;

final class zzl implements Runnable {
    zzl(zzd arg1, DataHolder arg2) {
        this.zzao = arg1;
        this.zzan = arg2;
        super();
    }

    public final void run() {
        DataEventBuffer v0 = new DataEventBuffer(this.zzan);
        try {
            this.zzao.zzak.onDataChanged(v0);
        }
        catch(Throwable v1) {
            v0.release();
            throw v1;
        }

        v0.release();
    }
}

