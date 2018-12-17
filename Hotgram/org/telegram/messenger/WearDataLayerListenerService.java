package org.telegram.messenger;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;

public class WearDataLayerListenerService extends WearableListenerService {
    private int currentAccount;
    private static boolean watchConnected;

    public WearDataLayerListenerService() {
        super();
        this.currentAccount = UserConfig.selectedAccount;
    }

    static int access$000(WearDataLayerListenerService arg0) {
        return arg0.currentAccount;
    }

    static boolean access$102(boolean arg0) {
        WearDataLayerListenerService.watchConnected = arg0;
        return arg0;
    }

    public static boolean isWatchConnected() {
        return WearDataLayerListenerService.watchConnected;
    }

    public void onCapabilityChanged(CapabilityInfo arg3) {
        if("remote_notifications".equals(arg3.getName())) {
            WearDataLayerListenerService.watchConnected = false;
            Iterator v3 = arg3.getNodes().iterator();
            while(v3.hasNext()) {
                if(!v3.next().isNearby()) {
                    continue;
                }

                WearDataLayerListenerService.watchConnected = true;
            }
        }
    }

    public void onChannelOpened(Channel arg12) {
        byte[] v3_8;
        FileInputStream v4_1;
        FileLocation v6_3;
        DataInputStream v1_4;
        DataOutputStream v2_4;
        long v6_2;
        org.telegram.messenger.WearDataLayerListenerService$4 v3_3;
        CyclicBarrier v2_3;
        String[] v1_3;
        long v7;
        org.telegram.messenger.WearDataLayerListenerService$1 v3_1;
        CyclicBarrier v6;
        File v4;
        DataOutputStream v1_2;
        int v3;
        GoogleApiClient v0 = new Builder(((Context)this)).addApi(Wearable.API).build();
        if(!v0.blockingConnect().isSuccess()) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("failed to connect google api client");
            }

