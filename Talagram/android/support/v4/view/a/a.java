package android.support.v4.view.a;

import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;

public final class a {
    public static int a(AccessibilityEvent arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getContentChangeTypes();
        }

        return 0;
    }

    public static void a(AccessibilityEvent arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            arg2.setContentChangeTypes(arg3);
        }
    }
}

