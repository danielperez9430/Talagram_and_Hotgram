package org.telegram.customization.util.c;

import android.app.NotificationManager;
import android.content.Context;

public class a {
    private static NotificationManager a;

    public static void a(Context arg0, int arg1) {
        a.a(arg0).cancel(arg1);
    }

    private static NotificationManager a(Context arg1) {
        if(a.a == null) {
            a.a = arg1.getSystemService("notification");
        }

        return a.a;
    }
}

