package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageDecoder$ImageInfo;
import android.graphics.ImageDecoder$Source;
import android.graphics.ImageDecoder;
import android.graphics.Paint;
import android.graphics.Path$Direction;
import android.graphics.Path$FillType;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioAttributes$Builder;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.PowerManager$WakeLock;
import android.os.SystemClock;
import android.provider.Settings$System;
import android.support.v4.app.ab;
import android.support.v4.app.ac;
import android.support.v4.app.w$c;
import android.support.v4.app.w$d;
import android.support.v4.app.w$e$a$a;
import android.support.v4.app.w$e;
import android.support.v4.app.w$f;
import android.support.v4.app.w$g;
import android.support.v4.app.w$h;
import android.support.v4.app.w$i;
import android.support.v4.app.w$j;
import android.support.v4.app.z;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$PhoneCallDiscardReason;
import org.telegram.tgnet.TLRPC$TL_account_updateNotifySettings;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_inputNotifyPeer;
import org.telegram.tgnet.TLRPC$TL_inputPeerNotifySettings;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonCallback;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonRow;
import org.telegram.tgnet.TLRPC$TL_messageActionChannelCreate;
import org.telegram.tgnet.TLRPC$TL_messageActionChannelMigrateFrom;
import org.telegram.tgnet.TLRPC$TL_messageActionChatAddUser;
import org.telegram.tgnet.TLRPC$TL_messageActionChatCreate;
import org.telegram.tgnet.TLRPC$TL_messageActionChatDeletePhoto;
import org.telegram.tgnet.TLRPC$TL_messageActionChatDeleteUser;
import org.telegram.tgnet.TLRPC$TL_messageActionChatEditPhoto;
import org.telegram.tgnet.TLRPC$TL_messageActionChatEditTitle;
import org.telegram.tgnet.TLRPC$TL_messageActionChatJoinedByLink;
import org.telegram.tgnet.TLRPC$TL_messageActionChatMigrateTo;
import org.telegram.tgnet.TLRPC$TL_messageActionEmpty;
import org.telegram.tgnet.TLRPC$TL_messageActionGameScore;
import org.telegram.tgnet.TLRPC$TL_messageActionLoginUnknownLocation;
import org.telegram.tgnet.TLRPC$TL_messageActionPaymentSent;
import org.telegram.tgnet.TLRPC$TL_messageActionPhoneCall;
import org.telegram.tgnet.TLRPC$TL_messageActionPinMessage;
import org.telegram.tgnet.TLRPC$TL_messageActionScreenshotTaken;
import org.telegram.tgnet.TLRPC$TL_messageActionUserJoined;
import org.telegram.tgnet.TLRPC$TL_messageActionUserUpdatedPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaContact;
import org.telegram.tgnet.TLRPC$TL_messageMediaDocument;
import org.telegram.tgnet.TLRPC$TL_messageMediaGame;
import org.telegram.tgnet.TLRPC$TL_messageMediaGeo;
import org.telegram.tgnet.TLRPC$TL_messageMediaGeoLive;
import org.telegram.tgnet.TLRPC$TL_messageMediaPhoto;
import org.telegram.tgnet.TLRPC$TL_messageMediaVenue;
import org.telegram.tgnet.TLRPC$TL_messageService;
import org.telegram.tgnet.TLRPC$TL_phoneCallDiscardReasonMissed;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PopupNotificationActivity;

public class NotificationsController {
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
    private static volatile NotificationsController[] Instance;
    public static String OTHER_NOTIFICATIONS_CHANNEL;
    private AlarmManager alarmManager;
    protected static AudioManager audioManager;
    private int currentAccount;
    private ArrayList delayedPushMessages;
    private LongSparseArray fcmRandomMessagesDict;
    public static long globalSecretChatId;
    private boolean inChatSoundEnabled;
    private int lastBadgeCount;
    private int lastButtonId;
    public static long lastNoDataNotificationTime;
    private boolean lastNotificationIsNoData;
    private int lastOnlineFromOtherDevice;
    private long lastSoundOutPlay;
    private long lastSoundPlay;
    private LongSparseArray lastWearNotifiedMessageId;
    private String launcherClassName;
    private Runnable notificationDelayRunnable;
    private PowerManager$WakeLock notificationDelayWakelock;
    private String notificationGroup;
    private int notificationId;
    private static z notificationManager;
    private static DispatchQueue notificationsQueue;
    private boolean notifyCheck;
    private long opened_dialog_id;
    private int personal_count;
    public ArrayList popupMessages;
    public ArrayList popupReplyMessages;
    private LongSparseArray pushDialogs;
    private LongSparseArray pushDialogsOverrideMention;
    private ArrayList pushMessages;
    private LongSparseArray pushMessagesDict;
    public boolean showBadgeNumber;
    private LongSparseArray smartNotificationsDialogs;
    private int soundIn;
    private boolean soundInLoaded;
    private int soundOut;
    private boolean soundOutLoaded;
    private SoundPool soundPool;
    private int soundRecord;
    private boolean soundRecordLoaded;
    private static NotificationManager systemNotificationManager;
    private int total_unread_count;
    private LongSparseArray wearNotificationsIds;

    static {
        NotificationsController.notificationsQueue = new DispatchQueue("notificationsQueue");
        NotificationsController.notificationManager = null;
        NotificationsController.systemNotificationManager = null;
        NotificationsController.globalSecretChatId = -4294967296L;
        if(Build$VERSION.SDK_INT >= 26 && ApplicationLoader.applicationContext != null) {
            NotificationsController.notificationManager = z.a(ApplicationLoader.applicationContext);
            NotificationsController.systemNotificationManager = ApplicationLoader.applicationContext.getSystemService("notification");
            NotificationsController.checkOtherNotificationsChannel();
        }

        NotificationsController.audioManager = ApplicationLoader.applicationContext.getSystemService("audio");
        NotificationsController.Instance = new NotificationsController[3];
    }

    public NotificationsController(int arg4) {
        String v2;
        super();
        this.pushMessages = new ArrayList();
        this.delayedPushMessages = new ArrayList();
        this.pushMessagesDict = new LongSparseArray();
        this.fcmRandomMessagesDict = new LongSparseArray();
        this.smartNotificationsDialogs = new LongSparseArray();
        this.pushDialogs = new LongSparseArray();
        this.wearNotificationsIds = new LongSparseArray();
        this.lastWearNotifiedMessageId = new LongSparseArray();
        this.pushDialogsOverrideMention = new LongSparseArray();
        this.popupMessages = new ArrayList();
        this.popupReplyMessages = new ArrayList();
        this.opened_dialog_id = 0;
        this.lastButtonId = 5000;
        this.total_unread_count = 0;
        this.personal_count = 0;
        this.notifyCheck = false;
        this.lastOnlineFromOtherDevice = 0;
        this.lastBadgeCount = -1;
        this.currentAccount = arg4;
        this.notificationId = this.currentAccount + 1;
        StringBuilder v4 = new StringBuilder();
        v4.append("messages");
        if(this.currentAccount == 0) {
            v2 = "";
        }
        else {
            Integer v2_1 = Integer.valueOf(this.currentAccount);
        }

        v4.append(v2);
        this.notificationGroup = v4.toString();
        SharedPreferences v4_1 = MessagesController.getNotificationsSettings(this.currentAccount);
        this.inChatSoundEnabled = v4_1.getBoolean("EnableInChatSound", true);
        this.showBadgeNumber = v4_1.getBoolean("badgeNumber", true);
        NotificationsController.notificationManager = z.a(ApplicationLoader.applicationContext);
        NotificationsController.systemNotificationManager = ApplicationLoader.applicationContext.getSystemService("notification");
        try {
            NotificationsController.audioManager = ApplicationLoader.applicationContext.getSystemService("audio");
        }
        catch(Exception v4_2) {
            FileLog.e(((Throwable)v4_2));
        }

        try {
            this.alarmManager = ApplicationLoader.applicationContext.getSystemService("alarm");
        }
        catch(Exception v4_2) {
            FileLog.e(((Throwable)v4_2));
        }

        try {
            this.notificationDelayWakelock = ApplicationLoader.applicationContext.getSystemService("power").newWakeLock(1, "lock");
            this.notificationDelayWakelock.setReferenceCounted(false);
        }
        catch(Exception v4_2) {
            FileLog.e(((Throwable)v4_2));
        }

        this.notificationDelayRunnable = new -$$Lambda$NotificationsController$u_XWL43v4eUkt0lAcsDPJJv0mZM(this);
    }

    static z access$000() {
        return NotificationsController.notificationManager;
    }

    public static void checkOtherNotificationsChannel() {
        SharedPreferences v0;
        if(Build$VERSION.SDK_INT < 26) {
            return;
        }

        String v2 = null;
        if(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL == null) {
            v0 = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
            NotificationsController.OTHER_NOTIFICATIONS_CHANNEL = v0.getString("OtherKey", "Other3");
        }
        else {
            v0 = ((SharedPreferences)v2);
        }

        NotificationChannel v3 = NotificationsController.systemNotificationManager.getNotificationChannel(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
        if(v3 != null && v3.getImportance() == 0) {
            NotificationsController.systemNotificationManager.deleteNotificationChannel(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
            NotificationsController.OTHER_NOTIFICATIONS_CHANNEL = v2;
            v3 = ((NotificationChannel)v2);
        }

        if(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL == null) {
            if(v0 == null) {
                v0 = ApplicationLoader.applicationContext.getSharedPreferences("Notifications", 0);
            }

            NotificationsController.OTHER_NOTIFICATIONS_CHANNEL = "Other" + Utilities.random.nextLong();
            v0.edit().putString("OtherKey", NotificationsController.OTHER_NOTIFICATIONS_CHANNEL).commit();
        }

        if(v3 == null) {
            NotificationChannel v0_1 = new NotificationChannel(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL, "Other", 3);
            v0_1.enableLights(false);
            v0_1.enableVibration(false);
            v0_1.setSound(((Uri)v2), ((AudioAttributes)v2));
            NotificationsController.systemNotificationManager.createNotificationChannel(v0_1);
        }
    }

    public void cleanup() {
        this.popupMessages.clear();
        this.popupReplyMessages.clear();
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$A9SCTrujp78_YxIRivW7UAoIEBo(this));
    }

    private void dismissNotification() {
        int v0 = 0;
        try {
            this.lastNotificationIsNoData = false;
            NotificationsController.notificationManager.a(this.notificationId);
            this.pushMessages.clear();
            this.pushMessagesDict.clear();
            this.lastWearNotifiedMessageId.clear();
            while(v0 < this.wearNotificationsIds.size()) {
                NotificationsController.notificationManager.a(this.wearNotificationsIds.valueAt(v0).intValue());
                ++v0;
            }

            this.wearNotificationsIds.clear();
            AndroidUtilities.runOnUIThread(-$$Lambda$NotificationsController$5Rsj4cKl6jw3lYOyTI75JO6M6O0.INSTANCE);
            if(!WearDataLayerListenerService.isWatchConnected()) {
                return;
            }

            try {
                JSONObject v0_2 = new JSONObject();
                v0_2.put("id", UserConfig.getInstance(this.currentAccount).getClientUserId());
                v0_2.put("cancel_all", true);
                WearDataLayerListenerService.sendMessageToWatch("/notify", v0_2.toString().getBytes("UTF-8"), "remote_notifications");
            }
            catch(JSONException ) {
            }
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    protected void forceShowPopupForReply() {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$eQV-fs8YB0lhGMYS2TKm4CX_EZk(this));
    }

    public static NotificationsController getInstance(int arg3) {
        NotificationsController v0 = NotificationsController.Instance[arg3];
        if(v0 == null) {
            Class v1 = NotificationsController.class;
            __monitor_enter(v1);
            try {
                v0 = NotificationsController.Instance[arg3];
                if(v0 == null) {
                    NotificationsController[] v0_1 = NotificationsController.Instance;
                    NotificationsController v2 = new NotificationsController(arg3);
                    v0_1[arg3] = v2;
                    v0 = v2;
                }

                __monitor_exit(v1);
                return v0;
            label_16:
                __monitor_exit(v1);
            }
            catch(Throwable v3) {
                goto label_16;
            }

            throw v3;
        }

        return v0;
    }

    private int getNotifyOverride(SharedPreferences arg4, long arg5) {
        StringBuilder v0 = new StringBuilder();
        v0.append("notify2_");
        v0.append(arg5);
        int v0_1 = arg4.getInt(v0.toString(), -1);
        if(v0_1 == 3) {
            StringBuilder v1 = new StringBuilder();
            v1.append("notifyuntil_");
            v1.append(arg5);
            if(arg4.getInt(v1.toString(), 0) >= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) {
                v0_1 = 2;
            }
        }

        return v0_1;
    }

    private String getShortStringForMessage(MessageObject arg14, String[] arg15) {
        CharSequence v14_2;
        String v14_1;
        User v14;
        Object[] v1_1;
        int v15_2;
        Chat v11;
        String v10_1;
        Object[] v2_1;
        int v2 = 2131626442;
        if(!AndroidUtilities.needShowPasscode(false)) {
            if(SharedConfig.isWaitingForPasscodeEnter) {
            }
            else {
                long v3 = arg14.messageOwner.dialog_id;
                int v1 = arg14.messageOwner.to_id.chat_id != 0 ? arg14.messageOwner.to_id.chat_id : arg14.messageOwner.to_id.channel_id;
                int v5 = arg14.messageOwner.to_id.user_id;
                int v7 = 27;
                int v8 = 2;
                if(arg14.isFcmMessage()) {
                    if(v1 != 0 || v5 == 0) {
                        if(v1 != 0) {
                            if(!MessagesController.getNotificationsSettings(this.currentAccount).getBoolean("EnablePreviewGroup", true)) {
                                if(!arg14.isMegagroup() && arg14.messageOwner.to_id.channel_id != 0) {
                                    return LocaleController.formatString("ChannelMessageNoText", 2131624339, new Object[]{arg14.localName});
                                }

                                v2_1 = new Object[v8];
                                v2_1[0] = arg14.localUserName;
                                v2_1[1] = arg14.localName;
                                return LocaleController.formatString("NotificationMessageGroupNoText", 2131625364, v2_1);
                            }
                            else {
                                if(arg14.messageOwner.to_id.channel_id != 0) {
                                    if(arg14.isMegagroup()) {
                                    }
                                    else {
                                        if(Build$VERSION.SDK_INT > v7) {
                                            arg15[0] = arg14.localName;
                                        }
                                        else {
                                        }

                                        goto label_89;
                                    }
                                }

                                arg15[0] = arg14.localUserName;
                            }
                        }
                    }
                    else if(!MessagesController.getNotificationsSettings(this.currentAccount).getBoolean("EnablePreviewAll", true)) {
                        return LocaleController.formatString("NotificationMessageNoText", 2131625375, new Object[]{arg14.localName});
                    }
                    else if(Build$VERSION.SDK_INT > v7) {
                        arg15[0] = arg14.localName;
                    }

                label_89:
                    return arg14.messageOwner.message;
                }

                if(v5 == 0) {
                    if(arg14.isFromUser()) {
                        goto label_100;
                    }
                    else if(arg14.getId() < 0) {
                        goto label_100;
                    }
                    else {
                        v5 = -v1;
                    }
                }
                else if(v5 == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                label_100:
                    v5 = arg14.messageOwner.from_id;
                }

                if(v3 == 0) {
                    if(v1 != 0) {
                        v3 = ((long)(-v1));
                    }
                    else if(v5 != 0) {
                        v3 = ((long)v5);
                    }
                }

                String v6 = null;
                if(v5 > 0) {
                    User v10 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(v5));
                    if(v10 != null) {
                        v10_1 = UserObject.getUserName(v10);
                        if(v1 != 0) {
                            arg15[0] = v10_1;
                        }
                        else if(Build$VERSION.SDK_INT > v7) {
                            arg15[0] = v10_1;
                        }
                        else {
                            arg15[0] = v6;
                        }
                    }
                    else {
                        goto label_142;
                    }
                }
                else {
                    Chat v10_2 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-v5));
                    if(v10_2 != null) {
                        v10_1 = v10_2.title;
                        arg15[0] = v10_1;
                    }
                    else {
                    label_142:
                        v10_1 = v6;
                    }
                }

                if(v10_1 == null) {
                    return v6;
                }

                if(v1 != 0) {
                    v11 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(v1));
                    if(v11 == null) {
                        return v6;
                    }
                    else if((ChatObject.isChannel(v11)) && !v11.megagroup && Build$VERSION.SDK_INT <= v7) {
                        arg15[0] = v6;
                    }
                }
                else {
                    v11 = ((Chat)v6);
                }

                if((((int)v3)) == 0) {
                    return LocaleController.getString("YouHaveNewMessage", v2);
                }

                SharedPreferences v2_2 = MessagesController.getNotificationsSettings(this.currentAccount);
                int v3_1 = 2131625176;
                if((v1 != 0 || v5 == 0 || !v2_2.getBoolean("EnablePreviewAll", true)) && (v1 == 0 || !v2_2.getBoolean("EnablePreviewGroup", true))) {
                    goto label_1283;
                }

                v2 = 19;
                if((arg14.messageOwner instanceof TL_messageService)) {
                    arg15[0] = v6;
                    if((arg14.messageOwner.action instanceof TL_messageActionUserJoined)) {
                        return LocaleController.formatString("NotificationContactJoined", 2131625334, new Object[]{v10_1});
                    }
                    else if((arg14.messageOwner.action instanceof TL_messageActionUserUpdatedPhoto)) {
                        return LocaleController.formatString("NotificationContactNewPhoto", 2131625335, new Object[]{v10_1});
                    }
                    else {
                        v1 = 3;
                        if((arg14.messageOwner.action instanceof TL_messageActionLoginUnknownLocation)) {
                            Object[] v3_2 = new Object[v8];
                            v3_2[0] = LocaleController.getInstance().formatterYear.format((((long)arg14.messageOwner.date)) * 1000);
                            v3_2[1] = LocaleController.getInstance().formatterDay.format((((long)arg14.messageOwner.date)) * 1000);
                            String v15 = LocaleController.formatString("formatDateAtTime", 2131626775, v3_2);
                            Object[] v4 = new Object[4];
                            v4[0] = UserConfig.getInstance(this.currentAccount).getCurrentUser().first_name;
                            v4[1] = v15;
                            v4[v8] = arg14.messageOwner.action.title;
                            v4[v1] = arg14.messageOwner.action.address;
                            return LocaleController.formatString("NotificationUnrecognizedDevice", 2131625385, v4);
                        }
                        else {
                            if(!(arg14.messageOwner.action instanceof TL_messageActionGameScore)) {
                                if((arg14.messageOwner.action instanceof TL_messageActionPaymentSent)) {
                                }
                                else if((arg14.messageOwner.action instanceof TL_messageActionPhoneCall)) {
                                    PhoneCallDiscardReason v15_1 = arg14.messageOwner.action.reason;
                                    if(arg14.isOut()) {
                                        return v6;
                                    }
                                    else if((v15_1 instanceof TL_phoneCallDiscardReasonMissed)) {
                                        return LocaleController.getString("CallMessageIncomingMissed", 2131624241);
                                    }
                                    else {
                                        return v6;
                                    }
                                }
                                else if((arg14.messageOwner.action instanceof TL_messageActionChatAddUser)) {
                                    v15_2 = arg14.messageOwner.action.user_id;
                                    if(v15_2 == 0 && arg14.messageOwner.action.users.size() == 1) {
                                        v15_2 = arg14.messageOwner.action.users.get(0).intValue();
                                    }

                                    v2 = 2131625338;
                                    if(v15_2 != 0) {
                                        if(arg14.messageOwner.to_id.channel_id != 0 && !v11.megagroup) {
                                            v1_1 = new Object[v8];
                                            v1_1[0] = v10_1;
                                            v1_1[1] = v11.title;
                                            return LocaleController.formatString("ChannelAddedByNotification", 2131624286, v1_1);
                                        }

                                        if(v15_2 == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                                            v1_1 = new Object[v8];
                                            v1_1[0] = v10_1;
                                            v1_1[1] = v11.title;
                                            return LocaleController.formatString("NotificationInvitedToGroup", 2131625346, v1_1);
                                        }

                                        v14 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(v15_2));
                                        if(v14 == null) {
                                            return v6;
                                        }

                                        if(v5 == v14.id) {
                                            if(v11.megagroup) {
                                                v1_1 = new Object[v8];
                                                v1_1[0] = v10_1;
                                                v1_1[1] = v11.title;
                                                return LocaleController.formatString("NotificationGroupAddSelfMega", 2131625340, v1_1);
                                            }

                                            v1_1 = new Object[v8];
                                            v1_1[0] = v10_1;
                                            v1_1[1] = v11.title;
                                            return LocaleController.formatString("NotificationGroupAddSelf", 2131625339, v1_1);
                                        }

                                        v1_1 = new Object[v1];
                                        v1_1[0] = v10_1;
                                        v1_1[1] = v11.title;
                                        v1_1[v8] = UserObject.getUserName(v14);
                                        return LocaleController.formatString("NotificationGroupAddMember", v2, v1_1);
                                    }

                                    StringBuilder v15_3 = new StringBuilder("");
                                    for(v3_1 = 0; v3_1 < arg14.messageOwner.action.users.size(); ++v3_1) {
                                        User v4_1 = MessagesController.getInstance(this.currentAccount).getUser(arg14.messageOwner.action.users.get(v3_1));
                                        if(v4_1 != null) {
                                            String v4_2 = UserObject.getUserName(v4_1);
                                            if(v15_3.length() != 0) {
                                                v15_3.append(", ");
                                            }

                                            v15_3.append(v4_2);
                                        }
                                    }

                                    v1_1 = new Object[v1];
                                    v1_1[0] = v10_1;
                                    v1_1[1] = v11.title;
                                    v1_1[v8] = v15_3.toString();
                                    return LocaleController.formatString("NotificationGroupAddMember", v2, v1_1);
                                }
                                else {
                                    if((arg14.messageOwner.action instanceof TL_messageActionChatJoinedByLink)) {
                                        v1_1 = new Object[v8];
                                        v1_1[0] = v10_1;
                                        v1_1[1] = v11.title;
                                        return LocaleController.formatString("NotificationInvitedToGroupByLink", 2131625347, v1_1);
                                    }

                                    if((arg14.messageOwner.action instanceof TL_messageActionChatEditTitle)) {
                                        v2_1 = new Object[v8];
                                        v2_1[0] = v10_1;
                                        v2_1[1] = arg14.messageOwner.action.title;
                                        return LocaleController.formatString("NotificationEditedGroupName", 2131625336, v2_1);
                                    }

                                    if(!(arg14.messageOwner.action instanceof TL_messageActionChatEditPhoto)) {
                                        if((arg14.messageOwner.action instanceof TL_messageActionChatDeletePhoto)) {
                                        }
                                        else if((arg14.messageOwner.action instanceof TL_messageActionChatDeleteUser)) {
                                            if(arg14.messageOwner.action.user_id == UserConfig.getInstance(this.currentAccount).getClientUserId()) {
                                                v1_1 = new Object[v8];
                                                v1_1[0] = v10_1;
                                                v1_1[1] = v11.title;
                                                return LocaleController.formatString("NotificationGroupKickYou", 2131625344, v1_1);
                                            }
                                            else if(arg14.messageOwner.action.user_id == v5) {
                                                v1_1 = new Object[v8];
                                                v1_1[0] = v10_1;
                                                v1_1[1] = v11.title;
                                                return LocaleController.formatString("NotificationGroupLeftMember", 2131625345, v1_1);
                                            }
                                            else {
                                                v14 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(arg14.messageOwner.action.user_id));
                                                if(v14 == null) {
                                                    return v6;
                                                }
                                                else {
                                                    v1_1 = new Object[v1];
                                                    v1_1[0] = v10_1;
                                                    v1_1[1] = v11.title;
                                                    v1_1[v8] = UserObject.getUserName(v14);
                                                    return LocaleController.formatString("NotificationGroupKickMember", 2131625343, v1_1);
                                                }
                                            }
                                        }
                                        else if((arg14.messageOwner.action instanceof TL_messageActionChatCreate)) {
                                            return arg14.messageText.toString();
                                        }
                                        else if((arg14.messageOwner.action instanceof TL_messageActionChannelCreate)) {
                                            return arg14.messageText.toString();
                                        }
                                        else if((arg14.messageOwner.action instanceof TL_messageActionChatMigrateTo)) {
                                            return LocaleController.formatString("ActionMigrateFromGroupNotify", 2131623981, new Object[]{v11.title});
                                        }
                                        else if((arg14.messageOwner.action instanceof TL_messageActionChannelMigrateFrom)) {
                                            return LocaleController.formatString("ActionMigrateFromGroupNotify", 2131623981, new Object[]{arg14.messageOwner.action.title});
                                        }
                                        else if((arg14.messageOwner.action instanceof TL_messageActionScreenshotTaken)) {
                                            return arg14.messageText.toString();
                                        }
                                        else if((arg14.messageOwner.action instanceof TL_messageActionPinMessage)) {
                                            v15_2 = 20;
                                            if(v11 != null && (v11.megagroup)) {
                                                if(arg14.replyMessageObject == null) {
                                                    v1_1 = new Object[v8];
                                                    v1_1[0] = v10_1;
                                                    v1_1[1] = v11.title;
                                                    return LocaleController.formatString("NotificationActionPinnedNoText", 2131625318, v1_1);
                                                }
                                                else {
                                                    arg14 = arg14.replyMessageObject;
                                                    if(arg14.isMusic()) {
                                                        v1_1 = new Object[v8];
                                                        v1_1[0] = v10_1;
                                                        v1_1[1] = v11.title;
                                                        return LocaleController.formatString("NotificationActionPinnedMusic", 2131625316, v1_1);
                                                    }
                                                    else {
                                                        int v4_3 = 2131625328;
                                                        if(arg14.isVideo()) {
                                                            if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                                v14_1 = "📹 " + arg14.messageOwner.message;
                                                                v1_1 = new Object[v1];
                                                                v1_1[0] = v10_1;
                                                                v1_1[1] = v14_1;
                                                                v1_1[v8] = v11.title;
                                                                return LocaleController.formatString("NotificationActionPinnedText", v4_3, v1_1);
                                                            }

                                                            v1_1 = new Object[v8];
                                                            v1_1[0] = v10_1;
                                                            v1_1[1] = v11.title;
                                                            return LocaleController.formatString("NotificationActionPinnedVideo", 2131625330, v1_1);
                                                        }
                                                        else {
                                                            if(arg14.isGif()) {
                                                                if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                                    v14_1 = "🎬 " + arg14.messageOwner.message;
                                                                    v1_1 = new Object[v1];
                                                                    v1_1[0] = v10_1;
                                                                    v1_1[1] = v14_1;
                                                                    v1_1[v8] = v11.title;
                                                                    return LocaleController.formatString("NotificationActionPinnedText", v4_3, v1_1);
                                                                }

                                                                v1_1 = new Object[v8];
                                                                v1_1[0] = v10_1;
                                                                v1_1[1] = v11.title;
                                                                return LocaleController.formatString("NotificationActionPinnedGif", 2131625312, v1_1);
                                                            }

                                                            if(arg14.isVoice()) {
                                                                v1_1 = new Object[v8];
                                                                v1_1[0] = v10_1;
                                                                v1_1[1] = v11.title;
                                                                return LocaleController.formatString("NotificationActionPinnedVoice", 2131625332, v1_1);
                                                            }

                                                            if(arg14.isRoundVideo()) {
                                                                v1_1 = new Object[v8];
                                                                v1_1[0] = v10_1;
                                                                v1_1[1] = v11.title;
                                                                return LocaleController.formatString("NotificationActionPinnedRound", 2131625322, v1_1);
                                                            }

                                                            if(arg14.isSticker()) {
                                                                v14_1 = arg14.getStickerEmoji();
                                                                if(v14_1 != null) {
                                                                    v1_1 = new Object[v1];
                                                                    v1_1[0] = v10_1;
                                                                    v1_1[1] = v11.title;
                                                                    v1_1[v8] = v14_1;
                                                                    return LocaleController.formatString("NotificationActionPinnedStickerEmoji", 2131625326, v1_1);
                                                                }

                                                                v1_1 = new Object[v8];
                                                                v1_1[0] = v10_1;
                                                                v1_1[1] = v11.title;
                                                                return LocaleController.formatString("NotificationActionPinnedSticker", 2131625324, v1_1);
                                                            }

                                                            if((arg14.messageOwner.media instanceof TL_messageMediaDocument)) {
                                                                if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                                    v14_1 = "📎 " + arg14.messageOwner.message;
                                                                    v1_1 = new Object[v1];
                                                                    v1_1[0] = v10_1;
                                                                    v1_1[1] = v14_1;
                                                                    v1_1[v8] = v11.title;
                                                                    return LocaleController.formatString("NotificationActionPinnedText", v4_3, v1_1);
                                                                }

                                                                v1_1 = new Object[v8];
                                                                v1_1[0] = v10_1;
                                                                v1_1[1] = v11.title;
                                                                return LocaleController.formatString("NotificationActionPinnedFile", 2131625304, v1_1);
                                                            }

                                                            if(!(arg14.messageOwner.media instanceof TL_messageMediaGeo)) {
                                                                if((arg14.messageOwner.media instanceof TL_messageMediaVenue)) {
                                                                }
                                                                else if((arg14.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                                                                    v1_1 = new Object[v8];
                                                                    v1_1[0] = v10_1;
                                                                    v1_1[1] = v11.title;
                                                                    return LocaleController.formatString("NotificationActionPinnedGeoLive", 2131625310, v1_1);
                                                                }
                                                                else if((arg14.messageOwner.media instanceof TL_messageMediaContact)) {
                                                                    v1_1 = new Object[v8];
                                                                    v1_1[0] = v10_1;
                                                                    v1_1[1] = v11.title;
                                                                    return LocaleController.formatString("NotificationActionPinnedContact", 2131625302, v1_1);
                                                                }
                                                                else if((arg14.messageOwner.media instanceof TL_messageMediaPhoto)) {
                                                                    if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                                        v14_1 = "🖼 " + arg14.messageOwner.message;
                                                                        v1_1 = new Object[v1];
                                                                        v1_1[0] = v10_1;
                                                                        v1_1[1] = v14_1;
                                                                        v1_1[v8] = v11.title;
                                                                        return LocaleController.formatString("NotificationActionPinnedText", v4_3, v1_1);
                                                                    }

                                                                    v1_1 = new Object[v8];
                                                                    v1_1[0] = v10_1;
                                                                    v1_1[1] = v11.title;
                                                                    return LocaleController.formatString("NotificationActionPinnedPhoto", 2131625320, v1_1);
                                                                }
                                                                else {
                                                                    if((arg14.messageOwner.media instanceof TL_messageMediaGame)) {
                                                                        v1_1 = new Object[v8];
                                                                        v1_1[0] = v10_1;
                                                                        v1_1[1] = v11.title;
                                                                        return LocaleController.formatString("NotificationActionPinnedGame", 2131625306, v1_1);
                                                                    }

                                                                    if(arg14.messageText != null && arg14.messageText.length() > 0) {
                                                                        v14_2 = arg14.messageText;
                                                                        if(v14_2.length() > v15_2) {
                                                                            v14_1 = v14_2.subSequence(0, v15_2) + "...";
                                                                        }

                                                                        v1_1 = new Object[v1];
                                                                        v1_1[0] = v10_1;
                                                                        v1_1[1] = ((CharSequence)v14_1);
                                                                        v1_1[v8] = v11.title;
                                                                        return LocaleController.formatString("NotificationActionPinnedText", v4_3, v1_1);
                                                                    }

                                                                    v1_1 = new Object[v8];
                                                                    v1_1[0] = v10_1;
                                                                    v1_1[1] = v11.title;
                                                                    return LocaleController.formatString("NotificationActionPinnedNoText", 2131625318, v1_1);
                                                                }
                                                            }

                                                            v1_1 = new Object[v8];
                                                            v1_1[0] = v10_1;
                                                            v1_1[1] = v11.title;
                                                            return LocaleController.formatString("NotificationActionPinnedGeo", 2131625308, v1_1);
                                                        }
                                                    }
                                                }
                                            }

                                            if(arg14.replyMessageObject == null) {
                                                return LocaleController.formatString("NotificationActionPinnedNoTextChannel", 2131625319, new Object[]{v11.title});
                                            }

                                            arg14 = arg14.replyMessageObject;
                                            if(arg14.isMusic()) {
                                                return LocaleController.formatString("NotificationActionPinnedMusicChannel", 2131625317, new Object[]{v11.title});
                                            }

                                            v3_1 = 2131625329;
                                            if(arg14.isVideo()) {
                                                if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                    v14_1 = "📹 " + arg14.messageOwner.message;
                                                    v1_1 = new Object[v8];
                                                    v1_1[0] = v11.title;
                                                    v1_1[1] = v14_1;
                                                    return LocaleController.formatString("NotificationActionPinnedTextChannel", v3_1, v1_1);
                                                }

                                                return LocaleController.formatString("NotificationActionPinnedVideoChannel", 2131625331, new Object[]{v11.title});
                                            }

                                            if(arg14.isGif()) {
                                                if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                    v14_1 = "🎬 " + arg14.messageOwner.message;
                                                    v1_1 = new Object[v8];
                                                    v1_1[0] = v11.title;
                                                    v1_1[1] = v14_1;
                                                    return LocaleController.formatString("NotificationActionPinnedTextChannel", v3_1, v1_1);
                                                }

                                                return LocaleController.formatString("NotificationActionPinnedGifChannel", 2131625313, new Object[]{v11.title});
                                            }

                                            if(arg14.isVoice()) {
                                                return LocaleController.formatString("NotificationActionPinnedVoiceChannel", 2131625333, new Object[]{v11.title});
                                            }

                                            if(arg14.isRoundVideo()) {
                                                return LocaleController.formatString("NotificationActionPinnedRoundChannel", 2131625323, new Object[]{v11.title});
                                            }

                                            if(arg14.isSticker()) {
                                                v14_1 = arg14.getStickerEmoji();
                                                if(v14_1 != null) {
                                                    v2_1 = new Object[v8];
                                                    v2_1[0] = v11.title;
                                                    v2_1[1] = v14_1;
                                                    return LocaleController.formatString("NotificationActionPinnedStickerEmojiChannel", 2131625327, v2_1);
                                                }

                                                return LocaleController.formatString("NotificationActionPinnedStickerChannel", 2131625325, new Object[]{v11.title});
                                            }

                                            if((arg14.messageOwner.media instanceof TL_messageMediaDocument)) {
                                                if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                    v14_1 = "📎 " + arg14.messageOwner.message;
                                                    v1_1 = new Object[v8];
                                                    v1_1[0] = v11.title;
                                                    v1_1[1] = v14_1;
                                                    return LocaleController.formatString("NotificationActionPinnedTextChannel", v3_1, v1_1);
                                                }

                                                return LocaleController.formatString("NotificationActionPinnedFileChannel", 2131625305, new Object[]{v11.title});
                                            }

