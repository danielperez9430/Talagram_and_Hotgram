package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.MetadataValueReader;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.GmsVersionParser;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class GooglePlayServicesUtilLight {
    public static final String FEATURE_SIDEWINDER = "cn.google";
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
    @Deprecated public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
    public static final String GOOGLE_PLAY_STORE_GAMES_URI_STRING = "http://play.google.com/store/apps/category/GAME";
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static final String GOOGLE_PLAY_STORE_URI_STRING = "http://play.google.com/store/apps/";
    public static final boolean HONOR_DEBUG_CERTIFICATES = false;
    public static final String KEY_METADATA_GOOGLE_PLAY_SERVICES_VERSION = "com.google.android.gms.version";
    public static final int MAX_ATTEMPTS_NO_SUCH_ALGORITHM = 2;
    @VisibleForTesting public static boolean sIsTestMode = false;
    @VisibleForTesting public static boolean sTestIsUserBuild = false;
    private static boolean zzbr = false;
    @VisibleForTesting private static boolean zzbs = false;
    @VisibleForTesting static final AtomicBoolean zzbt;
    private static final AtomicBoolean zzbu;

    static {
        GooglePlayServicesUtilLight.zzbt = new AtomicBoolean();
        GooglePlayServicesUtilLight.zzbu = new AtomicBoolean();
    }

    GooglePlayServicesUtilLight() {
        super();
    }

    @Deprecated public static void cancelAvailabilityErrorNotifications(Context arg2) {
        if(GooglePlayServicesUtilLight.zzbt.getAndSet(true)) {
            return;
        }

        try {
            Object v2 = arg2.getSystemService("notification");
            if(v2 != null) {
                ((NotificationManager)v2).cancel(10436);
            }

            return;
        }
        catch(SecurityException ) {
            return;
        }
    }

    public static void enableUsingApkIndependentContext() {
        GooglePlayServicesUtilLight.zzbu.set(true);
    }

    @Deprecated public static void ensurePlayServicesAvailable(Context arg1) {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(arg1, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @Deprecated public static void ensurePlayServicesAvailable(Context arg3, int arg4) {
        arg4 = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(arg3, arg4);
        if(arg4 != 0) {
            Intent v3 = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(arg3, arg4, "e");
            StringBuilder v2 = new StringBuilder(57);
            v2.append("GooglePlayServices not available due to error ");
            v2.append(arg4);
            Log.e("GooglePlayServicesUtil", v2.toString());
            if(v3 == null) {
                throw new GooglePlayServicesNotAvailableException(arg4);
            }

            throw new GooglePlayServicesRepairableException(arg4, "Google Play Services not available", v3);
        }
    }

    @Deprecated public static int getApkVersion(Context arg2) {
        PackageInfo v2;
        try {
            v2 = arg2.getPackageManager().getPackageInfo("com.google.android.gms", 0);
        }
        catch(PackageManager$NameNotFoundException ) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }

        return v2.versionCode;
    }

    @Deprecated public static int getClientVersion(Context arg1) {
        Preconditions.checkState(true);
        return ClientLibraryUtils.getClientVersion(arg1, arg1.getPackageName());
    }

    @Deprecated public static PendingIntent getErrorPendingIntent(int arg1, Context arg2, int arg3) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(arg2, arg1, arg3);
    }

    @VisibleForTesting @Deprecated public static String getErrorString(int arg0) {
        return ConnectionResult.zza(arg0);
    }

    @Deprecated public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int arg2) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, arg2, null);
    }

    @Deprecated public static String getOpenSourceSoftwareLicenseInfo(Context arg3) {
        String v0_2;
        InputStream v3;
        Uri v0 = new Uri$Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
        String v1 = null;
        try {
            v3 = arg3.getContentResolver().openInputStream(v0);
        }
        catch(Exception ) {
            return v1;
        }

        try {
            v0_2 = new Scanner(v3).useDelimiter("\\A").next();
            if(v3 == null) {
                return v0_2;
            }

            goto label_20;
        }
        catch(Throwable v0_1) {
        }
        catch(NoSuchElementException ) {
            try {
                if(v3 != null) {
                    v3.close();
                    return v1;
                    if(v3 != null) {
                        v3.close();
                    }

                    throw v0_1;
                label_20:
                    v3.close();
                    return v0_2;
                }

                return v1;
            }
            catch(Exception ) {
                return v1;
            }
        }
    }

    public static Context getRemoteContext(Context arg2) {
        try {
            return arg2.createPackageContext("com.google.android.gms", 3);
        }
        catch(PackageManager$NameNotFoundException ) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context arg1) {
        try {
            return arg1.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch(PackageManager$NameNotFoundException ) {
            return null;
        }
    }

    public static boolean honorsDebugCertificates(Context arg0) {
        if(!GooglePlayServicesUtilLight.isTestKeysBuild(arg0)) {
            if(!GooglePlayServicesUtilLight.isUserBuildDevice()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    @Deprecated public static int isGooglePlayServicesAvailable(Context arg3, int arg4) {
        try {
            arg3.getResources().getString(string.common_google_play_services_unknown_issue);
        }
        catch(Throwable ) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }

        if(!"com.google.android.gms".equals(arg3.getPackageName()) && !GooglePlayServicesUtilLight.zzbu.get()) {
            int v0 = MetadataValueReader.getGooglePlayServicesVersion(arg3);
            if(v0 == 0) {
                throw new IllegalStateException("A required meta-data tag in your app\'s AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
            else if(v0 == GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
            }
            else {
                arg4 = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
                StringBuilder v2 = new StringBuilder(320);
                v2.append("The meta-data tag in your app\'s AndroidManifest.xml does not have the right value.  Expected ");
                v2.append(arg4);
                v2.append(" but found ");
                v2.append(v0);
                v2.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                throw new IllegalStateException(v2.toString());
            }
        }

        boolean v0_1 = (DeviceProperties.isWearableWithoutPlayStore(arg3)) || (DeviceProperties.isIoT(arg3)) ? false : true;
        return GooglePlayServicesUtilLight.zza(arg3, v0_1, arg4);
    }

    @Deprecated public static int isGooglePlayServicesAvailable(Context arg1) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(arg1, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @Deprecated public static boolean isGooglePlayServicesUid(Context arg0, int arg1) {
        return UidVerifier.isGooglePlayServicesUid(arg0, arg1);
    }

    @Deprecated public static boolean isPlayServicesPossiblyUpdating(Context arg2, int arg3) {
        if(arg3 == 18) {
            return 1;
        }

        if(arg3 == 1) {
            return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(arg2, "com.google.android.gms");
        }

        return 0;
    }

    @Deprecated public static boolean isPlayStorePossiblyUpdating(Context arg1, int arg2) {
        if(arg2 == 9) {
            return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(arg1, "com.android.vending");
        }

        return 0;
    }

    @TargetApi(value=18) public static boolean isRestrictedUserProfile(Context arg2) {
        if(PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle v2 = arg2.getSystemService("user").getApplicationRestrictions(arg2.getPackageName());
            if(v2 != null && ("true".equals(v2.getString("restricted_profile")))) {
                return 1;
            }
        }

        return 0;
    }

    @VisibleForTesting @Deprecated public static boolean isSidewinderDevice(Context arg0) {
        return DeviceProperties.isSidewinder(arg0);
    }

    public static boolean isTestKeysBuild(Context arg4) {
        if(!GooglePlayServicesUtilLight.zzbs) {
            try {
                PackageInfo v1 = Wrappers.packageManager(arg4).getPackageInfo("com.google.android.gms", 64);
                GoogleSignatureVerifier v4_2 = GoogleSignatureVerifier.getInstance(arg4);
                if(v1 != null && !v4_2.isGooglePublicSignedPackage(v1, false) && (v4_2.isGooglePublicSignedPackage(v1, true))) {
                    GooglePlayServicesUtilLight.zzbr = true;
                    goto label_24;
                }

                GooglePlayServicesUtilLight.zzbr = false;
            }
            catch(Throwable v4) {
            }
            catch(PackageManager$NameNotFoundException v4_1) {
                try {
                    Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", ((Throwable)v4_1));
                }
                catch(Throwable v4) {
                    GooglePlayServicesUtilLight.zzbs = true;
                    throw v4;
                }
            }

        label_24:
            GooglePlayServicesUtilLight.zzbs = true;
        }

        return GooglePlayServicesUtilLight.zzbr;
    }

    @TargetApi(value=21) static boolean isUninstalledAppPossiblyUpdating(Context arg5, String arg6) {
        List v1;
        boolean v0 = arg6.equals("com.google.android.gms");
        if(PlatformVersion.isAtLeastLollipop()) {
            try {
                v1 = arg5.getPackageManager().getPackageInstaller().getAllSessions();
            }
            catch(Exception ) {
                return 0;
            }

            Iterator v1_1 = v1.iterator();
            do {
                if(v1_1.hasNext()) {
                    if(!arg6.equals(v1_1.next().getAppPackageName())) {
                        continue;
                    }

                    return 1;
                }

                goto label_18;
            }
            while(true);

            return 1;
        }

    label_18:
        PackageManager v1_2 = arg5.getPackageManager();
        int v4 = 8192;
        try {
            ApplicationInfo v6 = v1_2.getApplicationInfo(arg6, v4);
            if(v0) {
                return v6.enabled;
            }

            if((v6.enabled) && !GooglePlayServicesUtilLight.isRestrictedUserProfile(arg5)) {
                return 1;
            }
        }
        catch(PackageManager$NameNotFoundException ) {
        }

        return 0;
    }

    @Deprecated public static boolean isUserBuildDevice() {
        return DeviceProperties.isUserBuild();
    }

    @Deprecated public static boolean isUserRecoverableError(int arg1) {
        if(arg1 != 9) {
            switch(arg1) {
                case 1: 
                case 2: 
                case 3: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        return 1;
    }

    @TargetApi(value=19) @Deprecated public static boolean uidHasPackageName(Context arg0, int arg1, String arg2) {
        return UidVerifier.uidHasPackageName(arg0, arg1, arg2);
    }

    @VisibleForTesting private static int zza(Context arg7, boolean arg8, int arg9) {
        PackageInfo v5;
        String v8;
        String v7;
        boolean v2 = arg9 >= 0 ? true : false;
        Preconditions.checkArgument(v2);
        PackageManager v2_1 = arg7.getPackageManager();
        PackageInfo v3 = null;
        int v4 = 9;
        if(arg8) {
            try {
                v3 = v2_1.getPackageInfo("com.android.vending", 8256);
            }
            catch(PackageManager$NameNotFoundException ) {
                v7 = "GooglePlayServicesUtil";
                v8 = "Google Play Store is missing.";
                goto label_17;
            }
        }

        try {
            v5 = v2_1.getPackageInfo("com.google.android.gms", 64);
        }
        catch(PackageManager$NameNotFoundException ) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }

        GoogleSignatureVerifier v7_1 = GoogleSignatureVerifier.getInstance(arg7);
        if(!v7_1.isGooglePublicSignedPackage(v5, true)) {
            v7 = "GooglePlayServicesUtil";
            v8 = "Google Play services signature invalid.";
            goto label_17;
        }

        if((arg8) && (!v7_1.isGooglePublicSignedPackage(v3, true) || !v3.signatures[0].equals(v5.signatures[0]))) {
            v7 = "GooglePlayServicesUtil";
            v8 = "Google Play Store signature invalid.";
        label_17:
            Log.w(v7, v8);
            return v4;
        }

        if(GmsVersionParser.parseBuildVersion(v5.versionCode) < GmsVersionParser.parseBuildVersion(arg9)) {
            int v8_1 = v5.versionCode;
            StringBuilder v1 = new StringBuilder(77);
            v1.append("Google Play services out of date.  Requires ");
            v1.append(arg9);
            v1.append(" but found ");
            v1.append(v8_1);
            Log.w("GooglePlayServicesUtil", v1.toString());
            return 2;
        }

        ApplicationInfo v7_2 = v5.applicationInfo;
        if(v7_2 == null) {
            try {
                v7_2 = v2_1.getApplicationInfo("com.google.android.gms", 0);
            }
            catch(PackageManager$NameNotFoundException v7_3) {
                Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", ((Throwable)v7_3));
                return 1;
            }
        }

        if(!v7_2.enabled) {
            return 3;
        }

        return 0;
    }
}

