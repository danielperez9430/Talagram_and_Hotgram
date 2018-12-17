package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

final class zzdt implements zzds {
    zzdt() {
        super();
    }

    public final int zzb(int arg1, Object arg2, Object arg3) {
        if(((zzdr)arg2).isEmpty()) {
            return 0;
        }

        Iterator v1 = ((zzdr)arg2).entrySet().iterator();
        if(!v1.hasNext()) {
            return 0;
        }

        Object v1_1 = v1.next();
        ((Map$Entry)v1_1).getKey();
        ((Map$Entry)v1_1).getValue();
        throw new NoSuchMethodError();
    }

    public final Object zzb(Object arg2, Object arg3) {
        zzdr v2;
        if(!((zzdr)arg3).isEmpty()) {
            if(!((zzdr)arg2).isMutable()) {
                v2 = ((zzdr)arg2).zzcq();
            }

            v2.zza(((zzdr)arg3));
        }

        return v2;
    }

    public final Map zzh(Object arg1) {
        return arg1;
    }

    public final Map zzi(Object arg1) {
        return arg1;
    }

    public final boolean zzj(Object arg1) {
        if(!((zzdr)arg1).isMutable()) {
            return 1;
        }

        return 0;
    }

    public final Object zzk(Object arg2) {
        arg2.zzao();
        return arg2;
    }

    public final Object zzl(Object arg1) {
        return zzdr.zzcp().zzcq();
    }

    public final zzdq zzm(Object arg1) {
        throw new NoSuchMethodError();
    }
}

