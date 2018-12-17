package android.support.v4.e;

import android.os.Build$VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class b {
    private static Method a;
    private static Method b;

    static {
        if(Build$VERSION.SDK_INT < 21) {
            goto label_18;
        }

        try {
            b.b = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
            return;
        }
        catch(Exception v0) {
            throw new IllegalStateException(((Throwable)v0));
        }

        try {
        label_18:
            Class v0_1 = Class.forName("libcore.icu.ICU");
            if(v0_1 == null) {
                return;
            }

            b.a = v0_1.getMethod("getScript", String.class);
            b.b = v0_1.getMethod("addLikelySubtags", String.class);
        }
        catch(Exception v0) {
            b.a = null;
            b.b = null;
            Log.w("ICUCompat", ((Throwable)v0));
        }
    }

    private static String a(String arg3) {
        Object v0 = null;
        try {
            if(b.a == null) {
                goto label_13;
            }

            return b.a.invoke(v0, arg3);
        }
        catch(InvocationTargetException v3) {
            Log.w("ICUCompat", ((Throwable)v3));
        }

    label_13:
        return ((String)v0);
    }

    public static String a(Locale arg3) {
        Object v1 = null;
        if(Build$VERSION.SDK_INT >= 21) {
            try {
                return b.b.invoke(v1, arg3).getScript();
            }
            catch(IllegalAccessException v0) {
                Log.w("ICUCompat", ((Throwable)v0));
                return arg3.getScript();
            }
        }

        String v3 = b.b(arg3);
        if(v3 != null) {
            return b.a(v3);
        }

        return ((String)v1);
    }

    private static String b(Locale arg3) {
        String v3 = arg3.toString();
        try {
            if(b.b == null) {
                return v3;
            }

            return b.b.invoke(null, v3);
        }
        catch(InvocationTargetException v0) {
            Log.w("ICUCompat", ((Throwable)v0));
        }

        return v3;
    }
}

