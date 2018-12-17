package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;
import javax.annotation.Nullable;

final class zzau extends UnregisterListenerMethod {
    @Nullable private final String zzce;
    private final ChannelListener zzcf;

    zzau(ChannelListener arg1, @Nullable String arg2, ListenerKey arg3) {
        super(arg3);
        this.zzcf = arg1;
        this.zzce = arg2;
    }

    protected final void unregisterListener(AnyClient arg3, TaskCompletionSource arg4) {
        ((zzhg)arg3).zza(new zzgg(arg4), this.zzcf, this.zzce);
    }
}

