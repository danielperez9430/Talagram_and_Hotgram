package com.google.android.gms.internal.measurement;

import java.util.Comparator;
import java.util.Iterator;

final class zzuf implements Comparator {
    zzuf() {
        super();
    }

    public final int compare(Object arg5, Object arg6) {
        int v2;
        Iterator v0 = ((zzud)arg5).iterator();
        Iterator v1 = ((zzud)arg6).iterator();
        do {
            if((((zzuj)v0).hasNext()) && (((zzuj)v1).hasNext())) {
                v2 = Integer.compare(zzud.zzb(((zzuj)v0).nextByte()), zzud.zzb(((zzuj)v1).nextByte()));
                if(v2 == 0) {
                    continue;
                }

                return v2;
            }

            goto label_13;
        }
        while(true);

        return v2;
    label_13:
        return Integer.compare(((zzud)arg5).size(), ((zzud)arg6).size());
    }
}

