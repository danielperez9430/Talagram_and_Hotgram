package com.google.android.gms.internal.clearcut;

enum zzcd {
    public static final enum zzcd zzjg;
    public static final enum zzcd zzjh;
    public static final enum zzcd zzji;
    public static final enum zzcd zzjj;
    private final boolean zzjk;

    static {
        zzcd.zzjg = new zzcd("SCALAR", 0, false);
        zzcd.zzjh = new zzcd("VECTOR", 1, true);
        zzcd.zzji = new zzcd("PACKED_VECTOR", 2, true);
        zzcd.zzjj = new zzcd("MAP", 3, false);
        zzcd.zzjl = new zzcd[]{zzcd.zzjg, zzcd.zzjh, zzcd.zzji, zzcd.zzjj};
    }

    private zzcd(String arg1, int arg2, boolean arg3) {
        super(arg1, arg2);
        this.zzjk = arg3;
    }

    public static zzcd[] values() {
        // Method was not decompiled
    }
}

