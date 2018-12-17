package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class zzsv {
    private final String zzbrm;
    private final Uri zzbrn;
    private final String zzbro;
    private final String zzbrp;
    private final boolean zzbrq;
    private final boolean zzbrr;
    private final boolean zzbrs;

    public zzsv(Uri arg9) {
        this(null, arg9, "", "", false, false, false);
    }

    private zzsv(String arg1, Uri arg2, String arg3, String arg4, boolean arg5, boolean arg6, boolean arg7) {
        super();
        this.zzbrm = null;
        this.zzbrn = arg2;
        this.zzbro = arg3;
        this.zzbrp = arg4;
        this.zzbrq = false;
        this.zzbrr = false;
        this.zzbrs = false;
    }

    static Uri zza(zzsv arg0) {
        return arg0.zzbrn;
    }

    static String zzb(zzsv arg0) {
        return arg0.zzbro;
    }

    public final zzsl zzb(String arg1, double arg2) {
        return zzsl.zzb(this, arg1, arg2);
    }

    static String zzc(zzsv arg0) {
        return arg0.zzbrp;
    }

    public final zzsl zzd(String arg1, int arg2) {
        return zzsl.zzb(this, arg1, arg2);
    }

    public final zzsl zze(String arg1, long arg2) {
        return zzsl.zzb(this, arg1, arg2);
    }

    public final zzsl zzf(String arg1, boolean arg2) {
        return zzsl.zzb(this, arg1, arg2);
    }

    public final zzsl zzx(String arg1, String arg2) {
        return zzsl.zzb(this, arg1, arg2);
    }
}

