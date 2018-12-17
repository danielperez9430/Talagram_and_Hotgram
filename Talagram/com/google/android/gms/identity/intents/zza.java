package com.google.android.gms.identity.intents;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.identity.zze;

final class zza extends AbstractClientBuilder {
    zza() {
        super();
    }

    public final Client buildClient(Context arg9, Looper arg10, ClientSettings arg11, Object arg12, ConnectionCallbacks arg13, OnConnectionFailedListener arg14) {
        AddressOptions v12;
        Preconditions.checkArgument(arg9 instanceof Activity, "An Activity must be used for Address APIs");
        if(arg12 == null) {
            v12 = new AddressOptions();
        }

        return new zze(arg9, arg10, arg11, v12.theme, arg13, arg14);
    }
}

