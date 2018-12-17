package com.google.android.gms.measurement;

import android.content.BroadcastReceiver$PendingResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzbj;
import com.google.android.gms.measurement.internal.zzbm;

public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements zzbm {
    private zzbj zzadq;

    public AppMeasurementInstallReferrerReceiver() {
        super();
    }

    public final BroadcastReceiver$PendingResult doGoAsync() {
        return this.goAsync();
    }

    public final void doStartService(Context arg1, Intent arg2) {
    }

    public final void onReceive(Context arg2, Intent arg3) {
        if(this.zzadq == null) {
            this.zzadq = new zzbj(((zzbm)this));
        }

        this.zzadq.onReceive(arg2, arg3);
    }
}

