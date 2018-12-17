package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.MessageClient$OnMessageReceivedListener;

final class zzfc extends RegisterListenerMethod {
    private final IntentFilter[] zzba;
    private final ListenerHolder zzbz;
    private final OnMessageReceivedListener zzej;

    private zzfc(OnMessageReceivedListener arg1, IntentFilter[] arg2, ListenerHolder arg3) {
        super(arg3);
        this.zzej = arg1;
        this.zzba = arg2;
        this.zzbz = arg3;
    }

    zzfc(OnMessageReceivedListener arg1, IntentFilter[] arg2, ListenerHolder arg3, zzfb arg4) {
        this(arg1, arg2, arg3);
    }

    protected final void registerListener(AnyClient arg4, TaskCompletionSource arg5) {
        ((zzhg)arg4).zza(new zzgh(arg5), this.zzej, this.zzbz, this.zzba);
    }
}

