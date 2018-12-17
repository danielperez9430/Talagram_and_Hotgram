package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzwf extends zzwd {
    private static final Class zzcal;

    static {
        zzwf.zzcal = Collections.unmodifiableList(Collections.emptyList()).getClass();
    }

    zzwf(zzwe arg1) {
        this();
    }

    private zzwf() {
        super(null);
    }

    private static List zza(Object arg3, long arg4, int arg6) {
        ArrayList v1;
        ArrayList v0_3;
        zzvs v0_2;
        zzwb v0_1;
        List v0 = zzwf.zzc(arg3, arg4);
        if(v0.isEmpty()) {
            if((v0 instanceof zzwc)) {
                v0_1 = new zzwb(arg6);
            }
            else {
                if(((v0 instanceof zzxe)) && ((v0 instanceof zzvs))) {
                    v0_2 = ((zzvs)v0).zzak(arg6);
                    goto label_17;
                }

                v0_3 = new ArrayList(arg6);
            }

            goto label_17;
        }
        else {
            if(zzwf.zzcal.isAssignableFrom(v0.getClass())) {
                v1 = new ArrayList(v0.size() + arg6);
                v1.addAll(((Collection)v0));
            }
            else if((v0 instanceof zzye)) {
                zzwb v1_1 = new zzwb(v0.size() + arg6);
                ((zztz)v1_1).addAll(((Collection)v0));
            }
            else {
                goto label_39;
            }

            zzyh.zza(arg3, arg4, v1);
            v0_3 = v1;
            goto label_50;
        label_39:
            if(!(v0 instanceof zzxe)) {
                goto label_50;
            }

            if(!(v0 instanceof zzvs)) {
                goto label_50;
            }

            List v1_2 = v0;
            if(((zzvs)v1_2).zztw()) {
                goto label_50;
            }

            v0_2 = ((zzvs)v1_2).zzak(v0.size() + arg6);
        label_17:
            zzyh.zza(arg3, arg4, v0_1);
        }

    label_50:
        return ((List)v0_1);
    }

    final List zza(Object arg2, long arg3) {
        return zzwf.zza(arg2, arg3, 10);
    }

    final void zza(Object arg4, Object arg5, long arg6) {
        List v5 = zzwf.zzc(arg5, arg6);
        List v0 = zzwf.zza(arg4, arg6, v5.size());
        int v1 = v0.size();
        int v2 = v5.size();
        if(v1 > 0 && v2 > 0) {
            v0.addAll(((Collection)v5));
        }

        if(v1 > 0) {
            v5 = v0;
        }

        zzyh.zza(arg4, arg6, v5);
    }

    final void zzb(Object arg4, long arg5) {
        List v0_2;
        Object v0 = zzyh.zzp(arg4, arg5);
        if((v0 instanceof zzwc)) {
            zzwc v0_1 = ((zzwc)v0).zzww();
        }
        else if(zzwf.zzcal.isAssignableFrom(v0.getClass())) {
            return;
        }
        else {
            if(((v0 instanceof zzxe)) && ((v0 instanceof zzvs))) {
                if(((zzvs)v0).zztw()) {
                    ((zzvs)v0).zzsm();
                }

                return;
            }

            v0_2 = Collections.unmodifiableList(((List)v0));
        }

        zzyh.zza(arg4, arg5, v0_2);
    }

    private static List zzc(Object arg0, long arg1) {
        return zzyh.zzp(arg0, arg1);
    }
}

