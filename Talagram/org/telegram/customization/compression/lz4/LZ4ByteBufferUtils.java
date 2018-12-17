package org.telegram.customization.compression.lz4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.telegram.customization.compression.a.a;

enum LZ4ByteBufferUtils {
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
        LZ4ByteBufferUtils.$VALUES = new LZ4ByteBufferUtils[0];
    }

    private LZ4ByteBufferUtils(String arg1, int arg2) {
        super(arg1, arg2);
    }

    static int commonBytes(ByteBuffer arg6, int arg7, int arg8, int arg9) {
        int v0 = 0;
        while(true) {
            if(arg8 <= arg9 - 8) {
                if(a.d(arg6, arg8) == a.d(arg6, arg7)) {
                    v0 += 8;
                    arg7 += 8;
                    arg8 += 8;
                    continue;
                }
                else {
                    break;
                }
            }

            goto label_25;
        }

        int v6 = arg6.order() == ByteOrder.BIG_ENDIAN ? Long.numberOfLeadingZeros(a.d(arg6, arg7) ^ a.d(arg6, arg8)) : Long.numberOfTrailingZeros(a.d(arg6, arg7) ^ a.d(arg6, arg8));
        return v0 + (v6 >>> 3);
    label_25:
        while(arg8 < arg9) {
            int v1 = arg7 + 1;
            int v2 = arg8 + 1;
            if(a.b(arg6, arg7) != a.b(arg6, arg8)) {
                return v0;
            }

            ++v0;
            arg7 = v1;
            arg8 = v2;
        }

        return v0;
    }

    static int commonBytesBackward(ByteBuffer arg3, int arg4, int arg5, int arg6, int arg7) {
        int v0;
        for(v0 = 0; arg4 > arg6; ++v0) {
            if(arg5 <= arg7) {
                return v0;
            }

            --arg4;
            --arg5;
            if(arg3.get(arg4) != arg3.get(arg5)) {
                return v0;
            }
        }

        return v0;
    }

    static void copyTo(Match arg1, Match arg2) {
        arg2.len = arg1.len;
        arg2.start = arg1.start;
        arg2.ref = arg1.ref;
    }

    static int encodeSequence(ByteBuffer arg5, int arg6, int arg7, int arg8, int arg9, ByteBuffer arg10, int arg11, int arg12) {
        int v3;
        int v0 = arg7 - arg6;
        int v1 = arg11 + 1;
        if(v1 + v0 + 8 + (v0 >>> 8) <= arg12) {
            int v2 = 15;
            if(v0 >= v2) {
                v3 = -16;
                v1 = LZ4ByteBufferUtils.writeLen(v0 - 15, arg10, v1);
            }
            else {
                v3 = v0 << 4;
            }

            LZ4ByteBufferUtils.wildArraycopy(arg5, arg6, arg10, v1, v0);
            v1 += v0;
            arg7 -= arg8;
            int v5 = v1 + 1;
            arg10.put(v1, ((byte)arg7));
            arg6 = v5 + 1;
            arg10.put(v5, ((byte)(arg7 >>> 8)));
            arg9 += -4;
            if(arg6 + 6 + (arg9 >>> 8) <= arg12) {
                if(arg9 >= v2) {
                    v5 = v3 | 15;
                    arg6 = LZ4ByteBufferUtils.writeLen(arg9 - v2, arg10, arg6);
                }
                else {
                    v5 = v3 | arg9;
                }

                arg10.put(arg11, ((byte)v5));
                return arg6;
            }

            throw new LZ4Exception("maxDestLen is too small");
        }

        throw new LZ4Exception("maxDestLen is too small");
    }

    static int hash(ByteBuffer arg0, int arg1) {
        return LZ4Utils.hash(a.c(arg0, arg1));
    }

    static int hash64k(ByteBuffer arg0, int arg1) {
        return LZ4Utils.hash64k(a.c(arg0, arg1));
    }

    static int lastLiterals(ByteBuffer arg3, int arg4, int arg5, ByteBuffer arg6, int arg7, int arg8) {
        int v2 = 15;
        if(arg7 + arg5 + 1 + (arg5 + 255 - v2) / 255 <= arg8) {
            if(arg5 >= v2) {
                arg6.put(arg7, -16);
                arg7 = LZ4ByteBufferUtils.writeLen(arg5 - 15, arg6, arg7 + 1);
            }
            else {
                arg6.put(arg7, ((byte)(arg5 << 4)));
                ++arg7;
            }

            LZ4ByteBufferUtils.safeArraycopy(arg3, arg4, arg6, arg7, arg5);
            return arg7 + arg5;
        }

        throw new LZ4Exception();
    }

    static boolean readIntEquals(ByteBuffer arg0, int arg1, int arg2) {
        boolean v0 = arg0.getInt(arg1) == arg0.getInt(arg2) ? true : false;
        return v0;
    }

    static void safeArraycopy(ByteBuffer arg3, int arg4, ByteBuffer arg5, int arg6, int arg7) {
        int v0;
        for(v0 = 0; v0 < arg7; ++v0) {
            arg5.put(arg6 + v0, arg3.get(arg4 + v0));
        }
    }

    static void safeIncrementalCopy(ByteBuffer arg3, int arg4, int arg5, int arg6) {
        int v0;
        for(v0 = 0; v0 < arg6; ++v0) {
            arg3.put(arg5 + v0, arg3.get(arg4 + v0));
        }
    }

    public static LZ4ByteBufferUtils valueOf(String arg1) {
        return Enum.valueOf(LZ4ByteBufferUtils.class, arg1);
    }

    public static LZ4ByteBufferUtils[] values() {
        // Method was not decompiled
    }

    static void wildArraycopy(ByteBuffer arg4, int arg5, ByteBuffer arg6, int arg7, int arg8) {
        int v0 = 0;
        while(v0 < arg8) {
            int v1 = arg7 + v0;
            int v2 = arg5 + v0;
            try {
                arg6.putLong(v1, arg4.getLong(v2));
                v0 += 8;
                continue;
            }
            catch(IndexOutOfBoundsException ) {
                StringBuilder v6 = new StringBuilder();
                v6.append("Malformed input at offset ");
                v6.append(arg5);
                throw new LZ4Exception(v6.toString());
            }
        }
    }

    static void wildIncrementalCopy(ByteBuffer arg6, int arg7, int arg8, int arg9) {
        int v0 = arg8 - arg7;
        int v1 = 8;
        int v2 = 4;
        if(v0 < v2) {
            v0 = 0;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                a.c(arg6, arg8 + v3, a.b(arg6, arg7 + v3));
            }

            arg8 += 4;
            arg7 += 4;
            switch(arg8 - arg7) {
                case 1: {
                    arg7 += -3;
                    break;
                }
                case 2: {
                    arg7 += -2;
                    break;
                }
                case 3: {
                    arg7 += -3;
                    v0 = -1;
                    break;
                }
                case 5: {
                    v0 = 1;
                    break;
                }
                case 6: {
                    v0 = 2;
                    break;
                }
                case 7: {
                    v0 = 3;
                    break;
                }
                default: {
                    break;
                }
            }

            a.b(arg6, arg8, a.c(arg6, arg7));
            arg8 += v2;
            arg7 -= v0;
        }
        else if(v0 < v1) {
            a.a(arg6, arg8, a.d(arg6, arg7));
            arg8 += v0;
        }

        while(arg8 < arg9) {
            a.a(arg6, arg8, a.d(arg6, arg7));
            arg8 += 8;
            arg7 += v1;
        }
    }

    static int writeLen(int arg2, ByteBuffer arg3, int arg4) {
        while(arg2 >= 255) {
            arg3.put(arg4, -1);
            arg2 += -255;
            ++arg4;
        }

        arg3.put(arg4, ((byte)arg2));
        return arg4 + 1;
    }
}

