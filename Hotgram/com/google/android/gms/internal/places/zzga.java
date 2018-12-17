package com.google.android.gms.internal.places;

public abstract class zzga {
    int zzob;
    int zzoc;
    private int zzod;
    zzgd zzoe;
    private boolean zzof;
    private static volatile boolean zzog = true;

    static {
    }

    private zzga() {
        super();
        this.zzoc = 100;
        this.zzod = 2147483647;
        this.zzof = false;
    }

    zzga(zzgb arg1) {
        this();
    }

    public abstract double readDouble();

    public abstract float readFloat();

    public abstract String readString();

    public abstract void zzah(int arg1);

    public abstract boolean zzai(int arg1);

    public final int zzaj(int arg4) {
        if(arg4 >= 0) {
            int v0 = this.zzoc;
            this.zzoc = arg4;
            return v0;
        }

        StringBuilder v2 = new StringBuilder(47);
        v2.append("Recursion limit cannot be negative: ");
        v2.append(arg4);
        throw new IllegalArgumentException(v2.toString());
    }

    public abstract int zzak(int arg1);

    public abstract void zzal(int arg1);

    public abstract void zzam(int arg1);

    public static int zzan(int arg1) {
        return -(arg1 & 1) ^ arg1 >>> 1;
    }

    static zzga zzb(byte[] arg6, int arg7, int arg8, boolean arg9) {
        zzgc v9 = new zzgc(arg6, arg7, arg8, false, null);
        try {
            ((zzga)v9).zzak(arg8);
            return ((zzga)v9);
        }
        catch(zzhh v6) {
            throw new IllegalArgumentException(((Throwable)v6));
        }
    }

    public abstract zzih zzb(zzir arg1, zzgl arg2);

    public abstract boolean zzbf();

    public abstract long zzbi();

    public abstract long zzbj();

    public abstract int zzbk();

    public abstract long zzbl();

    public abstract int zzbm();

    public abstract boolean zzbn();

    public abstract String zzbo();

    public abstract zzfr zzbp();

    public abstract int zzbq();

    public abstract int zzbr();

    public abstract int zzbs();

    public abstract long zzbt();

    public abstract int zzbu();

    public abstract long zzbv();

    public abstract int zzcj();

    abstract long zzck();

    public abstract int zzcl();

    public static long zzd(long arg4) {
        return -(arg4 & 1) ^ arg4 >>> 1;
    }

    public static zzga zzf(byte[] arg1, int arg2, int arg3) {
        return zzga.zzb(arg1, arg2, arg3, false);
    }
}

