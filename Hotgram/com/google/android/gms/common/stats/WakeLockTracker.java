package com.google.android.gms.common.stats;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.DeviceStateUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.List;

public class WakeLockTracker {
    @VisibleForTesting private static boolean zzyh;
    private static WakeLockTracker zzyz;
    private static Boolean zzza;

    static {
        WakeLockTracker.zzyz = new WakeLockTracker();
        WakeLockTracker.zzyh = false;
    }

    public WakeLockTracker() {
        super();
    }

    public static WakeLockTracker getInstance() {
        return WakeLockTracker.zzyz;
    }

    public void registerAcquireEvent(Context arg11, Intent arg12, String arg13, String arg14, String arg15, int arg16, String arg17) {
        this.registerAcquireEvent(arg11, arg12, arg13, arg14, arg15, arg16, Arrays.asList(new String[]{arg17}));
    }

    public void registerAcquireEvent(Context arg11, Intent arg12, String arg13, String arg14, String arg15, int arg16, List arg17) {
        this.registerEvent(arg11, arg12.getStringExtra("WAKE_LOCK_KEY"), 7, arg13, arg14, arg15, arg16, arg17);
    }

    public void registerEvent(Context arg12, String arg13, int arg14, String arg15, String arg16, String arg17, int arg18, List arg19) {
        this.registerEvent(arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, 0);
    }

    public void registerEvent(Context arg19, String arg20, int arg21, String arg22, String arg23, String arg24, int arg25, List arg26, long arg27) {
        String v1_1;
        int v0 = arg21;
        List v1 = arg26;
        if(WakeLockTracker.zzza == null) {
            WakeLockTracker.zzza = Boolean.valueOf(false);
        }

        if(!WakeLockTracker.zzza.booleanValue()) {
            return;
        }

        if(TextUtils.isEmpty(((CharSequence)arg20))) {
            String v0_1 = "WakeLockTracker";
            v1_1 = "missing wakeLock key. ";
            String v2 = String.valueOf(arg20);
            v1_1 = v2.length() != 0 ? v1_1.concat(v2) : new String(v1_1);
            Log.e(v0_1, v1_1);
            return;
        }

        long v4 = System.currentTimeMillis();
        if(7 == v0 || 8 == v0 || 10 == v0 || 11 == v0) {
            WakeLockEvent v15 = null;
            List v2_1 = null;
            if(v1 != null && arg26.size() == 1 && ("com.google.android.gms".equals(v1.get(0)))) {
                v1 = v2_1;
            }

            List v7 = v1;
            long v9 = SystemClock.elapsedRealtime();
            int v11 = DeviceStateUtils.getDeviceState(arg19);
            v1_1 = arg19.getPackageName();
            String v13 = "com.google.android.gms".equals(v1_1) ? ((String)v2_1) : v1_1;
            Parcelable v0_2 = ((Parcelable)v15);
            super(v4, arg21, arg22, arg25, v7, arg20, v9, v11, arg23, v13, DeviceStateUtils.getPowerPercentage(arg19), arg27, arg24);
            try {
                arg19.startService(new Intent().setComponent(LoggingConstants.STATS_SERVICE_COMPONENT_NAME).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", v0_2));
                return;
            }
            catch(Exception v0_3) {
                Log.wtf("WakeLockTracker", ((Throwable)v0_3));
            }
        }
    }

    public void registerReleaseEvent(Context arg11, Intent arg12) {
        this.registerEvent(arg11, arg12.getStringExtra("WAKE_LOCK_KEY"), 8, null, null, null, 0, null);
    }

    public void registerSyncEnd(Context arg10, AbstractThreadedSyncAdapter arg11, String arg12, String arg13, boolean arg14) {
        this.registerEvent(arg10, StatsUtils.getEventKey(arg11, arg12), 11, arg12, arg13, null, 0, null);
    }

    public void registerSyncStart(Context arg10, AbstractThreadedSyncAdapter arg11, String arg12, String arg13) {
        this.registerEvent(arg10, StatsUtils.getEventKey(arg11, arg12), 10, arg12, arg13, null, 0, null);
    }
}

