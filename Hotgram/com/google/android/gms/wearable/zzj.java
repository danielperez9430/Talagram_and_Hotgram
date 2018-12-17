package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.wearable.internal.zzhg;

final class zzj extends AbstractClientBuilder {
    zzj() {
        super();
    }

    public final Client buildClient(Context arg9, Looper arg10, ClientSettings arg11, Object arg12, ConnectionCallbacks arg13, OnConnectionFailedListener arg14) {
        if(arg12 == null) {
            new WearableOptions(new Builder(), null);
        }

        return new zzhg(arg9, arg10, arg13, arg14, arg11);
    }
}

