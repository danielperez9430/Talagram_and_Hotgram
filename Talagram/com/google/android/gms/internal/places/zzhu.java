package com.google.android.gms.internal.places;

import java.util.Collection;
import java.util.List;

final class zzhu extends zzhr {
    zzhu(zzhs arg1) {
        this();
    }

    private zzhu() {
        super(null);
    }

    final List zzb(Object arg3, long arg4) {
        zzhg v0 = zzhu.zze(arg3, arg4);
        if(!v0.zzba()) {
            int v1 = v0.size();
            if(v1 == 0) {
                v1 = 10;
            }
            else {
                v1 <<= 1;
            }

            v0 = v0.zzae(v1);
            zzjw.zzb(arg3, arg4, v0);
        }

        return ((List)v0);
    }

    final void zzb(Object arg5, Object arg6, long arg7) {
        zzhg v0 = zzhu.zze(arg5, arg7);
        zzhg v6 = zzhu.zze(arg6, arg7);
        int v1 = v0.size();
        int v2 = v6.size();
        if(v1 > 0 && v2 > 0) {
            if(!v0.zzba()) {
                v0 = v0.zzae(v2 + v1);
            }

            v0.addAll(((Collection)v6));
        }

        if(v1 > 0) {
            v6 = v0;
        }

        zzjw.zzb(arg5, arg7, v6);
    }

    final void zzc(Object arg1, long arg2) {
        zzhu.zze(arg1, arg2).zzbb();
    }

    private static zzhg zze(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1);
    }
}

