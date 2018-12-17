package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.MessageApi$MessageListener;

final class zzex extends zzn {
    private ListenerHolder zzax;
    private IntentFilter[] zzba;
    private MessageListener zzeg;

    zzex(GoogleApiClient arg1, MessageListener arg2, ListenerHolder arg3, IntentFilter[] arg4, zzev arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    private zzex(GoogleApiClient arg1, MessageListener arg2, ListenerHolder arg3, IntentFilter[] arg4) {
        super(arg1);
        this.zzeg = Preconditions.checkNotNull(arg2);
        this.zzax = Preconditions.checkNotNull(arg3);
        this.zzba = Preconditions.checkNotNull(arg4);
    }

    public final Result createFailedResult(Status arg2) {
        this.zzeg = null;
        this.zzax = null;
        this.zzba = null;
        return arg2;
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzhg)arg4).zza(((ResultHolder)this), this.zzeg, this.zzax, this.zzba);
        this.zzeg = null;
        this.zzax = null;
        this.zzba = null;
    }
}

