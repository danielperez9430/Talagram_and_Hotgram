package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;

final class zzby {
    private final zzei zzgt;
    private boolean zzgu;
    private boolean zzgv;
    private static final zzby zzgw;

    static {
        zzby.zzgw = new zzby(true);
    }

    private zzby(boolean arg1) {
        super();
        this.zzgv = false;
        this.zzgt = zzei.zzaj(0);
        this.zzv();
    }

    private zzby() {
        super();
        this.zzgv = false;
        this.zzgt = zzei.zzaj(16);
    }

    public final Object clone() {
        zzby v0 = new zzby();
        int v1;
        for(v1 = 0; v1 < this.zzgt.zzdr(); ++v1) {
            Map$Entry v2 = this.zzgt.zzak(v1);
            v0.zza(v2.getKey(), v2.getValue());
        }

        Iterator v1_1 = this.zzgt.zzds().iterator();
        while(v1_1.hasNext()) {
            Object v2_1 = v1_1.next();
            v0.zza(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        v0.zzgv = this.zzgv;
        return v0;
    }

    final Iterator descendingIterator() {
        if(this.zzgv) {
            return new zzcu(this.zzgt.zzdt().iterator());
        }

        return this.zzgt.zzdt().iterator();
    }

    public final boolean equals(Object arg2) {
        if(this == (((zzby)arg2))) {
            return 1;
        }

        if(!(arg2 instanceof zzby)) {
            return 0;
        }

        return this.zzgt.equals(((zzby)arg2).zzgt);
    }

    public final int hashCode() {
        return this.zzgt.hashCode();
    }

    final boolean isEmpty() {
        return this.zzgt.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzgu;
    }

    public final boolean isInitialized() {
        int v1;
        for(v1 = 0; v1 < this.zzgt.zzdr(); ++v1) {
            if(!zzby.zzb(this.zzgt.zzak(v1))) {
                return 0;
            }
        }

        Iterator v1_1 = this.zzgt.zzds().iterator();
        do {
            if(!v1_1.hasNext()) {
                return 1;
            }
        }
        while(zzby.zzb(v1_1.next()));

        return 0;
    }

    public final Iterator iterator() {
        if(this.zzgv) {
            return new zzcu(this.zzgt.entrySet().iterator());
        }

        return this.zzgt.entrySet().iterator();
    }

    static int zza(zzfl arg1, int arg2, Object arg3) {
        arg2 = zzbn.zzr(arg2);
        if(arg1 == zzfl.zzql) {
            zzci.zzf(arg3);
            arg2 <<= 1;
        }

        return arg2 + zzby.zzb(arg1, arg3);
    }

    private final Object zza(zzca arg2) {
        zzdo v2_1;
        Object v2 = this.zzgt.get(arg2);
        if((v2 instanceof zzcr)) {
            v2_1 = zzcr.zzbr();
        }

        return v2_1;
    }

    static void zza(zzbn arg1, zzfl arg2, int arg3, Object arg4) {
        if(arg2 == zzfl.zzql) {
            zzci.zzf(((zzdo)arg4));
            arg1.zzb(arg3, 3);
            ((zzdo)arg4).zzb(arg1);
            arg1.zzb(arg3, 4);
            return;
        }

        arg1.zzb(arg3, arg2.zzel());
        switch(zzbz.zzgq[arg2.ordinal()]) {
            case 1: {
                goto label_78;
            }
            case 2: {
                goto label_75;
            }
            case 3: {
                goto label_72;
            }
            case 4: {
                goto label_69;
            }
            case 5: {
                goto label_66;
            }
            case 6: {
                goto label_63;
            }
            case 7: {
                goto label_60;
            }
            case 8: {
                goto label_57;
            }
            case 9: {
                goto label_55;
            }
            case 10: {
                goto label_53;
            }
            case 11: {
                goto label_47;
            }
            case 12: {
                goto label_39;
            }
            case 13: {
                goto label_36;
            }
            case 14: {
                goto label_33;
            }
            case 15: {
                goto label_30;
            }
            case 16: {
                goto label_27;
            }
            case 17: {
                goto label_24;
            }
            case 18: {
                goto label_16;
            }
        }

        return;
    label_33:
        arg1.zzq(((Integer)arg4).intValue());
        return;
    label_66:
        arg1.zzn(((Integer)arg4).intValue());
        return;
    label_36:
        arg1.zzo(((Integer)arg4).intValue());
        return;
    label_69:
        arg1.zzb(((Long)arg4).longValue());
        return;
    label_39:
        if((arg4 instanceof zzbb)) {
            arg1.zza(((zzbb)arg4));
            return;
        }

        arg1.zzd(((byte[])arg4), 0, arg4.length);
        return;
    label_72:
        arg1.zzb(((Long)arg4).longValue());
        return;
    label_75:
        arg1.zza(((Float)arg4).floatValue());
        return;
    label_78:
        arg1.zza(((Double)arg4).doubleValue());
        return;
    label_47:
        if((arg4 instanceof zzbb)) {
            arg1.zza(((zzbb)arg4));
            return;
        }

        arg1.zzg(((String)arg4));
        return;
    label_16:
        if((arg4 instanceof zzcj)) {
            arg1.zzn(((zzcj)arg4).zzc());
            return;
        }

        arg1.zzn(((Integer)arg4).intValue());
        return;
    label_53:
        arg1.zzb(((zzdo)arg4));
        return;
    label_55:
        ((zzdo)arg4).zzb(arg1);
        return;
    label_24:
        arg1.zzc(((Long)arg4).longValue());
        return;
    label_57:
        arg1.zza(((Boolean)arg4).booleanValue());
        return;
    label_27:
        arg1.zzp(((Integer)arg4).intValue());
        return;
    label_60:
        arg1.zzq(((Integer)arg4).intValue());
        return;
    label_30:
        arg1.zzd(((Long)arg4).longValue());
        return;
    label_63:
        arg1.zzd(((Long)arg4).longValue());
    }

    private final void zza(zzca arg6, Object arg7) {
        ArrayList v7;
        if(!arg6.zzaw()) {
            zzby.zza(arg6.zzau(), arg7);
        }
        else if((arg7 instanceof List)) {
            ArrayList v0 = new ArrayList();
            ((List)v0).addAll(((Collection)arg7));
            v7 = v0;
            int v1 = v7.size();
            int v2 = 0;
            while(v2 < v1) {
                Object v3 = v7.get(v2);
                ++v2;
                zzby.zza(arg6.zzau(), v3);
            }

            v7 = v0;
        }
        else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }

        if((v7 instanceof zzcr)) {
            this.zzgv = true;
        }

        this.zzgt.zza(((Comparable)arg6), v7);
    }

    private static void zza(zzfl arg2, Object arg3) {
        zzci.checkNotNull(arg3);
        boolean v1 = false;
        switch(zzbz.zzgx[arg2.zzek().ordinal()]) {
            case 1: {
                goto label_35;
            }
            case 2: {
                goto label_33;
            }
            case 3: {
                goto label_31;
            }
            case 4: {
                goto label_29;
            }
            case 5: {
                goto label_27;
            }
            case 6: {
                goto label_25;
            }
            case 7: {
                goto label_20;
            }
            case 8: {
                goto label_14;
            }
            case 9: {
                goto label_9;
            }
        }

        goto label_37;
    label_33:
        boolean v0 = arg3 instanceof Long;
        goto label_36;
    label_35:
        v0 = arg3 instanceof Integer;
        goto label_36;
    label_20:
        if((arg3 instanceof zzbb)) {
            goto label_18;
        }

        if(!(arg3 instanceof byte[])) {
            goto label_37;
        }

        goto label_18;
    label_25:
        v0 = arg3 instanceof String;
        goto label_36;
    label_9:
        if((arg3 instanceof zzdo)) {
            goto label_18;
        }

        if(!(arg3 instanceof zzcr)) {
            goto label_37;
        }

        goto label_18;
    label_27:
        v0 = arg3 instanceof Boolean;
        goto label_36;
    label_29:
        v0 = arg3 instanceof Double;
        goto label_36;
    label_14:
        if(!(arg3 instanceof Integer) && !(arg3 instanceof zzcj)) {
        }
        else {
        label_18:
            v1 = true;
            goto label_37;
        label_31:
            v0 = arg3 instanceof Float;
        label_36:
            v1 = v0;
        }

    label_37:
        if(v1) {
            return;
        }

        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    public final void zza(zzby arg3) {
        int v0;
        for(v0 = 0; v0 < arg3.zzgt.zzdr(); ++v0) {
            this.zzc(arg3.zzgt.zzak(v0));
        }

        Iterator v3 = arg3.zzgt.zzds().iterator();
        while(v3.hasNext()) {
            this.zzc(v3.next());
        }
    }

    public static zzby zzar() {
        return zzby.zzgw;
    }

    public final int zzas() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzgt.zzdr()) {
            Map$Entry v2 = this.zzgt.zzak(v0);
            v1 += zzby.zzb(v2.getKey(), v2.getValue());
            ++v0;
        }

        Iterator v0_1 = this.zzgt.zzds().iterator();
        while(v0_1.hasNext()) {
            Object v2_1 = v0_1.next();
            v1 += zzby.zzb(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        return v1;
    }

    public final int zzat() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzgt.zzdr()) {
            v1 += zzby.zzd(this.zzgt.zzak(v0));
            ++v0;
        }

        Iterator v0_1 = this.zzgt.zzds().iterator();
        while(v0_1.hasNext()) {
            v1 += zzby.zzd(v0_1.next());
        }

        return v1;
    }

