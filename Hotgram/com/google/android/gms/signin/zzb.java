package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

final class zzb extends AbstractClientBuilder {
    zzb() {
        super();
    }

    public final Client buildClient(Context arg10, Looper arg11, ClientSettings arg12, Object arg13, ConnectionCallbacks arg14, OnConnectionFailedListener arg15) {
        return new SignInClientImpl(arg10, arg11, false, arg12, ((SignInOptionsInternal)arg13).getSignInOptionsBundle(), arg14, arg15);
    }
}

