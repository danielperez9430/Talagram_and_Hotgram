package com.google.android.gms.internal.measurement;

final class zzyd extends zzyb {
    zzyd() {
        super();
    }

    private static void zza(Object arg0, zzyc arg1) {
        ((zzvm)arg0).zzbym = arg1;
    }

    final void zza(Object arg1, int arg2, long arg3) {
        ((zzyc)arg1).zzb(arg2 << 3, Long.valueOf(arg3));
    }

    final void zza(Object arg1, int arg2, zzud arg3) {
        ((zzyc)arg1).zzb(arg2 << 3 | 2, arg3);
    }

    final void zza(Object arg1, int arg2, Object arg3) {
        ((zzyc)arg1).zzb(arg2 << 3 | 3, arg3);
    }

    final void zza(Object arg1, zzyw arg2) {
        ((zzyc)arg1).zzb(arg2);
    }

    final boolean zza(zzxi arg1) {
        return 0;
    }

    final Object zzab(Object arg1) {
        ((zzyc)arg1).zzsm();
        return arg1;
    }

    final int zzae(Object arg1) {
        return ((zzyc)arg1).zzvu();
    }

    final Object zzah(Object arg1) {
        return ((zzvm)arg1).zzbym;
    }

    final Object zzai(Object arg3) {
        zzyc v0 = arg3.zzbym;
        if(v0 == zzyc.zzyf()) {
            v0 = zzyc.zzyg();
            zzyd.zza(arg3, v0);
        }

        return v0;
    }

    final int zzaj(Object arg1) {
        return ((zzyc)arg1).zzyh();
    }

    final void zzb(Object arg1, int arg2, long arg3) {
        ((zzyc)arg1).zzb(arg2 << 3 | 1, Long.valueOf(arg3));
    }

    final void zzc(Object arg1, int arg2, int arg3) {
        ((zzyc)arg1).zzb(arg2 << 3 | 5, Integer.valueOf(arg3));
    }

    final void zzc(Object arg1, zzyw arg2) {
        ((zzyc)arg1).zza(arg2);
    }

    final void zzf(Object arg1, Object arg2) {
        zzyd.zza(arg1, ((zzyc)arg2));
    }

    final void zzg(Object arg1, Object arg2) {
        zzyd.zza(arg1, ((zzyc)arg2));
    }

    final Object zzh(Object arg2, Object arg3) {
        if(((zzyc)arg3).equals(zzyc.zzyf())) {
            return arg2;
        }

        return zzyc.zza(((zzyc)arg2), ((zzyc)arg3));
    }

    final void zzu(Object arg1) {
        ((zzvm)arg1).zzbym.zzsm();
    }

    final Object zzye() {
        return zzyc.zzyg();
    }
}

