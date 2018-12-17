package com.google.android.gms.common.api.internal;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzaz implements ResultCallback {
    zzaz(zzav arg1, StatusPendingResult arg2, boolean arg3, GoogleApiClient arg4) {
        this.zzit = arg1;
        this.zziv = arg2;
        this.zziw = arg3;
        this.zzix = arg4;
        super();
    }

    public final void onResult(Result arg2) {
        Storage.getInstance(zzav.zzc(this.zzit)).removeSavedDefaultGoogleSignInAccount();
        if((((Status)arg2).isSuccess()) && (this.zzit.isConnected())) {
            this.zzit.reconnect();
        }

        this.zziv.setResult(arg2);
        if(this.zziw) {
            this.zzix.disconnect();
        }
    }
}

