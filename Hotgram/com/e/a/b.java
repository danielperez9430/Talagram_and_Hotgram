package com.e.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class b implements Runnable {
    final class com.e.a.b$1 extends ThreadLocal {
        com.e.a.b$1() {
            super();
        }

        protected StringBuilder a() {
            return new StringBuilder("Picasso-");
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    final class com.e.a.b$2 extends q {
        com.e.a.b$2() {
            super();
        }

        public a a(o arg3, int arg4) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Unrecognized type of request: ");
            v0.append(arg3);
            throw new IllegalStateException(v0.toString());
        }
    }

    final m a;
    final f b;
    final c c;
    final r d;
    final String e;
    final o f;
    final int g;
    int h;
    final q i;
    com.e.a.a j;
    List k;
    Bitmap l;
    com.e.a.m$b m;
    Exception n;
    int o;
    int p;
    private static final Object q;
    private static final ThreadLocal r;
    private static final AtomicInteger s;
    private static final q t;

    static {
        b.q = new Object();
        b.r = new com.e.a.b$1();
        b.s = new AtomicInteger();
        b.t = new com.e.a.b$2();
    }

    static Bitmap a(o arg13, Bitmap arg14, int arg15) {
        int v7_1;
        int v9_1;
        int v6_1;
        float v7;
        float v13;
        int v5;
        int v0 = arg14.getWidth();
        int v1 = arg14.getHeight();
        boolean v2 = arg13.l;
        Matrix v8 = new Matrix();
        int v4 = 0;
        if(arg13.f()) {
            int v3 = arg13.h;
            v5 = arg13.i;
            float v6 = arg13.m;
            if(v6 != 0f) {
                if(arg13.p) {
                    v8.setRotate(v6, arg13.n, arg13.o);
                }
                else {
                    v8.setRotate(v6);
                }
            }

            if(arg13.j) {
                v13 = ((float)v3);
                v6 = ((float)v0);
                v7 = v13 / v6;
                float v9 = ((float)v5);
                float v10 = ((float)v1);
                float v11 = v9 / v10;
                if(v7 > v11) {
                    int v13_1 = ((int)Math.ceil(((double)(v10 * (v11 / v7)))));
                    v6_1 = (v1 - v13_1) / 2;
                    v11 = v9 / (((float)v13_1));
                    v9_1 = v13_1;
                    v13 = v7;
                    v7_1 = v0;
                }
                else {
                    v6_1 = ((int)Math.ceil(((double)(v6 * (v7 / v11)))));
                    v13 /= ((float)v6_1);
                    v9_1 = v1;
                    v4 = (v0 - v6_1) / 2;
                    v7_1 = v6_1;
                    v6_1 = 0;
                }

                if(b.a(v2, v0, v1, v3, v5)) {
                    v8.preScale(v13, v11);
                }

                v5 = v6_1;
                v6_1 = v7_1;
                v7_1 = v9_1;
                goto label_103;
            }

            if(arg13.k) {
                v13 = (((float)v3)) / (((float)v0));
                v6 = (((float)v5)) / (((float)v1));
                if(v13 < v6) {
                }
                else {
                    v13 = v6;
                }

                if(!b.a(v2, v0, v1, v3, v5)) {
                    goto label_100;
                }

                v8.preScale(v13, v13);
                goto label_100;
            }

            if(v3 == 0 && v5 == 0) {
                goto label_100;
            }

            if(v3 == v0 && v5 == v1) {
                goto label_100;
            }

            if(v3 != 0) {
                v13 = ((float)v3);
                v6 = ((float)v0);
            }
            else {
                v13 = ((float)v5);
                v6 = ((float)v1);
            }

            v13 /= v6;
            if(v5 != 0) {
                v6 = ((float)v5);
                v7 = ((float)v1);
            }
            else {
                v6 = ((float)v3);
                v7 = ((float)v0);
            }

            v6 /= v7;
            if(!b.a(v2, v0, v1, v3, v5)) {
                goto label_100;
            }

            v8.preScale(v13, v6);
            goto label_100;
        }
        else {
        label_100:
            v6_1 = v0;
            v7_1 = v1;
            v5 = 0;
        }

    label_103:
        if(arg15 != 0) {
            v8.preRotate(((float)arg15));
        }

        Bitmap v13_2 = Bitmap.createBitmap(arg14, v4, v5, v6_1, v7_1, v8, true);
        if(v13_2 != arg14) {
            arg14.recycle();
        }
        else {
            v13_2 = arg14;
        }

        return v13_2;
    }

    private static boolean a(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        return !arg0 || arg1 > arg3 || arg2 > arg4 ? true : false;
    }

    static Bitmap a(InputStream arg6, o arg7) {
        i v0 = new i(arg6);
        long v1 = v0.a(65536);
        BitmapFactory$Options v6 = q.a(arg7);
        boolean v3 = q.a(v6);
        boolean v4 = v.c(((InputStream)v0));
        v0.a(v1);
        if(v4) {
            byte[] v0_1 = v.b(((InputStream)v0));
            if(v3) {
                BitmapFactory.decodeByteArray(v0_1, 0, v0_1.length, v6);
                q.a(arg7.h, arg7.i, v6, arg7);
            }

            return BitmapFactory.decodeByteArray(v0_1, 0, v0_1.length, v6);
        }

        Rect v4_1 = null;
        if(v3) {
            BitmapFactory.decodeStream(((InputStream)v0), v4_1, v6);
            q.a(arg7.h, arg7.i, v6, arg7);
            v0.a(v1);
        }

        Bitmap v6_1 = BitmapFactory.decodeStream(((InputStream)v0), v4_1, v6);
        if(v6_1 != null) {
            return v6_1;
        }

        throw new IOException("Failed to decode stream.");
    }

    static Bitmap a(List arg6, Bitmap arg7) {
        Iterator v6_1;
        StringBuilder v7;
        Bitmap v4;
        Bitmap v3;
        int v0 = arg6.size();
        int v1 = 0;
        while(v1 < v0) {
            Object v2 = arg6.get(v1);
            v3 = null;
            try {
                v4 = ((u)v2).a(arg7);
                if(v4 != null) {
                    goto label_32;
                }
            }
            catch(RuntimeException v6) {
                m.a.post(new Runnable(((u)v2), v6) {
                    public void run() {
                        StringBuilder v1 = new StringBuilder();
                        v1.append("Transformation ");
                        v1.append(this.a.a());
                        v1.append(" crashed with exception.");
                        throw new RuntimeException(v1.toString(), this.b);
                    }
                });
                return v3;
            }

            v7 = new StringBuilder();
            v7.append("Transformation ");
            v7.append(((u)v2).a());
            v7.append(" returned null after ");
            v7.append(v1);
            v7.append(" previous transformation(s).\n\nTransformation list:\n");
            v6_1 = arg6.iterator();
            goto label_19;
        label_32:
            if(v4 == arg7 && (arg7.isRecycled())) {
                m.a.post(new Runnable(((u)v2)) {
                    public void run() {
                        StringBuilder v1 = new StringBuilder();
                        v1.append("Transformation ");
                        v1.append(this.a.a());
                        v1.append(" returned input Bitmap but recycled it.");
                        throw new IllegalStateException(v1.toString());
                    }
                });
                return v3;
            }

            if(v4 != arg7 && !arg7.isRecycled()) {
                m.a.post(new Runnable(((u)v2)) {
                    public void run() {
                        StringBuilder v1 = new StringBuilder();
                        v1.append("Transformation ");
                        v1.append(this.a.a());
                        v1.append(" mutated input Bitmap but failed to recycle the original.");
                        throw new IllegalStateException(v1.toString());
                    }
                });
                return v3;
            }

            ++v1;
            arg7 = v4;
        }

        return arg7;
    label_19:
        while(v6_1.hasNext()) {
            v7.append(v6_1.next().a());
            v7.append('\n');
        }

        m.a.post(new Runnable(v7) {
            public void run() {
                throw new NullPointerException(this.a.toString());
            }
        });
        return v3;
    }

    static void a(o arg3) {
        String v3 = arg3.c();
        Object v0 = b.r.get();
        ((StringBuilder)v0).ensureCapacity("Picasso-".length() + v3.length());
        ((StringBuilder)v0).replace("Picasso-".length(), ((StringBuilder)v0).length(), v3);
        Thread.currentThread().setName(((StringBuilder)v0).toString());
    }

    Bitmap a() {
        Bitmap v1_3;
        Bitmap v0;
        if(j.a(this.g)) {
            v0 = this.c.a(this.e);
            if(v0 != null) {
                this.d.a();
                this.m = com.e.a.m$b.a;
                if(this.a.k) {
                    v.a("Hunter", "decoded", this.f.a(), "from cache");
                }

                return v0;
            }
        }
        else {
            v0 = null;
        }

        o v1 = this.f;
        int v2 = this.p == 0 ? k.c.d : this.h;
        v1.c = v2;
        a v1_1 = this.i.a(this.f, this.h);
        if(v1_1 != null) {
            this.m = v1_1.c();
            this.o = v1_1.d();
            v0 = v1_1.a();
            if(v0 == null) {
                InputStream v0_1 = v1_1.b();
                try {
                    v1_3 = b.a(v0_1, this.f);
                }
                catch(Throwable v1_2) {
                    v.a(v0_1);
                    throw v1_2;
                }

                v.a(v0_1);
                v0 = v1_3;
            }
        }

        if(v0 != null) {
            if(this.a.k) {
                v.a("Hunter", "decoded", this.f.a());
            }

            this.d.a(v0);
            if(!this.f.e() && this.o == 0) {
                return v0;
            }

            Object v1_4 = b.q;
            __monitor_enter(v1_4);
            try {
                if((this.f.f()) || this.o != 0) {
                    v0 = b.a(this.f, v0, this.o);
                    if(this.a.k) {
                        v.a("Hunter", "transformed", this.f.a());
                    }
                }

                if(this.f.g()) {
                    v0 = b.a(this.f.g, v0);
                    if(this.a.k) {
                        v.a("Hunter", "transformed", this.f.a(), "from custom transformations");
                    }
                }

                __monitor_exit(v1_4);
                if(v0 == null) {
                    return v0;
                }
            }
            catch(Throwable v0_2) {
                try {
                label_105:
                    __monitor_exit(v1_4);
                }
                catch(Throwable v0_2) {
                    goto label_105;
                }

                throw v0_2;
            }

            this.d.b(v0);
        }

        return v0;
    }

    Bitmap b() {
        return this.l;
    }

    o c() {
        return this.f;
    }

    com.e.a.a d() {
        return this.j;
    }

    List e() {
        return this.k;
    }

    Exception f() {
        return this.n;
    }

    com.e.a.m$b g() {
        return this.m;
    }

    public void run() {
        f v0_6;
        try {
            b.a(this.f);
            if(this.a.k) {
                v.a("Hunter", "executing", v.a(this));
            }

            this.l = this.a();
            if(this.l == null) {
                this.b.c(this);
                goto label_49;
            }

            this.b.a(this);
        }
        catch(Throwable v0) {
        }
        catch(Exception v0_1) {
            try {
                this.n = v0_1;
                v0_6 = this.b;
            label_24:
                v0_6.c(this);
            }
            catch(Throwable v0) {
            label_63:
                Thread.currentThread().setName("Picasso-Idle");
                throw v0;
            }
        }
        catch(OutOfMemoryError v0_2) {
        }
        catch(IOException v0_3) {
        }
        catch(com.e.a.l$a v0_4) {
        }
        catch(com.e.a.g$b v0_5) {
            try {
                if(!v0_5.a || v0_5.b != 504) {
                    this.n = ((Exception)v0_5);
                }

                v0_6 = this.b;
                goto label_24;
                this.n = ((Exception)v0_4);
                v0_6 = this.b;
                goto label_43;
                this.n = ((Exception)v0_3);
                v0_6 = this.b;
            label_43:
                v0_6.b(this);
                goto label_49;
                StringWriter v1 = new StringWriter();
                this.d.c().a(new PrintWriter(((Writer)v1)));
                this.n = new RuntimeException(v1.toString(), ((Throwable)v0_2));
                v0_6 = this.b;
                goto label_24;
            }
            catch(Throwable v0) {
                goto label_63;
            }
        }

    label_49:
        Thread.currentThread().setName("Picasso-Idle");
    }
}

