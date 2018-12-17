package org.telegram.messenger;

import android.annotation.TargetApi;
import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import java.io.Closeable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NotificationBadge {
    public class AdwHomeBadger implements Badger {
        public static final String CLASSNAME = "CNAME";
        public static final String COUNT = "COUNT";
        public static final String INTENT_UPDATE_COUNTER = "org.adw.launcher.counter.SEND";
        public static final String PACKAGENAME = "PNAME";

        public AdwHomeBadger() {
            super();
        }

        public void executeBadge(int arg4) {
            Intent v0 = new Intent("org.adw.launcher.counter.SEND");
            v0.putExtra("PNAME", NotificationBadge.componentName.getPackageName());
            v0.putExtra("CNAME", NotificationBadge.componentName.getClassName());
            v0.putExtra("COUNT", arg4);
            if(NotificationBadge.canResolveBroadcast(v0)) {
                AndroidUtilities.runOnUIThread(new Runnable(v0) {
                    public void run() {
                        ApplicationLoader.applicationContext.sendBroadcast(this.val$intent);
                    }
                });
            }
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"org.adw.launcher", "org.adwfreak.launcher"});
        }
    }

    public class ApexHomeBadger implements Badger {
        private static final String CLASS = "class";
        private static final String COUNT = "count";
        private static final String INTENT_UPDATE_COUNTER = "com.anddoes.launcher.COUNTER_CHANGED";
        private static final String PACKAGENAME = "package";

        public ApexHomeBadger() {
            super();
        }

        public void executeBadge(int arg4) {
            Intent v0 = new Intent("com.anddoes.launcher.COUNTER_CHANGED");
            v0.putExtra("package", NotificationBadge.componentName.getPackageName());
            v0.putExtra("count", arg4);
            v0.putExtra("class", NotificationBadge.componentName.getClassName());
            if(NotificationBadge.canResolveBroadcast(v0)) {
                AndroidUtilities.runOnUIThread(new Runnable(v0) {
                    public void run() {
                        ApplicationLoader.applicationContext.sendBroadcast(this.val$intent);
                    }
                });
            }
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.anddoes.launcher"});
        }
    }

    public class AsusHomeBadger implements Badger {
        private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
        private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
        private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";

        public AsusHomeBadger() {
            super();
        }

        public void executeBadge(int arg3) {
            Intent v0 = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            v0.putExtra("badge_count", arg3);
            v0.putExtra("badge_count_package_name", NotificationBadge.componentName.getPackageName());
            v0.putExtra("badge_count_class_name", NotificationBadge.componentName.getClassName());
            v0.putExtra("badge_vip_count", 0);
            if(NotificationBadge.canResolveBroadcast(v0)) {
                AndroidUtilities.runOnUIThread(new Runnable(v0) {
                    public void run() {
                        ApplicationLoader.applicationContext.sendBroadcast(this.val$intent);
                    }
                });
            }
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.asus.launcher"});
        }
    }

    public interface Badger {
        void executeBadge(int arg1);

        List getSupportLaunchers();
    }

    public class DefaultBadger implements Badger {
        private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
        private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
        private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";

        public DefaultBadger() {
            super();
        }

        public void executeBadge(int arg3) {
            Intent v0 = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            v0.putExtra("badge_count", arg3);
            v0.putExtra("badge_count_package_name", NotificationBadge.componentName.getPackageName());
            v0.putExtra("badge_count_class_name", NotificationBadge.componentName.getClassName());
            AndroidUtilities.runOnUIThread(new Runnable(v0) {
                public void run() {
                    try {
                        ApplicationLoader.applicationContext.sendBroadcast(this.val$intent);
                        return;
                    }
                    catch(Exception ) {
                        return;
                    }
                }
            });
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"fr.neamar.kiss", "com.quaap.launchtime", "com.quaap.launchtime_official"});
        }
    }

    public class HuaweiHomeBadger implements Badger {
        public HuaweiHomeBadger() {
            super();
        }

        public void executeBadge(int arg4) {
            Bundle v0 = new Bundle();
            v0.putString("package", ApplicationLoader.applicationContext.getPackageName());
            v0.putString("class", NotificationBadge.componentName.getClassName());
            v0.putInt("badgenumber", arg4);
            AndroidUtilities.runOnUIThread(new Runnable(v0) {
                public void run() {
                    try {
                        ApplicationLoader.applicationContext.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, this.val$localBundle);
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            });
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.huawei.android.launcher"});
        }
    }

    public class NewHtcHomeBadger implements Badger {
        public static final String COUNT = "count";
        public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
        public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
        public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
        public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
        public static final String PACKAGENAME = "packagename";

        public NewHtcHomeBadger() {
            super();
        }

        public void executeBadge(int arg5) {
            Intent v0 = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
            v0.putExtra("com.htc.launcher.extra.COMPONENT", NotificationBadge.componentName.flattenToShortString());
            v0.putExtra("com.htc.launcher.extra.COUNT", arg5);
            Intent v1 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
            v1.putExtra("packagename", NotificationBadge.componentName.getPackageName());
            v1.putExtra("count", arg5);
            if((NotificationBadge.canResolveBroadcast(v0)) || (NotificationBadge.canResolveBroadcast(v1))) {
                AndroidUtilities.runOnUIThread(new Runnable(v0, v1) {
                    public void run() {
                        ApplicationLoader.applicationContext.sendBroadcast(this.val$intent1);
                        ApplicationLoader.applicationContext.sendBroadcast(this.val$intent);
                    }
                });
            }
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.htc.launcher"});
        }
    }

    public class NovaHomeBadger implements Badger {
        private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
        private static final String COUNT = "count";
        private static final String TAG = "tag";

        public NovaHomeBadger() {
            super();
        }

        public void executeBadge(int arg5) {
            ContentValues v0 = new ContentValues();
            v0.put("tag", NotificationBadge.componentName.getPackageName() + "/" + NotificationBadge.componentName.getClassName());
            v0.put("count", Integer.valueOf(arg5));
            ApplicationLoader.applicationContext.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), v0);
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.teslacoilsw.launcher"});
        }
    }

    public class OPPOHomeBader implements Badger {
        private static final String INTENT_ACTION = "com.oppo.unsettledevent";
        private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
        private static final String INTENT_EXTRA_BADGE_COUNT = "number";
        private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
        private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
        private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
        private int mCurrentTotalCount;

        public OPPOHomeBader() {
            super();
            this.mCurrentTotalCount = -1;
        }

        public void executeBadge(int arg2) {
            if(this.mCurrentTotalCount == arg2) {
                return;
            }

            this.mCurrentTotalCount = arg2;
            this.executeBadgeByContentProvider(arg2);
        }

        @TargetApi(value=11) private void executeBadgeByContentProvider(int arg5) {
            try {
                Bundle v0 = new Bundle();
                v0.putInt("app_badge_count", arg5);
                ApplicationLoader.applicationContext.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, v0);
                return;
            }
            catch(Throwable ) {
                return;
            }
        }

        public List getSupportLaunchers() {
            return Collections.singletonList("com.oppo.launcher");
        }
    }

    public class SamsungHomeBadger implements Badger {
        private static final String[] CONTENT_PROJECTION = null;
        private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";
        private static DefaultBadger defaultBadger;

        static {
            SamsungHomeBadger.CONTENT_PROJECTION = new String[]{"_id", "class"};
        }

        public SamsungHomeBadger() {
            super();
        }

        public void executeBadge(int arg12) {
            Cursor v1;
            try {
                if(SamsungHomeBadger.defaultBadger == null) {
                    SamsungHomeBadger.defaultBadger = new DefaultBadger();
                }

                SamsungHomeBadger.defaultBadger.executeBadge(arg12);
                goto label_7;
            }
            catch(Exception ) {
            label_7:
                Uri v0 = Uri.parse("content://com.sec.badge/apps?notify=true");
                ContentResolver v7 = ApplicationLoader.applicationContext.getContentResolver();
                Cursor v8 = null;
                try {
                    v1 = v7.query(v0, SamsungHomeBadger.CONTENT_PROJECTION, "package=?", new String[]{NotificationBadge.componentName.getPackageName()}, null);
                    if(v1 == null) {
                        goto label_52;
                    }
                }
                catch(Throwable v12) {
                    v1 = v8;
                    goto label_56;
                }

                try {
                    String v2 = NotificationBadge.componentName.getClassName();
                    int v3;
                    for(v3 = 0; v1.moveToNext(); v3 = 1) {
                        v7.update(v0, this.getContentValues(NotificationBadge.componentName, arg12, false), "_id=?", new String[]{String.valueOf(v1.getInt(0))});
                        if(!v2.equals(v1.getString(v1.getColumnIndex("class")))) {
                            continue;
                        }
                    }

                    if(v3 == 0) {
                        v7.insert(v0, this.getContentValues(NotificationBadge.componentName, arg12, true));
                    }

                    goto label_52;
                }
                catch(Throwable v12) {
                }

            label_56:
                NotificationBadge.close(v1);
                throw v12;
            label_52:
                NotificationBadge.close(v1);
                return;
            }
        }

        private ContentValues getContentValues(ComponentName arg3, int arg4, boolean arg5) {
            ContentValues v0 = new ContentValues();
            if(arg5) {
                v0.put("package", arg3.getPackageName());
                v0.put("class", arg3.getClassName());
            }

            v0.put("badgecount", Integer.valueOf(arg4));
            return v0;
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.sec.android.app.launcher", "com.sec.android.app.twlauncher"});
        }
    }

    public class SonyHomeBadger implements Badger {
        private final Uri BADGE_CONTENT_URI;
        private static final String INTENT_ACTION = "com.sonyericsson.home.action.UPDATE_BADGE";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";
        private static final String INTENT_EXTRA_MESSAGE = "com.sonyericsson.home.intent.extra.badge.MESSAGE";
        private static final String INTENT_EXTRA_PACKAGE_NAME = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";
        private static final String INTENT_EXTRA_SHOW_MESSAGE = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";
        private static final String PROVIDER_COLUMNS_ACTIVITY_NAME = "activity_name";
        private static final String PROVIDER_COLUMNS_BADGE_COUNT = "badge_count";
        private static final String PROVIDER_COLUMNS_PACKAGE_NAME = "package_name";
        private static final String PROVIDER_CONTENT_URI = "content://com.sonymobile.home.resourceprovider/badge";
        private static final String SONY_HOME_PROVIDER_NAME = "com.sonymobile.home.resourceprovider";
        private static AsyncQueryHandler mQueryHandler;

        public SonyHomeBadger() {
            super();
            this.BADGE_CONTENT_URI = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");
        }

        public void executeBadge(int arg2) {
            if(SonyHomeBadger.sonyBadgeContentProviderExists()) {
                this.executeBadgeByContentProvider(arg2);
            }
            else {
                SonyHomeBadger.executeBadgeByBroadcast(arg2);
            }
        }

        private static void executeBadgeByBroadcast(int arg3) {
            Intent v0 = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
            v0.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", NotificationBadge.componentName.getPackageName());
            v0.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", NotificationBadge.componentName.getClassName());
            v0.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(arg3));
            String v1 = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";
            boolean v3 = arg3 > 0 ? true : false;
            v0.putExtra(v1, v3);
            AndroidUtilities.runOnUIThread(new Runnable(v0) {
                public void run() {
                    ApplicationLoader.applicationContext.sendBroadcast(this.val$intent);
                }
            });
        }

        private void executeBadgeByContentProvider(int arg3) {
            if(arg3 < 0) {
                return;
            }

            if(SonyHomeBadger.mQueryHandler == null) {
                SonyHomeBadger.mQueryHandler = new AsyncQueryHandler(ApplicationLoader.applicationContext.getApplicationContext().getContentResolver()) {
                    public void handleMessage(Message arg1) {
                        try {
                            super.handleMessage(arg1);
                            return;
                        }
                        catch(Throwable ) {
                            return;
                        }
                    }
                };
            }

            this.insertBadgeAsync(arg3, NotificationBadge.componentName.getPackageName(), NotificationBadge.componentName.getClassName());
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.sonyericsson.home", "com.sonymobile.home"});
        }

        private void insertBadgeAsync(int arg3, String arg4, String arg5) {
            ContentValues v0 = new ContentValues();
            v0.put("badge_count", Integer.valueOf(arg3));
            v0.put("package_name", arg4);
            v0.put("activity_name", arg5);
            SonyHomeBadger.mQueryHandler.startInsert(0, null, this.BADGE_CONTENT_URI, v0);
        }

        private static boolean sonyBadgeContentProviderExists() {
            boolean v2 = false;
            if(ApplicationLoader.applicationContext.getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null) {
                v2 = true;
            }

            return v2;
        }
    }

    public class VivoHomeBadger implements Badger {
        public VivoHomeBadger() {
            super();
        }

        public void executeBadge(int arg4) {
            Intent v0 = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            v0.putExtra("packageName", ApplicationLoader.applicationContext.getPackageName());
            v0.putExtra("className", NotificationBadge.componentName.getClassName());
            v0.putExtra("notificationNum", arg4);
            ApplicationLoader.applicationContext.sendBroadcast(v0);
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.vivo.launcher"});
        }
    }

    public class XiaomiHomeBadger implements Badger {
        public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
        public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";
        public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";

        public XiaomiHomeBadger() {
            super();
        }

        public void executeBadge(int arg5) {
            Integer v5_1;
            Integer v2_1;
            try {
                Object v0 = Class.forName("android.app.MiuiNotification").newInstance();
                Field v1 = v0.getClass().getDeclaredField("messageCount");
                v1.setAccessible(true);
                if(arg5 == 0) {
                    String v2 = "";
                }
                else {
                    v2_1 = Integer.valueOf(arg5);
                }

                v1.set(v0, String.valueOf(v2_1));
            }
            catch(Throwable ) {
                Intent v0_1 = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
                v0_1.putExtra("android.intent.extra.update_application_component_name", NotificationBadge.componentName.getPackageName() + "/" + NotificationBadge.componentName.getClassName());
                String v1_1 = "android.intent.extra.update_application_message_text";
                if(arg5 == 0) {
                    String v5 = "";
                }
                else {
                    v5_1 = Integer.valueOf(arg5);
                }

                v0_1.putExtra(v1_1, String.valueOf(v5_1));
                if(!NotificationBadge.canResolveBroadcast(v0_1)) {
                    return;
                }

                AndroidUtilities.runOnUIThread(new Runnable(v0_1) {
                    public void run() {
                        ApplicationLoader.applicationContext.sendBroadcast(this.val$localIntent);
                    }
                });
            }
        }

        public List getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2"});
        }
    }

    public class ZukHomeBadger implements Badger {
        private final Uri CONTENT_URI;

        public ZukHomeBadger() {
            super();
            this.CONTENT_URI = Uri.parse("content://com.android.badge/badge");
        }

        static Uri access$200(ZukHomeBadger arg0) {
            return arg0.CONTENT_URI;
        }

        @TargetApi(value=11) public void executeBadge(int arg3) {
            Bundle v0 = new Bundle();
            v0.putInt("app_badge_count", arg3);
            AndroidUtilities.runOnUIThread(new Runnable(v0) {
                public void run() {
                    try {
                        ApplicationLoader.applicationContext.getContentResolver().call(ZukHomeBadger.this.CONTENT_URI, "setAppBadgeCount", null, this.val$extra);
                    }
                    catch(Exception v0) {
                        FileLog.e(((Throwable)v0));
                    }
                }
            });
        }

        public List getSupportLaunchers() {
            return Collections.singletonList("com.zui.launcher");
        }
    }

    private static final List BADGERS;
    private static Badger badger;
    private static ComponentName componentName;
    private static boolean initied;

    static {
        NotificationBadge.BADGERS = new LinkedList();
        NotificationBadge.BADGERS.add(AdwHomeBadger.class);
        NotificationBadge.BADGERS.add(ApexHomeBadger.class);
        NotificationBadge.BADGERS.add(NewHtcHomeBadger.class);
        NotificationBadge.BADGERS.add(NovaHomeBadger.class);
        NotificationBadge.BADGERS.add(SonyHomeBadger.class);
        NotificationBadge.BADGERS.add(XiaomiHomeBadger.class);
        NotificationBadge.BADGERS.add(AsusHomeBadger.class);
        NotificationBadge.BADGERS.add(HuaweiHomeBadger.class);
        NotificationBadge.BADGERS.add(OPPOHomeBader.class);
        NotificationBadge.BADGERS.add(SamsungHomeBadger.class);
        NotificationBadge.BADGERS.add(ZukHomeBadger.class);
        NotificationBadge.BADGERS.add(VivoHomeBadger.class);
    }

    public NotificationBadge() {
        super();
    }

    static ComponentName access$000() {
        return NotificationBadge.componentName;
    }

    static boolean access$100(Intent arg0) {
        return NotificationBadge.canResolveBroadcast(arg0);
    }

    public static boolean applyCount(int arg3) {
        try {
            if(NotificationBadge.badger == null && !NotificationBadge.initied) {
                NotificationBadge.initBadger();
                NotificationBadge.initied = true;
            }

            if(NotificationBadge.badger == null) {
                return 0;
            }

            NotificationBadge.badger.executeBadge(arg3);
            return 1;
        }
        catch(Throwable ) {
            return 0;
        }
    }

    private static boolean canResolveBroadcast(Intent arg2) {
        boolean v1 = false;
        List v2 = ApplicationLoader.applicationContext.getPackageManager().queryBroadcastReceivers(arg2, 0);
        if(v2 != null && v2.size() > 0) {
            v1 = true;
        }

        return v1;
    }

    public static void close(Cursor arg1) {
        if(arg1 != null && !arg1.isClosed()) {
            arg1.close();
        }
    }

    public static void closeQuietly(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(Throwable ) {
                return;
            }
        }
    }

    private static boolean initBadger() {
        ZukHomeBadger v0_3;
        Object v4_1;
        Object v8;
        Context v0 = ApplicationLoader.applicationContext;
        Intent v1 = v0.getPackageManager().getLaunchIntentForPackage(v0.getPackageName());
        int v2 = 0;
        if(v1 == null) {
            return 0;
        }

        NotificationBadge.componentName = v1.getComponent();
        v1 = new Intent("android.intent.action.MAIN");
        v1.addCategory("android.intent.category.HOME");
        int v4 = 65536;
        ResolveInfo v3 = v0.getPackageManager().resolveActivity(v1, v4);
        Object v5 = null;
        if(v3 != null) {
            String v3_1 = v3.activityInfo.packageName;
            Iterator v7 = NotificationBadge.BADGERS.iterator();
            do {
            label_24:
                if(!v7.hasNext()) {
                    goto label_35;
                }

                v8 = v7.next();
                try {
                    v8 = ((Class)v8).newInstance();
                }
                catch(Exception ) {
                    v8 = v5;
                }

                if(v8 == null) {
                    goto label_24;
                }
            }
            while(!((Badger)v8).getSupportLaunchers().contains(v3_1));

            NotificationBadge.badger = ((Badger)v8);
        label_35:
            if(NotificationBadge.badger == null) {
                goto label_38;
            }

            return 1;
        }

    label_38:
        List v0_1 = v0.getPackageManager().queryIntentActivities(v1, v4);
        if(v0_1 != null) {
            while(v2 < v0_1.size()) {
                String v1_1 = v0_1.get(v2).activityInfo.packageName;
                Iterator v3_2 = NotificationBadge.BADGERS.iterator();
                do {
                label_48:
                    if(!v3_2.hasNext()) {
                        goto label_59;
                    }

                    v4_1 = v3_2.next();
                    try {
                        v4_1 = ((Class)v4_1).newInstance();
                    }
                    catch(Exception ) {
                        v4_1 = v5;
                    }

                    if(v4_1 == null) {
                        goto label_48;
                    }
                }
                while(!((Badger)v4_1).getSupportLaunchers().contains(v1_1));

                NotificationBadge.badger = ((Badger)v4_1);
            label_59:
                if(NotificationBadge.badger != null) {
                    break;
                }

                ++v2;
            }
        }

        if(NotificationBadge.badger == null) {
            if(Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                XiaomiHomeBadger v0_2 = new XiaomiHomeBadger();
            }
            else if(Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
                v0_3 = new ZukHomeBadger();
            }
            else if(Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
                OPPOHomeBader v0_4 = new OPPOHomeBader();
            }
            else if(Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
                VivoHomeBadger v0_5 = new VivoHomeBadger();
            }
            else {
                DefaultBadger v0_6 = new DefaultBadger();
            }

            NotificationBadge.badger = ((Badger)v0_3);
        }

        return 1;
    }
}

