package com.google.android.gms.internal.wearable;

import java.util.Arrays;

final class zzv {
    final int tag;
    final byte[] zzhm;

    zzv(int arg1, byte[] arg2) {
        super();
        this.tag = arg1;
        this.zzhm = arg2;
    }

    public final boolean equals(Object arg5) {
        if((((zzv)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzv)) {
            return 0;
        }

        if(this.tag == ((zzv)arg5).tag && (Arrays.equals(this.zzhm, ((zzv)arg5).zzhm))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.zzhm);
    }
}

