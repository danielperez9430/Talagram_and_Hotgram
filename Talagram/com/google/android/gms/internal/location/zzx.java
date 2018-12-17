package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

final class zzx extends zzab {
    zzx(zzq arg1, GoogleApiClient arg2, LocationRequest arg3, LocationCallback arg4, Looper arg5) {
        this.zzck = arg3;
        this.zzcm = arg4;
        this.zzcp = arg5;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg6) {
        ((zzaz)arg6).zza(zzbd.zza(this.zzck), ListenerHolders.createListenerHolder(this.zzcm, zzbm.zza(this.zzcp), LocationCallback.class.getSimpleName()), new zzac(((ResultHolder)this)));
    }
}