            return;
        }

        String v1 = arg12.getPath();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("wear channel path: " + v1);
        }

        try {
            v3 = 2;
            if(!"/getCurrentUser".equals(v1)) {
                goto label_93;
            }

            v1_2 = new DataOutputStream(new BufferedOutputStream(arg12.getOutputStream(v0).await().getOutputStream()));
            if(UserConfig.getInstance(this.currentAccount).isClientActivated()) {
                User v2_1 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
                v1_2.writeInt(v2_1.id);
                v1_2.writeUTF(v2_1.first_name);
                v1_2.writeUTF(v2_1.last_name);
                v1_2.writeUTF(v2_1.phone);
                if(v2_1.photo != null) {
                    v4 = FileLoader.getPathToAttach(v2_1.photo.photo_small, true);
                    v6 = new CyclicBarrier(v3);
                    if(!v4.exists()) {
                        v3_1 = new NotificationCenterDelegate(v4, v6) {
                            public void didReceivedNotification(int arg2, int arg3, Object[] arg4) {
                                if(arg2 == NotificationCenter.FileDidLoaded) {
                                    if(BuildVars.LOGS_ENABLED) {
                                        FileLog.d("file loaded: " + arg4[0] + " " + arg4[0].getClass().getName());
                                    }

                                    if(!arg4[0].equals(this.val$photo.getName())) {
                                        return;
                                    }

                                    if(BuildVars.LOGS_ENABLED) {
                                        FileLog.e("LOADED USER PHOTO");
                                    }

                                    try {
                                        this.val$barrier.await(10, TimeUnit.MILLISECONDS);
                                        return;
                                    }
                                    catch(Exception ) {
                                        return;
                                    }
                                }
                            }
                        };
                        AndroidUtilities.runOnUIThread(new Runnable(((NotificationCenterDelegate)v3_1), v2_1) {
                            public void run() {
                                NotificationCenter.getInstance(WearDataLayerListenerService.this.currentAccount).addObserver(this.val$listener, NotificationCenter.FileDidLoaded);
                                FileLoader.getInstance(WearDataLayerListenerService.this.currentAccount).loadFile(this.val$user.photo.photo_small, null, 0, 1);
                            }
                        });
                        v7 = 10;
                        goto label_66;
                    }

                    goto label_71;
                }
                else {
                    goto label_89;
                }
            }
            else {
                goto label_89;
            }

            goto label_90;
        }
        catch(Exception v1_1) {
            goto label_217;
        }

        try {
        label_66:
            v6.await(v7, TimeUnit.SECONDS);
            goto label_68;
        }
        catch(Exception ) {
            try {
            label_68:
                AndroidUtilities.runOnUIThread(new Runnable(((NotificationCenterDelegate)v3_1)) {
                    public void run() {
                        NotificationCenter.getInstance(WearDataLayerListenerService.this.currentAccount).removeObserver(this.val$listener, NotificationCenter.FileDidLoaded);
                    }
                });
            label_71:
                if(v4.exists()) {
                    if(v4.length() > 52428800) {
                        goto label_89;
                    }

                    byte[] v2_2 = new byte[((int)v4.length())];
                    FileInputStream v3_2 = new FileInputStream(v4);
                    new DataInputStream(((InputStream)v3_2)).readFully(v2_2);
                    v3_2.close();
                    v1_2.writeInt(v2_2.length);
                    v1_2.write(v2_2);
                }
                else {
                label_89:
                    v1_2.writeInt(0);
                }

            label_90:
                v1_2.flush();
                v1_2.close();
                goto label_220;
            label_93:
                String v6_1 = null;
                if(!"/waitForAuthCode".equals(v1)) {
                    goto label_133;
                }

                ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
                v1_3 = new String[]{v6_1};
                v2_3 = new CyclicBarrier(v3);
                v3_3 = new NotificationCenterDelegate(v1_3, v2_3) {
                    public void didReceivedNotification(int arg5, int arg6, Object[] arg7) {
                        if(arg5 == NotificationCenter.didReceivedNewMessages && arg7[0].longValue() == 777000) {
                            Object v6 = arg7[1];
                            if(((ArrayList)v6).size() > 0) {
                                v6 = ((ArrayList)v6).get(0);
                                if(!TextUtils.isEmpty(((MessageObject)v6).messageText)) {
                                    Matcher v6_1 = Pattern.compile("[0-9]+").matcher(((MessageObject)v6).messageText);
                                    if(v6_1.find()) {
                                        this.val$code[0] = v6_1.group();
                                        try {
                                            this.val$barrier.await(10, TimeUnit.MILLISECONDS);
                                            return;
                                        }
                                        catch(Exception ) {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                };
                AndroidUtilities.runOnUIThread(new Runnable(((NotificationCenterDelegate)v3_3)) {
                    public void run() {
                        NotificationCenter.getInstance(WearDataLayerListenerService.this.currentAccount).addObserver(this.val$listener, NotificationCenter.didReceivedNewMessages);
                    }
                });
                v6_2 = 15;
            }
            catch(Exception v1_1) {
                goto label_217;
            }
        }

        try {
            v2_3.await(v6_2, TimeUnit.SECONDS);
            goto label_112;
        }
        catch(Exception ) {
            try {
            label_112:
                AndroidUtilities.runOnUIThread(new Runnable(((NotificationCenterDelegate)v3_3)) {
                    public void run() {
                        NotificationCenter.getInstance(WearDataLayerListenerService.this.currentAccount).removeObserver(this.val$listener, NotificationCenter.didReceivedNewMessages);
                    }
                });
                v2_4 = new DataOutputStream(arg12.getOutputStream(v0).await().getOutputStream());
                v1 = v1_3[0] != null ? v1_3[0] : "";
                v2_4.writeUTF(v1);
                v2_4.flush();
                v2_4.close();
                ConnectionsManager.getInstance(this.currentAccount).setAppPaused(true, false);
                goto label_220;
            label_133:
                if(!"/getChatPhoto".equals(v1)) {
                    goto label_220;
                }

                v1_4 = new DataInputStream(arg12.getInputStream(v0).await().getInputStream());
                v2_4 = new DataOutputStream(arg12.getOutputStream(v0).await().getOutputStream());
            }
            catch(Exception v1_1) {
                goto label_217;
            }
        }

        try {
            JSONObject v7_1 = new JSONObject(v1_4.readUTF());
            v3 = v7_1.getInt("chat_id");
            int v7_2 = v7_1.getInt("account_id");
            int v8 = 0;
            while(true) {
                int v10 = -1;
                if(v8 >= UserConfig.getActivatedAccountsCount()) {
                    break;
                }
                else if(UserConfig.getInstance(v8).getClientUserId() == v7_2) {
                }
                else {
                    ++v8;
                    continue;
                }

                goto label_164;
            }

            v8 = -1;
        label_164:
            if(v8 != v10) {
                if(v3 > 0) {
                    User v3_5 = MessagesController.getInstance(v8).getUser(Integer.valueOf(v3));
                    if(v3_5 != null && v3_5.photo != null) {
                        v6_3 = v3_5.photo.photo_small;
                    }
                }
                else {
                    Chat v3_6 = MessagesController.getInstance(v8).getChat(Integer.valueOf(-v3));
                    if(v3_6 != null && v3_6.photo != null) {
                        v6_3 = v3_6.photo.photo_small;
                    }
                }

                if(v6_3 == null) {
                    goto label_204;
                }

                File v3_7 = FileLoader.getPathToAttach(((TLObject)v6_3), true);
                if(!v3_7.exists()) {
                    goto label_204;
                }

                if(v3_7.length() >= 102400) {
                    goto label_204;
                }

                v2_4.writeInt(((int)v3_7.length()));
                v4_1 = new FileInputStream(v3_7);
                v3_8 = new byte[10240];
                goto label_198;
            }
            else {
            label_204:
                v2_4.writeInt(0);
                goto label_205;
                while(true) {
                label_198:
                    int v6_4 = v4_1.read(v3_8);
                    if(v6_4 <= 0) {
                        break;
                    }

                    v2_4.write(v3_8, 0, v6_4);
                }

                v4_1.close();
            }

        label_205:
            v2_4.flush();
        }
        catch(Throwable v3_4) {
            goto label_210;
        }
        catch(Exception ) {
            goto label_213;
        }

        try {
            v1_4.close();
            goto label_207;
        label_213:
            v1_4.close();
        label_207:
            v2_4.close();
            goto label_220;
        label_210:
            v1_4.close();
            v2_4.close();
            throw v3_4;
        }
        catch(Exception v1_1) {
        label_217:
            if(!BuildVars.LOGS_ENABLED) {
                goto label_220;
            }

            FileLog.e("error processing wear request", ((Throwable)v1_1));
        }

    label_220:
        arg12.close(v0).await();
        v0.disconnect();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("WearableDataLayer channel thread exiting");
        }
    }

    public void onCreate() {
        super.onCreate();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("WearableDataLayer service created");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("WearableDataLayer service destroyed");
        }
    }

    public void onMessageReceived(MessageEvent arg3) {
        if("/reply".equals(arg3.getPath())) {
            AndroidUtilities.runOnUIThread(new Runnable(arg3) {
                public void run() {
                    try {
                        ApplicationLoader.postInitApplication();
                        JSONObject v1 = new JSONObject(new String(this.val$messageEvent.getData(), "UTF-8"));
                        String v0_1 = v1.getString("text");
                        if(v0_1 != null) {
                            if(((CharSequence)v0_1).length() == 0) {
                            }
                            else {
                                long v13 = v1.getLong("chat_id");
                                int v2 = v1.getInt("max_id");
                                int v1_1 = v1.getInt("account_id");
                                int v3 = 0;
                                while(true) {
                                    int v5 = -1;
                                    if(v3 >= UserConfig.getActivatedAccountsCount()) {
                                        break;
                                    }
                                    else if(UserConfig.getInstance(v3).getClientUserId() == v1_1) {
                                        v1_1 = v3;
                                    }
                                    else {
                                        ++v3;
                                        continue;
                                    }

                                    goto label_32;
                                }

                                v1_1 = -1;
                            label_32:
                                if(v13 == 0) {
                                    return;
                                }

                                if(v2 == 0) {
                                    return;
                                }

                                if(v1_1 == v5) {
                                    return;
                                }

                                SendMessagesHelper.getInstance(v1_1).sendMessage(((CharSequence)v0_1).toString(), v13, null, null, true, null, null, null);
                                MessagesController.getInstance(v1_1).markDialogAsRead(v13, v2, v2, 0, false, 0, true);
                                return;
                            }
                        }

                        return;
                    }
                    catch(Exception v0) {
                        if(!BuildVars.LOGS_ENABLED) {
                            return;
                        }

                        FileLog.e(((Throwable)v0));
                    }
                }
            });
        }
    }

    public static void sendMessageToWatch(String arg2, byte[] arg3, String arg4) {
        Wearable.getCapabilityClient(ApplicationLoader.applicationContext).getCapability(arg4, 1).addOnCompleteListener(new OnCompleteListener(arg2, arg3) {
            public void onComplete(Task arg5) {
                Object v5 = arg5.getResult();
                if(v5 != null) {
                    MessageClient v0 = Wearable.getMessageClient(ApplicationLoader.applicationContext);
                    Iterator v5_1 = ((CapabilityInfo)v5).getNodes().iterator();
                    while(v5_1.hasNext()) {
                        v0.sendMessage(v5_1.next().getId(), this.val$path, this.val$data);
                    }
                }
            }
        });
    }

    public static void updateWatchConnectionState() {
        try {
            Wearable.getCapabilityClient(ApplicationLoader.applicationContext).getCapability("remote_notifications", 1).addOnCompleteListener(new OnCompleteListener() {
                public void onComplete(Task arg2) {
                    WearDataLayerListenerService.watchConnected = false;
                    try {
                        Object v2 = arg2.getResult();
                        if(v2 == null) {
                            return;
                        }

                        Iterator v2_1 = ((CapabilityInfo)v2).getNodes().iterator();
                        while(true) {
                            if(!v2_1.hasNext()) {
                                return;
                            }

                            if(!v2_1.next().isNearby()) {
                                continue;
                            }

                            WearDataLayerListenerService.watchConnected = true;
                        }
                    }
                    catch(Exception ) {
                        return;
                    }
                }
            });
            return;
        }
        catch(Throwable ) {
            return;
        }
    }
}

