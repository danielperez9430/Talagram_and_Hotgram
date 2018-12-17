package com.google.android.gms.internal.vision;

final class zzdu {
    private static final zzds zzna;
    private static final zzds zznb;

    static {
        zzdu.zzna = zzdu.zzcu();
        zzdu.zznb = new zzdt();
    }

    static zzds zzcs() {
        return zzdu.zzna;
    }

    static zzds zzct() {
        return zzdu.zznb;
    }

    private static zzds zzcu() {
        try {
            return Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

