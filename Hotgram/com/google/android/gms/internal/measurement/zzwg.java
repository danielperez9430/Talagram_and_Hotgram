package com.google.android.gms.internal.measurement;

import java.util.Collection;
import java.util.List;

final class zzwg extends zzwd {
    zzwg(zzwe arg1) {
        this();
    }

    private zzwg() {
        super(null);
    }

    final List zza(Object arg3, long arg4) {
        zzvs v0 = zzwg.zzd(arg3, arg4);
        if(!v0.zztw()) {
            int v1 = v0.size();
            if(v1 == 0) {
                v1 = 10;
            }
            else {
                v1 <<= 1;
            }

            v0 = v0.zzak(v1);
            zzyh.zza(arg3, arg4, v0);
        }

        return ((List)v0);
    }

    final void zza(Object arg5, Object arg6, long arg7) {
        zzvs v0 = zzwg.zzd(arg5, arg7);
        zzvs v6 = zzwg.zzd(arg6, arg7);
        int v1 = v0.size();
        int v2 = v6.size();
        if(v1 > 0 && v2 > 0) {
            if(!v0.zztw()) {
                v0 = v0.zzak(v2 + v1);
            }

            v0.addAll(((Collection)v6));
        }

        if(v1 > 0) {
            v6 = v0;
        }

        zzyh.zza(arg5, arg7, v6);
    }

    final void zzb(Object arg1, long arg2) {
        zzwg.zzd(arg1, arg2).zzsm();
    }

    private static zzvs zzd(Object arg0, long arg1) {
        return zzyh.zzp(arg0, arg1);
    }
}

