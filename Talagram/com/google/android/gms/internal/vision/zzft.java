package com.google.android.gms.internal.vision;

public enum zzft {
    public static final enum zzft zzpw;
    public static final enum zzft zzpx;
    public static final enum zzft zzpy;
    public static final enum zzft zzpz;
    public static final enum zzft zzqa;
    public static final enum zzft zzqb;
    public static final enum zzft zzqc;
    public static final enum zzft zzqd;
    public static final enum zzft zzqe;
    public static final enum zzft zzqf;
    public static final enum zzft zzqg;
    public static final enum zzft zzqh;
    public static final enum zzft zzqi;
    public static final enum zzft zzqj;
    public static final enum zzft zzqk;
    public static final enum zzft zzql;
    public static final enum zzft zzqm;
    public static final enum zzft zzqn;
    private final zzfy zzqo;
    private final int zzqp;

    static {
        zzft.zzpw = new zzft("DOUBLE", 0, zzfy.zzqu, 1);
        zzft.zzpx = new zzft("FLOAT", 1, zzfy.zzqt, 5);
        zzft.zzpy = new zzft("INT64", 2, zzfy.zzqs, 0);
        zzft.zzpz = new zzft("UINT64", 3, zzfy.zzqs, 0);
        zzft.zzqa = new zzft("INT32", 4, zzfy.zzqr, 0);
        zzft.zzqb = new zzft("FIXED64", 5, zzfy.zzqs, 1);
        zzft.zzqc = new zzft("FIXED32", 6, zzfy.zzqr, 5);
        zzft.zzqd = new zzft("BOOL", 7, zzfy.zzqv, 0);
        zzft.zzqe = new zzfu("STRING", 8, zzfy.zzqw, 2);
        zzft.zzqf = new zzfv("GROUP", 9, zzfy.zzqz, 3);
        zzft.zzqg = new zzfw("MESSAGE", 10, zzfy.zzqz, 2);
        zzft.zzqh = new zzfx("BYTES", 11, zzfy.zzqx, 2);
        zzft.zzqi = new zzft("UINT32", 12, zzfy.zzqr, 0);
        zzft.zzqj = new zzft("ENUM", 13, zzfy.zzqy, 0);
        zzft.zzqk = new zzft("SFIXED32", 14, zzfy.zzqr, 5);
        zzft.zzql = new zzft("SFIXED64", 15, zzfy.zzqs, 1);
        zzft.zzqm = new zzft("SINT32", 16, zzfy.zzqr, 0);
        zzft.zzqn = new zzft("SINT64", 17, zzfy.zzqs, 0);
        zzft.zzqq = new zzft[]{zzft.zzpw, zzft.zzpx, zzft.zzpy, zzft.zzpz, zzft.zzqa, zzft.zzqb, zzft.zzqc, zzft.zzqd, zzft.zzqe, zzft.zzqf, zzft.zzqg, zzft.zzqh, zzft.zzqi, zzft.zzqj, zzft.zzqk, zzft.zzql, zzft.zzqm, zzft.zzqn};
    }

    private zzft(String arg1, int arg2, zzfy arg3, int arg4) {
        super(arg1, arg2);
        this.zzqo = arg3;
        this.zzqp = arg4;
    }

    zzft(String arg1, int arg2, zzfy arg3, int arg4, zzfs arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    public static zzft[] values() {
        // Method was not decompiled
    }

    public final zzfy zzed() {
        return this.zzqo;
    }

    public final int zzee() {
        return this.zzqp;
    }
}

