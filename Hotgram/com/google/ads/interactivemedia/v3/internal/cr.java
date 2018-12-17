package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import java.nio.charset.Charset;

final class cr {
    private static final int a;
    private static final Charset[] b;

    static {
        cr.a = ft.c("ID3");
        cr.b = new Charset[]{Charset.forName("ISO-8859-1"), Charset.forName("UTF-16LE"), Charset.forName("UTF-16BE"), Charset.forName("UTF-8")};
    }

    private static Pair a(int arg9, fp arg10) {
        int v5;
        Pair v4;
        while(true) {
            int v1 = 3;
            int v3 = 2;
            v4 = null;
            if(arg9 != v3) {
                if(arg10.b() < 10) {
                    return v4;
                }

                int v6 = 4;
                String v5_1 = arg10.a(v6, Charset.forName("US-ASCII"));
                if(v5_1.equals("\u0000\u0000\u0000\u0000")) {
                    return v4;
                }

                int v7 = arg9 == v6 ? arg10.r() : arg10.s();
                if(v7 != 0) {
                    if(v7 > arg10.b() - v3) {
                    }
                    else {
                        int v8 = arg10.g();
                        if(arg9 != v6 || (v8 & 12) == 0) {
                            if(arg9 == v1 && (v8 & 192) != 0) {
                            label_55:
                                v1 = 1;
                                goto label_58;
                            }

                            v1 = 0;
                        }
                        else {
                            goto label_55;
                        }

                    label_58:
                        if(v1 == 0 && (v5_1.equals("COMM"))) {
                            v5 = v7;
                        label_63:
                            arg9 = arg10.f();
                            if(arg9 >= 0) {
                                if(arg9 >= cr.b.length) {
                                }
                                else {
                                    String[] v9 = arg10.a(v5 - 1, cr.b[arg9]).split("\u0000");
                                    if(v9.length == v3) {
                                        v4 = Pair.create(v9[0], v9[1]);
                                    }
                                }
                            }

                            return v4;
                        }

                        v5 = v7;
                        goto label_82;
                    }
                }

                return v4;
            }
            else if(arg10.b() < 6) {
                return v4;
            }
            else {
                String v1_1 = arg10.a(v1, Charset.forName("US-ASCII"));
                if(v1_1.equals("\u0000\u0000\u0000")) {
                    return v4;
                }
                else {
                    v5 = arg10.j();
                    if(v5 != 0) {
                        if(v5 > arg10.b()) {
                        }
                        else if(v1_1.equals("COM")) {
                            goto label_63;
                        }
                        else {
                            goto label_82;
                        }
                    }

                    return v4;
                }
            }

        label_82:
            arg10.d(v5);
        }

        return v4;
    }

    public static cg a(cd arg9) {
        int v1 = 10;
        fp v0 = new fp(v1);
        cg v3 = null;
        int v4;
        for(v4 = 0; true; v4 += v8 + 10) {
            arg9.c(v0.a, 0, v1);
            v0.c(0);
            if(v0.j() != cr.a) {
                break;
            }

            int v5 = v0.f();
            int v6 = v0.f();
            int v7 = v0.f();
            int v8 = v0.r();
            if(v3 != null || !cr.a(v5, v6, v7, v8)) {
                arg9.c(v8);
            }
            else {
                byte[] v3_1 = new byte[v8];
                arg9.c(v3_1, 0, v8);
                v3 = cr.a(new fp(v3_1), v5, v7);
            }
        }

        arg9.a();
        arg9.c(v4);
        return v3;
    }

    private static boolean a(int arg2, int arg3, int arg4, int arg5) {
        boolean v2;
        if(arg3 != 255) {
            arg3 = 2;
            if(arg2 >= arg3) {
                int v0 = 4;
                if(arg2 > v0) {
                    goto label_22;
                }
                else if(arg5 <= 3145728) {
                    if(arg2 == arg3) {
                        if((arg4 & 63) != 0) {
                        }
                        else if((arg4 & 64) == 0) {
                            goto label_13;
                        }

                        goto label_22;
                    }

                label_13:
                    if(arg2 == 3 && (arg4 & 31) != 0) {
                        goto label_22;
                    }

                    if(arg2 == v0 && (arg4 & 15) != 0) {
                        goto label_22;
                    }

                    v2 = true;
                }
                else {
                    goto label_22;
                }
            }
            else {
                goto label_22;
            }
        }
        else {
        label_22:
            v2 = false;
        }

        return v2;
    }

