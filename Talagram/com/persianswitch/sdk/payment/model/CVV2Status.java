package com.persianswitch.sdk.payment.model;

public enum CVV2Status {
    public static final enum CVV2Status a;
    public static final enum CVV2Status b;
    private final int c;

    static {
        CVV2Status.a = new CVV2Status("CVV2_REQUIRED", 0, 2);
        CVV2Status.b = new CVV2Status("CVV2_NOT_REQUIRED_STATUS", 1, 1);
        CVV2Status.d = new CVV2Status[]{CVV2Status.a, CVV2Status.b};
    }

    private CVV2Status(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.c = arg3;
    }

    public static CVV2Status a(int arg5) {
        CVV2Status[] v0 = CVV2Status.values();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            CVV2Status v3 = v0[v2];
            if(v3.c == arg5) {
                return v3;
            }
        }

        return CVV2Status.a;
    }

    public int a() {
        return this.c;
    }

    public static CVV2Status valueOf(String arg1) {
        return Enum.valueOf(CVV2Status.class, arg1);
    }

    public static CVV2Status[] values() {
        // Method was not decompiled
    }
}

