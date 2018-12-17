package org.telegram.messenger.support.customtabsclient.shared;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.ApplicationLoader;

public class CustomTabsHelper {
    private static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    static final String BETA_PACKAGE = "com.chrome.beta";
    static final String DEV_PACKAGE = "com.chrome.dev";
    private static final String EXTRA_CUSTOM_TABS_KEEP_ALIVE = "android.support.customtabs.extra.KEEP_ALIVE";
    static final String LOCAL_PACKAGE = "com.google.android.apps.chrome";
    static final String STABLE_PACKAGE = "com.android.chrome";
    private static final String TAG = "CustomTabsHelper";
    private static String sPackageNameToUse;

    private CustomTabsHelper() {
        super();
    }

    public static void addKeepAliveExtra(Context arg2, Intent arg3) {
        arg3.putExtra("android.support.customtabs.extra.KEEP_ALIVE", new Intent().setClassName(arg2.getPackageName(), KeepAliveService.class.getCanonicalName()));
    }

    public static String getPackageNameToUse(Context arg10) {
        String v10_1;
        Object v10;
        CharSequence v3_2;
        if(CustomTabsHelper.sPackageNameToUse != null) {
            return CustomTabsHelper.sPackageNameToUse;
        }

        PackageManager v0 = arg10.getPackageManager();
        Intent v1 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo v3 = v0.resolveActivity(v1, 0);
        String v4 = null;
        if(v3 != null) {
            String v3_1 = v3.activityInfo.packageName;
        }
        else {
            v3_2 = ((CharSequence)v4);
        }

        List v5 = v0.queryIntentActivities(v1, 0);
        ArrayList v6 = new ArrayList();
        Iterator v5_1 = v5.iterator();
        while(v5_1.hasNext()) {
            Object v7 = v5_1.next();
            Intent v8 = new Intent();
            v8.setAction("android.support.customtabs.action.CustomTabsService");
            v8.setPackage(((ResolveInfo)v7).activityInfo.packageName);
            if(v0.resolveService(v8, 0) == null) {
                continue;
            }

            ((List)v6).add(((ResolveInfo)v7).activityInfo.packageName);
        }

        if(((List)v6).isEmpty()) {
            CustomTabsHelper.sPackageNameToUse = v4;
        }
        else {
            goto label_43;
        }

        try {
        label_76:
            if(!"com.sec.android.app.sbrowser".equalsIgnoreCase(CustomTabsHelper.sPackageNameToUse)) {
                goto label_91;
            }

            goto label_80;
        }
        catch(Throwable ) {
            goto label_91;
        }

    label_43:
        if(((List)v6).size() == 1) {
            v10 = ((List)v6).get(0);
        }
        else {
            if(!TextUtils.isEmpty(v3_2) && !CustomTabsHelper.hasSpecializedHandlerIntents(arg10, v1) && (((List)v6).contains(v3_2))) {
                CustomTabsHelper.sPackageNameToUse = ((String)v3_2);
                goto label_76;
            }

            if(((List)v6).contains("com.android.chrome")) {
                v10_1 = "com.android.chrome";
                goto label_46;
            }

            if(((List)v6).contains("com.chrome.beta")) {
                v10_1 = "com.chrome.beta";
                goto label_46;
            }

            if(((List)v6).contains("com.chrome.dev")) {
                v10_1 = "com.chrome.dev";
                goto label_46;
            }

            if(!((List)v6).contains("com.google.android.apps.chrome")) {
                goto label_76;
            }

            v10_1 = "com.google.android.apps.chrome";
        }

    label_46:
        CustomTabsHelper.sPackageNameToUse = ((String)v10);
        goto label_76;
        try {
        label_80:
            PackageManager v10_2 = ApplicationLoader.applicationContext.getPackageManager();
            ApplicationInfo v0_1 = v10_2.getApplicationInfo("com.android.chrome", 0);
            if(v0_1 == null) {
                goto label_91;
            }

            if(!v0_1.enabled) {
                goto label_91;
            }

            v10_2.getPackageInfo("com.android.chrome", 1);
            CustomTabsHelper.sPackageNameToUse = "com.android.chrome";
            goto label_91;
        }
        catch(Throwable ) {
        label_91:
            return CustomTabsHelper.sPackageNameToUse;
        }
    }

    public static String[] getPackages() {
        return new String[]{"", "com.android.chrome", "com.chrome.beta", "com.chrome.dev", "com.google.android.apps.chrome"};
    }

    private static boolean hasSpecializedHandlerIntents(Context arg3, Intent arg4) {
        try {
            List v3 = arg3.getPackageManager().queryIntentActivities(arg4, 64);
            if(v3 != null) {
                if(v3.size() == 0) {
                }
                else {
                    Iterator v3_1 = v3.iterator();
                    while(true) {
                    label_9:
                        if(v3_1.hasNext()) {
                            Object v4 = v3_1.next();
                            IntentFilter v1 = ((ResolveInfo)v4).filter;
                            if(v1 == null) {
                                continue;
                            }
                            else {
                                if(v1.countDataAuthorities() == 0) {
                                    continue;
                                }

                                if(v1.countDataPaths() == 0) {
                                    continue;
                                }
                                else if(((ResolveInfo)v4).activityInfo == null) {
                                    goto label_22;
                                }
                                else {
                                    return 1;
                                }
                            }
                        }
                        else {
                            return 0;
                        }

                        return 0;
                    }
                }
            }

            return 0;
        }
        catch(RuntimeException ) {
            goto label_26;
        }

    label_22:
        goto label_9;
        return 1;
    label_26:
        Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        return 0;
    }
}

