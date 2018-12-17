package org.telegram.customization.speechrecognitionview;

import android.graphics.RectF;

public class a {
    private int a;
    private int b;
    private int c;
    private int d;
    private final int e;
    private final int f;
    private final int g;
    private final RectF h;

    public a(int arg3, int arg4, int arg5, int arg6, int arg7) {
        super();
        this.a = arg3;
        this.b = arg4;
        this.c = arg7;
        this.f = arg3;
        this.g = arg4;
        this.d = arg5;
        this.e = arg6;
        arg5 /= 2;
        this.h = new RectF(((float)(arg3 - arg7)), ((float)(arg4 - arg5)), ((float)(arg3 + arg7)), ((float)(arg4 + arg5)));
    }

    public void a(int arg1) {
        this.a = arg1;
    }

    public void a() {
        this.h.set(((float)(this.a - this.c)), ((float)(this.b - this.d / 2)), ((float)(this.a + this.c)), ((float)(this.b + this.d / 2)));
    }

    public void b(int arg1) {
        this.b = arg1;
    }

    public int b() {
        return this.a;
    }

    public void c(int arg1) {
        this.d = arg1;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public RectF h() {
        return this.h;
    }

    public int i() {
        return this.c;
    }
}

