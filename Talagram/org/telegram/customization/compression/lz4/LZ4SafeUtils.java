package org.telegram.customization.compression.lz4;

import org.telegram.customization.compression.a.c;

enum LZ4SafeUtils {
    class Match {
        int len;
        int ref;
        int start;

        Match() {
            super();
        }

        int end() {
            return this.start + this.len;
        }

        void fix(int arg2) {
            this.start += arg2;
            this.ref += arg2;
            this.len -= arg2;
        }
    }

    static {
        LZ4SafeUtils.$VALUES = new LZ4SafeUtils[0];
    }

    private LZ4SafeUtils(String arg1, int arg2) {
        super(arg1, arg2);
    }

    static int commonBytes(byte[] arg3, int arg4, int arg5, int arg6) {
        int v0 = 0;
        while(arg5 < arg6) {
            int v1 = arg4 + 1;
            int v2 = arg5 + 1;
            if(arg3[arg4] != arg3[arg5]) {
                return v0;
            }

            ++v0;
            arg4 = v1;
            arg5 = v2;
        }

        return v0;
    }

    static int commonBytesBackward(byte[] arg3, int arg4, int arg5, int arg6, int arg7) {
        int v0;
        for(v0 = 0; arg4 > arg6; ++v0) {
            if(arg5 <= arg7) {
                return v0;
            }

            --arg4;
            --arg5;
            if(arg3[arg4] != arg3[arg5]) {
                return v0;
            }
        }

        return v0;
    }

    static void copy8Bytes(byte[] arg3, int arg4, byte[] arg5, int arg6) {
        int v0;
        for(v0 = 0; v0 < 8; ++v0) {
            arg5[arg6 + v0] = arg3[arg4 + v0];
        }
    }

    static void copyTo(Match arg1, Match arg2) {
        arg2.len = arg1.len;
        arg2.start = arg1.start;
        arg2.ref = arg1.ref;
    }

    static int encodeSequence(byte[] arg5, int arg6, int arg7, int arg8, int arg9, byte[] arg10, int arg11, int arg12) {
        int v3;
        int v0 = arg7 - arg6;
        int v1 = arg11 + 1;
        if(v1 + v0 + 8 + (v0 >>> 8) <= arg12) {
            int v2 = 15;
            if(v0 >= v2) {
                v3 = -16;
                v1 = LZ4SafeUtils.writeLen(v0 - 15, arg10, v1);
            }
            else {
                v3 = v0 << 4;
            }

            LZ4SafeUtils.wildArraycopy(arg5, arg6, arg10, v1, v0);
            v1 += v0;
            arg7 -= arg8;
            int v5 = v1 + 1;
            arg10[v1] = ((byte)arg7);
            arg6 = v5 + 1;
            arg10[v5] = ((byte)(arg7 >>> 8));
            arg9 += -4;
            if(arg6 + 6 + (arg9 >>> 8) <= arg12) {
                if(arg9 >= v2) {
                    v5 = v3 | 15;
                    arg6 = LZ4SafeUtils.writeLen(arg9 - v2, arg10, arg6);
                }
                else {
                    v5 = v3 | arg9;
                }

                arg10[arg11] = ((byte)v5);
                return arg6;
            }

            throw new LZ4Exception("maxDestLen is too small");
        }

        throw new LZ4Exception("maxDestLen is too small");
    }

    static int hash(byte[] arg0, int arg1) {
        return LZ4Utils.hash(c.e(arg0, arg1));
    }

    static int hash64k(byte[] arg0, int arg1) {
        return LZ4Utils.hash64k(c.e(arg0, arg1));
    }

    static int lastLiterals(byte[] arg3, int arg4, int arg5, byte[] arg6, int arg7, int arg8) {
        int v2 = 15;
        if(arg7 + arg5 + 1 + (arg5 + 255 - v2) / 255 <= arg8) {
            if(arg5 >= v2) {
                arg6[arg7] = -16;
                arg7 = LZ4SafeUtils.writeLen(arg5 - 15, arg6, arg7 + 1);
            }
            else {
                arg6[arg7] = ((byte)(arg5 << 4));
                ++arg7;
            }

            System.arraycopy(arg3, arg4, arg6, arg7, arg5);
            return arg7 + arg5;
        }

        throw new LZ4Exception();
    }

    static boolean readIntEquals(byte[] arg2, int arg3, int arg4) {
        boolean v2 = arg2[arg3] != arg2[arg4] || arg2[arg3 + 1] != arg2[arg4 + 1] || arg2[arg3 + 2] != arg2[arg4 + 2] || arg2[arg3 + 3] != arg2[arg4 + 3] ? false : true;
        return v2;
    }

    static void safeArraycopy(byte[] arg0, int arg1, byte[] arg2, int arg3, int arg4) {
        System.arraycopy(arg0, arg1, arg2, arg3, arg4);
    }

    static void safeIncrementalCopy(byte[] arg3, int arg4, int arg5, int arg6) {
        int v0;
        for(v0 = 0; v0 < arg6; ++v0) {
            arg3[arg5 + v0] = arg3[arg4 + v0];
        }
    }

    public static LZ4SafeUtils valueOf(String arg1) {
        return Enum.valueOf(LZ4SafeUtils.class, arg1);
    }

    public static LZ4SafeUtils[] values() {
        // Method was not decompiled
    }

    static void wildArraycopy(byte[] arg3, int arg4, byte[] arg5, int arg6, int arg7) {
        int v0 = 0;
        while(v0 < arg7) {
            int v1 = arg4 + v0;
            int v2 = arg6 + v0;
            try {
                LZ4SafeUtils.copy8Bytes(arg3, v1, arg5, v2);
                v0 += 8;
                continue;
            }
            catch(ArrayIndexOutOfBoundsException ) {
                StringBuilder v5 = new StringBuilder();
                v5.append("Malformed input at offset ");
                v5.append(arg4);
                throw new LZ4Exception(v5.toString());
            }
        }
    }

    static void wildIncrementalCopy(byte[] arg0, int arg1, int arg2, int arg3) {
        do {
            LZ4SafeUtils.copy8Bytes(arg0, arg1, arg0, arg2);
            arg1 += 8;
            arg2 += 8;
        }
        while(arg2 < arg3);
    }

    static int writeLen(int arg2, byte[] arg3, int arg4) {
        while(arg2 >= 255) {
            arg3[arg4] = -1;
            arg2 += -255;
            ++arg4;
        }

        arg3[arg4] = ((byte)arg2);
        return arg4 + 1;
    }
}

