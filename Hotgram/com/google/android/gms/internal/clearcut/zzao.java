package com.google.android.gms.internal.clearcut;

import android.net.Uri;

public final class zzao {
    private final String zzef;
    private final Uri zzeg;
    private final String zzeh;
    private final String zzei;
    private final boolean zzej;
    private final boolean zzek;

    public zzao(Uri arg8) {
        this(null, arg8, "", "", false, false);
    }

    private zzao(String arg1, Uri arg2, String arg3, String arg4, boolean arg5, boolean arg6) {
        super();
        this.zzef = arg1;
        this.zzeg = arg2;
        this.zzeh = arg3;
        this.zzei = arg4;
        this.zzej = arg5;
        this.zzek = arg6;
    }

    static String zza(zzao arg0) {
        return arg0.zzef;
    }

    public final zzae zza(String arg1, Object arg2, zzan arg3) {
        return zzae.zzb(this, arg1, arg2, arg3);
    }

    public final zzae zza(String arg1, String arg2) {
        return zzae.zzb(this, arg1, null);
    }

    static Uri zzb(zzao arg0) {
        return arg0.zzeg;
    }

    static String zzc(zzao arg0) {
        return arg0.zzeh;
    }

    public final zzae zzc(String arg1, boolean arg2) {
        return zzae.zzb(this, arg1, false);
    }

    public final zzao zzc(String arg9) {
        if(!this.zzej) {
            return new zzao(this.zzef, this.zzeg, arg9, this.zzei, this.zzej, this.zzek);
        }

        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    static String zzd(zzao arg0) {
        return arg0.zzei;
    }

    public final zzao zzd(String arg9) {
        return new zzao(this.zzef, this.zzeg, this.zzeh, arg9, this.zzej, this.zzek);
    }

    static boolean zze(zzao arg0) {
        return arg0.zzek;
    }

    static boolean zzf(zzao arg0) {
        return arg0.zzej;
    }
}

