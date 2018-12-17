package com.google.android.gms.internal.clearcut;

public enum zzcq {
    public static final enum zzcq zzkx;
    public static final enum zzcq zzky;
    public static final enum zzcq zzkz;
    public static final enum zzcq zzla;
    public static final enum zzcq zzlb;
    public static final enum zzcq zzlc;
    public static final enum zzcq zzld;
    public static final enum zzcq zzle;
    public static final enum zzcq zzlf;
    public static final enum zzcq zzlg;
    private final Class zzlh;
    private final Class zzli;
    private final Object zzlj;

    static {
        zzcq.zzkx = new zzcq("VOID", 0, Void.class, Void.class, null);
        zzcq.zzky = new zzcq("INT", 1, Integer.TYPE, Integer.class, Integer.valueOf(0));
        zzcq.zzkz = new zzcq("LONG", 2, Long.TYPE, Long.class, Long.valueOf(0));
        zzcq.zzla = new zzcq("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0f));
        zzcq.zzlb = new zzcq("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0));
        zzcq.zzlc = new zzcq("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.valueOf(false));
        zzcq.zzld = new zzcq("STRING", 6, String.class, String.class, "");
        zzcq.zzle = new zzcq("BYTE_STRING", 7, zzbb.class, zzbb.class, zzbb.zzfi);
        zzcq.zzlf = new zzcq("ENUM", 8, Integer.TYPE, Integer.class, null);
        zzcq.zzlg = new zzcq("MESSAGE", 9, Object.class, Object.class, null);
        zzcq.zzlk = new zzcq[]{zzcq.zzkx, zzcq.zzky, zzcq.zzkz, zzcq.zzla, zzcq.zzlb, zzcq.zzlc, zzcq.zzld, zzcq.zzle, zzcq.zzlf, zzcq.zzlg};
    }

    private zzcq(String arg1, int arg2, Class arg3, Class arg4, Object arg5) {
        super(arg1, arg2);
        this.zzlh = arg3;
        this.zzli = arg4;
        this.zzlj = arg5;
    }

    public static zzcq[] values() {
        // Method was not decompiled
    }

    public final Class zzbq() {
        return this.zzli;
    }
}