    private static cg a(fp arg5, int arg6, int arg7) {
        cg v7_1;
        cr.b(arg5, arg6, arg7);
        arg5.c(0);
        int v0 = 6;
        int v1 = 3;
        int v2 = 4;
        cg v3 = null;
        if(arg6 != v1 || (arg7 & 64) == 0) {
            if(arg6 != v2) {
                goto label_45;
            }

            if((arg7 & 64) == 0) {
                goto label_45;
            }

            if(arg5.b() < v2) {
                return v3;
            }

            arg7 = arg5.r();
            if(arg7 >= v0) {
                if(arg7 > arg5.b() + v2) {
                }
                else {
                    arg5.c(arg7);
                    goto label_45;
                }
            }

            return v3;
        }
        else if(arg5.b() < v2) {
            return v3;
        }
        else {
            arg7 = arg5.s();
            if(arg7 > arg5.b()) {
                return v3;
            }
            else {
                if(arg7 >= v0) {
                    arg5.d(2);
                    v0 = arg5.s();
                    arg5.c(v2);
                    arg5.b(arg5.c() - v0);
                    if(arg5.b() < arg7) {
                        return v3;
                    }
                }

                arg5.d(arg7);
            }
        }

        do {
        label_45:
            Pair v7 = cr.a(arg6, arg5);
            if(v7 == null) {
                return v3;
            }

            if(v7.first.length() <= v1) {
                goto label_45;
            }

            v7_1 = cg.a(v7.first.substring(v1), v7.second);
        }
        while(v7_1 == null);

        return v7_1;
    }

    private static void a(byte[] arg2, int arg3, int arg4) {
        arg2[arg3] = ((byte)(arg4 >> 21 & 127));
        arg2[arg3 + 1] = ((byte)(arg4 >> 14 & 127));
        arg2[arg3 + 2] = ((byte)(arg4 >> 7 & 127));
        arg2[arg3 + 3] = ((byte)(arg4 & 127));
    }

    private static boolean a(fp arg11, boolean arg12) {
        arg11.c(0);
        while(arg11.b() >= 10) {
            if(arg11.m() == 0) {
                return 1;
            }

            long v1 = arg11.k();
            if(!arg12) {
                if((8421504 & v1) != 0) {
                    return 0;
                }
                else {
                    v1 = (v1 >> 24 & 127) << 21 | (v1 & 127 | (v1 >> 8 & 127) << 7 | (v1 >> 16 & 127) << 14);
                }
            }

            if(v1 > (((long)(arg11.b() - 2)))) {
                return 0;
            }

            if((1 & arg11.g()) != 0 && arg11.b() < 4) {
                return 0;
            }

            arg11.d(((int)v1));
        }

        return 1;
    }

    private static boolean b(fp arg5, int arg6, int arg7) {
        int v1 = 0;
        if(arg6 == 4) {
            if(cr.a(arg5, false)) {
                cr.b(arg5, false);
                return 1;
            }

            if(!cr.a(arg5, true)) {
                return 0;
            }

            cr.b(arg5, true);
        }
        else if((arg7 & 128) != 0) {
            byte[] v6 = arg5.a;
            arg7 = v6.length;
            while(true) {
                int v2 = v1 + 1;
                if(v2 >= arg7) {
                    break;
                }

                if((v6[v1] & 255) == 255 && v6[v2] == 0) {
                    System.arraycopy(v6, v1 + 2, v6, v2, arg7 - v1 - 2);
                    --arg7;
                }

                v1 = v2;
            }

            arg5.b(arg7);
        }

        return 1;
    }

    private static void b(fp arg10, boolean arg11) {
        int v4;
        arg10.c(0);
        byte[] v1 = arg10.a;
        while(arg10.b() >= 10) {
            if(arg10.m() == 0) {
                return;
            }

            int v2 = arg11 ? arg10.s() : arg10.r();
            int v3 = arg10.g();
            if((v3 & 1) != 0) {
                v4 = arg10.d();
                System.arraycopy(v1, v4 + 4, v1, v4, arg10.b() - 4);
                v2 += -4;
                v4 = v3 & -2;
                arg10.b(arg10.c() - 4);
            }
            else {
                v4 = v3;
            }

            if((v4 & 2) != 0) {
                int v6 = arg10.d() + 1;
                int v7 = v6;
                int v5 = v2;
                v2 = 0;
                while(true) {
                    ++v2;
                    if(v2 >= v5) {
                        break;
                    }

                    if((v1[v6 - 1] & 255) == 255 && v1[v6] == 0) {
                        ++v6;
                        --v5;
                    }

                    v1[v7] = v1[v6];
                    ++v7;
                    ++v6;
                }

                arg10.b(arg10.c() - (v6 - v7));
                System.arraycopy(v1, v6, v1, v7, arg10.b() - v6);
                v4 &= -3;
                v2 = v5;
            }

            if(v4 != v3 || (arg11)) {
                v3 = arg10.d() - 6;
                cr.a(v1, v3, v2);
                v1[v3 + 4] = ((byte)(v4 >> 8));
                v1[v3 + 5] = ((byte)(v4 & 255));
            }

            arg10.d(v2);
        }
    }
}

