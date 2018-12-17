package org.telegram.messenger.browser;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import f.l;
import java.lang.ref.WeakReference;
import java.util.List;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.v;
import org.telegram.customization.i.h;
import org.telegram.customization.j.a.a;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.CustomTabsCopyReceiver;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.ShareBroadcastReceiver;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.support.customtabs.CustomTabsCallback;
import org.telegram.messenger.support.customtabs.CustomTabsClient;
import org.telegram.messenger.support.customtabs.CustomTabsIntent$Builder;
import org.telegram.messenger.support.customtabs.CustomTabsIntent;
import org.telegram.messenger.support.customtabs.CustomTabsServiceConnection;
import org.telegram.messenger.support.customtabs.CustomTabsSession;
import org.telegram.messenger.support.customtabsclient.shared.CustomTabsHelper;
import org.telegram.messenger.support.customtabsclient.shared.ServiceConnection;
import org.telegram.messenger.support.customtabsclient.shared.ServiceConnectionCallback;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_messages_getWebPagePreview;
import org.telegram.tgnet.TLRPC$TL_webPage;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.LaunchActivity;
import utils.a.b;
import utils.view.ToastUtil;

public class Browser {
    class NavigationCallback extends CustomTabsCallback {
        NavigationCallback(org.telegram.messenger.browser.Browser$1 arg1) {
            this();
        }

        private NavigationCallback() {
            super();
        }

        public void onNavigationEvent(int arg1, Bundle arg2) {
        }
    }

    private static WeakReference currentCustomTabsActivity;
    private static CustomTabsClient customTabsClient;
    private static WeakReference customTabsCurrentSession;
    private static String customTabsPackageToBind;
    private static CustomTabsServiceConnection customTabsServiceConnection;
    private static CustomTabsSession customTabsSession;
    private static AlertDialog visibleDialog;

    public Browser() {
        super();
    }

    static CustomTabsClient access$100() {
        return Browser.customTabsClient;
    }

    static CustomTabsClient access$102(CustomTabsClient arg0) {
        Browser.customTabsClient = arg0;
        return arg0;
    }

    static AlertDialog access$202(AlertDialog arg0) {
        Browser.visibleDialog = arg0;
        return arg0;
    }

    public static void bindCustomTabsService(Activity arg3) {
        CustomTabsServiceConnection v1 = null;
        Object v0 = Browser.currentCustomTabsActivity == null ? v1 : Browser.currentCustomTabsActivity.get();
        if(v0 != null && (((Activity)v0)) != arg3) {
            Browser.unbindCustomTabsService(((Activity)v0));
        }

        if(Browser.customTabsClient != null) {
            return;
        }

        Browser.currentCustomTabsActivity = new WeakReference(arg3);
        try {
            if(TextUtils.isEmpty(Browser.customTabsPackageToBind)) {
                Browser.customTabsPackageToBind = CustomTabsHelper.getPackageNameToUse(((Context)arg3));
                if(Browser.customTabsPackageToBind == null) {
                    return;
                }
            }

            Browser.customTabsServiceConnection = new ServiceConnection(new ServiceConnectionCallback() {
                public void onServiceConnected(CustomTabsClient arg3) {
                    Browser.customTabsClient = arg3;
                    if((SharedConfig.customTabs) && Browser.customTabsClient != null) {
                        try {
                            Browser.customTabsClient.warmup(0);
                        }
                        catch(Exception v3) {
                            FileLog.e(((Throwable)v3));
                        }
                    }
                }

                public void onServiceDisconnected() {
                    Browser.customTabsClient = null;
                }
            });
            if(CustomTabsClient.bindCustomTabsService(((Context)arg3), Browser.customTabsPackageToBind, Browser.customTabsServiceConnection)) {
                return;
            }

            Browser.customTabsServiceConnection = v1;
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }
    }

    private static CustomTabsSession getCurrentSession() {
        CustomTabsSession v0;
        if(Browser.customTabsCurrentSession == null) {
            v0 = null;
        }
        else {
            Object v0_1 = Browser.customTabsCurrentSession.get();
        }

        return v0;
    }

    private static CustomTabsSession getSession() {
        CustomTabsSession v1 = null;
        if(Browser.customTabsClient == null) {
            Browser.customTabsSession = v1;
        }
        else if(Browser.customTabsSession == null) {
            Browser.customTabsSession = Browser.customTabsClient.newSession(new NavigationCallback(((org.telegram.messenger.browser.Browser$1)v1)));
            Browser.setCurrentSession(Browser.customTabsSession);
        }

        return Browser.customTabsSession;
    }

