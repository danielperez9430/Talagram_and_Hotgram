package net.hockeyapp.android.e;

import java.io.UnsupportedEncodingException;

public class b {
    abstract class a {
        public byte[] a;
        public int b;

        a() {
            super();
        }
    }

    class net.hockeyapp.android.e.b$b extends a {
        int c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        private static final byte[] g;
        private static final byte[] h;
        private final byte[] i;
        private int j;
        private final byte[] k;

        static {
            net.hockeyapp.android.e.b$b.g = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
            net.hockeyapp.android.e.b$b.h = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        }

        public net.hockeyapp.android.e.b$b(int arg3, byte[] arg4) {
            super();
            this.a = arg4;
            boolean v1 = true;
            boolean v4 = (arg3 & 1) == 0 ? true : false;
            this.d = v4;
            v4 = (arg3 & 2) == 0 ? true : false;
            this.e = v4;
            if((arg3 & 4) != 0) {
            }
            else {
                v1 = false;
            }

            this.f = v1;
            byte[] v3 = (arg3 & 8) == 0 ? net.hockeyapp.android.e.b$b.g : net.hockeyapp.android.e.b$b.h;
            this.k = v3;
            this.i = new byte[2];
            this.c = 0;
            arg3 = this.e ? 19 : -1;
            this.j = arg3;
        }

        public boolean a(byte[] arg19, int arg20, int arg21, boolean arg22) {
            int v4_1;
            int v1;
            int v2;
            int v7;
            int v11;
            net.hockeyapp.android.e.b$b v0 = this;
            byte[] v3 = v0.k;
            byte[] v4 = v0.a;
            int v5 = v0.j;
            int v6 = arg21 + arg20;
            int v8 = -1;
            int v9 = 0;
            switch(v0.c) {
                case 1: {
                    if(arg20 + 2 > v6) {
                        goto label_43;
                    }

                    v11 = arg20 + 1;
                    v7 = v11 + 1;
                    v2 = (arg19[arg20] & 255) << 8 | (v0.i[0] & 255) << 16 | arg19[v11] & 255;
                    goto label_41;
                }
                case 2: {
                    v7 = arg20 + 1;
                    if(v7 > v6) {
                        goto label_43;
                    }

                    v2 = arg19[arg20] & 255 | ((v0.i[0] & 255) << 16 | (v0.i[1] & 255) << 8);
                label_41:
                    v0.c = 0;
                    goto label_45;
                }
                default: {
                    break;
                }
            }

        label_43:
            v7 = arg20;
            v2 = -1;
        label_45:
            int v12 = 4;
            byte v13 = 13;
            byte v14 = 10;
            int v15 = 2;
            if(v2 != v8) {
                v4[0] = v3[v2 >> 18 & 63];
                v4[1] = v3[v2 >> 12 & 63];
                v4[v15] = v3[v2 >> 6 & 63];
                v4[3] = v3[v2 & 63];
                --v5;
                if(v5 == 0) {
                    if(v0.f) {
                        v2 = 5;
                        v4[v12] = v13;
                    }
                    else {
                        v2 = 4;
                    }

                    v5 = v2 + 1;
                    v4[v2] = v14;
                    v2 = 19;
                }
                else {
                    v2 = v5;
                    v5 = 4;
                }
            }
            else {
                v2 = v5;
                v5 = 0;
            }

            while(true) {
                v8 = v7 + 3;
                if(v8 > v6) {
                    break;
                }

                v7 = arg19[v7 + 2] & 255 | ((arg19[v7] & 255) << 16 | (arg19[v7 + 1] & 255) << 8);
                v4[v5] = v3[v7 >> 18 & 63];
                v4[v5 + 1] = v3[v7 >> 12 & 63];
                v4[v5 + 2] = v3[v7 >> 6 & 63];
                v4[v5 + 3] = v3[v7 & 63];
                v5 += 4;
                --v2;
                if(v2 == 0) {
                    if(v0.f) {
                        v2 = v5 + 1;
                        v4[v5] = v13;
                    }
                    else {
                        v2 = v5;
                    }

                    v5 = v2 + 1;
                    v4[v2] = v14;
                    v7 = v8;
                    v2 = 19;
                    continue;
                }

                v7 = v8;
            }

            if(arg22) {
                if(v7 - v0.c == v6 - 1) {
                    if(v0.c > 0) {
                        v1 = v0.i[0];
                        v9 = 1;
                    }
                    else {
                        v1 = arg19[v7];
                        ++v7;
                    }

                    v1 = (v1 & 255) << v12;
                    v0.c -= v9;
                    v8 = v5 + 1;
                    v4[v5] = v3[v1 >> 6 & 63];
                    v5 = v8 + 1;
                    v4[v8] = v3[v1 & 63];
                    if(v0.d) {
                        v1 = v5 + 1;
                        v4[v5] = 61;
                        v5 = v1 + 1;
                        v4[v1] = 61;
                    }

                    if(!v0.e) {
                        goto label_260;
                    }

                    if(v0.f) {
                        v1 = v5 + 1;
                        v4[v5] = v13;
                    }
                    else {
                        v1 = v5;
                    }

                    v5 = v1 + 1;
                    v4[v1] = v14;
                }
                else {
                    if(v7 - v0.c == v6 - 2) {
                        if(v0.c > 1) {
                            v9 = 1;
                            v8 = v7;
                            v7 = v0.i[0];
                        }
                        else {
                            v8 = v7 + 1;
                            v7 = arg19[v7];
                        }

                        v7 = (v7 & 255) << v14;
                        if(v0.c > 0) {
                            v11 = v9 + 1;
                            v1 = v0.i[v9];
                        }
                        else {
                            v1 = arg19[v8];
                            ++v8;
                            v11 = v9;
                        }

                        v1 = (v1 & 255) << 2 | v7;
                        v0.c -= v11;
                        v7 = v5 + 1;
                        v4[v5] = v3[v1 >> 12 & 63];
                        v5 = v7 + 1;
                        v4[v7] = v3[v1 >> 6 & 63];
                        v7 = v5 + 1;
                        v4[v5] = v3[v1 & 63];
                        if(v0.d) {
                            v1 = v7 + 1;
                            v4[v7] = 61;
                        }
                        else {
                            v1 = v7;
                        }

                        if(v0.e) {
                            if(v0.f) {
                                v4[v1] = v13;
                                ++v1;
                            }

                            v4[v1] = v14;
                            ++v1;
                        }

                        v5 = v1;
                        v7 = v8;
                        goto label_260;
                    }

                    if(!v0.e) {
                        goto label_260;
                    }

                    if(v5 <= 0) {
                        goto label_260;
                    }

                    if(v2 == 19) {
                        goto label_260;
                    }

                    if(v0.f) {
                        v1 = v5 + 1;
                        v4[v5] = v13;
                    }
                    else {
                        v1 = v5;
                    }

                    v4[v1] = v14;
                    v5 = v1 + 1;
                }

            label_260:
                if(v0.c != 0) {
                    e.d("BASE64", "Error during encoding");
                }

                if(v7 == v6) {
                    goto label_295;
                }

                e.d("BASE64", "Error during encoding");
            }
            else {
                if(v7 == v6 - 1) {
                    v3 = v0.i;
                    v4_1 = v0.c;
                    v0.c = v4_1 + 1;
                    v3[v4_1] = arg19[v7];
                    goto label_295;
                }

                if(v7 != v6 - 2) {
                    goto label_295;
                }

                v3 = v0.i;
                v4_1 = v0.c;
                v0.c = v4_1 + 1;
                v3[v4_1] = arg19[v7];
                v3 = v0.i;
                v4_1 = v0.c;
                v0.c = v4_1 + 1;
                v3[v4_1] = arg19[v7 + 1];
            }

        label_295:
            v0.b = v5;
            v0.j = v2;
            return 1;
        }
    }

