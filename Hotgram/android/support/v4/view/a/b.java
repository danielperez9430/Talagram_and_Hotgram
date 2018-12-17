package android.support.v4.view.a;

import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityManager$TouchExplorationStateChangeListener;
import android.view.accessibility.AccessibilityManager;

public final class b {
    public interface a {
        void a(boolean arg1);
    }

    class android.support.v4.view.a.b$b implements AccessibilityManager$TouchExplorationStateChangeListener {
        final a a;

        android.support.v4.view.a.b$b(a arg1) {
            super();
            this.a = arg1;
        }

        public boolean equals(Object arg3) {
            if(this == (((android.support.v4.view.a.b$b)arg3))) {
                return 1;
            }

            if(arg3 != null) {
                if(this.getClass() != arg3.getClass()) {
                }
                else {
                    return this.a.equals(((android.support.v4.view.a.b$b)arg3).a);
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public void onTouchExplorationStateChanged(boolean arg2) {
            this.a.a(arg2);
        }
    }

    public static boolean a(AccessibilityManager arg3, a arg4) {
        if(Build$VERSION.SDK_INT >= 19) {
            if(arg4 == null) {
                return 0;
            }

            return arg3.addTouchExplorationStateChangeListener(new android.support.v4.view.a.b$b(arg4));
        }

        return 0;
    }

    public static boolean b(AccessibilityManager arg3, a arg4) {
        if(Build$VERSION.SDK_INT >= 19) {
            if(arg4 == null) {
                return 0;
            }

            return arg3.removeTouchExplorationStateChangeListener(new android.support.v4.view.a.b$b(arg4));
        }

        return 0;
    }
}

