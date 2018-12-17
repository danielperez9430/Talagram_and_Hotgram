package com.google.android.gms.internal.places;

public enum zzkj {
    private final Object zzub;
    public static final enum zzkj zzzo;
    public static final enum zzkj zzzp;
    public static final enum zzkj zzzq;
    public static final enum zzkj zzzr;
    public static final enum zzkj zzzs;
    public static final enum zzkj zzzt;
    public static final enum zzkj zzzu;
    public static final enum zzkj zzzv;
    public static final enum zzkj zzzw;

    static {
        zzkj.zzzo = new zzkj("INT", 0, Integer.valueOf(0));
        zzkj.zzzp = new zzkj("LONG", 1, Long.valueOf(0));
        zzkj.zzzq = new zzkj("FLOAT", 2, Float.valueOf(0f));
        zzkj.zzzr = new zzkj("DOUBLE", 3, Double.valueOf(0));
        zzkj.zzzs = new zzkj("BOOLEAN", 4, Boolean.valueOf(false));
        zzkj.zzzt = new zzkj("STRING", 5, "");
        zzkj.zzzu = new zzkj("BYTE_STRING", 6, zzfr.zznt);
        zzkj.zzzv = new zzkj("ENUM", 7, null);
        zzkj.zzzw = new zzkj("MESSAGE", 8, null);
        zzkj.zzzx = new zzkj[]{zzkj.zzzo, zzkj.zzzp, zzkj.zzzq, zzkj.zzzr, zzkj.zzzs, zzkj.zzzt, zzkj.zzzu, zzkj.zzzv, zzkj.zzzw};
    }

    private zzkj(String arg1, int arg2, Object arg3) {
        super(arg1, arg2);
        this.zzub = arg3;
    }

    public static zzkj[] values() {
        // Method was not decompiled
    }
}

