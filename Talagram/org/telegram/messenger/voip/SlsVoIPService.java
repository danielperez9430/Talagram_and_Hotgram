package org.telegram.messenger.voip;

import android.app.Notification$Builder;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent$CanceledException;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.z;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.customization.Model.WhatsupNotif;
import org.telegram.customization.j.a.a;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$PhoneCall;
import org.telegram.tgnet.TLRPC$TL_dataJSON;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_phone_getCallConfig;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.SlsVoIPActivity;

public class SlsVoIPService extends VoIPBaseService implements NotificationCenterDelegate {
    private static final int CALL_MAX_LAYER = 65;
    private static final int CALL_MIN_LAYER = 65;
    public static final int STATE_BUSY = 17;
    public static final int STATE_EXCHANGING_KEYS = 12;
    public static final int STATE_HANGING_UP = 10;
    public static final int STATE_REQUESTING = 14;
    public static final int STATE_RINGING = 16;
    public static final int STATE_WAITING = 13;
    public static final int STATE_WAITING_INCOMING = 15;
    private static final String TAG = "tg-voip-service";
    private byte[] a_or_b;
    private byte[] authKey;
    public static PhoneCall callIShouldHavePutIntoIntent;
    private int callReqId;
    private Runnable delayedStartOutgoingCall;
    private boolean endCallAfterRequest;
    private boolean forceRating;
    private byte[] g_a;
    private byte[] g_a_hash;
    a injectable;
    private long keyFingerprint;
    private boolean needSendDebugLog;
    private ArrayList pendingUpdates;
    private User user;
    WhatsupNotif whatsupNotif;

    public SlsVoIPService() {
        super();
        this.needSendDebugLog = false;
        this.endCallAfterRequest = false;
        this.pendingUpdates = new ArrayList();
        this.injectable = new a();
    }

