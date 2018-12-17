package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;

final class zzcj {
    private final zzeq zzhu;
    private boolean zzhv;
    private boolean zzhw;
    private static final zzcj zzhx;

    static {
        zzcj.zzhx = new zzcj(true);
    }

    private zzcj(boolean arg1) {
        super();
        this.zzhw = false;
        this.zzhu = zzeq.zzam(0);
        this.zzao();
    }

    private zzcj() {
        super();
        this.zzhw = false;
        this.zzhu = zzeq.zzam(16);
    }

    public final Object clone() {
        zzcj v0 = new zzcj();
        int v1;
        for(v1 = 0; v1 < this.zzhu.zzdl(); ++v1) {
            Map$Entry v2 = this.zzhu.zzan(v1);
            v0.zza(v2.getKey(), v2.getValue());
        }

        Iterator v1_1 = this.zzhu.zzdm().iterator();
        while(v1_1.hasNext()) {
            Object v2_1 = v1_1.next();
            v0.zza(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        v0.zzhw = this.zzhw;
        return v0;
    }

    final Iterator descendingIterator() {
        if(this.zzhw) {
            return new zzdd(this.zzhu.zzdn().iterator());
        }

        return this.zzhu.zzdn().iterator();
    }

    public final boolean equals(Object arg2) {
        if(this == (((zzcj)arg2))) {
            return 1;
        }

        if(!(arg2 instanceof zzcj)) {
            return 0;
        }

        return this.zzhu.equals(((zzcj)arg2).zzhu);
    }

    public final int hashCode() {
        return this.zzhu.hashCode();
    }

    final boolean isEmpty() {
        return this.zzhu.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzhv;
    }

    public final boolean isInitialized() {
        int v1;
        for(v1 = 0; v1 < this.zzhu.zzdl(); ++v1) {
            if(!zzcj.zzb(this.zzhu.zzan(v1))) {
                return 0;
            }
        }

        Iterator v1_1 = this.zzhu.zzdm().iterator();
        do {
            if(!v1_1.hasNext()) {
                return 1;
            }
        }
        while(zzcj.zzb(v1_1.next()));

        return 0;
    }

    public final Iterator iterator() {
        if(this.zzhw) {
            return new zzdd(this.zzhu.entrySet().iterator());
        }

        return this.zzhu.entrySet().iterator();
    }

    static int zza(zzft arg1, int arg2, Object arg3) {
        arg2 = zzca.zzt(arg2);
        if(arg1 == zzft.zzqf) {
            zzct.zzf(arg3);
            arg2 <<= 1;
        }

        return arg2 + zzcj.zzb(arg1, arg3);
    }

    private final Object zza(zzcl arg2) {
        Object v2 = this.zzhu.get(arg2);
        if((v2 instanceof zzda)) {
            zzdx v2_1 = zzda.zzci();
        }

        return v2;
    }

    static void zza(zzca arg1, zzft arg2, int arg3, Object arg4) {
        if(arg2 == zzft.zzqf) {
            zzct.zzf(((zzdx)arg4));
            arg1.zzd(arg3, 3);
            ((zzdx)arg4).zzb(arg1);
            arg1.zzd(arg3, 4);
            return;
        }

        arg1.zzd(arg3, arg2.zzee());
        switch(zzck.zzhz[arg2.ordinal()]) {
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
        arg1.zzs(((Integer)arg4).intValue());
        return;
    label_66:
        arg1.zzp(((Integer)arg4).intValue());
        return;
    label_36:
        arg1.zzq(((Integer)arg4).intValue());
        return;
    label_69:
        arg1.zzb(((Long)arg4).longValue());
        return;
    label_39:
        if((arg4 instanceof zzbo)) {
            arg1.zza(((zzbo)arg4));
            return;
        }

        arg1.zzd(((byte[])arg4), 0, arg4.length);
        return;
    label_72:
        arg1.zzb(((Long)arg4).longValue());
        return;
    label_75:
        arg1.zzc(((Float)arg4).floatValue());
        return;
    label_78:
        arg1.zza(((Double)arg4).doubleValue());
        return;
    label_47:
        if((arg4 instanceof zzbo)) {
            arg1.zza(((zzbo)arg4));
            return;
        }

        arg1.zzh(((String)arg4));
        return;
    label_16:
        if((arg4 instanceof zzcu)) {
            arg1.zzp(((zzcu)arg4).zzbn());
            return;
        }

        arg1.zzp(((Integer)arg4).intValue());
        return;
    label_53:
        arg1.zzb(((zzdx)arg4));
        return;
    label_55:
        ((zzdx)arg4).zzb(arg1);
        return;
    label_24:
        arg1.zzc(((Long)arg4).longValue());
        return;
    label_57:
        arg1.zza(((Boolean)arg4).booleanValue());
        return;
    label_27:
        arg1.zzr(((Integer)arg4).intValue());
        return;
    label_60:
        arg1.zzs(((Integer)arg4).intValue());
        return;
    label_30:
        arg1.zzd(((Long)arg4).longValue());
        return;
    label_63:
        arg1.zzd(((Long)arg4).longValue());
    }

    private final void zza(zzcl arg6, Object arg7) {
        ArrayList v7;
        if(!arg6.zzbq()) {
            zzcj.zza(arg6.zzbo(), arg7);
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
                zzcj.zza(arg6.zzbo(), v3);
            }

            v7 = v0;
        }
        else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }

        if((v7 instanceof zzda)) {
            this.zzhw = true;
        }

        this.zzhu.zza(((Comparable)arg6), v7);
    }

    private static void zza(zzft arg2, Object arg3) {
        zzct.checkNotNull(arg3);
        boolean v1 = false;
        switch(zzck.zzhy[arg2.zzed().ordinal()]) {
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
        if((arg3 instanceof zzbo)) {
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
        if((arg3 instanceof zzdx)) {
            goto label_18;
        }

        if(!(arg3 instanceof zzda)) {
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
        if(!(arg3 instanceof Integer) && !(arg3 instanceof zzcu)) {
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

    public final void zza(zzcj arg3) {
        int v0;
        for(v0 = 0; v0 < arg3.zzhu.zzdl(); ++v0) {
            this.zzc(arg3.zzhu.zzan(v0));
        }

        Iterator v3 = arg3.zzhu.zzdm().iterator();
        while(v3.hasNext()) {
            this.zzc(v3.next());
        }
    }

    public final void zzao() {
        if(this.zzhv) {
            return;
        }

        this.zzhu.zzao();
        this.zzhv = true;
    }

    private static int zzb(zzft arg1, Object arg2) {
        switch(zzck.zzhz[arg1.ordinal()]) {
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
        return zzca.zze(((Long)arg2).longValue());
    label_69:
        return zzca.zzd(((Float)arg2).floatValue());
    label_37:
        if((arg2 instanceof zzbo)) {
            return zzca.zzb(((zzbo)arg2));
        }

        return zzca.zzi(((String)arg2));
    label_72:
        return zzca.zzb(((Double)arg2).doubleValue());
    label_8:
        if((arg2 instanceof zzcu)) {
            return zzca.zzz(((zzcu)arg2).zzbn());
        }

        return zzca.zzz(((Integer)arg2).intValue());
    label_43:
        if((arg2 instanceof zzda)) {
            return zzca.zza(((zzde)arg2));
        }

        return zzca.zzc(((zzdx)arg2));
    label_16:
        return zzca.zzg(((Long)arg2).longValue());
    label_49:
        return zzca.zzd(((zzdx)arg2));
    label_51:
        return zzca.zzb(((Boolean)arg2).booleanValue());
    label_19:
        return zzca.zzw(((Integer)arg2).intValue());
    label_54:
        return zzca.zzx(((Integer)arg2).intValue());
    label_22:
        return zzca.zzi(((Long)arg2).longValue());
    label_57:
        return zzca.zzh(((Long)arg2).longValue());
    label_25:
        return zzca.zzy(((Integer)arg2).intValue());
    label_60:
        return zzca.zzu(((Integer)arg2).intValue());
    label_28:
        return zzca.zzv(((Integer)arg2).intValue());
    label_63:
        return zzca.zzf(((Long)arg2).longValue());
    label_31:
        if((arg2 instanceof zzbo)) {
            return zzca.zzb(((zzbo)arg2));
        }

        return zzca.zze(((byte[])arg2));
    }

    private static int zzb(zzcl arg3, Object arg4) {
        Iterator v3;
        zzft v0 = arg3.zzbo();
        int v1 = arg3.zzbn();
        if(arg3.zzbq()) {
            int v2 = 0;
            if(arg3.zzbr()) {
                v3 = ((List)arg4).iterator();
                while(v3.hasNext()) {
                    v2 += zzcj.zzb(v0, v3.next());
                }

                return zzca.zzt(v1) + v2 + zzca.zzab(v2);
            }

            v3 = ((List)arg4).iterator();
            while(v3.hasNext()) {
                v2 += zzcj.zza(v0, v1, v3.next());
            }

            return v2;
        }

        return zzcj.zza(v0, v1, arg4);
    }

    private static boolean zzb(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        if(((zzcl)v0).zzbp() == zzfy.zzqz) {
            if(((zzcl)v0).zzbq()) {
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
                if((v4_1 instanceof zzdx)) {
                    if(!((zzdx)v4_1).isInitialized()) {
                        return 0;
                    }
                }
                else if((v4_1 instanceof zzda)) {
                    return 1;
                }
                else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }

        return 1;
    }

    public static zzcj zzbk() {
        return zzcj.zzhx;
    }

    public final int zzbl() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzhu.zzdl()) {
            Map$Entry v2 = this.zzhu.zzan(v0);
            v1 += zzcj.zzb(v2.getKey(), v2.getValue());
            ++v0;
        }

        Iterator v0_1 = this.zzhu.zzdm().iterator();
        while(v0_1.hasNext()) {
            Object v2_1 = v0_1.next();
            v1 += zzcj.zzb(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        return v1;
    }

    public final int zzbm() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzhu.zzdl()) {
            v1 += zzcj.zzd(this.zzhu.zzan(v0));
            ++v0;
        }

        Iterator v0_1 = this.zzhu.zzdm().iterator();
        while(v0_1.hasNext()) {
            v1 += zzcj.zzd(v0_1.next());
        }

        return v1;
    }

    private final void zzc(Map$Entry arg5) {
        zzee v5_3;
        ArrayList v1_1;
        Object v1;
        zzdx v5_1;
        Object v0 = arg5.getKey();
        Object v5 = arg5.getValue();
        if((v5 instanceof zzda)) {
            v5_1 = zzda.zzci();
        }

        if(((zzcl)v0).zzbq()) {
            v1 = this.zza(((zzcl)v0));
            if(v1 == null) {
                v1_1 = new ArrayList();
            }

            Iterator v5_2 = ((List)v5).iterator();
            while(v5_2.hasNext()) {
                v1_1.add(zzcj.zze(v5_2.next()));
            }

            this.zzhu.zza(((Comparable)v0), v1_1);
            return;
        }

        if(((zzcl)v0).zzbp() == zzfy.zzqz) {
            v1 = this.zza(((zzcl)v0));
            if(v1 == null) {
                this.zzhu.zza(((Comparable)v0), zzcj.zze(v5));
                return;
            }

            if((v1 instanceof zzee)) {
                v5_3 = ((zzcl)v0).zza(((zzee)v1), ((zzee)v5));
            }
            else {
                v5_1 = ((zzcl)v0).zza(((zzdx)v1).zzbu(), ((zzdx)v5)).zzca();
            }

            this.zzhu.zza(((Comparable)v0), v5_3);
            return;
        }

        this.zzhu.zza(((Comparable)v0), zzcj.zze(v5));
    }

    private static int zzd(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        Object v1 = arg4.getValue();
        if(((zzcl)v0).zzbp() == zzfy.zzqz && !((zzcl)v0).zzbq() && !((zzcl)v0).zzbr()) {
            if((v1 instanceof zzda)) {
                return zzca.zzb(arg4.getKey().zzbn(), ((zzde)v1));
            }
            else {
                return zzca.zzb(arg4.getKey().zzbn(), ((zzdx)v1));
            }
        }

        return zzcj.zzb(((zzcl)v0), v1);
    }

    private static Object zze(Object arg3) {
        if((arg3 instanceof zzee)) {
            return ((zzee)arg3).zzcy();
        }

        if((arg3 instanceof byte[])) {
            byte[] v0 = new byte[arg3.length];
            System.arraycopy(arg3, 0, v0, 0, arg3.length);
            return v0;
        }

        return arg3;
    }
}

