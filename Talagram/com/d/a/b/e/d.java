package com.d.a.b.e;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.d.a.b.a.h;
import com.d.a.c.c;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class d implements a {
    protected Reference a;
    protected boolean b;

    public d(View arg2) {
        this(arg2, true);
    }

    public d(View arg2, boolean arg3) {
        super();
        if(arg2 != null) {
            this.a = new WeakReference(arg2);
            this.b = arg3;
            return;
        }

        throw new IllegalArgumentException("view must not be null");
    }

    public int a() {
        Object v0 = this.a.get();
        int v1 = 0;
        if(v0 != null) {
            ViewGroup$LayoutParams v2 = ((View)v0).getLayoutParams();
            if((this.b) && v2 != null && v2.width != -2) {
                v1 = ((View)v0).getWidth();
            }

            if(v1 > 0) {
                return v1;
            }

            if(v2 == null) {
                return v1;
            }

            v1 = v2.width;
        }

        return v1;
    }

    protected abstract void a(Bitmap arg1, View arg2);

    protected abstract void a(Drawable arg1, View arg2);

    public boolean a(Bitmap arg4) {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            Object v0 = this.a.get();
            if(v0 != null) {
                this.a(arg4, ((View)v0));
                return 1;
            }
        }
        else {
            c.c("Can\'t set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        }

        return 0;
    }

    public boolean a(Drawable arg4) {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            Object v0 = this.a.get();
            if(v0 != null) {
                this.a(arg4, ((View)v0));
                return 1;
            }
        }
        else {
            c.c("Can\'t set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        }

        return 0;
    }

    public int b() {
        Object v0 = this.a.get();
        int v1 = 0;
        if(v0 != null) {
            ViewGroup$LayoutParams v2 = ((View)v0).getLayoutParams();
            if((this.b) && v2 != null && v2.height != -2) {
                v1 = ((View)v0).getHeight();
            }

            if(v1 > 0) {
                return v1;
            }

            if(v2 == null) {
                return v1;
            }

            v1 = v2.height;
        }

        return v1;
    }

    public h c() {
        return h.b;
    }

    public View d() {
        return this.a.get();
    }

    public boolean e() {
        boolean v0 = this.a.get() == null ? true : false;
        return v0;
    }

    public int f() {
        Object v0 = this.a.get();
        int v0_1 = v0 == null ? super.hashCode() : v0.hashCode();
        return v0_1;
    }
}

