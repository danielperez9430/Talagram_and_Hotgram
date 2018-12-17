package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

final class zzdk implements zzdj {
    zzdk() {
        super();
    }

    public final int zzb(int arg1, Object arg2, Object arg3) {
        if(((zzdi)arg2).isEmpty()) {
            return 0;
        }

        Iterator v1 = ((zzdi)arg2).entrySet().iterator();
        if(!v1.hasNext()) {
            return 0;
        }

        Object v1_1 = v1.next();
        ((Map$Entry)v1_1).getKey();
        ((Map$Entry)v1_1).getValue();
        throw new NoSuchMethodError();
    }

    public final Object zzb(Object arg2, Object arg3) {
        zzdi v2;
        if(!((zzdi)arg3).isEmpty()) {
            if(!((zzdi)arg2).isMutable()) {
                v2 = ((zzdi)arg2).zzca();
            }

            v2.zza(((zzdi)arg3));
        }

        return v2;
    }

    public final Map zzg(Object arg1) {
        return arg1;
    }

    public final Map zzh(Object arg1) {
        return arg1;
    }

    public final boolean zzi(Object arg1) {
        if(!((zzdi)arg1).isMutable()) {
            return 1;
        }

        return 0;
    }

    public final Object zzj(Object arg2) {
        arg2.zzv();
        return arg2;
    }

    public final Object zzk(Object arg1) {
        return zzdi.zzbz().zzca();
    }

    public final zzdh zzl(Object arg1) {
        throw new NoSuchMethodError();
    }
}

