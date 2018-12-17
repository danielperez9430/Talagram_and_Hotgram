package com.google.android.gms.internal.firebase_abt;

import java.io.IOException;

public abstract class zzj {
    protected volatile int zzab;

    public zzj() {
        super();
        this.zzab = -1;
    }

    public Object clone() {
        return this.zzj();
    }

    public String toString() {
        return zzk.zzb(this);
    }

    public abstract zzj zza(zza arg1);

    public static final zzj zza(zzj arg0, byte[] arg1, int arg2, int arg3) {
        try {
            zza v1 = zza.zza(arg1, 0, arg3);
            arg0.zza(v1);
            v1.zza(0);
            return arg0;
        }
        catch(IOException v0) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ((Throwable)v0));
        }
        catch(zzi v0_1) {
            throw v0_1;
        }
    }

    public zzj zzj() {
        return super.clone();
    }
}

