package com.d.a.b.e;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.d.a.b.a.h;
import com.d.a.c.c;
import java.lang.reflect.Field;

public class b extends d {
    public b(ImageView arg1) {
        super(((View)arg1));
    }

    private static int a(Object arg2, String arg3) {
        int v2_1;
        int v0 = 0;
        try {
            Field v3 = ImageView.class.getDeclaredField(arg3);
            v3.setAccessible(true);
            v2_1 = v3.get(arg2).intValue();
            if(v2_1 <= 0) {
                return v0;
            }
        }
        catch(Exception v2) {
            c.a(((Throwable)v2));
            return v0;
        }

        if(v2_1 < 2147483647) {
            v0 = v2_1;
        }

        return v0;
    }

    public int a() {
        int v0 = super.a();
        if(v0 <= 0) {
            Object v1 = this.a.get();
            if(v1 != null) {
                v0 = b.a(v1, "mMaxWidth");
            }
        }

        return v0;
    }

    protected void a(Bitmap arg1, View arg2) {
        ((ImageView)arg2).setImageBitmap(arg1);
    }

    protected void a(Drawable arg1, View arg2) {
        ((ImageView)arg2).setImageDrawable(arg1);
        if((arg1 instanceof AnimationDrawable)) {
            ((AnimationDrawable)arg1).start();
        }
    }

    public int b() {
        int v0 = super.b();
        if(v0 <= 0) {
            Object v1 = this.a.get();
            if(v1 != null) {
                v0 = b.a(v1, "mMaxHeight");
            }
        }

        return v0;
    }

    public h c() {
        Object v0 = this.a.get();
        if(v0 != null) {
            return h.a(((ImageView)v0));
        }

        return super.c();
    }

    public View d() {
        return this.g();
    }

    public ImageView g() {
        return super.d();
    }
}

