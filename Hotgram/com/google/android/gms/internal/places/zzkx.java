package com.google.android.gms.internal.places;

public final class zzkx {
    private static final int zzaaq = 11;
    private static final int zzaar = 12;
    private static final int zzaas = 16;
    private static final int zzaat = 26;
    public static final int[] zzaau;
    private static final long[] zzaav;
    private static final float[] zzaaw;
    private static final double[] zzaax;
    private static final boolean[] zzaay;
    public static final String[] zzaaz;
    public static final byte[][] zzaba;
    public static final byte[] zzabb;

    static {
        zzkx.zzaau = new int[0];
        zzkx.zzaav = new long[0];
        zzkx.zzaaw = new float[0];
        zzkx.zzaax = new double[0];
        zzkx.zzaay = new boolean[0];
        zzkx.zzaaz = new String[0];
        zzkx.zzaba = new byte[0][];
        zzkx.zzabb = new byte[0];
    }

    public static final int zzc(zzkl arg3, int arg4) {
        int v0 = arg3.getPosition();
        arg3.zzai(arg4);
        int v1;
        for(v1 = 1; arg3.zzcj() == arg4; ++v1) {
            arg3.zzai(arg4);
        }

        arg3.zzu(v0, arg4);
        return v1;
    }
}

