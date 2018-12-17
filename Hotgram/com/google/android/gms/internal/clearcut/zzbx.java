package com.google.android.gms.internal.clearcut;

final class zzbx {
    private static final zzbu zzgr;
    private static final zzbu zzgs;

    static {
        zzbx.zzgr = new zzbv();
        zzbx.zzgs = zzbx.zzao();
    }

    private static zzbu zzao() {
        try {
            return Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }

    static zzbu zzap() {
        return zzbx.zzgr;
    }

    static zzbu zzaq() {
        if(zzbx.zzgs != null) {
            return zzbx.zzgs;
        }

        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}

