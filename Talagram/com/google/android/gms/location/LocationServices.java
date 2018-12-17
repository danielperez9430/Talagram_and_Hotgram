package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.location.zzaf;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzbk;
import com.google.android.gms.internal.location.zzq;

public class LocationServices {
    public abstract class zza extends ApiMethodImpl {
        public zza(GoogleApiClient arg2) {
            super(LocationServices.API, arg2);
        }
    }

    public static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    private static final ClientKey CLIENT_KEY;
    @Deprecated public static final FusedLocationProviderApi FusedLocationApi;
    @Deprecated public static final GeofencingApi GeofencingApi;
    @Deprecated public static final SettingsApi SettingsApi;

    static {
        LocationServices.CLIENT_KEY = new ClientKey();
        LocationServices.CLIENT_BUILDER = new zzad();
        LocationServices.API = new Api("LocationServices.API", LocationServices.CLIENT_BUILDER, LocationServices.CLIENT_KEY);
        LocationServices.FusedLocationApi = new zzq();
        LocationServices.GeofencingApi = new zzaf();
        LocationServices.SettingsApi = new zzbk();
    }

    private LocationServices() {
        super();
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Activity arg1) {
        return new FusedLocationProviderClient(arg1);
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context arg1) {
        return new FusedLocationProviderClient(arg1);
    }

    public static GeofencingClient getGeofencingClient(Activity arg1) {
        return new GeofencingClient(arg1);
    }

    public static GeofencingClient getGeofencingClient(Context arg1) {
        return new GeofencingClient(arg1);
    }

    public static SettingsClient getSettingsClient(Activity arg1) {
        return new SettingsClient(arg1);
    }

    public static SettingsClient getSettingsClient(Context arg1) {
        return new SettingsClient(arg1);
    }

    public static zzaz zza(GoogleApiClient arg4) {
        boolean v0 = false;
        boolean v2 = arg4 != null ? true : false;
        Preconditions.checkArgument(v2, "GoogleApiClient parameter is required.");
        Client v4 = arg4.getClient(LocationServices.CLIENT_KEY);
        if(v4 != null) {
            v0 = true;
        }

        Preconditions.checkState(v0, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return ((zzaz)v4);
    }
}

