package com.persianswitch.sdk.payment;

public enum SDKVersion {
    public static final enum SDKVersion a;
    public static final enum SDKVersion b;
    private final int c;
    private final String d;

    static {
        SDKVersion.a = new SDKVersion("VERSION_UNDEFINED", 0, 0, "");
        SDKVersion.b = new SDKVersion("VERSION1", 1, 1, "1.8.0");
        SDKVersion.e = new SDKVersion[]{SDKVersion.a, SDKVersion.b};
    }

    private SDKVersion(String arg1, int arg2, int arg3, String arg4) {
        super(arg1, arg2);
        this.c = arg3;
        this.d = arg4;
    }

    public static SDKVersion valueOf(String arg1) {
        return Enum.valueOf(SDKVersion.class, arg1);
    }

    public static SDKVersion[] values() {
        // Method was not decompiled
    }
}

