package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.util.VisibleForTesting;

public class HashAccumulator {
    @VisibleForTesting private static int zzad = 31;
    private int zzae;

    static {
    }

    public HashAccumulator() {
        super();
        this.zzae = 1;
    }

    public HashAccumulator addBoolean(boolean arg3) {
        this.zzae = HashAccumulator.zzad * this.zzae + (((int)arg3));
        return this;
    }

    public HashAccumulator addObject(Object arg3) {
        int v0 = HashAccumulator.zzad * this.zzae;
        int v3 = arg3 == null ? 0 : arg3.hashCode();
        this.zzae = v0 + v3;
        return this;
    }

    public int hash() {
        return this.zzae;
    }
}

