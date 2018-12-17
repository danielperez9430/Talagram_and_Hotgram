package com.google.android.gms.internal.clearcut;

final class zzax {
    static int zza(int arg2, byte[] arg3, int arg4, int arg5, zzay arg6) {
        if(arg2 >>> 3 == 0) {
            goto label_32;
        }

        int v0 = arg2 & 7;
        if(v0 == 5) {
            goto label_30;
        }

        switch(v0) {
            case 0: {
                goto label_28;
            }
            case 1: {
                goto label_26;
            }
            case 2: {
                goto label_22;
            }
            case 3: {
                goto label_8;
            }
        }

        throw zzco.zzbm();
    label_22:
        return zzax.zza(arg3, arg4, arg6) + arg6.zzfd;
    label_8:
        arg2 = arg2 & -8 | 4;
        v0 = 0;
        while(arg4 < arg5) {
            arg4 = zzax.zza(arg3, arg4, arg6);
            v0 = arg6.zzfd;
            if(v0 == arg2) {
                break;
            }

            arg4 = zzax.zza(v0, arg3, arg4, arg5, arg6);
        }

        if(arg4 <= arg5 && v0 == arg2) {
            return arg4;
        }

        throw zzco.zzbo();
    label_26:
        return arg4 + 8;
    label_28:
        return zzax.zzb(arg3, arg4, arg6);
    label_30:
        return arg4 + 4;
    label_32:
        throw zzco.zzbm();
    }

    static int zza(byte[] arg1, int arg2, zzay arg3) {
        int v0 = arg2 + 1;
        arg2 = arg1[arg2];
        if(arg2 >= 0) {
            arg3.zzfd = arg2;
            return v0;
        }

        return zzax.zza(arg2, arg1, v0, arg3);
    }

    static int zza(int arg2, byte[] arg3, int arg4, int arg5, zzcn arg6, zzay arg7) {
        arg4 = zzax.zza(arg3, arg4, arg7);
        while(true) {
            ((zzch)arg6).zzac(arg7.zzfd);
            if(arg4 < arg5) {
                int v0 = zzax.zza(arg3, arg4, arg7);
                if(arg2 == arg7.zzfd) {
                    arg4 = zzax.zza(arg3, v0, arg7);
                    continue;
                }
            }

            return arg4;
        }

        return arg4;
    }

    static int zza(int arg9, byte[] arg10, int arg11, int arg12, zzey arg13, zzay arg14) {
        int v2;
        if(arg9 >>> 3 == 0) {
            goto label_59;
        }

        int v0 = arg9 & 7;
        if(v0 == 5) {
            goto label_54;
        }

        switch(v0) {
            case 0: {
                goto label_49;
            }
            case 1: {
                goto label_44;
            }
            case 2: {
                goto label_34;
            }
            case 3: {
                goto label_8;
            }
        }

        throw zzco.zzbm();
    label_49:
        int v10 = zzax.zzb(arg10, arg11, arg14);
        arg13.zzb(arg9, Long.valueOf(arg14.zzfe));
        return v10;
    label_34:
        arg11 = zzax.zza(arg10, arg11, arg14);
        arg12 = arg14.zzfd;
        zzbb v10_1 = arg12 == 0 ? zzbb.zzfi : zzbb.zzb(arg10, arg11, arg12);
        arg13.zzb(arg9, v10_1);
        return arg11 + arg12;
    label_8:
        zzey v6 = zzey.zzeb();
        int v7 = arg9 & -8 | 4;
        v0 = 0;
        while(true) {
            if(arg11 < arg12) {
                v2 = zzax.zza(arg10, arg11, arg14);
                arg11 = arg14.zzfd;
                if(arg11 != v7) {
                    v0 = arg11;
                    arg11 = zzax.zza(arg11, arg10, v2, arg12, v6, arg14);
                    continue;
                }
                else {
                    break;
                }
            }

            goto label_28;
        }

        v0 = arg11;
        arg11 = v2;
    label_28:
        if(arg11 <= arg12 && v0 == v7) {
            arg13.zzb(arg9, v6);
            return arg11;
        }

        throw zzco.zzbo();
    label_44:
        arg13.zzb(arg9, Long.valueOf(zzax.zzd(arg10, arg11)));
        return arg11 + 8;
    label_54:
        arg13.zzb(arg9, Integer.valueOf(zzax.zzc(arg10, arg11)));
        return arg11 + 4;
    label_59:
        throw zzco.zzbm();
    }

