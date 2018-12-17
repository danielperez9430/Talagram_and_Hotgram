package com.google.android.gms.internal.vision;

public abstract class zzbx {
    private int zzhb;
    private int zzhc;
    private boolean zzhd;
    private static volatile boolean zzhe = false;

    static {
    }

    private zzbx() {
        super();
        this.zzhb = 100;
        this.zzhc = 2147483647;
        this.zzhd = false;
    }

    zzbx(zzby arg1) {
        this();
    }

    public static long zza(long arg4) {
        return -(arg4 & 1) ^ arg4 >>> 1;
    }

    static zzbx zza(byte[] arg6, int arg7, int arg8, boolean arg9) {
        zzbz v7 = new zzbz(arg6, 0, arg8, false, null);
        try {
            ((zzbx)v7).zzn(arg8);
            return ((zzbx)v7);
        }
        catch(zzcx v6) {
            throw new IllegalArgumentException(((Throwable)v6));
        }
    }

    public abstract int zzay();

    public abstract int zzn(int arg1);

    public static int zzo(int arg1) {
        return -(arg1 & 1) ^ arg1 >>> 1;
    }
}

