package com.google.android.gms.internal.wearable;

import java.io.IOException;

public abstract class zzt {
    protected volatile int zzhl;

    public zzt() {
        super();
        this.zzhl = -1;
    }

    public Object clone() {
        return this.zzs();
    }

    public String toString() {
        return zzu.zzc(this);
    }

    public abstract zzt zza(zzk arg1);

    public void zza(zzl arg1) {
    }

    public static final zzt zza(zzt arg0, byte[] arg1, int arg2, int arg3) {
        try {
            zzk v1 = zzk.zza(arg1, 0, arg3);
            arg0.zza(v1);
            v1.zzc(0);
            return arg0;
        }
        catch(IOException v0) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ((Throwable)v0));
        }
        catch(zzs v0_1) {
            throw v0_1;
        }
    }

    public static final byte[] zzb(zzt arg3) {
        byte[] v0 = new byte[arg3.zzx()];
        int v1 = v0.length;
        try {
            zzl v1_1 = zzl.zzb(v0, 0, v1);
            arg3.zza(v1_1);
            v1_1.zzr();
            return v0;
        }
        catch(IOException v3) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ((Throwable)v3));
        }
    }

    protected int zzg() {
        return 0;
    }

    public zzt zzs() {
        return super.clone();
    }

    public final int zzx() {
        int v0 = this.zzg();
        this.zzhl = v0;
        return v0;
    }
}

