package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.e;
import android.util.Log;
import com.google.android.gms.common.stats.StatsUtils;
import com.google.android.gms.common.stats.WakeLockTracker;
import javax.annotation.Nonnull;

public abstract class GCoreWakefulBroadcastReceiver extends e {
    private static String TAG = "GCoreWakefulBroadcastReceiver";

    static {
    }

    public GCoreWakefulBroadcastReceiver() {
        super();
    }

    @SuppressLint(value={"UnwrappedWakefulBroadcastReceiver"}) public static boolean completeWakefulIntent(Context arg3, Intent arg4) {
        if(arg4 == null) {
            return 0;
        }

        if(arg3 != null) {
            WakeLockTracker.getInstance().registerReleaseEvent(arg3, arg4);
        }
        else {
            String v3 = GCoreWakefulBroadcastReceiver.TAG;
            String v1 = "context shouldn\'t be null. intent: ";
            String v0 = String.valueOf(arg4.toUri(0));
            v0 = v0.length() != 0 ? v1.concat(v0) : new String(v1);
            Log.w(v3, v0);
        }

        return e.completeWakefulIntent(arg4);
    }

    public static ComponentName startWakefulService(Context arg9, Intent arg10) {
        ComponentName v0 = GCoreWakefulBroadcastReceiver.zza(arg9, arg10);
        if(v0 == null) {
            return null;
        }

        WakeLockTracker v1 = WakeLockTracker.getInstance();
        String v2 = "wake:";
        String v3 = String.valueOf(v0.flattenToShortString());
        String v4 = v3.length() != 0 ? v2.concat(v3) : new String(v2);
        v1.registerAcquireEvent(arg9, arg10, v4, GCoreWakefulBroadcastReceiver.TAG, null, 1, "com.google.android.gms");
        return v0;
    }

    public static ComponentName startWakefulService(Context arg1, Intent arg2, @Nonnull String arg3) {
        return GCoreWakefulBroadcastReceiver.startWakefulService(arg1, arg2, arg3, arg1.getPackageName());
    }

    public static ComponentName startWakefulService(Context arg9, Intent arg10, @Nonnull String arg11, String arg12) {
        ComponentName v0 = GCoreWakefulBroadcastReceiver.zza(arg9, arg10);
        if(v0 == null) {
            return null;
        }

        WakeLockTracker.getInstance().registerAcquireEvent(arg9, arg10, arg11, GCoreWakefulBroadcastReceiver.TAG, null, 1, arg12);
        return v0;
    }

    @SuppressLint(value={"UnwrappedWakefulBroadcastReceiver"}) private static ComponentName zza(Context arg2, Intent arg3) {
        arg3.putExtra("WAKE_LOCK_KEY", StatsUtils.getEventKey(arg2, arg3));
        return e.startWakefulService(arg2, arg3);
    }
}

