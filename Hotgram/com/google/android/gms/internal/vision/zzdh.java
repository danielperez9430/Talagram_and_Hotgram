package com.google.android.gms.internal.vision;

abstract class zzdh {
    private static final zzdh zzmo;
    private static final zzdh zzmp;

    static {
        zzdh.zzmo = new zzdj(null);
        zzdh.zzmp = new zzdk(null);
    }

    private zzdh() {
        super();
    }

    zzdh(zzdi arg1) {
        this();
    }

    abstract void zza(Object arg1, long arg2);

    abstract void zza(Object arg1, Object arg2, long arg3);

    static zzdh zzcm() {
        return zzdh.zzmo;
    }

    static zzdh zzcn() {
        return zzdh.zzmp;
    }
}

