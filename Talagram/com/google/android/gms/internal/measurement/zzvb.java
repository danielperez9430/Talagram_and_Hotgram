package com.google.android.gms.internal.measurement;

import java.util.Map$Entry;

final class zzvb extends zzva {
    zzvb() {
        super();
    }

    final Object zza(zzuz arg1, zzwt arg2, int arg3) {
        return arg1.zza(arg2, arg3);
    }

    final Object zza(zzxi arg1, Object arg2, zzuz arg3, zzvd arg4, Object arg5, zzyb arg6) {
        throw new NoSuchMethodError();
    }

    final void zza(zzud arg1, Object arg2, zzuz arg3, zzvd arg4) {
        throw new NoSuchMethodError();
    }

    final void zza(zzxi arg1, Object arg2, zzuz arg3, zzvd arg4) {
        throw new NoSuchMethodError();
    }

    final void zza(zzyw arg1, Map$Entry arg2) {
        arg2.getKey();
        throw new NoSuchMethodError();
    }

    final void zza(Object arg1, zzvd arg2) {
        ((zzc)arg1).zzbys = arg2;
    }

    final int zzb(Map$Entry arg1) {
        arg1.getKey();
        throw new NoSuchMethodError();
    }

    final boolean zze(zzwt arg1) {
        return arg1 instanceof zzc;
    }

    final zzvd zzs(Object arg1) {
        return ((zzc)arg1).zzbys;
    }

    final zzvd zzt(Object arg3) {
        zzvd v0 = ((zzva)this).zzs(arg3);
        if(v0.isImmutable()) {
            Object v0_1 = v0.clone();
            ((zzva)this).zza(arg3, ((zzvd)v0_1));
        }

        return v0;
    }

    final void zzu(Object arg1) {
        ((zzva)this).zzs(arg1).zzsm();
    }
}

