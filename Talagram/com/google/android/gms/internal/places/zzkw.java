package com.google.android.gms.internal.places;

import java.util.Arrays;

final class zzkw {
    final int tag;
    final byte[] zzoa;

    zzkw(int arg1, byte[] arg2) {
        super();
        this.tag = arg1;
        this.zzoa = arg2;
    }

    public final boolean equals(Object arg5) {
        if((((zzkw)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzkw)) {
            return 0;
        }

        if(this.tag == ((zzkw)arg5).tag && (Arrays.equals(this.zzoa, ((zzkw)arg5).zzoa))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.zzoa);
    }
}

