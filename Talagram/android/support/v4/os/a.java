package android.support.v4.os;

import android.os.Build$VERSION;

public class a {
    @Deprecated public static boolean a() {
        boolean v0 = Build$VERSION.SDK_INT >= 25 ? true : false;
        return v0;
    }

    @Deprecated public static boolean b() {
        boolean v0 = Build$VERSION.SDK_INT >= 28 ? true : false;
        return v0;
    }
}

