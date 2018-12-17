package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.util.VisibleForTesting;

public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    static {
        GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    private GooglePlayServicesUtil() {
        super();
    }

    @Deprecated public static Dialog getErrorDialog(int arg1, Activity arg2, int arg3) {
        return GooglePlayServicesUtil.getErrorDialog(arg1, arg2, arg3, null);
    }

    @Deprecated public static Dialog getErrorDialog(int arg1, Activity arg2, int arg3, DialogInterface$OnCancelListener arg4) {
        if(GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(((Context)arg2), arg1)) {
            arg1 = 18;
        }

        return GoogleApiAvailability.getInstance().getErrorDialog(arg2, arg1, arg3, arg4);
    }

    @Deprecated public static PendingIntent getErrorPendingIntent(int arg0, Context arg1, int arg2) {
        return GooglePlayServicesUtilLight.getErrorPendingIntent(arg0, arg1, arg2);
    }

    @VisibleForTesting @Deprecated public static String getErrorString(int arg0) {
        return GooglePlayServicesUtilLight.getErrorString(arg0);
    }

    public static Context getRemoteContext(Context arg0) {
        return GooglePlayServicesUtilLight.getRemoteContext(arg0);
    }

    public static Resources getRemoteResource(Context arg0) {
        return GooglePlayServicesUtilLight.getRemoteResource(arg0);
    }

    @Deprecated public static int isGooglePlayServicesAvailable(Context arg0) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(arg0);
    }

    @Deprecated public static boolean isUserRecoverableError(int arg0) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(arg0);
    }

    @Deprecated public static boolean showErrorDialogFragment(int arg1, Activity arg2, int arg3) {
        return GooglePlayServicesUtil.showErrorDialogFragment(arg1, arg2, arg3, null);
    }

    @Deprecated public static boolean showErrorDialogFragment(int arg1, Activity arg2, int arg3, DialogInterface$OnCancelListener arg4) {
        return GooglePlayServicesUtil.showErrorDialogFragment(arg1, arg2, null, arg3, arg4);
    }

    public static boolean showErrorDialogFragment(int arg2, Activity arg3, Fragment arg4, int arg5, DialogInterface$OnCancelListener arg6) {
        if(GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(((Context)arg3), arg2)) {
            arg2 = 18;
        }

        GoogleApiAvailability v0 = GoogleApiAvailability.getInstance();
        if(arg4 == null) {
            return v0.showErrorDialogFragment(arg3, arg2, arg5, arg6);
        }

        Dialog v2 = GoogleApiAvailability.zza(((Context)arg3), arg2, DialogRedirect.getInstance(arg4, GoogleApiAvailability.getInstance().getErrorResolutionIntent(((Context)arg3), arg2, "d"), arg5), arg6);
        if(v2 == null) {
            return 0;
        }

        GoogleApiAvailability.zza(arg3, v2, "GooglePlayServicesErrorDialog", arg6);
        return 1;
    }

    @Deprecated public static void showErrorNotification(int arg2, Context arg3) {
        GoogleApiAvailability v0 = GoogleApiAvailability.getInstance();
        if(!GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(arg3, arg2)) {
            if(GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(arg3, arg2)) {
            }
            else {
                v0.showErrorNotification(arg3, arg2);
                return;
            }
        }

        v0.zza(arg3);
    }
}

