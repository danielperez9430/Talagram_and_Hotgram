package com.google.android.gms.internal.vision;

final class zzfh extends zzff {
    zzfh() {
        super();
    }

    private static void zza(Object arg0, zzfg arg1) {
        ((zzcr)arg0).zzkr = arg1;
    }

    final void zza(Object arg1, int arg2, long arg3) {
        ((zzfg)arg1).zzb(arg2 << 3, Long.valueOf(arg3));
    }

    final void zza(Object arg1, int arg2, zzbo arg3) {
        ((zzfg)arg1).zzb(arg2 << 3 | 2, arg3);
    }

    final void zza(Object arg1, zzfz arg2) {
        ((zzfg)arg1).zzb(arg2);
    }

    final void zzc(Object arg1, zzfz arg2) {
        ((zzfg)arg1).zza(arg2);
    }

    final void zzd(Object arg1) {
        ((zzcr)arg1).zzkr.zzao();
    }

    final Object zzdt() {
        return zzfg.zzdv();
    }

    final void zze(Object arg1, Object arg2) {
        zzfh.zza(arg1, ((zzfg)arg2));
    }

    final void zzf(Object arg1, Object arg2) {
        zzfh.zza(arg1, ((zzfg)arg2));
    }

    final Object zzg(Object arg2, Object arg3) {
        if(((zzfg)arg3).equals(zzfg.zzdu())) {
            return arg2;
        }

        return zzfg.zza(((zzfg)arg2), ((zzfg)arg3));
    }

    final int zzn(Object arg1) {
        return ((zzfg)arg1).zzbl();
    }

    final Object zzr(Object arg1) {
        return ((zzcr)arg1).zzkr;
    }

    final int zzs(Object arg1) {
        return ((zzfg)arg1).zzdw();
    }
}

