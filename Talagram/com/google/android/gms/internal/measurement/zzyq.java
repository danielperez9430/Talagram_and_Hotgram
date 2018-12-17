package com.google.android.gms.internal.measurement;

public enum zzyq {
    public static final enum zzyq zzcdq;
    public static final enum zzyq zzcdr;
    public static final enum zzyq zzcds;
    public static final enum zzyq zzcdt;
    public static final enum zzyq zzcdu;
    public static final enum zzyq zzcdv;
    public static final enum zzyq zzcdw;
    public static final enum zzyq zzcdx;
    public static final enum zzyq zzcdy;
    public static final enum zzyq zzcdz;
    public static final enum zzyq zzcea;
    public static final enum zzyq zzceb;
    public static final enum zzyq zzcec;
    public static final enum zzyq zzced;
    public static final enum zzyq zzcee;
    public static final enum zzyq zzcef;
    public static final enum zzyq zzceg;
    public static final enum zzyq zzceh;
    private final zzyv zzcei;
    private final int zzcej;

    static {
        zzyq.zzcdq = new zzyq("DOUBLE", 0, zzyv.zzceo, 1);
        zzyq.zzcdr = new zzyq("FLOAT", 1, zzyv.zzcen, 5);
        zzyq.zzcds = new zzyq("INT64", 2, zzyv.zzcem, 0);
        zzyq.zzcdt = new zzyq("UINT64", 3, zzyv.zzcem, 0);
        zzyq.zzcdu = new zzyq("INT32", 4, zzyv.zzcel, 0);
        zzyq.zzcdv = new zzyq("FIXED64", 5, zzyv.zzcem, 1);
        zzyq.zzcdw = new zzyq("FIXED32", 6, zzyv.zzcel, 5);
        zzyq.zzcdx = new zzyq("BOOL", 7, zzyv.zzcep, 0);
        zzyq.zzcdy = new zzyr("STRING", 8, zzyv.zzceq, 2);
        zzyq.zzcdz = new zzys("GROUP", 9, zzyv.zzcet, 3);
        zzyq.zzcea = new zzyt("MESSAGE", 10, zzyv.zzcet, 2);
        zzyq.zzceb = new zzyu("BYTES", 11, zzyv.zzcer, 2);
        zzyq.zzcec = new zzyq("UINT32", 12, zzyv.zzcel, 0);
        zzyq.zzced = new zzyq("ENUM", 13, zzyv.zzces, 0);
        zzyq.zzcee = new zzyq("SFIXED32", 14, zzyv.zzcel, 5);
        zzyq.zzcef = new zzyq("SFIXED64", 15, zzyv.zzcem, 1);
        zzyq.zzceg = new zzyq("SINT32", 16, zzyv.zzcel, 0);
        zzyq.zzceh = new zzyq("SINT64", 17, zzyv.zzcem, 0);
        zzyq.zzcek = new zzyq[]{zzyq.zzcdq, zzyq.zzcdr, zzyq.zzcds, zzyq.zzcdt, zzyq.zzcdu, zzyq.zzcdv, zzyq.zzcdw, zzyq.zzcdx, zzyq.zzcdy, zzyq.zzcdz, zzyq.zzcea, zzyq.zzceb, zzyq.zzcec, zzyq.zzced, zzyq.zzcee, zzyq.zzcef, zzyq.zzceg, zzyq.zzceh};
    }

    private zzyq(String arg1, int arg2, zzyv arg3, int arg4) {
        super(arg1, arg2);
        this.zzcei = arg3;
        this.zzcej = arg4;
    }

    zzyq(String arg1, int arg2, zzyv arg3, int arg4, zzyp arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    public static zzyq[] values() {
        // Method was not decompiled
    }

    public final zzyv zzyp() {
        return this.zzcei;
    }

    public final int zzyq() {
        return this.zzcej;
    }
}

