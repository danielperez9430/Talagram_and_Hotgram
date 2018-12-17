package com.google.android.gms.internal.config;

public final class zzbk {
    private static final int zzcs = 11;
    private static final int zzct = 12;
    private static final int zzcu = 16;
    private static final int zzcv = 26;
    private static final int[] zzcw;
    private static final long[] zzcx;
    private static final float[] zzcy;
    private static final double[] zzcz;
    private static final boolean[] zzda;
    private static final String[] zzdb;
    public static final byte[][] zzdc;
    public static final byte[] zzdd;

    static {
        zzbk.zzcw = new int[0];
        zzbk.zzcx = new long[0];
        zzbk.zzcy = new float[0];
        zzbk.zzcz = new double[0];
        zzbk.zzda = new boolean[0];
        zzbk.zzdb = new String[0];
        zzbk.zzdc = new byte[0][];
        zzbk.zzdd = new byte[0];
    }

    public static final int zzb(zzay arg3, int arg4) {
        int v0 = arg3.getPosition();
        arg3.zzh(arg4);
        int v1;
        for(v1 = 1; arg3.zzx() == arg4; ++v1) {
            arg3.zzh(arg4);
        }

        arg3.zzb(v0, arg4);
        return v1;
    }
}

