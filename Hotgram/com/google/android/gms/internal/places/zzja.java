package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzja {
    private static final Class zzwz;
    private static final zzjq zzxa;
    private static final zzjq zzxb;
    private static final zzjq zzxc;

    static {
        zzja.zzwz = zzja.zzge();
        zzja.zzxa = zzja.zzg(false);
        zzja.zzxb = zzja.zzg(true);
        zzja.zzxc = new zzjs();
    }

    static int zzaa(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzha)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzav(((zzha)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzav(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzab(List arg0) {
        return arg0.size() << 2;
    }

    static int zzac(List arg0) {
        return arg0.size() << 3;
    }

    static int zzad(List arg0) {
        return arg0.size();
    }

    static Object zzb(int arg2, int arg3, Object arg4, zzjq arg5) {
        if(arg4 == null) {
            arg4 = arg5.zzgo();
        }

        arg5.zzb(arg4, arg2, ((long)arg3));
        return arg4;
    }

    static Object zzb(int arg5, List arg6, zzhd arg7, Object arg8, zzjq arg9) {
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
                if(arg7.zzi(v3) != null) {
                    if(v1 != v8) {
                        arg6.set(v8, Integer.valueOf(v3));
                    }

                    ++v8;
                }
                else {
                    v2 = zzja.zzb(arg5, v3, v2, arg9);
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
                while(arg7.zzi(v8) != null);

                arg8 = zzja.zzb(arg5, v8, v2, arg9);
                v6.remove();
            }
        }

        return v2;
    }

    public static void zzb(int arg1, List arg2, zzkk arg3) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzc(arg1, arg2);
        }
    }

    public static void zzb(int arg1, List arg2, zzkk arg3, zziy arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2, arg4);
        }
    }

    public static void zzb(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzh(arg1, arg2, arg4);
        }
    }

    static void zzb(zzgm arg1, Object arg2, Object arg3) {
        zzgq v3 = arg1.zzb(arg3);
        if(!v3.isEmpty()) {
            arg1.zzc(arg2).zzb(v3);
        }
    }

    static void zzb(zzic arg1, Object arg2, Object arg3, long arg4) {
        zzjw.zzb(arg2, arg4, arg1.zzc(zzjw.zzq(arg2, arg4), zzjw.zzq(arg3, arg4)));
    }

    static void zzb(zzjq arg1, Object arg2, Object arg3) {
        arg1.zzf(arg2, arg1.zzh(arg1.zzq(arg2), arg1.zzq(arg3)));
    }

    public static void zzc(int arg1, List arg2, zzkk arg3) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzd(arg1, arg2);
        }
    }

    public static void zzc(int arg1, List arg2, zzkk arg3, zziy arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzc(arg1, arg2, arg4);
        }
    }

    public static void zzc(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzg(arg1, arg2, arg4);
        }
    }

    public static boolean zzd(int arg9, int arg10, int arg11) {
        if(arg10 < 40) {
            return 1;
        }

        long v3 = ((long)arg11);
        if((((long)arg10)) - (((long)arg9)) + 10 <= 2 * v3 + 3 + (v3 + 3) * 3) {
            return 1;
        }

        return 0;
    }

    static int zzd(int arg1, Object arg2, zziy arg3) {
        if((arg2 instanceof zzho)) {
            return zzgf.zzb(arg1, ((zzho)arg2));
        }

        return zzgf.zzc(arg1, ((zzih)arg2), arg3);
    }

    static int zzd(int arg4, List arg5, zziy arg6) {
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        arg4 = zzgf.zzas(arg4) * v0;
        while(v1 < v0) {
            Object v2 = arg5.get(v1);
            int v2_1 = (v2 instanceof zzho) ? zzgf.zzb(((zzho)v2)) : zzgf.zzc(((zzih)v2), arg6);
            arg4 += v2_1;
            ++v1;
        }

        return arg4;
    }

    public static void zzd(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzd(arg1, arg2, arg4);
        }
    }

    static int zze(int arg4, List arg5) {
        int v2_1;
        Object v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        arg4 = zzgf.zzas(arg4) * v0;
        if((arg5 instanceof zzhq)) {
            while(v1 < v0) {
                v2 = ((zzhq)arg5).getRaw(v1);
                v2_1 = (v2 instanceof zzfr) ? zzgf.zzc(((zzfr)v2)) : zzgf.zzl(((String)v2));
                arg4 += v2_1;
                ++v1;
            }
        }
        else {
            while(v1 < v0) {
                v2 = arg5.get(v1);
                v2_1 = (v2 instanceof zzfr) ? zzgf.zzc(((zzfr)v2)) : zzgf.zzl(((String)v2));
                arg4 += v2_1;
                ++v1;
            }
        }

        return arg4;
    }

    static int zze(int arg4, List arg5, zziy arg6) {
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        int v2 = 0;
        while(v1 < v0) {
            v2 += zzgf.zzd(arg4, arg5.get(v1), arg6);
            ++v1;
        }

        return v2;
    }

    public static void zze(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zze(arg1, arg2, arg4);
        }
    }

    static boolean zze(Object arg0, Object arg1) {
        if(arg0 != arg1 && (arg0 == null || !arg0.equals(arg1))) {
            return 0;
        }

        return 1;
    }

    static int zzf(int arg2, List arg3) {
        int v0 = arg3.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        v0 *= zzgf.zzas(arg2);
        while(v1 < arg3.size()) {
            v0 += zzgf.zzc(arg3.get(v1));
            ++v1;
        }

        return v0;
    }

    public static void zzf(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzo(arg1, arg2, arg4);
        }
    }

    private static zzjq zzg(boolean arg6) {
        zzjq v0 = null;
        try {
            Class v1 = zzja.zzgf();
            if(v1 == null) {
                return v0;
            }

            return v1.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(arg6));
        }
        catch(Throwable ) {
            return v0;
        }
    }

    public static void zzg(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzf(arg1, arg2, arg4);
        }
    }

    public static zzjq zzgb() {
        return zzja.zzxa;
    }

    public static zzjq zzgc() {
        return zzja.zzxb;
    }

    public static zzjq zzgd() {
        return zzja.zzxc;
    }

    private static Class zzge() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch(Throwable ) {
            return null;
        }
    }

    private static Class zzgf() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch(Throwable ) {
            return null;
        }
    }

    public static void zzh(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzm(arg1, arg2, arg4);
        }
    }

    public static void zzh(Class arg1) {
        if(!zzgz.class.isAssignableFrom(arg1) && zzja.zzwz != null) {
            if(zzja.zzwz.isAssignableFrom(arg1)) {
            }
            else {
                throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
            }
        }
    }

    public static void zzi(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2, arg4);
        }
    }

    public static void zzj(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzk(arg1, arg2, arg4);
        }
    }

    public static void zzk(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzn(arg1, arg2, arg4);
        }
    }

    public static void zzl(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzc(arg1, arg2, arg4);
        }
    }

    public static void zzm(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzl(arg1, arg2, arg4);
        }
    }

    public static void zzn(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzi(arg1, arg2, arg4);
        }
    }

    public static void zzo(int arg1, List arg2, zzkk arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzj(arg1, arg2, arg4);
        }
    }

    static int zzp(int arg0, List arg1, boolean arg2) {
        if(arg1.size() == 0) {
            return 0;
        }

        return zzja.zzu(arg1) + arg1.size() * zzgf.zzas(arg0);
    }

    static int zzq(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzja.zzv(arg1) + v2 * zzgf.zzas(arg0);
    }

    static int zzr(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzja.zzw(arg1) + v2 * zzgf.zzas(arg0);
    }

    static int zzs(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzja.zzx(arg1) + v2 * zzgf.zzas(arg0);
    }

    static int zzt(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzja.zzy(arg1) + v2 * zzgf.zzas(arg0);
    }

    static int zzu(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzhv)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzh(((zzhv)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzh(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzu(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzja.zzz(arg1) + v2 * zzgf.zzas(arg0);
    }

    static int zzv(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzhv)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzi(((zzhv)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzi(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzv(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzja.zzaa(arg1) + v2 * zzgf.zzas(arg0);
    }

    static int zzw(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzhv)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzj(((zzhv)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzj(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzw(int arg0, List arg1, boolean arg2) {
        int v1 = arg1.size();
        if(v1 == 0) {
            return 0;
        }

        return v1 * zzgf.zzl(arg0, 0);
    }

    static int zzx(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzha)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzay(((zzha)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzay(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzx(int arg2, List arg3, boolean arg4) {
        int v3 = arg3.size();
        if(v3 == 0) {
            return 0;
        }

        return v3 * zzgf.zzh(arg2, 0);
    }

    static int zzy(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzha)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzat(((zzha)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzat(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    static int zzy(int arg0, List arg1, boolean arg2) {
        int v1 = arg1.size();
        if(v1 == 0) {
            return 0;
        }

        return v1 * zzgf.zzd(arg0, true);
    }

    static int zzz(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzha)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzau(((zzha)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzgf.zzau(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }
}

