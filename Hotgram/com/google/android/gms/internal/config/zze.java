package com.google.android.gms.internal.config;

import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;

public final class zze {
    public static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    private static final ClientKey CLIENT_KEY;
    public static final zzg zze;

    static {
        zze.CLIENT_KEY = new ClientKey();
        zze.CLIENT_BUILDER = new zzf();
        zze.API = new Api("Config.API", zze.CLIENT_BUILDER, zze.CLIENT_KEY);
        zze.zze = new zzo();
    }
}

