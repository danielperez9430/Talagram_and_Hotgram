package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzdp implements Runnable {
    zzdp(zzdo arg1, boolean arg2, zzdn arg3, zzdn arg4) {
        this.zzarx = arg1;
        this.zzaru = arg2;
        this.zzarv = arg3;
        this.zzarw = arg4;
        super();
    }

    public final void run() {
        if((this.zzaru) && this.zzarx.zzaro != null) {
            zzdo.zza(this.zzarx, this.zzarx.zzaro);
        }

        int v0 = this.zzarv == null || this.zzarv.zzarm != this.zzarw.zzarm || !zzfk.zzu(this.zzarv.zzarl, this.zzarw.zzarl) || !zzfk.zzu(this.zzarv.zzuw, this.zzarw.zzuw) ? 1 : 0;
        if(v0 != 0) {
            Bundle v0_1 = new Bundle();
            zzdo.zza(this.zzarw, v0_1, true);
            if(this.zzarv != null) {
                if(this.zzarv.zzuw != null) {
                    v0_1.putString("_pn", this.zzarv.zzuw);
                }

                v0_1.putString("_pc", this.zzarv.zzarl);
                v0_1.putLong("_pi", this.zzarv.zzarm);
            }

            this.zzarx.zzge().zza("auto", "_vs", v0_1);
        }

        this.zzarx.zzaro = this.zzarw;
        this.zzarx.zzgg().zzb(this.zzarw);
    }
}

