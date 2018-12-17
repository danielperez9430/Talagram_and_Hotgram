package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver$PendingResult;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

final class zzbl implements Runnable {
    zzbl(zzbj arg1, zzbt arg2, long arg3, Bundle arg5, Context arg6, zzap arg7, BroadcastReceiver$PendingResult arg8) {
        this.zzaoj = arg2;
        this.zzaol = arg3;
        this.zzaom = arg5;
        this.val$context = arg6;
        this.zzaok = arg7;
        this.zzrf = arg8;
        super();
    }

    public final void run() {
        long v0 = this.zzaoj.zzgp().zzanj.get();
        long v2 = this.zzaol;
        long v4 = 0;
        if(v0 > v4 && (v2 >= v0 || v2 <= v4)) {
            v2 = v0 - 1;
        }

        if(v2 > v4) {
            this.zzaom.putLong("click_timestamp", v2);
        }

        this.zzaom.putString("_cis", "referrer broadcast");
        AppMeasurement.getInstance(this.val$context).logEventInternal("auto", "_cmp", this.zzaom);
        this.zzaok.zzjl().zzbx("Install campaign recorded");
        if(this.zzrf != null) {
            this.zzrf.finish();
        }
    }
}

