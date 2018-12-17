package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;

final class zzb extends zzn {
    private Object zzaw;
    private ListenerHolder zzax;
    private zzc zzay;

    private zzb(GoogleApiClient arg1, Object arg2, ListenerHolder arg3, zzc arg4) {
        super(arg1);
        this.zzaw = Preconditions.checkNotNull(arg2);
        this.zzax = Preconditions.checkNotNull(arg3);
        this.zzay = Preconditions.checkNotNull(arg4);
    }

    protected final Result createFailedResult(Status arg2) {
        this.zzaw = null;
        this.zzax = null;
        return arg2;
    }

    protected final void doExecute(AnyClient arg4) {
        this.zzay.zza(((zzhg)arg4), ((ResultHolder)this), this.zzaw, this.zzax);
        this.zzaw = null;
        this.zzax = null;
    }

    static PendingResult zza(GoogleApiClient arg2, zzc arg3, Object arg4) {
        return arg2.enqueue(new zzb(arg2, arg4, arg2.registerListener(arg4), arg3));
    }
}