    private static int zzb(zzfl arg1, Object arg2) {
        switch(zzbz.zzgq[arg1.ordinal()]) {
            case 1: {
                goto label_72;
            }
            case 2: {
                goto label_69;
            }
            case 3: {
                goto label_66;
            }
            case 4: {
                goto label_63;
            }
            case 5: {
                goto label_60;
            }
            case 6: {
                goto label_57;
            }
            case 7: {
                goto label_54;
            }
            case 8: {
                goto label_51;
            }
            case 9: {
                goto label_49;
            }
            case 10: {
                goto label_43;
            }
            case 11: {
                goto label_37;
            }
            case 12: {
                goto label_31;
            }
            case 13: {
                goto label_28;
            }
            case 14: {
                goto label_25;
            }
            case 15: {
                goto label_22;
            }
            case 16: {
                goto label_19;
            }
            case 17: {
                goto label_16;
            }
            case 18: {
                goto label_8;
            }
        }

        throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
    label_66:
        return zzbn.zze(((Long)arg2).longValue());
    label_69:
        return zzbn.zzb(((Float)arg2).floatValue());
    label_37:
        if((arg2 instanceof zzbb)) {
            return zzbn.zzb(((zzbb)arg2));
        }

        return zzbn.zzh(((String)arg2));
    label_72:
        return zzbn.zzb(((Double)arg2).doubleValue());
    label_8:
        if((arg2 instanceof zzcj)) {
            return zzbn.zzx(((zzcj)arg2).zzc());
        }

        return zzbn.zzx(((Integer)arg2).intValue());
    label_43:
        if((arg2 instanceof zzcr)) {
            return zzbn.zza(((zzcv)arg2));
        }

        return zzbn.zzc(((zzdo)arg2));
    label_16:
        return zzbn.zzg(((Long)arg2).longValue());
    label_49:
        return zzbn.zzd(((zzdo)arg2));
    label_51:
        return zzbn.zzb(((Boolean)arg2).booleanValue());
    label_19:
        return zzbn.zzu(((Integer)arg2).intValue());
    label_54:
        return zzbn.zzv(((Integer)arg2).intValue());
    label_22:
        return zzbn.zzi(((Long)arg2).longValue());
    label_57:
        return zzbn.zzh(((Long)arg2).longValue());
    label_25:
        return zzbn.zzw(((Integer)arg2).intValue());
    label_60:
        return zzbn.zzs(((Integer)arg2).intValue());
    label_28:
        return zzbn.zzt(((Integer)arg2).intValue());
    label_63:
        return zzbn.zzf(((Long)arg2).longValue());
    label_31:
        if((arg2 instanceof zzbb)) {
            return zzbn.zzb(((zzbb)arg2));
        }

        return zzbn.zzd(((byte[])arg2));
    }

