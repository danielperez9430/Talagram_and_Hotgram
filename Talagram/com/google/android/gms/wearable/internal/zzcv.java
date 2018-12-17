package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.DataClient$OnDataChangedListener;

final class zzcv extends RegisterListenerMethod {
    private final IntentFilter[] zzba;
    private final ListenerHolder zzbz;
    private final OnDataChangedListener zzdk;

    private zzcv(OnDataChangedListener arg1, IntentFilter[] arg2, ListenerHolder arg3) {
        super(arg3);
        this.zzdk = arg1;
        this.zzba = arg2;
        this.zzbz = arg3;
    }

    zzcv(OnDataChangedListener arg1, IntentFilter[] arg2, ListenerHolder arg3, zzct arg4) {
        this(arg1, arg2, arg3);
    }

    protected final void registerListener(AnyClient arg4, TaskCompletionSource arg5) {
        ((zzhg)arg4).zza(new zzgh(arg5), this.zzdk, this.zzbz, this.zzba);
    }
}

