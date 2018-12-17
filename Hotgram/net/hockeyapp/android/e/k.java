package net.hockeyapp.android.e;

import android.app.Notification$Builder;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import net.hockeyapp.android.f$d;

public class k {
    final class net.hockeyapp.android.e.k$1 extends ThreadLocal {
        net.hockeyapp.android.e.k$1() {
            super();
        }

        protected DateFormat a() {
            SimpleDateFormat v0 = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.US);
            ((DateFormat)v0).setTimeZone(TimeZone.getTimeZone("UTC"));
            return ((DateFormat)v0);
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    private static final Pattern a;
    private static final ThreadLocal b;

    static {
        k.a = Pattern.compile("[0-9a-f]+", 2);
        k.b = new net.hockeyapp.android.e.k$1();
    }

    public static boolean a(Context arg2) {
        boolean v0 = false;
        try {
            Object v2_1 = arg2.getApplicationContext().getSystemService("connectivity");
            if(v2_1 == null) {
                return 0;
            }

            NetworkInfo v2_2 = ((ConnectivityManager)v2_1).getActiveNetworkInfo();
            if(v2_2 != null) {
                if(!v2_2.isConnected()) {
                    return v0;
                }

                return true;
            }

            return v0;
        }
        catch(Exception v2) {
            e.b("Exception thrown when check network is connected", ((Throwable)v2));
            return 0;
        }

        return true;
    }

    public static void a(View arg3, CharSequence arg4) {
        Object v0 = arg3.getContext().getSystemService("accessibility");
        if(!((AccessibilityManager)v0).isEnabled()) {
            return;
        }

        int v1 = Build$VERSION.SDK_INT < 16 ? 8 : 16384;
        AccessibilityEvent v1_1 = AccessibilityEvent.obtain(v1);
        v1_1.getText().add(arg4);
        v1_1.setSource(arg3);
        v1_1.setEnabled(arg3.isEnabled());
        v1_1.setClassName(arg3.getClass().getName());
        v1_1.setPackageName(arg3.getContext().getPackageName());
        ((AccessibilityManager)v0).sendAccessibilityEvent(v1_1);
    }

    public static void a(Context arg1, int arg2) {
        arg1.getSystemService("notification").cancel(arg2);
    }

    public static String a(byte[] arg6) {
        StringBuilder v0 = new StringBuilder();
        int v1 = arg6.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            String v3;
            for(v3 = Integer.toHexString(arg6[v2] & 255); v3.length() < 2; v3 = "0" + v3) {
            }

            v0.append(v3);
        }

        return v0.toString();
    }

    public static byte[] a(byte[] arg0, String arg1) {
        MessageDigest v1 = MessageDigest.getInstance(arg1);
        v1.update(arg0);
        return v1.digest();
    }

    public static String a(InputStream arg3) {
        BufferedReader v0 = new BufferedReader(new InputStreamReader(arg3), 1024);
        StringBuilder v1 = new StringBuilder();
        try {
            while(true) {
                String v2 = v0.readLine();
                if(v2 == null) {
                    goto label_13;
                }

                v1.append(v2);
                v1.append('\n');
            }
        }
        catch(Throwable v0_1) {
        label_16:
            try {
                arg3.close();
                goto label_24;
            }
            catch(IOException ) {
            label_24:
                throw v0_1;
            }
        }
        catch(IOException v0_2) {
            try {
                e.b("Failed to convert stream to string", ((Throwable)v0_2));
            }
            catch(Throwable v0_1) {
                goto label_16;
            }

            try {
            label_13:
                arg3.close();
            }
            catch(IOException ) {
            }
        }

        return v1.toString();
    }

    public static void a(Context arg2, int arg3, Notification arg4, String arg5, CharSequence arg6) {
        Object v2 = arg2.getSystemService("notification");
        if(Build$VERSION.SDK_INT >= 26) {
            ((NotificationManager)v2).createNotificationChannel(new NotificationChannel(arg5, arg6, 3));
        }

        ((NotificationManager)v2).notify(arg3, arg4);
    }

    public static Notification a(Context arg2, PendingIntent arg3, String arg4, String arg5, int arg6, String arg7) {
        Notification$Builder v0 = Build$VERSION.SDK_INT >= 26 ? new Notification$Builder(arg2, arg7) : new Notification$Builder(arg2);
        v0.setContentTitle(((CharSequence)arg4)).setContentText(((CharSequence)arg5)).setContentIntent(arg3).setSmallIcon(arg6);
        if(Build$VERSION.SDK_INT >= 16) {
            return v0.build();
        }

        return v0.getNotification();
    }

    public static String a(String arg3) {
        try {
            return URLEncoder.encode(arg3, "UTF-8");
        }
        catch(UnsupportedEncodingException v0) {
            e.b("Failed to encode param " + arg3, ((Throwable)v0));
            return "";
        }
    }

    public static String b(Context arg3) {
        String v3_1;
        ApplicationInfo v1;
        PackageManager v0 = arg3.getPackageManager();
        try {
            v1 = v0.getApplicationInfo(arg3.getApplicationInfo().packageName, 0);
        }
        catch(PackageManager$NameNotFoundException ) {
            v1 = null;
        }

        if(v1 != null) {
            CharSequence v3 = v0.getApplicationLabel(v1);
        }
        else {
            v3_1 = arg3.getString(d.hockeyapp_crash_dialog_app_name_fallback);
        }

        return v3_1;
    }

    public static boolean b(String arg1) {
        boolean v1 = (TextUtils.isEmpty(((CharSequence)arg1))) || !Patterns.EMAIL_ADDRESS.matcher(((CharSequence)arg1)).matches() ? false : true;
        return v1;
    }
}

