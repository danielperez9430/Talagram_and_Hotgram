package com.google.android.gms.internal.measurement;

public enum zzyv {
    private final Object zzbzz;
    public static final enum zzyv zzcel;
    public static final enum zzyv zzcem;
    public static final enum zzyv zzcen;
    public static final enum zzyv zzceo;
    public static final enum zzyv zzcep;
    public static final enum zzyv zzceq;
    public static final enum zzyv zzcer;
    public static final enum zzyv zzces;
    public static final enum zzyv zzcet;

    static {
        zzyv.zzcel = new zzyv("INT", 0, Integer.valueOf(0));
        zzyv.zzcem = new zzyv("LONG", 1, Long.valueOf(0));
        zzyv.zzcen = new zzyv("FLOAT", 2, Float.valueOf(0f));
        zzyv.zzceo = new zzyv("DOUBLE", 3, Double.valueOf(0));
        zzyv.zzcep = new zzyv("BOOLEAN", 4, Boolean.valueOf(false));
        zzyv.zzceq = new zzyv("STRING", 5, "");
        zzyv.zzcer = new zzyv("BYTE_STRING", 6, zzud.zzbtz);
        zzyv.zzces = new zzyv("ENUM", 7, null);
        zzyv.zzcet = new zzyv("MESSAGE", 8, null);
        zzyv.zzceu = new zzyv[]{zzyv.zzcel, zzyv.zzcem, zzyv.zzcen, zzyv.zzceo, zzyv.zzcep, zzyv.zzceq, zzyv.zzcer, zzyv.zzces, zzyv.zzcet};
    }

    private zzyv(String arg1, int arg2, Object arg3) {
        super(arg1, arg2);
        this.zzbzz = arg3;
    }

    public static zzyv[] values() {
        // Method was not decompiled
    }
}

