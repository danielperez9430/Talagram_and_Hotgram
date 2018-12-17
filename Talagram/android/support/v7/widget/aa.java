package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

class aa implements ad {
    aa() {
        super();
    }

    public float a(ac arg1) {
        return this.j(arg1).a();
    }

    public void a() {
    }

    public void a(ac arg1, float arg2) {
        this.j(arg1).a(arg2);
    }

    public void a(ac arg1, Context arg2, ColorStateList arg3, float arg4, float arg5, float arg6) {
        arg1.a(new ay(arg3, arg4));
        View v2 = arg1.d();
        v2.setClipToOutline(true);
        v2.setElevation(arg5);
        this.b(arg1, arg6);
    }

    public void a(ac arg1, ColorStateList arg2) {
        this.j(arg1).a(arg2);
    }

    public void b(ac arg4, float arg5) {
        this.j(arg4).a(arg5, arg4.a(), arg4.b());
        this.f(arg4);
    }

    public float b(ac arg2) {
        return this.d(arg2) * 2f;
    }

    public float c(ac arg2) {
        return this.d(arg2) * 2f;
    }

    public void c(ac arg1, float arg2) {
        arg1.d().setElevation(arg2);
    }

    public float d(ac arg1) {
        return this.j(arg1).b();
    }

    public float e(ac arg1) {
        return arg1.d().getElevation();
    }

    public void f(ac arg5) {
        if(!arg5.a()) {
            arg5.a(0, 0, 0, 0);
            return;
        }

        float v0 = this.a(arg5);
        float v1 = this.d(arg5);
        int v2 = ((int)Math.ceil(((double)az.b(v0, v1, arg5.b()))));
        int v0_1 = ((int)Math.ceil(((double)az.a(v0, v1, arg5.b()))));
        arg5.a(v2, v0_1, v2, v0_1);
    }

    public void g(ac arg2) {
        this.b(arg2, this.a(arg2));
    }

    public void h(ac arg2) {
        this.b(arg2, this.a(arg2));
    }

    public ColorStateList i(ac arg1) {
        return this.j(arg1).c();
    }

    private ay j(ac arg1) {
        return arg1.c();
    }
}