                                            if(!(arg14.messageOwner.media instanceof TL_messageMediaGeo)) {
                                                if((arg14.messageOwner.media instanceof TL_messageMediaVenue)) {
                                                }
                                                else if((arg14.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                                                    return LocaleController.formatString("NotificationActionPinnedGeoLiveChannel", 2131625311, new Object[]{v11.title});
                                                }
                                                else if((arg14.messageOwner.media instanceof TL_messageMediaContact)) {
                                                    return LocaleController.formatString("NotificationActionPinnedContactChannel", 2131625303, new Object[]{v11.title});
                                                }
                                                else if((arg14.messageOwner.media instanceof TL_messageMediaPhoto)) {
                                                    if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                                        v14_1 = "🖼 " + arg14.messageOwner.message;
                                                        v1_1 = new Object[v8];
                                                        v1_1[0] = v11.title;
                                                        v1_1[1] = v14_1;
                                                        return LocaleController.formatString("NotificationActionPinnedTextChannel", v3_1, v1_1);
                                                    }

                                                    return LocaleController.formatString("NotificationActionPinnedPhotoChannel", 2131625321, new Object[]{v11.title});
                                                }
                                                else {
                                                    if((arg14.messageOwner.media instanceof TL_messageMediaGame)) {
                                                        return LocaleController.formatString("NotificationActionPinnedGameChannel", 2131625307, new Object[]{v11.title});
                                                    }

                                                    if(arg14.messageText != null && arg14.messageText.length() > 0) {
                                                        v14_2 = arg14.messageText;
                                                        if(v14_2.length() > v15_2) {
                                                            v14_1 = v14_2.subSequence(0, v15_2) + "...";
                                                        }

                                                        v1_1 = new Object[v8];
                                                        v1_1[0] = v11.title;
                                                        v1_1[1] = ((CharSequence)v14_1);
                                                        return LocaleController.formatString("NotificationActionPinnedTextChannel", v3_1, v1_1);
                                                    }

                                                    return LocaleController.formatString("NotificationActionPinnedNoTextChannel", 2131625319, new Object[]{v11.title});
                                                }
                                            }

