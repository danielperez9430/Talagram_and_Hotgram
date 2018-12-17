package com.e.a;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

class h extends a {
    d m;

    h(m arg12, ImageView arg13, o arg14, int arg15, int arg16, int arg17, Drawable arg18, String arg19, Object arg20, d arg21, boolean arg22) {
        super(arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg22);
        this.m = arg21;
    }

    public void a() {
        Object v0 = this.c.get();
        if(v0 == null) {
            return;
        }

        if(this.g != 0) {
            ((ImageView)v0).setImageResource(this.g);
        }
        else if(this.h != null) {
            ((ImageView)v0).setImageDrawable(this.h);
        }

        if(this.m != null) {
            this.m.b();
        }
    }

    public void a(Bitmap arg8, b arg9) {
        if(arg8 != null) {
            Object v1 = this.c.get();
            if(v1 == null) {
                return;
            }

            n.a(((ImageView)v1), this.a.c, arg8, arg9, this.d, this.a.j);
            if(this.m != null) {
                this.m.a();
            }

            return;
        }

        throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", this));
    }

    void b() {
        super.b();
        if(this.m != null) {
            this.m = null;
        }
    }
}

