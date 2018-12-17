package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.ad$a;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.api.Scope;
import java.util.Locale;

public class GmsIntents {
    public static final String ACTION_FITNESS_APP_DISCONNECTED = "com.google.android.gms.fitness.app_disconnected";
    public static final String ACTION_ICING_CONTACT_CHANGED = "com.google.android.gms.icing.action.CONTACT_CHANGED";
    public static final String ACTION_SET_GMS_ACCOUNT = "com.google.android.gms.common.SET_GMS_ACCOUNT";
    public static final String ACTION_UDC_SETTING_CHANGED = "com.google.android.gms.udc.action.SETTING_CHANGED";
    public static final String BROADCAST_CIRCLES_CHANGED = "com.google.android.gms.people.BROADCAST_CIRCLES_CHANGED";
    public static final String COMMON_SIGN_IN_EXTRA_PACKAGE_NAME = "SIGN_IN_PACKAGE_NAME";
    public static final String COMMON_SIGN_IN_EXTRA_SAVE_DEFAULT_ACCOUNT = "SIGN_IN_SAVE_DEFAULT_ACCOUNT";
    public static final String COMMON_SIGN_IN_EXTRA_SCOPE_ARRAY = "SIGN_IN_SCOPE_ARRAY";
    public static final String EXTRA_ACCOUNT = "com.google.android.gms.fitness.disconnected_account";
    public static final String EXTRA_APP = "com.google.android.gms.fitness.disconnected_app";
    public static final String EXTRA_ICING_CONTACT_CHANGED_IS_SIGNIFICANT = "com.google.android.gms.icing.extra.isSignificant";
    public static final String EXTRA_SET_GMS_ACCOUNT_NAME = "ACCOUNT_NAME";
    public static final String EXTRA_SET_GMS_ACCOUNT_PACKAGE_NAME = "PACKAGE_NAME";
    public static final String EXTRA_UDC_ACCOUNT_NAME = "com.google.android.gms.udc.extra.accountName";
    public static final String EXTRA_UDC_SETTING_ID_LIST = "com.google.android.gms.udc.extra.settingIdList";
    public static final String GOOGLE_NOW_PACKAGE_NAME = "com.google.android.googlequicksearchbox";
    public static final String MIME_ACTIVITY_DISCONNECT_TYPE = "vnd.google.android.fitness/app_disconnect";
    public static final String PERMISSION_GMS_INTERNAL_BROADCAST = "com.google.android.gms.permission.INTERNAL_BROADCAST";
    private static final Uri zztz;
    private static final Uri zzua;

    static {
        Uri v0 = Uri.parse("https://plus.google.com/");
        GmsIntents.zztz = v0;
        GmsIntents.zzua = v0.buildUpon().appendPath("circles").appendPath("find").build();
    }

    private GmsIntents() {
        super();
    }

    public static Intent createAndroidWearUpdateIntent() {
        Intent v0 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        v0.setPackage("com.google.android.wearable.app");
        return v0;
    }

    public static Intent createChooseGmsAccountIntent() {
        return AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"}, true, null, null, null, null, true);
    }

    public static Intent createChooseGmsAccountWithConsentIntent(String arg2, Scope[] arg3, boolean arg4) {
        Intent v0 = new Intent("com.google.android.gms.signin.action.SIGN_IN");
        v0.putExtra("SIGN_IN_PACKAGE_NAME", arg2);
        v0.putExtra("SIGN_IN_SCOPE_ARRAY", ((Parcelable[])arg3));
        v0.putExtra("SIGN_IN_SAVE_DEFAULT_ACCOUNT", arg4);
        return v0;
    }

    public static Intent createDateSettingsIntent() {
        return new Intent("android.settings.DATE_SETTINGS");
    }

    public static Intent createFindPeopleIntent(Context arg1) {
        return GmsIntents.zza(arg1, GmsIntents.zzua);
    }

    public static Intent createPlayStoreGamesIntent(Context arg3) {
        Intent v0 = new Intent("android.intent.action.VIEW");
        v0.setData(Uri.parse("http://play.google.com/store/apps/category/GAME"));
        v0.addFlags(524288);
        v0.setPackage("com.android.vending");
        if(arg3.getPackageManager().resolveActivity(v0, 65536) == null) {
            Intent v3 = new Intent(v0.getAction(), v0.getData());
            v3.setFlags(v0.getFlags());
            return v3;
        }

        return v0;
    }

    public static Intent createPlayStoreIntent(String arg3, String arg4) {
        Intent v0 = new Intent("android.intent.action.VIEW");
        Uri$Builder v3 = Uri.parse("market://details").buildUpon().appendQueryParameter("id", arg3);
        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            v3.appendQueryParameter("pcampaignid", arg4);
        }

