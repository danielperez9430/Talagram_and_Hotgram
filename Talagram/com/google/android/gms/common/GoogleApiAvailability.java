package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog$Builder;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.h;
import android.support.v4.app.w$c;
import android.support.v4.app.w$d;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.base.R$drawable;
import com.google.android.gms.base.R$string;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver$Callback;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zzbt;
import com.google.android.gms.common.api.internal.zzk;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    @SuppressLint(value={"HandlerLeak"}) final class zza extends Handler {
        private final Context zzau;

        public zza(GoogleApiAvailability arg1, Context arg2) {
            this.zzav = arg1;
            Looper v1 = Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper();
            super(v1);
            this.zzau = arg2.getApplicationContext();
        }

        public final void handleMessage(Message arg4) {
            int v4;
            if(arg4.what != 1) {
                v4 = arg4.what;
                StringBuilder v2 = new StringBuilder(50);
                v2.append("Don\'t know how to handle this message: ");
                v2.append(v4);
                Log.w("GoogleApiAvailability", v2.toString());
            }
            else {
                v4 = this.zzav.isGooglePlayServicesAvailable(this.zzau);
                if(this.zzav.isUserResolvableError(v4)) {
                    this.zzav.showErrorNotification(this.zzau, v4);
                }
            }
        }
    }

    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final Object mLock;
    private static final GoogleApiAvailability zzas;
    private String zzat;

    static {
        GoogleApiAvailability.mLock = new Object();
        GoogleApiAvailability.zzas = new GoogleApiAvailability();
        GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    GoogleApiAvailability() {
        super();
    }

    public Task checkApiAvailability(GoogleApi arg1, GoogleApi[] arg2) {
        return this.checkApiAvailabilityAndPackages(arg1, arg2).continueWith(new com.google.android.gms.common.zza(this));
    }

    public Task checkApiAvailabilityAndPackages(GoogleApi arg5, GoogleApi[] arg6) {
        Preconditions.checkNotNull(arg5, "Requested API must not be null.");
        int v0 = arg6.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Preconditions.checkNotNull(arg6[v1], "Requested API must not be null.");
        }

        ArrayList v0_1 = new ArrayList(arg6.length + 1);
        ((List)v0_1).add(arg5);
        ((List)v0_1).addAll(Arrays.asList(((Object[])arg6)));
        return GoogleApiManager.zzbf().zza(((Iterable)v0_1));
    }

    public int getApkVersion(Context arg1) {
        return super.getApkVersion(arg1);
    }

    public int getClientVersion(Context arg1) {
        return super.getClientVersion(arg1);
    }

    public Dialog getErrorDialog(Activity arg2, int arg3, int arg4) {
        return this.getErrorDialog(arg2, arg3, arg4, null);
    }

    public Dialog getErrorDialog(Activity arg2, int arg3, int arg4, DialogInterface$OnCancelListener arg5) {
        return GoogleApiAvailability.zza(((Context)arg2), arg3, DialogRedirect.getInstance(arg2, ((GoogleApiAvailabilityLight)this).getErrorResolutionIntent(((Context)arg2), arg3, "d"), arg4), arg5);
    }

    @Deprecated public Intent getErrorResolutionIntent(int arg1) {
        return super.getErrorResolutionIntent(arg1);
    }

    public Intent getErrorResolutionIntent(Context arg1, int arg2, String arg3) {
        return super.getErrorResolutionIntent(arg1, arg2, arg3);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context arg1, int arg2, int arg3) {
        return super.getErrorResolutionPendingIntent(arg1, arg2, arg3);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context arg1, int arg2, int arg3, String arg4) {
        return super.getErrorResolutionPendingIntent(arg1, arg2, arg3, arg4);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context arg2, ConnectionResult arg3) {
        if(arg3.hasResolution()) {
            return arg3.getResolution();
        }

        return ((GoogleApiAvailabilityLight)this).getErrorResolutionPendingIntent(arg2, arg3.getErrorCode(), 0);
    }

    public final String getErrorString(int arg1) {
        return super.getErrorString(arg1);
    }

    public static GoogleApiAvailability getInstance() {
        return GoogleApiAvailability.zzas;
    }

    public int isGooglePlayServicesAvailable(Context arg1) {
        return super.isGooglePlayServicesAvailable(arg1);
    }

    public int isGooglePlayServicesAvailable(Context arg1, int arg2) {
        return super.isGooglePlayServicesAvailable(arg1, arg2);
    }

    public boolean isPlayServicesPossiblyUpdating(Context arg1, int arg2) {
        return super.isPlayServicesPossiblyUpdating(arg1, arg2);
    }

    public boolean isPlayStorePossiblyUpdating(Context arg1, int arg2) {
        return super.isPlayStorePossiblyUpdating(arg1, arg2);
    }

    public final boolean isUserResolvableError(int arg1) {
        return super.isUserResolvableError(arg1);
    }

    public Task makeGooglePlayServicesAvailable(Activity arg4) {
        Preconditions.checkMainThread("makeGooglePlayServicesAvailable must be called from the main thread");
        int v0 = ((GoogleApiAvailabilityLight)this).isGooglePlayServicesAvailable(((Context)arg4));
        Object v1 = null;
        if(v0 == 0) {
            return Tasks.forResult(v1);
        }

        zzbt v4 = zzbt.zzd(arg4);
        ((zzk)v4).zzb(new ConnectionResult(v0, ((PendingIntent)v1)), 0);
        return v4.getTask();
    }

    public GooglePlayServicesUpdatedReceiver registerCallbackOnUpdate(Context arg3, Callback arg4) {
        IntentFilter v0 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        v0.addDataScheme("package");
        GooglePlayServicesUpdatedReceiver v1 = new GooglePlayServicesUpdatedReceiver(arg4);
        arg3.registerReceiver(((BroadcastReceiver)v1), v0);
        v1.zzc(arg3);
        if(!((GoogleApiAvailabilityLight)this).isUninstalledAppPossiblyUpdating(arg3, "com.google.android.gms")) {
            arg4.zzv();
            v1.unregister();
            return null;
        }

        return v1;
    }

    @TargetApi(value=26) public void setDefaultNotificationChannelId(Context arg2, String arg3) {
        if(PlatformVersion.isAtLeastO()) {
            Preconditions.checkNotNull(arg2.getSystemService("notification").getNotificationChannel(arg3));
        }

        Object v2 = GoogleApiAvailability.mLock;
        __monitor_enter(v2);
        try {
            this.zzat = arg3;
            __monitor_exit(v2);
            return;
        label_12:
            __monitor_exit(v2);
        }
        catch(Throwable v3) {
            goto label_12;
        }

        throw v3;
    }

    public boolean showErrorDialogFragment(Activity arg1, int arg2, int arg3, DialogInterface$OnCancelListener arg4) {
        Dialog v2 = this.getErrorDialog(arg1, arg2, arg3, arg4);
        if(v2 == null) {
            return 0;
        }

        GoogleApiAvailability.zza(arg1, v2, "GooglePlayServicesErrorDialog", arg4);
        return 1;
    }

    public boolean showErrorDialogFragment(Activity arg2, LifecycleFragment arg3, int arg4, int arg5, DialogInterface$OnCancelListener arg6) {
        Dialog v3 = GoogleApiAvailability.zza(((Context)arg2), arg4, DialogRedirect.getInstance(arg3, ((GoogleApiAvailabilityLight)this).getErrorResolutionIntent(((Context)arg2), arg4, "d"), arg5), arg6);
        if(v3 == null) {
            return 0;
        }

        GoogleApiAvailability.zza(arg2, v3, "GooglePlayServicesErrorDialog", arg6);
        return 1;
    }

    public boolean showErrorDialogFragment(Activity arg2, int arg3, int arg4) {
        return this.showErrorDialogFragment(arg2, arg3, arg4, null);
    }

    public void showErrorNotification(Context arg2, int arg3) {
        this.showErrorNotification(arg2, arg3, null);
    }

    public void showErrorNotification(Context arg3, int arg4, String arg5) {
        this.zza(arg3, arg4, arg5, ((GoogleApiAvailabilityLight)this).getErrorResolutionPendingIntent(arg3, arg4, 0, "n"));
    }

    public void showErrorNotification(Context arg3, ConnectionResult arg4) {
        this.zza(arg3, arg4.getErrorCode(), null, this.getErrorResolutionPendingIntent(arg3, arg4));
    }

    public Dialog showUpdatingDialog(Activity arg4, DialogInterface$OnCancelListener arg5) {
        ProgressBar v0 = new ProgressBar(((Context)arg4), null, 16842874);
        v0.setIndeterminate(true);
        v0.setVisibility(0);
        AlertDialog$Builder v2 = new AlertDialog$Builder(((Context)arg4));
        v2.setView(((View)v0));
        v2.setMessage(ConnectionErrorMessages.getErrorMessage(((Context)arg4), 18));
        v2.setPositiveButton("", null);
        AlertDialog v0_1 = v2.create();
        GoogleApiAvailability.zza(arg4, ((Dialog)v0_1), "GooglePlayServicesUpdatingDialog", arg5);
        return ((Dialog)v0_1);
    }

    public boolean showWrappedErrorNotification(Context arg3, ConnectionResult arg4, int arg5) {
        PendingIntent v0 = this.getErrorResolutionPendingIntent(arg3, arg4);
        if(v0 != null) {
            this.zza(arg3, arg4.getErrorCode(), null, GoogleApiActivity.zza(arg3, v0, arg5));
            return 1;
        }

        return 0;
    }

    static Dialog zza(Context arg5, int arg6, DialogRedirect arg7, DialogInterface$OnCancelListener arg8) {
        AlertDialog$Builder v0_1;
        Dialog v0 = null;
        if(arg6 == 0) {
            return v0;
        }

        TypedValue v1 = new TypedValue();
        arg5.getTheme().resolveAttribute(16843529, v1, true);
        if("Theme.Dialog.Alert".equals(arg5.getResources().getResourceEntryName(v1.resourceId))) {
            v0_1 = new AlertDialog$Builder(arg5, 5);
        }

        if(v0_1 == null) {
            v0_1 = new AlertDialog$Builder(arg5);
        }

        v0_1.setMessage(ConnectionErrorMessages.getErrorMessage(arg5, arg6));
        if(arg8 != null) {
            v0_1.setOnCancelListener(arg8);
        }

        String v8 = ConnectionErrorMessages.getErrorDialogButtonMessage(arg5, arg6);
        if(v8 != null) {
            v0_1.setPositiveButton(((CharSequence)v8), ((DialogInterface$OnClickListener)arg7));
        }

        String v5 = ConnectionErrorMessages.getErrorTitle(arg5, arg6);
        if(v5 != null) {
            v0_1.setTitle(((CharSequence)v5));
        }

        return v0_1.create();
    }

    @TargetApi(value=26) private final String zza(Context arg4, NotificationManager arg5) {
        Preconditions.checkState(PlatformVersion.isAtLeastO());
        String v0 = this.zzb();
        if(v0 == null) {
            v0 = "com.google.android.gms.availability";
            NotificationChannel v1 = arg5.getNotificationChannel(v0);
            String v4 = ConnectionErrorMessages.getDefaultNotificationChannelName(arg4);
            if(v1 == null) {
                v1 = new NotificationChannel(v0, ((CharSequence)v4), 4);
            }
            else if(!v4.equals(v1.getName())) {
                v1.setName(((CharSequence)v4));
            }
            else {
                return v0;
            }

            arg5.createNotificationChannel(v1);
        }

        return v0;
    }

    static void zza(Activity arg1, Dialog arg2, String arg3, DialogInterface$OnCancelListener arg4) {
        if((arg1 instanceof h)) {
            SupportErrorDialogFragment.newInstance(arg2, arg4).show(((h)arg1).d(), arg3);
            return;
        }

        ErrorDialogFragment.newInstance(arg2, arg4).show(arg1.getFragmentManager(), arg3);
    }

    @TargetApi(value=20) private final void zza(Context arg9, int arg10, String arg11, PendingIntent arg12) {
        Notification v9;
        if(arg10 == 18) {
            this.zza(arg9);
            return;
        }

        if(arg12 == null) {
            if(arg10 == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
            }

            return;
        }

        String v0 = ConnectionErrorMessages.getErrorNotificationTitle(arg9, arg10);
        String v1 = ConnectionErrorMessages.getErrorNotificationMessage(arg9, arg10);
        Resources v2 = arg9.getResources();
        Object v3 = arg9.getSystemService("notification");
        if(DeviceProperties.isWearable(arg9)) {
            Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
            Notification$Builder v0_1 = new Notification$Builder(arg9).setSmallIcon(arg9.getApplicationInfo().icon).setPriority(2).setAutoCancel(true).setContentTitle(((CharSequence)v0)).setStyle(new Notification$BigTextStyle().bigText(((CharSequence)v1)));
            if(DeviceProperties.isWearableWithoutPlayStore(arg9)) {
                v0_1.addAction(drawable.common_full_open_on_phone, v2.getString(string.common_open_on_phone), arg12);
            }
            else {
                v0_1.setContentIntent(arg12);
            }

            if((PlatformVersion.isAtLeastO()) && (PlatformVersion.isAtLeastO())) {
                v0_1.setChannelId(this.zza(arg9, ((NotificationManager)v3)));
            }

            v9 = v0_1.build();
        }
        else {
            d v12 = new d(arg9).a(17301642).d(v2.getString(string.common_google_play_services_notification_ticker)).a(System.currentTimeMillis()).b(true).a(arg12).a(((CharSequence)v0)).b(((CharSequence)v1)).c(true).a(new c().a(((CharSequence)v1)));
            if((PlatformVersion.isAtLeastO()) && (PlatformVersion.isAtLeastO())) {
                v12.e(this.zza(arg9, ((NotificationManager)v3)));
            }

            v9 = v12.b();
        }

        switch(arg10) {
            case 1: 
            case 2: 
            case 3: {
                arg10 = 10436;
                GooglePlayServicesUtilLight.zzbt.set(false);
                break;
            }
            default: {
                arg10 = 39789;
                break;
            }
        }

        if(arg11 == null) {
            ((NotificationManager)v3).notify(arg10, v9);
            return;
        }

        ((NotificationManager)v3).notify(arg11, arg10, v9);
    }

    final void zza(Context arg4) {
        new zza(this, arg4).sendEmptyMessageDelayed(1, 120000);
    }

    private final String zzb() {
        Object v0 = GoogleApiAvailability.mLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zzat;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }
}

