package com.google.android.gms.internal.vision;

public enum zzcz {
    public static final enum zzcz zzls;
    public static final enum zzcz zzlt;
    public static final enum zzcz zzlu;
    public static final enum zzcz zzlv;
    public static final enum zzcz zzlw;
    public static final enum zzcz zzlx;
    public static final enum zzcz zzly;
    public static final enum zzcz zzlz;
    public static final enum zzcz zzma;
    public static final enum zzcz zzmb;
    private final Class zzmc;
    private final Class zzmd;
    private final Object zzme;

    static {
        zzcz.zzls = new zzcz("VOID", 0, Void.class, Void.class, null);
        zzcz.zzlt = new zzcz("INT", 1, Integer.TYPE, Integer.class, Integer.valueOf(0));
        zzcz.zzlu = new zzcz("LONG", 2, Long.TYPE, Long.class, Long.valueOf(0));
        zzcz.zzlv = new zzcz("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0f));
        zzcz.zzlw = new zzcz("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0));
        zzcz.zzlx = new zzcz("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.valueOf(false));
        zzcz.zzly = new zzcz("STRING", 6, String.class, String.class, "");
        zzcz.zzlz = new zzcz("BYTE_STRING", 7, zzbo.class, zzbo.class, zzbo.zzgt);
        zzcz.zzma = new zzcz("ENUM", 8, Integer.TYPE, Integer.class, null);
        zzcz.zzmb = new zzcz("MESSAGE", 9, Object.class, Object.class, null);
        zzcz.zzmf = new zzcz[]{zzcz.zzls, zzcz.zzlt, zzcz.zzlu, zzcz.zzlv, zzcz.zzlw, zzcz.zzlx, zzcz.zzly, zzcz.zzlz, zzcz.zzma, zzcz.zzmb};
    }

    private zzcz(String arg1, int arg2, Class arg3, Class arg4, Object arg5) {
        super(arg1, arg2);
        this.zzmc = arg3;
        this.zzmd = arg4;
        this.zzme = arg5;
    }

    public static zzcz[] values() {
        // Method was not decompiled
    }

    public final Class zzch() {
        return this.zzmd;
    }
}

