package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;

public class a {
    private static final Object a;
    private static TypedValue b;

    static {
        a.a = new Object();
    }

    public static boolean a(Context arg2, Intent[] arg3, Bundle arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.startActivities(arg3, arg4);
        }
        else {
            arg2.startActivities(arg3);
        }

        return 1;
    }

    public static File[] a(Context arg2, String arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getExternalFilesDirs(arg3);
        }

        return new File[]{arg2.getExternalFilesDir(arg3)};
    }

    public static File[] a(Context arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getExternalCacheDirs();
        }

        return new File[]{arg2.getExternalCacheDir()};
    }

    public static Drawable a(Context arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg4.getDrawable(arg5);
        }

        if(Build$VERSION.SDK_INT < 16) {
            Object v0 = a.a;
            __monitor_enter(v0);
            try {
                if(a.b == null) {
                    a.b = new TypedValue();
                }

                arg4.getResources().getValue(arg5, a.b, true);
                arg5 = a.b.resourceId;
                __monitor_exit(v0);
                goto label_8;
            label_27:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_27;
            }

            throw v4;
        }

    label_8:
        return arg4.getResources().getDrawable(arg5);
    }

    private static File a(File arg4) {
        Class v0 = a.class;
        __monitor_enter(v0);
        try {
            if(!arg4.exists() && !arg4.mkdirs()) {
                if(arg4.exists()) {
                    goto label_8;
                }
                else {
                    goto label_10;
                }
            }

            goto label_22;
        }
        catch(Throwable v4) {
            goto label_25;
        }

    label_8:
        __monitor_exit(v0);
        return arg4;
        try {
        label_10:
            Log.w("ContextCompat", "Unable to create files subdir " + arg4.getPath());
        }
        catch(Throwable v4) {
        label_25:
            __monitor_exit(v0);
            throw v4;
        }

        __monitor_exit(v0);
        return null;
    label_22:
        __monitor_exit(v0);
        return arg4;
    }

    public static void a(Context arg2, Intent arg3, Bundle arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.startActivity(arg3, arg4);
        }
        else {
            arg2.startActivity(arg3);
        }
    }

    public static int b(Context arg2, String arg3) {
        if(arg3 != null) {
            return arg2.checkPermission(arg3, Process.myPid(), Process.myUid());
        }

        throw new IllegalArgumentException("permission is null");
    }

    public static ColorStateList b(Context arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColorStateList(arg3);
        }

        return arg2.getResources().getColorStateList(arg3);
    }

    public static File b(Context arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getNoBackupFilesDir();
        }

        return a.a(new File(arg2.getApplicationInfo().dataDir, "no_backup"));
    }

    public static int c(Context arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.getColor(arg3);
        }

        return arg2.getResources().getColor(arg3);
    }

    public static boolean c(Context arg2) {
        if(Build$VERSION.SDK_INT >= 24) {
            return arg2.isDeviceProtectedStorage();
        }

        return 0;
    }
}

