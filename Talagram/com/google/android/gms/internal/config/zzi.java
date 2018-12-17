package com.google.android.gms.internal.config;

import java.util.Collections;
import java.util.Map;

public final class zzi {
    private final long zzf;
    private final Map zzg;
    private final int zzh;
    private final int zzi;
    private final int zzj;
    private final String zzk;

    private zzi(zzj arg3) {
        super();
        this.zzf = zzj.zza(arg3);
        this.zzg = zzj.zzb(arg3);
        this.zzh = zzj.zzc(arg3);
        this.zzi = zzj.zzd(arg3);
        this.zzj = zzj.zze(arg3);
        this.zzk = zzj.zzf(arg3);
    }

    zzi(zzj arg1, zzh arg2) {
        this(arg1);
    }

    public final String getGmpAppId() {
        return this.zzk;
    }

    public final long zza() {
        return this.zzf;
    }

    public final Map zzb() {
        if(this.zzg == null) {
            return Collections.emptyMap();
        }

        return this.zzg;
    }

    public final int zzc() {
        return this.zzh;
    }

    public final int zzd() {
        return this.zzj;
    }

    public final int zze() {
        return this.zzi;
    }
}

