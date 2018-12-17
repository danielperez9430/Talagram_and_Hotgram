package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzcd implements Runnable {
    zzcd(zzcc arg1, LifecycleCallback arg2, String arg3) {
        this.zzly = arg1;
        this.zzle = arg2;
        this.zzlf = arg3;
        super();
    }

    public final void run() {
        if(zzcc.zza(this.zzly) > 0) {
            LifecycleCallback v0 = this.zzle;
            Bundle v1 = zzcc.zzb(this.zzly) != null ? zzcc.zzb(this.zzly).getBundle(this.zzlf) : null;
            v0.onCreate(v1);
        }

        if(zzcc.zza(this.zzly) >= 2) {
            this.zzle.onStart();
        }

        if(zzcc.zza(this.zzly) >= 3) {
            this.zzle.onResume();
        }

        if(zzcc.zza(this.zzly) >= 4) {
            this.zzle.onStop();
        }

        if(zzcc.zza(this.zzly) >= 5) {
            this.zzle.onDestroy();
        }
    }
}

