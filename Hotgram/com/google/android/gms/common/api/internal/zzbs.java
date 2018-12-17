package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzbs implements Runnable {
    zzbs(zzbr arg1, LifecycleCallback arg2, String arg3) {
        this.zzlg = arg1;
        this.zzle = arg2;
        this.zzlf = arg3;
        super();
    }

    public final void run() {
        if(zzbr.zza(this.zzlg) > 0) {
            LifecycleCallback v0 = this.zzle;
            Bundle v1 = zzbr.zzb(this.zzlg) != null ? zzbr.zzb(this.zzlg).getBundle(this.zzlf) : null;
            v0.onCreate(v1);
        }

        if(zzbr.zza(this.zzlg) >= 2) {
            this.zzle.onStart();
        }

        if(zzbr.zza(this.zzlg) >= 3) {
            this.zzle.onResume();
        }

        if(zzbr.zza(this.zzlg) >= 4) {
            this.zzle.onStop();
        }

        if(zzbr.zza(this.zzlg) >= 5) {
            this.zzle.onDestroy();
        }
    }
}

