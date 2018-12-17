package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;
import javax.annotation.Nullable;

final class zzan extends zzn {
    private final String zzce;
    private ChannelListener zzcf;

    zzan(GoogleApiClient arg1, ChannelListener arg2, @Nullable String arg3) {
        super(arg1);
        this.zzcf = Preconditions.checkNotNull(arg2);
        this.zzce = arg3;
    }

    public final Result createFailedResult(Status arg2) {
        this.zzcf = null;
        return arg2;
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzhg)arg3).zza(((ResultHolder)this), this.zzcf, this.zzce);
        this.zzcf = null;
    }
}

