package com.google.android.gms.internal.measurement;

public enum zzvv {
    public static final enum zzvv zzbzn;
    public static final enum zzvv zzbzo;
    public static final enum zzvv zzbzp;
    public static final enum zzvv zzbzq;
    public static final enum zzvv zzbzr;
    public static final enum zzvv zzbzs;
    public static final enum zzvv zzbzt;
    public static final enum zzvv zzbzu;
    public static final enum zzvv zzbzv;
    public static final enum zzvv zzbzw;
    private final Class zzbzx;
    private final Class zzbzy;
    private final Object zzbzz;

    static {
        zzvv.zzbzn = new zzvv("VOID", 0, Void.class, Void.class, null);
        zzvv.zzbzo = new zzvv("INT", 1, Integer.TYPE, Integer.class, Integer.valueOf(0));
        zzvv.zzbzp = new zzvv("LONG", 2, Long.TYPE, Long.class, Long.valueOf(0));
        zzvv.zzbzq = new zzvv("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0f));
        zzvv.zzbzr = new zzvv("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0));
        zzvv.zzbzs = new zzvv("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.valueOf(false));
        zzvv.zzbzt = new zzvv("STRING", 6, String.class, String.class, "");
        zzvv.zzbzu = new zzvv("BYTE_STRING", 7, zzud.class, zzud.class, zzud.zzbtz);
        zzvv.zzbzv = new zzvv("ENUM", 8, Integer.TYPE, Integer.class, null);
        zzvv.zzbzw = new zzvv("MESSAGE", 9, Object.class, Object.class, null);
        zzvv.zzcaa = new zzvv[]{zzvv.zzbzn, zzvv.zzbzo, zzvv.zzbzp, zzvv.zzbzq, zzvv.zzbzr, zzvv.zzbzs, zzvv.zzbzt, zzvv.zzbzu, zzvv.zzbzv, zzvv.zzbzw};
    }

    private zzvv(String arg1, int arg2, Class arg3, Class arg4, Object arg5) {
        super(arg1, arg2);
        this.zzbzx = arg3;
        this.zzbzy = arg4;
        this.zzbzz = arg5;
    }

    public static zzvv[] values() {
        // Method was not decompiled
    }

    public final Class zzws() {
        return this.zzbzy;
    }
}