    public static String a(byte[] arg1, int arg2) {
        try {
            return new String(b.b(arg1, arg2), "US-ASCII");
        }
        catch(UnsupportedEncodingException v1) {
            throw new AssertionError(v1);
        }
    }

    public static byte[] a(byte[] arg4, int arg5, int arg6, int arg7) {
        net.hockeyapp.android.e.b$b v0 = new net.hockeyapp.android.e.b$b(arg7, null);
        arg7 = arg6 / 3 * 4;
        if(!v0.d) {
            switch(arg6 % 3) {
                case 1: {
                    goto label_16;
                }
                case 2: {
                    goto label_14;
                }
            }

            goto label_17;
        label_14:
            arg7 += 3;
            goto label_17;
        label_16:
            arg7 += 2;
        }
        else if(arg6 % 3 > 0) {
            arg7 += 4;
        }

    label_17:
        if((v0.e) && arg6 > 0) {
            int v1 = (arg6 - 1) / 57 + 1;
            int v3 = v0.f ? 2 : 1;
            arg7 += v1 * v3;
        }

        v0.a = new byte[arg7];
        v0.a(arg4, arg5, arg6, true);
        if(v0.b == arg7) {
            return v0.a;
        }

        throw new AssertionError();
    }

    public static byte[] b(byte[] arg2, int arg3) {
        return b.a(arg2, 0, arg2.length, arg3);
    }
}

