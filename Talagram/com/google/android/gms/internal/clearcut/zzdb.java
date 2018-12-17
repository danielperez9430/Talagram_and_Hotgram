package com.google.android.gms.internal.clearcut;

import java.util.Collection;

final class zzdb extends zzcy {
    zzdb(zzcz arg1) {
        this();
    }

    private zzdb() {
        super(null);
    }

    final void zza(Object arg1, long arg2) {
        zzdb.zzc(arg1, arg2).zzv();
    }

    final void zza(Object arg5, Object arg6, long arg7) {
        zzcn v0 = zzdb.zzc(arg5, arg7);
        zzcn v6 = zzdb.zzc(arg6, arg7);
        int v1 = v0.size();
        int v2 = v6.size();
        if(v1 > 0 && v2 > 0) {
            if(!v0.zzu()) {
                v0 = v0.zzi(v2 + v1);
            }

            v0.addAll(((Collection)v6));
        }

        if(v1 > 0) {
            v6 = v0;
        }

        zzfd.zza(arg5, arg7, v6);
    }

    private static zzcn zzc(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1);
    }
}

