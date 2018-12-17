package com.google.android.gms.internal.config;

import java.util.HashMap;
import java.util.Map;

public final class zzj {
    private long zzf;
    private Map zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private String zzk;

    public zzj() {
        super();
        this.zzf = 43200;
        this.zzi = -1;
        this.zzj = -1;
    }

    static long zza(zzj arg2) {
        return arg2.zzf;
    }

    public final zzj zza(int arg1) {
        this.zzh = 10300;
        return this;
    }

    public final zzj zza(long arg1) {
        this.zzf = arg1;
        return this;
    }

    public final zzj zza(String arg1) {
        this.zzk = arg1;
        return this;
    }

    public final zzj zza(String arg2, String arg3) {
        if(this.zzg == null) {
            this.zzg = new HashMap();
        }

        this.zzg.put(arg2, arg3);
        return this;
    }

    static Map zzb(zzj arg0) {
        return arg0.zzg;
    }

    public final zzj zzb(int arg1) {
        this.zzi = arg1;
        return this;
    }

    static int zzc(zzj arg0) {
        return arg0.zzh;
    }

    public final zzj zzc(int arg1) {
        this.zzj = arg1;
        return this;
    }

    static int zzd(zzj arg0) {
        return arg0.zzi;
    }

    static int zze(zzj arg0) {
        return arg0.zzj;
    }

    static String zzf(zzj arg0) {
        return arg0.zzk;
    }

    public final zzi zzf() {
        return new zzi(this, null);
    }
}

