package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.location.places.PlacesOptions$Builder;
import com.google.android.gms.location.places.PlacesOptions;

public final class zzr extends AbstractClientBuilder {
    public zzr() {
        super();
    }

    public final Client buildClient(Context arg10, Looper arg11, ClientSettings arg12, Object arg13, ConnectionCallbacks arg14, OnConnectionFailedListener arg15) {
        PlacesOptions v13;
        if(arg13 == null) {
            v13 = new Builder().build();
        }

        return new zzp(arg10, arg11, arg12, arg14, arg15, arg10.getPackageName(), v13, null);
    }
}

