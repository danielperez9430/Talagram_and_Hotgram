package android.support.v4.view;

import android.os.Build$VERSION;
import android.view.ViewGroup$MarginLayoutParams;

public final class g {
    public static void a(ViewGroup$MarginLayoutParams arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setMarginEnd(arg3);
        }
        else {
            arg2.rightMargin = arg3;
        }
    }

    public static int a(ViewGroup$MarginLayoutParams arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getMarginStart();
        }

        return arg2.leftMargin;
    }

    public static int b(ViewGroup$MarginLayoutParams arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getMarginEnd();
        }

        return arg2.rightMargin;
    }
}

