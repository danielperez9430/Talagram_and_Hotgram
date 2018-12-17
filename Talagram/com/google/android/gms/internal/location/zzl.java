package com.google.android.gms.internal.location;

import android.os.IInterface;

final class zzl implements zzbj {
    zzl(zzk arg1) {
        this.zzcc = arg1;
        super();
    }

    public final void checkConnected() {
        zzk.zza(this.zzcc);
    }

    public final IInterface getService() {
        return this.zzcc.getService();
    }
}

