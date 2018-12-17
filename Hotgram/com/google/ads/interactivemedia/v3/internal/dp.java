package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import java.util.Arrays;

final class dp {
    public final class a {
        public final int a;
        public final int b;
        public final long[] c;
        public final int d;
        public final boolean e;

        public a(int arg1, int arg2, long[] arg3, int arg4, boolean arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
        }
    }

    public final class b {
        public final String a;
        public final String[] b;
        public final int c;

        public b(String arg1, String[] arg2, int arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }
    }

    public final class c {
        public final boolean a;
        public final int b;
        public final int c;
        public final int d;

        public c(boolean arg1, int arg2, int arg3, int arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
        }
    }

    public final class d {
        public final long a;
        public final int b;
        public final long c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final boolean i;
        public final byte[] j;

        public d(long arg1, int arg3, long arg4, int arg6, int arg7, int arg8, int arg9, int arg10, boolean arg11, byte[] arg12) {
            super();
            this.a = arg1;
            this.b = arg3;
            this.c = arg4;
            this.d = arg6;
            this.e = arg7;
            this.f = arg8;
            this.g = arg9;
            this.h = arg10;
            this.i = arg11;
            this.j = arg12;
        }
    }

    public static int a(int arg1) {
        int v0 = 0;
        while(arg1 > 0) {
            ++v0;
            arg1 >>>= 1;
        }

        return v0;
    }

    private static long a(long arg2, long arg4) {
        double v4 = ((double)arg4);
        Double.isNaN(v4);
        return ((long)Math.floor(Math.pow(((double)arg2), 1 / v4)));
    }

    public static d a(fp arg16) {
        fp v0 = arg16;
        dp.a(1, v0, false);
        long v4 = arg16.l();
        int v6 = arg16.f();
        long v7 = arg16.l();
        int v9 = arg16.n();
        int v10 = arg16.n();
        int v11 = arg16.n();
        int v3 = arg16.f();
        int v12 = ((int)Math.pow(2, ((double)(v3 & 15))));
        int v13 = ((int)Math.pow(2, ((double)((v3 & 240) >> 4))));
        boolean v14 = (arg16.f() & 1) > 0 ? true : false;
        return new d(v4, v6, v7, v9, v10, v11, v12, v13, v14, Arrays.copyOf(v0.a, arg16.c()));
    }

    public static boolean a(int arg2, fp arg3, boolean arg4) {
        if(arg3.f() != arg2) {
            if(arg4) {
                return 0;
            }

            String v4 = "expected header type ";
            String v2 = String.valueOf(Integer.toHexString(arg2));
            v2 = v2.length() != 0 ? v4.concat(v2) : new String(v4);
            throw new bl(v2);
        }

        if(arg3.f() == 118 && arg3.f() == 111 && arg3.f() == 114 && arg3.f() == 98 && arg3.f() == 105) {
            if(arg3.f() != 115) {
            }
            else {
                return 1;
            }
        }

        if(arg4) {
            return 0;
        }

        throw new bl("expected characters \'vorbis\'");
    }

    private static void a(int arg11, dn arg12) {
        int v0 = arg12.a(6) + 1;
        int v3;
        for(v3 = 0; true; ++v3) {
            if(v3 >= v0) {
                return;
            }

            int v4 = arg12.a(16);
            if(v4 != 0) {
                StringBuilder v7 = new StringBuilder(52);
                v7.append("mapping type other than 0 not supported: ");
                v7.append(v4);
                Log.e("VorbisUtil", v7.toString());
            }
            else {
                int v5 = 4;
                v4 = arg12.a() ? arg12.a(v5) + 1 : 1;
                int v7_1 = 8;
                if(arg12.a()) {
                    int v6 = arg12.a(v7_1) + 1;
                    int v8;
                    for(v8 = 0; v8 < v6; ++v8) {
                        int v9 = arg11 - 1;
                        arg12.b(dp.a(v9));
                        arg12.b(dp.a(v9));
                    }
                }

                if(arg12.a(2) != 0) {
                    break;
                }

                if(v4 > 1) {
                    for(v6 = 0; v6 < arg11; ++v6) {
                        arg12.b(v5);
                    }
                }

                for(v5 = 0; v5 < v4; ++v5) {
                    arg12.b(v7_1);
                    arg12.b(v7_1);
                    arg12.b(v7_1);
                }
            }
        }

        throw new bl("to reserved bits must be zero after mapping coupling steps");
    }

    private static c[] a(dn arg8) {
        int v0 = arg8.a(6) + 1;
        c[] v1 = new c[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = new c(arg8.a(), arg8.a(16), arg8.a(16), arg8.a(8));
        }

        return v1;
    }

    public static c[] a(fp arg4, int arg5) {
        int v0 = 0;
        dp.a(5, arg4, false);
        int v1 = arg4.f() + 1;
        dn v2 = new dn(arg4.a);
        v2.b(arg4.d() * 8);
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            dp.d(v2);
        }

        v4 = v2.a(6) + 1;
        while(true) {
            if(v0 >= v4) {
                goto label_29;
            }

            if(v2.a(16) != 0) {
                break;
            }

            ++v0;
        }

        throw new bl("placeholder of time domain transforms not zeroed out");
    label_29:
        dp.c(v2);
        dp.b(v2);
        dp.a(arg5, v2);
        c[] v4_1 = dp.a(v2);
        if(v2.a()) {
            return v4_1;
        }

