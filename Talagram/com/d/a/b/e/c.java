package com.d.a.b.e;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.d.a.b.a.e;
import com.d.a.b.a.h;

public class c implements a {
    protected final String a;
    protected final e b;
    protected final h c;

    public c(String arg1, e arg2, h arg3) {
        super();
        if(arg2 != null) {
            if(arg3 != null) {
                this.a = arg1;
                this.b = arg2;
                this.c = arg3;
                return;
            }

            throw new IllegalArgumentException("scaleType must not be null");
        }

        throw new IllegalArgumentException("imageSize must not be null");
    }

    public int a() {
        return this.b.a();
    }

    public boolean a(Bitmap arg1) {
        return 1;
    }

    public boolean a(Drawable arg1) {
        return 1;
    }

    public int b() {
        return this.b.b();
    }

    public h c() {
        return this.c;
    }

    public View d() {
        return null;
    }

    public boolean e() {
        return 0;
    }

    public int f() {
        int v0 = TextUtils.isEmpty(this.a) ? super.hashCode() : this.a.hashCode();
        return v0;
    }
}