    public static boolean isInternalUri(Uri arg4, boolean[] arg5) {
        String v4;
        String v0 = arg4.getHost();
        v0 = v0 != null ? v0.toLowerCase() : "";
        if("tg".equals(arg4.getScheme())) {
            return 1;
        }

        if("telegram.dog".equals(v0)) {
            v4 = arg4.getPath();
            if(v4 != null && v4.length() > 1) {
                v4 = v4.substring(1).toLowerCase();
                if(!v4.startsWith("blog") && !v4.equals("iv") && !v4.startsWith("faq")) {
                    if(v4.equals("apps")) {
                    }
                    else {
                        return 1;
                    }
                }

                if(arg5 != null) {
                    arg5[0] = true;
                }

                return 0;
            }
        }
        else {
            if(!"telegram.me".equals(v0) && !"t.me".equals(v0) && !"telesco.pe".equals(v0)) {
                return 0;
            }

            v4 = arg4.getPath();
            if(v4 == null) {
                return 0;
            }

            if(v4.length() <= 1) {
                return 0;
            }

            if(v4.substring(1).toLowerCase().equals("iv")) {
                if(arg5 != null) {
                    arg5[0] = true;
                }

                return 0;
            }

            return 1;
        }

        return 0;
    }

    public static boolean isInternalUrl(String arg0, boolean[] arg1) {
        return Browser.isInternalUri(Uri.parse(arg0), arg1);
    }

    public static boolean isPassportUrl(String arg1) {
        try {
            arg1 = arg1.toLowerCase();
            if(!arg1.startsWith("tg:passport") && !arg1.startsWith("tg://passport") && !arg1.startsWith("tg:secureid")) {
                if(arg1.contains("resolve")) {
                    if(!arg1.contains("domain=telegrampassport")) {
                        return 0;
                    }

                    return 1;
                }

                return 0;
            }
        }
        catch(Throwable ) {
            return 0;
        }

        return 1;
    }

    static void lambda$null$0(AlertDialog[] arg3, TLObject arg4, int arg5, Uri arg6, Context arg7, boolean arg8) {
        try {
            arg3[0].dismiss();
            goto label_3;
        }
        catch(Throwable ) {
        label_3:
            arg3[0] = null;
            int v1 = 1;
            if(!(arg4 instanceof TL_messageMediaWebPage) || !(((TL_messageMediaWebPage)arg4).webpage instanceof TL_webPage) || ((TL_messageMediaWebPage)arg4).webpage.cached_page == null) {
                v1 = 0;
            }
            else {
                NotificationCenter.getInstance(arg5).postNotificationName(NotificationCenter.openArticle, new Object[]{((TL_messageMediaWebPage)arg4).webpage, arg6.toString()});
            }

            if(v1 == 0) {
                Browser.openUrl(arg7, arg6, arg8, false);
            }

            return;
        }
    }

    static void lambda$null$2(int arg1, DialogInterface arg2, int arg3) {
        ConnectionsManager.getInstance(UserConfig.selectedAccount).cancelRequest(arg1, true);
        try {
            arg2.dismiss();
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }
    }

    static void lambda$openUrl$1(AlertDialog[] arg7, int arg8, Uri arg9, Context arg10, boolean arg11, TLObject arg12, TL_error arg13) {
        AndroidUtilities.runOnUIThread(new -$$Lambda$Browser$37_x46nyg5sVKyQShvHeLalxsGQ(arg7, arg12, arg8, arg9, arg10, arg11));
    }

