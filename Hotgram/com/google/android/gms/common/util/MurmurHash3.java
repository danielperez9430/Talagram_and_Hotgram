package com.google.android.gms.common.util;

public class MurmurHash3 {
    private MurmurHash3() {
        super();
    }

    public static int murmurhash3_x86_32(byte[] arg5, int arg6, int arg7, int arg8) {
        int v2;
        int v1;
        int v0 = (arg7 & -4) + arg6;
        while(true) {
            v1 = 461845907;
            v2 = -862048943;
            if(arg6 >= v0) {
                break;
            }

            int v3 = (arg5[arg6] & 255 | (arg5[arg6 + 1] & 255) << 8 | (arg5[arg6 + 2] & 255) << 16 | arg5[arg6 + 3] << 24) * v2;
            arg8 ^= (v3 << 15 | v3 >>> 17) * v1;
            arg8 = (arg8 >>> 19 | arg8 << 13) * 5 - 430675100;
            arg6 += 4;
        }

        arg6 = 0;
        switch(arg7 & 3) {
            case 1: {
                goto label_48;
            }
            case 2: {
                goto label_43;
            }
            case 3: {
                goto label_39;
            }
        }

        goto label_57;
    label_39:
        arg6 = (arg5[v0 + 2] & 255) << 16;
    label_43:
        arg6 |= (arg5[v0 + 1] & 255) << 8;
    label_48:
        int v5 = (arg5[v0] & 255 | arg6) * v2;
        arg8 ^= (v5 >>> 17 | v5 << 15) * v1;
    label_57:
        v5 = arg8 ^ arg7;
        v5 = (v5 ^ v5 >>> 16) * -2048144789;
        v5 = (v5 ^ v5 >>> 13) * -1028477387;
        return v5 ^ v5 >>> 16;
    }
}

