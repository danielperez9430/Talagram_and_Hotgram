package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public class zzfz {
    protected volatile int zzrs;

    public zzfz() {
        super();
        this.zzrs = -1;
    }

    public Object clone() {
        return this.zzep();
    }

    public String toString() {
        return zzga.zza(this);
    }

    public void zza(zzfs arg1) {
    }

    public static final void zza(zzfz arg0, byte[] arg1, int arg2, int arg3) {
        try {
            zzfs v1 = zzfs.zzh(arg1, 0, arg3);
            arg0.zza(v1);
            v1.zzem();
            return;
        }
        catch(IOException v0) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ((Throwable)v0));
        }
    }

    public final int zzas() {
        int v0 = this.zzen();
        this.zzrs = v0;
        return v0;
    }

    protected int zzen() {
        return 0;
    }

    public zzfz zzep() {
        return super.clone();
    }
}

