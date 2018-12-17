package com.e.a;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.concurrent.atomic.AtomicInteger;

public class p {
    private static final AtomicInteger a;
    private final m b;
    private final a c;
    private boolean d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private int j;
    private Drawable k;
    private Drawable l;
    private Object m;

    static {
        p.a = new AtomicInteger();
    }

    public void a(ImageView arg16, d arg17) {
        p v0 = this;
        ImageView v3 = arg16;
        d v11 = arg17;
        long v1 = System.nanoTime();
        v.a();
        if(v3 != null) {
            if(!v0.c.a()) {
                v0.b.a(v3);
                if(v0.f) {
                    n.a(v3, this.b());
                }

                return;
            }

            if(v0.e) {
                if(!v0.c.b()) {
                    int v4 = arg16.getWidth();
                    int v5 = arg16.getHeight();
                    if(v4 != 0) {
                        if(v5 == 0) {
                        }
                        else {
                            v0.c.a(v4, v5);
                            goto label_42;
                        }
                    }

                    if(v0.f) {
                        n.a(v3, this.b());
                    }

                    v0.b.a(v3, new e(this, v3, v11));
                    return;
                }
                else {
                    throw new IllegalStateException("Fit cannot be used with resize.");
                }
            }

        label_42:
            o v7 = this.a(v1);
            String v9 = v.a(v7);
            if(j.a(v0.i)) {
                Bitmap v4_1 = v0.b.a(v9);
                if(v4_1 != null) {
                    v0.b.a(v3);
                    n.a(arg16, v0.b.c, v4_1, b.a, v0.d, v0.b.j);
                    if(v0.b.k) {
                        String v3_1 = v7.b();
                        v.a("Main", "completed", v3_1, "from " + b.a);
                    }

                    if(v11 != null) {
                        arg17.a();
                    }

                    return;
                }
            }

            if(v0.f) {
                n.a(v3, this.b());
            }

            v0.b.a(new h(v0.b, arg16, v7, v0.i, v0.j, v0.h, v0.l, v9, v0.m, arg17, v0.d));
            return;
        }

        throw new IllegalArgumentException("Target must not be null.");
    }

    public p a(int arg2, int arg3) {
        this.c.a(arg2, arg3);
        return this;
    }

    p a() {
        this.e = false;
        return this;
    }

    private o a(long arg8) {
        int v0 = p.a.getAndIncrement();
        o v1 = this.c.c();
        v1.a = v0;
        v1.b = arg8;
        boolean v2 = this.b.k;
        if(v2) {
            v.a("Main", "created", v1.b(), v1.toString());
        }

        o v3 = this.b.a(v1);
        if(v3 != v1) {
            v3.a = v0;
            v3.b = arg8;
            if(v2) {
                String v0_1 = v3.a();
                v.a("Main", "changed", v0_1, "into " + v3);
            }
        }

        return v3;
    }

    private Drawable b() {
        if(this.g != 0) {
            return this.b.c.getResources().getDrawable(this.g);
        }

        return this.k;
    }
}

