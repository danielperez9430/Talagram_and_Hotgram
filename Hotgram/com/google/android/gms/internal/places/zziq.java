package com.google.android.gms.internal.places;

final class zziq {
    private static final zzio zzvs;
    private static final zzio zzvt;

    static {
        zziq.zzvs = zziq.zzfb();
        zziq.zzvt = new zzip();
    }

    static zzio zzez() {
        return zziq.zzvs;
    }

    static zzio zzfa() {
        return zziq.zzvt;
    }

    private static zzio zzfb() {
        try {
            return Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

