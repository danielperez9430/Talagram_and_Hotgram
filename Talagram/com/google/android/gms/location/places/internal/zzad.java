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
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zzm;
import java.util.Locale;
import javax.annotation.Nullable;

public final class zzad extends GmsClient {
    private final Locale locale;
    private final zzat zzgc;

    zzad(Context arg1, Looper arg2, ClientSettings arg3, ConnectionCallbacks arg4, OnConnectionFailedListener arg5, String arg6, PlacesOptions arg7, zzae arg8) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    private zzad(Context arg8, Looper arg9, ClientSettings arg10, ConnectionCallbacks arg11, OnConnectionFailedListener arg12, String arg13, PlacesOptions arg14) {
        super(arg8, arg9, 67, arg10, arg11, arg12);
        this.locale = Locale.getDefault();
        String v8 = arg10.getAccount() != null ? arg10.getAccount().name : null;
        this.zzgc = new zzat(arg13, this.locale, v8, null, 0);
    }

    protected final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        if((v0 instanceof zzs)) {
            return v0;
        }

        return new zzt(arg3);
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.location.places.PlaceDetectionApi";
    }

    public final void zzb(zzm arg3, @Nullable PlaceFilter arg4) {
        Preconditions.checkNotNull(arg3, "callback == null");
        if(arg4 == null) {
            arg4 = PlaceFilter.zzz();
        }

        this.getService().zzb(arg4, this.zzgc, ((zzy)arg3));
    }

    public final void zzb(zzm arg3, PlaceReport arg4) {
        Preconditions.checkNotNull(arg3, "callback == null");
        this.getService().zzb(arg4, this.zzgc, ((zzy)arg3));
    }
}

