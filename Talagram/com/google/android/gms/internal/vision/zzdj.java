package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzdj extends zzdh {
    private static final Class zzmq;

    static {
        zzdj.zzmq = Collections.unmodifiableList(Collections.emptyList()).getClass();
    }

    zzdj(zzdi arg1) {
        this();
    }

    private zzdj() {
        super(null);
    }

    final void zza(Object arg4, long arg5) {
        zzdg v0_1;
        Object v0 = zzfl.zzo(arg4, arg5);
        if((v0 instanceof zzdg)) {
            v0_1 = ((zzdg)v0).zzcl();
        }
        else if(zzdj.zzmq.isAssignableFrom(v0.getClass())) {
            return;
        }
        else {
            List v0_2 = Collections.unmodifiableList(((List)v0));
        }

        zzfl.zza(arg4, arg5, v0_1);
    }

    final void zza(Object arg5, Object arg6, long arg7) {
        ArrayList v2;
        ArrayList v1_2;
        List v6 = zzdj.zzb(arg6, arg7);
        int v0 = v6.size();
        List v1 = zzdj.zzb(arg5, arg7);
        if(v1.isEmpty()) {
            if((v1 instanceof zzdg)) {
                zzdf v1_1 = new zzdf(v0);
            }
            else {
                v1_2 = new ArrayList(v0);
            }

            zzfl.zza(arg5, arg7, v1_2);
        }
        else {
            if(zzdj.zzmq.isAssignableFrom(v1.getClass())) {
                v2 = new ArrayList(v1.size() + v0);
                v2.addAll(((Collection)v1));
            }
            else if((v1 instanceof zzfi)) {
                zzdf v2_1 = new zzdf(v1.size() + v0);
                ((zzbi)v2_1).addAll(((Collection)v1));
            }
            else {
                goto label_34;
            }

            zzfl.zza(arg5, arg7, v2);
            v1_2 = v2;
        }

    label_34:
        v0 = ((List)v1_2).size();
        int v2_2 = v6.size();
        if(v0 > 0 && v2_2 > 0) {
            ((List)v1_2).addAll(((Collection)v6));
        }

        if(v0 > 0) {
            v6 = ((List)v1_2);
        }

        zzfl.zza(arg5, arg7, v6);
    }

    private static List zzb(Object arg0, long arg1) {
        return zzfl.zzo(arg0, arg1);
    }
}

