package com.e.a;

import android.graphics.Bitmap$Config;
import android.net.Uri;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class o {
    class com.e.a.o$1 {
    }

    public final class a {
        private Uri a;
        private int b;
        private String c;
        private int d;
        private int e;
        private boolean f;
        private boolean g;
        private boolean h;
        private float i;
        private float j;
        private float k;
        private boolean l;
        private List m;
        private Bitmap$Config n;
        private c o;

        public a a(int arg1, int arg2) {
            if(arg1 >= 0) {
                if(arg2 >= 0) {
                    if(arg2 == 0) {
                        if(arg1 != 0) {
                        }
                        else {
                            throw new IllegalArgumentException("At least one dimension has to be positive number.");
                        }
                    }

                    this.d = arg1;
                    this.e = arg2;
                    return this;
                }

                throw new IllegalArgumentException("Height must be positive number or 0.");
            }

            throw new IllegalArgumentException("Width must be positive number or 0.");
        }

        boolean a() {
            boolean v0 = this.a != null || this.b != 0 ? true : false;
            return v0;
        }

        boolean b() {
            boolean v0 = this.d != 0 || this.e != 0 ? true : false;
            return v0;
        }

        public o c() {
            a v0 = this;
            if(v0.g) {
                if(!v0.f) {
                }
                else {
                    throw new IllegalStateException("Center crop and center inside can not be used together.");
                }
            }

            if((v0.f) && v0.d == 0) {
                if(v0.e != 0) {
                }
                else {
                    throw new IllegalStateException("Center crop requires calling resize with positive width and height.");
                }
            }

            if((v0.g) && v0.d == 0) {
                if(v0.e != 0) {
                }
                else {
                    throw new IllegalStateException("Center inside requires calling resize with positive width and height.");
                }
            }

            if(v0.o == null) {
                v0.o = c.b;
            }

            return new o(v0.a, v0.b, v0.c, v0.m, v0.d, v0.e, v0.f, v0.g, v0.h, v0.i, v0.j, v0.k, v0.l, v0.n, v0.o, null);
        }
    }

    int a;
    long b;
    int c;
    public final Uri d;
    public final int e;
    public final String f;
    public final List g;
    public final int h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final float m;
    public final float n;
    public final float o;
    public final boolean p;
    public final Bitmap$Config q;
    public final c r;
    private static final long s;

    static {
        o.s = TimeUnit.SECONDS.toNanos(5);
    }

    private o(Uri arg1, int arg2, String arg3, List arg4, int arg5, int arg6, boolean arg7, boolean arg8, boolean arg9, float arg10, float arg11, float arg12, boolean arg13, Bitmap$Config arg14, c arg15) {
        super();
        this.d = arg1;
        this.e = arg2;
        this.f = arg3;
        List v1 = arg4 == null ? null : Collections.unmodifiableList(arg4);
        this.g = v1;
        this.h = arg5;
        this.i = arg6;
        this.j = arg7;
        this.k = arg8;
        this.l = arg9;
        this.m = arg10;
        this.n = arg11;
        this.o = arg12;
        this.p = arg13;
        this.q = arg14;
        this.r = arg15;
    }

    o(Uri arg1, int arg2, String arg3, List arg4, int arg5, int arg6, boolean arg7, boolean arg8, boolean arg9, float arg10, float arg11, float arg12, boolean arg13, Bitmap$Config arg14, c arg15, com.e.a.o$1 arg16) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15);
    }

    String a() {
        // Method was not decompiled
    }

    String b() {
        return "[R" + this.a + ']';
    }

    String c() {
        if(this.d != null) {
            return String.valueOf(this.d.getPath());
        }

        return Integer.toHexString(this.e);
    }

    public boolean d() {
        boolean v0 = this.h != 0 || this.i != 0 ? true : false;
        return v0;
    }

    boolean e() {
        boolean v0 = (this.f()) || (this.g()) ? true : false;
        return v0;
    }

    boolean f() {
        boolean v0 = (this.d()) || this.m != 0f ? true : false;
        return v0;
    }

    boolean g() {
        boolean v0 = this.g != null ? true : false;
        return v0;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("Request{");
        if(this.e > 0) {
            v0.append(this.e);
        }
        else {
            v0.append(this.d);
        }

        char v2 = ' ';
        if(this.g != null && !this.g.isEmpty()) {
            Iterator v1 = this.g.iterator();
            while(v1.hasNext()) {
                Object v3 = v1.next();
                v0.append(v2);
                v0.append(((u)v3).a());
            }
        }

        char v3_1 = ')';
        if(this.f != null) {
            v0.append(" stableKey(");
            v0.append(this.f);
            v0.append(v3_1);
        }

        char v4 = ',';
        if(this.h > 0) {
            v0.append(" resize(");
            v0.append(this.h);
            v0.append(v4);
            v0.append(this.i);
            v0.append(v3_1);
        }

        if(this.j) {
            v0.append(" centerCrop");
        }

        if(this.k) {
            v0.append(" centerInside");
        }

        if(this.m != 0f) {
            v0.append(" rotation(");
            v0.append(this.m);
            if(this.p) {
                v0.append(" @ ");
                v0.append(this.n);
                v0.append(v4);
                v0.append(this.o);
            }

            v0.append(v3_1);
        }

        if(this.q != null) {
            v0.append(v2);
            v0.append(this.q);
        }

        v0.append('}');
        return v0.toString();
    }
}

