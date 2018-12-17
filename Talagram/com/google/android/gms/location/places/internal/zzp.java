package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter$Builder;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zze;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public final class zzp extends GmsClient {
    private final zzat zzgc;

    zzp(Context arg1, Looper arg2, ClientSettings arg3, ConnectionCallbacks arg4, OnConnectionFailedListener arg5, String arg6, PlacesOptions arg7, zzq arg8) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    private zzp(Context arg11, Looper arg12, ClientSettings arg13, ConnectionCallbacks arg14, OnConnectionFailedListener arg15, String arg16, PlacesOptions arg17) {
        super(arg11, arg12, 65, arg13, arg14, arg15);
        Locale v6 = Locale.getDefault();
        String v0 = null;
        if(arg13.getAccount() != null) {
            v0 = arg13.getAccount().name;
        }

        this.zzgc = new zzat(arg16, v6, v0, null, 0);
    }

    protected final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if((v0 instanceof zzu)) {
            return v0;
        }

        return new zzv(arg3);
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.location.places.internal.IGooglePlacesService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.location.places.GeoDataApi";
    }

    public final void zzb(zze arg3, String arg4) {
        Preconditions.checkNotNull(arg3, "callback cannot be null");
        this.getService().zzb(arg4, this.zzgc, ((zzw)arg3));
    }

    public final void zzb(zze arg9, String arg10, int arg11, int arg12, int arg13) {
        Preconditions.checkNotNull(arg9, "callback cannot be null");
        this.getService().zzb(arg10, arg11, arg12, arg13, this.zzgc, arg9);
    }

    public final void zzb(zzm arg3, AddPlaceRequest arg4) {
        Preconditions.checkNotNull(arg3, "callback == null");
        this.getService().zzb(arg4, this.zzgc, ((zzy)arg3));
    }

    public final void zzb(zzm arg8, String arg9, LatLngBounds arg10, int arg11, AutocompleteFilter arg12) {
        Preconditions.checkNotNull(arg8, "callback == null");
        if(arg9 == null) {
            arg9 = "";
        }

        String v1 = arg9;
        if(arg12 == null) {
            arg12 = new Builder().build();
        }

        this.getService().zzb(v1, arg10, arg11, arg12, this.zzgc, arg8);
    }

    public final void zzb(zzm arg3, List arg4) {
        Preconditions.checkNotNull(arg3, "callback == null");
        this.getService().zzb(arg4, this.zzgc, ((zzy)arg3));
    }
}

