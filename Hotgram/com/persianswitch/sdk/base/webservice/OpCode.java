package com.persianswitch.sdk.base.webservice;

public enum OpCode {
    public static final enum OpCode a;
    public static final enum OpCode b;
    public static final enum OpCode c;
    public static final enum OpCode d;
    public static final enum OpCode e;
    public static final enum OpCode f;
    public static final enum OpCode g;
    public static final enum OpCode h;
    public static final enum OpCode i;
    private final int j;
    private final boolean k;
    private final boolean l;

    static {
        OpCode.a = new OpCode("UNDEFINED_OP_CODE", 0, -1);
        OpCode.b = new OpCode("SEND_ACTIVATION_CODE", 1, 101);
        OpCode.c = new OpCode("REGISTER_APPLICATION", 2, 102);
        OpCode.d = new OpCode("RE_VERIFICATION_APPLICATION", 3, 113);
        OpCode.e = new OpCode("PAY_TRANSACTION", 4, 209, false, true);
        OpCode.f = new OpCode("GET_TRUST_CODE", 5, 125, true);
        OpCode.g = new OpCode("INQUIRY_MERCHANT", 6, 303, true);
        OpCode.h = new OpCode("INQUIRY_TRANSACTION", 7, 250);
        OpCode.i = new OpCode("SYNC_CARDS_BY_SERVER", 8, 251, true);
        OpCode.m = new OpCode[]{OpCode.a, OpCode.b, OpCode.c, OpCode.d, OpCode.e, OpCode.f, OpCode.g, OpCode.h, OpCode.i};
    }

    private OpCode(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.j = arg3;
        this.k = false;
        this.l = false;
    }

    private OpCode(String arg1, int arg2, int arg3, boolean arg4, boolean arg5) {
        super(arg1, arg2);
        this.j = arg3;
        this.k = arg4;
        this.l = arg5;
    }

    private OpCode(String arg1, int arg2, int arg3, boolean arg4) {
        super(arg1, arg2);
        this.j = arg3;
        this.k = arg4;
        this.l = false;
    }

    public static OpCode a(int arg5) {
        OpCode[] v0 = OpCode.values();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            OpCode v3 = v0[v2];
            if(v3.a() == arg5) {
                return v3;
            }
        }

        return OpCode.a;
    }

    public int a() {
        return this.j;
    }

    public boolean b() {
        return this.k;
    }

    public boolean c() {
        return this.l;
    }

    public static OpCode valueOf(String arg1) {
        return Enum.valueOf(OpCode.class, arg1);
    }

    public static OpCode[] values() {
        // Method was not decompiled
    }
}

