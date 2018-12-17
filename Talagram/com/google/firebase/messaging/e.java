package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.w$c;
import android.support.v4.app.w$d;
import android.support.v4.content.a;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.iid.x;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class e {
    private static final AtomicInteger a;
    private final Context b;
    private Bundle c;

    static {
        e.a = new AtomicInteger(((int)SystemClock.elapsedRealtime()));
    }

    public e(Context arg1) {
        super();
        this.b = arg1.getApplicationContext();
    }

    private final Bundle a() {
        ApplicationInfo v0;
        if(this.c != null) {
            return this.c;
        }

        try {
            v0 = this.b.getPackageManager().getApplicationInfo(this.b.getPackageName(), 128);
            goto label_12;
        }
        catch(PackageManager$NameNotFoundException ) {
        label_12:
            if(v0 != null && v0.metaData != null) {
                this.c = v0.metaData;
                return this.c;
            }

            return Bundle.EMPTY;
        }
    }

    private final Integer a(String arg5) {
        Integer v1 = null;
        if(Build$VERSION.SDK_INT < 21) {
            return v1;
        }

        if(!TextUtils.isEmpty(((CharSequence)arg5))) {
            try {
                return Integer.valueOf(Color.parseColor(arg5));
            }
            catch(IllegalArgumentException ) {
                StringBuilder v3 = new StringBuilder(String.valueOf(arg5).length() + 54);
                v3.append("Color ");
                v3.append(arg5);
                v3.append(" not valid. Notification will use default color.");
                Log.w("FirebaseMessaging", v3.toString());
            }
        }

        int v5 = this.a().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if(v5 != 0) {
            try {
                return Integer.valueOf(a.c(this.b, v5));
            }
            catch(Resources$NotFoundException ) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            }
        }

        return v1;
    }

    static String a(Bundle arg2, String arg3) {
        String v0 = arg2.getString(arg3);
        if(v0 == null) {
            v0 = arg2.getString(arg3.replace("gcm.n.", "gcm.notification."));
        }

        return v0;
    }

    private static void a(Intent arg3, Bundle arg4) {
        Iterator v0 = arg4.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(!((String)v1).startsWith("google.c.a.") && !((String)v1).equals("from")) {
                continue;
            }

            arg3.putExtra(((String)v1), arg4.getString(((String)v1)));
        }
    }

    @TargetApi(value=26) private final boolean a(int arg5) {
        if(Build$VERSION.SDK_INT != 26) {
            return 1;
        }

        try {
            if(!(this.b.getResources().getDrawable(arg5, null) instanceof AdaptiveIconDrawable)) {
                return 1;
            }

            StringBuilder v3 = new StringBuilder(77);
            v3.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
            v3.append(arg5);
            Log.e("FirebaseMessaging", v3.toString());
            return 0;
        }
        catch(Resources$NotFoundException ) {
            return 0;
        }

        return 1;
    }

    static boolean a(Bundle arg2) {
        if(!"1".equals(e.a(arg2, "gcm.n.e"))) {
            if(e.a(arg2, "gcm.n.icon") != null) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    static Uri b(Bundle arg2) {
        String v0 = e.a(arg2, "gcm.n.link_android");
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            v0 = e.a(arg2, "gcm.n.link");
        }

        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            return Uri.parse(v0);
        }

        return null;
    }

    static String b(Bundle arg2, String arg3) {
        arg3 = String.valueOf(arg3);
        String v0 = String.valueOf("_loc_key");
        arg3 = v0.length() != 0 ? arg3.concat(v0) : new String(arg3);
        return e.a(arg2, arg3);
    }

    static Object[] c(Bundle arg5, String arg6) {
        String[] v2;
        String v0 = String.valueOf(arg6);
        String v1 = String.valueOf("_loc_args");
        v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
        String v5 = e.a(arg5, v0);
        Object[] v1_1 = null;
        if(TextUtils.isEmpty(((CharSequence)v5))) {
            return v1_1;
        }

        try {
            JSONArray v0_1 = new JSONArray(v5);
            v2 = new String[v0_1.length()];
            int v3;
            for(v3 = 0; v3 < v2.length; ++v3) {
                v2[v3] = v0_1.opt(v3);
            }
        }
        catch(JSONException ) {
            goto label_27;
        }

        return ((Object[])v2);
    label_27:
        v0 = "FirebaseMessaging";
        arg6 = String.valueOf(arg6);
        String v2_1 = String.valueOf("_loc_args");
        arg6 = v2_1.length() != 0 ? arg6.concat(v2_1) : new String(arg6);
        arg6 = arg6.substring(6);
        StringBuilder v3_1 = new StringBuilder(String.valueOf(arg6).length() + 41 + String.valueOf(v5).length());
        v3_1.append("Malformed ");
        v3_1.append(arg6);
        v3_1.append(": ");
        v3_1.append(v5);
        v3_1.append("  Default value will be used.");
        Log.w(v0, v3_1.toString());
        return v1_1;
    }

    final boolean c(Bundle arg17) {
        Notification v2_3;
        PendingIntent v9_4;
        PendingIntent v11_2;
        Parcelable v11_1;
        Intent v11;
        String v9_1;
        Uri v7_2;
        int v8;
        CharSequence v2_2;
        int v2;
        e v0 = this;
        Bundle v1 = arg17;
        if("1".equals(e.a(v1, "gcm.n.noui"))) {
            return 1;
        }

        if(!v0.b.getSystemService("keyguard").inKeyguardRestrictedInputMode()) {
            if(!PlatformVersion.isAtLeastLollipop()) {
                SystemClock.sleep(10);
            }

            v2 = Process.myPid();
            List v5 = v0.b.getSystemService("activity").getRunningAppProcesses();
            if(v5 == null) {
                goto label_36;
            }

            Iterator v5_1 = v5.iterator();
            do {
                if(!v5_1.hasNext()) {
                    goto label_36;
                }

                Object v6 = v5_1.next();
            }
            while(((ActivityManager$RunningAppProcessInfo)v6).pid != v2);

            if(((ActivityManager$RunningAppProcessInfo)v6).importance != 100) {
                goto label_36;
            }

            v2 = 1;
        }
        else {
        label_36:
            v2 = 0;
        }

        if(v2 != 0) {
            return 0;
        }

        String v2_1 = v0.d(v1, "gcm.n.title");
        if(TextUtils.isEmpty(((CharSequence)v2_1))) {
            v2_2 = v0.b.getApplicationInfo().loadLabel(v0.b.getPackageManager());
        }

        String v5_2 = v0.d(v1, "gcm.n.body");
        String v6_1 = e.a(v1, "gcm.n.icon");
        if(!TextUtils.isEmpty(((CharSequence)v6_1))) {
            Resources v7 = v0.b.getResources();
            v8 = v7.getIdentifier(v6_1, "drawable", v0.b.getPackageName());
            if(v8 != 0 && (v0.a(v8))) {
                goto label_102;
            }

            v8 = v7.getIdentifier(v6_1, "mipmap", v0.b.getPackageName());
            if(v8 != 0 && (v0.a(v8))) {
                goto label_102;
            }

            StringBuilder v9 = new StringBuilder(String.valueOf(v6_1).length() + 61);
            v9.append("Icon resource ");
            v9.append(v6_1);
            v9.append(" not found. Notification will use default icon.");
            Log.w("FirebaseMessaging", v9.toString());
            goto label_85;
        }
        else {
        label_85:
            int v6_2 = this.a().getInt("com.google.firebase.messaging.default_notification_icon", 0);
            if(v6_2 == 0 || !v0.a(v6_2)) {
                v6_2 = v0.b.getApplicationInfo().icon;
            }

            if(v6_2 != 0) {
                if(!v0.a(v6_2)) {
                }
                else {
                    v8 = v6_2;
                    goto label_102;
                }
            }

            v8 = 17301651;
        }

    label_102:
        Integer v6_3 = v0.a(e.a(v1, "gcm.n.color"));
        String v7_1 = e.d(arg17);
        String v10 = null;
        if(TextUtils.isEmpty(((CharSequence)v7_1))) {
            v7_2 = ((Uri)v10);
        }
        else {
            if(!"default".equals(v7_1) && v0.b.getResources().getIdentifier(v7_1, "raw", v0.b.getPackageName()) != 0) {
                v9_1 = v0.b.getPackageName();
                StringBuilder v12 = new StringBuilder(String.valueOf(v9_1).length() + 24 + String.valueOf(v7_1).length());
                v12.append("android.resource://");
                v12.append(v9_1);
                v12.append("/raw/");
                v12.append(v7_1);
                v7_2 = Uri.parse(v12.toString());
                goto label_142;
            }

            v7_2 = RingtoneManager.getDefaultUri(2);
        }

    label_142:
        v9_1 = e.a(v1, "gcm.n.click_action");
        if(!TextUtils.isEmpty(((CharSequence)v9_1))) {
            v11 = new Intent(v9_1);
            v11.setPackage(v0.b.getPackageName());
            v11.setFlags(268435456);
        }
        else {
            Uri v9_2 = e.b(arg17);
            if(v9_2 != null) {
                v11 = new Intent("android.intent.action.VIEW");
                v11.setPackage(v0.b.getPackageName());
                v11.setData(v9_2);
            }
            else {
                v11 = v0.b.getPackageManager().getLaunchIntentForPackage(v0.b.getPackageName());
                if(v11 == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }
            }
        }

        int v9_3 = 1073741824;
        if(v11 == null) {
            v11_1 = ((Parcelable)v10);
        }
        else {
            v11.addFlags(67108864);
            Bundle v12_1 = new Bundle(v1);
            FirebaseMessagingService.zzj(v12_1);
            v11.putExtras(v12_1);
            Iterator v12_2 = v12_1.keySet().iterator();
            while(v12_2.hasNext()) {
                Object v13 = v12_2.next();
                if(!((String)v13).startsWith("gcm.n.") && !((String)v13).startsWith("gcm.notification.")) {
                    continue;
                }

                v11.removeExtra(((String)v13));
            }

            v11_2 = PendingIntent.getActivity(v0.b, e.a.incrementAndGet(), v11, v9_3);
        }

        boolean v12_3 = v1 == null ? false : "1".equals(v1.getString("google.c.a.e"));
        if(v12_3) {
            Intent v12_4 = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
            e.a(v12_4, v1);
            v12_4.putExtra("pending_intent", v11_1);
            v11_2 = x.a(v0.b, e.a.incrementAndGet(), v12_4, v9_3);
            v12_4 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
            e.a(v12_4, v1);
            v9_4 = x.a(v0.b, e.a.incrementAndGet(), v12_4, v9_3);
        }
        else {
            v9_4 = ((PendingIntent)v10);
        }

        int v13_1 = 3;
        if(!PlatformVersion.isAtLeastO() || v0.b.getApplicationInfo().targetSdkVersion <= 25) {
            d v4_3 = new d(v0.b).b(true).a(v8);
            if(!TextUtils.isEmpty(v2_2)) {
                v4_3.a(v2_2);
            }

            if(!TextUtils.isEmpty(((CharSequence)v5_2))) {
                v4_3.b(((CharSequence)v5_2));
                v4_3.a(new c().a(((CharSequence)v5_2)));
            }

            if(v6_3 != null) {
                v4_3.e(v6_3.intValue());
            }

            if(v7_2 != null) {
                v4_3.a(v7_2);
            }

            if((((PendingIntent)v11_1)) != null) {
                v4_3.a(((PendingIntent)v11_1));
            }

            if(v9_4 != null) {
                v4_3.b(v9_4);
            }

            v2_3 = v4_3.b();
        }
        else {
            String v12_5 = e.a(v1, "gcm.n.android_channel_id");
            if(!PlatformVersion.isAtLeastO()) {
            }
            else {
                Object v10_1 = v0.b.getSystemService(NotificationManager.class);
                if(!TextUtils.isEmpty(((CharSequence)v12_5))) {
                    if(((NotificationManager)v10_1).getNotificationChannel(v12_5) != null) {
                        v10 = v12_5;
                        goto label_289;
                    }
                    else {
                        StringBuilder v4 = new StringBuilder(String.valueOf(v12_5).length() + 122);
                        v4.append("Notification Channel requested (");
                        v4.append(v12_5);
                        v4.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                        Log.w("FirebaseMessaging", v4.toString());
                    }
                }

                String v4_1 = this.a().getString("com.google.firebase.messaging.default_notification_channel_id");
                if(TextUtils.isEmpty(((CharSequence)v4_1))) {
                    v4_1 = "FirebaseMessaging";
                    v12_5 = "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.";
                }
                else if(((NotificationManager)v10_1).getNotificationChannel(v4_1) != null) {
                    v10 = v4_1;
                    goto label_289;
                }
                else {
                    v4_1 = "FirebaseMessaging";
                    v12_5 = "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.";
                }

                Log.w(v4_1, v12_5);
                if(((NotificationManager)v10_1).getNotificationChannel("fcm_fallback_notification_channel") == null) {
                    ((NotificationManager)v10_1).createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", v0.b.getString(com.google.firebase.messaging.c$a.fcm_fallback_notification_channel_label), v13_1));
                }

                v10 = "fcm_fallback_notification_channel";
            }

        label_289:
            Notification$Builder v4_2 = new Notification$Builder(v0.b).setAutoCancel(true).setSmallIcon(v8);
            if(!TextUtils.isEmpty(v2_2)) {
                v4_2.setContentTitle(v2_2);
            }

            if(!TextUtils.isEmpty(((CharSequence)v5_2))) {
                v4_2.setContentText(((CharSequence)v5_2));
                v4_2.setStyle(new Notification$BigTextStyle().bigText(((CharSequence)v5_2)));
            }

            if(v6_3 != null) {
                v4_2.setColor(v6_3.intValue());
            }

            if(v7_2 != null) {
                v4_2.setSound(v7_2);
            }

            if((((PendingIntent)v11_1)) != null) {
                v4_2.setContentIntent(((PendingIntent)v11_1));
            }

            if(v9_4 != null) {
                v4_2.setDeleteIntent(v9_4);
            }

            if(v10 != null) {
                v4_2.setChannelId(v10);
            }

            v2_3 = v4_2.build();
        }

        String v1_1 = e.a(v1, "gcm.n.tag");
        if(Log.isLoggable("FirebaseMessaging", v13_1)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }

        Object v4_4 = v0.b.getSystemService("notification");
        if(TextUtils.isEmpty(((CharSequence)v1_1))) {
            long v5_3 = SystemClock.uptimeMillis();
            StringBuilder v7_3 = new StringBuilder(37);
            v7_3.append("FCM-Notification:");
            v7_3.append(v5_3);
            v1_1 = v7_3.toString();
        }

        ((NotificationManager)v4_4).notify(v1_1, 0, v2_3);
        return 1;
    }

    static String d(Bundle arg2) {
        String v0 = e.a(arg2, "gcm.n.sound2");
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            v0 = e.a(arg2, "gcm.n.sound");
        }

        return v0;
    }

    private final String d(Bundle arg6, String arg7) {
        String v6;
        String v0 = e.a(arg6, arg7);
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            return v0;
        }

        v0 = e.b(arg6, arg7);
        String v2 = null;
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            return v2;
        }

        Resources v1 = this.b.getResources();
        int v3 = v1.getIdentifier(v0, "string", this.b.getPackageName());
        if(v3 == 0) {
            v6 = "FirebaseMessaging";
            arg7 = String.valueOf(arg7);
            String v1_1 = String.valueOf("_loc_key");
            arg7 = v1_1.length() != 0 ? arg7.concat(v1_1) : new String(arg7);
            arg7 = arg7.substring(6);
            StringBuilder v3_1 = new StringBuilder(String.valueOf(arg7).length() + 49 + String.valueOf(v0).length());
            v3_1.append(arg7);
            v3_1.append(" resource not found: ");
            v3_1.append(v0);
            v3_1.append(" Default value will be used.");
            Log.w(v6, v3_1.toString());
            return v2;
        }

        Object[] v6_1 = e.c(arg6, arg7);
        if(v6_1 == null) {
            return v1.getString(v3);
        }

        try {
            return v1.getString(v3, v6_1);
        }
        catch(MissingFormatArgumentException v7) {
            v6 = Arrays.toString(v6_1);
            StringBuilder v4 = new StringBuilder(String.valueOf(v0).length() + 58 + String.valueOf(v6).length());
            v4.append("Missing format argument for ");
            v4.append(v0);
            v4.append(": ");
            v4.append(v6);
            v4.append(" Default value will be used.");
            Log.w("FirebaseMessaging", v4.toString(), ((Throwable)v7));
            return v2;
        }
    }
}

