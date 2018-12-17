package org.telegram.messenger.voip;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification$Builder;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path$Direction;
import android.graphics.Path;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager$OnAudioFocusChangeListener;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager$WakeLock;
import android.os.Vibrator;
import android.provider.Settings$Global;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccount$Builder;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.StatsController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.BottomSheet$Builder;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.VoIPPermissionActivity;

public abstract class VoIPBaseService extends Service implements SensorEventListener, AudioManager$OnAudioFocusChangeListener, NotificationCenterDelegate, ConnectionStateListener {
    class org.telegram.messenger.voip.VoIPBaseService$1 implements Runnable {
        org.telegram.messenger.voip.VoIPBaseService$1(VoIPBaseService arg1) {
            VoIPBaseService.this = arg1;
            super();
        }

        public void run() {
            VoIPBaseService.this.soundPool.release();
            if(VoIPBaseService.USE_CONNECTION_SERVICE) {
                return;
            }

            if(VoIPBaseService.this.isBtHeadsetConnected) {
                ApplicationLoader.applicationContext.getSystemService("audio").stopBluetoothSco();
            }

            ApplicationLoader.applicationContext.getSystemService("audio").setSpeakerphoneOn(false);
        }
    }

    class org.telegram.messenger.voip.VoIPBaseService$2 extends BroadcastReceiver {
        org.telegram.messenger.voip.VoIPBaseService$2(VoIPBaseService arg1) {
            VoIPBaseService.this = arg1;
            super();
        }

        public void onReceive(Context arg5, Intent arg6) {
            VoIPBaseService v5;
            boolean v0 = true;
            if("android.intent.action.HEADSET_PLUG".equals(arg6.getAction())) {
                v5 = VoIPBaseService.this;
                if(arg6.getIntExtra("state", 0) == 1) {
                }
                else {
                    v0 = false;
                }

                v5.isHeadsetPlugged = v0;
                if((VoIPBaseService.this.isHeadsetPlugged) && VoIPBaseService.this.proximityWakelock != null && (VoIPBaseService.this.proximityWakelock.isHeld())) {
                    VoIPBaseService.this.proximityWakelock.release();
                }

                VoIPBaseService.this.isProximityNear = false;
                VoIPBaseService.this.updateOutputGainControlState();
            }
            else {
                if("android.net.conn.CONNECTIVITY_CHANGE".equals(arg6.getAction())) {
                    VoIPBaseService.this.updateNetworkType();
                    return;
                }

                int v2 = 2;
                if("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(arg6.getAction())) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("bt headset state = " + arg6.getIntExtra("android.bluetooth.profile.extra.STATE", 0));
                    }

                    v5 = VoIPBaseService.this;
                    if(arg6.getIntExtra("android.bluetooth.profile.extra.STATE", 0) == v2) {
                    }
                    else {
                        v0 = false;
                    }

                    v5.updateBluetoothHeadsetState(v0);
                    return;
                }

                if("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(arg6.getAction())) {
                    int v5_2 = arg6.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("Bluetooth SCO state updated: " + v5_2);
                    }

                    if(v5_2 == 0 && (VoIPBaseService.this.isBtHeadsetConnected) && (!VoIPBaseService.this.btAdapter.isEnabled() || VoIPBaseService.this.btAdapter.getProfileConnectionState(1) != v2)) {
                        VoIPBaseService.this.updateBluetoothHeadsetState(false);
                        return;
                    }

                    VoIPBaseService v6_1 = VoIPBaseService.this;
                    boolean v5_3 = v5_2 == 1 ? true : false;
                    v6_1.bluetoothScoActive = v5_3;
                    if((VoIPBaseService.this.bluetoothScoActive) && (VoIPBaseService.this.needSwitchToBluetoothAfterScoActivates)) {
                        VoIPBaseService.this.needSwitchToBluetoothAfterScoActivates = false;
                        Object v5_4 = VoIPBaseService.this.getSystemService("audio");
                        ((AudioManager)v5_4).setSpeakerphoneOn(false);
                        ((AudioManager)v5_4).setBluetoothScoOn(true);
                    }

                    Iterator v5_5 = VoIPBaseService.this.stateListeners.iterator();
                    while(true) {
                        if(!v5_5.hasNext()) {
                            return;
                        }

                        v5_5.next().onAudioSettingsChanged();
                    }
                }

                if(!"android.intent.action.PHONE_STATE".equals(arg6.getAction())) {
                    return;
                }

                if(!TelephonyManager.EXTRA_STATE_OFFHOOK.equals(arg6.getStringExtra("state"))) {
                    return;
                }