    static void lambda$openUrl$3(AlertDialog[] arg5, int arg6) {
        if(arg5[0] == null) {
            return;
        }

        try {
            arg5[0].setMessage(LocaleController.getString("Loading", 2131625103));
            arg5[0].setCanceledOnTouchOutside(false);
            arg5[0].setCancelable(false);
            arg5[0].setButton(-2, LocaleController.getString("Cancel", 2131624257), new -$$Lambda$Browser$YFWnI_Rx-r8_fTM6iyHQarint2A(arg6));
            arg5[0].show();
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public static void openUrl(Context arg16, Uri arg17, boolean arg18, boolean arg19) {
        Intent v0_2;
        int v4;
        String[] v3_1;
        List v2_1;
        Context v7 = arg16;
        Uri v8 = arg17;
        if(v7 != null && v8 != null) {
            int v3 = UserConfig.selectedAccount;
            boolean[] v0 = new boolean[]{false};
            boolean v11 = Browser.isInternalUri(v8, v0);
            if(arg19) {
                try {
                    if(!arg17.getHost().toLowerCase().equals("telegra.ph") && !arg17.toString().toLowerCase().contains("telegram.org/faq")) {
                        goto label_46;
                    }

                    AlertDialog[] v12 = new AlertDialog[]{new AlertDialog(v7, 1)};
                    TL_messages_getWebPagePreview v13 = new TL_messages_getWebPagePreview();
                    v13.message = arg17.toString();
                    AndroidUtilities.runOnUIThread(new -$$Lambda$Browser$tzkgx-C1l2oH_szh01yqocI6uLg(v12, ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(((TLObject)v13), new -$$Lambda$Browser$UGNHp8wusapZ_BRGXGaD_Ayz4-I(v12, v3, arg17, arg16, arg18))), 1000);
                    return;
                }
                catch(Exception ) {
                    try {
                    label_46:
                        String v1 = arg17.getScheme() != null ? arg17.getScheme().toLowerCase() : "";
                        String v2 = v1;
                        if(!arg18) {
                            goto label_205;
                        }

                        if(!SharedConfig.customTabs) {
                            goto label_205;
                        }

                        if(v11) {
                            goto label_205;
                        }

                        if(v2.equals("tel")) {
                            goto label_205;
                        }
                    }
                    catch(Exception v0_1) {
                        goto label_204;
                    }

                    String[] v1_1 = null;
                    try {
                        v2_1 = arg16.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com")), 0);
                        if(v2_1 == null) {
                            goto label_92;
                        }
                        else if(!v2_1.isEmpty()) {
                            v3_1 = new String[v2_1.size()];
                            v4 = 0;
                        }
                        else {
                            goto label_92;
                        }
                    }
                    catch(Exception ) {
                        goto label_92;
                    }

                    try {
                        while(true) {
                        label_74:
                            if(v4 < v2_1.size()) {
                                v3_1[v4] = v2_1.get(v4).activityInfo.packageName;
                                if(BuildVars.LOGS_ENABLED) {
                                    FileLog.d("default browser name = " + v3_1[v4]);
                                }

                                break;
                            }

                            goto label_93;
                        }
                    }
                    catch(Exception ) {
                        goto label_93;
                    }

                    ++v4;
                    goto label_74;
                label_92:
                    v3_1 = v1_1;
                    try {
                    label_93:
                        v2_1 = arg16.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", v8), 0);
                        if(v3_1 == null) {
                            goto label_118;
                        }
                    }
                    catch(Exception ) {
                        v2_1 = ((List)v1_1);
                        goto label_161;
                    }

                    int v1_2 = 0;
                    try {
                        while(true) {
                            if(v1_2 < v2_1.size()) {
                                v4 = 0;
                                while(v4 < v3_1.length) {
                                    if(v3_1[v4].equals(v2_1.get(v1_2).activityInfo.packageName)) {
                                        v2_1.remove(v1_2);
                                        --v1_2;
                                    }
                                    else {
                                        ++v4;
                                        continue;
                                    }

                                    break;
                                }

                                ++v1_2;
                                continue;
                            }

                            goto label_139;
                        }

                    label_118:
                        for(v1_2 = 0; v1_2 < v2_1.size(); ++v1_2) {
                            if((v2_1.get(v1_2).activityInfo.packageName.toLowerCase().contains("browser")) || (v2_1.get(v1_2).activityInfo.packageName.toLowerCase().contains("chrome"))) {
                                v2_1.remove(v1_2);
                                --v1_2;
                            }
                        }

                    label_139:
                        if(!BuildVars.LOGS_ENABLED) {
                            goto label_161;
                        }

                        for(v1_2 = 0; true; ++v1_2) {
                            if(v1_2 >= v2_1.size()) {
                                goto label_161;
                            }

                            FileLog.d("device has " + v2_1.get(v1_2).activityInfo.packageName + " to open " + arg17.toString());
                        }
                    }
                    catch(Exception ) {
                        try {
                        label_161:
                            if(!v0[0] && v2_1 != null && !v2_1.isEmpty()) {
                                goto label_205;
                            }

                            v0_2 = new Intent(ApplicationLoader.applicationContext, ShareBroadcastReceiver.class);
                            v0_2.setAction("android.intent.action.SEND");
                            PendingIntent v1_3 = PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 0, new Intent(ApplicationLoader.applicationContext, CustomTabsCopyReceiver.class), 134217728);
                            Builder v2_2 = new Builder(Browser.getSession());
                            v2_2.addMenuItem(LocaleController.getString("CopyLink", 2131624493), v1_3);
                            v2_2.setToolbarColor(Theme.getColor("actionBarDefault"));
                            v2_2.setShowTitle(true);
                            v2_2.setActionButton(BitmapFactory.decodeResource(arg16.getResources(), 2131230756), LocaleController.getString("ShareFile", 2131626060), PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 0, v0_2, 0), false);
                            CustomTabsIntent v0_3 = v2_2.build();
                            v0_3.setUseNewTask();
                            v0_3.launchUrl(v7, v8);
                            return;
                        }
                        catch(Exception v0_1) {
                        label_204:
                            FileLog.e(((Throwable)v0_1));
                        }
                    }

                    try {
                    label_205:
                        v0_2 = new Intent("android.intent.action.VIEW", v8);
                        if(v11) {
                            v0_2.setComponent(new ComponentName(arg16.getPackageName(), LaunchActivity.class.getName()));
                        }

                        v0_2.putExtra("create_new_tab", true);
                        v0_2.putExtra("com.android.browser.application_id", arg16.getPackageName());
                        v7.startActivity(v0_2);
                    }
                    catch(Exception v0_1) {
                        FileLog.e(((Throwable)v0_1));
                    }

                    return;
                }
            }

            goto label_46;
        }
    }

