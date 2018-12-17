package com.google.android.gms.internal.places;

public enum zzhj {
    public static final enum zzhj zztp;
    public static final enum zzhj zztq;
    public static final enum zzhj zztr;
    public static final enum zzhj zzts;
    public static final enum zzhj zztt;
    public static final enum zzhj zztu;
    public static final enum zzhj zztv;
    public static final enum zzhj zztw;
    public static final enum zzhj zztx;
    public static final enum zzhj zzty;
    private final Class zztz;
    private final Class zzua;
    private final Object zzub;

    static {
        zzhj.zztp = new zzhj("VOID", 0, Void.class, Void.class, null);
        zzhj.zztq = new zzhj("INT", 1, Integer.TYPE, Integer.class, Integer.valueOf(0));
        zzhj.zztr = new zzhj("LONG", 2, Long.TYPE, Long.class, Long.valueOf(0));
        zzhj.zzts = new zzhj("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0f));
        zzhj.zztt = new zzhj("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0));
        zzhj.zztu = new zzhj("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.valueOf(false));
        zzhj.zztv = new zzhj("STRING", 6, String.class, String.class, "");
        zzhj.zztw = new zzhj("BYTE_STRING", 7, zzfr.class, zzfr.class, zzfr.zznt);
        zzhj.zztx = new zzhj("ENUM", 8, Integer.TYPE, Integer.class, null);
        zzhj.zzty = new zzhj("MESSAGE", 9, Object.class, Object.class, null);
        zzhj.zzuc = new zzhj[]{zzhj.zztp, zzhj.zztq, zzhj.zztr, zzhj.zzts, zzhj.zztt, zzhj.zztu, zzhj.zztv, zzhj.zztw, zzhj.zztx, zzhj.zzty};
    }

    private zzhj(String arg1, int arg2, Class arg3, Class arg4, Object arg5) {
        super(arg1, arg2);
        this.zztz = arg3;
        this.zzua = arg4;
        this.zzub = arg5;
    }

    public static zzhj[] values() {
        // Method was not decompiled
    }

    public final Class zzeh() {
        return this.zzua;
    }
}

