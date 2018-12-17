package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

final class zzid implements zzic {
    zzid() {
        super();
    }

    public final int zzc(int arg1, Object arg2, Object arg3) {
        if(((zzib)arg2).isEmpty()) {
            return 0;
        }

        Iterator v1 = ((zzib)arg2).entrySet().iterator();
        if(!v1.hasNext()) {
            return 0;
        }

        Object v1_1 = v1.next();
        ((Map$Entry)v1_1).getKey();
        ((Map$Entry)v1_1).getValue();
        throw new NoSuchMethodError();
    }

    public final Object zzc(Object arg2, Object arg3) {
        zzib v2;
        if(!((zzib)arg3).isEmpty()) {
            if(!((zzib)arg2).isMutable()) {
                v2 = ((zzib)arg2).zzeq();
            }

            v2.zzb(((zzib)arg3));
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
        if(!((zzib)arg1).isMutable()) {
            return 1;
        }

        return 0;
    }

    public final Object zzk(Object arg2) {
        arg2.zzbb();
        return arg2;
    }

    public final Object zzl(Object arg1) {
        return zzib.zzep().zzeq();
    }

    public final zzia zzm(Object arg1) {
        throw new NoSuchMethodError();
    }
}

