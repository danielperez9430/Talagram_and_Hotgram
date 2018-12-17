package com.google.android.gms.internal.clearcut;

public enum zzfl {
    public static final enum zzfl zzqc;
    public static final enum zzfl zzqd;
    public static final enum zzfl zzqe;
    public static final enum zzfl zzqf;
    public static final enum zzfl zzqg;
    public static final enum zzfl zzqh;
    public static final enum zzfl zzqi;
    public static final enum zzfl zzqj;
    public static final enum zzfl zzqk;
    public static final enum zzfl zzql;
    public static final enum zzfl zzqm;
    public static final enum zzfl zzqn;
    public static final enum zzfl zzqo;
    public static final enum zzfl zzqp;
    public static final enum zzfl zzqq;
    public static final enum zzfl zzqr;
    public static final enum zzfl zzqs;
    public static final enum zzfl zzqt;
    private final zzfq zzqu;
    private final int zzqv;

    static {
        zzfl.zzqc = new zzfl("DOUBLE", 0, zzfq.zzra, 1);
        zzfl.zzqd = new zzfl("FLOAT", 1, zzfq.zzqz, 5);
        zzfl.zzqe = new zzfl("INT64", 2, zzfq.zzqy, 0);
        zzfl.zzqf = new zzfl("UINT64", 3, zzfq.zzqy, 0);
        zzfl.zzqg = new zzfl("INT32", 4, zzfq.zzqx, 0);
        zzfl.zzqh = new zzfl("FIXED64", 5, zzfq.zzqy, 1);
        zzfl.zzqi = new zzfl("FIXED32", 6, zzfq.zzqx, 5);
        zzfl.zzqj = new zzfl("BOOL", 7, zzfq.zzrb, 0);
        zzfl.zzqk = new zzfm("STRING", 8, zzfq.zzrc, 2);
        zzfl.zzql = new zzfn("GROUP", 9, zzfq.zzrf, 3);
        zzfl.zzqm = new zzfo("MESSAGE", 10, zzfq.zzrf, 2);
        zzfl.zzqn = new zzfp("BYTES", 11, zzfq.zzrd, 2);
        zzfl.zzqo = new zzfl("UINT32", 12, zzfq.zzqx, 0);
        zzfl.zzqp = new zzfl("ENUM", 13, zzfq.zzre, 0);
        zzfl.zzqq = new zzfl("SFIXED32", 14, zzfq.zzqx, 5);
        zzfl.zzqr = new zzfl("SFIXED64", 15, zzfq.zzqy, 1);
        zzfl.zzqs = new zzfl("SINT32", 16, zzfq.zzqx, 0);
        zzfl.zzqt = new zzfl("SINT64", 17, zzfq.zzqy, 0);
        zzfl.zzqw = new zzfl[]{zzfl.zzqc, zzfl.zzqd, zzfl.zzqe, zzfl.zzqf, zzfl.zzqg, zzfl.zzqh, zzfl.zzqi, zzfl.zzqj, zzfl.zzqk, zzfl.zzql, zzfl.zzqm, zzfl.zzqn, zzfl.zzqo, zzfl.zzqp, zzfl.zzqq, zzfl.zzqr, zzfl.zzqs, zzfl.zzqt};
    }

    private zzfl(String arg1, int arg2, zzfq arg3, int arg4) {
        super(arg1, arg2);
        this.zzqu = arg3;
        this.zzqv = arg4;
    }

    zzfl(String arg1, int arg2, zzfq arg3, int arg4, zzfk arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    public static zzfl[] values() {
        // Method was not decompiled
    }

    public final zzfq zzek() {
        return this.zzqu;
    }

    public final int zzel() {
        return this.zzqv;
    }
}

