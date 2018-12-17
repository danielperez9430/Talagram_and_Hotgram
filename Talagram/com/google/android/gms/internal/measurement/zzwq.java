package com.google.android.gms.internal.measurement;

final class zzwq {
    private static final zzwo zzcav;
    private static final zzwo zzcaw;

    static {
        zzwq.zzcav = zzwq.zzxf();
        zzwq.zzcaw = new zzwp();
    }

    static zzwo zzxd() {
        return zzwq.zzcav;
    }

    static zzwo zzxe() {
        return zzwq.zzcaw;
    }

    private static zzwo zzxf() {
        try {
            return Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

