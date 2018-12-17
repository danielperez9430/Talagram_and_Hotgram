package com.google.android.gms.internal.clearcut;

public enum zzfq {
    private final Object zzlj;
    public static final enum zzfq zzqx;
    public static final enum zzfq zzqy;
    public static final enum zzfq zzqz;
    public static final enum zzfq zzra;
    public static final enum zzfq zzrb;
    public static final enum zzfq zzrc;
    public static final enum zzfq zzrd;
    public static final enum zzfq zzre;
    public static final enum zzfq zzrf;

    static {
        zzfq.zzqx = new zzfq("INT", 0, Integer.valueOf(0));
        zzfq.zzqy = new zzfq("LONG", 1, Long.valueOf(0));
        zzfq.zzqz = new zzfq("FLOAT", 2, Float.valueOf(0f));
        zzfq.zzra = new zzfq("DOUBLE", 3, Double.valueOf(0));
        zzfq.zzrb = new zzfq("BOOLEAN", 4, Boolean.valueOf(false));
        zzfq.zzrc = new zzfq("STRING", 5, "");
        zzfq.zzrd = new zzfq("BYTE_STRING", 6, zzbb.zzfi);
        zzfq.zzre = new zzfq("ENUM", 7, null);
        zzfq.zzrf = new zzfq("MESSAGE", 8, null);
        zzfq.zzrg = new zzfq[]{zzfq.zzqx, zzfq.zzqy, zzfq.zzqz, zzfq.zzra, zzfq.zzrb, zzfq.zzrc, zzfq.zzrd, zzfq.zzre, zzfq.zzrf};
    }

    private zzfq(String arg1, int arg2, Object arg3) {
        super(arg1, arg2);
        this.zzlj = arg3;
    }

    public static zzfq[] values() {
        // Method was not decompiled
    }
}

