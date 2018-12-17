package com.google.android.gms.internal.vision;

import java.util.Collection;

final class zzdk extends zzdh {
    zzdk(zzdi arg1) {
        this();
    }

    private zzdk() {
        super(null);
    }

    final void zza(Object arg1, long arg2) {
        zzdk.zzc(arg1, arg2).zzao();
    }

    final void zza(Object arg5, Object arg6, long arg7) {
        zzcw v0 = zzdk.zzc(arg5, arg7);
        zzcw v6 = zzdk.zzc(arg6, arg7);
        int v1 = v0.size();
        int v2 = v6.size();
        if(v1 > 0 && v2 > 0) {
            if(!v0.zzan()) {
                v0 = v0.zzk(v2 + v1);
            }

            v0.addAll(((Collection)v6));
        }

        if(v1 > 0) {
            v6 = v0;
        }

        zzfl.zza(arg5, arg7, v6);
    }

    private static zzcw zzc(Object arg0, long arg1) {
        return zzfl.zzo(arg0, arg1);
    }
}

