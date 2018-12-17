package com.google.android.gms.internal.places;

import java.io.IOException;

public abstract class zzku {
    protected volatile int zzaap;

    public zzku() {
        super();
        this.zzaap = -1;
    }

    public Object clone() {
        return this.zzhe();
    }

    public String toString() {
        return zzkv.zze(this);
    }

    protected int zzal() {
        return 0;
    }

    public abstract zzku zzb(zzkl arg1);

    public void zzb(zzkm arg1) {
    }

    public static final zzku zzb(zzku arg2, byte[] arg3) {
        return zzku.zzb(arg2, arg3, 0, arg3.length);
    }

    private static final zzku zzb(zzku arg0, byte[] arg1, int arg2, int arg3) {
        try {
            zzkl v1 = zzkl.zzk(arg1, 0, arg3);
            arg0.zzb(v1);
            v1.zzah(0);
            return arg0;
        }
        catch(IOException v0) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ((Throwable)v0));
        }
        catch(zzkt v0_1) {
            throw v0_1;
        }
    }

    public static final byte[] zzd(zzku arg3) {
        byte[] v0 = new byte[arg3.zzdg()];
        int v1 = v0.length;
        try {
            zzkm v1_1 = zzkm.zzl(v0, 0, v1);
            arg3.zzb(v1_1);
            v1_1.zzhd();
            return v0;
        }
        catch(IOException v3) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ((Throwable)v3));
        }
    }

    public final int zzdg() {
        int v0 = this.zzal();
        this.zzaap = v0;
        return v0;
    }

    public zzku zzhe() {
        return super.clone();
    }
}