    public static void openUrl(Context arg1, Uri arg2) {
        Browser.openUrl(arg1, arg2, true);
    }

    public static void openUrl(Context arg2, Uri arg3, boolean arg4) {
        if(b.r()) {
            org.telegram.messenger.browser.Browser$2 v0 = new a(true, arg2, arg3, arg4) {
                protected h getApiCallback() {
                    return new h() {
                        boolean didReceivedResponse;

                        public void checkSafeFailure(Object arg1, aa arg2, Object arg3, l arg4) {
                            this.didReceivedResponse = true;
                            Browser.openUrl(org.telegram.messenger.browser.Browser$2.this.val$context, org.telegram.messenger.browser.Browser$2.this.val$uri, org.telegram.messenger.browser.Browser$2.this.val$allowCustom, true);
                            org.telegram.messenger.browser.Browser$2.this.detach();
                        }

                        public void checkSafeResult(Integer arg7, aa arg8, Object arg9, l arg10) {
                            org.telegram.messenger.browser.Browser$2$1$1 v9;
                            String v8;
                            org.telegram.ui.ActionBar.AlertDialog$Builder v7;
                            this.didReceivedResponse = true;
                            Log.d("alireza", "alireza safe link" + arg7);
                            if(arg7.intValue() != 0) {
                                DialogInterface$OnClickListener v0 = null;
                                int v1 = 2131624257;
                                int v2 = 2131625420;
                                int v3 = 2131626278;
                                int v4 = 2131625224;
                                if(arg7.intValue() == 102) {
                                    v7 = new org.telegram.ui.ActionBar.AlertDialog$Builder(org.telegram.messenger.browser.Browser$2.this.val$context);
                                    v7.setTitle(LocaleController.getString("AppName", v4));
                                    v7.setMessage(LocaleController.formatString("UnsafeUrl", v3, new Object[0]));
                                    v8 = LocaleController.getString("OK", v2);
                                    v9 = new DialogInterface$OnClickListener() {
                                        public void onClick(DialogInterface arg3, int arg4) {
                                            Browser.openUrl(this.this$1.this$0.val$context, this.this$1.this$0.val$uri, this.this$1.this$0.val$allowCustom, true);
                                        }
                                    };
                                }
                                else if(arg7.intValue() == 104) {
                                    v7 = new org.telegram.ui.ActionBar.AlertDialog$Builder(org.telegram.messenger.browser.Browser$2.this.val$context);
                                    v7.setTitle(LocaleController.getString("AppName", v4));
                                    v7.setMessage(LocaleController.formatString("pornUrl", 2131626997, new Object[0]));
                                    v8 = LocaleController.getString("OK", v2);
                                    org.telegram.messenger.browser.Browser$2$1$2 v9_1 = new DialogInterface$OnClickListener() {
                                        public void onClick(DialogInterface arg3, int arg4) {
                                            Browser.openUrl(this.this$1.this$0.val$context, this.this$1.this$0.val$uri, this.this$1.this$0.val$allowCustom, true);
                                        }
                                    };
                                }
                                else {
                                    goto label_70;
                                }

                                v7.setPositiveButton(((CharSequence)v8), ((DialogInterface$OnClickListener)v9));
                                v7.setNegativeButton(LocaleController.getString("Cancel", v1), v0);
                                Browser.showAlertDialog(v7);
                                goto label_80;
                            label_70:
                                if(arg7.intValue() != 101) {
                                    goto label_12;
                                }

                                ToastUtil.a(ApplicationLoader.applicationContext, LocaleController.formatString("UnsafeUrl", v3, new Object[0])).show();
                                return;
                            }
                            else {
                            label_12:
                                Browser.openUrl(org.telegram.messenger.browser.Browser$2.this.val$context, org.telegram.messenger.browser.Browser$2.this.val$uri, org.telegram.messenger.browser.Browser$2.this.val$allowCustom, true);
                            }

                        label_80:
                            org.telegram.messenger.browser.Browser$2.this.detach();
                        }

                        public void checkSafeStart(Object arg3, aa arg4) {
                            this.didReceivedResponse = false;
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    if(!this.this$1.didReceivedResponse) {
                                        this.this$1.checkSafeFailure(null, null, null, null);
                                    }
                                }
                            }, 2000);
                        }
                    };
                }
            };
            ((a)v0).attach();
            String v2 = arg3.toString().replaceAll("^\"|\"$", "");
            Log.d("alireza", "alireza ckclicked link" + v2);
            ((a)v0).api.b(ab.a(v.a("text/plain"), v2));
        }
        else {
            Browser.openUrl(arg2, arg3, arg4, true);
        }
    }

    public static void openUrl(Context arg1, String arg2) {
        if(arg2 == null) {
            return;
        }

        Browser.openUrl(arg1, Uri.parse(arg2), true);
    }

    public static void openUrl(Context arg0, String arg1, boolean arg2) {
        if(arg0 != null) {
            if(arg1 == null) {
            }
            else {
                Browser.openUrl(arg0, Uri.parse(arg1), arg2);
            }
        }
    }

    public static void openUrl(Context arg0, String arg1, boolean arg2, boolean arg3) {
        Browser.openUrl(arg0, Uri.parse(arg1), arg2, arg3);
    }

    public static void openUrlSls(Context arg1, Uri arg2, boolean arg3) {
        if(arg1 != null && arg2 != null) {
            try {
                Intent v3 = new Intent("android.intent.action.VIEW", arg2);
                v3.addFlags(268435456);
                v3.putExtra("com.android.browser.application_id", arg1.getPackageName());
                arg1.startActivity(v3);
            }
            catch(Exception v1) {
                v1.printStackTrace();
            }
        }
    }

    private static void setCurrentSession(CustomTabsSession arg1) {
        Browser.customTabsCurrentSession = new WeakReference(arg1);
    }

    public static AlertDialog showAlertDialog(org.telegram.ui.ActionBar.AlertDialog$Builder arg2) {
        AlertDialog v0 = null;
        try {
            if(Browser.visibleDialog == null) {
                goto label_9;
            }

            Browser.visibleDialog.dismiss();
            Browser.visibleDialog = v0;
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
        label_9:
            Browser.visibleDialog = arg2.show();
            Browser.visibleDialog.setCanceledOnTouchOutside(true);
            Browser.visibleDialog.setOnDismissListener(new DialogInterface$OnDismissListener() {
                public void onDismiss(DialogInterface arg1) {
                    Browser.visibleDialog = null;
                }
            });
            return Browser.visibleDialog;
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
            return v0;
        }
    }

    public static void unbindCustomTabsService(Activity arg2) {
        if(Browser.customTabsServiceConnection == null) {
            return;
        }

        CustomTabsClient v1 = null;
        if(Browser.currentCustomTabsActivity == null) {
            Activity v0 = ((Activity)v1);
        }
        else {
            Object v0_1 = Browser.currentCustomTabsActivity.get();
        }

        if((((Activity)v0_1)) == arg2) {
            Browser.currentCustomTabsActivity.clear();
        }

        try {
            arg2.unbindService(Browser.customTabsServiceConnection);
            goto label_15;
        }
        catch(Exception ) {
        label_15:
            Browser.customTabsClient = v1;
            Browser.customTabsSession = ((CustomTabsSession)v1);
            return;
        }
    }
}

