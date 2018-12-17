package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class fn {
    public final class a {
        public final int a;
        public final int b;
        public final boolean c;

        public a(int arg1, int arg2, boolean arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }
    }

    public final class b {
        public final int a;
        public final int b;
        public final int c;
        public final float d;
        public final boolean e;
        public final boolean f;
        public final int g;
        public final int h;
        public final int i;
        public final boolean j;

        public b(int arg1, int arg2, int arg3, float arg4, boolean arg5, boolean arg6, int arg7, int arg8, int arg9, boolean arg10) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
            this.f = arg6;
            this.g = arg7;
            this.h = arg8;
            this.i = arg9;
            this.j = arg10;
        }
    }

    public static final byte[] a;
    public static final float[] b;
    private static final Object c;
    private static int[] d;

    static {
        fn.a = new byte[]{0, 0, 0, 1};
        fn.b = new float[]{1f, 1f, 1.090909f, 0.909091f, 1.454545f, 1.212121f, 2.181818f, 1.818182f, 2.909091f, 2.424242f, 1.636364f, 1.363636f, 1.939394f, 1.616162f, 1.333333f, 1.5f, 2f};
        fn.c = new Object();
        fn.d = new int[10];
    }

    public static byte[] a(fp arg2) {
        int v0 = arg2.g();
        int v1 = arg2.d();
        arg2.d(v0);
        return ff.a(arg2.a, v1, v0);
    }

    public static b a(fo arg19) {
        float v8_2;
        int v3;
        boolean v14;
        int v13;
        int v15;
        boolean v9;
        int v11;
        boolean v8;
        fo v0 = arg19;
        int v1 = 8;
        int v2 = v0.c(v1);
        v0.b(16);
        int v5 = arg19.d();
        int v4 = 3;
        int v7 = 1;
        if(v2 == 100 || v2 == 110 || v2 == 122 || v2 == 244 || v2 == 44 || v2 == 83 || v2 == 86 || v2 == 118 || v2 == 128 || v2 == 138) {
            v2 = arg19.d();
            v8 = v2 == v4 ? arg19.b() : false;
            arg19.d();
            arg19.d();
            v0.b(1);
            if(arg19.b()) {
                int v9_1 = v2 != v4 ? 8 : 12;
                int v10;
                for(v10 = 0; v10 < v9_1; ++v10) {
                    if(arg19.b()) {
                        v11 = v10 < 6 ? 16 : 64;
                        fn.a(v0, v11);
                    }
                }
            }

            v9 = v8;
        }
        else {
            v2 = 1;
            v9 = false;
        }

        v11 = arg19.d() + 4;
        int v12 = arg19.d();
        if(v12 == 0) {
            v15 = v5;
            v13 = arg19.d() + 4;
            goto label_68;
        }
        else if(v12 == 1) {
            v8 = arg19.b();
            arg19.e();
            arg19.e();
            long v13_1 = ((long)arg19.d());
            v15 = v5;
            for(v10 = 0; (((long)v10)) < v13_1; ++v10) {
                arg19.d();
            }

            v14 = v8;
            v13 = 0;
        }
        else {
            v15 = v5;
            v13 = 0;
        label_68:
            v14 = false;
        }

        arg19.d();
        v0.b(1);
        v4 = arg19.d() + 1;
        v5 = arg19.d() + 1;
        boolean v10_1 = arg19.b();
        int v6 = 2;
        int v8_1 = (2 - (((int)v10_1))) * v5;
        if(!v10_1) {
            v0.b(1);
        }

        v0.b(1);
        v4 *= 16;
        v8_1 *= 16;
        if(arg19.b()) {
            v5 = arg19.d();
            int v16 = arg19.d();
            int v17 = arg19.d();
            int v18 = arg19.d();
            if(v2 == 0) {
                v6 -= ((int)v10_1);
                v3 = 1;
            }
            else {
                v3 = v2 == 3 ? 1 : 2;
                if(v2 == 1) {
                    v7 = 2;
                }

                v6 = (v6 - (((int)v10_1))) * v7;
            }

            v4 -= (v5 + v16) * v3;
            v8_1 -= (v17 + v18) * v6;
        }

        v6 = v4;
        v7 = v8_1;
        float v2_1 = 1f;
        if(!arg19.b() || !arg19.b()) {
        label_165:
            v8_2 = 1f;
        }
        else {
            v1 = v0.c(v1);
            if(v1 == 255) {
                v1 = v0.c(16);
                int v0_1 = v0.c(16);
                if(v1 != 0 && v0_1 != 0) {
                    v2_1 = (((float)v1)) / (((float)v0_1));
                }

                v8_2 = v2_1;
            }
            else {
                if(v1 < fn.b.length) {
                    v8_2 = fn.b[v1];
                    goto label_166;
                }

                StringBuilder v4_1 = new StringBuilder(46);
                v4_1.append("Unexpected aspect_ratio_idc value: ");
                v4_1.append(v1);
                Log.w("NalUnitUtil", v4_1.toString());
                goto label_165;
            }
        }

    label_166:
        return new b(v15, v6, v7, v8_2, v9, v10_1, v11, v12, v13, v14);
    }

    public static int a(byte[] arg8, int arg9) {
        Object v0 = fn.c;
        __monitor_enter(v0);
        int v2 = 0;
        int v3 = 0;
        while(true) {
            if(v2 >= arg9) {
                goto label_25;
            }

            try {
                v2 = fn.a(arg8, v2, arg9);
                if(v2 >= arg9) {
                    continue;
                }

                if(fn.d.length <= v3) {
                    fn.d = Arrays.copyOf(fn.d, fn.d.length * 2);
                }

                fn.d[v3] = v2;
                v2 += 3;
                ++v3;
                continue;
            label_25:
                arg9 -= v3;
                v2 = 0;
                int v4 = 0;
                int v5 = 0;
                while(v2 < v3) {
                    int v6 = fn.d[v2] - v5;
                    System.arraycopy(arg8, v5, arg8, v4, v6);
                    v4 += v6;
                    int v7 = v4 + 1;
                    arg8[v4] = 0;
                    v4 = v7 + 1;
                    arg8[v7] = 0;
                    v5 += v6 + 3;
                    ++v2;
                }

                System.arraycopy(arg8, v5, arg8, v4, arg9 - v4);
                __monitor_exit(v0);
                return arg9;
            label_47:
                __monitor_exit(v0);
                break;
            }
            catch(Throwable v8) {
                goto label_47;
            }
        }

        throw v8;
    }

    private static int a(byte[] arg2, int arg3, int arg4) {
        while(arg3 < arg4 - 2) {
            if(arg2[arg3] == 0 && arg2[arg3 + 1] == 0 && arg2[arg3 + 2] == 3) {
                return arg3;
            }

            ++arg3;
        }

        return arg4;
    }

    public static int a(byte[] arg7, int arg8, int arg9, boolean[] arg10) {
        boolean v8;
        int v0 = arg9 - arg8;
        boolean v1 = false;
        boolean v3 = v0 >= 0 ? true : false;
        fe.b(v3);
        if(v0 == 0) {
            return arg9;
        }

        int v3_1 = 2;
        if(arg10 != null) {
            if(arg10[0]) {
                fn.a(arg10);
                return arg8 - 3;
            }
            else {
                if(v0 > 1 && (arg10[1]) && arg7[arg8] == 1) {
                    fn.a(arg10);
                    return arg8 - v3_1;
                }

                if(v0 <= v3_1) {
                    goto label_36;
                }

                if(!arg10[v3_1]) {
                    goto label_36;
                }

                if(arg7[arg8] != 0) {
                    goto label_36;
                }

                if(arg7[arg8 + 1] != 1) {
                    goto label_36;
                }

                fn.a(arg10);
                return arg8 - 1;
            }
        }

    label_36:
        int v4 = arg9 - 1;
        arg8 += v3_1;
        while(arg8 < v4) {
            if((arg7[arg8] & 254) != 0) {
            }
            else {
                int v5 = arg8 - 2;
                if(arg7[v5] == 0 && arg7[arg8 - 1] == 0 && arg7[arg8] == 1) {
                    if(arg10 != null) {
                        fn.a(arg10);
                    }

                    return v5;
                }

                arg8 += -2;
            }

            arg8 += 3;
        }

        if(arg10 != null) {
            if(v0 > v3_1) {
                if(arg7[arg9 - 3] != 0) {
                    goto label_69;
                }
                else if(arg7[arg9 - 2] == 0) {
                    if(arg7[v4] != 1) {
                        goto label_69;
                    }

                    goto label_67;
                }
                else {
                    goto label_69;
                }
            }
            else if(v0 == v3_1) {
                if(!arg10[v3_1]) {
                    goto label_69;
                }
                else if(arg7[arg9 - 2] != 0) {
                    goto label_69;
                }
                else if(arg7[v4] == 1) {
                    goto label_67;
                }
                else {
                    goto label_69;
                }
            }
            else if(!arg10[1]) {
            label_69:
                v8 = false;
            }
            else if(arg7[v4] == 1) {
            label_67:
                v8 = true;
            }
            else {
                goto label_69;
            }

            arg10[0] = v8;
            if(v0 > 1) {
                if(arg7[arg9 - 2] == 0) {
                    if(arg7[v4] != 0) {
                        goto label_94;
                    }

                    goto label_92;
                }
                else {
                    goto label_94;
                }
            }
            else if(!arg10[v3_1]) {
            label_94:
                v8 = false;
            }
            else if(arg7[v4] == 0) {
            label_92:
                v8 = true;
            }
            else {
                goto label_94;
            }

            arg10[1] = v8;
            if(arg7[v4] == 0) {
                v1 = true;
            }

            arg10[v3_1] = v1;
        }

        return arg9;
    }

    public static void a(boolean[] arg2) {
        arg2[0] = false;
        arg2[1] = false;
        arg2[2] = false;
    }

    private static void a(fo arg3, int arg4) {
        int v0 = 8;
        int v1 = 0;
        int v2 = 8;
        while(v1 < arg4) {
            if(v0 != 0) {
                v0 = (arg3.e() + v2 + 256) % 256;
            }

            if(v0 == 0) {
            }
            else {
                v2 = v0;
            }

            ++v1;
        }
    }

    public static void a(ByteBuffer arg9) {
        int v0 = arg9.position();
        int v2 = 0;
        int v3 = 0;
        while(true) {
            int v4 = v2 + 1;
            if(v4 >= v0) {
                break;
            }

            int v5 = arg9.get(v2) & 255;
            int v6 = 3;
            if(v3 == v6) {
                if(v5 == 1 && (arg9.get(v4) & 31) == 7) {
                    ByteBuffer v3_1 = arg9.duplicate();
                    v3_1.position(v2 - v6);
                    v3_1.limit(v0);
                    arg9.position(0);
                    arg9.put(v3_1);
                    return;
                }
            }
            else if(v5 == 0) {
                ++v3;
            }

            if(v5 != 0) {
                v3 = 0;
            }

            v2 = v4;
        }

        arg9.clear();
    }

    public static int b(byte[] arg0, int arg1) {
        return arg0[arg1 + 3] & 31;
    }

    public static a b(fo arg3) {
        int v0 = arg3.d();
        int v1 = arg3.d();
        arg3.b(1);
        return new a(v0, v1, arg3.b());
    }

    public static int c(byte[] arg0, int arg1) {
        return (arg0[arg1 + 3] & 126) >> 1;
    }
}

