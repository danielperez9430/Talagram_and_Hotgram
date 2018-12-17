package com.google.android.gms.internal.measurement;

final class zzyk {
    private static void zza(byte arg2, byte arg3, byte arg4, byte arg5, char[] arg6, int arg7) {
        if(!zzyk.zzg(arg3) && (arg2 << 28) + (arg3 + 112) >> 30 == 0 && !zzyk.zzg(arg4) && !zzyk.zzg(arg5)) {
            int v2 = (arg2 & 7) << 18 | (arg3 & 63) << 12 | (arg4 & 63) << 6 | arg5 & 63;
            arg6[arg7] = ((char)((v2 >>> 10) + 55232));
            arg6[arg7 + 1] = ((char)((v2 & 1023) + 56320));
            return;
        }

        throw zzvt.zzwr();
    }

    private static void zza(byte arg2, byte arg3, byte arg4, char[] arg5, int arg6) {
        if(!zzyk.zzg(arg3)) {
            int v1 = -96;
            if(arg2 == -32 && arg3 < v1) {
                goto label_21;
            }

            if(arg2 == -19 && arg3 >= v1) {
                goto label_21;
            }

            if(zzyk.zzg(arg4)) {
                goto label_21;
            }

            arg5[arg6] = ((char)((arg2 & 15) << 12 | (arg3 & 63) << 6 | arg4 & 63));
            return;
        }

    label_21:
        throw zzvt.zzwr();
    }

    private static void zza(byte arg1, byte arg2, char[] arg3, int arg4) {
        if(arg1 >= -62 && !zzyk.zzg(arg2)) {
            arg3[arg4] = ((char)((arg1 & 31) << 6 | arg2 & 63));
            return;
        }

        throw zzvt.zzwr();
    }

    private static void zza(byte arg0, char[] arg1, int arg2) {
        arg1[arg2] = ((char)arg0);
    }

    static void zzb(byte arg0, byte arg1, byte arg2, byte arg3, char[] arg4, int arg5) {
        zzyk.zza(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    static void zzb(byte arg0, byte arg1, byte arg2, char[] arg3, int arg4) {
        zzyk.zza(arg0, arg1, arg2, arg3, arg4);
    }

    static void zzb(byte arg0, byte arg1, char[] arg2, int arg3) {
        zzyk.zza(arg0, arg1, arg2, arg3);
    }

    static void zzb(byte arg0, char[] arg1, int arg2) {
        zzyk.zza(arg0, arg1, arg2);
    }

    private static boolean zzd(byte arg0) {
        if(arg0 >= 0) {
            return 1;
        }

        return 0;
    }

    private static boolean zze(byte arg1) {
        if(arg1 < -32) {
            return 1;
        }

        return 0;
    }

    private static boolean zzf(byte arg1) {
        if(arg1 < -16) {
            return 1;
        }

        return 0;
    }

    private static boolean zzg(byte arg1) {
        if(arg1 > -65) {
            return 1;
        }

        return 0;
    }

    static boolean zzh(byte arg0) {
        return zzyk.zzd(arg0);
    }

    static boolean zzi(byte arg0) {
        return zzyk.zze(arg0);
    }

    static boolean zzj(byte arg0) {
        return zzyk.zzf(arg0);
    }
}

