package android.support.f;

import android.os.Build$VERSION;
import android.view.ViewGroup;

class x {
    static w a(ViewGroup arg2) {
        if(Build$VERSION.SDK_INT >= 18) {
            return new v(arg2);
        }

        return u.a(arg2);
    }

    static void a(ViewGroup arg2, boolean arg3) {
        if(Build$VERSION.SDK_INT >= 18) {
            z.a(arg2, arg3);
        }
        else {
            y.a(arg2, arg3);
        }
    }
}