        throw new bl("framing bit after modes not set as expected");
    }

    private static void b(dn arg12) {
        int v0 = 6;
        int v1 = arg12.a(v0) + 1;
        int v4;
        for(v4 = 0; true; ++v4) {
            if(v4 >= v1) {
                return;
            }

            if(arg12.a(16) > 2) {
                break;
            }

            arg12.b(24);
            arg12.b(24);
            arg12.b(24);
            int v5 = arg12.a(v0) + 1;
            int v6 = 8;
            arg12.b(v6);
            int[] v7 = new int[v5];
            int v8;
            for(v8 = 0; v8 < v5; ++v8) {
                int v9 = arg12.a(3);
                int v10 = arg12.a() ? arg12.a(5) : 0;
                v7[v8] = v10 * 8 + v9;
            }

            for(v8 = 0; v8 < v5; ++v8) {
                for(v9 = 0; v9 < v6; ++v9) {
                    if((v7[v8] & 1 << v9) != 0) {
                        arg12.b(v6);
                    }
                }
            }
        }

        throw new bl("residueType greater than 2 is not decodable");
    }

    public static b b(fp arg9) {
        int v0 = 0;
        dp.a(3, arg9, false);
        String v1 = arg9.e(((int)arg9.l()));
        int v3 = 11 + v1.length();
        long v4 = arg9.l();
        String[] v2 = new String[((int)v4)];
        v3 += 4;
        while((((long)v0)) < v4) {
            v2[v0] = arg9.e(((int)arg9.l()));
            v3 = v3 + 4 + v2[v0].length();
            ++v0;
        }

        if((arg9.f() & 1) != 0) {
            return new b(v1, v2, v3 + 1);
        }

        throw new bl("framing bit expected to be set");
    }

    private static void c(dn arg14) {
        int v11;
        int v12;
        int v0 = 6;
        int v1 = arg14.a(v0) + 1;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            int v5 = 16;
            int v6 = arg14.a(v5);
            int v7 = 4;
            int v8 = 8;
            switch(v6) {
                case 0: {
                    goto label_72;
                }
                case 1: {
                    goto label_22;
                }
            }

            StringBuilder v1_1 = new StringBuilder(52);
            v1_1.append("floor type greater than 1 not decodable: ");
            v1_1.append(v6);
            throw new bl(v1_1.toString());
        label_22:
            v5 = arg14.a(5);
            int[] v9 = new int[v5];
            v6 = 0;
            int v10 = -1;
            while(v6 < v5) {
                v9[v6] = arg14.a(v7);
                if(v9[v6] > v10) {
                    v10 = v9[v6];
                }

                ++v6;
            }

            int[] v6_1 = new int[v10 + 1];
            for(v10 = 0; true; ++v10) {
                v12 = 2;
                if(v10 >= v6_1.length) {
                    break;
                }

                v6_1[v10] = arg14.a(3) + 1;
                v11 = arg14.a(v12);
                if(v11 > 0) {
                    arg14.b(v8);
                }

                for(v12 = 0; v12 < 1 << v11; ++v12) {
                    arg14.b(v8);
                }
            }

            arg14.b(v12);
            v7 = arg14.a(v7);
            v8 = 0;
            v10 = 0;
            v11 = 0;
            while(true) {
                if(v8 >= v5) {
                    goto label_84;
                }

                v10 += v6_1[v9[v8]];
                while(v11 < v10) {
                    arg14.b(v7);
                    ++v11;
                }

                ++v8;
            }

        label_72:
            arg14.b(v8);
            arg14.b(v5);
            arg14.b(v5);
            arg14.b(v0);
            arg14.b(v8);
            v5 = arg14.a(v7) + 1;
            for(v6 = 0; v6 < v5; ++v6) {
                arg14.b(v8);
            }

        label_84:
        }
    }

    private static a d(dn arg14) {
        int v9_1;
        int v0 = 24;
        if(arg14.a(v0) == 5653314) {
            int v3 = arg14.a(16);
            int v4 = arg14.a(v0);
            long[] v5 = new long[v4];
            boolean v7 = arg14.a();
            long v0_1 = 0;
            int v2 = 5;
            int v6 = 0;
            if(!v7) {
                boolean v9 = arg14.a();
                while(v6 < v5.length) {
                    if(!v9) {
                        v5[v6] = ((long)(arg14.a(v2) + 1));
                    }
                    else if(arg14.a()) {
                        v5[v6] = ((long)(arg14.a(v2) + 1));
                    }
                    else {
                        v5[v6] = v0_1;
                    }

                    ++v6;
                }
            }
            else {
                v9_1 = arg14.a(v2) + 1;
                for(v2 = 0; v2 < v5.length; v2 = v11) {
                    int v10 = arg14.a(dp.a(v4 - v2));
                    int v11 = v2;
                    for(v2 = 0; v2 < v10; ++v2) {
                        if(v11 >= v5.length) {
                            break;
                        }

                        v5[v11] = ((long)v9_1);
                        ++v11;
                    }

                    ++v9_1;
                }
            }

            v2 = 4;
            v6 = arg14.a(v2);
            v9_1 = 2;
            if(v6 <= v9_1) {
                if(v6 == 1 || v6 == v9_1) {
                    arg14.b(32);
                    arg14.b(32);
                    v2 = arg14.a(v2) + 1;
                    arg14.b(1);
                    if(v6 != 1) {
                        v0_1 = ((long)(v4 * v3));
                    }
                    else if(v3 != 0) {
                        v0_1 = dp.a(((long)v4), ((long)v3));
                    }

                    arg14.b(((int)(v0_1 * (((long)v2)))));
                }

                return new a(v3, v4, v5, v6, v7);
            }

            StringBuilder v1 = new StringBuilder(53);
            v1.append("lookup type greater than 2 not decodable: ");
            v1.append(v6);
            throw new bl(v1.toString());
        }

        int v14 = arg14.b();
        StringBuilder v2_1 = new StringBuilder(66);
        v2_1.append("expected code book to start with [0x56, 0x43, 0x42] at ");
        v2_1.append(v14);
        throw new bl(v2_1.toString());
    }
}

