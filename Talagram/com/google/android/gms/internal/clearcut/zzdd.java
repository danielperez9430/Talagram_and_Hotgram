package com.google.android.gms.internal.clearcut;

final class zzdd implements zzeg {
    private final zzdn zzly;
    private static final zzdn zzlz;

    static {
        zzdd.zzlz = new zzde();
    }

    public zzdd() {
        this(new zzdf(new zzdn[]{zzcf.zzay(), zzdd.zzby()}));
    }

    private zzdd(zzdn arg2) {
        super();
        this.zzly = zzci.zza(arg2, "messageInfoFactory");
    }

    private static boolean zza(zzdm arg1) {
        if(arg1.zzcf() == zzg.zzkl) {
            return 1;
        }

        return 0;
    }

    private static zzdn zzby() {
        try {
            return Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance").invoke(null);
        }
        catch(Exception ) {
            return zzdd.zzlz;
        }
    }

    public final zzef zzd(Class arg9) {
        zzeh.zzf(arg9);
        zzdm v2 = this.zzly.zzb(arg9);
        if(v2.zzcg()) {
            if(zzcg.class.isAssignableFrom(arg9)) {
                return zzdu.zza(zzeh.zzdo(), zzbx.zzap(), v2.zzch());
            }

            return zzdu.zza(zzeh.zzdm(), zzbx.zzaq(), v2.zzch());
        }

        if(zzcg.class.isAssignableFrom(arg9)) {
            if(zzdd.zza(v2)) {
                return zzds.zza(arg9, v2, zzdy.zzck(), zzcy.zzbw(), zzeh.zzdo(), zzbx.zzap(), zzdl.zzcd());
            }

            return zzds.zza(arg9, v2, zzdy.zzck(), zzcy.zzbw(), zzeh.zzdo(), null, zzdl.zzcd());
        }

        if(zzdd.zza(v2)) {
            return zzds.zza(arg9, v2, zzdy.zzcj(), zzcy.zzbv(), zzeh.zzdm(), zzbx.zzaq(), zzdl.zzcc());
        }

        return zzds.zza(arg9, v2, zzdy.zzcj(), zzcy.zzbv(), zzeh.zzdn(), null, zzdl.zzcc());
    }
}

