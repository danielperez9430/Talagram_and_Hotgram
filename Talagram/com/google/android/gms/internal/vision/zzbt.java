package com.google.android.gms.internal.vision;

final class zzbt {
    private final byte[] buffer;
    private final zzca zzgz;

    zzbt(int arg1, zzbp arg2) {
        this(arg1);
    }

    private zzbt(int arg1) {
        super();
        this.buffer = new byte[arg1];
        this.zzgz = zzca.zzd(this.buffer);
    }

    public final zzbo zzaw() {
        this.zzgz.zzba();
        return new zzbv(this.buffer);
    }

    public final zzca zzax() {
        return this.zzgz;
    }
}