    private static int zzb(zzca arg3, Object arg4) {
        Iterator v3;
        zzfl v0 = arg3.zzau();
        int v1 = arg3.zzc();
        if(arg3.zzaw()) {
            int v2 = 0;
            if(arg3.zzax()) {
                v3 = ((List)arg4).iterator();
                while(v3.hasNext()) {
                    v2 += zzby.zzb(v0, v3.next());
                }

                return zzbn.zzr(v1) + v2 + zzbn.zzz(v2);
            }

            v3 = ((List)arg4).iterator();
            while(v3.hasNext()) {
                v2 += zzby.zza(v0, v1, v3.next());
            }

            return v2;
        }

        return zzby.zza(v0, v1, arg4);
    }

    private static boolean zzb(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        if(((zzca)v0).zzav() == zzfq.zzrf) {
            if(((zzca)v0).zzaw()) {
                Iterator v4 = arg4.getValue().iterator();
                do {
                    if(v4.hasNext()) {
                        if(v4.next().isInitialized()) {
                            continue;
                        }

                        return 0;
                    }

                    return 1;
                }
                while(true);

                return 0;
            }
            else {
                Object v4_1 = arg4.getValue();
                if((v4_1 instanceof zzdo)) {
                    if(!((zzdo)v4_1).isInitialized()) {
                        return 0;
                    }
                }
                else if((v4_1 instanceof zzcr)) {
                    return 1;
                }
                else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }

        return 1;
    }

    private final void zzc(Map$Entry arg5) {
        ArrayList v1_1;
        Object v1;
        zzdo v5_1;
        Object v0 = arg5.getKey();
        Object v5 = arg5.getValue();
        if((v5 instanceof zzcr)) {
            v5_1 = zzcr.zzbr();
        }

        if(((zzca)v0).zzaw()) {
            v1 = this.zza(((zzca)v0));
            if(v1 == null) {
                v1_1 = new ArrayList();
            }

            Iterator v5_2 = ((List)v5).iterator();
            while(v5_2.hasNext()) {
                v1_1.add(zzby.zzd(v5_2.next()));
            }

            this.zzgt.zza(((Comparable)v0), v1_1);
            return;
        }

        if(((zzca)v0).zzav() == zzfq.zzrf) {
            v1 = this.zza(((zzca)v0));
            if(v1 == null) {
                this.zzgt.zza(((Comparable)v0), zzby.zzd(v5));
                return;
            }

            if((v1 instanceof zzdv)) {
                zzdv v5_3 = ((zzca)v0).zza(((zzdv)v1), ((zzdv)v5));
            }
            else {
                v5_1 = ((zzca)v0).zza(((zzdo)v1).zzbc(), ((zzdo)v5)).zzbj();
            }

            this.zzgt.zza(((Comparable)v0), v5_1);
            return;
        }

        this.zzgt.zza(((Comparable)v0), zzby.zzd(v5));
    }

    private static Object zzd(Object arg3) {
        if((arg3 instanceof zzdv)) {
            return ((zzdv)arg3).zzci();
        }

        if((arg3 instanceof byte[])) {
            byte[] v0 = new byte[arg3.length];
            System.arraycopy(arg3, 0, v0, 0, arg3.length);
            return v0;
        }

        return arg3;
    }

    private static int zzd(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        Object v1 = arg4.getValue();
        if(((zzca)v0).zzav() == zzfq.zzrf && !((zzca)v0).zzaw() && !((zzca)v0).zzax()) {
            if((v1 instanceof zzcr)) {
                return zzbn.zzb(arg4.getKey().zzc(), ((zzcv)v1));
            }
            else {
                return zzbn.zzd(arg4.getKey().zzc(), ((zzdo)v1));
            }
        }

        return zzby.zzb(((zzca)v0), v1);
    }

    public final void zzv() {
        if(this.zzgu) {
            return;
        }

        this.zzgt.zzv();
        this.zzgu = true;
    }
}

