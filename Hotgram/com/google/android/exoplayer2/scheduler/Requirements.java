package com.google.android.exoplayer2.scheduler;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.PowerManager;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Requirements {
    @Retention(value=RetentionPolicy.SOURCE) @public interface NetworkType {
    }

    private static final int DEVICE_CHARGING = 16;
    private static final int DEVICE_IDLE = 8;
    public static final int NETWORK_TYPE_ANY = 1;
    private static final int NETWORK_TYPE_MASK = 7;
    public static final int NETWORK_TYPE_METERED = 4;
    public static final int NETWORK_TYPE_NONE = 0;
    public static final int NETWORK_TYPE_NOT_ROAMING = 3;
    private static final String[] NETWORK_TYPE_STRINGS = null;
    public static final int NETWORK_TYPE_UNMETERED = 2;
    private static final String TAG = "Requirements";
    private final int requirements;

    static {
    }

    public Requirements(int arg2, boolean arg3, boolean arg4) {
        int v0 = 0;
        int v3 = arg3 ? 16 : 0;
        arg2 |= v3;
        if(arg4) {
            v0 = 8;
        }

        this(arg2 | v0);
    }

    public Requirements(int arg1) {
        super();
        this.requirements = arg1;
    }

    private boolean checkChargingRequirement(Context arg5) {
        if(!this.isChargingRequired()) {
            return 1;
        }

        Intent v5 = arg5.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean v0 = false;
        if(v5 == null) {
            return 0;
        }

        int v5_1 = v5.getIntExtra("status", -1);
        if(v5_1 == 2 || v5_1 == 5) {
            v0 = true;
        }

        return v0;
    }

    private boolean checkIdleRequirement(Context arg5) {
        boolean v1 = true;
        if(!this.isIdleRequired()) {
            return 1;
        }

        Object v5 = arg5.getSystemService("power");
        if(Util.SDK_INT >= 23) {
            if(!((PowerManager)v5).isDeviceIdleMode()) {
            }
            else {
                goto label_13;
            }
        }
        else if(Util.SDK_INT >= 20) {
            if(!((PowerManager)v5).isInteractive()) {
            }
            else {
                goto label_13;
            }
        }
        else if(((PowerManager)v5).isScreenOn()) {
        label_13:
            v1 = false;
        }

        return v1;
    }

    private static boolean checkInternetConnectivity(ConnectivityManager arg3) {
        if(Util.SDK_INT < 23) {
            return 1;
        }

        Network v0 = arg3.getActiveNetwork();
        boolean v2 = false;
        if(v0 == null) {
            Requirements.logd("No active network.");
            return 0;
        }

        NetworkCapabilities v3 = arg3.getNetworkCapabilities(v0);
        if(v3 == null || !v3.hasCapability(16)) {
            v2 = true;
        }

        Requirements.logd("Network capability validated: " + v2);
        return (((int)v2)) ^ 1;
    }

    private boolean checkNetworkRequirements(Context arg6) {
        boolean v6_1;
        int v0 = this.getRequiredNetworkType();
        if(v0 == 0) {
            return 1;
        }

        Object v6 = arg6.getSystemService("connectivity");
        NetworkInfo v2 = ((ConnectivityManager)v6).getActiveNetworkInfo();
        if(v2 != null) {
            if(!v2.isConnected()) {
            }
            else if(!Requirements.checkInternetConnectivity(((ConnectivityManager)v6))) {
                return 0;
            }
            else if(v0 == 1) {
                return 1;
            }
            else if(v0 == 3) {
                v6_1 = v2.isRoaming();
                Requirements.logd("Roaming: " + v6_1);
                return (((int)v6_1)) ^ 1;
            }
            else {
                v6_1 = Requirements.isActiveNetworkMetered(((ConnectivityManager)v6), v2);
                Requirements.logd("Metered network: " + v6_1);
                if(v0 == 2) {
                    return (((int)v6_1)) ^ 1;
                }
                else if(v0 == 4) {
                    return v6_1;
                }
                else {
                    throw new IllegalStateException();
                }
            }
        }

        Requirements.logd("No network info or no connection.");
        return 0;
    }

    public boolean checkRequirements(Context arg2) {
        boolean v2 = !this.checkNetworkRequirements(arg2) || !this.checkChargingRequirement(arg2) || !this.checkIdleRequirement(arg2) ? false : true;
        return v2;
    }

    public int getRequiredNetworkType() {
        return this.requirements & 7;
    }

    public int getRequirementsData() {
        return this.requirements;
    }

    private static boolean isActiveNetworkMetered(ConnectivityManager arg2, NetworkInfo arg3) {
        if(Util.SDK_INT >= 16) {
            return arg2.isActiveNetworkMetered();
        }

        int v2 = arg3.getType();
        boolean v3 = true;
        if(v2 == 1 || v2 == 7 || v2 == 9) {
            v3 = false;
        }
        else {
        }

        return v3;
    }

    public boolean isChargingRequired() {
        boolean v0 = (this.requirements & 16) != 0 ? true : false;
        return v0;
    }

    public boolean isIdleRequired() {
        boolean v0 = (this.requirements & 8) != 0 ? true : false;
        return v0;
    }

    private static void logd(String arg0) {
    }

    public String toString() {
        return super.toString();
    }
}

