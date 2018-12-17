package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzzg {
    protected volatile int zzcfm;

    public zzzg() {
        super();
        this.zzcfm = -1;
    }

    public Object clone() {
        return this.zzyu();
    }

    public String toString() {
        return zzzh.zzc(this);
    }

    public abstract zzzg zza(zzyx arg1);

    public void zza(zzyy arg1) {
    }

    public static final zzzg zza(zzzg arg2, byte[] arg3) {
        return zzzg.zzb(arg2, arg3, 0, arg3.length);
    }

    public static final void zza(zzzg arg0, byte[] arg1, int arg2, int arg3) {
        try {
            zzyy v1 = zzyy.zzk(arg1, 0, arg3);
            arg0.zza(v1);
            v1.zzyt();
            return;
        }
        catch(IOException v0) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ((Throwable)v0));
        }
    }

    private static final zzzg zzb(zzzg arg0, byte[] arg1, int arg2, int arg3) {
        try {
            zzyx v1 = zzyx.zzj(arg1, 0, arg3);
            arg0.zza(v1);
            v1.zzan(0);
            return arg0;
        }
        catch(IOException v0) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ((Throwable)v0));
        }
        catch(zzzf v0_1) {
            throw v0_1;
        }
    }

    protected int zzf() {
        return 0;
    }

    public final int zzvu() {
        int v0 = this.zzf();
        this.zzcfm = v0;
        return v0;
    }

    public zzzg zzyu() {
        return super.clone();
    }

    public final int zzza() {
        if(this.zzcfm < 0) {
            this.zzvu();
        }

        return this.zzcfm;
    }
}

