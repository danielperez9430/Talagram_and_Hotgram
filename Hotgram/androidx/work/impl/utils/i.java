package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager$WakeLock;
import android.os.PowerManager;

public class i {
    public static PowerManager$WakeLock a(Context arg2, String arg3) {
        Object v2 = arg2.getApplicationContext().getSystemService("power");
        StringBuilder v0 = new StringBuilder();
        v0.append("WorkManager: ");
        v0.append(arg3);
        return ((PowerManager)v2).newWakeLock(1, v0.toString());
    }
}

