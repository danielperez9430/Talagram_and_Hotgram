package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.ClientSettings;

public final class zzv extends GoogleApi {
    private final AbstractClientBuilder zzdh;
    private final Client zzgd;
    private final zzp zzge;
    private final ClientSettings zzgf;

    public zzv(Context arg1, Api arg2, Looper arg3, Client arg4, zzp arg5, ClientSettings arg6, AbstractClientBuilder arg7) {
        super(arg1, arg2, arg3);
        this.zzgd = arg4;
        this.zzge = arg5;
        this.zzgf = arg6;
        this.zzdh = arg7;
        this.zzcq.zza(((GoogleApi)this));
    }

    public final Client zza(Looper arg1, zza arg2) {
        this.zzge.zza(((zzq)arg2));
        return this.zzgd;
    }

    public final zzby zza(Context arg4, Handler arg5) {
        return new zzby(arg4, arg5, this.zzgf, this.zzdh);
    }

    public final Client zzae() {
        return this.zzgd;
    }
}

