package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import java.lang.ref.WeakReference;

public class bp extends Resources {
    private static boolean a = false;
    private final WeakReference b;

    static {
    }

    public bp(Context arg3, Resources arg4) {
        super(arg4.getAssets(), arg4.getDisplayMetrics(), arg4.getConfiguration());
        this.b = new WeakReference(arg3);
    }

    public static void a(boolean arg0) {
        bp.a = arg0;
    }

    public static boolean a() {
        boolean v0 = !bp.b() || Build$VERSION.SDK_INT > 20 ? false : true;
        return v0;
    }

    final Drawable a(int arg1) {
        return super.getDrawable(arg1);
    }

    public static boolean b() {
        return bp.a;
    }

    public Drawable getDrawable(int arg3) {
        Object v0 = this.b.get();
        if(v0 != null) {
            return k.a().a(((Context)v0), this, arg3);
        }

        return super.getDrawable(arg3);
    }
}

