package com.google.android.exoplayer2.util;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint(value={"InlinedApi"}) public final class NotificationUtil {
    @Retention(value=RetentionPolicy.SOURCE) @public interface Importance {
    }

    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;

    private NotificationUtil() {
        super();
    }

    public static void createNotificationChannel(Context arg2, String arg3, int arg4, int arg5) {
        if(Util.SDK_INT >= 26) {
            arg2.getSystemService("notification").createNotificationChannel(new NotificationChannel(arg3, arg2.getString(arg4), arg5));
        }
    }

    public static void setNotification(Context arg1, int arg2, Notification arg3) {
        Object v1 = arg1.getSystemService("notification");
        if(arg3 != null) {
            ((NotificationManager)v1).notify(arg2, arg3);
        }
        else {
            ((NotificationManager)v1).cancel(arg2);
        }
    }
}

