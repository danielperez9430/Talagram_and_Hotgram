package android.support.v4.view;

import android.view.MotionEvent;

public final class i {
    @Deprecated public static int a(MotionEvent arg0) {
        return arg0.getActionMasked();
    }

    public static boolean a(MotionEvent arg0, int arg1) {
        boolean v0 = (arg0.getSource() & arg1) == arg1 ? true : false;
        return v0;
    }
}

