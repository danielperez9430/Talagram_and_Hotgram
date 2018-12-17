package com.google.android.gms.internal.firebase_abt;

import java.util.Arrays;

final class zzl {
    final int tag;
    final byte[] zzac;

    zzl(int arg1, byte[] arg2) {
        super();
        this.tag = arg1;
        this.zzac = arg2;
    }

    public final boolean equals(Object arg5) {
        if((((zzl)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzl)) {
            return 0;
        }

        if(this.tag == ((zzl)arg5).tag && (Arrays.equals(this.zzac, ((zzl)arg5).zzac))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.zzac);
    }
}