        v0.setData(v3.build());
        v0.setPackage("com.android.vending");
        v0.addFlags(524288);
        return v0;
    }

    public static Intent createPlayStoreIntent(String arg1) {
        return GmsIntents.createPlayStoreIntent(arg1, null);
    }

    public static Intent createPlayStoreLightPurchaseFlowIntent(Context arg4, String arg5, String arg6) {
        Intent v0 = new Intent("com.android.vending.billing.PURCHASE");
        v0.addCategory("android.intent.category.DEFAULT");
        v0.setPackage("com.android.vending");
        if(!TextUtils.isEmpty(((CharSequence)arg5))) {
            v0.putExtra("authAccount", arg5);
        }

        v0.putExtra("backend", 3);
        v0.putExtra("document_type", 1);
        v0.putExtra("full_docid", arg6);
        v0.putExtra("backend_docid", arg6);
        v0.putExtra("offer_type", 1);
        if(!GmsIntents.isIntentResolvable(arg4.getPackageManager(), v0)) {
            v0 = new Intent("android.intent.action.VIEW");
            v0.setData(Uri.parse(String.format(Locale.US, "https://play.google.com/store/apps/details?id=%1$s&rdid=%1$s&rdot=%2$d", arg6, Integer.valueOf(1))));
            v0.setPackage("com.android.vending");
            v0.putExtra("use_direct_purchase", true);
        }

        return v0;
    }

    public static Intent createSettingsIntent(String arg2) {
        Uri v2 = Uri.fromParts("package", arg2, null);
        Intent v0 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        v0.setData(v2);
        return v0;
    }

    public static Intent createShareOnPlusIntent(Activity arg1, String arg2, String arg3) {
        Intent v2 = a.a(arg1).b(arg2).a(((CharSequence)arg3)).a("text/plain").a();
        v2.setPackage("com.google.android.apps.plus");
        if(GmsIntents.isIntentResolvable(arg1.getPackageManager(), v2)) {
            return v2;
        }

        return GmsIntents.createPlayStoreIntent("com.google.android.apps.plus");
    }

    public static Intent createShowProfileIntent(Context arg3, String arg4) {
        return GmsIntents.zza(arg3, Uri.parse(String.format("https://plus.google.com/%s/about", arg4)));
    }

    public static Intent getFitnessAppDisconnectedIntent(String arg2, String arg3) {
        Intent v0 = new Intent();
        v0.setPackage("com.google.android.gms");
        v0.setAction("com.google.android.gms.fitness.app_disconnected");
        v0.setType("vnd.google.android.fitness/app_disconnect");
        v0.putExtra("com.google.android.gms.fitness.disconnected_app", arg2);
        v0.putExtra("com.google.android.gms.fitness.disconnected_account", arg3);
        return v0;
    }

    public static Uri getPlayStoreUri(String arg2) {
        return Uri.parse("https://play.google.com/store/apps/details").buildUpon().appendQueryParameter("id", arg2).build();
    }

    public static boolean isIntentResolvable(PackageManager arg1, Intent arg2) {
        if(arg1.resolveActivity(arg2, 65536) != null) {
            return 1;
        }

        return 0;
    }

    public static void sendIcingContactChangedBroadcast(Context arg5, boolean arg6) {
        Intent v0 = new Intent("com.google.android.gms.icing.action.CONTACT_CHANGED").setPackage("com.google.android.gms").putExtra("com.google.android.gms.icing.extra.isSignificant", arg6);
        if(Log.isLoggable("GmsIntents", 2)) {
            String v2 = v0.getAction();
            StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 98);
            v4.append("Icing detected contact change, broadcasting it with intent action: ");
            v4.append(v2);
            v4.append(" and isSignificant extra: ");
            v4.append(arg6);
            Log.v("GmsIntents", v4.toString());
        }

        arg5.sendBroadcast(v0);
    }

    public static void sendSetGmsAccountIntent(Context arg2, String arg3, String arg4) {
        Intent v0 = new Intent("com.google.android.gms.common.SET_GMS_ACCOUNT");
        v0.putExtra("ACCOUNT_NAME", arg3);
        v0.putExtra("PACKAGE_NAME", arg4);
        v0.setPackage("com.google.android.gms");
        arg2.sendBroadcast(v0, "com.google.android.gms.permission.INTERNAL_BROADCAST");
    }

    public static void sendUdcSettingsChangedBroadcast(Context arg2, String arg3, int[] arg4) {
        GmsIntents.zza("com.google.android.gms", arg2, arg3, arg4);
        if(!GoogleSignatureVerifier.getInstance(arg2).isPackageGoogleSigned("com.google.android.googlequicksearchbox")) {
            if(Log.isLoggable("GmsIntents", 5)) {
                Log.w("GmsIntents", "Google now certificate not valid. UDC settings broadcast will not be sent.");
            }

            return;
        }

        GmsIntents.zza("com.google.android.googlequicksearchbox", arg2, arg3, arg4);
    }

    private static Intent zza(Context arg2, Uri arg3) {
        Intent v0 = new Intent("android.intent.action.VIEW");
        v0.setData(arg3);
        v0.setPackage("com.google.android.apps.plus");
        if(GmsIntents.isIntentResolvable(arg2.getPackageManager(), v0)) {
            return v0;
        }

        return GmsIntents.createPlayStoreIntent("com.google.android.apps.plus");
    }

    private static void zza(String arg3, Context arg4, String arg5, int[] arg6) {
        Intent v5 = new Intent("com.google.android.gms.udc.action.SETTING_CHANGED").setPackage(arg3).putExtra("com.google.android.gms.udc.extra.accountName", arg5).putExtra("com.google.android.gms.udc.extra.settingIdList", arg6);
        if(Log.isLoggable("GmsIntents", 3)) {
            String v0 = v5.getAction();
            StringBuilder v2 = new StringBuilder(String.valueOf(arg3).length() + 72 + String.valueOf(v0).length());
            v2.append("UDC settings changed, sending broadcast to package ");
            v2.append(arg3);
            v2.append(" with intent action: ");
            v2.append(v0);
            Log.d("GmsIntents", v2.toString());
        }

        arg4.sendBroadcast(v5);
    }
}

