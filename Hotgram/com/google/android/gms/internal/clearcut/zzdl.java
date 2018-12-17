package com.google.android.gms.internal.clearcut;

final class zzdl {
    private static final zzdj zzmf;
    private static final zzdj zzmg;

    static {
        zzdl.zzmf = zzdl.zzce();
        zzdl.zzmg = new zzdk();
    }

    static zzdj zzcc() {
        return zzdl.zzmf;
    }

    static zzdj zzcd() {
        return zzdl.zzmg;
    }

    private static zzdj zzce() {
        try {
            return Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

