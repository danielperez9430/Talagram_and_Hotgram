package com.google.android.gms.internal.vision;

final class zzdm implements zzeo {
    private final zzdw zzmt;
    private static final zzdw zzmu;

    static {
        zzdm.zzmu = new zzdn();
    }

    public zzdm() {
        this(new zzdo(new zzdw[]{zzcq.zzbs(), zzdm.zzco()}));
    }

    private zzdm(zzdw arg2) {
        super();
        this.zzmt = zzct.zza(arg2, "messageInfoFactory");
    }

    private static boolean zza(zzdv arg1) {
        if(arg1.zzcv() == zzd.zzlg) {
            return 1;
        }

        return 0;
    }

    private static zzdw zzco() {
        try {
            return Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance").invoke(null);
        }
        catch(Exception ) {
            return zzdm.zzmu;
        }
    }

    public final zzen zzd(Class arg9) {
        zzep.zzf(arg9);
        zzdv v2 = this.zzmt.zzb(arg9);
        if(v2.zzcw()) {
            if(zzcr.class.isAssignableFrom(arg9)) {
                return zzed.zza(zzep.zzdi(), zzci.zzbi(), v2.zzcx());
            }

            return zzed.zza(zzep.zzdg(), zzci.zzbj(), v2.zzcx());
        }

        if(zzcr.class.isAssignableFrom(arg9)) {
            if(zzdm.zza(v2)) {
                return zzeb.zza(arg9, v2, zzeh.zzda(), zzdh.zzcn(), zzep.zzdi(), zzci.zzbi(), zzdu.zzct());
            }

            return zzeb.zza(arg9, v2, zzeh.zzda(), zzdh.zzcn(), zzep.zzdi(), null, zzdu.zzct());
        }

        if(zzdm.zza(v2)) {
            return zzeb.zza(arg9, v2, zzeh.zzcz(), zzdh.zzcm(), zzep.zzdg(), zzci.zzbj(), zzdu.zzcs());
        }

        return zzeb.zza(arg9, v2, zzeh.zzcz(), zzdh.zzcm(), zzep.zzdh(), null, zzdu.zzcs());
    }
}

