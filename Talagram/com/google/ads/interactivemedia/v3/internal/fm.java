package com.google.ads.interactivemedia.v3.internal;

public final class fm {
    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    private static final String[] h;
    private static final int[] i;
    private static final int[] j;
    private static final int[] k;
    private static final int[] l;
    private static final int[] m;
    private static final int[] n;

    static {
        fm.h = new String[]{"audio/mpeg-L1", "audio/mpeg-L2", "audio/mpeg"};
        fm.i = new int[]{44100, 48000, 32000};
        fm.j = new int[]{32, 64, 96, 128, 160, 192, 224, 256, 288, 320, 352, 384, 416, 448};
        fm.k = new int[]{32, 48, 56, 64, 80, 96, 112, 128, 144, 160, 176, 192, 224, 256};
        fm.l = new int[]{32, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384};
        fm.m = new int[]{32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320};
        fm.n = new int[]{8, 16, 24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160};
    }

    public fm() {
        super();
    }

    public static int a(int arg7) {
        int v2 = -1;
        if((arg7 & -2097152) != -2097152) {
            return v2;
        }

        int v1 = 3;
        int v0 = arg7 >>> 19 & v1;
        if(v0 == 1) {
            return v2;
        }

        int v4 = arg7 >>> 17 & v1;
        if(v4 == 0) {
            return v2;
        }

        int v6 = 15;
        int v5 = arg7 >>> 12 & v6;
        if(v5 != 0) {
            if(v5 == v6) {
            }
            else {
                v6 = arg7 >>> 10 & v1;
                if(v6 == v1) {
                    return v2;
                }
                else {
                    v2 = fm.i[v6];
                    v6 = 2;
                    if(v0 == v6) {
                        v2 /= 2;
                    }
                    else if(v0 == 0) {
                        v2 /= 4;
                    }

                    arg7 = arg7 >>> 9 & 1;
                    if(v4 == v1) {
                        v0 = v0 == v1 ? fm.j[v5 - 1] : fm.k[v5 - 1];
                        return (v0 * 12000 / v2 + arg7) * 4;
                    }

                    if(v0 != v1) {
                        v5 = fm.n[v5 - 1];
                    }
                    else if(v4 == v6) {
                        v5 = fm.l[v5 - 1];
                    }
                    else {
                        v5 = fm.m[v5 - 1];
                    }

                    v6 = 144000;
                    if(v0 == v1) {
                        return v5 * v6 / v2 + arg7;
                    }

                    if(v4 == 1) {
                        v6 = 72000;
                    }

                    return v6 * v5 / v2 + arg7;
                }
            }
        }

        return v2;
    }

    private void a(int arg1, String arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
        this.g = arg7;
    }

    public static boolean a(int arg11, fm arg12) {
        int v10;
        int v9;
        if((arg11 & -2097152) != -2097152) {
            return 0;
        }

        int v1 = 3;
        int v4 = arg11 >>> 19 & 3;
        if(v4 == 1) {
            return 0;
        }

        int v3 = arg11 >>> 17 & v1;
        if(v3 == 0) {
            return 0;
        }

        int v6 = 15;
        int v5 = arg11 >>> 12 & v6;
        if(v5 != 0) {
            if(v5 == v6) {
            }
            else {
                v6 = arg11 >>> 10 & v1;
                if(v6 == v1) {
                    return 0;
                }
                else {
                    int v2 = fm.i[v6];
                    v6 = 2;
                    if(v4 == v6) {
                        v2 /= 2;
                    }
                    else if(v4 == 0) {
                        v2 /= 4;
                    }

                    int v7 = v2;
                    v2 = arg11 >>> 9 & 1;
                    int v8 = 1152;
                    if(v3 == v1) {
                        v5 = v4 == v1 ? fm.j[v5 - 1] : fm.k[v5 - 1];
                        v9 = (v5 * 12000 / v7 + v2) * 4;
                        v10 = 384;
                    }
                    else {
                        v9 = 144000;
                        if(v4 == v1) {
                            v5 = v3 == v6 ? fm.l[v5 - 1] : fm.m[v5 - 1];
                            v9 = v9 * v5 / v7 + v2;
                            v10 = 1152;
                            goto label_81;
                        }

                        v5 = fm.n[v5 - 1];
                        if(v3 == 1) {
                            v8 = 576;
                        }

                        if(v3 == 1) {
                            v9 = 72000;
                        }

                        v9 = v9 * v5 / v7 + v2;
                        v10 = v8;
                    }

                label_81:
                    String v2_1 = fm.h[3 - v3];
                    v8 = (arg11 >> 6 & v1) == v1 ? 1 : 2;
                    arg12.a(v4, v2_1, v9, v7, v8, v5 * 1000, v10);
                    return 1;
                }
            }
        }

        return 0;
    }
}

