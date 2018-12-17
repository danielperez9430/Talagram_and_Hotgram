package com.google.android.gms.internal.measurement;

public final class zzzj {
    public static final int[] zzcax = null;
    private static final int zzcfn = 11;
    private static final int zzcfo = 12;
    private static final int zzcfp = 16;
    private static final int zzcfq = 26;
    public static final long[] zzcfr;
    private static final float[] zzcfs;
    private static final double[] zzcft;
    private static final boolean[] zzcfu;
    public static final String[] zzcfv;
    private static final byte[][] zzcfw;
    public static final byte[] zzcfx;

    static {
        zzzj.zzcax = new int[0];
        zzzj.zzcfr = new long[0];
        zzzj.zzcfs = new float[0];
        zzzj.zzcft = new double[0];
        zzzj.zzcfu = new boolean[0];
        zzzj.zzcfv = new String[0];
        zzzj.zzcfw = new byte[0][];
        zzzj.zzcfx = new byte[0];
    }

    public static final int zzb(zzyx arg3, int arg4) {
        int v0 = arg3.getPosition();
        arg3.zzao(arg4);
        int v1;
        for(v1 = 1; arg3.zzug() == arg4; ++v1) {
            arg3.zzao(arg4);
        }

        arg3.zzt(v0, arg4);
        return v1;
    }
}

