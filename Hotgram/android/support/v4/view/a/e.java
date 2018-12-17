package android.support.v4.view.a;

import android.os.Build$VERSION;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;

public class e {
    private final AccessibilityRecord a;

    public static void a(AccessibilityRecord arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 15) {
            arg2.setMaxScrollX(arg3);
        }
    }

    public static void a(AccessibilityRecord arg2, View arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.setSource(arg3, arg4);
        }
    }

    public static void b(AccessibilityRecord arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 15) {
            arg2.setMaxScrollY(arg3);
        }
    }

    @Deprecated public boolean equals(Object arg5) {
        if(this == (((e)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(this.a == null) {
            if(((e)arg5).a != null) {
                return 0;
            }
        }
        else if(!this.a.equals(((e)arg5).a)) {
            return 0;
        }

        return 1;
    }

    @Deprecated public int hashCode() {
        int v0 = this.a == null ? 0 : this.a.hashCode();
        return v0;
    }
}