                                            return LocaleController.formatString("NotificationActionPinnedGeoChannel", 2131625309, new Object[]{v11.title});
                                        }
                                        else {
                                            if(!(arg14.messageOwner.action instanceof TL_messageActionGameScore)) {
                                                return v6;
                                            }

                                            return arg14.messageText.toString();
                                        }
                                    }

                                    if(arg14.messageOwner.to_id.channel_id != 0 && !v11.megagroup) {
                                        return LocaleController.formatString("ChannelPhotoEditNotification", 2131624349, new Object[]{v11.title});
                                    }

                                    v1_1 = new Object[v8];
                                    v1_1[0] = v10_1;
                                    v1_1[1] = v11.title;
                                    return LocaleController.formatString("NotificationEditedGroupPhoto", 2131625337, v1_1);
                                }
                            }

                            return arg14.messageText.toString();
                        }
                    }
                }
                else {
                    if(arg14.isMediaEmpty()) {
                        if(!TextUtils.isEmpty(arg14.messageOwner.message)) {
                            return arg14.messageOwner.message;
                        }

                        return LocaleController.getString("Message", v3_1);
                    }

                    if((arg14.messageOwner.media instanceof TL_messageMediaPhoto)) {
                        if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                            return "🖼 " + arg14.messageOwner.message;
                        }

                        if(arg14.messageOwner.media.ttl_seconds != 0) {
                            return LocaleController.getString("AttachDestructingPhoto", 2131624137);
                        }

                        return LocaleController.getString("AttachPhoto", 2131624151);
                    }

                    if(arg14.isVideo()) {
                        if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                            return "📹 " + arg14.messageOwner.message;
                        }

                        if(arg14.messageOwner.media.ttl_seconds != 0) {
                            return LocaleController.getString("AttachDestructingVideo", 2131624138);
                        }

                        return LocaleController.getString("AttachVideo", 2131624158);
                    }

                    if(arg14.isGame()) {
                        return LocaleController.getString("AttachGame", 2131624141);
                    }

                    if(arg14.isVoice()) {
                        return LocaleController.getString("AttachAudio", 2131624133);
                    }

                    if(arg14.isRoundVideo()) {
                        return LocaleController.getString("AttachRound", 2131624153);
                    }

                    if(arg14.isMusic()) {
                        return LocaleController.getString("AttachMusic", 2131624150);
                    }

                    if((arg14.messageOwner.media instanceof TL_messageMediaContact)) {
                        return LocaleController.getString("AttachContact", 2131624136);
                    }

                    if(!(arg14.messageOwner.media instanceof TL_messageMediaGeo)) {
                        if((arg14.messageOwner.media instanceof TL_messageMediaVenue)) {
                        }
                        else if((arg14.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                            return LocaleController.getString("AttachLiveLocation", 2131624145);
                        }
                        else if(!(arg14.messageOwner.media instanceof TL_messageMediaDocument)) {
                            return v6;
                        }
                        else if(arg14.isSticker()) {
                            v14_1 = arg14.getStickerEmoji();
                            v15_2 = 2131624154;
                            if(v14_1 != null) {
                                return v14_1 + " " + LocaleController.getString("AttachSticker", v15_2);
                            }
                            else {
                                return LocaleController.getString("AttachSticker", v15_2);
                            }
                        }
                        else if(arg14.isGif()) {
                            if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                return "🎬 " + arg14.messageOwner.message;
                            }

                            return LocaleController.getString("AttachGif", 2131624142);
                        }
                        else {
                            if(Build$VERSION.SDK_INT >= v2 && !TextUtils.isEmpty(arg14.messageOwner.message)) {
                                return "📎 " + arg14.messageOwner.message;
                            }

                            return LocaleController.getString("AttachDocument", 2131624139);
                        }
                    }

                    goto label_1279;
                }

                return v6;
            label_1279:
                return LocaleController.getString("AttachLocation", 2131624147);
            label_1283:
                return LocaleController.getString("Message", v3_1);
            }
        }

        return LocaleController.getString("YouHaveNewMessage", v2);
    }

    private String getStringForMessage(MessageObject arg19, boolean arg20, boolean[] arg21) {
        Object[] v2_3;
        int v3_1;
        String v2_1;
        Object[] v5_1;
        int v2;
        String v1_1;
        CharSequence v1_3;
        User v1_2;
        Chat v12_1;
        String v11_2;
        Object[] v3;
        NotificationsController v0 = this;
        MessageObject v1 = arg19;
        int v6 = 2131626442;
        if(!AndroidUtilities.needShowPasscode(false)) {
            if(SharedConfig.isWaitingForPasscodeEnter) {
            }
            else {
                long v7 = v1.messageOwner.dialog_id;
                int v5 = v1.messageOwner.to_id.chat_id != 0 ? v1.messageOwner.to_id.chat_id : v1.messageOwner.to_id.channel_id;
                int v9 = v1.messageOwner.to_id.user_id;
                int v11 = 2131624339;
                int v12 = 2131625364;
                int v13 = 2131625375;
                int v14 = 2;
                if(arg19.isFcmMessage()) {
                    if(v5 != 0 || v9 == 0) {
                        if(v5 != 0 && !MessagesController.getNotificationsSettings(v0.currentAccount).getBoolean("EnablePreviewGroup", true)) {
                            if(!arg19.isMegagroup() && v1.messageOwner.to_id.channel_id != 0) {
                                return LocaleController.formatString("ChannelMessageNoText", v11, new Object[]{v1.localName});
                            }

                            v3 = new Object[v14];
                            v3[0] = v1.localUserName;
                            v3[1] = v1.localName;
                            return LocaleController.formatString("NotificationMessageGroupNoText", v12, v3);
                        }
                    }
                    else if(!MessagesController.getNotificationsSettings(v0.currentAccount).getBoolean("EnablePreviewAll", true)) {
                        return LocaleController.formatString("NotificationMessageNoText", v13, new Object[]{v1.localName});
                    }

                    arg21[0] = true;
                    return v1.messageText;
                }

                if(v9 == 0) {
                    if(arg19.isFromUser()) {
                        goto label_82;
                    }
                    else if(arg19.getId() < 0) {
                        goto label_82;
                    }
                    else {
                        v9 = -v5;
                    }
                }
                else if(v9 == UserConfig.getInstance(v0.currentAccount).getClientUserId()) {
                label_82:
                    v9 = v1.messageOwner.from_id;
                }

                if(v7 == 0) {
                    if(v5 != 0) {
                        v7 = ((long)(-v5));
                    }
                    else if(v9 != 0) {
                        v7 = ((long)v9);
                    }
                }

                String v10 = null;
                if(v9 > 0) {
                    User v11_1 = MessagesController.getInstance(v0.currentAccount).getUser(Integer.valueOf(v9));
                    if(v11_1 != null) {
                        v11_2 = UserObject.getUserName(v11_1);
                    }
                    else {
                        goto label_115;
                    }
                }
                else {
                    Chat v11_3 = MessagesController.getInstance(v0.currentAccount).getChat(Integer.valueOf(-v9));
                    if(v11_3 != null) {
                        v11_2 = v11_3.title;
                    }
                    else {
                    label_115:
                        v11_2 = v10;
                    }
                }

                if(v11_2 == null) {
                    return v10;
                }

                if(v5 != 0) {
                    v12_1 = MessagesController.getInstance(v0.currentAccount).getChat(Integer.valueOf(v5));
                    if(v12_1 == null) {
                        return v10;
                    }
                }
                else {
                    v12_1 = ((Chat)v10);
                }

                if((((int)v7)) == 0) {
                    v10 = LocaleController.getString("YouHaveNewMessage", v6);
                }
                else {
                    v6 = 2131625382;
                    int v7_1 = 3;
                    int v8 = 19;
                    if(v5 != 0 || v9 == 0) {
                        if(v5 == 0) {
                            return v10;
                        }

                        if(!MessagesController.getNotificationsSettings(v0.currentAccount).getBoolean("EnablePreviewGroup", true)) {
                            if((ChatObject.isChannel(v12_1)) && !v12_1.megagroup) {
                                v1_1 = "ChannelMessageNoText";
                                v2_3 = new Object[]{v11_2};
                            label_1731:
                                v3_1 = 2131624339;
                                goto label_1739;
                            }

                            v1_1 = "NotificationMessageGroupNoText";
                            v2_3 = new Object[v14];
                            v2_3[0] = v11_2;
                            v2_3[1] = v12_1.title;
                        }
                        else if(!(v1.messageOwner instanceof TL_messageService)) {
                            if((ChatObject.isChannel(v12_1)) && !v12_1.megagroup) {
                                if(arg19.isMediaEmpty()) {
                                    if(!arg20 && v1.messageOwner.message != null && v1.messageOwner.message.length() != 0) {
                                        v5_1 = new Object[v14];
                                        v5_1[0] = v11_2;
                                        v5_1[1] = v1.messageOwner.message;
                                        v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                        arg21[0] = true;
                                        return v10;
                                    }

                                    v1_1 = "ChannelMessageNoText";
                                    v2_3 = new Object[]{v11_2};
                                    goto label_1731;
                                }
                                else {
                                    if((v1.messageOwner.media instanceof TL_messageMediaPhoto)) {
                                        if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                            v5_1 = new Object[v14];
                                            v5_1[0] = v11_2;
                                            v5_1[1] = "🖼 " + v1.messageOwner.message;
                                            v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                            arg21[0] = true;
                                            return v10;
                                        }

                                        v1_1 = "ChannelMessagePhoto";
                                        v2 = 2131624340;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }

                                    if(arg19.isVideo()) {
                                        if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                            v5_1 = new Object[v14];
                                            v5_1[0] = v11_2;
                                            v5_1[1] = "📹 " + v1.messageOwner.message;
                                            v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                            arg21[0] = true;
                                            return v10;
                                        }

                                        v1_1 = "ChannelMessageVideo";
                                        v2 = 2131624344;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }

                                    if(arg19.isVoice()) {
                                        v1_1 = "ChannelMessageAudio";
                                        v2 = 2131624321;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }

                                    if(arg19.isRoundVideo()) {
                                        v1_1 = "ChannelMessageRound";
                                        v2 = 2131624341;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }

                                    if(arg19.isMusic()) {
                                        v1_1 = "ChannelMessageMusic";
                                        v2 = 2131624338;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }

                                    if((v1.messageOwner.media instanceof TL_messageMediaContact)) {
                                        v1_1 = "ChannelMessageContact";
                                        v2 = 2131624322;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }

                                    if(!(v1.messageOwner.media instanceof TL_messageMediaGeo)) {
                                        if((v1.messageOwner.media instanceof TL_messageMediaVenue)) {
                                        }
                                        else if((v1.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                                            v1_1 = "ChannelMessageLiveLocation";
                                            v2 = 2131624336;
                                            v3 = new Object[]{v11_2};
                                            goto label_612;
                                        }
                                        else if(!(v1.messageOwner.media instanceof TL_messageMediaDocument)) {
                                            return v10;
                                        }
                                        else if(arg19.isSticker()) {
                                            v1_1 = arg19.getStickerEmoji();
                                            if(v1_1 != null) {
                                                v2_1 = "ChannelMessageStickerEmoji";
                                                v3_1 = 2131624343;
                                                v5_1 = new Object[v14];
                                                v5_1[0] = v11_2;
                                                v5_1[1] = v1_1;
                                                goto label_407;
                                            }
                                            else {
                                                v1_1 = "ChannelMessageSticker";
                                                v2 = 2131624342;
                                                v3 = new Object[]{v11_2};
                                                goto label_414;
                                            }
                                        }
                                        else {
                                            if(arg19.isGif()) {
                                                if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                    v5_1 = new Object[v14];
                                                    v5_1[0] = v11_2;
                                                    v5_1[1] = "🎬 " + v1.messageOwner.message;
                                                    v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                                    arg21[0] = true;
                                                    return v10;
                                                }

                                                v1_1 = "ChannelMessageGIF";
                                                v2 = 2131624325;
                                                v3 = new Object[]{v11_2};
                                            }
                                            else {
                                                if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                    v5_1 = new Object[v14];
                                                    v5_1[0] = v11_2;
                                                    v5_1[1] = "📎 " + v1.messageOwner.message;
                                                    v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                                    arg21[0] = true;
                                                    return v10;
                                                }

                                                v1_1 = "ChannelMessageDocument";
                                                v2 = 2131624323;
                                                v3 = new Object[]{v11_2};
                                            }

                                            goto label_612;
                                        }
                                    }

                                    v1_1 = "ChannelMessageMap";
                                    v2 = 2131624337;
                                    v3 = new Object[]{v11_2};
                                    goto label_612;
                                }
                            }

                            v5 = 2131625369;
                            if(arg19.isMediaEmpty()) {
                                if(!arg20 && v1.messageOwner.message != null && v1.messageOwner.message.length() != 0) {
                                    v2_1 = "NotificationMessageGroupText";
                                    v3 = new Object[v7_1];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    v3[v14] = v1.messageOwner.message;
                                    goto label_1488;
                                }

                                v1_1 = "NotificationMessageGroupNoText";
                                v2_3 = new Object[v14];
                                v2_3[0] = v11_2;
                                v2_3[1] = v12_1.title;
                                goto label_1738;
                            }
                            else {
                                if((v1.messageOwner.media instanceof TL_messageMediaPhoto)) {
                                    if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                        v2_1 = "NotificationMessageGroupText";
                                        v3 = new Object[v7_1];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        v3[v14] = "🖼 " + v1.messageOwner.message;
                                        goto label_1488;
                                    }

                                    v1_1 = "NotificationMessageGroupPhoto";
                                    v2 = 2131625365;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_612;
                                }

                                if(arg19.isVideo()) {
                                    if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                        v2_1 = "NotificationMessageGroupText";
                                        v3 = new Object[v7_1];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        v3[v14] = "📹 " + v1.messageOwner.message;
                                        goto label_1488;
                                    }

                                    v1_1 = "NotificationMessageGroupVideo";
                                    v2 = 2131625370;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_612;
                                }

                                if(arg19.isVoice()) {
                                    v1_1 = "NotificationMessageGroupAudio";
                                    v2 = 2131625355;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_612;
                                }

                                if(arg19.isRoundVideo()) {
                                    v1_1 = "NotificationMessageGroupRound";
                                    v2 = 2131625366;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_612;
                                }

                                if(arg19.isMusic()) {
                                    v1_1 = "NotificationMessageGroupMusic";
                                    v2 = 2131625363;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_612;
                                }

                                if((v1.messageOwner.media instanceof TL_messageMediaContact)) {
                                    v1_1 = "NotificationMessageGroupContact";
                                    v2 = 2131625356;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_612;
                                }

                                if((v1.messageOwner.media instanceof TL_messageMediaGame)) {
                                    v2_1 = "NotificationMessageGroupGame";
                                    v3_1 = 2131625358;
                                    v5_1 = new Object[v7_1];
                                    v5_1[0] = v11_2;
                                    v5_1[1] = v12_1.title;
                                    v5_1[v14] = v1.messageOwner.media.game.title;
                                label_626:
                                    return LocaleController.formatString(v2_1, v3_1, v5_1);
                                }

                                if(!(v1.messageOwner.media instanceof TL_messageMediaGeo)) {
                                    if((v1.messageOwner.media instanceof TL_messageMediaVenue)) {
                                    }
                                    else if((v1.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                                        v1_1 = "NotificationMessageGroupLiveLocation";
                                        v2 = 2131625361;
                                        v3 = new Object[v14];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        goto label_612;
                                    }
                                    else if(!(v1.messageOwner.media instanceof TL_messageMediaDocument)) {
                                        return v10;
                                    }
                                    else if(arg19.isSticker()) {
                                        v1_1 = arg19.getStickerEmoji();
                                        if(v1_1 != null) {
                                            v2_1 = "NotificationMessageGroupStickerEmoji";
                                            v3_1 = 2131625368;
                                            v5_1 = new Object[v7_1];
                                            v5_1[0] = v11_2;
                                            v5_1[1] = v12_1.title;
                                            v5_1[v14] = v1_1;
                                        label_407:
                                            return LocaleController.formatString(v2_1, v3_1, v5_1);
                                        }
                                        else {
                                            v1_1 = "NotificationMessageGroupSticker";
                                            v2 = 2131625367;
                                            v3 = new Object[v14];
                                            v3[0] = v11_2;
                                            v3[1] = v12_1.title;
                                        label_414:
                                            return LocaleController.formatString(v1_1, v2, v3);
                                        }
                                    }
                                    else if(arg19.isGif()) {
                                        if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                            v2_1 = "NotificationMessageGroupText";
                                            v3 = new Object[v7_1];
                                            v3[0] = v11_2;
                                            v3[1] = v12_1.title;
                                            v3[v14] = "🎬 " + v1.messageOwner.message;
                                            goto label_1488;
                                        }

                                        v1_1 = "NotificationMessageGroupGif";
                                        v2 = 2131625359;
                                        v3 = new Object[v14];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        goto label_612;
                                    }
                                    else {
                                        if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                            v2_1 = "NotificationMessageGroupText";
                                            v3 = new Object[v7_1];
                                            v3[0] = v11_2;
                                            v3[1] = v12_1.title;
                                            v3[v14] = "📎 " + v1.messageOwner.message;
                                            goto label_1488;
                                        }

                                        goto label_1710;
                                    }
                                }

                                goto label_1717;
                            }

                        label_1488:
                            return LocaleController.formatString(v2_1, v5, v3);
                        label_1710:
                            v1_1 = "NotificationMessageGroupDocument";
                            v2 = 2131625357;
                            v3 = new Object[v14];
                            v3[0] = v11_2;
                            v3[1] = v12_1.title;
                            goto label_612;
                        label_1717:
                            v1_1 = "NotificationMessageGroupMap";
                            v2 = 2131625362;
                            v3 = new Object[v14];
                            v3[0] = v11_2;
                            v3[1] = v12_1.title;
                        label_612:
                            return LocaleController.formatString(v1_1, v2, v3);
                        }
                        else if((v1.messageOwner.action instanceof TL_messageActionChatAddUser)) {
                            v2 = v1.messageOwner.action.user_id;
                            if(v2 == 0 && v1.messageOwner.action.users.size() == 1) {
                                v2 = v1.messageOwner.action.users.get(0).intValue();
                            }

                            if(v2 != 0) {
                                if(v1.messageOwner.to_id.channel_id != 0 && !v12_1.megagroup) {
                                    v1_1 = "ChannelAddedByNotification";
                                    v2 = 2131624286;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_414;
                                }

                                if(v2 == UserConfig.getInstance(v0.currentAccount).getClientUserId()) {
                                    v1_1 = "NotificationInvitedToGroup";
                                    v2 = 2131625346;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_414;
                                }

                                v1_2 = MessagesController.getInstance(v0.currentAccount).getUser(Integer.valueOf(v2));
                                if(v1_2 == null) {
                                    return v10;
                                }

                                if(v9 == v1_2.id) {
                                    if(v12_1.megagroup) {
                                        v1_1 = "NotificationGroupAddSelfMega";
                                        v2 = 2131625340;
                                        v3 = new Object[v14];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        goto label_414;
                                    }

                                    v1_1 = "NotificationGroupAddSelf";
                                    v2 = 2131625339;
                                    v3 = new Object[v14];
                                    v3[0] = v11_2;
                                    v3[1] = v12_1.title;
                                    goto label_414;
                                }

                                v2_1 = "NotificationGroupAddMember";
                                v3_1 = 2131625338;
                                v5_1 = new Object[v7_1];
                                v5_1[0] = v11_2;
                                v5_1[1] = v12_1.title;
                                v5_1[v14] = UserObject.getUserName(v1_2);
                                goto label_407;
                            }

                            StringBuilder v2_4 = new StringBuilder("");
                            for(v3_1 = 0; v3_1 < v1.messageOwner.action.users.size(); ++v3_1) {
                                User v5_2 = MessagesController.getInstance(v0.currentAccount).getUser(v1.messageOwner.action.users.get(v3_1));
                                if(v5_2 != null) {
                                    String v5_3 = UserObject.getUserName(v5_2);
                                    if(v2_4.length() != 0) {
                                        v2_4.append(", ");
                                    }

                                    v2_4.append(v5_3);
                                }
                            }

                            v5_1 = new Object[v7_1];
                            v5_1[0] = v11_2;
                            v5_1[1] = v12_1.title;
                            v5_1[v14] = v2_4.toString();
                            return LocaleController.formatString("NotificationGroupAddMember", 2131625338, v5_1);
                        }
                        else {
                            if((v1.messageOwner.action instanceof TL_messageActionChatJoinedByLink)) {
                                v1_1 = "NotificationInvitedToGroupByLink";
                                v2 = 2131625347;
                                v3 = new Object[v14];
                                v3[0] = v11_2;
                                v3[1] = v12_1.title;
                                goto label_612;
                            }

                            if((v1.messageOwner.action instanceof TL_messageActionChatEditTitle)) {
                                v2_1 = "NotificationEditedGroupName";
                                v3_1 = 2131625336;
                                v5_1 = new Object[v14];
                                v5_1[0] = v11_2;
                                v5_1[1] = v1.messageOwner.action.title;
                                goto label_626;
                            }

                            if(!(v1.messageOwner.action instanceof TL_messageActionChatEditPhoto)) {
                                if((v1.messageOwner.action instanceof TL_messageActionChatDeletePhoto)) {
                                }
                                else if((v1.messageOwner.action instanceof TL_messageActionChatDeleteUser)) {
                                    if(v1.messageOwner.action.user_id == UserConfig.getInstance(v0.currentAccount).getClientUserId()) {
                                        v1_1 = "NotificationGroupKickYou";
                                        v2 = 2131625344;
                                        v3 = new Object[v14];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        goto label_612;
                                    }
                                    else if(v1.messageOwner.action.user_id == v9) {
                                        v1_1 = "NotificationGroupLeftMember";
                                        v2 = 2131625345;
                                        v3 = new Object[v14];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        goto label_612;
                                    }
                                    else {
                                        v1_2 = MessagesController.getInstance(v0.currentAccount).getUser(Integer.valueOf(v1.messageOwner.action.user_id));
                                        if(v1_2 == null) {
                                            return v10;
                                        }
                                        else {
                                            v2_1 = "NotificationGroupKickMember";
                                            v3_1 = 2131625343;
                                            v5_1 = new Object[v7_1];
                                            v5_1[0] = v11_2;
                                            v5_1[1] = v12_1.title;
                                            v5_1[v14] = UserObject.getUserName(v1_2);
                                            goto label_626;
                                        }
                                    }
                                }
                                else if(((v1.messageOwner.action instanceof TL_messageActionChatCreate)) || ((v1.messageOwner.action instanceof TL_messageActionChannelCreate)) || ((v1.messageOwner.action instanceof TL_messageActionScreenshotTaken))) {
                                label_688:
                                    return v1.messageText.toString();
                                }
                                else if((v1.messageOwner.action instanceof TL_messageActionChatMigrateTo)) {
                                    v1_1 = "ActionMigrateFromGroupNotify";
                                    v2 = 2131623981;
                                    v3 = new Object[]{v12_1.title};
                                    goto label_612;
                                }
                                else if((v1.messageOwner.action instanceof TL_messageActionChannelMigrateFrom)) {
                                    v2_1 = "ActionMigrateFromGroupNotify";
                                    v3_1 = 2131623981;
                                    v5_1 = new Object[]{v1.messageOwner.action.title};
                                    goto label_626;
                                }
                                else if((v1.messageOwner.action instanceof TL_messageActionPinMessage)) {
                                    v2 = 20;
                                    if(v12_1 == null || !v12_1.megagroup) {
                                        if(v1.replyMessageObject == null) {
                                            v1_1 = "NotificationActionPinnedNoTextChannel";
                                            v2 = 2131625319;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_612;
                                        }

                                        v1 = v1.replyMessageObject;
                                        if(v1.isMusic()) {
                                            v1_1 = "NotificationActionPinnedMusicChannel";
                                            v2 = 2131625317;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_414;
                                        }

                                        v5 = 2131625329;
                                        if(v1.isVideo()) {
                                            if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                v1_1 = "📹 " + v1.messageOwner.message;
                                                v2_1 = "NotificationActionPinnedTextChannel";
                                                v3 = new Object[v14];
                                                v3[0] = v12_1.title;
                                                v3[1] = v1_1;
                                                goto label_773;
                                            }

                                            v1_1 = "NotificationActionPinnedVideoChannel";
                                            v2 = 2131625331;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_414;
                                        }

                                        if(v1.isGif()) {
                                            if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                v1_1 = "🎬 " + v1.messageOwner.message;
                                                v2_1 = "NotificationActionPinnedTextChannel";
                                                v3 = new Object[v14];
                                                v3[0] = v12_1.title;
                                                v3[1] = v1_1;
                                                goto label_773;
                                            }

                                            v1_1 = "NotificationActionPinnedGifChannel";
                                            v2 = 2131625313;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_414;
                                        }

                                        if(v1.isVoice()) {
                                            v1_1 = "NotificationActionPinnedVoiceChannel";
                                            v2 = 2131625333;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_414;
                                        }

                                        if(v1.isRoundVideo()) {
                                            v1_1 = "NotificationActionPinnedRoundChannel";
                                            v2 = 2131625323;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_414;
                                        }

                                        if(v1.isSticker()) {
                                            v1_1 = v1.getStickerEmoji();
                                            if(v1_1 != null) {
                                                v2_1 = "NotificationActionPinnedStickerEmojiChannel";
                                                v3_1 = 2131625327;
                                                v5_1 = new Object[v14];
                                                v5_1[0] = v12_1.title;
                                                v5_1[1] = v1_1;
                                                goto label_407;
                                            }

                                            v1_1 = "NotificationActionPinnedStickerChannel";
                                            v2 = 2131625325;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_414;
                                        }

                                        if((v1.messageOwner.media instanceof TL_messageMediaDocument)) {
                                            if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                v1_1 = "📎 " + v1.messageOwner.message;
                                                v2_1 = "NotificationActionPinnedTextChannel";
                                                v3 = new Object[v14];
                                                v3[0] = v12_1.title;
                                                v3[1] = v1_1;
                                                goto label_773;
                                            }

                                            v1_1 = "NotificationActionPinnedFileChannel";
                                            v2 = 2131625305;
                                            v3 = new Object[]{v12_1.title};
                                            goto label_414;
                                        }

                                        if(!(v1.messageOwner.media instanceof TL_messageMediaGeo)) {
                                            if((v1.messageOwner.media instanceof TL_messageMediaVenue)) {
                                            }
                                            else if((v1.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                                                v1_1 = "NotificationActionPinnedGeoLiveChannel";
                                                v2 = 2131625311;
                                                v3 = new Object[]{v12_1.title};
                                                goto label_414;
                                            }
                                            else if((v1.messageOwner.media instanceof TL_messageMediaContact)) {
                                                v1_1 = "NotificationActionPinnedContactChannel";
                                                v2 = 2131625303;
                                                v3 = new Object[]{v12_1.title};
                                                goto label_414;
                                            }
                                            else if((v1.messageOwner.media instanceof TL_messageMediaPhoto)) {
                                                if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                    v1_1 = "🖼 " + v1.messageOwner.message;
                                                    v2_1 = "NotificationActionPinnedTextChannel";
                                                    v3 = new Object[v14];
                                                    v3[0] = v12_1.title;
                                                    v3[1] = v1_1;
                                                    goto label_773;
                                                }

                                                v1_1 = "NotificationActionPinnedPhotoChannel";
                                                v2 = 2131625321;
                                                v3 = new Object[]{v12_1.title};
                                                goto label_414;
                                            }
                                            else {
                                                if((v1.messageOwner.media instanceof TL_messageMediaGame)) {
                                                    v1_1 = "NotificationActionPinnedGameChannel";
                                                    v2 = 2131625307;
                                                    v3 = new Object[]{v12_1.title};
                                                    goto label_414;
                                                }

                                                if(v1.messageText != null && v1.messageText.length() > 0) {
                                                    v1_3 = v1.messageText;
                                                    if(v1_3.length() > v2) {
                                                        v1_1 = v1_3.subSequence(0, v2) + "...";
                                                    }

                                                    v2_1 = "NotificationActionPinnedTextChannel";
                                                    v3 = new Object[v14];
                                                    v3[0] = v12_1.title;
                                                    v3[1] = v1_3;
                                                    goto label_773;
                                                }

                                                goto label_1218;
                                            }
                                        }

                                        goto label_1224;
                                    }
                                    else if(v1.replyMessageObject == null) {
                                        v1_1 = "NotificationActionPinnedNoText";
                                        v2 = 2131625318;
                                        v3 = new Object[v14];
                                        v3[0] = v11_2;
                                        v3[1] = v12_1.title;
                                        goto label_612;
                                    }
                                    else {
                                        v1 = v1.replyMessageObject;
                                        if(v1.isMusic()) {
                                            v1_1 = "NotificationActionPinnedMusic";
                                            v2 = 2131625316;
                                            v3 = new Object[v14];
                                            v3[0] = v11_2;
                                            v3[1] = v12_1.title;
                                        }
                                        else {
                                            v5 = 2131625328;
                                            if(v1.isVideo()) {
                                                if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                    v1_1 = "📹 " + v1.messageOwner.message;
                                                    v2_1 = "NotificationActionPinnedText";
                                                    v3 = new Object[v7_1];
                                                    v3[0] = v11_2;
                                                    v3[1] = v1_1;
                                                    v3[v14] = v12_1.title;
                                                    goto label_773;
                                                }

                                                v1_1 = "NotificationActionPinnedVideo";
                                                v2 = 2131625330;
                                                v3 = new Object[v14];
                                                v3[0] = v11_2;
                                                v3[1] = v12_1.title;
                                            }
                                            else {
                                                if(v1.isGif()) {
                                                    if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                        v1_1 = "🎬 " + v1.messageOwner.message;
                                                        v2_1 = "NotificationActionPinnedText";
                                                        v3 = new Object[v7_1];
                                                        v3[0] = v11_2;
                                                        v3[1] = v1_1;
                                                        v3[v14] = v12_1.title;
                                                        goto label_773;
                                                    }

                                                    v1_1 = "NotificationActionPinnedGif";
                                                    v2 = 2131625312;
                                                    v3 = new Object[v14];
                                                    v3[0] = v11_2;
                                                    v3[1] = v12_1.title;
                                                    goto label_414;
                                                }

                                                if(v1.isVoice()) {
                                                    v1_1 = "NotificationActionPinnedVoice";
                                                    v2 = 2131625332;
                                                    v3 = new Object[v14];
                                                    v3[0] = v11_2;
                                                    v3[1] = v12_1.title;
                                                    goto label_414;
                                                }

                                                if(v1.isRoundVideo()) {
                                                    v1_1 = "NotificationActionPinnedRound";
                                                    v2 = 2131625322;
                                                    v3 = new Object[v14];
                                                    v3[0] = v11_2;
                                                    v3[1] = v12_1.title;
                                                    goto label_414;
                                                }

                                                if(v1.isSticker()) {
                                                    v1_1 = v1.getStickerEmoji();
                                                    if(v1_1 != null) {
                                                        v2_1 = "NotificationActionPinnedStickerEmoji";
                                                        v3_1 = 2131625326;
                                                        v5_1 = new Object[v7_1];
                                                        v5_1[0] = v11_2;
                                                        v5_1[1] = v12_1.title;
                                                        v5_1[v14] = v1_1;
                                                        goto label_407;
                                                    }

                                                    v1_1 = "NotificationActionPinnedSticker";
                                                    v2 = 2131625324;
                                                    v3 = new Object[v14];
                                                    v3[0] = v11_2;
                                                    v3[1] = v12_1.title;
                                                    goto label_414;
                                                }

                                                if((v1.messageOwner.media instanceof TL_messageMediaDocument)) {
                                                    if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                        v1_1 = "📎 " + v1.messageOwner.message;
                                                        v2_1 = "NotificationActionPinnedText";
                                                        v3 = new Object[v7_1];
                                                        v3[0] = v11_2;
                                                        v3[1] = v1_1;
                                                        v3[v14] = v12_1.title;
                                                        goto label_773;
                                                    }

                                                    v1_1 = "NotificationActionPinnedFile";
                                                    v2 = 2131625304;
                                                    v3 = new Object[v14];
                                                    v3[0] = v11_2;
                                                    v3[1] = v12_1.title;
                                                    goto label_414;
                                                }

                                                if(!(v1.messageOwner.media instanceof TL_messageMediaGeo)) {
                                                    if((v1.messageOwner.media instanceof TL_messageMediaVenue)) {
                                                    }
                                                    else if((v1.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                                                        v1_1 = "NotificationActionPinnedGeoLive";
                                                        v2 = 2131625310;
                                                        v3 = new Object[v14];
                                                        v3[0] = v11_2;
                                                        v3[1] = v12_1.title;
                                                        goto label_414;
                                                    }
                                                    else {
                                                        if((v1.messageOwner.media instanceof TL_messageMediaContact)) {
                                                            v1_1 = "NotificationActionPinnedContact";
                                                            v2 = 2131625302;
                                                            v3 = new Object[v14];
                                                            v3[0] = v11_2;
                                                            v3[1] = v12_1.title;
                                                        }
                                                        else if((v1.messageOwner.media instanceof TL_messageMediaPhoto)) {
                                                            if(Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                                v1_1 = "🖼 " + v1.messageOwner.message;
                                                                v2_1 = "NotificationActionPinnedText";
                                                                v3 = new Object[v7_1];
                                                                v3[0] = v11_2;
                                                                v3[1] = v1_1;
                                                                v3[v14] = v12_1.title;
                                                                goto label_773;
                                                            }

                                                            v1_1 = "NotificationActionPinnedPhoto";
                                                            v2 = 2131625320;
                                                            v3 = new Object[v14];
                                                            v3[0] = v11_2;
                                                            v3[1] = v12_1.title;
                                                        }
                                                        else {
                                                            if((v1.messageOwner.media instanceof TL_messageMediaGame)) {
                                                                v1_1 = "NotificationActionPinnedGame";
                                                                v2 = 2131625306;
                                                                v3 = new Object[v14];
                                                                v3[0] = v11_2;
                                                                v3[1] = v12_1.title;
                                                                goto label_414;
                                                            }

                                                            if(v1.messageText != null && v1.messageText.length() > 0) {
                                                                v1_3 = v1.messageText;
                                                                if(v1_3.length() > v2) {
                                                                    v1_1 = v1_3.subSequence(0, v2) + "...";
                                                                }

                                                                v2_1 = "NotificationActionPinnedText";
                                                                v3 = new Object[v7_1];
                                                                v3[0] = v11_2;
                                                                v3[1] = v1_3;
                                                                v3[v14] = v12_1.title;
                                                                goto label_773;
                                                            }

                                                            v1_1 = "NotificationActionPinnedNoText";
                                                            v2 = 2131625318;
                                                            v3 = new Object[v14];
                                                            v3[0] = v11_2;
                                                            v3[1] = v12_1.title;
                                                        }

                                                        goto label_414;
                                                    }
                                                }

                                                v1_1 = "NotificationActionPinnedGeo";
                                                v2 = 2131625308;
                                                v3 = new Object[v14];
                                                v3[0] = v11_2;
                                                v3[1] = v12_1.title;
                                            }
                                        }

                                        goto label_414;
                                    }

                                label_773:
                                    return LocaleController.formatString(v2_1, v5, v3);
                                label_1218:
                                    v1_1 = "NotificationActionPinnedNoTextChannel";
                                    v2 = 2131625319;
                                    v3 = new Object[]{v12_1.title};
                                    goto label_414;
                                label_1224:
                                    v1_1 = "NotificationActionPinnedGeoChannel";
                                    v2 = 2131625309;
                                    v3 = new Object[]{v12_1.title};
                                    goto label_414;
                                }
                                else {
                                    if(!(v1.messageOwner.action instanceof TL_messageActionGameScore)) {
                                        return v10;
                                    }

                                    goto label_688;
                                }
                            }

                            if(v1.messageOwner.to_id.channel_id != 0 && !v12_1.megagroup) {
                                v1_1 = "ChannelPhotoEditNotification";
                                v2 = 2131624349;
                                v3 = new Object[]{v12_1.title};
                                goto label_612;
                            }

                            v1_1 = "NotificationEditedGroupPhoto";
                            v2 = 2131625337;
                            v3 = new Object[v14];
                            v3[0] = v11_2;
                            v3[1] = v12_1.title;
                            goto label_612;
                        }

                    label_1738:
                        v3_1 = 2131625364;
                    }
                    else {
                        if(!MessagesController.getNotificationsSettings(v0.currentAccount).getBoolean("EnablePreviewAll", true)) {
                            v1_1 = "NotificationMessageNoText";
                            v2_3 = new Object[]{v11_2};
                        }
                        else if((v1.messageOwner instanceof TL_messageService)) {
                            if((v1.messageOwner.action instanceof TL_messageActionUserJoined)) {
                                v1_1 = "NotificationContactJoined";
                                v2 = 2131625334;
                                v3 = new Object[]{v11_2};
                                goto label_612;
                            }
                            else if((v1.messageOwner.action instanceof TL_messageActionUserUpdatedPhoto)) {
                                v1_1 = "NotificationContactNewPhoto";
                                v2 = 2131625335;
                                v3 = new Object[]{v11_2};
                                goto label_612;
                            }
                            else if((v1.messageOwner.action instanceof TL_messageActionLoginUnknownLocation)) {
                                v5_1 = new Object[v14];
                                v5_1[0] = LocaleController.getInstance().formatterYear.format((((long)v1.messageOwner.date)) * 1000);
                                v5_1[1] = LocaleController.getInstance().formatterDay.format((((long)v1.messageOwner.date)) * 1000);
                                v2_1 = LocaleController.formatString("formatDateAtTime", 2131626775, v5_1);
                                Object[] v6_1 = new Object[4];
                                v6_1[0] = UserConfig.getInstance(v0.currentAccount).getCurrentUser().first_name;
                                v6_1[1] = v2_1;
                                v6_1[v14] = v1.messageOwner.action.title;
                                v6_1[v7_1] = v1.messageOwner.action.address;
                                return LocaleController.formatString("NotificationUnrecognizedDevice", 2131625385, v6_1);
                            }
                            else if((v1.messageOwner.action instanceof TL_messageActionGameScore)) {
                                goto label_688;
                            }
                            else if((v1.messageOwner.action instanceof TL_messageActionPaymentSent)) {
                                goto label_688;
                            }
                            else if((v1.messageOwner.action instanceof TL_messageActionPhoneCall)) {
                                PhoneCallDiscardReason v2_2 = v1.messageOwner.action.reason;
                                if(arg19.isOut()) {
                                }
                                else if((v2_2 instanceof TL_phoneCallDiscardReasonMissed)) {
                                    v10 = LocaleController.getString("CallMessageIncomingMissed", 2131624241);
                                }
                                else {
                                }

                                return v10;
                            }
                            else {
                                return v10;
                            }
                        }
                        else if(!arg19.isMediaEmpty()) {
                            if((v1.messageOwner.media instanceof TL_messageMediaPhoto)) {
                                if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                    v5_1 = new Object[v14];
                                    v5_1[0] = v11_2;
                                    v5_1[1] = "🖼 " + v1.messageOwner.message;
                                    v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                    arg21[0] = true;
                                    return v10;
                                }

                                if(v1.messageOwner.media.ttl_seconds != 0) {
                                    v1_1 = "NotificationMessageSDPhoto";
                                    v2 = 2131625378;
                                    v3 = new Object[]{v11_2};
                                    goto label_612;
                                }

                                v1_1 = "NotificationMessagePhoto";
                                v2 = 2131625376;
                                v3 = new Object[]{v11_2};
                            }
                            else {
                                if(arg19.isVideo()) {
                                    if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                        v5_1 = new Object[v14];
                                        v5_1[0] = v11_2;
                                        v5_1[1] = "📹 " + v1.messageOwner.message;
                                        v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                        arg21[0] = true;
                                        return v10;
                                    }

                                    if(v1.messageOwner.media.ttl_seconds != 0) {
                                        v1_1 = "NotificationMessageSDVideo";
                                        v2 = 2131625379;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }

                                    v1_1 = "NotificationMessageVideo";
                                    v2 = 2131625383;
                                    v3 = new Object[]{v11_2};
                                    goto label_612;
                                }

                                if(arg19.isGame()) {
                                    v2_1 = "NotificationMessageGame";
                                    v3_1 = 2131625353;
                                    v5_1 = new Object[v14];
                                    v5_1[0] = v11_2;
                                    v5_1[1] = v1.messageOwner.media.game.title;
                                    goto label_626;
                                }

                                if(arg19.isVoice()) {
                                    v1_1 = "NotificationMessageAudio";
                                    v2 = 2131625348;
                                    v3 = new Object[]{v11_2};
                                    goto label_612;
                                }

                                if(arg19.isRoundVideo()) {
                                    v1_1 = "NotificationMessageRound";
                                    v2 = 2131625377;
                                    v3 = new Object[]{v11_2};
                                    goto label_612;
                                }

                                if(arg19.isMusic()) {
                                    v1_1 = "NotificationMessageMusic";
                                    v2 = 2131625374;
                                    v3 = new Object[]{v11_2};
                                    goto label_612;
                                }

                                if((v1.messageOwner.media instanceof TL_messageMediaContact)) {
                                    v1_1 = "NotificationMessageContact";
                                    v2 = 2131625349;
                                    v3 = new Object[]{v11_2};
                                    goto label_612;
                                }

                                if(!(v1.messageOwner.media instanceof TL_messageMediaGeo)) {
                                    if((v1.messageOwner.media instanceof TL_messageMediaVenue)) {
                                    }
                                    else if((v1.messageOwner.media instanceof TL_messageMediaGeoLive)) {
                                        v1_1 = "NotificationMessageLiveLocation";
                                        v2 = 2131625372;
                                        v3 = new Object[]{v11_2};
                                        goto label_612;
                                    }
                                    else if(!(v1.messageOwner.media instanceof TL_messageMediaDocument)) {
                                        return v10;
                                    }
                                    else if(arg19.isSticker()) {
                                        v1_1 = arg19.getStickerEmoji();
                                        if(v1_1 != null) {
                                            v2_1 = "NotificationMessageStickerEmoji";
                                            v3_1 = 2131625381;
                                            v5_1 = new Object[v14];
                                            v5_1[0] = v11_2;
                                            v5_1[1] = v1_1;
                                            goto label_407;
                                        }
                                        else {
                                            v1_1 = "NotificationMessageSticker";
                                            v2 = 2131625380;
                                            v3 = new Object[]{v11_2};
                                            goto label_414;
                                        }
                                    }
                                    else {
                                        if(arg19.isGif()) {
                                            if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                v5_1 = new Object[v14];
                                                v5_1[0] = v11_2;
                                                v5_1[1] = "🎬 " + v1.messageOwner.message;
                                                v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                                arg21[0] = true;
                                                return v10;
                                            }

                                            v1_1 = "NotificationMessageGif";
                                            v2 = 2131625354;
                                            v3 = new Object[]{v11_2};
                                        }
                                        else {
                                            if(!arg20 && Build$VERSION.SDK_INT >= v8 && !TextUtils.isEmpty(v1.messageOwner.message)) {
                                                v5_1 = new Object[v14];
                                                v5_1[0] = v11_2;
                                                v5_1[1] = "📎 " + v1.messageOwner.message;
                                                v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                                                arg21[0] = true;
                                                return v10;
                                            }

                                            v1_1 = "NotificationMessageDocument";
                                            v2 = 2131625350;
                                            v3 = new Object[]{v11_2};
                                        }

                                        goto label_612;
                                    }
                                }

                                v1_1 = "NotificationMessageMap";
                                v2 = 2131625373;
                                v3 = new Object[]{v11_2};
                            }

                            goto label_612;
                        }
                        else if(arg20) {
                            v3_1 = 2131625375;
                            v1_1 = "NotificationMessageNoText";
                            v2_3 = new Object[]{v11_2};
                            goto label_1739;
                        }
                        else if(!TextUtils.isEmpty(v1.messageOwner.message)) {
                            v5_1 = new Object[v14];
                            v5_1[0] = v11_2;
                            v5_1[1] = v1.messageOwner.message;
                            v10 = LocaleController.formatString("NotificationMessageText", v6, v5_1);
                            arg21[0] = true;
                            return v10;
                        }
                        else {
                            v1_1 = "NotificationMessageNoText";
                            v2_3 = new Object[]{v11_2};
                        }

                        v3_1 = 2131625375;
                    }

                label_1739:
                    v10 = LocaleController.formatString(v1_1, v3_1, v2_3);
                }

                return v10;
            }
        }

        return LocaleController.getString("YouHaveNewMessage", v6);
    }

    private int getTotalAllUnreadCount() {
        int v0 = 0;
        int v1 = 0;
        while(v0 < 3) {
            if(UserConfig.getInstance(v0).isClientActivated()) {
                NotificationsController v2 = NotificationsController.getInstance(v0);
                if(v2.showBadgeNumber) {
                    v1 += v2.total_unread_count;
                }
            }

            ++v0;
        }

        return v1;
    }

    public int getTotalUnreadCount() {
        return this.total_unread_count;
    }

    public boolean hasMessagesToReply() {
        int v1;
        for(v1 = 0; v1 < this.pushMessages.size(); ++v1) {
            Object v2 = this.pushMessages.get(v1);
            long v3 = ((MessageObject)v2).getDialogId();
            if((!((MessageObject)v2).messageOwner.mentioned || !(((MessageObject)v2).messageOwner.action instanceof TL_messageActionPinMessage)) && (((int)v3)) != 0 && (((MessageObject)v2).messageOwner.to_id.channel_id == 0 || (((MessageObject)v2).isMegagroup()))) {
                return 1;
            }
        }

        return 0;
    }

    private boolean isEmptyVibration(long[] arg8) {
        if(arg8 != null) {
            if(arg8.length == 0) {
            }
            else {
                int v1 = 0;
                while(true) {
                    if(v1 >= arg8.length) {
                        return 1;
                    }
                    else if(arg8[0] != 0) {
                        return 0;
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    private boolean isPersonalMessage(MessageObject arg2) {
        boolean v2;
        if(arg2.messageOwner.to_id == null || arg2.messageOwner.to_id.chat_id != 0 || arg2.messageOwner.to_id.channel_id != 0) {
        label_20:
            v2 = false;
        }
        else {
            if(arg2.messageOwner.action != null && !(arg2.messageOwner.action instanceof TL_messageActionEmpty)) {
                goto label_20;
            }

            v2 = true;
        }

        return v2;
    }

    public static void lambda$cleanup$1(NotificationsController arg6) {
        arg6.opened_dialog_id = 0;
        int v0 = 0;
        arg6.total_unread_count = 0;
        arg6.personal_count = 0;
        arg6.pushMessages.clear();
        arg6.pushMessagesDict.clear();
        arg6.fcmRandomMessagesDict.clear();
        arg6.pushDialogs.clear();
        arg6.wearNotificationsIds.clear();
        arg6.lastWearNotifiedMessageId.clear();
        arg6.delayedPushMessages.clear();
        arg6.notifyCheck = false;
        arg6.lastBadgeCount = 0;
        try {
            if(!arg6.notificationDelayWakelock.isHeld()) {
                goto label_29;
            }

            arg6.notificationDelayWakelock.release();
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

    label_29:
        arg6.setBadge(arg6.getTotalAllUnreadCount());
        SharedPreferences$Editor v1_1 = MessagesController.getNotificationsSettings(arg6.currentAccount).edit();
        v1_1.clear();
        v1_1.commit();
        if(Build$VERSION.SDK_INT < 26) {
            return;
        }

        try {
            String v1_3 = arg6.currentAccount + "channel";
            List v2 = NotificationsController.systemNotificationManager.getNotificationChannels();
            int v3 = v2.size();
            while(true) {
            label_49:
                if(v0 >= v3) {
                    return;
                }

                String v4 = v2.get(v0).getId();
                if(v4.startsWith(v1_3)) {
                    NotificationsController.systemNotificationManager.deleteNotificationChannel(v4);
                }

                break;
            }
        }
        catch(Throwable v0_1) {
            goto label_59;
        }

        ++v0;
        goto label_49;
    label_59:
        FileLog.e(v0_1);
    }

    static void lambda$dismissNotification$23() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    }

    public static void lambda$forceShowPopupForReply$5(NotificationsController arg7) {
        ArrayList v0 = new ArrayList();
        int v2;
        for(v2 = 0; v2 < arg7.pushMessages.size(); ++v2) {
            Object v3 = arg7.pushMessages.get(v2);
            long v4 = ((MessageObject)v3).getDialogId();
            if((!((MessageObject)v3).messageOwner.mentioned || !(((MessageObject)v3).messageOwner.action instanceof TL_messageActionPinMessage)) && (((int)v4)) != 0 && (((MessageObject)v3).messageOwner.to_id.channel_id == 0 || (((MessageObject)v3).isMegagroup()))) {
                v0.add(0, v3);
            }
        }

        if(!v0.isEmpty() && !AndroidUtilities.needShowPasscode(false)) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$SfVCz2vPoedKrTlwsJaPH9ngam4(arg7, v0));
        }
    }

    public static void lambda$new$0(NotificationsController arg2) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("delay reached");
        }

        if(!arg2.delayedPushMessages.isEmpty()) {
            arg2.showOrUpdateNotification(true);
            arg2.delayedPushMessages.clear();
        }
        else if(arg2.lastNotificationIsNoData) {
            NotificationsController.notificationManager.a(arg2.notificationId);
        }

        try {
            if(!arg2.notificationDelayWakelock.isHeld()) {
                return;
            }

            arg2.notificationDelayWakelock.release();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public static void lambda$null$10(NotificationsController arg6, int arg7) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(arg6.currentAccount)});
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(arg7)});
    }

    public static void lambda$null$12(NotificationsController arg5, ArrayList arg6) {
        int v0 = arg6.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            arg5.popupMessages.remove(arg6.get(v2));
        }

        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
    }

    public static void lambda$null$14(NotificationsController arg2, ArrayList arg3, int arg4) {
        arg2.popupMessages.addAll(0, ((Collection)arg3));
        if((ApplicationLoader.mainInterfacePaused) || !ApplicationLoader.isScreenOn && !SharedConfig.isWaitingForPasscodeEnter) {
            if(arg4 != 3 && (arg4 != 1 || !ApplicationLoader.isScreenOn)) {
                if(arg4 != 2) {
                    return;
                }

                if(ApplicationLoader.isScreenOn) {
                    return;
                }
            }

            Intent v3 = new Intent(ApplicationLoader.applicationContext, PopupNotificationActivity.class);
            v3.setFlags(268763140);
            ApplicationLoader.applicationContext.startActivity(v3);
        }
    }

    public static void lambda$null$15(NotificationsController arg6, int arg7) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(arg6.currentAccount)});
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(arg7)});
    }

    public static void lambda$null$17(NotificationsController arg4, ArrayList arg5) {
        int v0 = arg5.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            arg4.popupMessages.remove(arg5.get(v1));
        }
    }

    public static void lambda$null$18(NotificationsController arg6, int arg7) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(arg6.currentAccount)});
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(arg7)});
    }

    public static void lambda$null$20(NotificationsController arg6, int arg7) {
        if(arg6.total_unread_count == 0) {
            arg6.popupMessages.clear();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pushMessagesUpdated, new Object[0]);
        }

        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(arg6.currentAccount)});
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(arg7)});
    }

    static void lambda$null$24(SoundPool arg7, int arg8, int arg9) {
        if(arg9 == 0) {
            float v2 = 1f;
            float v3 = 1f;
            float v6 = 1f;
            SoundPool v0 = arg7;
            int v1 = arg8;
            try {
                v0.play(v1, v2, v3, 1, 0, v6);
            }
            catch(Exception v7) {
                FileLog.e(((Throwable)v7));
            }
        }
    }

    static int lambda$null$27(Canvas arg9) {
        Path v8 = new Path();
        v8.setFillType(Path$FillType.INVERSE_EVEN_ODD);
        int v0 = arg9.getWidth();
        float v6 = ((float)(v0 / 2));
        v8.addRoundRect(0f, 0f, ((float)v0), ((float)arg9.getHeight()), v6, v6, Path$Direction.CW);
        Paint v0_1 = new Paint();
        v0_1.setAntiAlias(true);
        v0_1.setColor(0);
        v0_1.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.SRC));
        arg9.drawPath(v8, v0_1);
        return -3;
    }

    static void lambda$null$29(SoundPool arg7, int arg8, int arg9) {
        if(arg9 == 0) {
            float v2 = 1f;
            float v3 = 1f;
            float v6 = 1f;
            SoundPool v0 = arg7;
            int v1 = arg8;
            try {
                v0.play(v1, v2, v3, 1, 0, v6);
            }
            catch(Exception v7) {
                FileLog.e(((Throwable)v7));
            }
        }
    }

    public static void lambda$null$4(NotificationsController arg2, ArrayList arg3) {
        arg2.popupReplyMessages = arg3;
        Intent v3 = new Intent(ApplicationLoader.applicationContext, PopupNotificationActivity.class);
        v3.putExtra("force", true);
        v3.putExtra("currentAccount", arg2.currentAccount);
        v3.setFlags(268763140);
        ApplicationLoader.applicationContext.startActivity(v3);
        ApplicationLoader.applicationContext.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }

    public static void lambda$null$6(NotificationsController arg4, ArrayList arg5) {
        int v0 = arg5.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            arg4.popupMessages.remove(arg5.get(v1));
        }
    }

    public static void lambda$null$7(NotificationsController arg6, int arg7) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.notificationsCountUpdated, new Object[]{Integer.valueOf(arg6.currentAccount)});
        NotificationCenter.getInstance(arg6.currentAccount).postNotificationName(NotificationCenter.dialogsUnreadCounterChanged, new Object[]{Integer.valueOf(arg7)});
    }

    public static void lambda$null$9(NotificationsController arg4, ArrayList arg5) {
        int v0 = arg5.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            arg4.popupMessages.remove(arg5.get(v1));
        }
    }

    public static void lambda$playInChatSound$25(NotificationsController arg8) {
        if(Math.abs(System.currentTimeMillis() - arg8.lastSoundPlay) <= 500) {
            return;
        }

        try {
            if(arg8.soundPool == null) {
                arg8.soundPool = new SoundPool(3, 1, 0);
                arg8.soundPool.setOnLoadCompleteListener(-$$Lambda$NotificationsController$CG8xy9PeU9jJYBqVxvPbdjjlVs4.INSTANCE);
            }

            if(arg8.soundIn == 0 && !arg8.soundInLoaded) {
                arg8.soundInLoaded = true;
                arg8.soundIn = arg8.soundPool.load(ApplicationLoader.applicationContext, 2131558407, 1);
            }

            if(arg8.soundIn == 0) {
                return;
            }
        }
        catch(Exception v0) {
            goto label_43;
        }

        try {
            arg8.soundPool.play(arg8.soundIn, 1f, 1f, 1, 0, 1f);
        }
        catch(Exception v0) {
            try {
                FileLog.e(((Throwable)v0));
            }
            catch(Exception v0) {
            label_43:
                FileLog.e(((Throwable)v0));
            }
        }
    }

    public static void lambda$playOutChatSound$30(NotificationsController arg8) {
        try {
            if(Math.abs(System.currentTimeMillis() - arg8.lastSoundOutPlay) <= 100) {
                return;
            }

            arg8.lastSoundOutPlay = System.currentTimeMillis();
            if(arg8.soundPool == null) {
                arg8.soundPool = new SoundPool(3, 1, 0);
                arg8.soundPool.setOnLoadCompleteListener(-$$Lambda$NotificationsController$pbMijsQAiK1M_HPn6KywZJE6nGQ.INSTANCE);
            }

            if(arg8.soundOut == 0 && !arg8.soundOutLoaded) {
                arg8.soundOutLoaded = true;
                arg8.soundOut = arg8.soundPool.load(ApplicationLoader.applicationContext, 2131558408, 1);
            }

            if(arg8.soundOut == 0) {
                return;
            }
        }
        catch(Exception v0) {
            goto label_46;
        }

        try {
            arg8.soundPool.play(arg8.soundOut, 1f, 1f, 1, 0, 1f);
        }
        catch(Exception v0) {
            try {
                FileLog.e(((Throwable)v0));
            }
            catch(Exception v0) {
            label_46:
                FileLog.e(((Throwable)v0));
            }
        }
    }

    public static void lambda$processDialogsUpdateRead$19(NotificationsController arg17, LongSparseArray arg18, ArrayList arg19) {
        Integer v12_1;
        boolean v7_2;
        Object v11;
        boolean v8;
        NotificationsController v0 = arg17;
        LongSparseArray v1 = arg18;
        ArrayList v2 = arg19;
        int v3 = v0.total_unread_count;
        SharedPreferences v4 = MessagesController.getNotificationsSettings(v0.currentAccount);
        int v6;
        for(v6 = 0; true; ++v6) {
            v8 = true;
            if(v6 >= arg18.size()) {
                break;
            }

            long v9 = v1.keyAt(v6);
            int v7 = v0.getNotifyOverride(v4, v9);
            if(v0.notifyCheck) {
                v11 = v0.pushDialogsOverrideMention.get(v9);
                if(v11 != null && ((Integer)v11).intValue() == 1) {
                    v0.pushDialogsOverrideMention.put(v9, Integer.valueOf(0));
                    v7 = 1;
                }
            }

            if(v7 == -1) {
                String v7_1 = (((int)v9)) < 0 ? "EnableGroup" : "EnableAll";
                v7_2 = v4.getBoolean(v7_1, true);
            }
            else {
                if(v7 != 2) {
                    v7_2 = true;
                    goto label_38;
                }

                v7_2 = false;
            }

        label_38:
            v11 = v0.pushDialogs.get(v9);
            Object v12 = v1.get(v9);
            if(((Integer)v12).intValue() == 0) {
                v0.smartNotificationsDialogs.remove(v9);
            }

            if(((Integer)v12).intValue() >= 0) {
            label_53:
                if(((v7_2) || v12_1.intValue() == 0) && v11 != null) {
                    v0.total_unread_count -= ((Integer)v11).intValue();
                }

                if(v12_1.intValue() == 0) {
                    v0.pushDialogs.remove(v9);
                    v0.pushDialogsOverrideMention.remove(v9);
                    for(v7 = 0; true; ++v7) {
                        if(v7 >= v0.pushMessages.size()) {
                            goto label_110;
                        }

                        v11 = v0.pushMessages.get(v7);
                        if(((MessageObject)v11).getDialogId() == v9) {
                            if(v0.isPersonalMessage(((MessageObject)v11))) {
                                --v0.personal_count;
                            }

                            v0.pushMessages.remove(v7);
                            --v7;
                            v0.delayedPushMessages.remove(v11);
                            long v12_2 = ((long)((MessageObject)v11).getId());
                            if(((MessageObject)v11).messageOwner.to_id.channel_id != 0) {
                                v12_2 |= (((long)((MessageObject)v11).messageOwner.to_id.channel_id)) << 32;
                            }

                            v0.pushMessagesDict.remove(v12_2);
                            v2.add(v11);
                        }
                    }
                }

                if(!v7_2) {
                    goto label_110;
                }

                v0.total_unread_count += v12_1.intValue();
                v0.pushDialogs.put(v9, v12_1);
            }
            else if(v11 == null) {
            }
            else {
                v12_1 = Integer.valueOf(((Integer)v11).intValue() + ((Integer)v12).intValue());
                goto label_53;
            }

        label_110:
        }

        if(!arg19.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$ONJqyaSxnewsyizGxRK-V30P95A(v0, v2));
        }

        if(v3 != v0.total_unread_count) {
            if(!v0.notifyCheck) {
                v0.delayedPushMessages.clear();
                v0.showOrUpdateNotification(v0.notifyCheck);
            }
            else {
                if(v0.lastOnlineFromOtherDevice > ConnectionsManager.getInstance(v0.currentAccount).getCurrentTime()) {
                }
                else {
                    v8 = false;
                }

                v0.scheduleNotificationDelay(v8);
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$GAjtCMO1qmPedRnHLLIKT37DETU(v0, v0.pushDialogs.size()));
        }

        v0.notifyCheck = false;
        if(v0.showBadgeNumber) {
            v0.setBadge(arg17.getTotalAllUnreadCount());
        }
    }

    public static void lambda$processLoadedUnreadMessages$21(NotificationsController arg17, ArrayList arg18, LongSparseArray arg19) {
        String v8_3;
        boolean v8_2;
        boolean v7_1;
        String v10_3;
        boolean v10_2;
        long v8_1;
        int v16;
        NotificationsController v0 = arg17;
        ArrayList v1 = arg18;
        LongSparseArray v2 = arg19;
        v0.pushDialogs.clear();
        v0.pushMessages.clear();
        v0.pushMessagesDict.clear();
        boolean v3 = false;
        v0.total_unread_count = 0;
        v0.personal_count = 0;
        SharedPreferences v4 = MessagesController.getNotificationsSettings(v0.currentAccount);
        LongSparseArray v5 = new LongSparseArray();
        int v6 = 2;
        int v7 = -1;
        int v8 = 1;
        if(v1 != null) {
            int v9 = 0;
            while(v9 < arg18.size()) {
                Object v10 = v1.get(v9);
                long v11 = ((long)((Message)v10).id);
                if(((Message)v10).to_id.channel_id != 0) {
                    v11 |= (((long)((Message)v10).to_id.channel_id)) << 32;
                }

                if(v0.pushMessagesDict.indexOfKey(v11) >= 0) {
                    v16 = v9;
                }
                else {
                    MessageObject v13 = new MessageObject(v0.currentAccount, ((Message)v10), false);
                    if(v0.isPersonalMessage(v13)) {
                        v0.personal_count += v8;
                    }

                    long v14 = v13.getDialogId();
                    if(v13.messageOwner.mentioned) {
                        v16 = v9;
                        v8_1 = ((long)v13.messageOwner.from_id);
                    }
                    else {
                        v16 = v9;
                        v8_1 = v14;
                    }

                    int v10_1 = v5.indexOfKey(v8_1);
                    if(v10_1 >= 0) {
                        v10_2 = v5.valueAt(v10_1).booleanValue();
                    }
                    else {
                        v10_1 = v0.getNotifyOverride(v4, v8_1);
                        if(v10_1 == v7) {
                            if((((int)v8_1)) < 0) {
                                v10_3 = "EnableGroup";
                                v7_1 = true;
                            }
                            else {
                                v7_1 = true;
                                v10_3 = "EnableAll";
                            }

                            v10_2 = v4.getBoolean(v10_3, v7_1);
                        }
                        else {
                            v7_1 = v10_1 != v6 ? true : false;
                            v10_2 = v7_1;
                        }

                        v5.put(v8_1, Boolean.valueOf(v10_2));
                    }

                    if(!v10_2) {
                        goto label_97;
                    }

                    if(v8_1 == v0.opened_dialog_id && (ApplicationLoader.isScreenOn)) {
                        goto label_97;
                    }

                    v0.pushMessagesDict.put(v11, v13);
                    v0.pushMessages.add(0, v13);
                    if(v14 == v8_1) {
                        goto label_97;
                    }

                    v0.pushDialogsOverrideMention.put(v14, Integer.valueOf(1));
                }

            label_97:
                v9 = v16 + 1;
                v6 = 2;
                v7 = -1;
                v8 = 1;
            }
        }

        int v1_1;
        for(v1_1 = 0; v1_1 < arg19.size(); ++v1_1) {
            long v6_1 = v2.keyAt(v1_1);
            v8 = v5.indexOfKey(v6_1);
            if(v8 >= 0) {
                v8_2 = v5.valueAt(v8).booleanValue();
            }
            else {
                v8 = v0.getNotifyOverride(v4, v6_1);
                Object v9_1 = v0.pushDialogsOverrideMention.get(v6_1);
                if(v9_1 != null && ((Integer)v9_1).intValue() == 1) {
                    v0.pushDialogsOverrideMention.put(v6_1, Integer.valueOf(0));
                    v8 = 1;
                }

                if(v8 == -1) {
                    if((((int)v6_1)) < 0) {
                        v8_3 = "EnableGroup";
                        v10_2 = true;
                    }
                    else {
                        v10_2 = true;
                        v8_3 = "EnableAll";
                    }

                    v8_2 = v4.getBoolean(v8_3, v10_2);
                }
                else {
                    if(v8 != 2) {
                        v8_2 = true;
                        goto label_144;
                    }

                    v8_2 = false;
                }

            label_144:
                v5.put(v6_1, Boolean.valueOf(v8_2));
            }

            if(!v8_2) {
            }
            else {
                v8 = v2.valueAt(v1_1).intValue();
                v0.pushDialogs.put(v6_1, Integer.valueOf(v8));
                v0.total_unread_count += v8;
            }
        }

        AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$CkSMdSXLZtMteSgS81186zoUJaI(v0, v0.pushDialogs.size()));
        if(SystemClock.elapsedRealtime() / 1000 < 60) {
            v3 = true;
        }

        v0.showOrUpdateNotification(v3);
        if(v0.showBadgeNumber) {
            v0.setBadge(arg17.getTotalAllUnreadCount());
        }
    }

    public static void lambda$processNewMessages$16(NotificationsController arg28, ArrayList arg29, boolean arg30, ArrayList arg31, boolean arg32) {
        boolean v4_5;
        LongSparseArray v6_4;
        int v4_3;
        int v6_3;
        String v4_1;
        String v6_1;
        boolean v15_1;
        long v13;
        boolean v26;
        LongSparseArray v27;
        int v7_1;
        long v24;
        int v23;
        int v21;
        Object v22;
        NotificationsController v0 = arg28;
        ArrayList v1 = arg29;
        ArrayList v3 = arg31;
        LongSparseArray v4 = new LongSparseArray();
        SharedPreferences v5 = MessagesController.getNotificationsSettings(v0.currentAccount);
        boolean v6 = v5.getBoolean("PinnedMessages", true);
        int v9 = 0;
        int v10 = 0;
        int v11 = 0;
        while(v9 < arg29.size()) {
            Object v12 = v1.get(v9);
            int v16 = v9;
            long v8 = ((long)((MessageObject)v12).getId());
            long v17 = 0;
            long v14 = ((MessageObject)v12).isFcmMessage() ? ((MessageObject)v12).messageOwner.random_id : v17;
            long v7 = ((MessageObject)v12).messageOwner.to_id.channel_id != 0 ? v8 | (((long)((MessageObject)v12).messageOwner.to_id.channel_id)) << 32 : v8;
            Object v9_1 = v0.pushMessagesDict.get(v7);
            if(v9_1 == null) {
                v22 = v9_1;
                v21 = v10;
                if(((MessageObject)v12).messageOwner.random_id != v17) {
                    v23 = v11;
                    v9_1 = v0.fcmRandomMessagesDict.get(((MessageObject)v12).messageOwner.random_id);
                    if(v9_1 != null) {
                        v24 = v14;
                        v0.fcmRandomMessagesDict.remove(((MessageObject)v12).messageOwner.random_id);
                    }
                    else {
                        v24 = v14;
                    }
                }
                else {
                    goto label_65;
                }
            }
            else {
                v22 = v9_1;
                v21 = v10;
            label_65:
                v23 = v11;
                v24 = v14;
                v9_1 = v22;
            }

            if(v9_1 != null) {
                if(((MessageObject)v9_1).isFcmMessage()) {
                    v0.pushMessagesDict.put(v7, v12);
                    v7_1 = v0.pushMessages.indexOf(v9_1);
                    if(v7_1 >= 0) {
                        v0.pushMessages.set(v7_1, v12);
                    }
                    else {
                    }
                }
                else {
                }

                goto label_95;
            }
            else {
                long v9_2 = ((MessageObject)v12).getDialogId();
                if(v9_2 == v0.opened_dialog_id && (ApplicationLoader.isScreenOn)) {
                    if(!arg30) {
                        arg28.playInChatSound();
                    }
                    else {
                    }

                    goto label_95;
                }

                if(((MessageObject)v12).messageOwner.mentioned) {
                    if(!v6 && ((((MessageObject)v12).messageOwner.action instanceof TL_messageActionPinMessage))) {
                    label_95:
                        v27 = v4;
                        v26 = v6;
                        v10 = v21;
                        v11 = v23;
                        goto label_211;
                    }

                    v13 = ((long)((MessageObject)v12).messageOwner.from_id);
                }
                else {
                    v13 = v9_2;
                }

                if(v0.isPersonalMessage(((MessageObject)v12))) {
                    ++v0.personal_count;
                }

                v11 = ((int)v13);
                int v15 = v4.indexOfKey(v13);
                if(v15 >= 0) {
                    v15_1 = v4.valueAt(v15).booleanValue();
                    v26 = v6;
                }
                else {
                    v15 = v0.getNotifyOverride(v5, v13);
                    v26 = v6;
                    if(v15 == -1) {
                        if(v11 < 0) {
                            v6_1 = "EnableGroup";
                            v15_1 = true;
                        }
                        else {
                            v15_1 = true;
                            v6_1 = "EnableAll";
                        }

                        v6 = v5.getBoolean(v6_1, v15_1);
                    }
                    else {
                        if(v15 != 2) {
                            v6 = true;
                            goto label_130;
                        }

                        v6 = false;
                    }

                label_130:
                    v15_1 = v6;
                    v4.put(v13, Boolean.valueOf(v15_1));
                }

                if(v11 != 0) {
                    StringBuilder v6_2 = new StringBuilder();
                    v27 = v4;
                    v6_2.append("custom_");
                    v6_2.append(v13);
                    v4_1 = v6_2.toString();
                    v6_3 = 0;
                    if(v5.getBoolean(v4_1, false)) {
                        StringBuilder v4_2 = new StringBuilder();
                        v4_2.append("popup_");
                        v4_2.append(v13);
                        v6_3 = 0;
                        v4_3 = v5.getInt(v4_2.toString(), 0);
                    }
                    else {
                        v4_3 = 0;
                    }

                    if(v4_3 == 0) {
                        v4_1 = v11 < 0 ? "popupGroup" : "popupAll";
                        v4_3 = v5.getInt(v4_1, v6_3);
                        goto label_178;
                    }

                    if(v4_3 == 1) {
                        v4_3 = 3;
                        goto label_178;
                    }

                    if(v4_3 != 2) {
                        goto label_178;
                    }

                    v4_3 = 0;
                }
                else {
                    v27 = v4;
                    v4_3 = v23;
                }

            label_178:
                if(v4_3 != 0 && ((MessageObject)v12).messageOwner.to_id.channel_id != 0 && !((MessageObject)v12).isMegagroup()) {
                    v4_3 = 0;
                }

                if(v15_1) {
                    if(v4_3 != 0) {
                        v6_3 = 0;
                        v3.add(0, v12);
                    }
                    else {
                        v6_3 = 0;
                    }

                    v0.delayedPushMessages.add(v12);
                    v0.pushMessages.add(v6_3, v12);
                    if(v7 != v17) {
                        v6_4 = v0.pushMessagesDict;
                        goto label_198;
                    }
                    else if(v24 != v17) {
                        v6_4 = v0.fcmRandomMessagesDict;
                        v7 = v24;
                    label_198:
                        v6_4.put(v7, v12);
                    }

                    if(v9_2 == v13) {
                        goto label_209;
                    }

                    v0.pushDialogsOverrideMention.put(v9_2, Integer.valueOf(1));
                }

            label_209:
                v11 = v4_3;
                v10 = 1;
            }

        label_211:
            v9 = v16 + 1;
            v6 = v26;
            v4 = v27;
        }

        v21 = v10;
        v23 = v11;
        if(v21 != 0) {
            v0.notifyCheck = arg32;
        }

        if(!arg31.isEmpty()) {
            v4_3 = 0;
            if(!AndroidUtilities.needShowPasscode(false)) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$vBhFCZdXUS15Ipx-fzqzTMIuA3o(v0, v3, v23));
            }
        }
        else {
            v4_3 = 0;
        }

        if(v21 != 0 && (arg30)) {
            long v1_1 = v1.get(v4_3).getDialogId();
            int v3_1 = v0.total_unread_count;
            v7_1 = v0.getNotifyOverride(v5, v1_1);
            if(v0.notifyCheck) {
                Object v4_4 = v0.pushDialogsOverrideMention.get(v1_1);
                if(v4_4 == null) {
                    goto label_253;
                }
                else if(((Integer)v4_4).intValue() == 1) {
                    v0.pushDialogsOverrideMention.put(v1_1, Integer.valueOf(0));
                    v4_3 = -1;
                    v7_1 = 1;
                }
                else {
                    goto label_253;
                }
            }
            else {
            label_253:
                v4_3 = -1;
            }

            if(v7_1 == v4_3) {
                if((((int)v1_1)) < 0) {
                    v4_1 = "EnableGroup";
                    v6 = true;
                }
                else {
                    v6 = true;
                    v4_1 = "EnableAll";
                }

                v4_5 = v5.getBoolean(v4_1, v6);
            }
            else {
                if(v7_1 != 2) {
                    v4_5 = true;
                    goto label_269;
                }

                v4_5 = false;
            }

        label_269:
            Object v5_1 = v0.pushDialogs.get(v1_1);
            if(v5_1 != null) {
                v15_1 = true;
                v7_1 = ((Integer)v5_1).intValue() + 1;
            }
            else {
                v15_1 = true;
                v7_1 = 1;
            }

            Integer v6_5 = Integer.valueOf(v7_1);
            if(v4_5) {
                if(v5_1 != null) {
                    v0.total_unread_count -= ((Integer)v5_1).intValue();
                }

                v0.total_unread_count += v6_5.intValue();
                v0.pushDialogs.put(v1_1, v6_5);
            }

            if(v3_1 != v0.total_unread_count) {
                if(!v0.notifyCheck) {
                    v0.delayedPushMessages.clear();
                    v0.showOrUpdateNotification(v0.notifyCheck);
                }
                else {
                    if(v0.lastOnlineFromOtherDevice > ConnectionsManager.getInstance(v0.currentAccount).getCurrentTime()) {
                    }
                    else {
                        v15_1 = false;
                    }

                    v0.scheduleNotificationDelay(v15_1);
                }

                AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$R3R5Z37efc0XPsswynnBTmucwac(v0, v0.pushDialogs.size()));
            }

            v0.notifyCheck = false;
            if(!v0.showBadgeNumber) {
                return;
            }

            v0.setBadge(arg28.getTotalAllUnreadCount());
        }
    }

    public static void lambda$processReadMessages$13(NotificationsController arg20, SparseLongArray arg21, ArrayList arg22, long arg23, int arg25, int arg26, boolean arg27) {
        long v18;
        int v17;
        NotificationsController v0 = arg20;
        SparseLongArray v1 = arg21;
        ArrayList v2 = arg22;
        int v3 = arg25;
        int v4 = arg26;
        int v7 = 1;
        if(v1 != null) {
            int v8 = 0;
            while(v8 < arg21.size()) {
                int v9 = v1.keyAt(v8);
                long v10 = v1.get(v9);
                int v12 = 0;
                while(v12 < v0.pushMessages.size()) {
                    Object v13 = v0.pushMessages.get(v12);
                    if(((MessageObject)v13).getDialogId() != (((long)v9)) || ((MessageObject)v13).getId() > (((int)v10))) {
                        v17 = v9;
                        v18 = v10;
                    }
                    else {
                        if(v0.isPersonalMessage(((MessageObject)v13))) {
                            v0.personal_count -= v7;
                        }

                        v2.add(v13);
                        long v5 = ((long)((MessageObject)v13).getId());
                        if(((MessageObject)v13).messageOwner.to_id.channel_id != 0) {
                            v17 = v9;
                            v18 = v10;
                            v5 |= (((long)((MessageObject)v13).messageOwner.to_id.channel_id)) << 32;
                        }
                        else {
                            v17 = v9;
                            v18 = v10;
                        }

                        v0.pushMessagesDict.remove(v5);
                        v0.delayedPushMessages.remove(v13);
                        v0.pushMessages.remove(v12);
                        --v12;
                    }

                    ++v12;
                    v9 = v17;
                    v10 = v18;
                    v7 = 1;
                }

                ++v8;
                v7 = 1;
            }
        }

        if(arg23 != 0 && (v3 != 0 || v4 != 0)) {
            int v1_1;
            for(v1_1 = 0; v1_1 < v0.pushMessages.size(); ++v1_1) {
                Object v5_1 = v0.pushMessages.get(v1_1);
                if(((MessageObject)v5_1).getDialogId() == arg23) {
                    if(v4 != 0) {
                        if(((MessageObject)v5_1).messageOwner.date > v4) {
                            goto label_94;
                        }

                        goto label_83;
                    }
                    else if(!arg27) {
                        if(((MessageObject)v5_1).getId() <= v3) {
                            goto label_83;
                        }
                        else if(v3 < 0) {
                            goto label_83;
                        }
                        else {
                            goto label_94;
                        }
                    }
                    else if(((MessageObject)v5_1).getId() == v3 || v3 < 0) {
                    label_83:
                        v7 = 1;
                    }
                    else {
                    label_94:
                        v7 = 0;
                    }

                    if(v7 == 0) {
                        goto label_127;
                    }

                    if(v0.isPersonalMessage(((MessageObject)v5_1))) {
                        --v0.personal_count;
                    }

                    v0.pushMessages.remove(v1_1);
                    v0.delayedPushMessages.remove(v5_1);
                    v2.add(v5_1);
                    long v7_1 = ((long)((MessageObject)v5_1).getId());
                    if(((MessageObject)v5_1).messageOwner.to_id.channel_id != 0) {
                        v7_1 |= (((long)((MessageObject)v5_1).messageOwner.to_id.channel_id)) << 32;
                    }

                    v0.pushMessagesDict.remove(v7_1);
                    --v1_1;
                }

            label_127:
            }
        }

        if(!arg22.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$uwXUA8kYkjmDHBUM6M6MDaJprzI(v0, v2));
        }
    }

    public static void lambda$removeDeletedHisoryFromNotifications$11(NotificationsController arg18, SparseIntArray arg19, ArrayList arg20) {
        Integer v12_1;
        int v17;
        Integer v10_1;
        boolean v7;
        NotificationsController v0 = arg18;
        SparseIntArray v1 = arg19;
        ArrayList v2 = arg20;
        int v3 = v0.total_unread_count;
        MessagesController.getNotificationsSettings(v0.currentAccount);
        int v4 = 0;
        int v5 = 0;
        while(true) {
            v7 = true;
            if(v5 >= arg19.size()) {
                break;
            }

            int v6 = v1.keyAt(v5);
            long v8 = ((long)(-v6));
            v6 = v1.get(v6);
            Object v10 = v0.pushDialogs.get(v8);
            if(v10 == null) {
                v10_1 = Integer.valueOf(v4);
            }

            Object v12 = v10_1;
            int v11 = 0;
            while(v11 < v0.pushMessages.size()) {
                Object v13 = v0.pushMessages.get(v11);
                if(((MessageObject)v13).getDialogId() != v8 || ((MessageObject)v13).getId() > v6) {
                    v17 = v5;
                }
                else {
                    v17 = v5;
                    v0.pushMessagesDict.remove(((MessageObject)v13).getIdWithChannel());
                    v0.delayedPushMessages.remove(v13);
                    v0.pushMessages.remove(v13);
                    --v11;
                    if(v0.isPersonalMessage(((MessageObject)v13))) {
                        --v0.personal_count;
                    }

                    v2.add(v13);
                    v12_1 = Integer.valueOf(v12_1.intValue() - 1);
                }

                ++v11;
                v5 = v17;
            }

            v17 = v5;
            if(v12_1.intValue() <= 0) {
                v12_1 = Integer.valueOf(0);
                v0.smartNotificationsDialogs.remove(v8);
            }

            if(!v12_1.equals(v10_1)) {
                v0.total_unread_count -= v10_1.intValue();
                v0.total_unread_count += v12_1.intValue();
                v0.pushDialogs.put(v8, v12_1);
            }

            if(v12_1.intValue() == 0) {
                v0.pushDialogs.remove(v8);
                v0.pushDialogsOverrideMention.remove(v8);
            }

            v5 = v17 + 1;
            v4 = 0;
        }

        if(arg20.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$sZTwdrj4Q3g5O_k6lbH6PmmVEkI(v0, v2));
        }

        if(v3 != v0.total_unread_count) {
            if(!v0.notifyCheck) {
                v0.delayedPushMessages.clear();
                v0.showOrUpdateNotification(v0.notifyCheck);
            }
            else {
                if(v0.lastOnlineFromOtherDevice > ConnectionsManager.getInstance(v0.currentAccount).getCurrentTime()) {
                }
                else {
                    v7 = false;
                }

                v0.scheduleNotificationDelay(v7);
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$hEqV8j2COvHkVH0SA_DnqOAATPc(v0, v0.pushDialogs.size()));
        }

        v0.notifyCheck = false;
        if(v0.showBadgeNumber) {
            v0.setBadge(arg18.getTotalAllUnreadCount());
        }
    }

    public static void lambda$removeDeletedMessagesFromNotifications$8(NotificationsController arg18, SparseArray arg19, ArrayList arg20) {
        Integer v13_1;
        int v16;
        Integer v11_1;
        boolean v7;
        NotificationsController v0 = arg18;
        SparseArray v1 = arg19;
        ArrayList v2 = arg20;
        int v3 = v0.total_unread_count;
        MessagesController.getNotificationsSettings(v0.currentAccount);
        int v4 = 0;
        int v5 = 0;
        while(true) {
            v7 = true;
            if(v5 >= arg19.size()) {
                break;
            }

            int v6 = v1.keyAt(v5);
            long v8 = ((long)(-v6));
            Object v10 = v1.get(v6);
            Object v11 = v0.pushDialogs.get(v8);
            if(v11 == null) {
                v11_1 = Integer.valueOf(v4);
            }

            Object v13 = v11_1;
            int v12 = 0;
            while(v12 < ((ArrayList)v10).size()) {
                v16 = v5;
                long v4_1 = (((long)v6)) << 32 | (((long)((ArrayList)v10).get(v12).intValue()));
                Object v14 = v0.pushMessagesDict.get(v4_1);
                if(v14 != null) {
                    v0.pushMessagesDict.remove(v4_1);
                    v0.delayedPushMessages.remove(v14);
                    v0.pushMessages.remove(v14);
                    if(v0.isPersonalMessage(((MessageObject)v14))) {
                        --v0.personal_count;
                    }

                    v2.add(v14);
                    v13_1 = Integer.valueOf(((Integer)v13).intValue() - 1);
                }

                ++v12;
                v5 = v16;
            }

            v16 = v5;
            if(((Integer)v13).intValue() <= 0) {
                v13_1 = Integer.valueOf(0);
                v0.smartNotificationsDialogs.remove(v8);
            }

            if(!((Integer)v13).equals(v11_1)) {
                v0.total_unread_count -= v11_1.intValue();
                v0.total_unread_count += ((Integer)v13).intValue();
                v0.pushDialogs.put(v8, v13);
            }

            if(((Integer)v13).intValue() == 0) {
                v0.pushDialogs.remove(v8);
                v0.pushDialogsOverrideMention.remove(v8);
            }

            v5 = v16 + 1;
            v4 = 0;
        }

        if(!arg20.isEmpty()) {
            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$uUrKIQpuu_OHFjMyR7HGe660wQk(v0, v2));
        }

        if(v3 != v0.total_unread_count) {
            if(!v0.notifyCheck) {
                v0.delayedPushMessages.clear();
                v0.showOrUpdateNotification(v0.notifyCheck);
            }
            else {
                if(v0.lastOnlineFromOtherDevice > ConnectionsManager.getInstance(v0.currentAccount).getCurrentTime()) {
                }
                else {
                    v7 = false;
                }

                v0.scheduleNotificationDelay(v7);
            }

            AndroidUtilities.runOnUIThread(new -$$Lambda$NotificationsController$VcdDGTs8T17vFBc_zmAJ5lCdPBU(v0, v0.pushDialogs.size()));
        }

        v0.notifyCheck = false;
        if(v0.showBadgeNumber) {
            v0.setBadge(arg18.getTotalAllUnreadCount());
        }
    }

    public static void lambda$repeatNotificationMaybe$26(NotificationsController arg2) {
        int v0 = Calendar.getInstance().get(11);
        if(v0 < 11 || v0 > 22) {
            arg2.scheduleNotificationRepeat();
        }
        else {
            NotificationsController.notificationManager.a(arg2.notificationId);
            arg2.showOrUpdateNotification(true);
        }
    }

    public static void lambda$setBadgeEnabled$22(NotificationsController arg1) {
        arg1.setBadge(arg1.getTotalAllUnreadCount());
    }

    public static void lambda$setLastOnlineFromOtherDevice$3(NotificationsController arg2, int arg3) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("set last online from other device = " + arg3);
        }

        arg2.lastOnlineFromOtherDevice = arg3;
    }

    public static void lambda$setOpenedDialogId$2(NotificationsController arg0, long arg1) {
        arg0.opened_dialog_id = arg1;
    }

    static void lambda$showExtraNotifications$28(ImageDecoder arg0, ImageDecoder$ImageInfo arg1, ImageDecoder$Source arg2) {
        arg0.setPostProcessor(-$$Lambda$NotificationsController$MrAv3qDOPhaY4I4LCoDwfXwgw9s.INSTANCE);
    }

    static void lambda$updateServerNotificationsSettings$31(TLObject arg0, TL_error arg1) {
    }

    private void playInChatSound() {
        if(!this.inChatSoundEnabled) {
            return;
        }

        if(MediaController.getInstance().isRecordingAudio()) {
            return;
        }

        try {
            if(NotificationsController.audioManager.getRingerMode() != 0) {
                goto label_12;
            }
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            goto label_12;
        }

        return;
        try {
        label_12:
            if(this.getNotifyOverride(MessagesController.getNotificationsSettings(this.currentAccount), this.opened_dialog_id) == 2) {
                return;
            }

            NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$alPVwcUCIFeft1jL9KfZh4w0pFA(this));
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public void playOutChatSound() {
        if(!this.inChatSoundEnabled) {
            return;
        }

        if(MediaController.getInstance().isRecordingAudio()) {
            return;
        }

        try {
            if(NotificationsController.audioManager.getRingerMode() != 0) {
                goto label_12;
            }
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            goto label_12;
        }

        return;
    label_12:
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$0zKSLzHDN_VyCU2s190jNcxAhb8(this));
    }

    public void processDialogsUpdateRead(LongSparseArray arg4) {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$bRv8AkmkiAwGyZ1dPg2TuCyHYS0(this, arg4, new ArrayList()));
    }

    public void processLoadedUnreadMessages(LongSparseArray arg3, ArrayList arg4, ArrayList arg5, ArrayList arg6, ArrayList arg7) {
        MessagesController.getInstance(this.currentAccount).putUsers(arg5, true);
        MessagesController.getInstance(this.currentAccount).putChats(arg6, true);
        MessagesController.getInstance(this.currentAccount).putEncryptedChats(arg7, true);
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$j6jSparPzyKhFlUAg0tFWGlUFAY(this, arg4, arg3));
    }

    public void processNewMessages(ArrayList arg9, boolean arg10, boolean arg11) {
        if(arg9.isEmpty()) {
            return;
        }

        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$TUw5sRt4eCcBz60DEHiiHoPusww(this, arg9, arg11, new ArrayList(0), arg10));
    }

    public void processReadMessages(SparseLongArray arg12, long arg13, int arg15, int arg16, boolean arg17) {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$bn_qy54k0GHNymLhNYsBBa6g2mw(this, arg12, new ArrayList(0), arg13, arg16, arg15, arg17));
    }

    public void removeDeletedHisoryFromNotifications(SparseIntArray arg4) {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$4ZPSiSXCXkKfxVPcPpmsFy8foEU(this, arg4, new ArrayList(0)));
    }

    public void removeDeletedMessagesFromNotifications(SparseArray arg4) {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$8lQbr5XMNBt__wC6arYqfGdfeMk(this, arg4, new ArrayList(0)));
    }

    public void removeNotificationsForDialog(long arg9) {
        NotificationsController.getInstance(this.currentAccount).processReadMessages(null, arg9, 0, 2147483647, false);
        LongSparseArray v0 = new LongSparseArray();
        v0.put(arg9, Integer.valueOf(0));
        NotificationsController.getInstance(this.currentAccount).processDialogsUpdateRead(v0);
    }

    protected void repeatNotificationMaybe() {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$-G7ZkuJVioRRv-4XRm9QaBtv10c(this));
    }

    private void scheduleNotificationDelay(boolean arg5) {
        try {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("delay notification start, onlineReason = " + arg5);
            }

            this.notificationDelayWakelock.acquire(10000);
            NotificationsController.notificationsQueue.cancelRunnable(this.notificationDelayRunnable);
            DispatchQueue v0_1 = NotificationsController.notificationsQueue;
            Runnable v1 = this.notificationDelayRunnable;
            int v5_1 = arg5 ? 3000 : 1000;
            v0_1.postRunnable(v1, ((long)v5_1));
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
            this.showOrUpdateNotification(this.notifyCheck);
        }
    }

    private void scheduleNotificationRepeat() {
        try {
            Intent v0_1 = new Intent(ApplicationLoader.applicationContext, NotificationRepeat.class);
            v0_1.putExtra("currentAccount", this.currentAccount);
            PendingIntent v0_2 = PendingIntent.getService(ApplicationLoader.applicationContext, 0, v0_1, 0);
            int v1 = MessagesController.getNotificationsSettings(this.currentAccount).getInt("repeat_messages", 60);
            if(v1 > 0 && this.personal_count > 0) {
                this.alarmManager.set(2, SystemClock.elapsedRealtime() + (((long)(v1 * 60 * 1000))), v0_2);
                return;
            }

            this.alarmManager.cancel(v0_2);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    private void setBadge(int arg2) {
        if(this.lastBadgeCount == arg2) {
            return;
        }

        this.lastBadgeCount = arg2;
        NotificationBadge.applyCount(arg2);
    }

    public void setBadgeEnabled(boolean arg2) {
        this.showBadgeNumber = arg2;
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$kARPTwpQ-6TP8_YfeNI-XUycr7s(this));
    }

    public void setInChatSoundEnabled(boolean arg1) {
        this.inChatSoundEnabled = arg1;
    }

    public void setLastOnlineFromOtherDevice(int arg3) {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$aMKmdt9uT4z6-2MONOs1umiLD6k(this, arg3));
    }

    public void setOpenedDialogId(long arg3) {
        NotificationsController.notificationsQueue.postRunnable(new -$$Lambda$NotificationsController$XWu9HxcgJh0WGxxES9w4G4Lj_cA(this, arg3));
    }

    @SuppressLint(value={"InlinedApi"}) private void showExtraNotifications(d arg66, boolean arg67, String arg68) {
        class 1NotificationHolder {
            int id;
            Notification notification;

            1NotificationHolder(NotificationsController arg1, int arg2, Notification arg3) {
                NotificationsController.this = arg1;
                super();
                this.id = arg2;
                this.notification = arg3;
            }

            void call() {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.w("show dialog notification with id " + this.id);
                }

                NotificationsController.notificationManager.a(this.id, this.notification);
            }
        }

        JSONObject v7_10;
        ArrayList v64;
        int v63;
        int v62;
        Object v2_6;
        int v3_8;
        JSONObject v1_13;
        Uri v8_4;
        File v1_10;
        int v7_6;
        String v10_3;
        long v8_3;
        LongSparseArray v2_4;
        long v59;
        String[] v56;
        a v57;
        Object v54;
        int v55;
        StringBuilder v58;
        android.support.v4.app.w$a v53;
        Intent v52;
        String v5_6;
        Object v51;
        String v50;
        ArrayList v49;
        Integer v1_6;
        android.support.v4.app.w$a v8_2;
        boolean v12_1;
        int v46;
        String v6_7;
        Bitmap v45;
        PendingIntent v1_4;
        Intent v1_3;
        boolean v44;
        int v6_6;
        Bitmap v6_4;
        Chat v37;
        File v16_2;
        boolean v1_2;
        User v36;
        String v2_3;
        User v5_4;
        JSONArray v8_1;
        int v38;
        ArrayList v1_1;
        Notification v35;
        boolean v32;
        boolean v34;
        boolean v7_3;
        Chat v5_3;
        boolean v16_1;
        Chat v6_2;
        String v3_3;
        StringBuilder v2_2;
        JSONObject v5_2;
        ArrayList v33;
        LongSparseArray v30;
        String v13_2;
        FileLocation v4_3;
        String v31;
        User v7_2;
        JSONArray v29;
        boolean v28;
        int v27;
        LongSparseArray v24;
        JSONObject v16;
        Integer v3_2;
        int v2_1;
        int v9_2;
        ArrayList v9_1;
        Object v9;
        NotificationsController v0 = this;
        Notification v1 = arg66.b();
        if(Build$VERSION.SDK_INT < 18) {
            NotificationsController.notificationManager.a(v0.notificationId, v1);
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("show summary notification by SDK check");
            }

            return;
        }

        ArrayList v2 = new ArrayList();
        LongSparseArray v3 = new LongSparseArray();
        int v4 = 0;
        int v5;
        for(v5 = 0; v5 < v0.pushMessages.size(); ++v5) {
            Object v6 = v0.pushMessages.get(v5);
            long v7 = ((MessageObject)v6).getDialogId();
            v9 = v3.get(v7);
            if(v9 == null) {
                v9_1 = new ArrayList();
                v3.put(v7, v9_1);
                v2.add(0, Long.valueOf(v7));
            }

            v9_1.add(v6);
        }

        LongSparseArray v5_1 = v0.wearNotificationsIds.clone();
        v0.wearNotificationsIds.clear();
        ArrayList v6_1 = new ArrayList();
        JSONArray v7_1 = WearDataLayerListenerService.isWatchConnected() ? new JSONArray() : null;
        int v10 = 27;
        if(Build$VERSION.SDK_INT > v10) {
            if(Build$VERSION.SDK_INT > v10 && v2.size() > 1) {
                goto label_58;
            }

            v9_2 = 0;
        }
        else {
        label_58:
            v9_2 = 1;
        }

        v10 = 26;
        if(v9_2 != 0 && Build$VERSION.SDK_INT >= v10) {
            NotificationsController.checkOtherNotificationsChannel();
        }

        int v12 = v2.size();
        int v13 = 0;
        while(v13 < v12) {
            long v14 = v2.get(v13).longValue();
            Object v10_1 = v3.get(v14);
            int v8 = ((ArrayList)v10_1).get(v4).getId();
            int v11 = ((int)v14);
            ArrayList v19 = v2;
            LongSparseArray v20 = v3;
            v2_1 = ((int)(v14 >> 32));
            Object v3_1 = v5_1.get(v14);
            if(v3_1 != null) {
                v5_1.remove(v14);
            }
            else if(v11 != 0) {
                v3_2 = Integer.valueOf(v11);
            }
            else {
                v3_2 = Integer.valueOf(v2_1);
            }

            if(v7_1 != null) {
                v16 = new JSONObject();
                v4 = 0;
            }
            else {
                v4 = 0;
                v16 = null;
            }

            Object v4_1 = ((ArrayList)v10_1).get(v4);
            int v23 = v12;
            v12 = ((MessageObject)v4_1).messageOwner.date;
            v24 = v5_1;
            v5_1 = new LongSparseArray();
            long v25 = 0;
            if(v11 != 0) {
                v27 = v13;
                boolean v13_1 = v11 != 777000 ? true : false;
                if(v11 > 0) {
                    v28 = v13_1;
                    v29 = v7_1;
                    v7_2 = MessagesController.getInstance(v0.currentAccount).getUser(Integer.valueOf(v11));
                    if(v7_2 != null) {
                        String v4_2 = UserObject.getUserName(v7_2);
                        if(v7_2.photo == null || v7_2.photo.photo_small == null) {
                            v31 = v4_2;
                            v30 = v5_1;
                        }
                        else {
                            v31 = v4_2;
                            v30 = v5_1;
                            if(v7_2.photo.photo_small.volume_id != v25 && v7_2.photo.photo_small.local_id != 0) {
                                v4_3 = v7_2.photo.photo_small;
                                v33 = v6_1;
                                v5_2 = v16;
                                v13_2 = v31;
                                goto label_168;
                            }
                        }

                        v33 = v6_1;
                        v5_2 = v16;
                        v13_2 = v31;
                    label_167:
                        v4_3 = null;
                    }
                    else if(((MessageObject)v4_1).isFcmMessage()) {
                        v13_2 = ((MessageObject)v4_1).localName;
                        v30 = v5_1;
                        v33 = v6_1;
                        v5_2 = v16;
                        goto label_167;
                    }
                    else if(BuildVars.LOGS_ENABLED) {
                        v2_2 = new StringBuilder();
                        v3_3 = "not found user to show dialog notification ";
                        goto label_131;
                    }
                    else {
                        goto label_135;
                    }

                label_168:
                    v6_2 = null;
                    v16_1 = false;
                    goto label_291;
                }
                else {
                    v30 = v5_1;
                    v29 = v7_1;
                    v28 = v13_1;
                    v5_3 = MessagesController.getInstance(v0.currentAccount).getChat(Integer.valueOf(-v11));
                    if(v5_3 != null) {
                        goto label_200;
                    }
                    else if(((MessageObject)v4_1).isFcmMessage()) {
                        v7_3 = ((MessageObject)v4_1).isMegagroup();
                        v13_2 = ((MessageObject)v4_1).localName;
                        v34 = ((MessageObject)v4_1).localChannel;
                        v33 = v6_1;
                        v32 = v7_3;
                        v4_3 = null;
                        v7_2 = null;
                        v6_2 = v5_3;
                        v5_2 = v16;
                    }
                    else {
                        if(BuildVars.LOGS_ENABLED) {
                            v2_2 = new StringBuilder();
                            v3_3 = "not found chat to show dialog notification ";
                        label_131:
                            v2_2.append(v3_3);
                            v2_2.append(v11);
                            FileLog.w(v2_2.toString());
                        }
                        else {
                        }

                        goto label_135;
                    }

                    goto label_192;
                }

            label_135:
                v35 = v1;
                v1_1 = v6_1;
                v38 = v9_2;
                v8_1 = v29;
                goto label_261;
            label_200:
                boolean v4_4 = v5_3.megagroup;
                v7_3 = !ChatObject.isChannel(v5_3) || (v5_3.megagroup) ? false : true;
                v13_2 = v5_3.title;
                v32 = v4_4;
                if(v5_3.photo == null || v5_3.photo.photo_small == null) {
                    v33 = v6_1;
                    v34 = v7_3;
                label_232:
                    v6_2 = v5_3;
                    v5_2 = v16;
                    v4_3 = null;
                }
                else {
                    v33 = v6_1;
                    v34 = v7_3;
                    if(v5_3.photo.photo_small.volume_id == v25) {
                        goto label_232;
                    }
                    else if(v5_3.photo.photo_small.local_id != 0) {
                        v4_3 = v5_3.photo.photo_small;
                        v6_2 = v5_3;
                        v5_2 = v16;
                    }
                    else {
                        goto label_232;
                    }
                }

                v7_2 = null;
            label_192:
                v16_1 = false;
            }
            else {
                v30 = v5_1;
                v33 = v6_1;
                v29 = v7_1;
                v27 = v13;
                if(v14 != NotificationsController.globalSecretChatId) {
                    EncryptedChat v4_5 = MessagesController.getInstance(v0.currentAccount).getEncryptedChat(Integer.valueOf(v2_1));
                    if(v4_5 != null) {
                        v5_4 = MessagesController.getInstance(v0.currentAccount).getUser(Integer.valueOf(v4_5.user_id));
                        if(v5_4 != null) {
                            goto label_281;
                        }
                        else if(BuildVars.LOGS_ENABLED) {
                            v2_3 = "not found secret chat user to show dialog notification " + v4_5.user_id;
                        label_256:
                            FileLog.w(v2_3);
                        }
                    }
                    else if(BuildVars.LOGS_ENABLED) {
                        v2_3 = "not found secret chat to show dialog notification " + v2_1;
                        goto label_256;
                    }

                    v35 = v1;
                    v38 = v9_2;
                    v8_1 = v29;
                    v1_1 = v33;
                label_261:
                    v4 = 0;
                    goto label_1118;
                }
                else {
                    v5_4 = null;
                }

            label_281:
                v13_2 = LocaleController.getString("SecretChatName", 2131625984);
                v7_2 = v5_4;
                v4_3 = null;
                v5_2 = null;
                v6_2 = null;
                v16_1 = false;
                v28 = false;
            label_291:
                v32 = false;
                v34 = false;
            }

            if((AndroidUtilities.needShowPasscode(v16_1)) || (SharedConfig.isWaitingForPasscodeEnter)) {
                v13_2 = LocaleController.getString("AppName", 2131624086);
                v35 = v1;
                v36 = v7_2;
                v1_2 = false;
                v4_3 = null;
            }
            else {
                v35 = v1;
                v36 = v7_2;
                v1_2 = v28;
            }

            if(v4_3 != null) {
                v16_2 = FileLoader.getPathToAttach(((TLObject)v4_3), true);
                v37 = v6_2;
                v38 = v9_2;
                String v9_3 = null;
                BitmapDrawable v6_3 = ImageLoader.getInstance().getImageFromMemory(((TLObject)v4_3), v9_3, "50_50");
                if(v6_3 != null) {
                    v6_4 = v6_3.getBitmap();
                }
                else {
                    if(Build$VERSION.SDK_INT < 28) {
                        try {
                            if(v16_2.exists()) {
                                float v6_5 = 160f / (((float)AndroidUtilities.dp(50f)));
                                BitmapFactory$Options v7_4 = new BitmapFactory$Options();
                                v6_6 = v6_5 < 1f ? 1 : ((int)v6_5);
                                v7_4.inSampleSize = v6_6;
                                v6_4 = BitmapFactory.decodeFile(v16_2.getAbsolutePath(), v7_4);
                                goto label_349;
                            }

                            goto label_342;
                        }
                        catch(Throwable ) {
                        label_342:
                            v6_4 = ((Bitmap)v9_3);
                            goto label_349;
                        }
                    }

                    goto label_342;
                }
            }
            else {
                v37 = v6_2;
                v38 = v9_2;
                v6_4 = null;
                v16_2 = ((File)v6_4);
            }

        label_349:
            Object v39 = v10_1;
            long v40 = 1000;
            a v7_5 = new a(v13_2).a((((long)v12)) * v40);
            FileLocation v42 = v4_3;
            Intent v9_4 = new Intent(ApplicationLoader.applicationContext, AutoMessageHeardReceiver.class);
            v9_4.addFlags(32);
            v9_4.setAction("org.telegram.messenger.ACTION_MESSAGE_HEARD");
            v9_4.putExtra("dialog_id", v14);
            v9_4.putExtra("max_id", v8);
            v9_4.putExtra("currentAccount", v0.currentAccount);
            int v43 = v12;
            v7_5.a(PendingIntent.getBroadcast(ApplicationLoader.applicationContext, ((Integer)v3_1).intValue(), v9_4, 134217728));
            if((v34) && !v32 || (!v1_2 || (SharedConfig.isWaitingForPasscodeEnter))) {
                v44 = v1_2;
                v45 = v6_4;
                v46 = v8;
                v8_2 = null;
            }
            else {
                Intent v4_6 = new Intent(ApplicationLoader.applicationContext, AutoMessageReplyReceiver.class);
                v4_6.addFlags(32);
                v4_6.setAction("org.telegram.messenger.ACTION_MESSAGE_REPLY");
                v4_6.putExtra("dialog_id", v14);
                v4_6.putExtra("max_id", v8);
                v4_6.putExtra("currentAccount", v0.currentAccount);
                v44 = v1_2;
                v7_5.a(PendingIntent.getBroadcast(ApplicationLoader.applicationContext, ((Integer)v3_1).intValue(), v4_6, 134217728), new android.support.v4.app.ac$a("extra_voice_reply").a(LocaleController.getString("Reply", 2131625860)).a());
                v1_3 = new Intent(ApplicationLoader.applicationContext, WearReplyReceiver.class);
                v1_3.putExtra("dialog_id", v14);
                v1_3.putExtra("max_id", v8);
                v1_3.putExtra("currentAccount", v0.currentAccount);
                v1_4 = PendingIntent.getBroadcast(ApplicationLoader.applicationContext, ((Integer)v3_1).intValue(), v1_3, 134217728);
                ac v4_7 = new android.support.v4.app.ac$a("extra_voice_reply").a(LocaleController.getString("Reply", 2131625860)).a();
                if(v11 < 0) {
                    v45 = v6_4;
                    v6_7 = LocaleController.formatString("ReplyToGroup", 2131625861, new Object[]{v13_2});
                    v46 = v8;
                    v12_1 = true;
                }
                else {
                    v45 = v6_4;
                    v46 = v8;
                    v12_1 = true;
                    v6_7 = LocaleController.formatString("ReplyToUser", 2131625862, new Object[]{v13_2});
                }

                v8_2 = new android.support.v4.app.w$a$a(2131231231, ((CharSequence)v6_7), v1_4).a(v12_1).a(v4_7).a();
            }

            Object v1_5 = v0.pushDialogs.get(v14);
            if(v1_5 == null) {
                v4 = 0;
                v1_6 = Integer.valueOf(0);
            }
            else {
                v4 = 0;
            }

            Object[] v12_2 = new Object[2];
            v12_2[v4] = v13_2;
            v12_2[1] = LocaleController.formatPluralString("NewMessages", Math.max(v1_6.intValue(), ((ArrayList)v39).size()));
            String v1_7 = String.format("%1$s (%2$s)", v12_2);
            h v6_8 = new h("");
            v6_8.a(((CharSequence)v1_7));
            if(!v34 && v11 < 0) {
                v6_8.a(true);
            }

            StringBuilder v1_8 = new StringBuilder();
            String[] v10_2 = new String[1];
            JSONArray v12_3 = v5_2 != null ? new JSONArray() : null;
            JSONObject v47 = v5_2;
            v4 = ((ArrayList)v39).size() - 1;
            ArrayList v5_5 = null;
            int v48 = 0;
            while(v4 >= 0) {
                v49 = v5_5;
                v50 = v13_2;
                Object v13_3 = v39.get(v4);
                v51 = v39;
                v5_6 = v0.getShortStringForMessage(((MessageObject)v13_3), v10_2);
                if(v5_6 == null) {
                    if(BuildVars.LOGS_ENABLED) {
                        StringBuilder v5_7 = new StringBuilder();
                        v52 = v9_4;
                        v5_7.append("message text is null for ");
                        v5_7.append(((MessageObject)v13_3).getId());
                        v5_7.append(" did = ");
                        v53 = v8_2;
                        v5_7.append(((MessageObject)v13_3).getDialogId());
                        FileLog.w(v5_7.toString());
                    }
                    else {
                        v53 = v8_2;
                        v52 = v9_4;
                    }

                    v58 = v1_8;
                    v55 = v2_1;
                    v54 = v3_1;
                    v57 = v7_5;
                    v56 = v10_2;
                    v59 = v14;
                    v2_4 = v30;
                    goto label_732;
                }

                v53 = v8_2;
                v52 = v9_4;
                if(v1_8.length() > 0) {
                    v1_8.append("\n\n");
                }

                if(v10_2[0] != null) {
                    v55 = v2_1;
                    v54 = v3_1;
                    v1_8.append(String.format("%1$s: %2$s", v10_2[0], v5_6));
                }
                else {
                    v55 = v2_1;
                    v54 = v3_1;
                    v1_8.append(v5_6);
                }

                v7_5.a(v5_6);
                if(v11 > 0) {
                    v8_3 = ((long)v11);
                }
                else {
                    if(v34) {
                        v2_1 = -v11;
                    }
                    else if(v11 < 0) {
                        v2_1 = ((MessageObject)v13_3).getFromId();
                    }
                    else {
                        goto label_582;
                    }

                    v8_3 = ((long)v2_1);
                    goto label_573;
                label_582:
                    v8_3 = v14;
                }

            label_573:
                v2_4 = v30;
                Object v17 = v2_4.get(v8_3);
                if(v17 == null) {
                    android.support.v4.app.ab$a v3_5 = new android.support.v4.app.ab$a();
                    if(v10_2[0] == null) {
                        v56 = v10_2;
                        v10_3 = "";
                    }
                    else {
                        v56 = v10_2;
                        v10_3 = v10_2[0];
                    }

                    v3_5 = v3_5.a(((CharSequence)v10_3));
                    v57 = v7_5;
                    if(Build$VERSION.SDK_INT >= 28) {
                        if(v11 > 0 || (v34)) {
                            v58 = v1_8;
                            v59 = v14;
                            v1_10 = v16_2;
                        }
                        else {
                            if(v11 < 0) {
                                v7_6 = ((MessageObject)v13_3).getFromId();
                                v58 = v1_8;
                                User v1_9 = MessagesController.getInstance(v0.currentAccount).getUser(Integer.valueOf(v7_6));
                                if(v1_9 == null) {
                                    v1_9 = MessagesStorage.getInstance(v0.currentAccount).getUserSync(v7_6);
                                    if(v1_9 != null) {
                                        MessagesController.getInstance(v0.currentAccount).putUser(v1_9, true);
                                    }
                                }

                                if(v1_9 == null) {
                                    goto label_643;
                                }

                                if(v1_9.photo == null) {
                                    goto label_643;
                                }

                                if(v1_9.photo.photo_small == null) {
                                    goto label_643;
                                }

                                v59 = v14;
                                if(v1_9.photo.photo_small.volume_id == v25) {
                                    goto label_644;
                                }

                                if(v1_9.photo.photo_small.local_id == 0) {
                                    goto label_644;
                                }

                                v1_10 = FileLoader.getPathToAttach(v1_9.photo.photo_small, true);
                                goto label_649;
                            }
                            else {
                                v58 = v1_8;
                            label_643:
                                v59 = v14;
                            }

                        label_644:
                            v1_10 = null;
                        }

                    label_649:
                        if(v1_10 == null) {
                            goto label_658;
                        }

                        try {
                            v3_5.a(IconCompat.a(ImageDecoder.decodeBitmap(ImageDecoder.createSource(v1_10), -$$Lambda$NotificationsController$VYpdFl2BJc_pz6kIUMH1MI0rgUs.INSTANCE)));
                        }
                        catch(Throwable ) {
                        }
                    }
                    else {
                        v58 = v1_8;
                        v59 = v14;
                    }

                label_658:
                    ab v1_11 = v3_5.a();
                    v2_4.put(v8_3, v1_11);
                }
                else {
                    v58 = v1_8;
                    v57 = v7_5;
                    v56 = v10_2;
                    v59 = v14;
                    v1_5 = v17;
                }

                v6_8.a(((CharSequence)v5_6), (((long)((MessageObject)v13_3).messageOwner.date)) * v40, ((ab)v1_5));
                if(((MessageObject)v13_3).isVoice()) {
                    List v1_12 = v6_8.a();
                    if(v1_12.isEmpty()) {
                        goto label_695;
                    }

                    File v3_6 = FileLoader.getPathToMessage(((MessageObject)v13_3).messageOwner);
                    if(Build$VERSION.SDK_INT >= 24) {
                        try {
                            v8_4 = FileProvider.a(ApplicationLoader.applicationContext, "org.ir.talaeii.provider", v3_6);
                        }
                        catch(Exception ) {
                            v8_4 = null;
                        }
                    }
                    else {
                        v8_4 = Uri.fromFile(v3_6);
                    }

                    if(v8_4 != null) {
                        v1_12.get(v1_12.size() - 1).a("audio/ogg", v8_4);
                    }
                }

            label_695:
                if(v12_3 != null) {
                    try {
                        v1_13 = new JSONObject();
                        v1_13.put("text", v5_6);
                        v1_13.put("date", ((MessageObject)v13_3).messageOwner.date);
                        if((((MessageObject)v13_3).isFromUser()) && v11 < 0) {
                            User v3_7 = MessagesController.getInstance(v0.currentAccount).getUser(Integer.valueOf(((MessageObject)v13_3).getFromId()));
                            if(v3_7 != null) {
                                v1_13.put("fname", v3_7.first_name);
                                v1_13.put("lname", v3_7.last_name);
                            }
                        }

                        v12_3.put(v1_13);
                        goto label_720;
                    }
                    catch(JSONException ) {
                    label_720:
                        if(v59 != 777000) {
                            goto label_732;
                        }

                        if(((MessageObject)v13_3).messageOwner.reply_markup == null) {
                            goto label_732;
                        }

                        v5_5 = ((MessageObject)v13_3).messageOwner.reply_markup.rows;
                        v48 = ((MessageObject)v13_3).getId();
                        goto label_733;
                    }
                }

                goto label_720;
            label_732:
                v5_5 = v49;
            label_733:
                --v4;
                v30 = v2_4;
                v13_2 = v50;
                v39 = v51;
                v9_4 = v52;
                v8_2 = v53;
                v3_1 = v54;
                v2_1 = v55;
                v10_2 = v56;
                v7_5 = v57;
                v1_8 = v58;
                v14 = v59;
            }

            v58 = v1_8;
            v55 = v2_1;
            v54 = v3_1;
            v49 = v5_5;
            v57 = v7_5;
            v53 = v8_2;
            v52 = v9_4;
            v50 = v13_2;
            v59 = v14;
            v51 = v39;
            v1_3 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
            v1_3.setAction("com.tmessages.openchat" + Math.random() + 2147483647);
            v1_3.setFlags(32768);
            if(v11 != 0) {
                if(v11 > 0) {
                    v1_3.putExtra("userId", v11);
                }
                else {
                    v1_3.putExtra("chatId", -v11);
                }

                v3_8 = v55;
            }
            else {
                v3_8 = v55;
                v1_3.putExtra("encId", v3_8);
            }

            v1_3.putExtra("currentAccount", v0.currentAccount);
            v1_4 = PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, v1_3, 1073741824);
            j v2_5 = new j();
            if(v53 != null) {
                v8_2 = v53;
                v2_5.a(v8_2);
            }
            else {
                v8_2 = v53;
            }

            android.support.v4.app.w$a v4_8 = new android.support.v4.app.w$a$a(2131231356, LocaleController.getString("MarkAsRead", 2131625142), PendingIntent.getBroadcast(ApplicationLoader.applicationContext, ((Integer)v54).intValue(), v52, 134217728)).a();
            if(v11 != 0) {
                if(v11 > 0) {
                    v3_4 = new StringBuilder();
                    v3_4.append("tguser");
                    v3_4.append(v11);
                    v3_4.append("_");
                    v5 = v46;
                }
                else {
                    v5 = v46;
                    v3_4 = new StringBuilder();
                    v3_4.append("tgchat");
                    v3_4.append(-v11);
                    v3_4.append("_");
                }

                v3_4.append(v5);
                v3_3 = v3_4.toString();
            }
            else {
                v5 = v46;
                if(v59 != NotificationsController.globalSecretChatId) {
                    v3_3 = "tgenc" + v3_8 + "_" + v5;
                    goto label_848;
                }

                v3_3 = null;
            }

        label_848:
            if(v3_3 != null) {
                v2_5.a(v3_3);
                j v7_8 = new j();
                v7_8.a("summary_" + v3_3);
                arg66.a(((f)v7_8));
            }

            v2_5.b("tgaccount" + UserConfig.getInstance(v0.currentAccount).getClientUserId());
            long v9_6 = (((long)v51.get(0).messageOwner.date)) * v40;
            d v7_9 = new d(ApplicationLoader.applicationContext).a(v50).a(2131231416).b(v58.toString()).b(true).b(v51.size()).e(-13851168).d(false).a(v9_6).a(true);
            StringBuilder v13_4 = new StringBuilder();
            v13_4.append("sdid_");
            String v61 = v50;
            v14 = v59;
            v13_4.append(v14);
            d v1_14 = v7_9.f(v13_4.toString()).a(((i)v6_8)).a(v1_4).a(((f)v2_5));
            v2_2 = new StringBuilder();
            v2_2.append("");
            v2_2.append(9223372036854775807L - v9_6);
            v1_14 = v1_14.d(v2_2.toString()).a(new e().a(v57.a())).a("msg");
            if(v38 != 0) {
                v1_14.c(v0.notificationGroup);
                v2_1 = 1;
                v1_14.f(1);
            }
            else {
                v2_1 = 1;
            }

            if(v8_2 != null) {
                v1_14.a(v8_2);
            }

            v1_14.a(v4_8);
            if(v0.pushDialogs.size() == v2_1 && !TextUtils.isEmpty(((CharSequence)arg68))) {
                v1_14.c(arg68);
            }

            if(v11 == 0) {
                v1_14.c(((boolean)v2_1));
            }

            if(v45 != null && Build$VERSION.SDK_INT < 28) {
                v1_14.a(v45);
            }

            if(!AndroidUtilities.needShowPasscode(false) && !SharedConfig.isWaitingForPasscodeEnter && v49 != null) {
                v6_6 = v49.size();
                v7_6 = 0;
                while(v7_6 < v6_6) {
                    ArrayList v8_5 = v49;
                    v9 = v8_5.get(v7_6);
                    v10 = ((TL_keyboardButtonRow)v9).buttons.size();
                    v13 = 0;
                    while(v13 < v10) {
                        v2_6 = ((TL_keyboardButtonRow)v9).buttons.get(v13);
                        if((v2_6 instanceof TL_keyboardButtonCallback)) {
                            v62 = v6_6;
                            Intent v3_9 = new Intent(ApplicationLoader.applicationContext, NotificationCallbackReceiver.class);
                            v3_9.putExtra("currentAccount", v0.currentAccount);
                            v3_9.putExtra("did", v14);
                            if(((KeyboardButton)v2_6).data != null) {
                                v3_9.putExtra("data", ((KeyboardButton)v2_6).data);
                            }

                            v3_9.putExtra("mid", v48);
                            v2_3 = ((KeyboardButton)v2_6).text;
                            Context v4_9 = ApplicationLoader.applicationContext;
                            v63 = v48;
                            v6_6 = v0.lastButtonId;
                            v64 = v8_5;
                            v0.lastButtonId = v6_6 + 1;
                            v1_14.a(0, ((CharSequence)v2_3), PendingIntent.getBroadcast(v4_9, v6_6, v3_9, 134217728));
                        }
                        else {
                            v62 = v6_6;
                            v64 = v8_5;
                            v63 = v48;
                        }

                        ++v13;
                        v6_6 = v62;
                        v48 = v63;
                        v8_5 = v64;
                    }

                    ++v7_6;
                    v49 = v8_5;
                }
            }

            v4 = 0;
            if(v37 == null && v36 != null) {
                v7_2 = v36;
                if(v7_2.phone != null && v7_2.phone.length() > 0) {
                    v1_14.b("tel:+" + v7_2.phone);
                }
            }

            if(Build$VERSION.SDK_INT >= 26) {
                v2_3 = v38 != 0 ? NotificationsController.OTHER_NOTIFICATIONS_CHANNEL : v35.getChannelId();
                v1_14.e(v2_3);
            }

            1NotificationHolder v2_7 = new 1NotificationHolder(v0, ((Integer)v54).intValue(), v1_14.b());
            v1_1 = v33;
            v1_1.add(v2_7);
            v0.wearNotificationsIds.put(v14, v54);
            if(v11 != 0 && v47 != null) {
                try {
                    v7_10 = v47;
                    v7_10.put("reply", v44);
                    v7_10.put("name", v61);
                    v7_10.put("max_id", v5);
                    v7_10.put("max_date", v43);
                    v7_10.put("id", Math.abs(v11));
                    if(v42 != null) {
                        v7_10.put("photo", v42.dc_id + "_" + v42.volume_id + "_" + v42.secret);
                    }

                    if(v12_3 != null) {
                        v7_10.put("msgs", v12_3);
                    }

                    if(v11 > 0) {
                        v2_3 = "type";
                        v5_6 = "user";
                        goto label_1102;
                    }
                    else if(v11 < 0) {
                        if(!v34) {
                            if(v32) {
                            }
                            else {
                                v2_3 = "type";
                                v5_6 = "group";
                                goto label_1102;
                            }
                        }

                        v2_3 = "type";
                        v5_6 = "channel";
                    label_1102:
                        v7_10.put(v2_3, v5_6);
                    }
                }
                catch(JSONException ) {
                    goto label_1117;
                }

                v8_1 = v29;
                try {
                    v8_1.put(v7_10);
                }
                catch(JSONException ) {
                }
            }
            else {
            label_1117:
                v8_1 = v29;
            }

        label_1118:
            v13 = v27 + 1;
            v6_1 = v1_1;
            v7_1 = v8_1;
            v2 = v19;
            v3 = v20;
            v12 = v23;
            v5_1 = v24;
            v1 = v35;
            v9_2 = v38;
        }

        v35 = v1;
        v24 = v5_1;
        v1_1 = v6_1;
        v8_1 = v7_1;
        if(v9_2 != 0) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("show summary with id " + v0.notificationId);
            }

            NotificationsController.notificationManager.a(v0.notificationId, v35);
        }
        else {
            NotificationsController.notificationManager.a(v0.notificationId);
        }

        v2_1 = v1_1.size();
        for(v3_8 = 0; v3_8 < v2_1; ++v3_8) {
            v1_1.get(v3_8).call();
        }

        while(v4 < v24.size()) {
            LongSparseArray v1_15 = v24;
            v2_6 = v1_15.valueAt(v4);
            if(BuildVars.LOGS_ENABLED) {
                FileLog.w("cancel notification id " + v2_6);
            }

            NotificationsController.notificationManager.a(((Integer)v2_6).intValue());
            ++v4;
            v24 = v1_15;
        }

        if(v8_1 != null) {
            try {
                v1_13 = new JSONObject();
                v1_13.put("id", UserConfig.getInstance(v0.currentAccount).getClientUserId());
                v1_13.put("n", v8_1);
                WearDataLayerListenerService.sendMessageToWatch("/notify", v1_13.toString().getBytes("UTF-8"), "remote_notifications");
                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }

    private void showOrUpdateNotification(boolean arg57) {
        Object v55;
        long v8_4;
        Uri v54;
        int v53;
        int v52;
        Object v51;
        long[] v4_5;
        Uri v9_3;
        long[] v2_9;
        boolean v2_8;
        FileLocation v6_6;
        Intent v2_4;
        int v5_2;
        int v4_2;
        int v46;
        FileLocation v48;
        String v47;
        String v5_1;
        boolean v1_8;
        String v4_1;
        boolean[] v6_5;
        d v14_2;
        long v43;
        String v42;
        String v13_3;
        String v2_3;
        String v8_1;
        Object v7_3;
        PendingIntent v1_6;
        User v6_4;
        FileLocation v11_4;
        int v41;
        int v40;
        String v39;
        long[] v38;
        Chat v9_2;
        int v37;
        Intent v1_5;
        long[] v10_2;
        Uri v23;
        Uri v1_4;
        long[] v7_2;
        int v13_2;
        int v11_3;
        int v2_1;
        String v36;
        String v11_1;
        int v22;
        int v32;
        int v10_1;
        Chat v29;
        User v28;
        String v1_2;
        int v1_1;
        Object v25;
        String v3_2;
        int v21;
        Object v3_1;
        StringBuilder v15_3;
        int v6_2;
        int v7;
        long v19;
        int v14;
        boolean v15_2;
        String v15_1;
        int v17;
        int v15;
        Message v9_1;
        int v9;
        int v8;
        long v4;
        int v3;
        Object v1;
        NotificationsController v12 = this;
        boolean v13 = arg57;
        if(UserConfig.getInstance(v12.currentAccount).isClientActivated()) {
            if(v12.pushMessages.isEmpty()) {
            }
            else {
                try {
                    ConnectionsManager.getInstance(v12.currentAccount).resumeNetworkMaybe();
                    v1 = v12.pushMessages.get(0);
                    SharedPreferences v2 = MessagesController.getNotificationsSettings(v12.currentAccount);
                    v3 = v2.getInt("dismissDate", 0);
                    if(((MessageObject)v1).messageOwner.date <= v3) {
                        this.dismissNotification();
                        return;
                    }
                    else {
                        v4 = ((MessageObject)v1).getDialogId();
                        long v6 = ((MessageObject)v1).messageOwner.mentioned ? ((long)((MessageObject)v1).messageOwner.from_id) : v4;
                        ((MessageObject)v1).getId();
                        v8 = ((MessageObject)v1).messageOwner.to_id.chat_id != 0 ? ((MessageObject)v1).messageOwner.to_id.chat_id : ((MessageObject)v1).messageOwner.to_id.channel_id;
                        v9 = ((MessageObject)v1).messageOwner.to_id.user_id;
                        if(v9 == 0) {
                            v9_1 = ((MessageObject)v1).messageOwner;
                            goto label_51;
                        }
                        else if(v9 == UserConfig.getInstance(v12.currentAccount).getClientUserId()) {
                            v9_1 = ((MessageObject)v1).messageOwner;
                        label_51:
                            v9 = v9_1.from_id;
                        }

                        User v10 = MessagesController.getInstance(v12.currentAccount).getUser(Integer.valueOf(v9));
                        Chat v11 = v8 != 0 ? MessagesController.getInstance(v12.currentAccount).getChat(Integer.valueOf(v8)) : null;
                        v15 = v12.getNotifyOverride(v2, v6);
                        v17 = v3;
                        if(v15 == -1) {
                            v15_1 = (((int)v4)) < 0 ? "EnableGroup" : "EnableAll";
                            v15_2 = v2.getBoolean(v15_1, true);
                        }
                        else {
                            if(v15 != 2) {
                                v15_2 = true;
                                goto label_87;
                            }

                            v15_2 = false;
                        }

                    label_87:
                        v14 = !v13 || !v15_2 ? 1 : 0;
                        v19 = 1000;
                        if(v14 != 0 || v4 != v6 || v11 == null) {
                        label_161:
                            v21 = v14;
                        }
                        else {
                            StringBuilder v6_1 = new StringBuilder();
                            v6_1.append("custom_");
                            v6_1.append(v4);
                            v7 = 180;
                            if(v2.getBoolean(v6_1.toString(), false)) {
                                v6_1 = new StringBuilder();
                                v6_1.append("smart_max_count_");
                                v6_1.append(v4);
                                v6_2 = v2.getInt(v6_1.toString(), 2);
                                v15_3 = new StringBuilder();
                                v15_3.append("smart_delay_");
                                v15_3.append(v4);
                                v7 = v2.getInt(v15_3.toString(), v7);
                            }
                            else {
                                v6_2 = 2;
                            }

                            if(v6_2 == 0) {
                                goto label_161;
                            }

                            v3_1 = v12.smartNotificationsDialogs.get(v4);
                            if(v3_1 == null) {
                                v12.smartNotificationsDialogs.put(v4, new Point(1, ((int)(System.currentTimeMillis() / v19))));
                                goto label_161;
                            }

                            v21 = v14;
                            if((((long)(((Point)v3_1).y + v7))) < System.currentTimeMillis() / v19) {
                                ((Point)v3_1).set(1, ((int)(System.currentTimeMillis() / v19)));
                                goto label_162;
                            }

                            v7 = ((Point)v3_1).x;
                            if(v7 < v6_2) {
                                ((Point)v3_1).set(v7 + 1, ((int)(System.currentTimeMillis() / v19)));
                                goto label_162;
                            }

                            v21 = 1;
                        }

                    label_162:
                        v3_2 = Settings$System.DEFAULT_NOTIFICATION_URI.getPath();
                        boolean v6_3 = v2.getBoolean("EnableInAppSounds", true);
                        boolean v14_1 = v2.getBoolean("EnableInAppVibrate", true);
                        boolean v24 = v2.getBoolean("EnableInAppPreview", true);
                        boolean v7_1 = v2.getBoolean("EnableInAppPriority", false);
                        v15_3 = new StringBuilder();
                        v15_3.append("custom_");
                        v15_3.append(v4);
                        v13 = v2.getBoolean(v15_3.toString(), false);
                        if(v13) {
                            v15_3 = new StringBuilder();
                            v25 = v1;
                            v15_3.append("vibrate_");
                            v15_3.append(v4);
                            v1_1 = v2.getInt(v15_3.toString(), 0);
                            v15_3 = new StringBuilder();
                            int v26 = v1_1;
                            v15_3.append("priority_");
                            v15_3.append(v4);
                            v1_1 = v2.getInt(v15_3.toString(), 3);
                            v15_3 = new StringBuilder();
                            int v27 = v1_1;
                            v15_3.append("sound_path_");
                            v15_3.append(v4);
                            v1_2 = v2.getString(v15_3.toString(), null);
                            v28 = v10;
                            v29 = v11;
                            v15 = v26;
                            v10_1 = v27;
                        }
                        else {
                            v25 = v1;
                            v28 = v10;
                            v29 = v11;
                            v1_2 = null;
                            v10_1 = 3;
                            v15 = 0;
                        }

                        if(v8 != 0) {
                            if(v1_2 != null && (v1_2.equals(v3_2))) {
                                v1_2 = null;
                            }
                            else if(v1_2 == null) {
                                v1_2 = v2.getString("GroupSoundPath", v3_2);
                            }

                            int v31 = v2.getInt("vibrate_group", 0);
                            v32 = v2.getInt("priority_group", 1);
                            v22 = v2.getInt("GroupLed", -16776961);
                            v11_1 = v1_2;
                            v1_1 = v31;
                        }
                        else {
                            if(v9 != 0) {
                                if(v1_2 != null && (v1_2.equals(v3_2))) {
                                    v1_2 = null;
                                }
                                else if(v1_2 == null) {
                                    v1_2 = v2.getString("GlobalSoundPath", v3_2);
                                }

                                int v34 = v2.getInt("vibrate_messages", 0);
                                int v35 = v2.getInt("priority_messages", 1);
                                v22 = v2.getInt("MessagesLed", -16776961);
                                v11_1 = v1_2;
                                v1_1 = v34;
                                v32 = v35;
                                goto label_279;
                            }

                            v11_1 = v1_2;
                            v1_1 = 0;
                            v22 = -16776961;
                            v32 = 0;
                        }

                    label_279:
                        if(v13) {
                            StringBuilder v13_1 = new StringBuilder();
                            v36 = v11_1;
                            v13_1.append("color_");
                            v13_1.append(v4);
                            if(v2.contains(v13_1.toString())) {
                                StringBuilder v11_2 = new StringBuilder();
                                v11_2.append("color_");
                                v11_2.append(v4);
                                v22 = v2.getInt(v11_2.toString(), 0);
                            }
                        }
                        else {
                            v36 = v11_1;
                        }

                        v2_1 = 3;
                        if(v10_1 != v2_1) {
                        }
                        else {
                            v10_1 = v32;
                        }

                        if(v1_1 == 4) {
                            v1_1 = 0;
                            v11_3 = 2;
                            v13_2 = 1;
                        }
                        else {
                            v11_3 = 2;
                            v13_2 = 0;
                        }

                        if(v1_1 != v11_3 || v15 != 1 && v15 != v2_1) {
                            v2_1 = 2;
                            if(v1_1 != v2_1 && v15 == v2_1) {
                                goto label_323;
                            }

                            if(v15 != 0 && v15 != 4) {
                                goto label_323;
                            }

                            v15 = v1_1;
                        }

                    label_323:
                        if(!ApplicationLoader.mainInterfacePaused) {
                            goto label_325;
                        }
                        else {
                            goto label_339;
                        }
                    }

                    goto label_1227;
                }
                catch(Exception v0) {
                    goto label_1225;
                }

            label_325:
                if(!v6_3) {
                    v36 = null;
                }

                if(!v14_1) {
                    v15 = 2;
                }

                if(!v7_1) {
                    v11_1 = v36;
                    v1_1 = 2;
                    v14 = 0;
                    goto label_342;
                }

                v1_1 = 2;
                if(v10_1 != v1_1) {
                }
                else {
                    v11_1 = v36;
                    v14 = 1;
                    goto label_342;
                label_339:
                    v1_1 = 2;
                }

                v14 = v10_1;
                v11_1 = v36;
            label_342:
                if(v13_2 == 0) {
                    goto label_354;
                }

                if(v15 == v1_1) {
                    goto label_354;
                }

                try {
                    v1_1 = NotificationsController.audioManager.getRingerMode();
                    if(v1_1 == 0) {
                        goto label_354;
                    }
                }
                catch(Exception v0) {
                    Exception v1_3 = v0;
                    try {
                        FileLog.e(((Throwable)v1_3));
                        goto label_354;
                    }
                    catch(Exception v0) {
                        goto label_1225;
                    }
                }

                if(v1_1 == 1) {
                    goto label_354;
                }

                v15 = 2;
                try {
                label_354:
                    v2_1 = 5;
                    if(Build$VERSION.SDK_INT >= 26) {
                        v1_1 = 2;
                        if(v15 == v1_1) {
                            v7_2 = new long[]{0, 0};
                        }
                        else if(v15 == 1) {
                            v7_2 = new long[]{0, 100, 0, 100};
                        }
                        else {
                            v1_1 = 4;
                            if(v15 != 0) {
                                if(v15 == v1_1) {
                                }
                                else {
                                    v7_2 = v15 == 3 ? new long[]{0, 1000} : null;
                                    goto label_380;
                                }
                            }

                            v7_2 = new long[0];
                        }

                    label_380:
                        if(v11_1 == null || (v11_1.equals("NoSound"))) {
                            v1_4 = null;
                        }
                        else if(v11_1.equals(v3_2)) {
                            v1_4 = Settings$System.DEFAULT_NOTIFICATION_URI;
                        }
                        else {
                            v1_4 = Uri.parse(v11_1);
                        }

                        if(v14 == 0) {
                            v23 = v1_4;
                            v10_2 = v7_2;
                            v13_2 = 3;
                            goto label_423;
                        }

                        if(v14 != 1) {
                            if(v14 == 2) {
                            }
                            else {
                                if(v14 == 4) {
                                    v23 = v1_4;
                                    v10_2 = v7_2;
                                    v13_2 = 1;
                                }
                                else if(v14 == v2_1) {
                                    v23 = v1_4;
                                    v10_2 = v7_2;
                                    v13_2 = 2;
                                }
                                else {
                                    v23 = v1_4;
                                    v10_2 = v7_2;
                                    v13_2 = 0;
                                }

                                goto label_423;
                            }
                        }

                        v23 = v1_4;
                        v10_2 = v7_2;
                        v13_2 = 4;
                    }
                    else {
                        v10_2 = null;
                        v13_2 = 0;
                        v23 = null;
                    }

                label_423:
                    if(v21 != 0) {
                        v7 = 0;
                        v11_1 = null;
                        v14 = 0;
                        v15 = 0;
                    }
                    else {
                        v7 = v22;
                    }

                    v1_5 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
                    StringBuilder v2_2 = new StringBuilder();
                    v2_2.append("com.tmessages.openchat");
                    v37 = v7;
                    v2_2.append(Math.random());
                    v2_2.append(2147483647);
                    v1_5.setAction(v2_2.toString());
                    v1_5.setFlags(32768);
                    v2_1 = ((int)v4);
                    if(v2_1 != 0) {
                        if(v12.pushDialogs.size() == 1) {
                            if(v8 != 0) {
                                v1_5.putExtra("chatId", v8);
                            }
                            else if(v9 != 0) {
                                v1_5.putExtra("userId", v9);
                            }
                        }

                        if(!AndroidUtilities.needShowPasscode(false)) {
                            if(SharedConfig.isWaitingForPasscodeEnter) {
                            }
                            else {
                                if(v12.pushDialogs.size() == 1 && Build$VERSION.SDK_INT < 28) {
                                    if(v29 != null) {
                                        v9_2 = v29;
                                        if(v9_2.photo != null && v9_2.photo.photo_small != null && v9_2.photo.photo_small.volume_id != 0 && v9_2.photo.photo_small.local_id != 0) {
                                            v38 = v10_2;
                                            v39 = v11_1;
                                            v40 = v13_2;
                                            v41 = v14;
                                            v11_4 = v9_2.photo.photo_small;
                                            v6_4 = v28;
                                            goto label_565;
                                        }

                                        v38 = v10_2;
                                        v39 = v11_1;
                                        v40 = v13_2;
                                        v41 = v14;
                                        v6_4 = v28;
                                        goto label_564;
                                    }
                                    else {
                                        v9_2 = v29;
                                        if(v28 != null) {
                                            v6_4 = v28;
                                            if(v6_4.photo != null && v6_4.photo.photo_small != null) {
                                                v38 = v10_2;
                                                v39 = v11_1;
                                                if(v6_4.photo.photo_small.volume_id == 0) {
                                                    goto label_544;
                                                }
                                                else if(v6_4.photo.photo_small.local_id != 0) {
                                                    v11_4 = v6_4.photo.photo_small;
                                                    v40 = v13_2;
                                                    v41 = v14;
                                                    goto label_565;
                                                }
                                                else {
                                                    goto label_544;
                                                }
                                            }

                                            v38 = v10_2;
                                            v39 = v11_1;
                                            goto label_544;
                                        }

                                        v38 = v10_2;
                                        v39 = v11_1;
                                        v6_4 = v28;
                                        goto label_544;
                                    }
                                }

                                v38 = v10_2;
                                v39 = v11_1;
                                v6_4 = v28;
                                v9_2 = v29;
                                goto label_544;
                            }
                        }

                        v38 = v10_2;
                        v39 = v11_1;
                        v6_4 = v28;
                        v9_2 = v29;
                        goto label_544;
                    }
                    else {
                        v38 = v10_2;
                        v39 = v11_1;
                        v6_4 = v28;
                        v9_2 = v29;
                        if(v12.pushDialogs.size() == 1) {
                            if(v4 == NotificationsController.globalSecretChatId) {
                                goto label_544;
                            }

                            v40 = v13_2;
                            v41 = v14;
                            v1_5.putExtra("encId", ((int)(v4 >> 32)));
                        }
                        else {
                        label_544:
                            v40 = v13_2;
                            v41 = v14;
                        }

                    label_564:
                        v11_4 = null;
                    }

                label_565:
                    v1_5.putExtra("currentAccount", v12.currentAccount);
                    v1_6 = PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, v1_5, 1073741824);
                    if((v8 == 0 || v9_2 != null) && v6_4 != null || !((MessageObject)v25).isFcmMessage()) {
                        v7_3 = v25;
                        v8_1 = v9_2 != null ? v9_2.title : UserObject.getUserName(v6_4);
                    }
                    else {
                        v7_3 = v25;
                        v8_1 = ((MessageObject)v7_3).localName;
                    }

                    if(v2_1 == 0 || v12.pushDialogs.size() > 1 || (AndroidUtilities.needShowPasscode(false)) || (SharedConfig.isWaitingForPasscodeEnter)) {
                        v2_3 = LocaleController.getString("AppName", 2131624086);
                        v10_1 = 0;
                    }
                    else {
                        v2_3 = v8_1;
                        v10_1 = 1;
                    }

                    if(UserConfig.getActivatedAccountsCount() <= 1) {
                        v13_3 = "";
                    }
                    else if(v12.pushDialogs.size() == 1) {
                        v13_3 = UserObject.getFirstName(UserConfig.getInstance(v12.currentAccount).getCurrentUser());
                    }
                    else {
                        v13_3 = UserObject.getFirstName(UserConfig.getInstance(v12.currentAccount).getCurrentUser()) + "・";
                    }

                    v42 = v8_1;
                    if(v12.pushDialogs.size() == 1 && Build$VERSION.SDK_INT >= 23) {
                        goto label_635;
                    }
                    else if(v12.pushDialogs.size() == 1) {
                        v13_3 = v13_3 + LocaleController.formatPluralString("NewMessages", v12.total_unread_count);
                    label_635:
                        v43 = v4;
                    }
                    else {
                        v8_2 = new StringBuilder();
                        v8_2.append(v13_3);
                        v43 = v4;
                        v8_2.append(LocaleController.formatString("NotificationMessagesPeopleDisplayOrder", 2131625384, new Object[]{LocaleController.formatPluralString("NewMessages", v12.total_unread_count), LocaleController.formatPluralString("FromChats", v12.pushDialogs.size())}));
                        v13_3 = v8_2.toString();
                    }

                    v14_2 = new d(ApplicationLoader.applicationContext).a(((CharSequence)v2_3)).a(2131231416).b(true).b(v12.total_unread_count).a(v1_6).c(v12.notificationGroup).d(true).a(true).a((((long)((MessageObject)v7_3).messageOwner.date)) * v19).e(-13851168);
                    v14_2.a("msg");
                    if(v9_2 == null && v6_4 != null && v6_4.phone != null && v6_4.phone.length() > 0) {
                        v14_2.b("tel:+" + v6_4.phone);
                    }

                    if(v12.pushMessages.size() == 1) {
                        v1 = v12.pushMessages.get(0);
                        v6_5 = new boolean[1];
                        v4_1 = v12.getStringForMessage(((MessageObject)v1), false, v6_5);
                        v1_8 = ((MessageObject)v1).messageOwner.silent;
                        if(v4_1 == null) {
                            return;
                        }
                        else {
                            if(v10_1 != 0) {
                                if(v9_2 != null) {
                                    v2_3 = " @ " + v2_3;
                                    v5_1 = "";
                                }
                                else if(v6_5[0]) {
                                    v2_3 = v2_3 + ": ";
                                    v5_1 = "";
                                }
                                else {
                                    v2_3 = v2_3 + " ";
                                    v5_1 = "";
                                }

                                v2_3 = v4_1.replace(((CharSequence)v2_3), ((CharSequence)v5_1));
                            }
                            else {
                                v2_3 = v4_1;
                            }

                            v14_2.b(((CharSequence)v2_3));
                            v14_2.a(new c().a(((CharSequence)v2_3)));
                            v47 = v3_2;
                            v48 = v11_4;
                            v46 = v15;
                        }
                    }
                    else {
                        v14_2.b(((CharSequence)v13_3));
                        g v1_9 = new g();
                        v1_9.a(((CharSequence)v2_3));
                        v4_2 = Math.min(10, v12.pushMessages.size());
                        v6_5 = new boolean[1];
                        v5_2 = 0;
                        boolean v8_3 = false;
                        String v19_1 = null;
                        while(v5_2 < v4_2) {
                            int v45 = v4_2;
                            Object v4_3 = v12.pushMessages.get(v5_2);
                            v47 = v3_2;
                            v46 = v15;
                            v3_2 = v12.getStringForMessage(((MessageObject)v4_3), false, v6_5);
                            if(v3_2 != null) {
                                v48 = v11_4;
                                v11_3 = v17;
                                if(((MessageObject)v4_3).messageOwner.date <= v11_3) {
                                }
                                else {
                                    if(!v8_3) {
                                        v8_3 = ((MessageObject)v4_3).messageOwner.silent;
                                        v19_1 = v3_2;
                                    }

                                    if(v12.pushDialogs.size() == 1 && v10_1 != 0) {
                                        if(v9_2 != null) {
                                            v4_1 = " @ " + v2_3;
                                            v15_1 = "";
                                        }
                                        else if(v6_5[0]) {
                                            v4_1 = v2_3 + ": ";
                                            v15_1 = "";
                                        }
                                        else {
                                            v4_1 = v2_3 + " ";
                                            v15_1 = "";
                                        }

                                        v3_2 = v3_2.replace(((CharSequence)v4_1), ((CharSequence)v15_1));
                                    }

                                    v1_9.c(((CharSequence)v3_2));
                                }
                            }
                            else {
                                v48 = v11_4;
                                v11_3 = v17;
                            }

                            ++v5_2;
                            v17 = v11_3;
                            v4_2 = v45;
                            v15 = v46;
                            v3_2 = v47;
                            v11_4 = v48;
                        }

                        v47 = v3_2;
                        v48 = v11_4;
                        v46 = v15;
                        v1_9.b(((CharSequence)v13_3));
                        v14_2.a(((i)v1_9));
                        v1_8 = v8_3;
                        v4_1 = v19_1;
                    }

                    v2_4 = new Intent(ApplicationLoader.applicationContext, NotificationDismissReceiver.class);
                    v2_4.putExtra("messageDate", ((MessageObject)v7_3).messageOwner.date);
                    v2_4.putExtra("currentAccount", v12.currentAccount);
                    v14_2.b(PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 1, v2_4, 134217728));
                    if(v48 != null) {
                        v6_6 = v48;
                        v15_1 = null;
                        BitmapDrawable v2_5 = ImageLoader.getInstance().getImageFromMemory(((TLObject)v6_6), v15_1, "50_50");
                        if(v2_5 != null) {
                            v14_2.a(v2_5.getBitmap());
                        }
                        else {
                            goto label_877;
                        }
                    }
                    else {
                        goto label_898;
                    }

                    goto label_899;
                }
                catch(Exception v0) {
                    goto label_1225;
                }

                try {
                label_877:
                    File v3_3 = FileLoader.getPathToAttach(((TLObject)v6_6), true);
                    if(v3_3.exists()) {
                        float v2_6 = 160f / (((float)AndroidUtilities.dp(50f)));
                        BitmapFactory$Options v6_7 = new BitmapFactory$Options();
                        v2_1 = v2_6 < 1f ? 1 : ((int)v2_6);
                        v6_7.inSampleSize = v2_1;
                        Bitmap v2_7 = BitmapFactory.decodeFile(v3_3.getAbsolutePath(), v6_7);
                        if(v2_7 == null) {
                            goto label_899;
                        }

                        v14_2.a(v2_7);
                    }

                    goto label_899;
                }
                catch(Throwable ) {
                    goto label_899;
                }
                catch(Exception v0) {
                    goto label_1225;
                }

            label_898:
                long[] v15_4 = null;
            label_899:
                boolean v11_5 = arg57;
                if(!v11_5) {
                    goto label_945;
                }
                else if(v1_8) {
                    goto label_945;
                }
                else if(v41 == 0) {
                    try {
                        v14_2.d(0);
                        if(Build$VERSION.SDK_INT >= 26) {
                            v2_8 = true;
                            v8 = 3;
                            goto label_955;
                        label_913:
                            v2_1 = v41;
                            if(v2_1 != 1) {
                                if(v2_1 == 2) {
                                }
                                else if(v2_1 == 4) {
                                    v14_2.d(-2);
                                    if(Build$VERSION.SDK_INT >= 26) {
                                        v2_8 = true;
                                        v8 = 1;
                                        goto label_955;
                                    }
                                    else {
                                        goto label_953;
                                    }
                                }
                                else if(v2_1 == 5) {
                                    v14_2.d(-1);
                                    if(Build$VERSION.SDK_INT >= 26) {
                                        goto label_950;
                                    }
                                    else {
                                        goto label_953;
                                    }
                                }
                                else {
                                    goto label_953;
                                }
                            }

                            v14_2.d(1);
                            if(Build$VERSION.SDK_INT < 26) {
                                goto label_953;
                            }

                            v2_8 = true;
                            v8 = 4;
                            goto label_955;
                        label_945:
                            v14_2.d(-1);
                            if(Build$VERSION.SDK_INT < 26) {
                                goto label_953;
                            }

                        label_950:
                            v2_8 = true;
                            v8 = 2;
                        }
                        else {
                        label_953:
                            v2_8 = true;
                            v8 = 0;
                        }

                    label_955:
                        if(v1_8 == v2_8 || v21 != 0) {
                            v6_2 = v37;
                            v2_9 = new long[]{0, 0};
                            v14_2.a(v2_9);
                            v9_3 = ((Uri)v15_1);
                            v1_8 = false;
                            v15_4 = v2_9;
                        }
                        else {
                            if((ApplicationLoader.mainInterfacePaused) || (v24)) {
                                if(v4_1.length() > 100) {
                                    v4_1 = v4_1.substring(0, 100).replace('\n', ' ').trim() + "...";
                                }

                                v14_2.d(((CharSequence)v4_1));
                            }

                            if((MediaController.getInstance().isRecordingAudio()) || v39 == null) {
                            label_1005:
                                v1_4 = ((Uri)v15_1);
                            }
                            else {
                                v2_3 = v39;
                                if(v2_3.equals("NoSound")) {
                                    goto label_1005;
                                }
                                else if(Build$VERSION.SDK_INT < 26) {
                                    if(v2_3.equals(v47)) {
                                        v1_4 = Settings$System.DEFAULT_NOTIFICATION_URI;
                                        v3 = 5;
                                    }
                                    else {
                                        v3 = 5;
                                        v1_4 = Uri.parse(v2_3);
                                    }

                                    v14_2.a(v1_4, v3);
                                    goto label_1005;
                                }
                                else if(v2_3.equals(v47)) {
                                    v1_4 = Settings$System.DEFAULT_NOTIFICATION_URI;
                                }
                                else {
                                    v1_4 = Uri.parse(v2_3);
                                }
                            }

                            if(v37 != 0) {
                                v6_2 = v37;
                                v14_2.a(v6_2, 1000, 1000);
                            }
                            else {
                                v6_2 = v37;
                            }

                            v2_1 = v46;
                            if(v2_1 == 2) {
                                v2_1 = 2;
                            label_1048:
                                v4_5 = new long[]{0, 0};
                            label_1049:
                                v14_2.a(v4_5);
                            label_1050:
                                v9_3 = v1_4;
                                v15_4 = v4_5;
                            }
                            else if(MediaController.getInstance().isRecordingAudio()) {
                                v2_1 = 2;
                                goto label_1048;
                            }
                            else if(v2_1 == 1) {
                                v2_9 = new long[]{0, 100, 0, 100};
                                v14_2.a(v2_9);
                                v9_3 = v1_4;
                                v15_4 = v2_9;
                            }
                            else {
                                if(v2_1 != 0) {
                                    if(v2_1 == 4) {
                                    }
                                    else if(v2_1 == 3) {
                                        v4_5 = new long[]{0, 1000};
                                        goto label_1049;
                                    }
                                    else {
                                        v9_3 = v1_4;
                                        goto label_1052;
                                    }
                                }

                                v14_2.c(2);
                                v4_5 = new long[0];
                                goto label_1050;
                            }

                        label_1052:
                            v1_8 = false;
                        }

                        if((AndroidUtilities.needShowPasscode(v1_8)) || (SharedConfig.isWaitingForPasscodeEnter) || ((MessageObject)v7_3).getDialogId() != 777000 || ((MessageObject)v7_3).messageOwner.reply_markup == null) {
                            v53 = v8;
                            v54 = v9_3;
                            v8_4 = v43;
                            v10_1 = 0;
                        }
                        else {
                            ArrayList v1_10 = ((MessageObject)v7_3).messageOwner.reply_markup.rows;
                            v2_1 = v1_10.size();
                            v4_2 = 0;
                            v10_1 = 0;
                            while(v4_2 < v2_1) {
                                v3_1 = v1_10.get(v4_2);
                                v5_2 = ((TL_keyboardButtonRow)v3_1).buttons.size();
                                int v16 = v10_1;
                                v10_1 = 0;
                                while(v10_1 < v5_2) {
                                    ArrayList v49 = v1_10;
                                    v1 = ((TL_keyboardButtonRow)v3_1).buttons.get(v10_1);
                                    int v50 = v2_1;
                                    if((v1 instanceof TL_keyboardButtonCallback)) {
                                        v51 = v3_1;
                                        v52 = v5_2;
                                        v2_4 = new Intent(ApplicationLoader.applicationContext, NotificationCallbackReceiver.class);
                                        v2_4.putExtra("currentAccount", v12.currentAccount);
                                        v53 = v8;
                                        v54 = v9_3;
                                        v8_4 = v43;
                                        v2_4.putExtra("did", v8_4);
                                        if(((KeyboardButton)v1).data != null) {
                                            v2_4.putExtra("data", ((KeyboardButton)v1).data);
                                        }

                                        v2_4.putExtra("mid", ((MessageObject)v7_3).getId());
                                        v1_2 = ((KeyboardButton)v1).text;
                                        Context v3_4 = ApplicationLoader.applicationContext;
                                        v5_2 = v12.lastButtonId;
                                        v55 = v7_3;
                                        v12.lastButtonId = v5_2 + 1;
                                        v14_2.a(0, ((CharSequence)v1_2), PendingIntent.getBroadcast(v3_4, v5_2, v2_4, 134217728));
                                        v16 = 1;
                                    }
                                    else {
                                        v51 = v3_1;
                                        v52 = v5_2;
                                        v55 = v7_3;
                                        v53 = v8;
                                        v54 = v9_3;
                                        v8_4 = v43;
                                    }

                                    ++v10_1;
                                    v43 = v8_4;
                                    v1_10 = v49;
                                    v2_1 = v50;
                                    v3_1 = v51;
                                    v5_2 = v52;
                                    v8 = v53;
                                    v9_3 = v54;
                                    v7_3 = v55;
                                }

                                ++v4_2;
                                v10_1 = v16;
                                v8 = v8;
                                v9_3 = v9_3;
                            }

                            v53 = v8;
                            v54 = v9_3;
                            v8_4 = v43;
                        }

                        if(v10_1 == 0 && Build$VERSION.SDK_INT < 24 && SharedConfig.passcodeHash.length() == 0 && (this.hasMessagesToReply())) {
                            v1_5 = new Intent(ApplicationLoader.applicationContext, PopupReplyReceiver.class);
                            v1_5.putExtra("currentAccount", v12.currentAccount);
                            if(Build$VERSION.SDK_INT <= 19) {
                                v2_1 = 2131231115;
                                v3_2 = LocaleController.getString("Reply", 2131625860);
                                v1_6 = PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 2, v1_5, 134217728);
                            }
                            else {
                                v2_1 = 2131231114;
                                v3_2 = LocaleController.getString("Reply", 2131625860);
                                v1_6 = PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 2, v1_5, 134217728);
                            }

                            v14_2.a(v2_1, ((CharSequence)v3_2), v1_6);
                        }

                        if(Build$VERSION.SDK_INT >= 26) {
                            long[] v5_3 = ((long[])v15_1);
                            v15_2 = v11_5;
                            v14_2.e(this.validateChannelId(v8_4, v42, v5_3, v6_2, v54, v53, v38, v23, v40));
                        }
                        else {
                            v15_2 = v11_5;
                        }

                        v12.showExtraNotifications(v14_2, v15_2, v13_3);
                        v12.lastNotificationIsNoData = false;
                        this.scheduleNotificationRepeat();
                    }
                    catch(Exception v0) {
                    label_1225:
                        FileLog.e(v0);
                    }
                }
                else {
                    goto label_913;
                }

                return;
            }
        }

    label_1227:
        this.dismissNotification();
    }

    public void updateServerNotificationsSettings(long arg9) {
        int v2 = 0;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.notificationsSettingsUpdated, new Object[0]);
        int v0 = ((int)arg9);
        if(v0 == 0) {
            return;
        }

        SharedPreferences v1 = MessagesController.getNotificationsSettings(this.currentAccount);
        TL_account_updateNotifySettings v3 = new TL_account_updateNotifySettings();
        v3.settings = new TL_inputPeerNotifySettings();
        v3.settings.flags |= 1;
        TL_inputPeerNotifySettings v4 = v3.settings;
        StringBuilder v5 = new StringBuilder();
        v5.append("preview_");
        v5.append(arg9);
        v4.show_previews = v1.getBoolean(v5.toString(), true);
        int v6 = 2;
        v3.settings.flags |= v6;
        v4 = v3.settings;
        v5 = new StringBuilder();
        v5.append("silent_");
        v5.append(arg9);
        v4.silent = v1.getBoolean(v5.toString(), false);
        StringBuilder v4_1 = new StringBuilder();
        v4_1.append("notify2_");
        v4_1.append(arg9);
        int v4_2 = v1.getInt(v4_1.toString(), -1);
        if(v4_2 != -1) {
            v3.settings.flags |= 4;
            if(v4_2 == 3) {
                v4 = v3.settings;
                v5 = new StringBuilder();
                v5.append("notifyuntil_");
                v5.append(arg9);
                v4.mute_until = v1.getInt(v5.toString(), 0);
            }
            else {
                TL_inputPeerNotifySettings v9 = v3.settings;
                if(v4_2 != v6) {
                }
                else {
                    v2 = 2147483647;
                }

                v9.mute_until = v2;
            }
        }

        v3.peer = new TL_inputNotifyPeer();
        v3.peer.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(v0);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(((TLObject)v3), -$$Lambda$NotificationsController$gzeVuWsrsbbw3VMzMvkek-GPn2k.INSTANCE);
    }

    @TargetApi(value=26) private String validateChannelId(long arg18, String arg20, long[] arg21, int arg22, Uri arg23, int arg24, long[] arg25, Uri arg26, int arg27) {
        NotificationsController v0 = this;
        long v1 = arg18;
        long[] v3 = arg21;
        int v4 = arg22;
        Uri v5 = arg23;
        int v6 = arg24;
        SharedPreferences v7 = MessagesController.getNotificationsSettings(v0.currentAccount);
        String v8_1 = "org.telegram.key" + v1;
        String v10 = v7.getString(v8_1, null);
        StringBuilder v11 = new StringBuilder();
        v11.append(v8_1);
        v11.append("_s");
        String v11_1 = v7.getString(v11.toString(), null);
        StringBuilder v12 = new StringBuilder();
        int v14 = 0;
        while(v14 < v3.length) {
            v12.append(v3[v14]);
            ++v14;
            v10 = v10;
        }

        String v16 = v10;
        v12.append(v4);
        if(v5 != null) {
            v12.append(arg23.toString());
        }

        v12.append(v6);
        String v9 = Utilities.MD5(v12.toString());
        if(v16 == null || (v11_1.equals(v9))) {
            v11_1 = v16;
        }
        else {
            NotificationsController.systemNotificationManager.deleteNotificationChannel(v16);
            v11_1 = null;
        }

        if(v11_1 == null) {
            v11_1 = v0.currentAccount + "channel" + v1 + "_" + Utilities.random.nextLong();
            NotificationChannel v1_1 = new NotificationChannel(v11_1, arg20, v6);
            if(v4 != 0) {
                v1_1.enableLights(true);
                v1_1.setLightColor(v4);
            }

            if(!v0.isEmptyVibration(v3)) {
                v1_1.enableVibration(true);
                if(v3 != null && v3.length > 0) {
                    v1_1.setVibrationPattern(v3);
                }
            }
            else {
                v1_1.enableVibration(false);
            }

            AudioAttributes$Builder v2 = new AudioAttributes$Builder();
            v2.setContentType(4);
            v2.setUsage(5);
            if(v5 != null) {
                v1_1.setSound(v5, v2.build());
            }
            else {
                v1_1.setSound(null, v2.build());
            }

            NotificationsController.systemNotificationManager.createNotificationChannel(v1_1);
            SharedPreferences$Editor v1_2 = v7.edit().putString(v8_1, v11_1);
            StringBuilder v2_1 = new StringBuilder();
            v2_1.append(v8_1);
            v2_1.append("_s");
            v1_2.putString(v2_1.toString(), v9).commit();
        }

        return v11_1;
    }
}

