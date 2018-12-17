package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build$VERSION;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings$Secure;
import com.google.android.gms.internal.stable.zzg;

public final class DeviceStateUtils {
    private static final IntentFilter filter;
    private static long zzzw;
    private static float zzzx;

    static {
        DeviceStateUtils.filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        DeviceStateUtils.zzzx = NaNf;
    }

    private DeviceStateUtils() {
        super();
    }

    @TargetApi(value=20) public static int getDeviceState(Context arg4) {
        int v0 = -1;
        if(arg4 != null) {
            if(arg4.getApplicationContext() == null) {
            }
            else {
                Intent v1 = arg4.getApplicationContext().registerReceiver(null, DeviceStateUtils.filter);
                int v2 = 0;
                int v1_1 = v1 == null ? 0 : v1.getIntExtra("plugged", 0);
                if((v1_1 & 7) != 0) {
                    v2 = 1;
                }

                Object v4 = arg4.getSystemService("power");
                if(v4 == null) {
                    return v0;
                }

                return DeviceStateUtils.isInteractive(((PowerManager)v4)) << 1 | v2;
            }
        }

        return v0;
    }

    public static float getPowerPercentage(Context arg6) {
        float v6_1;
        Class v0 = DeviceStateUtils.class;
        __monitor_enter(v0);
        try {
            if(SystemClock.elapsedRealtime() - DeviceStateUtils.zzzw < 60000 && !Float.isNaN(DeviceStateUtils.zzzx)) {
                v6_1 = DeviceStateUtils.zzzx;
                goto label_12;
            }

            goto label_14;
        }
        catch(Throwable v6) {
            goto label_34;
        }

    label_12:
        __monitor_exit(v0);
        return v6_1;
        try {
        label_14:
            Intent v6_2 = arg6.getApplicationContext().registerReceiver(null, DeviceStateUtils.filter);
            if(v6_2 != null) {
                DeviceStateUtils.zzzx = (((float)v6_2.getIntExtra("level", -1))) / (((float)v6_2.getIntExtra("scale", -1)));
            }

            DeviceStateUtils.zzzw = SystemClock.elapsedRealtime();
            v6_1 = DeviceStateUtils.zzzx;
        }
        catch(Throwable v6) {
        label_34:
            __monitor_exit(v0);
            throw v6;
        }

        __monitor_exit(v0);
        return v6_1;
    }

    public static boolean hasConsentedNlp(Context arg2) {
        if(zzg.getInt(arg2.getContentResolver(), "network_location_opt_in", -1) == 1) {
            return 1;
        }

        return 0;
    }

    public static boolean isCallActive(Context arg1) {
        if(arg1.getSystemService("audio").getMode() == 2) {
            return 1;
        }

        return 0;
    }

    @TargetApi(value=20) public static boolean isInteractive(PowerManager arg1) {
        if(PlatformVersion.isAtLeastKitKatWatch()) {
            return arg1.isInteractive();
        }

        return arg1.isScreenOn();
    }

    public static boolean isInteractive(Context arg1) {
        return DeviceStateUtils.isInteractive(arg1.getSystemService("power"));
    }

    public static boolean isUserSetupComplete(Context arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            if(Settings$Secure.getInt(arg3.getContentResolver(), "user_setup_complete", -1) == 1) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    @VisibleForTesting public static void resetForTest() {
        Class v0 = DeviceStateUtils.class;
        __monitor_enter(v0);
        long v1 = 0;
        try {
            DeviceStateUtils.zzzw = v1;
            DeviceStateUtils.zzzx = NaNf;
        }
        catch(Throwable v1_1) {
            __monitor_exit(v0);
            throw v1_1;
        }

        __monitor_exit(v0);
    }
}

