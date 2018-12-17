package com.google.android.gms.internal.places;

enum zzgv {
    public static final enum zzgv zzrw;
    public static final enum zzgv zzrx;
    public static final enum zzgv zzry;
    public static final enum zzgv zzrz;
    private final boolean zzsa;

    static {
        zzgv.zzrw = new zzgv("SCALAR", 0, false);
        zzgv.zzrx = new zzgv("VECTOR", 1, true);
        zzgv.zzry = new zzgv("PACKED_VECTOR", 2, true);
        zzgv.zzrz = new zzgv("MAP", 3, false);
        zzgv.zzsb = new zzgv[]{zzgv.zzrw, zzgv.zzrx, zzgv.zzry, zzgv.zzrz};
    }

    private zzgv(String arg1, int arg2, boolean arg3) {
        super(arg1, arg2);
        this.zzsa = arg3;
    }

    public static zzgv[] values() {
        // Method was not decompiled
    }
}

