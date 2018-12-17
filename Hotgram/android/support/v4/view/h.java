package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.os.Build$VERSION;
import android.util.Log;
import android.view.MenuItem;

public final class h {
    public static MenuItem a(MenuItem arg1, b arg2) {
        if((arg1 instanceof android.support.v4.a.a.b)) {
            return ((android.support.v4.a.a.b)arg1).a(arg2);
        }

        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return arg1;
    }

    public static void a(MenuItem arg2, char arg3, int arg4) {
        if((arg2 instanceof android.support.v4.a.a.b)) {
            ((android.support.v4.a.a.b)arg2).setNumericShortcut(arg3, arg4);
        }
        else if(Build$VERSION.SDK_INT >= 26) {
            arg2.setNumericShortcut(arg3, arg4);
        }
    }

    public static void a(MenuItem arg2, ColorStateList arg3) {
        if((arg2 instanceof android.support.v4.a.a.b)) {
            ((android.support.v4.a.a.b)arg2).setIconTintList(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 26) {
            arg2.setIconTintList(arg3);
        }
    }

    public static void a(MenuItem arg2, PorterDuff$Mode arg3) {
        if((arg2 instanceof android.support.v4.a.a.b)) {
            ((android.support.v4.a.a.b)arg2).setIconTintMode(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 26) {
            arg2.setIconTintMode(arg3);
        }
    }

    public static void a(MenuItem arg2, CharSequence arg3) {
        if((arg2 instanceof android.support.v4.a.a.b)) {
            ((android.support.v4.a.a.b)arg2).a(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 26) {
            arg2.setContentDescription(arg3);
        }
    }

    public static void b(MenuItem arg2, char arg3, int arg4) {
        if((arg2 instanceof android.support.v4.a.a.b)) {
            ((android.support.v4.a.a.b)arg2).setAlphabeticShortcut(arg3, arg4);
        }
        else if(Build$VERSION.SDK_INT >= 26) {
            arg2.setAlphabeticShortcut(arg3, arg4);
        }
    }

    public static void b(MenuItem arg2, CharSequence arg3) {
        if((arg2 instanceof android.support.v4.a.a.b)) {
            ((android.support.v4.a.a.b)arg2).b(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 26) {
            arg2.setTooltipText(arg3);
        }
    }
}

