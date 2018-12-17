package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import java.util.Arrays;
import java.util.Collections;

final class dv extends du {
    final class a {
        public int a;
        public int b;
        public byte[] c;
        private boolean d;

        public a(int arg1) {
            super();
            this.c = new byte[arg1];
        }

        public void a() {
            this.d = false;
            this.a = 0;
            this.b = 0;
        }

        public void a(byte[] arg3, int arg4, int arg5) {
            if(!this.d) {
                return;
            }

            arg5 -= arg4;
            if(this.c.length < this.a + arg5) {
                this.c = Arrays.copyOf(this.c, (this.a + arg5) * 2);
            }

            System.arraycopy(arg3, arg4, this.c, this.a, arg5);
            this.a += arg5;
        }

        public boolean a(int arg4, int arg5) {
            if(this.d) {
                if(this.b == 0 && arg4 == 181) {
                    this.b = this.a;
                    return 0;
                }

                this.a -= arg5;
                this.d = false;
                return 1;
            }
            else {
                if(arg4 != 179) {
                    return 0;
                }

                this.d = true;
            }

            return 0;
        }
    }

    private static final double[] b;
    private boolean c;
    private long d;
    private final boolean[] e;
    private final a f;
    private boolean g;
    private long h;
    private long i;
    private boolean j;
    private boolean k;
    private long l;
    private long m;

    static {
        dv.b = new double[]{23.976024, 24, 25, 29.97003, 30, 50, 59.94006, 60};
    }

    public dv(ck arg2) {
        super(arg2);
        this.e = new boolean[4];
        this.f = new a(128);
    }

    private static Pair a(a arg19) {
        a v0 = arg19;
        byte[] v1 = Arrays.copyOf(v0.c, v0.a);
        int v3 = v1[4] & 255;
        int v4 = 5;
        int v5 = v1[v4] & 255;
        int v14 = v3 << 4 | v5 >> 4;
        int v15 = (v5 & 15) << 8 | v1[6] & 255;
        v3 = 7;
        switch((v1[v3] & 240) >> 4) {
            case 2: {
                goto label_35;
            }
            case 3: {
                goto label_31;
            }
            case 4: {
                goto label_27;
            }
        }

        float v18 = 1f;
        goto label_41;
    label_35:
        float v2 = ((float)(v15 * 4));
        v5 = v14 * 3;
        goto label_38;
    label_27:
        v2 = ((float)(v15 * 121));
        v5 = v14 * 100;
        goto label_38;
    label_31:
        v2 = ((float)(v15 * 16));
        v5 = v14 * 9;
    label_38:
        v18 = v2 / (((float)v5));
    label_41:
        bj v2_1 = bj.a(null, "video/mpeg2", -1, -1, -1, v14, v15, Collections.singletonList(v1), -1, v18);
        long v5_1 = 0;
        v3 = (v1[v3] & 15) - 1;
        if(v3 >= 0 && v3 < dv.b.length) {
            double v6 = dv.b[v3];
            int v0_1 = v0.b + 9;
            v3 = (v1[v0_1] & 96) >> v4;
            v0_1 = v1[v0_1] & 31;
            if(v3 != v0_1) {
                double v3_1 = ((double)v3);
                Double.isNaN(v3_1);
                double v0_2 = ((double)(v0_1 + 1));
                Double.isNaN(v0_2);
                v6 *= (v3_1 + 1) / v0_2;
            }

            v5_1 = ((long)(1000000 / v6));
        }

        return Pair.create(v2_1, Long.valueOf(v5_1));
    }

    public void a() {
        fn.a(this.e);
        this.f.a();
        this.j = false;
        this.g = false;
        this.h = 0;
    }

    public void a(long arg3, boolean arg5) {
        arg5 = arg3 != -1 ? true : false;
        this.j = arg5;
        if(this.j) {
            this.i = arg3;
        }
    }

    public void a(fp arg20) {
        int v8;
        dv v0 = this;
        fp v1 = arg20;
        if(arg20.b() > 0) {
            int v2 = arg20.d();
            int v3 = arg20.c();
            byte[] v4 = v1.a;
            v0.h += ((long)arg20.b());
            v0.a.a(v1, arg20.b());
            int v5 = v2;
            while(true) {
                v2 = fn.a(v4, v2, v3, v0.e);
                if(v2 == v3) {
                    break;
                }

                int v7 = v2 + 3;
                int v6 = v1.a[v7] & 255;
                if(!v0.c) {
                    v8 = v2 - v5;
                    if(v8 > 0) {
                        v0.f.a(v4, v5, v2);
                    }

                    v5 = v8 < 0 ? -v8 : 0;
                    if(!v0.f.a(v6, v5)) {
                        goto label_52;
                    }

                    Pair v5_1 = dv.a(v0.f);
                    v0.a.a(v5_1.first);
                    v0.d = v5_1.second.longValue();
                    v0.c = true;
                }

            label_52:
                if((v0.c) && (v6 == 184 || v6 == 0)) {
                    v8 = v3 - v2;
                    if(v0.g) {
                        v0.a.a(v0.m, v0.k, (((int)(v0.h - v0.l))) - v8, v8, null);
                        v0.k = false;
                        v5 = v6;
                    }
                    else {
                        v5 = v6;
                    }

                    if(v5 == 184) {
                        v0.g = false;
                        v0.k = true;
                        goto label_95;
                    }

                    long v5_2 = v0.j ? v0.i : v0.m + v0.d;
                    v0.m = v5_2;
                    v0.l = v0.h - (((long)v8));
                    v0.j = false;
                    v0.g = true;
                }

            label_95:
                v5 = v2;
                v2 = v7;
            }

            if(!v0.c) {
                v0.f.a(v4, v5, v3);
            }

            return;
        }
    }

    public void b() {
    }
}

