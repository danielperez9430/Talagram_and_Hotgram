package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.GmsIntents;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;

public class GoogleApiAvailabilityLight {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static final GoogleApiAvailabilityLight zzaw;

    static {
        GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        GoogleApiAvailabilityLight.zzaw = new GoogleApiAvailabilityLight();
    }

    GoogleApiAvailabilityLight() {
        super();
    }

    public void cancelAvailabilityErrorNotifications(Context arg1) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(arg1);
    }

    public int getApkVersion(Context arg1) {
        return GooglePlayServicesUtilLight.getApkVersion(arg1);
    }

    public int getClientVersion(Context arg1) {
        return GooglePlayServicesUtilLight.getClientVersion(arg1);
    }

    @Deprecated public Intent getErrorResolutionIntent(int arg2) {
        return this.getErrorResolutionIntent(null, arg2, null);
    }

    public Intent getErrorResolutionIntent(Context arg1, int arg2, String arg3) {
        switch(arg2) {
            case 1: 
            case 2: {
                goto label_6;
            }
            case 3: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return GmsIntents.createSettingsIntent("com.google.android.gms");
    label_6:
        if(arg1 != null && (DeviceProperties.isWearableWithoutPlayStore(arg1))) {
            return GmsIntents.createAndroidWearUpdateIntent();
        }

        return GmsIntents.createPlayStoreIntent("com.google.android.gms", GoogleApiAvailabilityLight.zza(arg1, arg3));
    }

    public PendingIntent getErrorResolutionPendingIntent(Context arg2, int arg3, int arg4) {
        return this.getErrorResolutionPendingIntent(arg2, arg3, arg4, null);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context arg1, int arg2, int arg3, String arg4) {
        Intent v2 = this.getErrorResolutionIntent(arg1, arg2, arg4);
        if(v2 == null) {
            return null;
        }

        return PendingIntent.getActivity(arg1, arg3, v2, 134217728);
    }

    public String getErrorString(int arg1) {
        return GooglePlayServicesUtilLight.getErrorString(arg1);
    }

    public static GoogleApiAvailabilityLight getInstance() {
        return GoogleApiAvailabilityLight.zzaw;
    }

    public int isGooglePlayServicesAvailable(Context arg1, int arg2) {
        arg2 = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(arg1, arg2);
        if(GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(arg1, arg2)) {
            arg2 = 18;
        }

        return arg2;
    }

    public int isGooglePlayServicesAvailable(Context arg2) {
        return this.isGooglePlayServicesAvailable(arg2, GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public boolean isPlayServicesPossiblyUpdating(Context arg1, int arg2) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(arg1, arg2);
    }

    public boolean isPlayStorePossiblyUpdating(Context arg1, int arg2) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(arg1, arg2);
    }

    public boolean isUninstalledAppPossiblyUpdating(Context arg1, String arg2) {
        return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(arg1, arg2);
    }

    public boolean isUserResolvableError(int arg1) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(arg1);
    }

    public void verifyGooglePlayServicesIsAvailable(Context arg1) {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(arg1);
    }

    public void verifyGooglePlayServicesIsAvailable(Context arg1, int arg2) {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(arg1, arg2);
    }

    @VisibleForTesting private static String zza(Context arg2, String arg3) {
        StringBuilder v0 = new StringBuilder();
        v0.append("gcore_");
        v0.append(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        v0.append("-");
        if(!TextUtils.isEmpty(((CharSequence)arg3))) {
            v0.append(arg3);
        }

        v0.append("-");
        if(arg2 != null) {
            v0.append(arg2.getPackageName());
        }

        v0.append("-");
        if(arg2 != null) {
            try {
                v0.append(Wrappers.packageManager(arg2).getPackageInfo(arg2.getPackageName(), 0).versionCode);
                goto label_25;
            }
            catch(PackageManager$NameNotFoundException ) {
            label_25:
                return v0.toString();
            }
        }

        goto label_25;
    }
}

