package android.support.v4.widget;

import android.os.Build$VERSION;

public interface b {
    public static final boolean a;

    static {
        boolean v0 = Build$VERSION.SDK_INT >= 27 ? true : false;
        b.a = v0;
    }
}

