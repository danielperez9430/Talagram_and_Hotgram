package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build$VERSION;

public final class d {
    public static int a(Context arg2, String arg3, String arg4) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getSystemService(AppOpsManager.class).noteProxyOpNoThrow(arg3, arg4);
        }

        return 1;
    }

    public static String a(String arg2) {
        if(Build$VERSION.SDK_INT >= 23) {
            return AppOpsManager.permissionToOp(arg2);
        }

        return null;
    }
}

