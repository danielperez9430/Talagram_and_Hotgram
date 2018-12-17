package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;

abstract class zzfg {
    zzfg() {
        super();
    }

    abstract int zzb(CharSequence arg1, byte[] arg2, int arg3, int arg4);

    abstract void zzb(CharSequence arg1, ByteBuffer arg2);

    abstract int zzb(int arg1, byte[] arg2, int arg3, int arg4);

    static void zzc(CharSequence arg7, ByteBuffer arg8) {
        byte v6;
        int v5;
        char v4_1;
        int v4;
        int v3;
        int v0 = arg7.length();
        int v1 = arg8.position();
        int v2 = 0;
        while(true) {
            v3 = 128;
            if(v2 < v0) {
                try {
                    v4 = arg7.charAt(v2);
                    if(v4 < v3) {
                        arg8.put(v1 + v2, ((byte)v4));
                        ++v2;
                        continue;
                    }

                label_12:
                    if(v2 == v0) {
                        arg8.position(v1 + v2);
                        return;
                    }

                    v1 += v2;
                    while(true) {
                    label_17:
                        if(v2 >= v0) {
                            goto label_100;
                        }

                        v4_1 = arg7.charAt(v2);
                        if(v4_1 < v3) {
                            arg8.put(v1, ((byte)v4_1));
                        }
                        else {
                            goto label_23;
                        }

                        goto label_97;
                    }
                }
                catch(IndexOutOfBoundsException ) {
                    goto label_102;
                }
            }

            goto label_12;
        }

    label_23:
        if(v4_1 < 2048) {
            v5 = v1 + 1;
            v6 = ((byte)(v4_1 >>> 6 | 192));
            try {
                arg8.put(v1, v6);
                arg8.put(v5, ((byte)(v4_1 & 63 | v3)));
                v1 = v5;
                goto label_97;
            }
            catch(IndexOutOfBoundsException ) {
                goto label_36;
            }
        }
        else {
            if(v4_1 >= 55296) {
                if(57343 < v4_1) {
                }
                else {
                    v5 = v2 + 1;
                    if(v5 != v0) {
                        try {
                            char v2_1 = arg7.charAt(v5);
                            if(Character.isSurrogatePair(v4_1, v2_1)) {
                                v2 = Character.toCodePoint(v4_1, v2_1);
                                v4 = v1 + 1;
                                v6 = ((byte)(v2 >>> 18 | 240));
                            }
                            else {
                                goto label_75;
                            }
                        }
                        catch(IndexOutOfBoundsException ) {
                            goto label_77;
                        }

                        try {
                            arg8.put(v1, v6);
                            v1 = v4 + 1;
                            v6 = ((byte)(v2 >>> 12 & 63 | v3));
                        }
                        catch(IndexOutOfBoundsException ) {
                            goto label_73;
                        }

                        try {
                            arg8.put(v4, v6);
                            v4 = v1 + 1;
                            v6 = ((byte)(v2 >>> 6 & 63 | v3));
                        }
                        catch(IndexOutOfBoundsException ) {
                            goto label_77;
                        }

                        try {
                            arg8.put(v1, v6);
                            arg8.put(v4, ((byte)(v2 & 63 | v3)));
                            v1 = v4;
                            v2 = v5;
                            goto label_97;
                        }
                        catch(IndexOutOfBoundsException ) {
                        label_73:
                            v1 = v4;
                        }

                    label_77:
                        v2 = v5;
                        goto label_102;
                    label_75:
                        v2 = v5;
                    }

                    try {
                        throw new zzfi(v2, v0);
                    }
                    catch(IndexOutOfBoundsException ) {
                        goto label_102;
                    }
                }
            }

            v5 = v1 + 1;
            v6 = ((byte)(v4_1 >>> 12 | 224));
            try {
                arg8.put(v1, v6);
                v1 = v5 + 1;
                v6 = ((byte)(v4_1 >>> 6 & 63 | v3));
            }
            catch(IndexOutOfBoundsException ) {
            label_36:
                v1 = v5;
                goto label_102;
            }

            try {
                arg8.put(v5, v6);
                arg8.put(v1, ((byte)(v4_1 & 63 | v3)));
            label_97:
                ++v2;
                ++v1;
                goto label_17;
            label_100:
                arg8.position(v1);
                return;
            }
            catch(IndexOutOfBoundsException ) {
            label_102:
                v0 = arg8.position() + Math.max(v2, v1 - arg8.position() + 1);
                char v7 = arg7.charAt(v2);
                StringBuilder v2_2 = new StringBuilder(37);
                v2_2.append("Failed writing ");
                v2_2.append(v7);
                v2_2.append(" at index ");
                v2_2.append(v0);
                throw new ArrayIndexOutOfBoundsException(v2_2.toString());
            }
        }

        goto label_97;
    }

    final boolean zze(byte[] arg2, int arg3, int arg4) {
        if(this.zzb(0, arg2, arg3, arg4) == 0) {
            return 1;
        }

        return 0;
    }
}

