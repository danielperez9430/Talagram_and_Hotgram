package android.support.v7.widget;

import android.os.Build$VERSION;
import android.view.View;

public class bm {
    public static void a(View arg2, CharSequence arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            arg2.setTooltipText(arg3);
        }
        else {
            bn.a(arg2, arg3);
        }
    }
}

