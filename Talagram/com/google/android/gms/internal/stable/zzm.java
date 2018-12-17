package com.google.android.gms.internal.stable;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zzm {
    private final ConcurrentHashMap zzahj;
    private final ReferenceQueue zzahk;

    zzm() {
        super();
        this.zzahj = new ConcurrentHashMap(16, 0.75f, 10);
        this.zzahk = new ReferenceQueue();
    }

    public final List zza(Throwable arg4, boolean arg5) {
        while(true) {
            Reference v5 = this.zzahk.poll();
            if(v5 == null) {
                break;
            }

            this.zzahj.remove(v5);
        }

        Object v5_1 = this.zzahj.get(new zzn(arg4, null));
        if(v5_1 != null) {
            return ((List)v5_1);
        }

        Vector v5_2 = new Vector(2);
        Object v4 = this.zzahj.putIfAbsent(new zzn(arg4, this.zzahk), v5_2);
        if(v4 == null) {
            return ((List)v5_2);
        }

        return ((List)v4);
    }
}

