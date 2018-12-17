package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

final class zza extends AbstractClientBuilder {
    zza() {
        super();
    }

    public final Client buildClient(Context arg9, Looper arg10, ClientSettings arg11, Object arg12, ConnectionCallbacks arg13, OnConnectionFailedListener arg14) {
        SignInOptions v12;
        if(arg12 == null) {
            v12 = SignInOptions.DEFAULT;
        }

        return new SignInClientImpl(arg9, arg10, true, arg11, v12, arg13, arg14);
    }
}

