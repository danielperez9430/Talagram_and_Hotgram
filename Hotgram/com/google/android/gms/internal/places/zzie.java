package com.google.android.gms.internal.places;

final class zzie {
    private static final zzic zzux;
    private static final zzic zzuy;

    static {
        zzie.zzux = zzie.zzeu();
        zzie.zzuy = new zzid();
    }

    static zzic zzes() {
        return zzie.zzux;
    }

    static zzic zzet() {
        return zzie.zzuy;
    }

    private static zzic zzeu() {
        try {
            return Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

