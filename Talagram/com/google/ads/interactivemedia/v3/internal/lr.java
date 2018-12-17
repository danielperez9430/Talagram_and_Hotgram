package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class lr {
    public class a extends IOException {
        a(int arg3, int arg4) {
            StringBuilder v0 = new StringBuilder(108);
            v0.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
            v0.append(arg3);
            v0.append(" limit ");
            v0.append(arg4);
            v0.append(").");
            super(v0.toString());
        }
    }

    private final ByteBuffer a;

    private lr(ByteBuffer arg2) {
        super();
        this.a = arg2;
        this.a.order(ByteOrder.LITTLE_ENDIAN);
    }

    private lr(byte[] arg1, int arg2, int arg3) {
        this(ByteBuffer.wrap(arg1, arg2, arg3));
    }

    private static int a(CharSequence arg5) {
        int v0 = arg5.length();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg5.charAt(v1) >= 128) {
                break;
            }
        }

        int v2 = v0;
        while(true) {
            if(v1 < v0) {
                int v3 = arg5.charAt(v1);
                if(v3 < 2048) {
                    v2 += 127 - v3 >>> 31;
                    ++v1;
                    continue;
                }
                else {
                    break;
                }
            }

            goto label_20;
        }

        v2 += lr.a(arg5, v1);
    label_20:
        if(v2 >= v0) {
            return v2;
        }

        StringBuilder v3_1 = new StringBuilder(54);
        v3_1.append("UTF-8 length does not fit in int: ");
        v3_1.append((((long)v2)) + 4294967296L);
        throw new IllegalArgumentException(v3_1.toString());
    }

    private static int a(CharSequence arg4, int arg5) {
        int v0 = arg4.length();
        int v1 = 0;
        while(arg5 < v0) {
            int v2 = arg4.charAt(arg5);
            if(v2 < 2048) {
                v1 += 127 - v2 >>> 31;
            }
            else {
                v1 += 2;
                if(55296 <= v2 && v2 <= 57343) {
                    if(Character.codePointAt(arg4, arg5) >= 65536) {
                        ++arg5;
                    }
                    else {
                        StringBuilder v1_1 = new StringBuilder(39);
                        v1_1.append("Unpaired surrogate at index ");
                        v1_1.append(arg5);
                        throw new IllegalArgumentException(v1_1.toString());
                    }
                }
            }

            ++arg5;
        }

        return v1;
    }

    private static int a(CharSequence arg6, byte[] arg7, int arg8, int arg9) {
        char v3_1;
        int v4;
        int v3;
        int v2;
        int v0 = arg6.length();
        arg9 += arg8;
        int v1 = 0;
        while(true) {
            v2 = 128;
            if(v1 < v0) {
                v3 = v1 + arg8;
                if(v3 < arg9) {
                    v4 = arg6.charAt(v1);
                    if(v4 < v2) {
                        arg7[v3] = ((byte)v4);
                        ++v1;
                        continue;
                    }
                }
            }

            break;
        }

        if(v1 == v0) {
            return arg8 + v0;
        }

        arg8 += v1;
        while(true) {
            if(v1 >= v0) {
                return arg8;
            }

            v3_1 = arg6.charAt(v1);
            if(v3_1 >= v2 || arg8 >= arg9) {
                if(v3_1 < 2048 && arg8 <= arg9 - 2) {
                    v4 = arg8 + 1;
                    arg7[arg8] = ((byte)(v3_1 >>> 6 | 960));
                    arg8 = v4 + 1;
                    arg7[v4] = ((byte)(v3_1 & 63 | v2));
                    goto label_96;
                }

                if((v3_1 < 55296 || 57343 < v3_1) && arg8 <= arg9 - 3) {
                    v4 = arg8 + 1;
                    arg7[arg8] = ((byte)(v3_1 >>> 12 | 480));
                    arg8 = v4 + 1;
                    arg7[v4] = ((byte)(v3_1 >>> 6 & 63 | v2));
                    v4 = arg8 + 1;
                    arg7[arg8] = ((byte)(v3_1 & 63 | v2));
                label_24:
                    arg8 = v4;
                    goto label_96;
                }

                if(arg8 > arg9 - 4) {
                    goto label_110;
                }

                v4 = v1 + 1;
                if(v4 != arg6.length()) {
                    char v1_1 = arg6.charAt(v4);
                    if(Character.isSurrogatePair(v3_1, v1_1)) {
                        v1 = Character.toCodePoint(v3_1, v1_1);
                        v3 = arg8 + 1;
                        arg7[arg8] = ((byte)(v1 >>> 18 | 240));
                        arg8 = v3 + 1;
                        arg7[v3] = ((byte)(v1 >>> 12 & 63 | v2));
                        v3 = arg8 + 1;
                        arg7[arg8] = ((byte)(v1 >>> 6 & 63 | v2));
                        arg8 = v3 + 1;
                        arg7[v3] = ((byte)(v1 & 63 | v2));
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
                v4 = arg8 + 1;
                arg7[arg8] = ((byte)v3_1);
                goto label_24;
            }

        label_96:
            ++v1;
        }

        v1 = v4;
    label_99:
        StringBuilder v8 = new StringBuilder(39);
        v8.append("Unpaired surrogate at index ");
        v8.append(v1 - 1);
        throw new IllegalArgumentException(v8.toString());
    label_110:
        StringBuilder v9 = new StringBuilder(37);
        v9.append("Failed writing ");
        v9.append(v3_1);
        v9.append(" at index ");
        v9.append(arg8);
        throw new ArrayIndexOutOfBoundsException(v9.toString());
        return arg8;
    }

    public static lr a(byte[] arg2) {
        return lr.a(arg2, 0, arg2.length);
    }

    public static lr a(byte[] arg1, int arg2, int arg3) {
        return new lr(arg1, arg2, arg3);
    }

    private static void a(CharSequence arg3, ByteBuffer arg4) {
        if(!arg4.isReadOnly()) {
            if(arg4.hasArray()) {
                try {
                    arg4.position(lr.a(arg3, arg4.array(), arg4.arrayOffset() + arg4.position(), arg4.remaining()) - arg4.arrayOffset());
                }
                catch(ArrayIndexOutOfBoundsException v3) {
                    BufferOverflowException v4 = new BufferOverflowException();
                    v4.initCause(((Throwable)v3));
                    throw v4;
                }
            }
            else {
                lr.b(arg3, arg4);
            }

            return;
        }

        throw new ReadOnlyBufferException();
    }

    public int a() {
        return this.a.remaining();
    }

    public void a(byte arg3) {
        if(this.a.hasRemaining()) {
            this.a.put(arg3);
            return;
        }

        throw new a(this.a.position(), this.a.limit());
    }

    public void a(int arg1) {
        this.a(((byte)arg1));
    }

    public void a(int arg1, int arg2) {
        this.b(ls.a(arg1, arg2));
    }

    public void a(int arg2, long arg3) {
        this.a(arg2, 0);
        this.a(arg3);
    }

    public void a(long arg1) {
        this.b(arg1);
    }

    public void a(int arg2, String arg3) {
        this.a(arg2, 2);
        this.a(arg3);
    }

    public void a(String arg5) {
        try {
            int v0 = lr.c(arg5.length());
            if(v0 == lr.c(arg5.length() * 3)) {
                int v1 = this.a.position();
                if(this.a.remaining() >= v0) {
                    this.a.position(v1 + v0);
                    lr.a(((CharSequence)arg5), this.a);
                    int v5_1 = this.a.position();
                    this.a.position(v1);
                    this.b(v5_1 - v1 - v0);
                    this.a.position(v5_1);
                }
                else {
                    throw new a(v1 + v0, this.a.limit());
                }
            }
            else {
                this.b(lr.a(((CharSequence)arg5)));
                lr.a(((CharSequence)arg5), this.a);
            }
        }
        catch(BufferOverflowException v5) {
            a v0_1 = new a(this.a.position(), this.a.limit());
            v0_1.initCause(((Throwable)v5));
            throw v0_1;
        }
    }

    private static void b(CharSequence arg6, ByteBuffer arg7) {
        int v2_1;
        int v4;
        int v0 = arg6.length();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            char v2 = arg6.charAt(v1);
            int v3 = 128;
            if(v2 >= v3) {
                if(v2 < 2048) {
                    v4 = v2 >>> 6 | 960;
                }
                else {
                    if(v2 >= 55296) {
                        if(57343 < v2) {
                        }
                        else {
                            v4 = v1 + 1;
                            if(v4 != arg6.length()) {
                                char v1_1 = arg6.charAt(v4);
                                if(Character.isSurrogatePair(v2, v1_1)) {
                                    v1 = Character.toCodePoint(v2, v1_1);
                                    arg7.put(((byte)(v1 >>> 18 | 240)));
                                    arg7.put(((byte)(v1 >>> 12 & 63 | v3)));
                                    arg7.put(((byte)(v1 >>> 6 & 63 | v3)));
                                    arg7.put(((byte)(v1 & 63 | v3)));
                                    v1 = v4;
                                    goto label_70;
                                }
                                else {
                                    v1 = v4;
                                }
                            }

                            StringBuilder v0_1 = new StringBuilder(39);
                            v0_1.append("Unpaired surrogate at index ");
                            v0_1.append(v1 - 1);
                            throw new IllegalArgumentException(v0_1.toString());
                        }
                    }

                    arg7.put(((byte)(v2 >>> 12 | 480)));
                    v4 = v2 >>> 6 & 63 | v3;
                }

                arg7.put(((byte)v4));
                v2_1 = v2 & 63 | v3;
                goto label_6;
            }
            else {
            label_6:
                arg7.put(((byte)v2_1));
            }

        label_70:
        }
    }

    public void b(int arg2) {
        while((arg2 & -128) != 0) {
            this.a(arg2 & 127 | 128);
            arg2 >>>= 7;
        }

        this.a(arg2);
    }

    public void b(long arg6) {
        while((-128 & arg6) != 0) {
            this.a((((int)arg6)) & 127 | 128);
            arg6 >>>= 7;
        }

        this.a(((int)arg6));
    }

    public static int c(int arg1) {
        if((arg1 & -128) == 0) {
            return 1;
        }

        if((arg1 & -16384) == 0) {
            return 2;
        }

        if((-2097152 & arg1) == 0) {
            return 3;
        }

        if((arg1 & -268435456) == 0) {
            return 4;
        }

        return 5;
    }
}

