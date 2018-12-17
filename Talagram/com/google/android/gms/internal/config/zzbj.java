package com.google.android.gms.internal.config;

import java.util.Arrays;

final class zzbj {
    final int tag;
    final byte[] zzcr;

    zzbj(int arg1, byte[] arg2) {
        super();
        this.tag = arg1;
        this.zzcr = arg2;
    }

    public final boolean equals(Object arg5) {
        if((((zzbj)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzbj)) {
            return 0;
        }

        if(this.tag == ((zzbj)arg5).tag && (Arrays.equals(this.zzcr, ((zzbj)arg5).zzcr))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.zzcr);
    }
}

