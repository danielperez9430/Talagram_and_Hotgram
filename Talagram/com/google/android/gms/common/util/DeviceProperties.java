package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.os.Build;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Objects;

public final class DeviceProperties {
    public static final String FEATURE_AUTO = "android.hardware.type.automotive";
    public static final String FEATURE_CHROME_OS = "org.chromium.arc";
    public static final String FEATURE_EMBEDDED = "android.hardware.type.embedded";
    public static final String FEATURE_IOT = "android.hardware.type.iot";
    public static final String FEATURE_LATCHSKY = "cn.google.services";
    public static final String FEATURE_PIXEL = "com.google.android.feature.PIXEL_EXPERIENCE";
    public static final String FEATURE_SIDEWINDER = "cn.google";
    public static final String FEATURE_TV_1 = "com.google.android.tv";
    public static final String FEATURE_TV_2 = "android.hardware.type.television";
    public static final String FEATURE_TV_3 = "android.software.leanback";
    private static Boolean zzzl;
    private static Boolean zzzm;
    private static Boolean zzzn;
    private static Boolean zzzo;
    private static Boolean zzzp;
    private static Boolean zzzq;
    private static Boolean zzzr;
    private static Boolean zzzs;
    private static Boolean zzzt;
    private static Boolean zzzu;
    private static Boolean zzzv;

    private DeviceProperties() {
        super();
    }

    public static boolean isAuto(Context arg1) {
        if(DeviceProperties.zzzt == null) {
            boolean v1 = !PlatformVersion.isAtLeastO() || !arg1.getPackageManager().hasSystemFeature("android.hardware.type.automotive") ? false : true;
            DeviceProperties.zzzt = Boolean.valueOf(v1);
        }

        return DeviceProperties.zzzt.booleanValue();
    }

    public static boolean isChromeOsDevice(Context arg1) {
        if(DeviceProperties.zzzs == null) {
            DeviceProperties.zzzs = Boolean.valueOf(arg1.getPackageManager().hasSystemFeature("org.chromium.arc"));
        }

        return DeviceProperties.zzzs.booleanValue();
    }

    public static boolean isIoT(Context arg2) {
        if(DeviceProperties.zzzr == null) {
            boolean v2 = (arg2.getPackageManager().hasSystemFeature("android.hardware.type.iot")) || (arg2.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) ? true : false;
            DeviceProperties.zzzr = Boolean.valueOf(v2);
        }

        return DeviceProperties.zzzr.booleanValue();
    }

    public static boolean isLatchsky(Context arg1) {
        if(DeviceProperties.zzzp == null) {
            boolean v1 = !PlatformVersion.isAtLeastM() || !arg1.getPackageManager().hasSystemFeature("cn.google.services") ? false : true;
            DeviceProperties.zzzp = Boolean.valueOf(v1);
        }

        return DeviceProperties.zzzp.booleanValue();
    }

    public static boolean isLowRamOrPreKitKat(Context arg2) {
        if(Build$VERSION.SDK_INT < 19) {
            return 1;
        }

        if(DeviceProperties.zzzq == null) {
            Object v2 = arg2.getSystemService("activity");
            if(v2 != null) {
                DeviceProperties.zzzq = Boolean.valueOf(((ActivityManager)v2).isLowRamDevice());
            }
        }

        return Objects.equal(DeviceProperties.zzzq, Boolean.TRUE);
    }

    public static boolean isPixelDevice(Context arg1) {
        if(DeviceProperties.zzzv == null) {
            DeviceProperties.zzzv = Boolean.valueOf(arg1.getPackageManager().hasSystemFeature("com.google.android.feature.PIXEL_EXPERIENCE"));
        }

        return DeviceProperties.zzzv.booleanValue();
    }

    @TargetApi(value=21) public static boolean isSidewinder(Context arg1) {
        if(DeviceProperties.zzzo == null) {
            boolean v1 = !PlatformVersion.isAtLeastLollipop() || !arg1.getPackageManager().hasSystemFeature("cn.google") ? false : true;
            DeviceProperties.zzzo = Boolean.valueOf(v1);
        }

        return DeviceProperties.zzzo.booleanValue();
    }

