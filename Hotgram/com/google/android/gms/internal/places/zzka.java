package com.google.android.gms.internal.places;

import java.nio.ByteBuffer;

final class zzka extends zzjz {
    zzka() {
        super();
    }

    final int zzc(int arg7, byte[] arg8, int arg9, int arg10) {
        while(arg9 < arg10) {
            if(arg8[arg9] < 0) {
                break;
            }

            ++arg9;
        }

        if(arg9 >= arg10) {
            return 0;
        }

        while(arg9 < arg10) {
            int v0 = arg9 + 1;
            arg9 = arg8[arg9];
            if(arg9 < 0) {
                int v1 = -32;
                int v2 = -1;
                int v3 = -65;
                if(arg9 >= v1) {
                    if(arg9 < -16) {
                        if(v0 >= arg10 - 1) {
                            return zzjy.zzj(arg8, v0, arg10);
                        }

                        int v4 = v0 + 1;
                        v0 = arg8[v0];
                        if(v0 <= v3) {
                            int v5 = -96;
                            if(arg9 == v1 && v0 < v5) {
                                return v2;
                            }

                            if(arg9 == -19 && v0 >= v5) {
                                return v2;
                            }

                            arg9 = v4 + 1;
                            if(arg8[v4] <= v3) {
                                continue;
                            }
                        }

                        return v2;
                    }

                    if(v0 >= arg10 - 2) {
                        return zzjy.zzj(arg8, v0, arg10);
                    }

                    v1 = v0 + 1;
                    v0 = arg8[v0];
                    if(v0 <= v3 && (arg9 << 28) + (v0 + 112) >> 30 == 0) {
                        arg9 = v1 + 1;
                        if(arg8[v1] <= v3) {
                            v0 = arg9 + 1;
                            if(arg8[arg9] > v3) {
                                return v2;
                            }

                            goto label_63;
                        }
                    }

                    return v2;
                }
                else if(v0 >= arg10) {
                    return arg9;
                }
                else {
                    if(arg9 >= -62) {
                        arg9 = v0 + 1;
                        if(arg8[v0] > v3) {
                            return v2;
                        }

                        continue;
                    }

                    return v2;
                }
            }

        label_63:
            arg9 = v0;
        }

        return 0;
    }

    final int zzc(CharSequence arg8, byte[] arg9, int arg10, int arg11) {
        char v3_1;
        int v4;
        int v3;
        int v2;
        int v0 = arg8.length();
        arg11 += arg10;
        int v1 = 0;
        while(true) {
            v2 = 128;
            if(v1 < v0) {
                v3 = v1 + arg10;
                if(v3 < arg11) {
                    v4 = arg8.charAt(v1);
                    if(v4 < v2) {
                        arg9[v3] = ((byte)v4);
                        ++v1;
                        continue;
                    }
                }
            }

            break;
        }

        if(v1 == v0) {
            return arg10 + v0;
        }

        arg10 += v1;
        while(true) {
            if(v1 >= v0) {
                return arg10;
            }

            v3_1 = arg8.charAt(v1);
            if(v3_1 >= v2 || arg10 >= arg11) {
                if(v3_1 < 2048 && arg10 <= arg11 - 2) {
                    v4 = arg10 + 1;
                    arg9[arg10] = ((byte)(v3_1 >>> 6 | 960));
                    arg10 = v4 + 1;
                    arg9[v4] = ((byte)(v3_1 & 63 | v2));
                    goto label_96;
                }

                v4 = 57343;
                int v5 = 55296;
                if((v3_1 < v5 || v4 < v3_1) && arg10 <= arg11 - 3) {
                    v4 = arg10 + 1;
                    arg9[arg10] = ((byte)(v3_1 >>> 12 | 480));
                    arg10 = v4 + 1;
                    arg9[v4] = ((byte)(v3_1 >>> 6 & 63 | v2));
                    v4 = arg10 + 1;
                    arg9[arg10] = ((byte)(v3_1 & 63 | v2));
                label_24:
                    arg10 = v4;
                    goto label_96;
                }

                if(arg10 > arg11 - 4) {
                    goto label_103;
                }

                v4 = v1 + 1;
                if(v4 != arg8.length()) {
                    char v1_1 = arg8.charAt(v4);
                    if(Character.isSurrogatePair(v3_1, v1_1)) {
                        v1 = Character.toCodePoint(v3_1, v1_1);
                        v3 = arg10 + 1;
                        arg9[arg10] = ((byte)(v1 >>> 18 | 240));
                        arg10 = v3 + 1;
                        arg9[v3] = ((byte)(v1 >>> 12 & 63 | v2));
                        v3 = arg10 + 1;
                        arg9[arg10] = ((byte)(v1 >>> 6 & 63 | v2));
                        arg10 = v3 + 1;
                        arg9[v3] = ((byte)(v1 & 63 | v2));
                        v1 = v4;
                        goto label_96;
                    }
                    else {
                        break;
                    }
                }

                goto label_99;
            }
            else {
                v4 = arg10 + 1;
                arg9[arg10] = ((byte)v3_1);
                goto label_24;
            }

        label_96:
            ++v1;
        }

        v1 = v4;
    label_99:
        throw new zzkb(v1 - 1, v0);
    label_103:
        if(v5 <= v3_1 && v3_1 <= v4) {
            int v9 = v1 + 1;
            if(v9 != arg8.length() && (Character.isSurrogatePair(v3_1, arg8.charAt(v9)))) {
                goto label_114;
            }

            throw new zzkb(v1, v0);
        }

    label_114:
        StringBuilder v11 = new StringBuilder(37);
        v11.append("Failed writing ");
        v11.append(v3_1);
        v11.append(" at index ");
        v11.append(arg10);
        throw new ArrayIndexOutOfBoundsException(v11.toString());
        return arg10;
    }

    final void zzc(CharSequence arg1, ByteBuffer arg2) {
        zzka.zzd(arg1, arg2);
    }
}

