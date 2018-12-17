package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

final class zzw extends zzab {
    zzw(zzq arg1, GoogleApiClient arg2, LocationRequest arg3, LocationListener arg4, Looper arg5) {
        this.zzck = arg3;
        this.zzcl = arg4;
        this.zzcp = arg5;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg6) {
        ((zzaz)arg6).zza(this.zzck, ListenerHolders.createListenerHolder(this.zzcl, zzbm.zza(this.zzcp), LocationListener.class.getSimpleName()), new zzac(((ResultHolder)this)));
    }
}

