package com.google.android.gms.internal.places;

final class zzgp {
    private static final zzgm zzph;
    private static final zzgm zzpi;

    static {
        zzgp.zzph = new zzgn();
        zzgp.zzpi = zzgp.zzdc();
    }

    private static zzgm zzdc() {
        try {
            return Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }

    static zzgm zzdd() {
        return zzgp.zzph;
    }

    static zzgm zzde() {
        if(zzgp.zzpi != null) {
            return zzgp.zzpi;
        }

        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}

