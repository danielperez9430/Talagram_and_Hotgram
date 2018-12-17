package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;

final class zzff {
    private static final zzfg zzqb;

    static {
        zzfh v0_2;
        int v0 = !zzfd.zzed() || !zzfd.zzee() ? 0 : 1;
        if(v0 != 0) {
            zzfj v0_1 = new zzfj();
        }
        else {
            v0_2 = new zzfh();
        }

        zzff.zzqb = ((zzfg)v0_2);
    }

    static int zza(CharSequence arg8) {
        int v4;
        int v0 = arg8.length();
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg8.charAt(v2) >= 128) {
                break;
            }
        }

        int v3 = v0;
        while(true) {
            if(v2 < v0) {
                v4 = arg8.charAt(v2);
                int v5 = 2048;
                if(v4 < v5) {
                    v3 += 127 - v4 >>> 31;
                    ++v2;
                    continue;
                }
                else {
                    break;
                }
            }

            goto label_43;
        }

        v4 = arg8.length();
        while(v2 < v4) {
            int v6 = arg8.charAt(v2);
            if(v6 < v5) {
                v1 += 127 - v6 >>> 31;
            }
            else {
                v1 += 2;
                if(55296 <= v6 && v6 <= 57343) {
                    if(Character.codePointAt(arg8, v2) >= 65536) {
                        ++v2;
                    }
                    else {
                        throw new zzfi(v2, v4);
                    }
                }
            }

            ++v2;
        }

        v3 += v1;
    label_43:
        if(v3 >= v0) {
            return v3;
        }

        long v0_1 = ((long)v3);
        StringBuilder v3_1 = new StringBuilder(54);
        v3_1.append("UTF-8 length does not fit in int: ");
        v3_1.append(v0_1 + 4294967296L);
        throw new IllegalArgumentException(v3_1.toString());
    }

    static int zza(CharSequence arg1, byte[] arg2, int arg3, int arg4) {
        return zzff.zzqb.zzb(arg1, arg2, arg3, arg4);
    }

    static void zza(CharSequence arg4, ByteBuffer arg5) {
        zzfg v0 = zzff.zzqb;
        if(arg5.hasArray()) {
            int v0_1 = arg5.arrayOffset();
            arg5.position(zzff.zza(arg4, arg5.array(), arg5.position() + v0_1, arg5.remaining()) - v0_1);
            return;
        }

        if(arg5.isDirect()) {
            v0.zzb(arg4, arg5);
            return;
        }

        zzfg.zzc(arg4, arg5);
    }

    private static int zzam(int arg1) {
        if(arg1 > -12) {
            arg1 = -1;
        }

        return arg1;
    }

    static int zzan(int arg0) {
        return zzff.zzam(arg0);
    }

    private static int zzd(int arg1, int arg2, int arg3) {
        if(arg1 <= -12) {
            int v0 = -65;
            if(arg2 <= v0) {
                if(arg3 > v0) {
                }
                else {
                    return arg1 ^ arg2 << 8 ^ arg3 << 16;
                }
            }
        }

        return -1;
    }

    public static boolean zze(byte[] arg1, int arg2, int arg3) {
        return zzff.zzqb.zze(arg1, arg2, arg3);
    }

    public static boolean zze(byte[] arg3) {
        return zzff.zzqb.zze(arg3, 0, arg3.length);
    }

    static int zze(int arg0, int arg1, int arg2) {
        return zzff.zzd(arg0, arg1, arg2);
    }

    private static int zzf(byte[] arg1, int arg2, int arg3) {
        int v0 = arg1[arg2 - 1];
        switch(arg3 - arg2) {
            case 0: {
                goto label_15;
            }
            case 1: {
                goto label_12;
            }
            case 2: {
                goto label_7;
            }
        }

        throw new AssertionError();
    label_7:
        return zzff.zzd(v0, arg1[arg2], arg1[arg2 + 1]);
    label_12:
        return zzff.zzp(v0, arg1[arg2]);
    label_15:
        return zzff.zzam(v0);
    }

    static int zzg(byte[] arg0, int arg1, int arg2) {
        return zzff.zzf(arg0, arg1, arg2);
    }

    private static int zzp(int arg1, int arg2) {
        if(arg1 <= -12) {
            if(arg2 > -65) {
            }
            else {
                return arg1 ^ arg2 << 8;
            }
        }

        return -1;
    }

    static int zzq(int arg0, int arg1) {
        return zzff.zzp(arg0, arg1);
    }
}

