package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

final class zzs extends zzab {
    zzs(zzq arg1, GoogleApiClient arg2, LocationCallback arg3) {
        this.zzcm = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzaz)arg3).zzb(ListenerHolders.createListenerKey(this.zzcm, LocationCallback.class.getSimpleName()), new zzac(((ResultHolder)this)));
    }
}

