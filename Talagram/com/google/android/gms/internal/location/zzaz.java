package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzal;
import javax.annotation.Nullable;

public final class zzaz extends zzk {
    private final zzas zzde;

    public zzaz(Context arg8, Looper arg9, ConnectionCallbacks arg10, OnConnectionFailedListener arg11, String arg12) {
        this(arg8, arg9, arg10, arg11, arg12, ClientSettings.createDefault(arg8));
    }

    public zzaz(Context arg1, Looper arg2, ConnectionCallbacks arg3, OnConnectionFailedListener arg4, String arg5, @Nullable ClientSettings arg6) {
        super(arg1, arg2, arg3, arg4, arg5, arg6);
        this.zzde = new zzas(arg1, this.zzcb);
    }

    public final void disconnect() {
        zzas v0 = this.zzde;
        __monitor_enter(v0);
        try {
            if(!this.isConnected()) {
                goto label_13;
            }

            try {
                this.zzde.removeAllListeners();
                this.zzde.zzb();
                goto label_13;
            }
            catch(Exception v1_1) {
                try {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", ((Throwable)v1_1));
                label_13:
                    super.disconnect();
                    __monitor_exit(v0);
                    return;
                label_17:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_17;
                }
            }
        }
        catch(Throwable v1) {
            goto label_17;
        }

        throw v1;
    }

    public final Location getLastLocation() {
        return this.zzde.getLastLocation();
    }

    public final LocationAvailability zza() {
        return this.zzde.zza();
    }

    public final void zza(zzbd arg3, ListenerHolder arg4, zzaj arg5) {
        zzas v0 = this.zzde;
        __monitor_enter(v0);
        try {
            this.zzde.zza(arg3, arg4, arg5);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public final void zza(long arg4, PendingIntent arg6) {
        // Method was not decompiled
    }

    public final void zza(PendingIntent arg2, ResultHolder arg3) {
        this.checkConnected();
        Preconditions.checkNotNull(arg3, "ResultHolder not provided.");
        this.getService().zza(arg2, new StatusCallback(arg3));
    }

    public final void zza(PendingIntent arg2, zzaj arg3) {
        this.zzde.zza(arg2, arg3);
    }

    public final void zza(Location arg2) {
        this.zzde.zza(arg2);
    }

    public final void zza(ListenerKey arg2, zzaj arg3) {
        this.zzde.zza(arg2, arg3);
    }

    public final void zza(zzaj arg2) {
        this.zzde.zza(arg2);
    }

    public final void zza(ActivityTransitionRequest arg2, PendingIntent arg3, ResultHolder arg4) {
        this.checkConnected();
        Preconditions.checkNotNull(arg4, "ResultHolder not provided.");
        this.getService().zza(arg2, arg3, new StatusCallback(arg4));
    }

    public final void zza(GeofencingRequest arg2, PendingIntent arg3, ResultHolder arg4) {
        this.checkConnected();
        Preconditions.checkNotNull(arg2, "geofencingRequest can\'t be null.");
        Preconditions.checkNotNull(arg3, "PendingIntent must be specified.");
        Preconditions.checkNotNull(arg4, "ResultHolder not provided.");
        this.getService().zza(arg2, arg3, new zzba(arg4));
    }

    public final void zza(LocationRequest arg2, PendingIntent arg3, zzaj arg4) {
        this.zzde.zza(arg2, arg3, arg4);
    }

    public final void zza(LocationRequest arg3, ListenerHolder arg4, zzaj arg5) {
        zzas v0 = this.zzde;
        __monitor_enter(v0);
        try {
            this.zzde.zza(arg3, arg4, arg5);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public final void zza(LocationSettingsRequest arg5, ResultHolder arg6, @Nullable String arg7) {
        this.checkConnected();
        boolean v0 = false;
        boolean v2 = arg5 != null ? true : false;
        Preconditions.checkArgument(v2, "locationSettingsRequest can\'t be null nor empty.");
        if(arg6 != null) {
            v0 = true;
        }

        Preconditions.checkArgument(v0, "listener can\'t be null.");
        this.getService().zza(arg5, new zzbc(arg6), arg7);
    }

    public final void zza(zzal arg2, ResultHolder arg3) {
        this.checkConnected();
        Preconditions.checkNotNull(arg2, "removeGeofencingRequest can\'t be null.");
        Preconditions.checkNotNull(arg3, "ResultHolder not provided.");
        this.getService().zza(arg2, new zzbb(arg3));
    }

    public final void zza(boolean arg2) {
        this.zzde.zza(arg2);
    }

    public final void zzb(ListenerKey arg2, zzaj arg3) {
        this.zzde.zzb(arg2, arg3);
    }

    public final void zzb(PendingIntent arg2) {
        this.checkConnected();
        Preconditions.checkNotNull(arg2);
        this.getService().zzb(arg2);
    }
}

