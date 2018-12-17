package com.google.android.gms.internal.measurement;

final class zzuk {
    private final byte[] buffer;
    private final zzut zzbuf;

    zzuk(int arg1, zzue arg2) {
        this(arg1);
    }

    private zzuk(int arg1) {
        super();
        this.buffer = new byte[arg1];
        this.zzbuf = zzut.zzj(this.buffer);
    }

    public final zzud zzue() {
        if(this.zzbuf.zzvg() == 0) {
            return new zzum(this.buffer);
        }

        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzut zzuf() {
        return this.zzbuf;
    }
}

