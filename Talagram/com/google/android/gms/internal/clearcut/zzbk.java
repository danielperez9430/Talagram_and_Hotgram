package com.google.android.gms.internal.clearcut;

public abstract class zzbk {
    private int zzfq;
    private int zzfr;
    private boolean zzfs;
    private static volatile boolean zzft = true;

    static {
    }

    private zzbk() {
        super();
        this.zzfq = 100;
        this.zzfr = 2147483647;
        this.zzfs = false;
    }

    zzbk(zzbl arg1) {
        this();
    }

    public static long zza(long arg4) {
        return -(arg4 & 1) ^ arg4 >>> 1;
    }

    static zzbk zza(byte[] arg6, int arg7, int arg8, boolean arg9) {
        zzbm v7 = new zzbm(arg6, 0, arg8, false, null);
        try {
            ((zzbk)v7).zzl(arg8);
            return ((zzbk)v7);
        }
        catch(zzco v6) {
            throw new IllegalArgumentException(((Throwable)v6));
        }
    }

    public abstract int zzaf();

    public abstract int zzl(int arg1);

    public static int zzm(int arg1) {
        return -(arg1 & 1) ^ arg1 >>> 1;
    }
}

