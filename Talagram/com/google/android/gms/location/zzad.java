package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.location.zzaz;

final class zzad extends AbstractClientBuilder {
    zzad() {
        super();
    }

    public final Client buildClient(Context arg8, Looper arg9, ClientSettings arg10, Object arg11, ConnectionCallbacks arg12, OnConnectionFailedListener arg13) {
        return new zzaz(arg8, arg9, arg12, arg13, "locationServices", arg10);
    }
}

