package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;

final class zzvd {
    private boolean zzbpo;
    private final zzxm zzbvq;
    private boolean zzbvr;
    private static final zzvd zzbvs;

    static {
        zzvd.zzbvs = new zzvd(true);
    }

    private zzvd(boolean arg1) {
        super();
        this.zzbvr = false;
        this.zzbvq = zzxm.zzbt(0);
        this.zzsm();
    }

    private zzvd() {
        super();
        this.zzbvr = false;
        this.zzbvq = zzxm.zzbt(16);
    }

    public final Object clone() {
        zzvd v0 = new zzvd();
        int v1;
        for(v1 = 0; v1 < this.zzbvq.zzxw(); ++v1) {
            Map$Entry v2 = this.zzbvq.zzbu(v1);
            v0.zza(v2.getKey(), v2.getValue());
        }

        Iterator v1_1 = this.zzbvq.zzxx().iterator();
        while(v1_1.hasNext()) {
            Object v2_1 = v1_1.next();
            v0.zza(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        v0.zzbvr = this.zzbvr;
        return v0;
    }

    final Iterator descendingIterator() {
        if(this.zzbvr) {
            return new zzvz(this.zzbvq.zzxy().iterator());
        }

        return this.zzbvq.zzxy().iterator();
    }

    public final boolean equals(Object arg2) {
        if(this == (((zzvd)arg2))) {
            return 1;
        }

        if(!(arg2 instanceof zzvd)) {
            return 0;
        }

        return this.zzbvq.equals(((zzvd)arg2).zzbvq);
    }

    public final int hashCode() {
        return this.zzbvq.hashCode();
    }

    final boolean isEmpty() {
        return this.zzbvq.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzbpo;
    }

    public final boolean isInitialized() {
        int v1;
        for(v1 = 0; v1 < this.zzbvq.zzxw(); ++v1) {
            if(!zzvd.zzc(this.zzbvq.zzbu(v1))) {
                return 0;
            }
        }

        Iterator v1_1 = this.zzbvq.zzxx().iterator();
        do {
            if(!v1_1.hasNext()) {
                return 1;
            }
        }
        while(zzvd.zzc(v1_1.next()));

        return 0;
    }

    public final Iterator iterator() {
        if(this.zzbvr) {
            return new zzvz(this.zzbvq.entrySet().iterator());
        }

        return this.zzbvq.entrySet().iterator();
    }

    static int zza(zzyq arg1, int arg2, Object arg3) {
        arg2 = zzut.zzbb(arg2);
        if(arg1 == zzyq.zzcdz) {
            zzvo.zzf(arg3);
            arg2 <<= 1;
        }

        return arg2 + zzvd.zzb(arg1, arg3);
    }

    private final Object zza(zzvf arg2) {
        Object v2 = this.zzbvq.get(arg2);
        if((v2 instanceof zzvw)) {
            zzwt v2_1 = zzvw.zzwt();
        }

        return v2;
    }

    static void zza(zzut arg1, zzyq arg2, int arg3, Object arg4) {
        if(arg2 == zzyq.zzcdz) {
            zzvo.zzf(((zzwt)arg4));
            arg1.zzc(arg3, 3);
            ((zzwt)arg4).zzb(arg1);
            arg1.zzc(arg3, 4);
            return;
        }

        arg1.zzc(arg3, arg2.zzyq());
        switch(zzve.zzbuu[arg2.ordinal()]) {
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
        arg1.zzba(((Integer)arg4).intValue());
        return;
    label_66:
        arg1.zzax(((Integer)arg4).intValue());
        return;
    label_36:
        arg1.zzay(((Integer)arg4).intValue());
        return;
    label_69:
        arg1.zzav(((Long)arg4).longValue());
        return;
    label_39:
        if((arg4 instanceof zzud)) {
            arg1.zza(((zzud)arg4));
            return;
        }

        arg1.zze(((byte[])arg4), 0, arg4.length);
        return;
    label_72:
        arg1.zzav(((Long)arg4).longValue());
        return;
    label_75:
        arg1.zza(((Float)arg4).floatValue());
        return;
    label_78:
        arg1.zzb(((Double)arg4).doubleValue());
        return;
    label_47:
        if((arg4 instanceof zzud)) {
            arg1.zza(((zzud)arg4));
            return;
        }

        arg1.zzfw(((String)arg4));
        return;
    label_16:
        if((arg4 instanceof zzvp)) {
            arg1.zzax(((zzvp)arg4).zzc());
            return;
        }

        arg1.zzax(((Integer)arg4).intValue());
        return;
    label_53:
        arg1.zzb(((zzwt)arg4));
        return;
    label_55:
        ((zzwt)arg4).zzb(arg1);
        return;
    label_24:
        arg1.zzaw(((Long)arg4).longValue());
        return;
    label_57:
        arg1.zzu(((Boolean)arg4).booleanValue());
        return;
    label_27:
        arg1.zzaz(((Integer)arg4).intValue());
        return;
    label_60:
        arg1.zzba(((Integer)arg4).intValue());
        return;
    label_30:
        arg1.zzax(((Long)arg4).longValue());
        return;
    label_63:
        arg1.zzax(((Long)arg4).longValue());
    }

    private final void zza(zzvf arg6, Object arg7) {
        ArrayList v7;
        if(!arg6.zzvy()) {
            zzvd.zza(arg6.zzvw(), arg7);
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
                zzvd.zza(arg6.zzvw(), v3);
            }

            v7 = v0;
        }
        else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }

        if((v7 instanceof zzvw)) {
            this.zzbvr = true;
        }

        this.zzbvq.zza(((Comparable)arg6), v7);
    }

    private static void zza(zzyq arg2, Object arg3) {
        zzvo.checkNotNull(arg3);
        boolean v1 = false;
        switch(zzve.zzbvt[arg2.zzyp().ordinal()]) {
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
        if((arg3 instanceof zzud)) {
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
        if((arg3 instanceof zzwt)) {
            goto label_18;
        }

        if(!(arg3 instanceof zzvw)) {
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
        if(!(arg3 instanceof Integer) && !(arg3 instanceof zzvp)) {
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

    public final void zza(zzvd arg3) {
        int v0;
        for(v0 = 0; v0 < arg3.zzbvq.zzxw(); ++v0) {
            this.zzd(arg3.zzbvq.zzbu(v0));
        }

        Iterator v3 = arg3.zzbvq.zzxx().iterator();
        while(v3.hasNext()) {
            this.zzd(v3.next());
        }
    }

    private static int zzb(zzyq arg1, Object arg2) {
        switch(zzve.zzbuu[arg1.ordinal()]) {
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
        return zzut.zzay(((Long)arg2).longValue());
    label_69:
        return zzut.zzb(((Float)arg2).floatValue());
    label_37:
        if((arg2 instanceof zzud)) {
            return zzut.zzb(((zzud)arg2));
        }

        return zzut.zzfx(((String)arg2));
    label_72:
        return zzut.zzc(((Double)arg2).doubleValue());
    label_8:
        if((arg2 instanceof zzvp)) {
            return zzut.zzbh(((zzvp)arg2).zzc());
        }

        return zzut.zzbh(((Integer)arg2).intValue());
    label_43:
        if((arg2 instanceof zzvw)) {
            return zzut.zza(((zzwa)arg2));
        }

        return zzut.zzc(((zzwt)arg2));
    label_16:
        return zzut.zzba(((Long)arg2).longValue());
    label_49:
        return zzut.zzd(((zzwt)arg2));
    label_51:
        return zzut.zzv(((Boolean)arg2).booleanValue());
    label_19:
        return zzut.zzbe(((Integer)arg2).intValue());
    label_54:
        return zzut.zzbf(((Integer)arg2).intValue());
    label_22:
        return zzut.zzbc(((Long)arg2).longValue());
    label_57:
        return zzut.zzbb(((Long)arg2).longValue());
    label_25:
        return zzut.zzbg(((Integer)arg2).intValue());
    label_60:
        return zzut.zzbc(((Integer)arg2).intValue());
    label_28:
        return zzut.zzbd(((Integer)arg2).intValue());
    label_63:
        return zzut.zzaz(((Long)arg2).longValue());
    label_31:
        if((arg2 instanceof zzud)) {
            return zzut.zzb(((zzud)arg2));
        }

        return zzut.zzk(((byte[])arg2));
    }

    private static int zzb(zzvf arg3, Object arg4) {
        Iterator v3;
        zzyq v0 = arg3.zzvw();
        int v1 = arg3.zzc();
        if(arg3.zzvy()) {
            int v2 = 0;
            if(arg3.zzvz()) {
                v3 = ((List)arg4).iterator();
                while(v3.hasNext()) {
                    v2 += zzvd.zzb(v0, v3.next());
                }

                return zzut.zzbb(v1) + v2 + zzut.zzbj(v2);
            }

            v3 = ((List)arg4).iterator();
            while(v3.hasNext()) {
                v2 += zzvd.zza(v0, v1, v3.next());
            }

            return v2;
        }

        return zzvd.zza(v0, v1, arg4);
    }

    private static boolean zzc(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        if(((zzvf)v0).zzvx() == zzyv.zzcet) {
            if(((zzvf)v0).zzvy()) {
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
                if((v4_1 instanceof zzwt)) {
                    if(!((zzwt)v4_1).isInitialized()) {
                        return 0;
                    }
                }
                else if((v4_1 instanceof zzvw)) {
                    return 1;
                }
                else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }

        return 1;
    }

    private final void zzd(Map$Entry arg5) {
        Object v1;
        zzwt v5_1;
        Object v0 = arg5.getKey();
        Object v5 = arg5.getValue();
        if((v5 instanceof zzvw)) {
            v5_1 = zzvw.zzwt();
        }

        if(((zzvf)v0).zzvy()) {
            v1 = this.zza(((zzvf)v0));
            if(v1 == null) {
                ArrayList v1_1 = new ArrayList();
            }

            Iterator v5_2 = ((List)v5_1).iterator();
            while(v5_2.hasNext()) {
                v1.add(zzvd.zzv(v5_2.next()));
            }

            this.zzbvq.zza(((Comparable)v0), v1);
            return;
        }

        if(((zzvf)v0).zzvx() == zzyv.zzcet) {
            v1 = this.zza(((zzvf)v0));
            if(v1 == null) {
                this.zzbvq.zza(((Comparable)v0), zzvd.zzv(v5_1));
                return;
            }

            if((v1 instanceof zzwz)) {
                zzwz v5_3 = ((zzvf)v0).zza(((zzwz)v1), ((zzwz)v5_1));
            }
            else {
                v5_1 = ((zzvf)v0).zza(((zzwt)v1).zzwd(), v5_1).zzwj();
            }

            this.zzbvq.zza(((Comparable)v0), v5_1);
            return;
        }

        this.zzbvq.zza(((Comparable)v0), zzvd.zzv(v5_1));
    }

    private static int zze(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        Object v1 = arg4.getValue();
        if(((zzvf)v0).zzvx() == zzyv.zzcet && !((zzvf)v0).zzvy() && !((zzvf)v0).zzvz()) {
            if((v1 instanceof zzvw)) {
                return zzut.zzb(arg4.getKey().zzc(), ((zzwa)v1));
            }
            else {
                return zzut.zzd(arg4.getKey().zzc(), ((zzwt)v1));
            }
        }

        return zzvd.zzb(((zzvf)v0), v1);
    }

    public final void zzsm() {
        if(this.zzbpo) {
            return;
        }

        this.zzbvq.zzsm();
        this.zzbpo = true;
    }

    private static Object zzv(Object arg3) {
        if((arg3 instanceof zzwz)) {
            return ((zzwz)arg3).zzxj();
        }

        if((arg3 instanceof byte[])) {
            byte[] v0 = new byte[arg3.length];
            System.arraycopy(arg3, 0, v0, 0, arg3.length);
            return v0;
        }

        return arg3;
    }

    public static zzvd zzvt() {
        return zzvd.zzbvs;
    }

    public final int zzvu() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzbvq.zzxw()) {
            Map$Entry v2 = this.zzbvq.zzbu(v0);
            v1 += zzvd.zzb(v2.getKey(), v2.getValue());
            ++v0;
        }

        Iterator v0_1 = this.zzbvq.zzxx().iterator();
        while(v0_1.hasNext()) {
            Object v2_1 = v0_1.next();
            v1 += zzvd.zzb(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        return v1;
    }

    public final int zzvv() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzbvq.zzxw()) {
            v1 += zzvd.zze(this.zzbvq.zzbu(v0));
            ++v0;
        }

        Iterator v0_1 = this.zzbvq.zzxx().iterator();
        while(v0_1.hasNext()) {
            v1 += zzvd.zze(v0_1.next());
        }

        return v1;
    }
}

