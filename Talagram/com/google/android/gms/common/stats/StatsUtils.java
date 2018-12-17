package com.google.android.gms.common.stats;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager$WakeLock;
import android.os.Process;
import android.text.TextUtils;

public class StatsUtils {
    public StatsUtils() {
        super();
    }

    public static String getEventKey(AbstractThreadedSyncAdapter arg4, String arg5) {
        String v4 = String.valueOf(String.valueOf((((long)Process.myPid())) << 32 | (((long)System.identityHashCode(arg4)))));
        if(TextUtils.isEmpty(((CharSequence)arg5))) {
            arg5 = "";
        }

        arg5 = String.valueOf(arg5);
        if(arg5.length() != 0) {
            return v4.concat(arg5);
        }

        return new String(v4);
    }

    public static String getEventKey(Context arg2, Intent arg3) {
        return String.valueOf((((long)System.identityHashCode(arg3))) | (((long)System.identityHashCode(arg2))) << 32);
    }

    public static String getEventKey(PowerManager$WakeLock arg4, String arg5) {
        String v4 = String.valueOf(String.valueOf((((long)Process.myPid())) << 32 | (((long)System.identityHashCode(arg4)))));
        if(TextUtils.isEmpty(((CharSequence)arg5))) {
            arg5 = "";
        }

        arg5 = String.valueOf(arg5);
        if(arg5.length() != 0) {
            return v4.concat(arg5);
        }

        return new String(v4);
    }

    public static boolean isLoggingEnabled() {
        StatisticalEventTracker v0 = StatisticalEventTrackerProvider.getImpl();
        if(v0 != null && (v0.isEnabled()) && ((StatsUtils.zza(Integer.valueOf(v0.getLogLevel(3)))) || (StatsUtils.zza(Integer.valueOf(v0.getLogLevel(2)))) || (StatsUtils.zza(Integer.valueOf(v0.getLogLevel(1)))))) {
            return 1;
        }

        return 0;
    }

    public static boolean isTimeOutEvent(StatsEvent arg1) {
        int v1 = arg1.getEventType();
        if(v1 != 6 && v1 != 9 && v1 != 12) {
            return 0;
        }

        return 1;
    }

    private static boolean zza(Integer arg1) {
        if(!arg1.equals(Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF))) {
            return 1;
        }

        return 0;
    }
}

