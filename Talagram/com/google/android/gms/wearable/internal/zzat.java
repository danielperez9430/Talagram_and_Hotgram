package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;
import javax.annotation.Nullable;

final class zzat extends RegisterListenerMethod {
    private final IntentFilter[] zzba;
    @Nullable private final String zzce;
    private final ChannelListener zzcf;
    private final ListenerHolder zzci;

    zzat(ChannelListener arg1, @Nullable String arg2, IntentFilter[] arg3, ListenerHolder arg4, ListenerHolder arg5) {
        super(arg4);
        this.zzcf = arg1;
        this.zzba = arg3;
        this.zzce = arg2;
        this.zzci = arg5;
    }

    protected final void registerListener(AnyClient arg7, TaskCompletionSource arg8) {
        arg7.zza(new zzgh(arg8), this.zzcf, this.zzci, this.zzce, this.zzba);
    }
}

