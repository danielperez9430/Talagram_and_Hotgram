package android.support.v4.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;

public final class d {
    public static int a(Context arg3, String arg4) {
        return d.a(arg3, arg4, Process.myPid(), Process.myUid(), arg3.getPackageName());
    }

    public static int a(Context arg1, String arg2, int arg3, int arg4, String arg5) {
        int v0 = -1;
        if(arg1.checkPermission(arg2, arg3, arg4) == v0) {
            return v0;
        }

        arg2 = android.support.v4.app.d.a(arg2);
        if(arg2 == null) {
            return 0;
        }

        if(arg5 == null) {
            String[] v4 = arg1.getPackageManager().getPackagesForUid(arg4);
            if(v4 != null) {
                if(v4.length <= 0) {
                }
                else {
                    arg5 = v4[0];
                    goto label_18;
                }
            }

            return v0;
        }

    label_18:
        if(android.support.v4.app.d.a(arg1, arg2, arg5) != 0) {
            return -2;
        }

        return 0;
    }

    public static int a(Context arg2, String arg3, String arg4) {
        if(Binder.getCallingPid() == Process.myPid()) {
            return -1;
        }

        return d.a(arg2, arg3, Binder.getCallingPid(), Binder.getCallingUid(), arg4);
    }

    public static int b(Context arg3, String arg4) {
        String v0 = Binder.getCallingPid() == Process.myPid() ? arg3.getPackageName() : null;
        return d.a(arg3, arg4, Binder.getCallingPid(), Binder.getCallingUid(), v0);
    }
}

