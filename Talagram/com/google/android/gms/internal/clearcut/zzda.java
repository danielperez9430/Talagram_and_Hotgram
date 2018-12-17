package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzda extends zzcy {
    private static final Class zzlv;

    static {
        zzda.zzlv = Collections.unmodifiableList(Collections.emptyList()).getClass();
    }

    zzda(zzcz arg1) {
        this();
    }

    private zzda() {
        super(null);
    }

    final void zza(Object arg4, long arg5) {
        List v0_2;
        Object v0 = zzfd.zzo(arg4, arg5);
        if((v0 instanceof zzcx)) {
            zzcx v0_1 = ((zzcx)v0).zzbu();
        }
        else if(zzda.zzlv.isAssignableFrom(v0.getClass())) {
            return;
        }
        else {
            v0_2 = Collections.unmodifiableList(((List)v0));
        }

        zzfd.zza(arg4, arg5, v0_2);
    }

    final void zza(Object arg5, Object arg6, long arg7) {
        zzcw v2_1;
        ArrayList v1_2;
        List v6 = zzda.zzb(arg6, arg7);
        int v0 = v6.size();
        List v1 = zzda.zzb(arg5, arg7);
        if(v1.isEmpty()) {
            if((v1 instanceof zzcx)) {
                zzcw v1_1 = new zzcw(v0);
            }
            else {
                v1_2 = new ArrayList(v0);
            }

            zzfd.zza(arg5, arg7, v1_2);
        }
        else {
            if(zzda.zzlv.isAssignableFrom(v1.getClass())) {
                ArrayList v2 = new ArrayList(v1.size() + v0);
                v2.addAll(((Collection)v1));
            }
            else if((v1 instanceof zzfa)) {
                v2_1 = new zzcw(v1.size() + v0);
                ((zzav)v2_1).addAll(((Collection)v1));
            }
            else {
                goto label_34;
            }

            zzfd.zza(arg5, arg7, v2_1);
            v1_2 = ((ArrayList)v2_1);
        }

    label_34:
        v0 = v1.size();
        int v2_2 = v6.size();
        if(v0 > 0 && v2_2 > 0) {
            v1.addAll(((Collection)v6));
        }

        if(v0 > 0) {
            v6 = v1;
        }

        zzfd.zza(arg5, arg7, v6);
    }

    private static List zzb(Object arg0, long arg1) {
        return zzfd.zzo(arg0, arg1);
    }
}

