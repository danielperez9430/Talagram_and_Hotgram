package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.v4.content.a.c$a;
import android.support.v4.d.b;
import android.support.v4.f.g;

public class c {
    private static final h a;
    private static final g b;

    static {
        d v0_3;
        if(Build$VERSION.SDK_INT >= 28) {
            android.support.v4.graphics.g v0 = new android.support.v4.graphics.g();
        }
        else if(Build$VERSION.SDK_INT >= 26) {
            f v0_1 = new f();
        }
        else {
            if(Build$VERSION.SDK_INT >= 24 && (e.a())) {
                e v0_2 = new e();
                goto label_5;
            }

            if(Build$VERSION.SDK_INT >= 21) {
                v0_3 = new d();
                goto label_5;
            }

            h v0_4 = new h();
        }

    label_5:
        c.a = ((h)v0_3);
        c.b = new g(16);
    }

    public static Typeface a(Resources arg1, int arg2, int arg3) {
        return c.b.get(c.b(arg1, arg2, arg3));
    }

    public static Typeface a(Context arg7, a arg8, Resources arg9, int arg10, int arg11, android.support.v4.content.a.f$a arg12, Handler arg13, boolean arg14) {
        Typeface v0_1;
        if((arg8 instanceof android.support.v4.content.a.c$d)) {
            a v0 = arg8;
            boolean v4 = false;
            if(arg14) {
                if(((android.support.v4.content.a.c$d)v0).b() != 0) {
                    goto label_12;
                }

                goto label_8;
            }
            else if(arg12 == null) {
            label_8:
                v4 = true;
            }

        label_12:
            int v5 = arg14 ? ((android.support.v4.content.a.c$d)v0).c() : -1;
            v0_1 = b.a(arg7, ((android.support.v4.content.a.c$d)v0).a(), arg12, arg13, v4, v5, arg11);
        }
        else {
            v0_1 = c.a.a(arg7, arg8, arg9, arg11);
            if(arg12 == null) {
                goto label_34;
            }

            if(v0_1 != null) {
                arg12.a(v0_1, arg13);
                goto label_34;
            }

            arg12.a(-3, arg13);
        }

    label_34:
        if(v0_1 != null) {
            c.b.put(c.b(arg9, arg10, arg11), v0_1);
        }

        return v0_1;
    }

    public static Typeface a(Context arg6, Resources arg7, int arg8, String arg9, int arg10) {
        Typeface v6 = c.a.a(arg6, arg7, arg8, arg9, arg10);
        if(v6 != null) {
            c.b.put(c.b(arg7, arg8, arg10), v6);
        }

        return v6;
    }

    public static Typeface a(Context arg1, CancellationSignal arg2, android.support.v4.d.b$b[] arg3, int arg4) {
        return c.a.a(arg1, arg2, arg3, arg4);
    }

    private static String b(Resources arg1, int arg2, int arg3) {
        return arg1.getResourcePackageName(arg2) + "-" + arg2 + "-" + arg3;
    }
}

