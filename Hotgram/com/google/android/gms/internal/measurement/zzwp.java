package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

final class zzwp implements zzwo {
    zzwp() {
        super();
    }

    public final boolean zzaa(Object arg1) {
        if(!((zzwn)arg1).isMutable()) {
            return 1;
        }

        return 0;
    }

    public final Object zzab(Object arg2) {
        arg2.zzsm();
        return arg2;
    }

    public final Object zzac(Object arg1) {
        return zzwn.zzxa().zzxb();
    }

    public final zzwm zzad(Object arg1) {
        throw new NoSuchMethodError();
    }

    public final int zzb(int arg1, Object arg2, Object arg3) {
        if(((zzwn)arg2).isEmpty()) {
            return 0;
        }

        Iterator v1 = ((zzwn)arg2).entrySet().iterator();
        if(!v1.hasNext()) {
            return 0;
        }

        Object v1_1 = v1.next();
        ((Map$Entry)v1_1).getKey();
        ((Map$Entry)v1_1).getValue();
        throw new NoSuchMethodError();
    }

    public final Object zzc(Object arg2, Object arg3) {
        zzwn v2;
        if(!((zzwn)arg3).isEmpty()) {
            if(!((zzwn)arg2).isMutable()) {
                v2 = ((zzwn)arg2).zzxb();
            }

            v2.zza(((zzwn)arg3));
        }

        return v2;
    }

    public final Map zzy(Object arg1) {
        return arg1;
    }

    public final Map zzz(Object arg1) {
        return arg1;
    }
}

