package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.Build;
import android.os.Bundle;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import net.hockeyapp.android.e.c;
import net.hockeyapp.android.e.e;

public class a {
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    static String h;
    static CountDownLatch i;

    static {
        a.i = new CountDownLatch(1);
    }

    public static void a(Context arg1) {
        a.d = Build$VERSION.RELEASE;
        a.e = Build.DISPLAY;
        a.f = Build.MODEL;
        a.g = Build.MANUFACTURER;
        a.c(arg1);
        a.d(arg1);
    }

    private static int a(Context arg2, PackageManager arg3) {
        try {
            Bundle v2_1 = arg3.getApplicationInfo(arg2.getPackageName(), 128).metaData;
            if(v2_1 == null) {
                return 0;
            }

            return v2_1.getInt("buildNumber", 0);
        }
        catch(PackageManager$NameNotFoundException v2) {
            e.b("Exception thrown when accessing the application info", ((Throwable)v2));
        }

        return 0;
    }

    public static Future a() {
        if(a.i.getCount() == 0) {
            return new c(a.h);
        }

        return net.hockeyapp.android.e.a.a(new Callable() {
            public String a() {
                a.i.await();
                return a.h;
            }

            public Object call() {
                return this.a();
            }
        });
    }

    public static File b(Context arg2) {
        File v0 = new File(arg2.getExternalFilesDir(null), "HockeyApp");
        int v2 = (v0.exists()) || (v0.mkdirs()) ? 1 : 0;
        if(v2 == 0) {
            e.b("Couldn\'t create HockeyApp Storage dir");
        }

        return v0;
    }

    private static void c(Context arg4) {
        if(arg4 != null) {
            try {
                PackageManager v0 = arg4.getPackageManager();
                PackageInfo v1 = v0.getPackageInfo(arg4.getPackageName(), 0);
                a.c = v1.packageName;
                a.a = "" + v1.versionCode;
                a.b = v1.versionName;
                int v4_1 = a.a(arg4, v0);
                if(v4_1 == 0) {
                    return;
                }

                if(v4_1 <= v1.versionCode) {
                    return;
                }

                a.a = "" + v4_1;
            }
            catch(PackageManager$NameNotFoundException v4) {
                e.b("Exception thrown when accessing the package info", ((Throwable)v4));
            }
        }
    }

    @SuppressLint(value={"StaticFieldLeak"}) private static void d(Context arg1) {
        if(a.h != null) {
            return;
        }

        net.hockeyapp.android.e.a.a(new AsyncTask(arg1) {
            protected String a(Void[] arg3) {
                SharedPreferences v3 = this.a.getSharedPreferences("HockeyApp", 0);
                String v0 = v3.getString("deviceIdentifier", null);
                if(v0 == null) {
                    v0 = UUID.randomUUID().toString();
                    v3.edit().putString("deviceIdentifier", v0).apply();
                }

                return v0;
            }

            protected void a(String arg1) {
                a.h = arg1;
                a.i.countDown();
            }

            protected Object doInBackground(Object[] arg1) {
                return this.a(((Void[])arg1));
            }

            protected void onPostExecute(Object arg1) {
                this.a(((String)arg1));
            }
        });
    }
}

