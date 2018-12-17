package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;

final class zzz extends zzab {
    zzz(zzq arg1, GoogleApiClient arg2, LocationListener arg3) {
        this.zzcl = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzaz)arg3).zza(ListenerHolders.createListenerKey(this.zzcl, LocationListener.class.getSimpleName()), new zzac(((ResultHolder)this)));
    }
}

