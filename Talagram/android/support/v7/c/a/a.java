package android.support.v7.c.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v7.widget.k;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

public final class a {
    class android.support.v7.c.a.a$a {
        final ColorStateList a;
        final Configuration b;

        android.support.v7.c.a.a$a(ColorStateList arg1, Configuration arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    private static final ThreadLocal a;
    private static final WeakHashMap b;
    private static final Object c;

    static {
        a.a = new ThreadLocal();
        a.b = new WeakHashMap(0);
        a.c = new Object();
    }

    public static ColorStateList a(Context arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColorStateList(arg3);
        }

        ColorStateList v0 = a.d(arg2, arg3);
        if(v0 != null) {
            return v0;
        }

        v0 = a.c(arg2, arg3);
        if(v0 != null) {
            a.a(arg2, arg3, v0);
            return v0;
        }

        return android.support.v4.content.a.b(arg2, arg3);
    }

    private static void a(Context arg3, int arg4, ColorStateList arg5) {
        Object v0 = a.c;
        __monitor_enter(v0);
        try {
            Object v1 = a.b.get(arg3);
            if(v1 == null) {
                SparseArray v1_1 = new SparseArray();
                a.b.put(arg3, v1_1);
            }

            ((SparseArray)v1).append(arg4, new android.support.v7.c.a.a$a(arg5, arg3.getResources().getConfiguration()));
            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }

    private static TypedValue a() {
        TypedValue v0_1;
        Object v0 = a.a.get();
        if(v0 == null) {
            v0_1 = new TypedValue();
            a.a.set(v0_1);
        }

        return v0_1;
    }

    public static Drawable b(Context arg1, int arg2) {
        return k.a().a(arg1, arg2);
    }

    private static ColorStateList c(Context arg2, int arg3) {
        ColorStateList v1 = null;
        if(a.e(arg2, arg3)) {
            return v1;
        }

        Resources v0 = arg2.getResources();
        XmlResourceParser v3 = v0.getXml(arg3);
        try {
            return android.support.v4.content.a.a.a(v0, ((XmlPullParser)v3), arg2.getTheme());
        }
        catch(Exception v2) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", ((Throwable)v2));
            return v1;
        }
    }

    private static ColorStateList d(Context arg4, int arg5) {
        Object v0 = a.c;
        __monitor_enter(v0);
        try {
            Object v1 = a.b.get(arg4);
            if(v1 != null && ((SparseArray)v1).size() > 0) {
                Object v2 = ((SparseArray)v1).get(arg5);
                if(v2 != null) {
                    if(((android.support.v7.c.a.a$a)v2).b.equals(arg4.getResources().getConfiguration())) {
                        __monitor_exit(v0);
                        return ((android.support.v7.c.a.a$a)v2).a;
                    }
                    else {
                        ((SparseArray)v1).remove(arg5);
                    }
                }
            }

            __monitor_exit(v0);
            return null;
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_22;
        }

        throw v4;
    }

    private static boolean e(Context arg2, int arg3) {
        Resources v2 = arg2.getResources();
        TypedValue v0 = a.a();
        boolean v1 = true;
        v2.getValue(arg3, v0, true);
        if(v0.type < 28 || v0.type > 31) {
            v1 = false;
        }
        else {
        }

        return v1;
    }
}

