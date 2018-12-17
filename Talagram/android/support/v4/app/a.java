package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class a extends android.support.v4.content.a {
    public interface android.support.v4.app.a$a {
        void onRequestPermissionsResult(int arg1, String[] arg2, int[] arg3);
    }

    public interface b {
        boolean a(Activity arg1, String[] arg2, int arg3);

        boolean a(Activity arg1, int arg2, int arg3, Intent arg4);
    }

    public interface c {
        void a(int arg1);
    }

    private static b a;

    public static b a() {
        return a.a;
    }

    public static void a(Activity arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.finishAffinity();
        }
        else {
            arg2.finish();
        }
    }

    public static void a(Activity arg2, Intent arg3, int arg4, Bundle arg5) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.startActivityForResult(arg3, arg4, arg5);
        }
        else {
            arg2.startActivityForResult(arg3, arg4);
        }
    }

    public static void a(Activity arg2, IntentSender arg3, int arg4, Intent arg5, int arg6, int arg7, int arg8, Bundle arg9) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.startIntentSenderForResult(arg3, arg4, arg5, arg6, arg7, arg8, arg9);
        }
        else {
            arg2.startIntentSenderForResult(arg3, arg4, arg5, arg6, arg7, arg8);
        }
    }

    public static void a(Activity arg2, String[] arg3, int arg4) {
        if(a.a != null && (a.a.a(arg2, arg3, arg4))) {
            return;
        }

        if(Build$VERSION.SDK_INT >= 23) {
            if((arg2 instanceof c)) {
                arg2.a(arg4);
            }

            arg2.requestPermissions(arg3, arg4);
        }
        else {
            if(!(arg2 instanceof android.support.v4.app.a$a)) {
                return;
            }

            new Handler(Looper.getMainLooper()).post(new Runnable(arg3, arg2, arg4) {
                public void run() {
                    int[] v0 = new int[this.a.length];
                    PackageManager v1 = this.b.getPackageManager();
                    String v2 = this.b.getPackageName();
                    int v3 = this.a.length;
                    int v4;
                    for(v4 = 0; v4 < v3; ++v4) {
                        v0[v4] = v1.checkPermission(this.a[v4], v2);
                    }

                    this.b.onRequestPermissionsResult(this.c, this.a, v0);
                }
            });
        }
    }

    public static boolean a(Activity arg2, String arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            return arg2.shouldShowRequestPermissionRationale(arg3);
        }

        return 0;
    }
}

