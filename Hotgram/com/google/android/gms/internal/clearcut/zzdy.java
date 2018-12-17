package com.google.android.gms.internal.clearcut;

final class zzdy {
    private static final zzdw zzna;
    private static final zzdw zznb;

    static {
        zzdy.zzna = zzdy.zzcl();
        zzdy.zznb = new zzdx();
    }

    static zzdw zzcj() {
        return zzdy.zzna;
    }

    static zzdw zzck() {
        return zzdy.zznb;
    }

    private static zzdw zzcl() {
        try {
            return Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }
}