    public void acceptIncomingCall() {
        this.stopRinging();
        this.showNotification();
        this.configureDeviceForCall();
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
            }
        });
        this.injectable.sipWrapper.answerIncomingCall();
        try {
            SlsVoIPActivity.launchMe(MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(Integer.parseInt(this.injectable.sipWrapper.getWhatsupNotif().getFromId()))), false, true);
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    static Runnable access$002(SlsVoIPService arg0, Runnable arg1) {
        arg0.delayedStartOutgoingCall = arg1;
        return arg1;
    }

    private void acknowledgeCallAndStartRinging() {
        Log.d("servicelog", "onStartCommand6 acknowledgeCallAndStartRinging");
        this.startRinging();
    }

    protected void callEnded() {
        new a().sipWrapper.endIncomingCall(true);
        super.callEnded();
    }

    protected void callFailed(int arg1) {
        super.callFailed(arg1);
    }

    public void debugCtl(int arg2, int arg3) {
        if(this.controller != null) {
            this.controller.debugCtl(arg2, arg3);
        }
    }

    public void declineIncomingCall() {
        this.declineIncomingCall(1, null);
    }

    public void declineIncomingCall(int arg2, Runnable arg3) {
        int v3 = 10;
        if(this.currentState == 14) {
            if(this.delayedStartOutgoingCall != null) {
                AndroidUtilities.cancelRunOnUIThread(this.delayedStartOutgoingCall);
                this.callEnded();
            }
            else {
                this.dispatchStateChanged(v3);
                this.endCallAfterRequest = true;
            }

            return;
        }

        if(this.currentState != v3) {
            if(this.currentState == 11) {
            }
            else {
                this.dispatchStateChanged(v3);
                this.callEnded();
            }
        }
    }

    public void didReceivedNotification(int arg1, int arg2, Object[] arg3) {
        if(arg1 == NotificationCenter.appDidLogout) {
            this.callEnded();
        }
    }

    private void dumpCallObject() {
        try {
            PhoneCall.class.getFields();
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public void forceRating() {
        this.forceRating = true;
    }

    public long getCallID() {
        return 0;
    }

    public CallConnection getConnectionAndStartCall() {
        return null;
    }

    public byte[] getEncryptionKey() {
        return this.authKey;
    }

    public byte[] getGA() {
        return this.g_a;
    }

    public static SlsVoIPService getSharedInstance() {
        VoIPBaseService v0;
        if((SlsVoIPService.sharedInstance instanceof SlsVoIPService)) {
            v0 = SlsVoIPService.sharedInstance;
        }
        else {
            SlsVoIPService v0_1 = null;
        }

        return ((SlsVoIPService)v0);
    }

    protected Class getUIActivityClass() {
        return null;
    }

    public User getUser() {
        return this.user;
    }

    public void hangUp() {
        int v0;
        if(this.currentState != 16) {
            if(this.currentState == 13 && (this.isOutgoing)) {
                goto label_11;
            }

            v0 = 1;
        }
        else {
        label_11:
            v0 = 3;
        }

        this.declineIncomingCall(v0, null);
    }

    public void hangUp(Runnable arg3) {
        int v0;
        if(this.currentState != 16) {
            if(this.currentState == 13 && (this.isOutgoing)) {
                goto label_11;
            }

            v0 = 1;
        }
        else {
        label_11:
            v0 = 3;
        }

        this.declineIncomingCall(v0, arg3);
    }

    static Intent incomingActivityIntent() {
        try {
            int v1 = Integer.parseInt(new a().sipWrapper.getWhatsupNotif().getFromId());
            User v2 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(v1));
            if(v2 == null) {
                v2 = MessagesStorage.getInstance(UserConfig.selectedAccount).getUser(v1);
            }

            return SlsVoIPActivity.myIntent(v2, false, false);
        }
        catch(Exception ) {
            return SlsVoIPActivity.myIntent(null, false, false);
        }
    }

    public IBinder onBind(Intent arg2) {
        Log.d("servicelog", "onBind");
        return null;
    }

    public void onCallUpgradeRequestReceived() {
    }

    protected void onControllerPreRelease() {
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onGroupCallKeyReceived(byte[] arg1) {
    }

    public void onGroupCallKeySent() {
    }

    void onMediaButtonEvent(KeyEvent arg3) {
        if(arg3.getKeyCode() == 79 && arg3.getAction() == 1) {
            if(this.currentState == 15) {
                this.acceptIncomingCall();
            }
            else {
                this.setMicMute(this.isMicMute() ^ 1);
                Iterator v3 = this.stateListeners.iterator();
                while(v3.hasNext()) {
                    v3.next().onAudioSettingsChanged();
                }
            }
        }
    }

    public int onStartCommand(Intent arg3, int arg4, int arg5) {
        Log.d("servicelog", "onStartCommand1");
        if(SlsVoIPService.sharedInstance != null) {
            Log.d("servicelog", "onStartCommand2 sharedInstance!=null");
        }

        this.isOutgoing = arg3.getBooleanExtra("is_outgoing", false);
        this.currentAccount = UserConfig.selectedAccount;
        if(this.isOutgoing) {
            arg4 = arg3.getIntExtra("user_id", 0);
            this.user = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(arg4));
            if(this.user == null) {
                this.user = MessagesStorage.getInstance(UserConfig.selectedAccount).getUser(arg4);
            }

            this.dispatchStateChanged(14);
            this.delayedStartOutgoingCall = new Runnable() {
                public void run() {
                    SlsVoIPService.this.delayedStartOutgoingCall = null;
                    SlsVoIPService.this.startOutgoingCall();
                }
            };
            AndroidUtilities.runOnUIThread(this.delayedStartOutgoingCall, 2000);
            if(!arg3.getBooleanExtra("start_incall_activity", false)) {
                goto label_86;
            }

            this.startActivity(SlsVoIPService.outgoingActivityIntent().addFlags(268435456));
        }
        else {
            if(arg3 != null && arg3.getExtras() != null) {
                this.whatsupNotif = this.injectable.gson.a(arg3.getStringExtra("EXTRA_CALL_INFO"), WhatsupNotif.class);
                Log.d("servicelog", "onStartCommand3 intent!=null&& intent.getExtras()!=null");
            }

            int v3 = Integer.parseInt(this.whatsupNotif.getFromId());
            this.user = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(v3));
            if(this.user == null) {
                this.user = MessagesStorage.getInstance(UserConfig.selectedAccount).getUser(v3);
            }

            Log.d("servicelog", "onStartCommand4 MessagesController.getInstance(UserConfig.selectedAccount).getUser(userID);");
            NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.closeInCallActivity, new Object[0]);
            SlsVoIPService.callIShouldHavePutIntoIntent = null;
            this.acknowledgeCallAndStartRinging();
            Log.d("servicelog", "onStartCommand6 acknowledgeCallAndStartRinging");
        }

    label_86:
        arg4 = 2;
        if(this.user == null) {
            FileLog.w("VoIPService: user==null");
            this.stopSelf();
            Log.d("servicelog", "onStartCommand5 user==null");
            return arg4;
        }

        SlsVoIPService.sharedInstance = ((VoIPBaseService)this);
        return arg4;
    }

    public void onUIForegroundStateChanged(boolean arg3) {
        if(this.currentState == 15) {
            if(arg3) {
                this.stopForeground(true);
            }
            else if(!this.getSystemService("keyguard").inKeyguardRestrictedInputMode()) {
                this.showIncomingNotification();
            }
            else {
                AndroidUtilities.runOnUIThread(new Runnable() {
                    public void run() {
                        Intent v0 = new Intent(SlsVoIPService.this, SlsVoIPActivity.class);
                        v0.addFlags(805306368);
                        try {
                            PendingIntent.getActivity(SlsVoIPService.this, 0, v0, 0).send();
                        }
                        catch(PendingIntent$CanceledException v0_1) {
                            FileLog.e("error restarting activity", ((Throwable)v0_1));
                        }
                    }
                }, 500);
            }
        }
    }

    static Intent outgoingActivityIntent() {
        try {
            int v1 = Integer.parseInt(new a().sipWrapper.getCallRequestModel().getCalleeUser());
            User v2 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(v1));
            if(v2 == null) {
                v2 = MessagesStorage.getInstance(UserConfig.selectedAccount).getUser(v1);
            }

            return SlsVoIPActivity.myIntent(v2, false, false);
        }
        catch(Exception ) {
            return SlsVoIPActivity.myIntent(null, true, false);
        }
    }

    private void processAcceptedCall() {
    }

    public void publicCallEnded() {
        this.callEnded();
    }

    protected void showIncomingNotification() {
        User v1_2;
        SpannableString v12_1;
        SpannableString v9_3;
        int v7_1;
        int v12;
        SlsVoIPService v0 = this;
        String v1_1 = v0.user.first_name + " " + v0.user.last_name;
        int v3 = 2131626381;
        String v2 = LocaleController.getString("VoipInCallBranding", v3);
        Intent v4 = SlsVoIPService.incomingActivityIntent();
        v4.addFlags(805306368);
        Notification$Builder v5 = new Notification$Builder(((Context)v0)).setContentTitle(LocaleController.getString("VoipInCallBranding", v3)).setContentText(((CharSequence)v1_1)).setSmallIcon(2131231416).setContentIntent(PendingIntent.getActivity(((Context)v0), 0, v4, 0));
        if(Build$VERSION.SDK_INT >= 26) {
            SharedPreferences v7 = MessagesController.getGlobalNotificationsSettings();
            int v9 = v7.getInt("calls_notification_channel", 0);
            Object v10 = v0.getSystemService("notification");
            StringBuilder v11 = new StringBuilder();
            v11.append("incoming_calls");
            v11.append(v9);
            NotificationChannel v11_1 = ((NotificationManager)v10).getNotificationChannel(v11.toString());
            v12 = 4;
            if(v11_1 != null) {
                if(v11_1.getImportance() >= v12 && v11_1.getSound() == null) {
                    if(v11_1.getVibrationPattern() != null) {
                    }
                    else {
                        v7_1 = 0;
                        goto label_71;
                    }
                }

                FileLog.d("User messed up the notification channel; deleting it and creating a proper one");
                ((NotificationManager)v10).deleteNotificationChannel("incoming_calls" + v9);
                ++v9;
                v7.edit().putInt("calls_notification_channel", v9).commit();
                goto label_70;
            }
            else {
            label_70:
                v7_1 = 1;
            }

        label_71:
            if(v7_1 != 0) {
                v11 = new StringBuilder();
                v11.append("incoming_calls");
                v11.append(v9);
                NotificationChannel v7_2 = new NotificationChannel(v11.toString(), LocaleController.getString("IncomingCalls", 2131624981), v12);
                v7_2.setSound(null, null);
                v7_2.enableVibration(false);
                v7_2.enableLights(false);
                ((NotificationManager)v10).createNotificationChannel(v7_2);
            }

            v5.setChannelId("incoming_calls" + v9);
        }

        Intent v7_4 = new Intent(((Context)v0), VoIPActionsReceiver.class);
        v7_4.setAction(this.getPackageName() + ".DECLINE_CALL");
        v7_4.putExtra("call_id", this.getCallID());
        String v9_2 = LocaleController.getString("VoipDeclineCall", 2131626373);
        v12 = 24;
        if(Build$VERSION.SDK_INT >= v12) {
            SpannableString v11_2 = new SpannableString(((CharSequence)v9_2));
            v11_2.setSpan(new ForegroundColorSpan(-769226), 0, ((CharSequence)v11_2).length(), 0);
            v9_3 = v11_2;
        }

        int v11_3 = 268435456;
        PendingIntent v7_5 = PendingIntent.getBroadcast(((Context)v0), 0, v7_4, v11_3);
        v5.addAction(2131231135, ((CharSequence)v9_3), v7_5);
        Intent v9_4 = new Intent(((Context)v0), VoIPActionsReceiver.class);
        v9_4.setAction(this.getPackageName() + ".ANSWER_CALL");
        v9_4.putExtra("call_id", this.getCallID());
        int v14 = 2131626365;
        String v13_1 = LocaleController.getString("VoipAnswerCall", v14);
        if(Build$VERSION.SDK_INT >= v12) {
            v12_1 = new SpannableString(((CharSequence)v13_1));
            v12_1.setSpan(new ForegroundColorSpan(-16733696), 0, ((CharSequence)v12_1).length(), 0);
        }
        else {
            String v12_2 = v13_1;
        }

        PendingIntent v9_5 = PendingIntent.getBroadcast(((Context)v0), 0, v9_4, v11_3);
        v5.addAction(2131231144, ((CharSequence)v12_1), v9_5);
        v5.setPriority(2);
        if(Build$VERSION.SDK_INT >= 17) {
            v5.setShowWhen(false);
        }

        v11_3 = 21;
        if(Build$VERSION.SDK_INT >= v11_3) {
            v5.setColor(-13851168);
            v5.setVibrate(new long[0]);
            v5.setCategory("call");
            v5.setFullScreenIntent(PendingIntent.getActivity(((Context)v0), 0, v4, 0), true);
        }

        Notification v4_1 = v5.getNotification();
        if(Build$VERSION.SDK_INT >= v11_3) {
            String v10_1 = this.getPackageName();
            v11_3 = LocaleController.isRTL ? 2131492952 : 2131492951;
            RemoteViews v5_1 = new RemoteViews(v10_1, v11_3);
            v5_1.setTextViewText(2131296744, ((CharSequence)v1_1));
            int v10_2 = 8;
            v11_3 = 2131296968;
            v12 = 2131296924;
            if(TextUtils.isEmpty(((CharSequence)v2))) {
                v5_1.setViewVisibility(v12, v10_2);
                if(UserConfig.getActivatedAccountsCount() > 1) {
                    v1_2 = UserConfig.getInstance(v0.currentAccount).getCurrentUser();
                    v1_1 = LocaleController.formatString("VoipInCallBrandingWithName", 2131626382, new Object[]{ContactsController.formatName(v1_2.first_name, v1_2.last_name)});
                }
                else {
                    v1_1 = LocaleController.getString("VoipInCallBranding", v3);
                }

                v5_1.setTextViewText(v11_3, ((CharSequence)v1_1));
            }
            else {
                if(UserConfig.getActivatedAccountsCount() > 1) {
                    v1_2 = UserConfig.getInstance(v0.currentAccount).getCurrentUser();
                    v5_1.setTextViewText(v12, LocaleController.formatString("VoipAnsweringAsAccount", 2131626366, new Object[]{ContactsController.formatName(v1_2.first_name, v1_2.last_name)}));
                }
                else {
                    v5_1.setViewVisibility(v12, v10_2);
                }

                v5_1.setTextViewText(v11_3, ((CharSequence)v2));
            }

            v5_1.setTextViewText(2131296306, LocaleController.getString("VoipAnswerCall", v14));
            v5_1.setTextViewText(2131296430, LocaleController.getString("VoipDeclineCall", 2131626373));
            v5_1.setImageViewBitmap(2131296782, v0.getRoundAvatarBitmap(v0.user));
            v5_1.setOnClickPendingIntent(2131296305, v9_5);
            v5_1.setOnClickPendingIntent(2131296429, v7_5);
            v4_1.bigContentView = v5_1;
            v4_1.headsUpContentView = v5_1;
        }

        v0.startForeground(202, v4_1);
    }

    protected void showNotification() {
        String v0 = ContactsController.formatName(this.user.first_name, this.user.last_name);
        FileLocation v1 = this.user.photo != null ? this.user.photo.photo_small : null;
        this.showNotification(v0, v1, SlsVoIPActivity.class);
    }

    private void startConnectingSound() {
        if(this.spPlayID != 0) {
            this.soundPool.stop(this.spPlayID);
        }

        this.spPlayID = this.soundPool.play(this.spConnectingId, 1f, 1f, 0, -1, 1f);
        if(this.spPlayID == 0) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    if(VoIPBaseService.sharedInstance == null) {
                        return;
                    }

                    if(SlsVoIPService.this.spPlayID == 0) {
                        SlsVoIPService.this.spPlayID = SlsVoIPService.this.soundPool.play(SlsVoIPService.this.spConnectingId, 1f, 1f, 0, -1, 1f);
                    }

                    if(SlsVoIPService.this.spPlayID == 0) {
                        AndroidUtilities.runOnUIThread(((Runnable)this), 100);
                    }
                }
            }, 100);
        }
    }

    public void startOutgoingCall() {
        this.configureDeviceForCall();
        this.showNotification();
        this.startConnectingSound();
        this.dispatchStateChanged(14);
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
            }
        });
    }

    protected void startRinging() {
        String v5;
        String v2_2;
        this.dispatchStateChanged(15);
        SharedPreferences v0 = this.getSharedPreferences("Notifications", 0);
        this.ringtonePlayer = new MediaPlayer();
        this.ringtonePlayer.setOnPreparedListener(new MediaPlayer$OnPreparedListener() {
            public void onPrepared(MediaPlayer arg1) {
                SlsVoIPService.this.ringtonePlayer.start();
            }
        });
        this.ringtonePlayer.setLooping(true);
        int v4 = 2;
        this.ringtonePlayer.setAudioStreamType(v4);
        try {
            StringBuilder v2_1 = new StringBuilder();
            v2_1.append("custom_");
            v2_1.append(this.user.id);
            if(v0.getBoolean(v2_1.toString(), false)) {
                v2_2 = "ringtone_path_" + this.user.id;
                v5 = RingtoneManager.getDefaultUri(1).toString();
            }
            else {
                v2_2 = "CallsRingtonePath";
                v5 = RingtoneManager.getDefaultUri(1).toString();
            }

            v2_2 = v0.getString(v2_2, v5);
            this.ringtonePlayer.setDataSource(((Context)this), Uri.parse(v2_2));
            this.ringtonePlayer.prepareAsync();
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
            if(this.ringtonePlayer == null) {
                goto label_58;
            }

            this.ringtonePlayer.release();
            this.ringtonePlayer = null;
        }

    label_58:
        Object v2_3 = this.getSystemService("audio");
        StringBuilder v5_1 = new StringBuilder();
        v5_1.append("custom_");
        v5_1.append(this.user.id);
        v5 = v0.getBoolean(v5_1.toString(), false) ? "calls_vibrate_" + this.user.id : "vibrate_calls";
        int v0_1 = v0.getInt(v5, 0);
        int v5_2 = 4;
        if(v0_1 == v4 || v0_1 == v5_2 || ((AudioManager)v2_3).getRingerMode() != v4) {
            if(v0_1 == v5_2 && ((AudioManager)v2_3).getRingerMode() == 1) {
            label_92:
                this.vibrator = this.getSystemService("vibrator");
                long v5_3 = 700;
                int v2_4 = 3;
                if(v0_1 == 1) {
                    v5_3 = 350;
                }
                else if(v0_1 == v2_4) {
                    v5_3 = 1400;
                }

                Vibrator v0_2 = this.vibrator;
                long[] v2_5 = new long[v2_4];
                v2_5[0] = 0;
                v2_5[1] = v5_3;
                v2_5[v4] = 500;
                v0_2.vibrate(v2_5, 0);
            }
        }
        else if(((AudioManager)v2_3).getRingerMode() == 1) {
            goto label_92;
        }
        else {
            goto label_92;
        }

        if(Build$VERSION.SDK_INT >= 21 && !this.getSystemService("keyguard").inKeyguardRestrictedInputMode() && (z.a(((Context)this)).a())) {
            this.showIncomingNotification();
            FileLog.d("Showing incoming call notification");
            return;
        }

        FileLog.d("Starting incall activity for incoming call");
        v0_1 = 12345;
        try {
            PendingIntent.getActivity(((Context)this), v0_1, SlsVoIPService.incomingActivityIntent().addFlags(268435456), 0).send();
        }
        catch(Exception v0_3) {
            FileLog.e("Error starting incall activity", ((Throwable)v0_3));
        }
    }

    public void stopConnectingSound() {
        if(this.spPlayID != 0) {
            this.soundPool.stop(this.spPlayID);
        }
    }

    protected void updateServerConfig() {
        SharedPreferences v0 = this.getSharedPreferences("mainconfig", 0);
        VoIPServerConfig.setConfig(v0.getString("voip_server_config", "{}"));
        if(System.currentTimeMillis() - v0.getLong("voip_server_config_updated", 0) > 86400000) {
            ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(new TL_phone_getCallConfig(), new RequestDelegate(v0) {
                public void run(TLObject arg3, TL_error arg4) {
                    if(arg4 == null) {
                        String v3 = ((TL_dataJSON)arg3).data;
                        VoIPServerConfig.setConfig(v3);
                        this.val$preferences.edit().putString("voip_server_config", v3).putLong("voip_server_config_updated", System.currentTimeMillis()).apply();
                    }
                }
            });
        }
    }
}

