package com.google.android.gms.phenotype;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.phenotype.zzd;

@KeepForSdk public final class Phenotype {
    @Deprecated private static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    private static final ClientKey CLIENT_KEY;
    @Deprecated private static final zzm zzaj;

    static {
        Phenotype.CLIENT_KEY = new ClientKey();
        Phenotype.CLIENT_BUILDER = new zzl();
        Phenotype.API = new Api("Phenotype.API", Phenotype.CLIENT_BUILDER, Phenotype.CLIENT_KEY);
        Phenotype.zzaj = new zzd();
    }

    private Phenotype() {
        super();
    }

    @KeepForSdk public static Uri getContentProviderUri(String arg2) {
        String v0 = "content://com.google.android.gms.phenotype/";
        arg2 = String.valueOf(Uri.encode(arg2));
        arg2 = arg2.length() != 0 ? v0.concat(arg2) : new String(v0);
        return Uri.parse(arg2);
    }
}

