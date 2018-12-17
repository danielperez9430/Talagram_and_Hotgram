package com.google.android.gms.internal.clearcut;

final class zzbg {
    private final byte[] buffer;
    private final zzbn zzfo;

    zzbg(int arg1, zzbc arg2) {
        this(arg1);
    }

    private zzbg(int arg1) {
        super();
        this.buffer = new byte[arg1];
        this.zzfo = zzbn.zzc(this.buffer);
    }

    public final zzbb zzad() {
        if(this.zzfo.zzag() == 0) {
            return new zzbi(this.buffer);
        }

        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzbn zzae() {
        return this.zzfo;
    }
}

