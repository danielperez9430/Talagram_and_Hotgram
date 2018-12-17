package com.google.android.gms.internal.wearable;

public final class zzw {
    private static final int zzhn = 11;
    private static final int zzho = 12;
    private static final int zzhp = 16;
    private static final int zzhq = 26;
    private static final int[] zzhr;
    public static final long[] zzhs;
    public static final float[] zzht;
    private static final double[] zzhu;
    private static final boolean[] zzhv;
    public static final String[] zzhw;
    private static final byte[][] zzhx;
    public static final byte[] zzhy;

    static {
        zzw.zzhr = new int[0];
        zzw.zzhs = new long[0];
        zzw.zzht = new float[0];
        zzw.zzhu = new double[0];
        zzw.zzhv = new boolean[0];
        zzw.zzhw = new String[0];
        zzw.zzhx = new byte[0][];
        zzw.zzhy = new byte[0];
    }

    public static final int zzb(zzk arg3, int arg4) {
        int v0 = arg3.getPosition();
        arg3.zzd(arg4);
        int v1;
        for(v1 = 1; arg3.zzj() == arg4; ++v1) {
            arg3.zzd(arg4);
        }

        arg3.zzc(v0, arg4);
        return v1;
    }
}

