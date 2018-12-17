package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzep {
    private static final Class zzob;
    private static final zzff zzoc;
    private static final zzff zzod;
    private static final zzff zzoe;

    static {
        zzep.zzob = zzep.zzdj();
        zzep.zzoc = zzep.zzd(false);
        zzep.zzod = zzep.zzd(true);
        zzep.zzoe = new zzfh();
    }

    static int zza(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzdl)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zze(((zzdl)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zze(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    private static Object zza(int arg2, int arg3, Object arg4, zzff arg5) {
        if(arg4 == null) {
            arg4 = arg5.zzdt();
        }

        arg5.zza(arg4, arg2, ((long)arg3));
        return arg4;
    }

    static Object zza(int arg5, List arg6, zzcv arg7, Object arg8, zzff arg9) {
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
                if(arg7.zzaf(v3) != null) {
                    if(v1 != v8) {
                        arg6.set(v8, Integer.valueOf(v3));
                    }

                    ++v8;
                }
                else {
                    v2 = zzep.zza(arg5, v3, v2, arg9);
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
                while(arg7.zzaf(v8) != null);

                arg8 = zzep.zza(arg5, v8, v2, arg9);
                v6.remove();
            }
        }

        return v2;
    }

    public static void zza(int arg1, List arg2, zzfz arg3) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zza(arg1, arg2);
        }
    }

    public static void zza(int arg1, List arg2, zzfz arg3, zzen arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zza(arg1, arg2, arg4);
        }
    }

    public static void zza(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzg(arg1, arg2, arg4);
        }
    }

    static void zza(zzcg arg1, Object arg2, Object arg3) {
        zzcj v3 = arg1.zzb(arg3);
        if(!v3.isEmpty()) {
            arg1.zzc(arg2).zza(v3);
        }
    }

    static void zza(zzds arg1, Object arg2, Object arg3, long arg4) {
        zzfl.zza(arg2, arg4, arg1.zzb(zzfl.zzo(arg2, arg4), zzfl.zzo(arg3, arg4)));
    }

    static void zza(zzff arg1, Object arg2, Object arg3) {
        arg1.zze(arg2, arg1.zzg(arg1.zzr(arg2), arg1.zzr(arg3)));
    }

    static int zzb(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzdl)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzf(((zzdl)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzf(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    public static void zzb(int arg1, List arg2, zzfz arg3) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2);
        }
    }

    public static void zzb(int arg1, List arg2, zzfz arg3, zzen arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2, arg4);
        }
    }

    public static void zzb(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzf(arg1, arg2, arg4);
        }
    }

    static int zzc(int arg1, Object arg2, zzen arg3) {
        if((arg2 instanceof zzde)) {
            return zzca.zza(arg1, ((zzde)arg2));
        }

        return zzca.zzb(arg1, ((zzdx)arg2), arg3);
    }

    static int zzc(int arg4, List arg5) {
        int v2_1;
        Object v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        arg4 = zzca.zzt(arg4) * v0;
        if((arg5 instanceof zzdg)) {
            while(v1 < v0) {
                v2 = ((zzdg)arg5).getRaw(v1);
                v2_1 = (v2 instanceof zzbo) ? zzca.zzb(((zzbo)v2)) : zzca.zzi(((String)v2));
                arg4 += v2_1;
                ++v1;
            }
        }
        else {
            while(v1 < v0) {
                v2 = arg5.get(v1);
                v2_1 = (v2 instanceof zzbo) ? zzca.zzb(((zzbo)v2)) : zzca.zzi(((String)v2));
                arg4 += v2_1;
                ++v1;
            }
        }

        return arg4;
    }

    static int zzc(int arg4, List arg5, zzen arg6) {
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        arg4 = zzca.zzt(arg4) * v0;
        while(v1 < v0) {
            Object v2 = arg5.get(v1);
            int v2_1 = (v2 instanceof zzde) ? zzca.zza(((zzde)v2)) : zzca.zza(((zzdx)v2), arg6);
            arg4 += v2_1;
            ++v1;
        }

        return arg4;
    }

    static int zzc(List arg5) {
        int v2;
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg5 instanceof zzdl)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzg(((zzdl)arg5).getLong(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzg(arg5.get(v1).longValue());
                ++v1;
            }
        }

        return v2;
    }

    public static void zzc(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzc(arg1, arg2, arg4);
        }
    }

    private static zzff zzd(boolean arg6) {
        zzff v0 = null;
        try {
            Class v1 = zzep.zzdk();
            if(v1 == null) {
                return v0;
            }

            return v1.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(arg6));
        }
        catch(Throwable ) {
            return v0;
        }
    }

    static int zzd(int arg2, List arg3) {
        int v0 = arg3.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        v0 *= zzca.zzt(arg2);
        while(v1 < arg3.size()) {
            v0 += zzca.zzb(arg3.get(v1));
            ++v1;
        }

        return v0;
    }

    static int zzd(int arg4, List arg5, zzen arg6) {
        int v0 = arg5.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        int v2 = 0;
        while(v1 < v0) {
            v2 += zzca.zzc(arg4, arg5.get(v1), arg6);
            ++v1;
        }

        return v2;
    }

    static int zzd(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzcs)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzz(((zzcs)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzz(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    public static void zzd(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzd(arg1, arg2, arg4);
        }
    }

    static boolean zzd(Object arg0, Object arg1) {
        if(arg0 != arg1 && (arg0 == null || !arg0.equals(arg1))) {
            return 0;
        }

        return 1;
    }

    public static zzff zzdg() {
        return zzep.zzoc;
    }

    public static zzff zzdh() {
        return zzep.zzod;
    }

    public static zzff zzdi() {
        return zzep.zzoe;
    }

    private static Class zzdj() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch(Throwable ) {
            return null;
        }
    }

    private static Class zzdk() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch(Throwable ) {
            return null;
        }
    }

    static int zze(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzcs)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzu(((zzcs)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzu(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    public static void zze(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzn(arg1, arg2, arg4);
        }
    }

    static int zzf(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzcs)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzv(((zzcs)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzv(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    public static void zzf(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zze(arg1, arg2, arg4);
        }
    }

    public static void zzf(Class arg1) {
        if(!zzcr.class.isAssignableFrom(arg1) && zzep.zzob != null) {
            if(zzep.zzob.isAssignableFrom(arg1)) {
            }
            else {
                throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
            }
        }
    }

    static int zzg(List arg4) {
        int v2;
        int v0 = arg4.size();
        int v1 = 0;
        if(v0 == 0) {
            return 0;
        }

        if((arg4 instanceof zzcs)) {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzw(((zzcs)arg4).getInt(v1));
                ++v1;
            }
        }
        else {
            v2 = 0;
            while(v1 < v0) {
                v2 += zzca.zzw(arg4.get(v1).intValue());
                ++v1;
            }
        }

        return v2;
    }

    public static void zzg(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzl(arg1, arg2, arg4);
        }
    }

    static int zzh(List arg0) {
        return arg0.size() << 2;
    }

    public static void zzh(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zza(arg1, arg2, arg4);
        }
    }

    static int zzi(List arg0) {
        return arg0.size() << 3;
    }

    public static void zzi(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzj(arg1, arg2, arg4);
        }
    }

    static int zzj(List arg0) {
        return arg0.size();
    }

    public static void zzj(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzm(arg1, arg2, arg4);
        }
    }

    public static void zzk(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzb(arg1, arg2, arg4);
        }
    }

    public static void zzl(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzk(arg1, arg2, arg4);
        }
    }

    public static void zzm(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzh(arg1, arg2, arg4);
        }
    }

    public static void zzn(int arg1, List arg2, zzfz arg3, boolean arg4) {
        if(arg2 != null && !arg2.isEmpty()) {
            arg3.zzi(arg1, arg2, arg4);
        }
    }

    static int zzo(int arg0, List arg1, boolean arg2) {
        if(arg1.size() == 0) {
            return 0;
        }

        return zzep.zza(arg1) + arg1.size() * zzca.zzt(arg0);
    }

    static int zzp(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzep.zzb(arg1) + v2 * zzca.zzt(arg0);
    }

    static int zzq(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzep.zzc(arg1) + v2 * zzca.zzt(arg0);
    }

    static int zzr(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzep.zzd(arg1) + v2 * zzca.zzt(arg0);
    }

    static int zzs(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzep.zze(arg1) + v2 * zzca.zzt(arg0);
    }

    static int zzt(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzep.zzf(arg1) + v2 * zzca.zzt(arg0);
    }

    static int zzu(int arg0, List arg1, boolean arg2) {
        int v2 = arg1.size();
        if(v2 == 0) {
            return 0;
        }

        return zzep.zzg(arg1) + v2 * zzca.zzt(arg0);
    }

    static int zzv(int arg0, List arg1, boolean arg2) {
        int v1 = arg1.size();
        if(v1 == 0) {
            return 0;
        }

        return v1 * zzca.zzl(arg0, 0);
    }

    static int zzw(int arg2, List arg3, boolean arg4) {
        int v3 = arg3.size();
        if(v3 == 0) {
            return 0;
        }

        return v3 * zzca.zzg(arg2, 0);
    }

    static int zzx(int arg0, List arg1, boolean arg2) {
        int v1 = arg1.size();
        if(v1 == 0) {
            return 0;
        }

        return v1 * zzca.zzc(arg0, true);
    }
}

