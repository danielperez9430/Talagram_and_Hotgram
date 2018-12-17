package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.a$k;
import android.support.v7.widget.bk;
import android.util.AttributeSet;

public final class b {
    private static final int[] a;
    private static final int[] b;

    static {
        b.a = new int[]{android.support.design.a$b.colorPrimary};
        b.b = new int[]{android.support.design.a$b.colorSecondary};
    }

    public static TypedArray a(Context arg0, AttributeSet arg1, int[] arg2, int arg3, int arg4, int[] arg5) {
        b.a(arg0, arg1, arg3, arg4);
        b.c(arg0, arg1, arg2, arg3, arg4, arg5);
        return arg0.obtainStyledAttributes(arg1, arg2, arg3, arg4);
    }

    private static void a(Context arg1, AttributeSet arg2, int arg3, int arg4) {
        TypedArray v2 = arg1.obtainStyledAttributes(arg2, k.ThemeEnforcement, arg3, arg4);
        boolean v3 = v2.getBoolean(k.ThemeEnforcement_enforceMaterialTheme, false);
        v2.recycle();
        if(v3) {
            b.b(arg1);
        }

        b.a(arg1);
    }

    public static void a(Context arg2) {
        b.a(arg2, b.a, "Theme.AppCompat");
    }

    private static void a(Context arg1, int[] arg2, String arg3) {
        if(b.a(arg1, arg2)) {
            return;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append("The style on this component requires your app theme to be ");
        v2.append(arg3);
        v2.append(" (or a descendant).");
        throw new IllegalArgumentException(v2.toString());
    }

    private static boolean a(Context arg0, int[] arg1) {
        TypedArray v0 = arg0.obtainStyledAttributes(arg1);
        boolean v1 = v0.hasValue(0);
        v0.recycle();
        return v1;
    }

    public static void b(Context arg2) {
        b.a(arg2, b.b, "Theme.MaterialComponents");
    }

    public static bk b(Context arg0, AttributeSet arg1, int[] arg2, int arg3, int arg4, int[] arg5) {
        b.a(arg0, arg1, arg3, arg4);
        b.c(arg0, arg1, arg2, arg3, arg4, arg5);
        return bk.a(arg0, arg1, arg2, arg3, arg4);
    }

    private static void c(Context arg3, AttributeSet arg4, int[] arg5, int arg6, int arg7, int[] arg8) {
        boolean v3;
        TypedArray v0 = arg3.obtainStyledAttributes(arg4, k.ThemeEnforcement, arg6, arg7);
        if(!v0.getBoolean(k.ThemeEnforcement_enforceTextAppearance, false)) {
            v0.recycle();
            return;
        }

        if(arg8 != null && arg8.length != 0) {
            v3 = b.d(arg3, arg4, arg5, arg6, arg7, arg8);
        }
        else if(v0.getResourceId(k.ThemeEnforcement_android_textAppearance, -1) != -1) {
            v3 = true;
        }
        else {
            v3 = false;
        }

        v0.recycle();
        if(v3) {
            return;
        }

        throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
    }

    private static boolean d(Context arg1, AttributeSet arg2, int[] arg3, int arg4, int arg5, int[] arg6) {
        TypedArray v1 = arg1.obtainStyledAttributes(arg2, arg3, arg4, arg5);
        int v2 = arg6.length;
        for(arg4 = 0; arg4 < v2; ++arg4) {
            if(v1.getResourceId(arg6[arg4], -1) == -1) {
                v1.recycle();
                return 0;
            }
        }

        v1.recycle();
        return 1;
    }
}

