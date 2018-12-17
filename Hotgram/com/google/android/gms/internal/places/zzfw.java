package com.google.android.gms.internal.places;

final class zzfw {
    private final byte[] buffer;
    private final zzgf zznz;

    zzfw(int arg1, zzfs arg2) {
        this(arg1);
    }

    private zzfw(int arg1) {
        super();
        this.buffer = new byte[arg1];
        this.zznz = zzgf.zzd(this.buffer);
    }

    public final zzfr zzch() {
        if(this.zznz.zzcs() == 0) {
            return new zzfy(this.buffer);
        }

        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzgf zzci() {
        return this.zznz;
    }
}

