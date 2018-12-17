package android.support.v4.widget;

import android.os.Build$VERSION;
import android.widget.EdgeEffect;

public final class i {
    private EdgeEffect a;

    public static void a(EdgeEffect arg2, float arg3, float arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.onPull(arg3, arg4);
        }
        else {
            arg2.onPull(arg3);
        }
    }
}

