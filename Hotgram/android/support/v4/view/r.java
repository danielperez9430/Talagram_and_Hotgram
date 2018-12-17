package android.support.v4.view;

import android.content.Context;
import android.os.Build$VERSION;
import android.view.PointerIcon;

public final class r {
    private Object a;

    private r(Object arg1) {
        super();
        this.a = arg1;
    }

    public static r a(Context arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 24) {
            return new r(PointerIcon.getSystemIcon(arg2, arg3));
        }

        return new r(null);
    }

    public Object a() {
        return this.a;
    }
}

