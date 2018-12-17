package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzht extends zzhr {
    private static final Class zzun;

    static {
        zzht.zzun = Collections.unmodifiableList(Collections.emptyList()).getClass();
    }

    zzht(zzhs arg1) {
        this();
    }

    private zzht() {
        super(null);
    }

    private static List zzb(Object arg3, long arg4, int arg6) {
        zzhp v1_1;
        ArrayList v0_2;
        zzhp v0_1;
        List v0 = zzht.zzd(arg3, arg4);
        if(v0.isEmpty()) {
            if((v0 instanceof zzhq)) {
                v0_1 = new zzhp(arg6);
            }
            else {
                v0_2 = new ArrayList(arg6);
            }

            zzjw.zzb(arg3, arg4, v0_1);
        }
        else {
            if(zzht.zzun.isAssignableFrom(v0.getClass())) {
                ArrayList v1 = new ArrayList(v0.size() + arg6);
                v1.addAll(((Collection)v0));
            }
            else if((v0 instanceof zzjt)) {
                v1_1 = new zzhp(v0.size() + arg6);
                ((zzfk)v1_1).addAll(((Collection)v0));
            }
            else {
                goto label_32;
            }

            zzjw.zzb(arg3, arg4, v1_1);
            v0_2 = ((ArrayList)v1_1);
        }

    label_32:
        return ((List)v0_1);
    }

    final List zzb(Object arg2, long arg3) {
        return zzht.zzb(arg2, arg3, 10);
    }

    final void zzb(Object arg4, Object arg5, long arg6) {
        List v5 = zzht.zzd(arg5, arg6);
        List v0 = zzht.zzb(arg4, arg6, v5.size());
        int v1 = v0.size();
        int v2 = v5.size();
        if(v1 > 0 && v2 > 0) {
            v0.addAll(((Collection)v5));
        }

        if(v1 > 0) {
            v5 = v0;
        }

        zzjw.zzb(arg4, arg6, v5);
    }

    final void zzc(Object arg4, long arg5) {
        zzhq v0_1;
        Object v0 = zzjw.zzq(arg4, arg5);
        if((v0 instanceof zzhq)) {
            v0_1 = ((zzhq)v0).zzel();
        }
        else if(zzht.zzun.isAssignableFrom(v0.getClass())) {
            return;
        }
        else {
            List v0_2 = Collections.unmodifiableList(((List)v0));
        }

        zzjw.zzb(arg4, arg5, v0_1);
    }

    private static List zzd(Object arg0, long arg1) {
        return zzjw.zzq(arg0, arg1);
    }
}

