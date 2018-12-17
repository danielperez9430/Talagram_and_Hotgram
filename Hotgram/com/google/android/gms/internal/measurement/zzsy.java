package com.google.android.gms.internal.measurement;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzsy {
    private final ConcurrentHashMap zzbrw;
    private final ReferenceQueue zzbrx;

    zzsy() {
        super();
        this.zzbrw = new ConcurrentHashMap(16, 0.75f, 10);
        this.zzbrx = new ReferenceQueue();
    }

    public final List zza(Throwable arg2, boolean arg3) {
        while(true) {
            Reference v3 = this.zzbrx.poll();
            if(v3 == null) {
                break;
            }

            this.zzbrw.remove(v3);
        }

        return this.zzbrw.get(new zzsz(arg2, null));
    }
}

