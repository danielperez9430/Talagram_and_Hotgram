package com.google.android.gms.internal.places;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;

final class zzgq {
    private final zzjb zzpj;
    private boolean zzpk;
    private boolean zzpl;
    private static final zzgq zzpm;

    static {
        zzgq.zzpm = new zzgq(true);
    }

    private zzgq(boolean arg1) {
        super();
        this.zzpl = false;
        this.zzpj = zzjb.zzbm(0);
        this.zzbb();
    }

    private zzgq() {
        super();
        this.zzpl = false;
        this.zzpj = zzjb.zzbm(16);
    }

    public final Object clone() {
        zzgq v0 = new zzgq();
        int v1;
        for(v1 = 0; v1 < this.zzpj.zzgg(); ++v1) {
            Map$Entry v2 = this.zzpj.zzbn(v1);
            v0.zzb(v2.getKey(), v2.getValue());
        }

        Iterator v1_1 = this.zzpj.zzgh().iterator();
        while(v1_1.hasNext()) {
            Object v2_1 = v1_1.next();
            v0.zzb(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        v0.zzpl = this.zzpl;
        return v0;
    }

    final Iterator descendingIterator() {
        if(this.zzpl) {
            return new zzhn(this.zzpj.zzgi().iterator());
        }

        return this.zzpj.zzgi().iterator();
    }

    public final boolean equals(Object arg2) {
        if(this == (((zzgq)arg2))) {
            return 1;
        }

        if(!(arg2 instanceof zzgq)) {
            return 0;
        }

        return this.zzpj.equals(((zzgq)arg2).zzpj);
    }

    public final int hashCode() {
        return this.zzpj.hashCode();
    }

    final boolean isEmpty() {
        return this.zzpj.isEmpty();
    }

    public final boolean isImmutable() {
        return this.zzpk;
    }

    public final boolean isInitialized() {
        int v1;
        for(v1 = 0; v1 < this.zzpj.zzgg(); ++v1) {
            if(!zzgq.zzc(this.zzpj.zzbn(v1))) {
                return 0;
            }
        }

        Iterator v1_1 = this.zzpj.zzgh().iterator();
        do {
            if(!v1_1.hasNext()) {
                return 1;
            }
        }
        while(zzgq.zzc(v1_1.next()));

        return 0;
    }

    public final Iterator iterator() {
        if(this.zzpl) {
            return new zzhn(this.zzpj.entrySet().iterator());
        }

        return this.zzpj.entrySet().iterator();
    }

    static int zzb(zzke arg1, int arg2, Object arg3) {
        arg2 = zzgf.zzas(arg2);
        if(arg1 == zzke.zzzc) {
            zzhb.zzg(arg3);
            arg2 <<= 1;
        }

        return arg2 + zzgq.zzc(arg1, arg3);
    }

    static void zzb(zzgf arg1, zzke arg2, int arg3, Object arg4) {
        if(arg2 == zzke.zzzc) {
            zzhb.zzg(((zzih)arg4));
            arg1.zzd(arg3, 3);
            ((zzih)arg4).zzc(arg1);
            arg1.zzd(arg3, 4);
            return;
        }

        arg1.zzd(arg3, arg2.zzha());
        switch(zzgr.zznn[arg2.ordinal()]) {
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
        arg1.zzar(((Integer)arg4).intValue());
        return;
    label_66:
        arg1.zzao(((Integer)arg4).intValue());
        return;
    label_36:
        arg1.zzap(((Integer)arg4).intValue());
        return;
    label_69:
        arg1.zze(((Long)arg4).longValue());
        return;
    label_39:
        if((arg4 instanceof zzfr)) {
            arg1.zzb(((zzfr)arg4));
            return;
        }

        arg1.zzg(((byte[])arg4), 0, arg4.length);
        return;
    label_72:
        arg1.zze(((Long)arg4).longValue());
        return;
    label_75:
        arg1.zzd(((Float)arg4).floatValue());
        return;
    label_78:
        arg1.zzb(((Double)arg4).doubleValue());
        return;
    label_47:
        if((arg4 instanceof zzfr)) {
            arg1.zzb(((zzfr)arg4));
            return;
        }

        arg1.zzk(((String)arg4));
        return;
    label_16:
        if((arg4 instanceof zzhc)) {
            arg1.zzao(((zzhc)arg4).zzap());
            return;
        }

        arg1.zzao(((Integer)arg4).intValue());
        return;
    label_53:
        arg1.zzc(((zzih)arg4));
        return;
    label_55:
        ((zzih)arg4).zzc(arg1);
        return;
    label_24:
        arg1.zzf(((Long)arg4).longValue());
        return;
    label_57:
        arg1.zzd(((Boolean)arg4).booleanValue());
        return;
    label_27:
        arg1.zzaq(((Integer)arg4).intValue());
        return;
    label_60:
        arg1.zzar(((Integer)arg4).intValue());
        return;
    label_30:
        arg1.zzg(((Long)arg4).longValue());
        return;
    label_63:
        arg1.zzg(((Long)arg4).longValue());
    }

    private static void zzb(zzke arg2, Object arg3) {
        zzhb.checkNotNull(arg3);
        boolean v1 = false;
        switch(zzgr.zzpn[arg2.zzgz().ordinal()]) {
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
        if((arg3 instanceof zzfr)) {
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
        if((arg3 instanceof zzih)) {
            goto label_18;
        }

        if(!(arg3 instanceof zzhk)) {
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
        if(!(arg3 instanceof Integer) && !(arg3 instanceof zzhc)) {
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

    public final Object zzb(zzgs arg2) {
        zzih v2_1;
        Object v2 = this.zzpj.get(arg2);
        if((v2 instanceof zzhk)) {
            v2_1 = zzhk.zzei();
        }

        return v2_1;
    }

    public final void zzb(zzgs arg6, Object arg7) {
        ArrayList v7;
        if(!arg6.zzdk()) {
            zzgq.zzb(arg6.zzdi(), arg7);
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
                zzgq.zzb(arg6.zzdi(), v3);
            }

            v7 = v0;
        }
        else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }

        if((v7 instanceof zzhk)) {
            this.zzpl = true;
        }

        this.zzpj.zzb(((Comparable)arg6), v7);
    }

    public final void zzb(zzgq arg3) {
        int v0;
        for(v0 = 0; v0 < arg3.zzpj.zzgg(); ++v0) {
            this.zzd(arg3.zzpj.zzbn(v0));
        }

        Iterator v3 = arg3.zzpj.zzgh().iterator();
        while(v3.hasNext()) {
            this.zzd(v3.next());
        }
    }

    public final void zzbb() {
        if(this.zzpk) {
            return;
        }

        this.zzpj.zzbb();
        this.zzpk = true;
    }

    private static int zzc(zzke arg1, Object arg2) {
        switch(zzgr.zznn[arg1.ordinal()]) {
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
        return zzgf.zzh(((Long)arg2).longValue());
    label_69:
        return zzgf.zze(((Float)arg2).floatValue());
    label_37:
        if((arg2 instanceof zzfr)) {
            return zzgf.zzc(((zzfr)arg2));
        }

        return zzgf.zzl(((String)arg2));
    label_72:
        return zzgf.zzc(((Double)arg2).doubleValue());
    label_8:
        if((arg2 instanceof zzhc)) {
            return zzgf.zzay(((zzhc)arg2).zzap());
        }

        return zzgf.zzay(((Integer)arg2).intValue());
    label_43:
        if((arg2 instanceof zzhk)) {
            return zzgf.zzb(((zzho)arg2));
        }

        return zzgf.zzd(((zzih)arg2));
    label_16:
        return zzgf.zzj(((Long)arg2).longValue());
    label_49:
        return zzgf.zze(((zzih)arg2));
    label_51:
        return zzgf.zze(((Boolean)arg2).booleanValue());
    label_19:
        return zzgf.zzav(((Integer)arg2).intValue());
    label_54:
        return zzgf.zzaw(((Integer)arg2).intValue());
    label_22:
        return zzgf.zzl(((Long)arg2).longValue());
    label_57:
        return zzgf.zzk(((Long)arg2).longValue());
    label_25:
        return zzgf.zzax(((Integer)arg2).intValue());
    label_60:
        return zzgf.zzat(((Integer)arg2).intValue());
    label_28:
        return zzgf.zzau(((Integer)arg2).intValue());
    label_63:
        return zzgf.zzi(((Long)arg2).longValue());
    label_31:
        if((arg2 instanceof zzfr)) {
            return zzgf.zzc(((zzfr)arg2));
        }

        return zzgf.zze(((byte[])arg2));
    }

    private static int zzc(zzgs arg3, Object arg4) {
        Iterator v3;
        zzke v0 = arg3.zzdi();
        int v1 = arg3.zzap();
        if(arg3.zzdk()) {
            int v2 = 0;
            if(arg3.zzdl()) {
                v3 = ((List)arg4).iterator();
                while(v3.hasNext()) {
                    v2 += zzgq.zzc(v0, v3.next());
                }

                return zzgf.zzas(v1) + v2 + zzgf.zzba(v2);
            }

            v3 = ((List)arg4).iterator();
            while(v3.hasNext()) {
                v2 += zzgq.zzb(v0, v1, v3.next());
            }

            return v2;
        }

        return zzgq.zzb(v0, v1, arg4);
    }

    private static boolean zzc(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        if(((zzgs)v0).zzdj() == zzkj.zzzw) {
            if(((zzgs)v0).zzdk()) {
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
                if((v4_1 instanceof zzih)) {
                    if(!((zzih)v4_1).isInitialized()) {
                        return 0;
                    }
                }
                else if((v4_1 instanceof zzhk)) {
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
        ArrayList v1_1;
        Object v1;
        zzih v5_1;
        Object v0 = arg5.getKey();
        Object v5 = arg5.getValue();
        if((v5 instanceof zzhk)) {
            v5_1 = zzhk.zzei();
        }

        if(((zzgs)v0).zzdk()) {
            v1 = this.zzb(((zzgs)v0));
            if(v1 == null) {
                v1_1 = new ArrayList();
            }

            Iterator v5_2 = ((List)v5).iterator();
            while(v5_2.hasNext()) {
                v1_1.add(zzgq.zze(v5_2.next()));
            }

            this.zzpj.zzb(((Comparable)v0), v1_1);
            return;
        }

        if(((zzgs)v0).zzdj() == zzkj.zzzw) {
            v1 = this.zzb(((zzgs)v0));
            if(v1 == null) {
                this.zzpj.zzb(((Comparable)v0), zzgq.zze(v5));
                return;
            }

            if((v1 instanceof zzin)) {
                zzin v5_3 = ((zzgs)v0).zzb(((zzin)v1), ((zzin)v5));
            }
            else {
                v5_1 = ((zzgs)v0).zzb(((zzih)v1).zzdq(), ((zzih)v5)).zzdx();
            }

            this.zzpj.zzb(((Comparable)v0), v5_1);
            return;
        }

        this.zzpj.zzb(((Comparable)v0), zzgq.zze(v5));
    }

    public static zzgq zzdf() {
        return zzgq.zzpm;
    }

    public final int zzdg() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzpj.zzgg()) {
            Map$Entry v2 = this.zzpj.zzbn(v0);
            v1 += zzgq.zzc(v2.getKey(), v2.getValue());
            ++v0;
        }

        Iterator v0_1 = this.zzpj.zzgh().iterator();
        while(v0_1.hasNext()) {
            Object v2_1 = v0_1.next();
            v1 += zzgq.zzc(((Map$Entry)v2_1).getKey(), ((Map$Entry)v2_1).getValue());
        }

        return v1;
    }

    public final int zzdh() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.zzpj.zzgg()) {
            v1 += zzgq.zze(this.zzpj.zzbn(v0));
            ++v0;
        }

        Iterator v0_1 = this.zzpj.zzgh().iterator();
        while(v0_1.hasNext()) {
            v1 += zzgq.zze(v0_1.next());
        }

        return v1;
    }

    private static Object zze(Object arg3) {
        if((arg3 instanceof zzin)) {
            return ((zzin)arg3).zzey();
        }

        if((arg3 instanceof byte[])) {
            byte[] v0 = new byte[arg3.length];
            System.arraycopy(arg3, 0, v0, 0, arg3.length);
            return v0;
        }

        return arg3;
    }

    private static int zze(Map$Entry arg4) {
        Object v0 = arg4.getKey();
        Object v1 = arg4.getValue();
        if(((zzgs)v0).zzdj() == zzkj.zzzw && !((zzgs)v0).zzdk() && !((zzgs)v0).zzdl()) {
            if((v1 instanceof zzhk)) {
                return zzgf.zzc(arg4.getKey().zzap(), ((zzho)v1));
            }
            else {
                return zzgf.zze(arg4.getKey().zzap(), ((zzih)v1));
            }
        }

        return zzgq.zzc(((zzgs)v0), v1);
    }
}

