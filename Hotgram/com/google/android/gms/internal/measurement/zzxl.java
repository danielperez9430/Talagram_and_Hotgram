package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzxl {
    private static final Class zzcbw;
    private static final zzyb zzcbx;
    private static final zzyb zzcby;
    private static final zzyb zzcbz;

    static {
        zzxl.zzcbw = zzxl.zzxu();
        zzxl.zzcbx = zzxl.zzx(false);
        zzxl.zzcby = zzxl.zzx(true);
        zzxl.zzcbz = new zzyd();
    }

    static Object zza(int arg2, int arg3, Object arg4, zzyb arg5) {
        if(arg4 == null) {
            arg4 = arg5.zzye();
        }

        arg5.zza(arg4, arg2, ((long)arg3));
        return arg4;
    }

    static Object zza(int arg5, List arg6, zzvr arg7, Object arg8, zzyb arg9) {
        int v8;
        Object v2;
        if(arg7 == null) {
            return arg8;
        }

        if((arg6 instanceof RandomAccess)) {
            int v0 = arg6.size();
            int v1 = 0;
            v2 = arg8;
            v8 = 0;
            while(v1 < v0) {
                int v3 = arg6.get(v1).intValue();
                if(arg7.zzb(v3)) {
                    if(v1 != v8) {
                        arg6.set(v8, Integer.valueOf(v3));
                    }

                    ++v8;
                }
                else {
                    v2 = zzxl.zza(arg5, v3, v2, arg9);
                }

                ++v1;
            }

            if(v8 == v0) {
                return v2;
            }

            arg6.subList(v8, v0).clear();
        }
        else {
            Iterator v6 = arg6.iterator();
            while(true) {
                v2 = arg8;
                do {
                    if(!v6.hasNext()) {
                        return v2;
                    }

                    v8 = v6.next().intValue();
                }
                while(arg7.zzb(v8));

                arg8 = zzxl.zza(arg5, v8, v2, arg9);
                v6.remove();
            }
        }

        return v2;
    }

    public static void zza(int arg1, List arg2, zzyw arg3) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zza(arg1, arg2);
        }
    }

    public static void zza(int arg1, List arg2, zzyw arg3, zzxj arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zza(arg1, arg2, arg4);
        }
    }

    public static void zza(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzg(arg1, arg2, arg4);
        }
    }

    static void zza(zzva arg1, Object arg2, Object arg3) {
        zzvd v3 = arg1.zzs(arg3);
        if(!v3.isEmpty()) {
            arg1.zzt(arg2).zza(v3);
        }
    }

    static void zza(zzwo arg1, Object arg2, Object arg3, long arg4) {
        zzyh.zza(arg2, arg4, arg1.zzc(zzyh.zzp(arg2, arg4), zzyh.zzp(arg3, arg4)));
    }

    static void zza(zzyb arg1, Object arg2, Object arg3) {
        arg1.zzf(arg2, arg1.zzh(arg1.zzah(arg2), arg1.zzah(arg3)));
    }

    static int zzaa(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzvn)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbh(((zzvn)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbh(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzab(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzvn)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbc(((zzvn)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbc(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzac(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzvn)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbd(((zzvn)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbd(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzad(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzvn)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbe(((zzvn)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzbe(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzae(List arg0) {
        return arg0.size() << 2;
    }

    static int zzaf(List arg0) {
        return arg0.size() << 3;
    }

    static int zzag(List arg0) {
        return arg0.size();
    }

    public static void zzb(int arg1, List arg2, zzyw arg3) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2);
        }
    }

    public static void zzb(int arg1, List arg2, zzyw arg3, zzxj arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2, arg4);
        }
    }

    public static void zzb(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzf(arg1, arg2, arg4);
        }
    }

    static int zzc(int arg1, Object arg2, zzxj arg3) {
        if((arg2 instanceof zzwa)) {
            return zzut.zza(arg1, ((zzwa)arg2));
        }

        return zzut.zzb(arg1, ((zzwt)arg2), arg3);
    }

    static int zzc(int arg4, List arg5) {
        int v2_1;
        Object v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        arg4 = zzut.zzbb(arg4) * v0;
        if((arg5 instanceof zzwc)) {
            while(v1 < v0) {
                v2 = ((zzwc)arg5).getRaw(v1);
                v2_1 = (v2 instanceof zzud) ? zzut.zzb(((zzud)v2)) : zzut.zzfx(((String)v2));
                arg4 += v2_1;
                ++v1;
            }
        }
        else {
            while(v1 < v0) {
                v2 = arg5.get(v1);
                v2_1 = (v2 instanceof zzud) ? zzut.zzb(((zzud)v2)) : zzut.zzfx(((String)v2));
                arg4 += v2_1;
                ++v1;
            }
        }

        return arg4;
    }

    static int zzc(int arg4, List arg5, zzxj arg6) {
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        arg4 = zzut.zzbb(arg4) * v0;
        while(v1 < v0) {
            Object v2 = arg5.get(v1);
            int v2_1 = (v2 instanceof zzwa) ? zzut.zza(((zzwa)v2)) : zzut.zzb(((zzwt)v2), arg6);
            arg4 += v2_1;
            ++v1;
        }

        return arg4;
    }

    public static void zzc(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzc(arg1, arg2, arg4);
        }
    }

    static int zzd(int arg2, List arg3) {
        int v0 = arg3.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        v0 *= zzut.zzbb(arg2);
        while(v1 < arg3.size()) {
            v0 += zzut.zzb(arg3.get(v1));
            ++v1;
        }

        return v0;
    }

    static int zzd(int arg4, List arg5, zzxj arg6) {
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        int v2 = 0;
        while(v1 < v0) {
            v2 += zzut.zzc(arg4, arg5.get(v1), arg6);
            ++v1;
        }

        return v2;
    }

    public static void zzd(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzd(arg1, arg2, arg4);
        }
    }

    public static void zze(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzn(arg1, arg2, arg4);
        }
    }

    static boolean zze(Object arg0, Object arg1) {
        if(arg0 != arg1 && (arg0 == null || !arg0.equals(arg1))) {
            return 0;
        }

        return 1;
    }

    public static void zzf(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zze(arg1, arg2, arg4);
        }
    }

    public static void zzg(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzl(arg1, arg2, arg4);
        }
    }

    public static void zzh(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zza(arg1, arg2, arg4);
        }
    }

    public static void zzi(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzj(arg1, arg2, arg4);
        }
    }

    public static void zzj(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzm(arg1, arg2, arg4);
        }
    }

    public static void zzj(Class arg1) {
        if(!zzvm.class.isAssignableFrom(arg1) && zzxl.zzcbw != null) {
            if(zzxl.zzcbw.isAssignableFrom(arg1)) {
            }
            else {
                throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
            }
        }
    }

    public static void zzk(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2, arg4);
        }
    }

    public static void zzl(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzk(arg1, arg2, arg4);
        }
    }

    public static void zzm(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzh(arg1, arg2, arg4);
        }
    }

    public static void zzn(int arg1, List arg2, zzyw arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzi(arg1, arg2, arg4);
        }
    }

    static int zzo(int arg0, List arg1, boolean arg2) {
        if(arg1.size() == 0) {
            return 0;
        }

        return zzxl.zzx(arg1) + arg1.size() * zzut.zzbb(arg0);
    }

    static int zzp(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzxl.zzy(arg1) + v2 * zzut.zzbb(arg0);
    }

    static int zzq(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzxl.zzz(arg1) + v2 * zzut.zzbb(arg0);
    }

    static int zzr(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzxl.zzaa(arg1) + v2 * zzut.zzbb(arg0);
    }

    static int zzs(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzxl.zzab(arg1) + v2 * zzut.zzbb(arg0);
    }

    static int zzt(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzxl.zzac(arg1) + v2 * zzut.zzbb(arg0);
    }

    static int zzu(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzxl.zzad(arg1) + v2 * zzut.zzbb(arg0);
    }

    static int zzv(int arg0, List arg1, boolean arg2) {
        int v1 = arg1.size();
        if(v1 == 0) {
            return 0;
        }

        return v1 * zzut.zzk(arg0, 0);
    }

    static int zzw(int arg2, List arg3, boolean arg4) {
        int v3 = arg3.size();
        if(v3 == 0) {
            return 0;
        }

        return v3 * zzut.zzg(arg2, 0);
    }

    private static zzyb zzx(boolean arg6) {
        zzyb v0 = null;
        try {
            Class v1 = zzxl.zzxv();
            if(v1 == null) {
                return v0;
            }

            return v1.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(arg6));
        }
        catch(Throwable ) {
            return v0;
        }
    }

    static int zzx(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzwh)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzay(((zzwh)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzay(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzx(int arg0, List arg1, boolean arg2) {
        int v1 = arg1.size();
        if(v1 == 0) {
            return 0;
        }

        return v1 * zzut.zzc(arg0, true);
    }

    public static zzyb zzxr() {
        return zzxl.zzcbx;
    }

    public static zzyb zzxs() {
        return zzxl.zzcby;
    }

    public static zzyb zzxt() {
        return zzxl.zzcbz;
    }

    private static Class zzxu() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch(Throwable ) {
            return null;
        }
    }

    private static Class zzxv() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch(Throwable ) {
            return null;
        }
    }

    static int zzy(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzwh)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzaz(((zzwh)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzaz(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzz(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzwh)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzba(((zzwh)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzut.zzba(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }
}

