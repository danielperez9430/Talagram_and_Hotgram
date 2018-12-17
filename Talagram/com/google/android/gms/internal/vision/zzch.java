package com.google.android.gms.internal.vision;

import java.util.Map$Entry;

final class zzch extends zzcg {
    zzch() {
        super();
    }

    final int zza(Map$Entry arg1) {
        arg1.getKey();
        throw new NoSuchMethodError();
    }

    final void zza(zzfz arg1, Map$Entry arg2) {
        arg2.getKey();
        throw new NoSuchMethodError();
    }

    final void zza(Object arg1, zzcj arg2) {
        ((zzc)arg1).zzkx = arg2;
    }

    final zzcj zzb(Object arg1) {
        return ((zzc)arg1).zzkx;
    }

    final zzcj zzc(Object arg3) {
        Object v0_1;
        zzcj v0 = ((zzcg)this).zzb(arg3);
        if(v0.isImmutable()) {
            v0_1 = v0.clone();
            ((zzcg)this).zza(arg3, ((zzcj)v0_1));
        }

        return ((zzcj)v0_1);
    }

    final void zzd(Object arg1) {
        ((zzcg)this).zzb(arg1).zzao();
    }

    final boolean zze(zzdx arg1) {
        return arg1 instanceof zzc;
    }
}

