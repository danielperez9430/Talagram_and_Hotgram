package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks;

final class zzbl implements SignOutCallbacks {
    zzbl(zza arg1) {
        this.zzkk = arg1;
        super();
    }

    public final void onSignOutComplete() {
        GoogleApiManager.zza(this.zzkk.zzjy).post(new zzbm(this));
    }
}

