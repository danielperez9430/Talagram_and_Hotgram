package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzk {
    private static int zza(byte[] arg2, int arg3) {
        return (arg2[arg3 + 3] & 255) << 24 | (arg2[arg3] & 255 | (arg2[arg3 + 1] & 255) << 8 | (arg2[arg3 + 2] & 255) << 16);
    }

    private static long zza(long arg3, long arg5, long arg7) {
        arg3 = (arg3 ^ arg5) * arg7;
        arg3 = (arg3 ^ arg3 >>> 47 ^ arg5) * arg7;
        return (arg3 ^ arg3 >>> 47) * arg7;
    }

    public static long zza(byte[] arg28) {
        long v20;
        long v14_1;
        long v1_1;
        long v19;
        long v8_1;
        long v5;
        byte[] v7 = arg28;
        int v0 = v7.length;
        if(v0 >= 0 && v0 <= v7.length) {
            int v8 = 37;
            int v1 = 2;
            int v4 = 43;
            long v9 = -5435081209227447693L;
            int v11 = 16;
            int v6 = 8;
            long v12 = -7286425919675154353L;
            int v14 = 0;
            if(v0 <= 32) {
                if(v0 > v11) {
                    v19 = (((long)(v0 << 1))) + v12;
                    v1_1 = zzk.zzb(v7, 0) * v9;
                    v5 = zzk.zzb(v7, v6);
                    v0 = v0;
                    v8_1 = zzk.zzb(v7, v0 - 8) * v19;
                    return zzk.zza(Long.rotateRight(v1_1 + v5, v4) + Long.rotateRight(v8_1, 30) + zzk.zzb(v7, v0 - v11) * v12, v1_1 + Long.rotateRight(v5 + v12, 18) + v8_1, v19);
                }
                else if(v0 >= v6) {
                    v19 = (((long)(v0 << 1))) + v12;
                    v1_1 = zzk.zzb(v7, 0) + v12;
                    long v3 = zzk.zzb(v7, v0 - v6);
                    return zzk.zza(Long.rotateRight(v3, v8) * v19 + v1_1, (Long.rotateRight(v1_1, 25) + v3) * v19, v19);
                }
                else if(v0 >= 4) {
                    return zzk.zza((((((long)zzk.zza(v7, 0))) & 4294967295L) << 3) + (((long)v0)), (((long)zzk.zza(v7, v0 - 4))) & 4294967295L, (((long)(v0 << 1))) + v12);
                }
                else if(v0 > 0) {
                    v1_1 = (((long)((v7[0] & 255) + ((v7[v0 >> 1] & 255) << v6)))) * v12 ^ (((long)(v0 + ((v7[v0 - 1] & 255) << 2)))) * -4348849565147123417L;
                    return (v1_1 ^ v1_1 >>> 47) * v12;
                }
                else {
                    return v12;
                }
            }
            else if(v0 <= 64) {
                v1_1 = (((long)(v0 << 1))) + v12;
                v8_1 = zzk.zzb(v7, 0) * v12;
                v5 = zzk.zzb(v7, v6);
                v0 = v0;
                v14_1 = zzk.zzb(v7, v0 - 8) * v1_1;
                long v10 = Long.rotateRight(v8_1 + v5, v4) + Long.rotateRight(v14_1, 30) + zzk.zzb(v7, v0 - 16) * v12;
                v5 = zzk.zza(v10, Long.rotateRight(v5 - 7286425919675154353L, 18) + v8_1 + v14_1, v1_1);
                v12 = zzk.zzb(v7, 16) * v1_1;
                v14_1 = zzk.zzb(v7, 24);
                v10 = (v10 + zzk.zzb(v7, v0 - 32)) * v1_1;
                return zzk.zza(Long.rotateRight(v12 + v14_1, 43) + Long.rotateRight(v10, 30) + (v5 + zzk.zzb(v7, v0 - 24)) * v1_1, v12 + Long.rotateRight(v14_1 + v8_1, 18) + v10, v1_1);
            }
            else {
                long v2 = 2480279821605975764L;
                long v4_1 = 1390051526045402406L;
                long[] v11_1 = new long[v1];
                long[] v12_1 = new long[v1];
                long v18 = zzk.zzb(v7, 0) + 95310865018149119L;
                --v0;
                v6 = v0 / 64 << 6;
                v1 = v0 & 63;
                int v16 = v6 + v1 - 63;
                int v17 = 0;
                while(true) {
                    v14_1 = Long.rotateRight(v18 + v2 + v11_1[v14] + zzk.zzb(v7, v17 + 8), v8) * v9;
                    v2 = Long.rotateRight(v2 + v11_1[1] + zzk.zzb(v7, v17 + 48), 42) * v9;
                    v14_1 ^= v12_1[1];
                    v18 = v2 + (v11_1[0] + zzk.zzb(v7, v17 + 40));
                    v20 = Long.rotateRight(v4_1 + v12_1[0], 33) * v9;
                    v2 = v11_1[1] * v9;
                    v8 = v1;
                    int v9_1 = v6;
                    zzk.zza(arg28, v17, v2, v12_1[0] + v14_1, v11_1);
                    zzk.zza(arg28, v17 + 32, v20 + v12_1[1], v18 + zzk.zzb(v7, v17 + 16), v12_1);
                    v0 = v17 + 64;
                    if(v0 == v9_1) {
                        break;
                    }

                    v17 = v0;
                    v1 = v8;
                    v6 = v9_1;
                    v4_1 = v14_1;
                    v2 = v18;
                    v18 = v20;
                    v8 = 37;
                    v9 = -5435081209227447693L;
                    v14 = 0;
                }

                v9 = ((255 & v14_1) << 1) - 5435081209227447693L;
                v12_1[0] += ((long)v8);
                v11_1[0] += v12_1[0];
                v12_1[0] += v11_1[0];
                long v0_1 = Long.rotateRight(v20 + v18 + v11_1[0] + zzk.zzb(v7, v16 + 8), 37) * v9;
                v2 = Long.rotateRight(v18 + v11_1[1] + zzk.zzb(v7, v16 + 48), 42) * v9;
                long v17_1 = v0_1 ^ v12_1[1] * 9;
                v19 = v2 + (v11_1[0] * 9 + zzk.zzb(v7, v16 + 40));
                v14_1 = Long.rotateRight(v14_1 + v12_1[0], 33) * v9;
                zzk.zza(arg28, v16, v11_1[1] * v9, v17_1 + v12_1[0], v11_1);
                zzk.zza(arg28, v16 + 32, v12_1[1] + v14_1, v19 + zzk.zzb(v7, v16 + 16), v12_1);
                return zzk.zza(zzk.zza(v11_1[0], v12_1[0], v9) + (v19 ^ v19 >>> 47) * -4348849565147123417L + v17_1, zzk.zza(v11_1[1], v12_1[1], v9) + v14_1, v9);
            }
        }

        StringBuilder v3_1 = new StringBuilder(67);
        v3_1.append("Out of bound index with offput: 0 and length: ");
        v3_1.append(v0);
        throw new IndexOutOfBoundsException(v3_1.toString());
    }

    private static void zza(byte[] arg6, int arg7, long arg8, long arg10, long[] arg12) {
        long v0 = zzk.zzb(arg6, arg7);
        long v2 = zzk.zzb(arg6, arg7 + 8);
        long v4 = zzk.zzb(arg6, arg7 + 16);
        long v6 = zzk.zzb(arg6, arg7 + 24);
        arg8 += v0;
        v2 = v2 + arg8 + v4;
        arg10 = Long.rotateRight(arg10 + arg8 + v6, 21) + Long.rotateRight(v2, 44);
        arg12[0] = v2 + v6;
        arg12[1] = arg10 + arg8;
    }

    private static long zzb(byte[] arg1, int arg2) {
        ByteBuffer v1 = ByteBuffer.wrap(arg1, arg2, 8);
        v1.order(ByteOrder.LITTLE_ENDIAN);
        return v1.getLong();
    }
}