    public static boolean isTablet(Resources arg4) {
        boolean v0 = false;
        if(arg4 == null) {
            return 0;
        }

        if(DeviceProperties.zzzl == null) {
            int v2 = 3;
            int v1 = (arg4.getConfiguration().screenLayout & 15) > v2 ? 1 : 0;
            if(v1 == 0) {
                if(DeviceProperties.zzzm == null) {
                    Configuration v4 = arg4.getConfiguration();
                    boolean v4_1 = (v4.screenLayout & 15) > v2 || v4.smallestScreenWidthDp < 600 ? false : true;
                    DeviceProperties.zzzm = Boolean.valueOf(v4_1);
                }

                if(!DeviceProperties.zzzm.booleanValue()) {
                    goto label_33;
                }

                goto label_32;
            }
            else {
            label_32:
                v0 = true;
            }

        label_33:
            DeviceProperties.zzzl = Boolean.valueOf(v0);
        }

        return DeviceProperties.zzzl.booleanValue();
    }

    public static boolean isTv(Context arg1) {
        if(DeviceProperties.zzzu == null) {
            PackageManager v1 = arg1.getPackageManager();
            boolean v1_1 = (v1.hasSystemFeature("com.google.android.tv")) || (v1.hasSystemFeature("android.hardware.type.television")) || (v1.hasSystemFeature("android.software.leanback")) ? true : false;
            DeviceProperties.zzzu = Boolean.valueOf(v1_1);
        }

        return DeviceProperties.zzzu.booleanValue();
    }

    public static boolean isUserBuild() {
        if(GooglePlayServicesUtilLight.sIsTestMode) {
            return GooglePlayServicesUtilLight.sTestIsUserBuild;
        }

        return "user".equals(Build.TYPE);
    }

    @TargetApi(value=20) public static boolean isWearable(Context arg1) {
        if(DeviceProperties.zzzn == null) {
            boolean v1 = !PlatformVersion.isAtLeastKitKatWatch() || !arg1.getPackageManager().hasSystemFeature("android.hardware.type.watch") ? false : true;
            DeviceProperties.zzzn = Boolean.valueOf(v1);
        }

        return DeviceProperties.zzzn.booleanValue();
    }

    @TargetApi(value=24) public static boolean isWearableWithoutPlayStore(Context arg1) {
        if((!PlatformVersion.isAtLeastN() || (DeviceProperties.isSidewinder(arg1))) && (DeviceProperties.isWearable(arg1))) {
            return 1;
        }

        return 0;
    }

    @VisibleForTesting public static void resetForTest() {
        DeviceProperties.zzzm = null;
        DeviceProperties.zzzl = null;
        DeviceProperties.zzzn = null;
        DeviceProperties.zzzo = null;
        DeviceProperties.zzzp = null;
        DeviceProperties.zzzq = null;
        DeviceProperties.zzzr = null;
        DeviceProperties.zzzs = null;
        DeviceProperties.zzzt = null;
        DeviceProperties.zzzu = null;
        DeviceProperties.zzzv = null;
    }

    @VisibleForTesting public static void setIsAutoForTest(boolean arg0) {
        DeviceProperties.zzzt = Boolean.valueOf(arg0);
    }

    @VisibleForTesting public static void setIsIoTForTest(boolean arg0) {
        DeviceProperties.zzzr = Boolean.valueOf(arg0);
    }

    @VisibleForTesting public static void setIsLatchskyForTest(boolean arg0) {
        DeviceProperties.zzzp = Boolean.valueOf(arg0);
    }

    @VisibleForTesting public static void setIsLowRamForTest(boolean arg0) {
        DeviceProperties.zzzq = Boolean.valueOf(arg0);
    }

    @VisibleForTesting public static void setIsPixelForTest(boolean arg0) {
        DeviceProperties.zzzv = Boolean.valueOf(arg0);
    }

    @VisibleForTesting public static void setIsSideWinderForTest(boolean arg0) {
        DeviceProperties.zzzo = Boolean.valueOf(arg0);
    }

    @VisibleForTesting public static void setIsTvForTest(boolean arg0) {
        DeviceProperties.zzzu = Boolean.valueOf(arg0);
    }

    @VisibleForTesting public static void setIsWearableForTest(boolean arg0) {
        DeviceProperties.zzzn = Boolean.valueOf(arg0);
    }
}

