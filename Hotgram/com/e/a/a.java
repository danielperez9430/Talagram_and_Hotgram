package com.e.a;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class a {
    class com.e.a.a$a extends WeakReference {
        final a a;

        public com.e.a.a$a(a arg1, Object arg2, ReferenceQueue arg3) {
            super(arg2, arg3);
            this.a = arg1;
        }
    }

    final m a;
    final o b;
    final WeakReference c;
    final boolean d;
    final int e;
    final int f;
    final int g;
    final Drawable h;
    final String i;
    final Object j;
    boolean k;
    boolean l;

    a(m arg1, Object arg2, o arg3, int arg4, int arg5, int arg6, Drawable arg7, String arg8, Object arg9, boolean arg10) {
        a v9;
        WeakReference v1;
        super();
        this.a = arg1;
        this.b = arg3;
        if(arg2 == null) {
            v1 = null;
        }
        else {
            com.e.a.a$a v1_1 = new com.e.a.a$a(this, arg2, arg1.i);
        }

        this.c = v1;
        this.e = arg4;
        this.f = arg5;
        this.d = arg10;
        this.g = arg6;
        this.h = arg7;
        this.i = arg8;
        if(arg9 != null) {
        }
        else {
            v9 = this;
        }

        this.j = v9;
    }

    abstract void a();

    abstract void a(Bitmap arg1, b arg2);

    void b() {
        this.l = true;
    }

    Object c() {
        Object v0 = this.c == null ? null : this.c.get();
        return v0;
    }

    String d() {
        return this.i;
    }

    boolean e() {
        return this.l;
    }

    boolean f() {
        return this.k;
    }

    m g() {
        return this.a;
    }
}

