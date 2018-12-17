package com.google.android.gms.internal.measurement;

enum zzvi {
    public static final enum zzvi zzbyc;
    public static final enum zzvi zzbyd;
    public static final enum zzvi zzbye;
    public static final enum zzvi zzbyf;
    private final boolean zzbyg;

    static {
        zzvi.zzbyc = new zzvi("SCALAR", 0, false);
        zzvi.zzbyd = new zzvi("VECTOR", 1, true);
        zzvi.zzbye = new zzvi("PACKED_VECTOR", 2, true);
        zzvi.zzbyf = new zzvi("MAP", 3, false);
        zzvi.zzbyh = new zzvi[]{zzvi.zzbyc, zzvi.zzbyd, zzvi.zzbye, zzvi.zzbyf};
    }

    private zzvi(String arg1, int arg2, boolean arg3) {
        super(arg1, arg2);
        this.zzbyg = arg3;
    }

    public static zzvi[] values() {
        // Method was not decompiled
    }
}

