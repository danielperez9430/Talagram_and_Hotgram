package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

class bj extends ax {
    private final WeakReference a;

    public bj(Context arg1, Resources arg2) {
        super(arg2);
        this.a = new WeakReference(arg1);
    }

    public Drawable getDrawable(int arg3) {
        Drawable v0 = super.getDrawable(arg3);
        Object v1 = this.a.get();
        if(v0 != null && v1 != null) {
            k.a();
            k.a(((Context)v1), arg3, v0);
        }

        return v0;
    }
}

