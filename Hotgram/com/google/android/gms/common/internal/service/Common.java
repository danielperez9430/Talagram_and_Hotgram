package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;

public final class Common {
    public static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    public static final ClientKey CLIENT_KEY;
    public static final CommonApi CommonApi;

    static {
        Common.CLIENT_KEY = new ClientKey();
        Common.CLIENT_BUILDER = new zza();
        Common.API = new Api("Common.API", Common.CLIENT_BUILDER, Common.CLIENT_KEY);
        Common.CommonApi = new CommonApiImpl();
    }

    public Common() {
        super();
    }
}

