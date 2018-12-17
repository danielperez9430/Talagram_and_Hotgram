package com.google.android.gms.internal.places;

final class zzjs extends zzjq {
    zzjs() {
        super();
    }

    private static void zzb(Object arg0, zzjr arg1) {
        ((zzgz)arg0).zzsg = arg1;
    }

    final void zzb(Object arg1, int arg2, long arg3) {
        ((zzjr)arg1).zzc(arg2 << 3, Long.valueOf(arg3));
    }

    final void zzb(Object arg1, int arg2, zzfr arg3) {
        ((zzjr)arg1).zzc(arg2 << 3 | 2, arg3);
    }

    final void zzb(Object arg1, int arg2, Object arg3) {
        ((zzjr)arg1).zzc(arg2 << 3 | 3, arg3);
    }

    final void zzb(Object arg1, zzkk arg2) {
        ((zzjr)arg1).zzc(arg2);
    }

    final boolean zzb(zzix arg1) {
        return 0;
    }

    final void zzc(Object arg1, int arg2, long arg3) {
        ((zzjr)arg1).zzc(arg2 << 3 | 1, Long.valueOf(arg3));
    }

    final void zzd(Object arg1) {
        ((zzgz)arg1).zzsg.zzbb();
    }

    final void zzd(Object arg1, int arg2, int arg3) {
        ((zzjr)arg1).zzc(arg2 << 3 | 5, Integer.valueOf(arg3));
    }

    final void zzd(Object arg1, zzkk arg2) {
        ((zzjr)arg1).zzb(arg2);
    }

    final void zzf(Object arg1, Object arg2) {
        zzjs.zzb(arg1, ((zzjr)arg2));
    }

    final void zzg(Object arg1, Object arg2) {
        zzjs.zzb(arg1, ((zzjr)arg2));
    }

    final Object zzgo() {
        return zzjr.zzgq();
    }

    final Object zzh(Object arg2, Object arg3) {
        if(((zzjr)arg3).equals(zzjr.zzgp())) {
            return arg2;
        }

        return zzjr.zzb(((zzjr)arg2), ((zzjr)arg3));
    }

    final Object zzk(Object arg1) {
        ((zzjr)arg1).zzbb();
        return arg1;
    }

    final int zzn(Object arg1) {
        return ((zzjr)arg1).zzdg();
    }

    final Object zzq(Object arg1) {
        return ((zzgz)arg1).zzsg;
    }

    final Object zzr(Object arg3) {
        zzjr v0 = arg3.zzsg;
        if(v0 == zzjr.zzgp()) {
            v0 = zzjr.zzgq();
            zzjs.zzb(arg3, v0);
        }

        return v0;
    }

    final int zzs(Object arg1) {
        return ((zzjr)arg1).zzgr();
    }
}

