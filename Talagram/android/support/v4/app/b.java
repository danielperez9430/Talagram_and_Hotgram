package android.support.v4.app;

import android.app.ActivityManager;
import android.os.Build$VERSION;

public final class b {
    public static boolean a(ActivityManager arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.isLowRamDevice();
        }

        return 0;
    }
}

