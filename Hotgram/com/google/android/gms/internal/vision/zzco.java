package com.google.android.gms.internal.vision;

enum zzco {
    public static final enum zzco zzki;
    public static final enum zzco zzkj;
    public static final enum zzco zzkk;
    public static final enum zzco zzkl;
    private final boolean zzkm;

    static {
        zzco.zzki = new zzco("SCALAR", 0, false);
        zzco.zzkj = new zzco("VECTOR", 1, true);
        zzco.zzkk = new zzco("PACKED_VECTOR", 2, true);
        zzco.zzkl = new zzco("MAP", 3, false);
        zzco.zzkn = new zzco[]{zzco.zzki, zzco.zzkj, zzco.zzkk, zzco.zzkl};
    }

    private zzco(String arg1, int arg2, boolean arg3) {
        super(arg1, arg2);
        this.zzkm = arg3;
    }

    public static zzco[] values() {
        // Method was not decompiled
    }
}

