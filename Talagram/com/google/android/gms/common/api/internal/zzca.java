package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.SignInResponse;

final class zzca implements Runnable {
    zzca(zzby arg1, SignInResponse arg2) {
        this.zzlx = arg1;
        this.zzid = arg2;
        super();
    }

    public final void run() {
        zzby.zza(this.zzlx, this.zzid);
    }
}