                VoIPBaseService.this.hangUp();
            }
        }
    }

    @TargetApi(value=26) public class CallConnection extends Connection {
        public CallConnection(VoIPBaseService arg1) {
            VoIPBaseService.this = arg1;
            super();
            this.setConnectionProperties(128);
            this.setAudioModeIsVoip(true);
        }

        public void onAnswer() {
            VoIPBaseService.this.acceptIncomingCallFromNotification();
        }

        public void onCallAudioStateChanged(CallAudioState arg3) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("ConnectionService call audio state changed: " + arg3);
            }

            Iterator v3 = VoIPBaseService.this.stateListeners.iterator();
            while(v3.hasNext()) {
                v3.next().onAudioSettingsChanged();
            }
        }

        public void onCallEvent(String arg2, Bundle arg3) {
            super.onCallEvent(arg2, arg3);
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("ConnectionService onCallEvent " + arg2);
            }
        }

        public void onDisconnect() {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("ConnectionService onDisconnect");
            }

            this.setDisconnected(new DisconnectCause(2));
            this.destroy();
            VoIPBaseService.this.systemCallConnection = null;
            VoIPBaseService.this.hangUp();
        }

        public void onReject() {
            VoIPBaseService.this.needPlayEndSound = false;
            VoIPBaseService.this.declineIncomingCall(1, null);
        }

        public void onShowIncomingCallUi() {
            VoIPBaseService.this.startRinging();
        }

        public void onSilence() {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("onSlience");
            }

            VoIPBaseService.this.stopRinging();
        }

        public void onStateChanged(int arg3) {
            super.onStateChanged(arg3);
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("ConnectionService onStateChanged " + arg3);
            }
        }
    }

    public interface StateListener {
        void onAudioSettingsChanged();

        void onSignalBarsCountChanged(int arg1);

        void onStateChanged(int arg1);
    }

    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
    public static final int AUDIO_ROUTE_BLUETOOTH = 2;
    public static final int AUDIO_ROUTE_EARPIECE = 0;
    public static final int AUDIO_ROUTE_SPEAKER = 1;
    public static final int DISCARD_REASON_DISCONNECT = 2;
    public static final int DISCARD_REASON_HANGUP = 1;
    public static final int DISCARD_REASON_LINE_BUSY = 4;
    public static final int DISCARD_REASON_MISSED = 3;
    protected static final int ID_INCOMING_CALL_NOTIFICATION = 202;
    protected static final int ID_ONGOING_CALL_NOTIFICATION = 201;
    protected static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    public static final int STATE_ENDED = 11;
    public static final int STATE_ESTABLISHED = 3;
    public static final int STATE_FAILED = 4;
    public static final int STATE_RECONNECTING = 5;
    public static final int STATE_WAIT_INIT = 1;
    public static final int STATE_WAIT_INIT_ACK = 2;
    protected static final boolean USE_CONNECTION_SERVICE;
    protected Runnable afterSoundRunnable;
    protected boolean audioConfigured;
    protected int audioRouteToSet;
    protected boolean bluetoothScoActive;
    protected BluetoothAdapter btAdapter;
    protected int callDiscardReason;
    protected VoIPController controller;
    protected boolean controllerStarted;
    protected PowerManager$WakeLock cpuWakelock;
    protected int currentAccount;
    protected int currentState;
    protected boolean haveAudioFocus;
    protected boolean isBtHeadsetConnected;
    protected boolean isHeadsetPlugged;
    protected boolean isOutgoing;
    protected boolean isProximityNear;
    protected int lastError;
    protected long lastKnownDuration;
    protected NetworkInfo lastNetInfo;
    private Boolean mHasEarpiece;
    protected boolean micMute;
    protected boolean needPlayEndSound;
    protected boolean needSwitchToBluetoothAfterScoActivates;
    protected Notification ongoingCallNotification;
    protected boolean playingSound;
    protected Stats prevStats;
    protected PowerManager$WakeLock proximityWakelock;
    protected BroadcastReceiver receiver;
    protected MediaPlayer ringtonePlayer;
    protected static VoIPBaseService sharedInstance;
    protected int signalBarCount;
    protected SoundPool soundPool;
    protected int spBusyId;
    protected int spConnectingId;
    protected int spEndId;
    protected int spFailedID;
    protected int spPlayID;
    protected int spRingbackID;
    protected boolean speakerphoneStateToSet;
    protected ArrayList stateListeners;
    protected Stats stats;
    protected CallConnection systemCallConnection;
    protected Runnable timeoutRunnable;
    protected Vibrator vibrator;
    private boolean wasEstablished;

    static {
        VoIPBaseService.USE_CONNECTION_SERVICE = VoIPBaseService.isDeviceCompatibleWithConnectionServiceAPI();
    }

    public VoIPBaseService() {
        super();
        this.currentAccount = -1;
        this.currentState = 0;
        this.stateListeners = new ArrayList();
        this.stats = new Stats();
        this.prevStats = new Stats();
        this.afterSoundRunnable = new org.telegram.messenger.voip.VoIPBaseService$1(this);
        this.lastKnownDuration = 0;
        this.receiver = new org.telegram.messenger.voip.VoIPBaseService$2(this);
        this.mHasEarpiece = null;
        this.audioRouteToSet = 2;
        this.bluetoothScoActive = false;
        this.needSwitchToBluetoothAfterScoActivates = false;
    }

    public abstract void acceptIncomingCall();

    private void acceptIncomingCallFromNotification() {
        this.showNotification();
        if(Build$VERSION.SDK_INT >= 23 && this.checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            try {
                PendingIntent.getActivity(((Context)this), 0, new Intent(((Context)this), VoIPPermissionActivity.class).addFlags(268435456), 0).send();
            }
            catch(Exception v0) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("Error starting permission activity", ((Throwable)v0));
                }
                else {
                }
            }

            return;
        }

        this.acceptIncomingCall();
        try {
            PendingIntent.getActivity(((Context)this), 0, new Intent(((Context)this), this.getUIActivityClass()).addFlags(805306368), 0).send();
        }
        catch(Exception v0) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("Error starting incall activity", ((Throwable)v0));
        }
    }

    static void access$000(VoIPBaseService arg0) {
        arg0.acceptIncomingCallFromNotification();
    }

    @TargetApi(value=26) protected PhoneAccountHandle addAccountToTelecomManager() {
        Object v0 = this.getSystemService("telecom");
        User v1 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        ComponentName v3 = new ComponentName(((Context)this), TelegramConnectionService.class);
        StringBuilder v4 = new StringBuilder();
        v4.append("");
        v4.append(v1.id);
        PhoneAccountHandle v2 = new PhoneAccountHandle(v3, v4.toString());
        ((TelecomManager)v0).registerPhoneAccount(new PhoneAccount$Builder(v2, ContactsController.formatName(v1.first_name, v1.last_name)).setCapabilities(2048).setIcon(Icon.createWithResource(((Context)this), 2131231181)).setHighlightColor(-13851168).addSupportedUriScheme("sip").build());
        return v2;
    }

    protected void callEnded() {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("Call " + this.getCallID() + " ended");
        }

        this.dispatchStateChanged(11);
        long v1 = 700;
        if(this.needPlayEndSound) {
            this.playingSound = true;
            this.soundPool.play(this.spEndId, 1f, 1f, 0, 0, 1f);
            AndroidUtilities.runOnUIThread(this.afterSoundRunnable, v1);
        }

        if(this.timeoutRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.timeoutRunnable);
            this.timeoutRunnable = null;
        }

        if(VoIPBaseService.USE_CONNECTION_SERVICE) {
            org.telegram.messenger.voip.VoIPBaseService$8 v0_1 = new Runnable() {
                public void run() {
                    DisconnectCause v1;
                    CallConnection v0;
                    int v2;
                    if(VoIPBaseService.this.systemCallConnection != null) {
                        switch(VoIPBaseService.this.callDiscardReason) {
                            case 1: {
                                v0 = VoIPBaseService.this.systemCallConnection;
                                v2 = VoIPBaseService.this.isOutgoing ? 2 : 6;
                                v1 = new DisconnectCause(v2);
                                break;
                            }
                            case 2: {
                                v0 = VoIPBaseService.this.systemCallConnection;
                                v1 = new DisconnectCause(1);
                                break;
                            }
                            case 3: {
                                v0 = VoIPBaseService.this.systemCallConnection;
                                v2 = VoIPBaseService.this.isOutgoing ? 4 : 5;
                                v1 = new DisconnectCause(v2);
                                break;
                            }
                            case 4: {
                                v0 = VoIPBaseService.this.systemCallConnection;
                                v1 = new DisconnectCause(7);
                                break;
                            }
                            default: {
                                v0 = VoIPBaseService.this.systemCallConnection;
                                v1 = new DisconnectCause(3);
                                break;
                            }
                        }

                        v0.setDisconnected(v1);
                        VoIPBaseService.this.systemCallConnection.destroy();
                        VoIPBaseService.this.systemCallConnection = null;
                    }
                }
            };
            if(this.needPlayEndSound) {
                AndroidUtilities.runOnUIThread(((Runnable)v0_1), v1);
            }
            else {
                ((Runnable)v0_1).run();
            }
        }

        this.stopSelf();
    }

    protected void callFailed() {
        int v0 = this.controller == null || !this.controllerStarted ? 0 : this.controller.getLastError();
        this.callFailed(v0);
    }

    protected void callFailed(int arg10) {
        try {
            StringBuilder v1 = new StringBuilder();
            v1.append("Call ");
            v1.append(this.getCallID());
            v1.append(" failed with error code ");
            v1.append(arg10);
            throw new Exception(v1.toString());
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            this.lastError = arg10;
            this.dispatchStateChanged(4);
            if(arg10 != -3 && this.soundPool != null) {
                this.playingSound = true;
                this.soundPool.play(this.spFailedID, 1f, 1f, 0, 0, 1f);
                AndroidUtilities.runOnUIThread(this.afterSoundRunnable, 1000);
            }

            if((VoIPBaseService.USE_CONNECTION_SERVICE) && this.systemCallConnection != null) {
                this.systemCallConnection.setDisconnected(new DisconnectCause(1));
                this.systemCallConnection.destroy();
                this.systemCallConnection = null;
            }

            this.stopSelf();
            return;
        }
    }

    void callFailedFromConnectionService() {
        if(this.isOutgoing) {
            this.callFailed(-5);
        }
        else {
            this.hangUp();
        }
    }

    protected void configureDeviceForCall() {
        this.needPlayEndSound = true;
        Object v1 = this.getSystemService("audio");
        int v3 = 3;
        if(!VoIPBaseService.USE_CONNECTION_SERVICE) {
            ((AudioManager)v1).setMode(v3);
            boolean v2 = false;
            ((AudioManager)v1).requestAudioFocus(((AudioManager$OnAudioFocusChangeListener)this), 0, 1);
            if((this.isBluetoothHeadsetConnected()) && (this.hasEarpiece())) {
                switch(this.audioRouteToSet) {
                    case 0: {
                        goto label_27;
                    }
                    case 1: {
                        goto label_24;
                    }
                    case 2: {
                        goto label_17;
                    }
                }

                goto label_36;
            label_17:
                if(!this.bluetoothScoActive) {
                    this.needSwitchToBluetoothAfterScoActivates = true;
                    try {
                        ((AudioManager)v1).startBluetoothSco();
                    }
                    catch(Throwable ) {
                    }

                    goto label_36;
                }
                else {
                    ((AudioManager)v1).setBluetoothScoOn(true);
                    goto label_35;
                label_24:
                    ((AudioManager)v1).setBluetoothScoOn(false);
                    ((AudioManager)v1).setSpeakerphoneOn(true);
                    goto label_36;
                label_27:
                    ((AudioManager)v1).setBluetoothScoOn(false);
                }
            }
            else if(this.isBluetoothHeadsetConnected()) {
                ((AudioManager)v1).setBluetoothScoOn(this.speakerphoneStateToSet);
                goto label_36;
            }
            else {
                v2 = this.speakerphoneStateToSet;
            }

        label_35:
            ((AudioManager)v1).setSpeakerphoneOn(v2);
        }

    label_36:
        this.updateOutputGainControlState();
        this.audioConfigured = true;
        Object v0 = this.getSystemService("sensor");
        Sensor v1_1 = ((SensorManager)v0).getDefaultSensor(8);
        if(v1_1 != null) {
            try {
                this.proximityWakelock = this.getSystemService("power").newWakeLock(32, "telegram-voip-prx");
                ((SensorManager)v0).registerListener(((SensorEventListener)this), v1_1, v3);
            }
            catch(Exception v0_1) {
                if(!BuildVars.LOGS_ENABLED) {
                    return;
                }

                FileLog.e("Error initializing proximity sensor", ((Throwable)v0_1));
            }
        }
    }

    protected VoIPController createController() {
        return new VoIPController();
    }

    public abstract void declineIncomingCall();

    public abstract void declineIncomingCall(int arg1, Runnable arg2);

    public void didReceivedNotification(int arg1, int arg2, Object[] arg3) {
        if(arg1 == NotificationCenter.appDidLogout) {
            this.callEnded();
        }
    }

    protected void dispatchStateChanged(int arg4) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("== Call " + this.getCallID() + " state changed to " + arg4 + " ==");
        }

        this.currentState = arg4;
        if((VoIPBaseService.USE_CONNECTION_SERVICE) && arg4 == 3 && this.systemCallConnection != null) {
            this.systemCallConnection.setActive();
        }

        int v0_1;
        for(v0_1 = 0; v0_1 < this.stateListeners.size(); ++v0_1) {
            this.stateListeners.get(v0_1).onStateChanged(arg4);
        }
    }

    public int getAccount() {
        return this.currentAccount;
    }

    public long getCallDuration() {
        if(this.controllerStarted) {
            if(this.controller == null) {
            }
            else {
                long v0 = this.controller.getCallDuration();
                this.lastKnownDuration = v0;
                return v0;
            }
        }

        return this.lastKnownDuration;
    }

    public abstract long getCallID();

    public int getCallState() {
        return this.currentState;
    }

    public abstract CallConnection getConnectionAndStartCall();

    public int getCurrentAudioRoute() {
        int v3 = 2;
        if(VoIPBaseService.USE_CONNECTION_SERVICE) {
            if(this.systemCallConnection != null && this.systemCallConnection.getCallAudioState() != null) {
                int v0 = this.systemCallConnection.getCallAudioState().getRoute();
                if(v0 != 4) {
                    if(v0 != 8) {
                        switch(v0) {
                            case 1: {
                                return 0;
                            }
                            case 2: {
                                return v3;
                            }
                        }

                        goto label_22;
                        return v3;
                    }
                    else {
                        return 1;
                    }
                }

                return 0;
            }

        label_22:
            return this.audioRouteToSet;
        }

        if(this.audioConfigured) {
            Object v0_1 = this.getSystemService("audio");
            if(((AudioManager)v0_1).isBluetoothScoOn()) {
                return v3;
            }

            if(((AudioManager)v0_1).isSpeakerphoneOn()) {
                return 1;
            }

            return 0;
        }

        return this.audioRouteToSet;
    }

    public String getDebugString() {
        return this.controller.getDebugString();
    }

    public int getLastError() {
        return this.lastError;
    }

    protected Bitmap getRoundAvatarBitmap(TLObject arg8) {
        Bitmap v1_1;
        Bitmap v3_2;
        BitmapFactory$Options v4_1;
        BitmapDrawable v4;
        TLObject v3;
        boolean v0 = arg8 instanceof User;
        String v1 = null;
        if(v0) {
            v3 = arg8;
            if(((User)v3).photo == null) {
                goto label_54;
            }
            else if(((User)v3).photo.photo_small != null) {
                v4 = ImageLoader.getInstance().getImageFromMemory(((User)v3).photo.photo_small, v1, "50_50");
                if(v4 != null) {
                    goto label_41;
                }
                else {
                    try {
                        v4_1 = new BitmapFactory$Options();
                        v4_1.inMutable = true;
                        v3_2 = BitmapFactory.decodeFile(FileLoader.getPathToAttach(((User)v3).photo.photo_small, true).toString(), v4_1);
                    }
                    catch(Throwable v3_1) {
                        goto label_27;
                    }
                }
            }
            else {
                goto label_54;
            }
        }
        else {
            v3 = arg8;
            if(((Chat)v3).photo == null) {
                goto label_54;
            }
            else if(((Chat)v3).photo.photo_small != null) {
                v4 = ImageLoader.getInstance().getImageFromMemory(((Chat)v3).photo.photo_small, v1, "50_50");
                if(v4 != null) {
                label_41:
                    v1_1 = v4.getBitmap().copy(Bitmap$Config.ARGB_8888, true);
                    goto label_54;
                }
                else {
                    try {
                        v4_1 = new BitmapFactory$Options();
                        v4_1.inMutable = true;
                        v3_2 = BitmapFactory.decodeFile(FileLoader.getPathToAttach(((Chat)v3).photo.photo_small, true).toString(), v4_1);
                    }
                    catch(Throwable v3_1) {
                    label_27:
                        FileLog.e(v3_1);
                        goto label_54;
                    }
                }
            }
            else {
                goto label_54;
            }
        }

        v1_1 = v3_2;
    label_54:
        if(v1_1 == null) {
            Theme.createDialogsResources(((Context)this));
            AvatarDrawable v0_1 = v0 ? new AvatarDrawable(((User)arg8)) : new AvatarDrawable(((Chat)arg8));
            v1_1 = Bitmap.createBitmap(AndroidUtilities.dp(42f), AndroidUtilities.dp(42f), Bitmap$Config.ARGB_8888);
            v0_1.setBounds(0, 0, v1_1.getWidth(), v1_1.getHeight());
            v0_1.draw(new Canvas(v1_1));
        }

        Canvas v8 = new Canvas(v1_1);
        Path v0_2 = new Path();
        v0_2.addCircle(((float)(v1_1.getWidth() / 2)), ((float)(v1_1.getHeight() / 2)), ((float)(v1_1.getWidth() / 2)), Path$Direction.CW);
        v0_2.toggleInverseFillType();
        Paint v3_3 = new Paint(1);
        v3_3.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
        v8.drawPath(v0_2, v3_3);
        return v1_1;
    }

    public static VoIPBaseService getSharedInstance() {
        return VoIPBaseService.sharedInstance;
    }

    protected int getStatsNetworkType() {
        int v0;
        if(this.lastNetInfo == null || this.lastNetInfo.getType() != 0) {
            v0 = 1;
        }
        else if(this.lastNetInfo.isRoaming()) {
            v0 = 2;
        }
        else {
            v0 = 0;
        }

        return v0;
    }

    protected abstract Class getUIActivityClass();

    public void handleNotificationAction(Intent arg4) {
        StringBuilder v0 = new StringBuilder();
        v0.append(this.getPackageName());
        v0.append(".END_CALL");
        if(v0.toString().equals(arg4.getAction())) {
            this.stopForeground(true);
            this.hangUp();
        }
        else {
            v0 = new StringBuilder();
            v0.append(this.getPackageName());
            v0.append(".DECLINE_CALL");
            if(v0.toString().equals(arg4.getAction())) {
                this.stopForeground(true);
                this.declineIncomingCall(4, null);
            }
            else {
                v0 = new StringBuilder();
                v0.append(this.getPackageName());
                v0.append(".ANSWER_CALL");
                if(v0.toString().equals(arg4.getAction())) {
                    this.acceptIncomingCallFromNotification();
                }
            }
        }
    }

    public abstract void hangUp();

    public abstract void hangUp(Runnable arg1);

    public boolean hasEarpiece() {
        boolean v1 = false;
        if((VoIPBaseService.USE_CONNECTION_SERVICE) && this.systemCallConnection != null && this.systemCallConnection.getCallAudioState() != null) {
            if((this.systemCallConnection.getCallAudioState().getSupportedRouteMask() & 5) != 0) {
                v1 = true;
            }

            return v1;
        }

        if(this.getSystemService("phone").getPhoneType() != 0) {
            return 1;
        }

        if(this.mHasEarpiece == null) {
            try {
                Object v0_1 = this.getSystemService("audio");
                Method v3 = AudioManager.class.getMethod("getDevicesForStream", Integer.TYPE);
                int v4 = AudioManager.class.getField("DEVICE_OUT_EARPIECE").getInt(null);
                Boolean v0_2 = (v3.invoke(v0_1, Integer.valueOf(0)).intValue() & v4) == v4 ? Boolean.TRUE : Boolean.FALSE;
                this.mHasEarpiece = v0_2;
            }
            catch(Throwable v0) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("Error while checking earpiece! ", v0);
                }

                this.mHasEarpiece = Boolean.TRUE;
            }
        }

        return this.mHasEarpiece.booleanValue();
    }

    protected void initializeAccountRelatedThings() {
        this.updateServerConfig();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.appDidLogout);
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
        this.controller = this.createController();
        this.controller.setConnectionStateListener(((ConnectionStateListener)this));
    }

    public static boolean isAnyKindOfCallActive() {
        boolean v1 = false;
        if(VoIPService.getSharedInstance() != null && VoIPService.getSharedInstance().getCallState() != 15) {
            v1 = true;
        }

        return v1;
    }

    public boolean isBluetoothHeadsetConnected() {
        if((VoIPBaseService.USE_CONNECTION_SERVICE) && this.systemCallConnection != null && this.systemCallConnection.getCallAudioState() != null) {
            boolean v0 = (this.systemCallConnection.getCallAudioState().getSupportedRouteMask() & 2) != 0 ? true : false;
            return v0;
        }

        return this.isBtHeadsetConnected;
    }

    private static boolean isDeviceCompatibleWithConnectionServiceAPI() {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT < 26) {
            return 0;
        }

        if(("angler".equals(Build.PRODUCT)) || ("bullhead".equals(Build.PRODUCT)) || ("sailfish".equals(Build.PRODUCT)) || ("marlin".equals(Build.PRODUCT)) || ("walleye".equals(Build.PRODUCT)) || ("taimen".equals(Build.PRODUCT)) || (MessagesController.getGlobalMainSettings().getBoolean("dbg_force_connection_service", false))) {
            v1 = true;
        }

        return v1;
    }

    protected boolean isFinished() {
        boolean v0 = this.currentState == 11 || this.currentState == 4 ? true : false;
        return v0;
    }

    public boolean isMicMute() {
        return this.micMute;
    }

    public boolean isOutgoing() {
        return this.isOutgoing;
    }

    protected boolean isRinging() {
        return 0;
    }

    public boolean isSpeakerphoneOn() {
        if((VoIPBaseService.USE_CONNECTION_SERVICE) && this.systemCallConnection != null && this.systemCallConnection.getCallAudioState() != null) {
            int v0 = this.systemCallConnection.getCallAudioState().getRoute();
            boolean v2 = false;
            if(this.hasEarpiece()) {
                if(v0 != 8) {
                    return v2;
                }

                goto label_16;
            }
            else if(v0 == 2) {
            label_16:
                v2 = true;
            }

            return v2;
        }

        if((this.audioConfigured) && !VoIPBaseService.USE_CONNECTION_SERVICE) {
            Object v0_1 = this.getSystemService("audio");
            boolean v0_2 = this.hasEarpiece() ? ((AudioManager)v0_1).isSpeakerphoneOn() : ((AudioManager)v0_1).isBluetoothScoOn();
            return v0_2;
        }

        return this.speakerphoneStateToSet;
    }

    public void onAccuracyChanged(Sensor arg1, int arg2) {
    }

    public void onAudioFocusChange(int arg2) {
        this.haveAudioFocus = arg2 == 1 ? true : false;
    }

    public void onConnectionStateChanged(int arg10) {
        if(arg10 == 4) {
            this.callFailed();
            return;
        }

        if(arg10 == 3) {
            if(this.spPlayID != 0) {
                this.soundPool.stop(this.spPlayID);
                this.spPlayID = 0;
            }

            if(this.wasEstablished) {
                goto label_40;
            }

            this.wasEstablished = true;
            if(!this.isProximityNear) {
                Object v2 = this.getSystemService("vibrator");
                if(((Vibrator)v2).hasVibrator()) {
                    ((Vibrator)v2).vibrate(100);
                }
            }

            AndroidUtilities.runOnUIThread(new Runnable() {
                public void run() {
                    if(VoIPBaseService.this.controller == null) {
                        return;
                    }

                    StatsController.getInstance(VoIPBaseService.this.currentAccount).incrementTotalCallsTime(VoIPBaseService.this.getStatsNetworkType(), 5);
                    AndroidUtilities.runOnUIThread(((Runnable)this), 5000);
                }
            }, 5000);
            if(this.isOutgoing) {
                StatsController.getInstance(this.currentAccount).incrementSentItemsCount(this.getStatsNetworkType(), 0, 1);
                goto label_40;
            }

            StatsController.getInstance(this.currentAccount).incrementReceivedItemsCount(this.getStatsNetworkType(), 0, 1);
        }

    label_40:
        if(arg10 == 5) {
            if(this.spPlayID != 0) {
                this.soundPool.stop(this.spPlayID);
            }

            this.spPlayID = this.soundPool.play(this.spConnectingId, 1f, 1f, 0, -1, 1f);
        }

        this.dispatchStateChanged(arg10);
    }

    protected void onControllerPreRelease() {
    }

    public void onCreate() {
        super.onCreate();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("=============== VoIPService STARTING ===============");
        }

        Object v0 = this.getSystemService("audio");
        int v3 = 2;
        int v1 = Build$VERSION.SDK_INT < 17 || ((AudioManager)v0).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER") == null ? AudioTrack.getMinBufferSize(48000, 4, v3) / v3 : Integer.parseInt(((AudioManager)v0).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER"));
        VoIPController.setNativeBufferSize(v1);
        try {
            this.cpuWakelock = this.getSystemService("power").newWakeLock(1, "telegram-voip");
            this.cpuWakelock.acquire();
            BluetoothAdapter v1_1 = ((AudioManager)v0).isBluetoothScoAvailableOffCall() ? BluetoothAdapter.getDefaultAdapter() : null;
            this.btAdapter = v1_1;
            IntentFilter v1_2 = new IntentFilter();
            v1_2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            if(!VoIPBaseService.USE_CONNECTION_SERVICE) {
                v1_2.addAction("android.intent.action.HEADSET_PLUG");
                if(this.btAdapter != null) {
                    v1_2.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                    v1_2.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
                }

                v1_2.addAction("android.intent.action.PHONE_STATE");
            }

            this.registerReceiver(this.receiver, v1_2);
            boolean v2 = false;
            this.soundPool = new SoundPool(1, 0, 0);
            this.spConnectingId = this.soundPool.load(((Context)this), 2131558411, 1);
            this.spRingbackID = this.soundPool.load(((Context)this), 2131558414, 1);
            this.spFailedID = this.soundPool.load(((Context)this), 2131558413, 1);
            this.spEndId = this.soundPool.load(((Context)this), 2131558412, 1);
            this.spBusyId = this.soundPool.load(((Context)this), 2131558410, 1);
            ((AudioManager)v0).registerMediaButtonEventReceiver(new ComponentName(((Context)this), VoIPMediaButtonReceiver.class));
            if(VoIPBaseService.USE_CONNECTION_SERVICE) {
                return;
            }

            if(this.btAdapter == null) {
                return;
            }

            if(!this.btAdapter.isEnabled()) {
                return;
            }

            if(this.btAdapter.getProfileConnectionState(1) == v3) {
                v2 = true;
            }

            this.updateBluetoothHeadsetState(v2);
            Iterator v0_2 = this.stateListeners.iterator();
            while(v0_2.hasNext()) {
                v0_2.next().onAudioSettingsChanged();
            }
        }
        catch(Exception v0_1) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("error initializing voip controller", ((Throwable)v0_1));
            }

            this.callFailed();
        }
    }

    public void onDestroy() {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("=============== VoIPService STOPPING ===============");
        }

        this.stopForeground(true);
        this.stopRinging();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.appDidLogout);
        Object v1 = this.getSystemService("sensor");
        if(((SensorManager)v1).getDefaultSensor(8) != null) {
            ((SensorManager)v1).unregisterListener(((SensorEventListener)this));
        }

        if(this.proximityWakelock != null && (this.proximityWakelock.isHeld())) {
            this.proximityWakelock.release();
        }

        this.unregisterReceiver(this.receiver);
        Runnable v2 = null;
        if(this.timeoutRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.timeoutRunnable);
            this.timeoutRunnable = v2;
        }

        super.onDestroy();
        VoIPBaseService.sharedInstance = ((VoIPBaseService)v2);
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didEndedCall, new Object[0]);
            }
        });
        if(this.controller != null && (this.controllerStarted)) {
            this.lastKnownDuration = this.controller.getCallDuration();
            this.updateStats();
            StatsController.getInstance(this.currentAccount).incrementTotalCallsTime(this.getStatsNetworkType(), (((int)(this.lastKnownDuration / 1000))) % 5);
            this.onControllerPreRelease();
            this.controller.release();
            this.controller = ((VoIPController)v2);
        }

        this.cpuWakelock.release();
        v1 = this.getSystemService("audio");
        if(!VoIPBaseService.USE_CONNECTION_SERVICE) {
            if((this.isBtHeadsetConnected) && !this.playingSound) {
                ((AudioManager)v1).stopBluetoothSco();
                ((AudioManager)v1).setSpeakerphoneOn(false);
            }

            try {
                ((AudioManager)v1).setMode(0);
            }
            catch(SecurityException v2_1) {
                if(!BuildVars.LOGS_ENABLED) {
                    goto label_78;
                }

                FileLog.e("Error setting audio more to normal", ((Throwable)v2_1));
            }

        label_78:
            ((AudioManager)v1).abandonAudioFocus(((AudioManager$OnAudioFocusChangeListener)this));
        }

        ((AudioManager)v1).unregisterMediaButtonEventReceiver(new ComponentName(((Context)this), VoIPMediaButtonReceiver.class));
        if(this.haveAudioFocus) {
            ((AudioManager)v1).abandonAudioFocus(((AudioManager$OnAudioFocusChangeListener)this));
        }

        if(!this.playingSound) {
            this.soundPool.release();
        }

        if((VoIPBaseService.USE_CONNECTION_SERVICE) && this.systemCallConnection != null && !this.playingSound) {
            this.systemCallConnection.destroy();
        }

        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(true, false);
        VoIPHelper.lastCallTime = System.currentTimeMillis();
    }

    public void onSensorChanged(SensorEvent arg4) {
        // Method was not decompiled
    }

    public void onSignalBarCountChanged(int arg3) {
        this.signalBarCount = arg3;
        int v0;
        for(v0 = 0; v0 < this.stateListeners.size(); ++v0) {
            this.stateListeners.get(v0).onSignalBarsCountChanged(arg3);
        }
    }

    public void registerStateListener(StateListener arg2) {
        this.stateListeners.add(arg2);
        if(this.currentState != 0) {
            arg2.onStateChanged(this.currentState);
        }

        if(this.signalBarCount != 0) {
            arg2.onSignalBarsCountChanged(this.signalBarCount);
        }
    }

    public void setMicMute(boolean arg2) {
        this.micMute = arg2;
        if(this.controller != null) {
            this.controller.setMicMute(arg2);
        }
    }

    protected void showIncomingNotification(String arg17, CharSequence arg18, TLObject arg19, List arg20, int arg21, Class arg22) {
        User v1_1;
        String v12_2;
        int v7_1;
        int v12;
        VoIPBaseService v0 = this;
        String v1 = arg17;
        CharSequence v2 = arg18;
        Intent v3 = new Intent(((Context)v0), arg22);
        v3.addFlags(805306368);
        int v6 = 2131626381;
        Notification$Builder v4 = new Notification$Builder(((Context)v0)).setContentTitle(LocaleController.getString("VoipInCallBranding", v6)).setContentText(((CharSequence)v1)).setSmallIcon(2131231416).setSubText(v2).setContentIntent(PendingIntent.getActivity(((Context)v0), 0, v3, 0));
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
                        goto label_63;
                    }
                }

                FileLog.d("User messed up the notification channel; deleting it and creating a proper one");
                ((NotificationManager)v10).deleteNotificationChannel("incoming_calls" + v9);
                ++v9;
                v7.edit().putInt("calls_notification_channel", v9).commit();
                goto label_62;
            }
            else {
            label_62:
                v7_1 = 1;
            }

        label_63:
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

            v4.setChannelId("incoming_calls" + v9);
        }

        Intent v7_4 = new Intent(((Context)v0), VoIPActionsReceiver.class);
        v7_4.setAction(this.getPackageName() + ".DECLINE_CALL");
        v7_4.putExtra("call_id", this.getCallID());
        String v9_2 = LocaleController.getString("VoipDeclineCall", 2131626373);
        v12 = 24;
        if(Build$VERSION.SDK_INT >= v12) {
            SpannableString v11_2 = new SpannableString(((CharSequence)v9_2));
            v11_2.setSpan(new ForegroundColorSpan(-769226), 0, ((CharSequence)v11_2).length(), 0);
            SpannableString v9_3 = v11_2;
        }

        int v11_3 = 268435456;
        PendingIntent v7_5 = PendingIntent.getBroadcast(((Context)v0), 0, v7_4, v11_3);
        v4.addAction(2131231135, ((CharSequence)v9_2), v7_5);
        Intent v9_4 = new Intent(((Context)v0), VoIPActionsReceiver.class);
        v9_4.setAction(this.getPackageName() + ".ANSWER_CALL");
        v9_4.putExtra("call_id", this.getCallID());
        int v14 = 2131626365;
        String v13_1 = LocaleController.getString("VoipAnswerCall", v14);
        if(Build$VERSION.SDK_INT >= v12) {
            SpannableString v12_1 = new SpannableString(((CharSequence)v13_1));
            v12_1.setSpan(new ForegroundColorSpan(-16733696), 0, ((CharSequence)v12_1).length(), 0);
        }
        else {
            v12_2 = v13_1;
        }

        PendingIntent v9_5 = PendingIntent.getBroadcast(((Context)v0), 0, v9_4, v11_3);
        v4.addAction(2131231144, ((CharSequence)v12_2), v9_5);
        v4.setPriority(2);
        if(Build$VERSION.SDK_INT >= 17) {
            v4.setShowWhen(false);
        }

        v11_3 = 21;
        if(Build$VERSION.SDK_INT >= v11_3) {
            v4.setColor(-13851168);
            v4.setVibrate(new long[0]);
            v4.setCategory("call");
            v4.setFullScreenIntent(PendingIntent.getActivity(((Context)v0), 0, v3, 0), true);
        }

        Notification v3_1 = v4.getNotification();
        if(Build$VERSION.SDK_INT >= v11_3) {
            String v10_1 = this.getPackageName();
            v11_3 = LocaleController.isRTL ? 2131492952 : 2131492951;
            RemoteViews v4_1 = new RemoteViews(v10_1, v11_3);
            v4_1.setTextViewText(2131296744, ((CharSequence)v1));
            int v10_2 = 8;
            v11_3 = 2131296968;
            v12 = 2131296924;
            if(TextUtils.isEmpty(arg18)) {
                v4_1.setViewVisibility(v12, v10_2);
                if(UserConfig.getActivatedAccountsCount() > 1) {
                    v1_1 = UserConfig.getInstance(v0.currentAccount).getCurrentUser();
                    v1 = LocaleController.formatString("VoipInCallBrandingWithName", 2131626382, new Object[]{ContactsController.formatName(v1_1.first_name, v1_1.last_name)});
                }
                else {
                    v1 = LocaleController.getString("VoipInCallBranding", v6);
                }

                v4_1.setTextViewText(v11_3, ((CharSequence)v1));
            }
            else {
                if(UserConfig.getActivatedAccountsCount() > 1) {
                    v1_1 = UserConfig.getInstance(v0.currentAccount).getCurrentUser();
                    v4_1.setTextViewText(v12, LocaleController.formatString("VoipAnsweringAsAccount", 2131626366, new Object[]{ContactsController.formatName(v1_1.first_name, v1_1.last_name)}));
                }
                else {
                    v4_1.setViewVisibility(v12, v10_2);
                }

                v4_1.setTextViewText(v11_3, v2);
            }

            v4_1.setTextViewText(2131296306, LocaleController.getString("VoipAnswerCall", v14));
            v4_1.setTextViewText(2131296430, LocaleController.getString("VoipDeclineCall", 2131626373));
            v4_1.setImageViewBitmap(2131296782, v0.getRoundAvatarBitmap(arg19));
            v4_1.setOnClickPendingIntent(2131296305, v9_5);
            v4_1.setOnClickPendingIntent(2131296429, v7_5);
            v3_1.bigContentView = v4_1;
            v3_1.headsUpContentView = v4_1;
        }

        v0.startForeground(202, v3_1);
    }

    protected abstract void showNotification();

    protected void showNotification(String arg5, FileLocation arg6, Class arg7) {
        // Method was not decompiled
    }

    protected abstract void startRinging();

    protected void startRingtoneAndVibration(int arg9) {
        String v6;
        String v2_3;
        StringBuilder v2_2;
        int v5;
        SharedPreferences v0 = MessagesController.getNotificationsSettings(this.currentAccount);
        Object v1 = this.getSystemService("audio");
        int v2 = ((AudioManager)v1).getRingerMode() != 0 ? 1 : 0;
        if(Build$VERSION.SDK_INT >= 21) {
            try {
                v5 = Settings$Global.getInt(this.getContentResolver(), "zen_mode");
                if(v2 != 0) {
                    goto label_18;
                }
            }
            catch(Exception ) {
            }

            goto label_22;
        label_18:
            v2 = v5 == 0 ? 1 : 0;
        }

    label_22:
        if(v2 != 0) {
            v5 = 2;
            if(!VoIPBaseService.USE_CONNECTION_SERVICE) {
                ((AudioManager)v1).requestAudioFocus(((AudioManager$OnAudioFocusChangeListener)this), v5, 1);
            }

            this.ringtonePlayer = new MediaPlayer();
            this.ringtonePlayer.setOnPreparedListener(new MediaPlayer$OnPreparedListener() {
                public void onPrepared(MediaPlayer arg1) {
                    VoIPBaseService.this.ringtonePlayer.start();
                }
            });
            this.ringtonePlayer.setLooping(true);
            this.ringtonePlayer.setAudioStreamType(v5);
            try {
                v2_2 = new StringBuilder();
                v2_2.append("custom_");
                v2_2.append(arg9);
                if(v0.getBoolean(v2_2.toString(), false)) {
                    v2_3 = "ringtone_path_" + arg9;
                    v6 = RingtoneManager.getDefaultUri(1).toString();
                }
                else {
                    v2_3 = "CallsRingtonePath";
                    v6 = RingtoneManager.getDefaultUri(1).toString();
                }

                v2_3 = v0.getString(v2_3, v6);
                this.ringtonePlayer.setDataSource(((Context)this), Uri.parse(v2_3));
                this.ringtonePlayer.prepareAsync();
            }
            catch(Exception v2_1) {
                FileLog.e(((Throwable)v2_1));
                if(this.ringtonePlayer == null) {
                    goto label_74;
                }

                this.ringtonePlayer.release();
                this.ringtonePlayer = null;
            }

        label_74:
            v2_2 = new StringBuilder();
            v2_2.append("custom_");
            v2_2.append(arg9);
            String v9 = v0.getBoolean(v2_2.toString(), false) ? "calls_vibrate_" + arg9 : "vibrate_calls";
            arg9 = v0.getInt(v9, 0);
            int v0_1 = 4;
            if(arg9 == v5 || arg9 == v0_1 || ((AudioManager)v1).getRingerMode() != 1 && ((AudioManager)v1).getRingerMode() != v5) {
                if(arg9 != v0_1) {
                }
                else if(((AudioManager)v1).getRingerMode() == 1) {
                    goto label_102;
                }

                return;
            }

        label_102:
            this.vibrator = this.getSystemService("vibrator");
            long v0_2 = 700;
            v2 = 3;
            if(arg9 == 1) {
                v0_2 = 350;
            }
            else if(arg9 == v2) {
                v0_2 = 1400;
            }

            Vibrator v9_1 = this.vibrator;
            long[] v2_4 = new long[v2];
            v2_4[0] = 0;
            v2_4[1] = v0_2;
            v2_4[v5] = 500;
            v9_1.vibrate(v2_4, 0);
        }
    }

    public void stopRinging() {
        MediaPlayer v1 = null;
        if(this.ringtonePlayer != null) {
            this.ringtonePlayer.stop();
            this.ringtonePlayer.release();
            this.ringtonePlayer = v1;
        }

        if(this.vibrator != null) {
            this.vibrator.cancel();
            this.vibrator = ((Vibrator)v1);
        }
    }

    public void toggleSpeakerphoneOrShowRouteSheet(Activity arg8) {
        CallConnection v8_2;
        int v1 = 2;
        if((this.isBluetoothHeadsetConnected()) && (this.hasEarpiece())) {
            Builder v0 = new Builder(((Context)arg8));
            CharSequence[] v3 = new CharSequence[3];
            int v5 = 0;
            v3[0] = LocaleController.getString("VoipAudioRoutingBluetooth", 2131626367);
            v3[1] = LocaleController.getString("VoipAudioRoutingEarpiece", 2131626368);
            v3[v1] = LocaleController.getString("VoipAudioRoutingSpeaker", 2131626369);
            BottomSheet v8 = v0.setItems(v3, new int[]{2131231130, 2131231216, 2131231265}, new DialogInterface$OnClickListener() {
                public void onClick(DialogInterface arg5, int arg6) {
                    Object v5 = VoIPBaseService.this.getSystemService("audio");
                    if(VoIPBaseService.getSharedInstance() == null) {
                        return;
                    }

                    int v1 = 2;
                    if(!VoIPBaseService.USE_CONNECTION_SERVICE || VoIPBaseService.this.systemCallConnection == null) {
                        if((VoIPBaseService.this.audioConfigured) && !VoIPBaseService.USE_CONNECTION_SERVICE) {
                            switch(arg6) {
                                case 0: {
                                    if(!VoIPBaseService.this.bluetoothScoActive) {
                                        VoIPBaseService.this.needSwitchToBluetoothAfterScoActivates = true;
                                        try {
                                            ((AudioManager)v5).startBluetoothSco();
                                        }
                                        catch(Throwable ) {
                                        }
                                    }
                                    else {
                                        ((AudioManager)v5).setBluetoothScoOn(true);
                                        ((AudioManager)v5).setSpeakerphoneOn(false);
                                    }

                                    break;
                                }
                                case 1: {
                                    if(VoIPBaseService.this.bluetoothScoActive) {
                                        ((AudioManager)v5).stopBluetoothSco();
                                    }

                                    ((AudioManager)v5).setSpeakerphoneOn(false);
                                    ((AudioManager)v5).setBluetoothScoOn(false);
                                    break;
                                }
                                case 2: {
                                    if(VoIPBaseService.this.bluetoothScoActive) {
                                        ((AudioManager)v5).stopBluetoothSco();
                                    }

                                    ((AudioManager)v5).setBluetoothScoOn(false);
                                    ((AudioManager)v5).setSpeakerphoneOn(true);
                                    break;
                                }
                                default: {
                                    break;
                                }
                            }

                            VoIPBaseService.this.updateOutputGainControlState();
                            goto label_72;
                        }

                        switch(arg6) {
                            case 0: {
                                goto label_70;
                            }
                            case 1: {
                                goto label_67;
                            }
                            case 2: {
                                goto label_64;
                            }
                        }

                        goto label_72;
                    label_67:
                        VoIPBaseService.this.audioRouteToSet = 0;
                        goto label_72;
                    label_70:
                        VoIPBaseService.this.audioRouteToSet = v1;
                        goto label_72;
                    label_64:
                        VoIPBaseService.this.audioRouteToSet = 1;
                    }
                    else {
                        switch(arg6) {
                            case 0: {
                                goto label_23;
                            }
                            case 1: {
                                goto label_18;
                            }
                            case 2: {
                                goto label_14;
                            }
                        }

                        goto label_72;
                    label_18:
                        CallConnection v5_1 = VoIPBaseService.this.systemCallConnection;
                        arg6 = 5;
                        goto label_21;
                    label_23:
                        VoIPBaseService.this.systemCallConnection.setAudioRoute(v1);
                        goto label_72;
                    label_14:
                        v5_1 = VoIPBaseService.this.systemCallConnection;
                        arg6 = 8;
                    label_21:
                        v5_1.setAudioRoute(arg6);
                    }

                label_72:
                    Iterator v5_2 = VoIPBaseService.this.stateListeners.iterator();
                    while(v5_2.hasNext()) {
                        v5_2.next().onAudioSettingsChanged();
                    }
                }
            }).create();
            v8.setBackgroundColor(-13948117);
            v8.show();
            ViewGroup v8_1 = v8.getSheetContainer();
            while(v5 < v8_1.getChildCount()) {
                v8_1.getChildAt(v5).setTextColor(-1);
                ++v5;
            }

            return;
        }

        if(!VoIPBaseService.USE_CONNECTION_SERVICE || this.systemCallConnection == null || this.systemCallConnection.getCallAudioState() == null) {
            if((this.audioConfigured) && !VoIPBaseService.USE_CONNECTION_SERVICE) {
                Object v8_3 = this.getSystemService("audio");
                if(this.hasEarpiece()) {
                    ((AudioManager)v8_3).setSpeakerphoneOn(((AudioManager)v8_3).isSpeakerphoneOn() ^ 1);
                }
                else {
                    ((AudioManager)v8_3).setBluetoothScoOn(((AudioManager)v8_3).isBluetoothScoOn() ^ 1);
                }

                this.updateOutputGainControlState();
                goto label_88;
            }

            this.speakerphoneStateToSet ^= 1;
        }
        else {
            int v0_1 = 5;
            if(this.hasEarpiece()) {
                v8_2 = this.systemCallConnection;
                if(this.systemCallConnection.getCallAudioState().getRoute() == 8) {
                }
                else {
                    v0_1 = 8;
                }
            }
            else {
                v8_2 = this.systemCallConnection;
                if(this.systemCallConnection.getCallAudioState().getRoute() == v1) {
                }
                else {
                    v0_1 = 2;
                }
            }

            v8_2.setAudioRoute(v0_1);
        }

    label_88:
        Iterator v8_4 = this.stateListeners.iterator();
        while(v8_4.hasNext()) {
            v8_4.next().onAudioSettingsChanged();
        }
    }

    public void unregisterStateListener(StateListener arg2) {
        this.stateListeners.remove(arg2);
    }

    protected void updateBluetoothHeadsetState(boolean arg4) {
        if(arg4 == this.isBtHeadsetConnected) {
            return;
        }

        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("updateBluetoothHeadsetState: " + arg4);
        }

        this.isBtHeadsetConnected = arg4;
        Object v0_1 = this.getSystemService("audio");
        if(!arg4 || (this.isRinging()) || this.currentState == 0) {
            this.bluetoothScoActive = false;
        }
        else if(this.bluetoothScoActive) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("SCO already active, setting audio routing");
            }

            ((AudioManager)v0_1).setSpeakerphoneOn(false);
            ((AudioManager)v0_1).setBluetoothScoOn(true);
        }
        else {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("startBluetoothSco");
            }

            this.needSwitchToBluetoothAfterScoActivates = true;
            AndroidUtilities.runOnUIThread(new Runnable(((AudioManager)v0_1)) {
                public void run() {
                    try {
                        this.val$am.startBluetoothSco();
                        return;
                    }
                    catch(Throwable ) {
                        return;
                    }
                }
            }, 500);
        }

        Iterator v4 = this.stateListeners.iterator();
        while(v4.hasNext()) {
            v4.next().onAudioSettingsChanged();
        }
    }

    protected void updateNetworkType() {
        int v0_1;
        NetworkInfo v0 = this.getSystemService("connectivity").getActiveNetworkInfo();
        this.lastNetInfo = v0;
        if(v0 != null) {
            int v1 = v0.getType();
            if(v1 != 9) {
                switch(v1) {
                    case 0: {
                        goto label_12;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                goto label_28;
            label_10:
                v0_1 = 6;
                goto label_29;
            label_12:
                switch(v0.getSubtype()) {
                    case 1: {
                        goto label_24;
                    }
                    case 3: 
                    case 5: {
                        goto label_20;
                    }
                    case 2: 
                    case 7: {
                        goto label_22;
                    }
                    case 13: {
                        goto label_16;
                    }
                    case 6: 
                    case 8: 
                    case 9: 
                    case 10: 
                    case 12: 
                    case 15: {
                        goto label_18;
                    }
                }

                v0_1 = 11;
                goto label_29;
            label_16:
                v0_1 = 5;
                goto label_29;
            label_18:
                v0_1 = 4;
                goto label_29;
            label_20:
                v0_1 = 3;
                goto label_29;
            label_22:
                v0_1 = 2;
                goto label_29;
            label_24:
                v0_1 = 1;
            }
            else {
                v0_1 = 7;
            }
        }
        else {
        label_28:
            v0_1 = 0;
        }

    label_29:
        if(this.controller != null) {
            this.controller.setNetworkType(v0_1);
        }
    }

    public void updateOutputGainControlState() {
        if(this.controller != null) {
            if(!this.controllerStarted) {
            }
            else {
                int v1 = 0;
                if(!VoIPBaseService.USE_CONNECTION_SERVICE) {
                    Object v0 = this.getSystemService("audio");
                    VoIPController v3 = this.controller;
                    boolean v4 = !this.hasEarpiece() || (((AudioManager)v0).isSpeakerphoneOn()) || (((AudioManager)v0).isBluetoothScoOn()) || (this.isHeadsetPlugged) ? false : true;
                    v3.setAudioOutputGainControlEnabled(v4);
                    v3 = this.controller;
                    if(!this.isHeadsetPlugged && (!this.hasEarpiece() || (((AudioManager)v0).isSpeakerphoneOn()) || (((AudioManager)v0).isBluetoothScoOn()) || (this.isHeadsetPlugged))) {
                        v1 = 1;
                    }

                    v3.setEchoCancellationStrength(v1);
                }
                else {
                    if(this.systemCallConnection.getCallAudioState().getRoute() == 1) {
                        boolean v1_1 = true;
                    }

                    this.controller.setAudioOutputGainControlEnabled(((boolean)v1));
                    this.controller.setEchoCancellationStrength(v1 ^ 1);
                }
            }
        }
    }

    protected abstract void updateServerConfig();

    protected void updateStats() {
        StatsController v0_1;
        this.controller.getStats(this.stats);
        long v0 = this.stats.bytesSentWifi - this.prevStats.bytesSentWifi;
        long v2 = this.stats.bytesRecvdWifi - this.prevStats.bytesRecvdWifi;
        long v4 = this.stats.bytesSentMobile - this.prevStats.bytesSentMobile;
        long v6 = this.stats.bytesRecvdMobile - this.prevStats.bytesRecvdMobile;
        Stats v8 = this.stats;
        this.stats = this.prevStats;
        this.prevStats = v8;
        long v8_1 = 0;
        if(Long.compare(v0, v8_1) > 0) {
            StatsController.getInstance(this.currentAccount).incrementSentBytesCount(1, 0, v0);
        }

        if(v2 > v8_1) {
            StatsController.getInstance(this.currentAccount).incrementReceivedBytesCount(1, 0, v2);
        }

        int v1 = 2;
        if(Long.compare(v4, v8_1) > 0) {
            v0_1 = StatsController.getInstance(this.currentAccount);
            int v2_1 = this.lastNetInfo == null || !this.lastNetInfo.isRoaming() ? 0 : 2;
            v0_1.incrementSentBytesCount(v2_1, 0, v4);
        }

        if(v6 > v8_1) {
            v0_1 = StatsController.getInstance(this.currentAccount);
            if(this.lastNetInfo == null || !this.lastNetInfo.isRoaming()) {
                v1 = 0;
            }
            else {
            }

            v0_1.incrementReceivedBytesCount(v1, 0, v6);
        }
    }
}

