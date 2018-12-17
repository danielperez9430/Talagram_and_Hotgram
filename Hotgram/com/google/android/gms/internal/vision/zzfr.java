package com.google.android.gms.internal.vision;

final class zzfr extends zzfo {
    zzfr() {
        super();
    }

    private static int zza(byte[] arg2, int arg3, long arg4, int arg6) {
        switch(arg6) {
            case 0: {
                goto label_13;
            }
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_4;
            }
        }

        throw new AssertionError();
    label_4:
        return zzfn.zzd(arg3, zzfl.zza(arg2, arg4), zzfl.zza(arg2, arg4 + 1));
    label_10:
        return zzfn.zzu(arg3, zzfl.zza(arg2, arg4));
    label_13:
        return zzfn.zzaq(arg3);
    }

    final int zzb(int arg18, byte[] arg19, int arg20, int arg21) {
        int v12;
        long v15;
        int v7_1;
        long v2_1;
        long v13;
        byte[] v0 = arg19;
        int v1 = arg20;
        int v2 = arg21;
        int v4 = 2;
        int v5 = 3;
        if((v1 | v2 | v0.length - v2) >= 0) {
            long v7 = ((long)v1);
            v1 = ((int)((((long)v2)) - v7));
            long v9 = 1;
            if(v1 < 16) {
                v2 = 0;
            }
            else {
                long v11 = v7;
                v2 = 0;
                while(true) {
                    if(v2 < v1) {
                        v13 = v11 + v9;
                        if(zzfl.zza(v0, v11) < 0) {
                        }
                        else {
                            ++v2;
                            v11 = v13;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    goto label_31;
                }

                v2 = v1;
            }

        label_31:
            v1 -= v2;
            v7 += ((long)v2);
            do {
            label_34:
                v2 = 0;
                while(true) {
                    if(v1 > 0) {
                        v2_1 = v7 + v9;
                        v7_1 = zzfl.zza(v0, v7);
                        if(v7_1 >= 0) {
                            --v1;
                            v15 = v2_1;
                            v2 = v7_1;
                            v7 = v15;
                            continue;
                        }
                        else {
                            break;
                        }
                    }

                    goto label_47;
                }

                v15 = v2_1;
                v2 = v7_1;
                v7 = v15;
            label_47:
                if(v1 == 0) {
                    return 0;
                }

                --v1;
                int v3 = -32;
                int v11_1 = -65;
                v12 = -1;
                if(v2 < v3) {
                    if(v1 == 0) {
                        return v2;
                    }

                    --v1;
                    if(v2 >= -62) {
                        v2_1 = v7 + v9;
                        if(zzfl.zza(v0, v7) > v11_1) {
                        }
                        else {
                            v7 = v2_1;
                            goto label_34;
                        }
                    }

                    return v12;
                }

                if(v2 < -16) {
                    if(v1 < v4) {
                        return zzfr.zza(v0, v2, v7, v1);
                    }

                    v1 += -2;
                    v13 = v7 + v9;
                    v7_1 = zzfl.zza(v0, v7);
                    if(v7_1 <= v11_1) {
                        int v8 = -96;
                        if(v2 == v3 && v7_1 < v8) {
                            return v12;
                        }

                        if(v2 == -19 && v7_1 >= v8) {
                            return v12;
                        }

                        v7 = v13 + v9;
                        if(zzfl.zza(v0, v13) <= v11_1) {
                            goto label_34;
                        }
                    }

                    return v12;
                }

                if(v1 < v5) {
                    return zzfr.zza(v0, v2, v7, v1);
                }

                v1 += -3;
                v13 = v7 + v9;
                v3 = zzfl.zza(v0, v7);
                if(v3 > v11_1) {
                    return v12;
                }

                if((v2 << 28) + (v3 + 112) >> 30 != 0) {
                    return v12;
                }

                v2_1 = v13 + v9;
                if(zzfl.zza(v0, v13) > v11_1) {
                    return v12;
                }

                v7 = v2_1 + v9;
            }
            while(zzfl.zza(v0, v2_1) <= v11_1);

            return v12;
        }

        Object[] v5_1 = new Object[v5];
        v5_1[0] = Integer.valueOf(v0.length);
        v5_1[1] = Integer.valueOf(arg20);
        v5_1[v4] = Integer.valueOf(arg21);
        throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", v5_1));
    }

    final int zzb(CharSequence arg18, byte[] arg19, int arg20, int arg21) {
        long v12;
        int v12_1;
        char v11_1;
        int v11;
        long v9;
        CharSequence v0 = arg18;
        byte[] v1 = arg19;
        int v2 = arg20;
        int v3 = arg21;
        long v4 = ((long)v2);
        long v6 = (((long)v3)) + v4;
        int v8 = arg18.length();
        if(v8 <= v3 && v1.length - v3 >= v2) {
            v2 = 0;
            while(true) {
                v3 = 128;
                v9 = 1;
                if(v2 < v8) {
                    v11 = v0.charAt(v2);
                    if(v11 < v3) {
                        zzfl.zza(v1, v4, ((byte)v11));
                        ++v2;
                        v4 = v9 + v4;
                        continue;
                    }
                }

                break;
            }

            if(v2 == v8) {
                return ((int)v4);
            }

            while(true) {
                if(v2 >= v8) {
                    goto label_136;
                }

                v11_1 = v0.charAt(v2);
                if(v11_1 >= v3 || v4 >= v6) {
                    if(v11_1 < 2048 && v4 <= v6 - 2) {
                        v12 = v4 + v9;
                        zzfl.zza(v1, v4, ((byte)(v11_1 >>> 6 | 960)));
                        v4 = v12 + v9;
                        zzfl.zza(v1, v12, ((byte)(v11_1 & 63 | v3)));
                        goto label_106;
                    }

                    v12_1 = 57343;
                    int v13 = 55296;
                    if((v11_1 < v13 || v12_1 < v11_1) && v4 <= v6 - 3) {
                        v12 = v4 + v9;
                        zzfl.zza(v1, v4, ((byte)(v11_1 >>> 12 | 480)));
                        v4 = v12 + v9;
                        zzfl.zza(v1, v12, ((byte)(v11_1 >>> 6 & 63 | v3)));
                        v12 = v4 + v9;
                        v11 = v11_1 & 63 | v3;
                    label_32:
                        zzfl.zza(v1, v4, ((byte)v11_1));
                        v4 = v12;
                        goto label_106;
                    }

                    if(v4 > v6 - 4) {
                        goto label_113;
                    }

                    v12_1 = v2 + 1;
                    if(v12_1 != v8) {
                        char v2_1 = v0.charAt(v12_1);
                        if(Character.isSurrogatePair(v11_1, v2_1)) {
                            v2 = Character.toCodePoint(v11_1, v2_1);
                            long v13_1 = v4 + v9;
                            zzfl.zza(v1, v4, ((byte)(v2 >>> 18 | 240)));
                            v4 = v13_1 + v9;
                            zzfl.zza(v1, v13_1, ((byte)(v2 >>> 12 & 63 | v3)));
                            v13_1 = v4 + v9;
                            zzfl.zza(v1, v4, ((byte)(v2 >>> 6 & 63 | v3)));
                            v4 = v13_1 + v9;
                            zzfl.zza(v1, v13_1, ((byte)(v2 & 63 | v3)));
                            v2 = v12_1;
                            goto label_106;
                        }
                    }
                    else {
                        break;
                    }

                    goto label_109;
                }
                else {
                    v12 = v4 + v9;
                    goto label_32;
                }

            label_106:
                ++v2;
            }

            v12_1 = v2;
        label_109:
            throw new zzfq(v12_1 - 1, v8);
        label_113:
            if(v13 <= v11_1 && v11_1 <= v12_1) {
                int v1_1 = v2 + 1;
                if(v1_1 != v8 && (Character.isSurrogatePair(v11_1, v0.charAt(v1_1)))) {
                    goto label_123;
                }

                throw new zzfq(v2, v8);
            }

        label_123:
            StringBuilder v2_2 = new StringBuilder(46);
            v2_2.append("Failed writing ");
            v2_2.append(v11_1);
            v2_2.append(" at index ");
            v2_2.append(v4);
            throw new ArrayIndexOutOfBoundsException(v2_2.toString());
        label_136:
            return ((int)v4);
        }

        char v0_1 = v0.charAt(v8 - 1);
        StringBuilder v4_1 = new StringBuilder(37);
        v4_1.append("Failed writing ");
        v4_1.append(v0_1);
        v4_1.append(" at index ");
        v4_1.append(v2 + v3);
        throw new ArrayIndexOutOfBoundsException(v4_1.toString());
    }
}

