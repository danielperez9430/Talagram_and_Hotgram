package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.BaseSignInCallbacks;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;

final class zzaq extends BaseSignInCallbacks {
    private final WeakReference zzhw;

    zzaq(zzaj arg2) {
        super();
        this.zzhw = new WeakReference(arg2);
    }

    public final void onSignInComplete(SignInResponse arg4) {
        Object v0 = this.zzhw.get();
        if(v0 == null) {
            return;
        }

        zzaj.zzd(((zzaj)v0)).zza(new zzar(this, ((zzbc)v0), ((zzaj)v0), arg4));
    }
}