    static int zza(int arg1, byte[] arg2, int arg3, zzay arg4) {
        int v2;
        arg1 &= 127;
        int v0 = arg3 + 1;
        arg3 = arg2[arg3];
        if(arg3 >= 0) {
            v2 = arg3 << 7;
        }
        else {
            arg1 |= (arg3 & 127) << 7;
            arg3 = v0 + 1;
            v0 = arg2[v0];
            if(v0 >= 0) {
                v2 = v0 << 14;
            }
            else {
                arg1 |= (v0 & 127) << 14;
                v0 = arg3 + 1;
                arg3 = arg2[arg3];
                if(arg3 >= 0) {
                    v2 = arg3 << 21;
                    goto label_5;
                }
                else {
                    goto label_26;
                }
            }

            goto label_15;
        }

    label_5:
        arg4.zzfd = arg1 | v2;
        return v0;
    label_26:
        arg1 |= (arg3 & 127) << 21;
        arg3 = v0 + 1;
        v0 = arg2[v0];
        if(v0 >= 0) {
            v2 = v0 << 28;
        }
        else {
            goto label_34;
        }

    label_15:
        arg4.zzfd = arg1 | v2;
        return arg3;
    label_34:
        arg1 |= (v0 & 127) << 28;
        while(true) {
            v0 = arg3 + 1;
            if(arg2[arg3] >= 0) {
                break;
            }

            arg3 = v0;
        }

        arg4.zzfd = arg1;
        return v0;
    }

    static int zza(byte[] arg2, int arg3, zzcn arg4, zzay arg5) {
        arg3 = zzax.zza(arg2, arg3, arg5);
        int v0 = arg5.zzfd + arg3;
        while(arg3 < v0) {
            arg3 = zzax.zza(arg2, arg3, arg5);
            ((zzch)arg4).zzac(arg5.zzfd);
        }

        if(arg3 == v0) {
            return arg3;
        }

        throw zzco.zzbl();
    }

    static int zzb(byte[] arg9, int arg10, zzay arg11) {
        int v0 = arg10 + 1;
        long v1 = ((long)arg9[arg10]);
        if(v1 >= 0) {
            arg11.zzfe = v1;
            return v0;
        }

        arg10 = v0 + 1;
        v0 = arg9[v0];
        int v5 = 7;
        long v2 = v1 & 127 | (((long)(v0 & 127))) << v5;
        int v1_1 = 7;
        while(v0 < 0) {
            v1_1 += v5;
            v2 |= (((long)(arg9[arg10] & 127))) << v1_1;
            v0 = arg9[arg10];
            ++arg10;
        }

        arg11.zzfe = v2;
        return arg10;
    }

    static int zzc(byte[] arg2, int arg3) {
        return (arg2[arg3 + 3] & 255) << 24 | (arg2[arg3] & 255 | (arg2[arg3 + 1] & 255) << 8 | (arg2[arg3 + 2] & 255) << 16);
    }

    static int zzc(byte[] arg3, int arg4, zzay arg5) {
        arg4 = zzax.zza(arg3, arg4, arg5);
        int v0 = arg5.zzfd;
        if(v0 == 0) {
            arg5.zzff = "";
            return arg4;
        }

        arg5.zzff = new String(arg3, arg4, v0, zzci.UTF_8);
        return arg4 + v0;
    }

    static long zzd(byte[] arg7, int arg8) {
        return ((((long)arg7[arg8 + 7])) & 255) << 56 | ((((long)arg7[arg8])) & 255 | ((((long)arg7[arg8 + 1])) & 255) << 8 | ((((long)arg7[arg8 + 2])) & 255) << 16 | ((((long)arg7[arg8 + 3])) & 255) << 24 | ((((long)arg7[arg8 + 4])) & 255) << 32 | ((((long)arg7[arg8 + 5])) & 255) << 40 | ((((long)arg7[arg8 + 6])) & 255) << 48);
    }

    static int zzd(byte[] arg4, int arg5, zzay arg6) {
        arg5 = zzax.zza(arg4, arg5, arg6);
        int v0 = arg6.zzfd;
        if(v0 == 0) {
            arg6.zzff = "";
            return arg5;
        }

        int v1 = arg5 + v0;
        if(zzff.zze(arg4, arg5, v1)) {
            arg6.zzff = new String(arg4, arg5, v0, zzci.UTF_8);
            return v1;
        }

        throw zzco.zzbp();
    }

    static double zze(byte[] arg0, int arg1) {
        return Double.longBitsToDouble(zzax.zzd(arg0, arg1));
    }

    static int zze(byte[] arg1, int arg2, zzay arg3) {
        arg2 = zzax.zza(arg1, arg2, arg3);
        int v0 = arg3.zzfd;
        if(v0 == 0) {
            arg3.zzff = zzbb.zzfi;
            return arg2;
        }

        arg3.zzff = zzbb.zzb(arg1, arg2, v0);
        return arg2 + v0;
    }

    static float zzf(byte[] arg0, int arg1) {
        return Float.intBitsToFloat(zzax.zzc(arg0, arg1));
    }
}

