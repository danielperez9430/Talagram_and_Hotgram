package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zzc {
    private final ConcurrentHashMap zzd;
    private final ReferenceQueue zze;

    zzc() {
        super();
        this.zzd = new ConcurrentHashMap(16, 0.75f, 10);
        this.zze = new ReferenceQueue();
    }

    public final List zza(Throwable arg4, boolean arg5) {
        while(true) {
            Reference v5 = this.zze.poll();
            if(v5 == null) {
                break;
            }

            this.zzd.remove(v5);
        }

        Object v5_1 = this.zzd.get(new zzd(arg4, null));
        if(v5_1 != null) {
            return ((List)v5_1);
        }

        Vector v5_2 = new Vector(2);
        Object v4 = this.zzd.putIfAbsent(new zzd(arg4, this.zze), v5_2);
        if(v4 == null) {
            return ((List)v5_2);
        }

        return ((List)v4);
    }
}

