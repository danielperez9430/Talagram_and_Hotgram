package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.analytics.connector.a;

@KeepForSdk public class b {
    @KeepForSdk public static void a(Intent arg1) {
        b.a("_nr", arg1);
    }

    private static void a(String arg4, Intent arg5) {
        Bundle v0 = new Bundle();
        String v1 = arg5.getStringExtra("google.c.a.c_id");
        if(v1 != null) {
            v0.putString("_nmid", v1);
        }

        v1 = arg5.getStringExtra("google.c.a.c_l");
        if(v1 != null) {
            v0.putString("_nmn", v1);
        }

        v1 = arg5.getStringExtra("google.c.a.m_l");
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            v0.putString("label", v1);
        }

        v1 = arg5.getStringExtra("google.c.a.m_c");
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            v0.putString("message_channel", v1);
        }

        v1 = arg5.getStringExtra("from");
        if(v1 == null || !v1.startsWith("/topics/")) {
            v1 = null;
        }
        else {
        }

        if(v1 != null) {
            v0.putString("_nt", v1);
        }

        if(arg5.hasExtra("google.c.a.ts")) {
            try {
                v0.putInt("_nmt", Integer.parseInt(arg5.getStringExtra("google.c.a.ts")));
            }
            catch(NumberFormatException v1_1) {
                Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", ((Throwable)v1_1));
            }
        }

        if(arg5.hasExtra("google.c.a.udt")) {
            try {
                v0.putInt("_ndt", Integer.parseInt(arg5.getStringExtra("google.c.a.udt")));
            }
            catch(NumberFormatException v5) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", ((Throwable)v5));
            }
        }

        if(Log.isLoggable("FirebaseMessaging", 3)) {
            v1 = String.valueOf(v0);
            StringBuilder v3 = new StringBuilder(String.valueOf(arg4).length() + 22 + String.valueOf(v1).length());
            v3.append("Sending event=");
            v3.append(arg4);
            v3.append(" params=");
            v3.append(v1);
            Log.d("FirebaseMessaging", v3.toString());
        }

        Object v5_1 = com.google.firebase.b.d().a(a.class);
        if(v5_1 != null) {
            ((a)v5_1).a("fcm", arg4, v0);
            return;
        }

        Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
    }

    @KeepForSdk public static void b(Intent arg5) {
        if(arg5 != null) {
            int v1 = 3;
            if("1".equals(arg5.getStringExtra("google.c.a.tc"))) {
                Object v0 = com.google.firebase.b.d().a(a.class);
                if(Log.isLoggable("FirebaseMessaging", v1)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }

                if(v0 != null) {
                    String v1_1 = arg5.getStringExtra("google.c.a.c_id");
                    ((a)v0).a("fcm", "_ln", v1_1);
                    Bundle v2 = new Bundle();
                    v2.putString("source", "Firebase");
                    v2.putString("medium", "notification");
                    v2.putString("campaign", v1_1);
                    ((a)v0).a("fcm", "_cmp", v2);
                    goto label_46;
                }

                Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
            }
            else {
                if(!Log.isLoggable("FirebaseMessaging", v1)) {
                    goto label_46;
                }

                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }

    label_46:
        b.a("_no", arg5);
    }

    @KeepForSdk public static void c(Intent arg1) {
        b.a("_nd", arg1);
    }

    @KeepForSdk public static void d(Intent arg1) {
        b.a("_nf", arg1);
    }

    @KeepForSdk public static boolean e(Intent arg2) {
        if(arg2 == null) {
            return 0;
        }

        return "1".equals(arg2.getStringExtra("google.c.a.e"));
    }
}

