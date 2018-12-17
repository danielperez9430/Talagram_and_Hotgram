package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences$Editor;
import android.content.res.Configuration;
import android.net.sip.SipProfile;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.google.a.f;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import f.l;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import okhttp3.aa;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAuthInfo;
import org.linphone.core.LinphoneCall$State;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallStats;
import org.linphone.core.LinphoneChatMessage;
import org.linphone.core.LinphoneChatRoom;
import org.linphone.core.LinphoneContent;
import org.linphone.core.LinphoneCore$AuthMethod;
import org.linphone.core.LinphoneCore$EcCalibratorStatus;
import org.linphone.core.LinphoneCore$GlobalState;
import org.linphone.core.LinphoneCore$LogCollectionUploadState;
import org.linphone.core.LinphoneCore$RegistrationState;
import org.linphone.core.LinphoneCore$RemoteProvisioningState;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneEvent;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneFriendList;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.PublishState;
import org.linphone.core.SubscriptionState;
import org.telegram.customization.Model.SettingAndUpdate;
import org.telegram.customization.b.a;
import org.telegram.customization.g.e;
import org.telegram.customization.i.h;
import org.telegram.customization.i.i;
import org.telegram.customization.i.j;
import org.telegram.customization.j.b.b;
import org.telegram.customization.j.c.k;
import org.telegram.customization.util.a.d;
import org.telegram.customization.util.a.g;
import org.telegram.customization.voip.LinphoneSipWrapper$SipCallback;
import org.telegram.customization.voip.LinphoneSipWrapper$SipManagerState;
import org.telegram.customization.voip.LinphoneSipWrapper;
import org.telegram.customization.voip.linphoneSip.linphone.LinphoneManager;
import org.telegram.messenger.voip.SlsVoIPService;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ForegroundDetector;
import org.telegram.ui.SlsVoIPActivity;
import utils.c;

public class ApplicationLoader extends a implements LinphoneCoreListener {
    class org.telegram.messenger.ApplicationLoader$1 implements SipCallback {
        org.telegram.messenger.ApplicationLoader$1(ApplicationLoader arg1) {
            ApplicationLoader.this = arg1;
            super();
        }

        public void hideAnswer() {
        }

        public void onSipManagerStateChanged(SipManagerState arg4) {
            Log.d("alireza", "alireza onSipManagerStateChanged" + arg4);
        }

        public void onSipStateChanged(int arg3, int arg4) {
            Log.d("alireza", "alireza onSipStateChanged" + arg4);
            if(arg4 == 16 && ApplicationLoader.this.sipWrapper.getWhatsupNotif() != null) {
                try {
                    User v3 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(Integer.parseInt(ApplicationLoader.this.sipWrapper.getWhatsupNotif().getFromId())));
                    Intent v4 = new Intent(ApplicationLoader.this, SlsVoIPActivity.class);
                    SerializedData v0_1 = new SerializedData();
                    v3.serializeToStream(((AbstractSerializedData)v0_1));
                    v4.addFlags(268435456);
                    v4.putExtra("EXTRA_TELEGRAM_USER", v0_1.toByteArray());
                    ApplicationLoader.this.startActivity(v4);
                    return;
                }
                catch(Throwable ) {
                    return;
                }
            }
        }

        public void showAnswer(SipProfile arg1) {
        }

        public void showCallBtn() {
        }

        public void showEndCallBtn() {
        }
    }

    public static final boolean IS_TEST_MODE = false;
    public j api;
    private i apiMapper;
    @SuppressLint(value={"StaticFieldLeak"}) public static volatile Context applicationContext = null;
    public static volatile Handler applicationHandler = null;
    private static volatile boolean applicationInited = false;
    private static org.telegram.customization.j.b.a component = null;
    public static volatile boolean externalInterfacePaused = true;
    f gson;
    public static volatile boolean isScreenOn = false;
    public static FirebaseAnalytics mFirebaseAnalytics = null;
    public static volatile boolean mainInterfacePaused = true;
    public static volatile boolean mainInterfacePausedStageQueue = true;
    public static volatile long mainInterfacePausedStageQueueTime;
    d prefs;
    SipCallback sipCallback;
    g sipPrefs;
    LinphoneSipWrapper sipWrapper;

    static {
    }

    public ApplicationLoader() {
        super();
        this.sipCallback = new org.telegram.messenger.ApplicationLoader$1(this);
    }

    public void authInfoRequested(LinphoneCore arg1, String arg2, String arg3, String arg4) {
    }

    public void authenticationRequested(LinphoneCore arg1, LinphoneAuthInfo arg2, AuthMethod arg3) {
    }

    public void callEncryptionChanged(LinphoneCore arg1, LinphoneCall arg2, boolean arg3, String arg4) {
    }

    public void callState(LinphoneCore arg4, LinphoneCall arg5, State arg6, String arg7) {
        Log.e("LINPHONE_TAG", "callState: " + arg6.toString() + arg7);
        if(arg6 == State.IncomingReceived && this.sipWrapper.getWhatsupNotif() != null) {
            this.sipWrapper.setLinphoneCore(arg4);
            this.sipWrapper.setlinphoneCall(arg5);
            User v4 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(Integer.parseInt(this.sipWrapper.getWhatsupNotif().getFromId())));
            if(v4 == null) {
                v4 = MessagesStorage.getInstance(UserConfig.selectedAccount).getUser(Integer.parseInt(this.sipWrapper.getWhatsupNotif().getFromId()));
            }

            this.sipWrapper.updateState(15);
            try {
                new org.telegram.customization.j.a.a();
            }
            catch(Exception v7) {
                v7.printStackTrace();
            }

            Intent v7_1 = new Intent(((Context)this), SlsVoIPActivity.class);
            v7_1.putExtra("EXTRA_CALL_INFO", new f().a(this.sipWrapper.getWhatsupNotif()));
            SerializedData v0 = new SerializedData();
            v4.serializeToStream(((AbstractSerializedData)v0));
            v7_1.addFlags(268435456);
            v7_1.putExtra("EXTRA_TELEGRAM_USER", v0.toByteArray());
            this.startActivity(v7_1);
        }

        if(arg6 == State.Connected) {
            this.sipWrapper.setlinphoneCall(arg5);
            this.sipWrapper.updateState(3);
            this.sipWrapper.showEndCallBtn();
            this.api.a(utils.d.a(true), LinphoneSipWrapper.callId, ((long)UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()));
            if(LinphoneSipWrapper.getsPhoneServiceCallback() != null) {
                LinphoneSipWrapper.getsPhoneServiceCallback().callConnected();
            }
        }

        if(arg6 == State.CallEnd) {
            if(LinphoneSipWrapper.getsPhoneServiceCallback() != null) {
                LinphoneSipWrapper.getsPhoneServiceCallback().callReleased();
            }

            this.sipWrapper.updateState(11);
            this.sipWrapper.showCallBtn();
        }

        if(arg6 == State.Error) {
            this.sipWrapper.updateState(17);
            this.sipWrapper.showCallBtn();
        }

        if(arg6 == State.CallReleased) {
            try {
                SlsVoIPService.getSharedInstance().publicCallEnded();
                this.sipWrapper.endIncomingCall(true);
                this.sipWrapper.endCall(true, true);
            }
            catch(Exception v4_1) {
                v4_1.printStackTrace();
            }
        }
    }

    public void callStatsUpdated(LinphoneCore arg1, LinphoneCall arg2, LinphoneCallStats arg3) {
    }

    private boolean checkPlayServices() {
        boolean v0 = true;
        try {
            if(GooglePlayServicesUtil.isGooglePlayServicesAvailable(((Context)this)) != 0) {
                return false;
            }
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
            return 1;
        }

        return v0;
    }

    public void configuringStatus(LinphoneCore arg1, RemoteProvisioningState arg2, String arg3) {
    }

    public void displayMessage(LinphoneCore arg1, String arg2) {
    }

    public void displayStatus(LinphoneCore arg1, String arg2) {
    }

    public void displayWarning(LinphoneCore arg1, String arg2) {
    }

    public void dtmfReceived(LinphoneCore arg1, LinphoneCall arg2, int arg3) {
    }

    public void ecCalibrationStatus(LinphoneCore arg1, EcCalibratorStatus arg2, int arg3, Object arg4) {
    }

    public void fileTransferProgressIndication(LinphoneCore arg1, LinphoneChatMessage arg2, LinphoneContent arg3, int arg4) {
    }

    public void fileTransferRecv(LinphoneCore arg1, LinphoneChatMessage arg2, LinphoneContent arg3, byte[] arg4, int arg5) {
    }

    public int fileTransferSend(LinphoneCore arg1, LinphoneChatMessage arg2, LinphoneContent arg3, ByteBuffer arg4, int arg5) {
        return 0;
    }

    public void friendListCreated(LinphoneCore arg1, LinphoneFriendList arg2) {
    }

    public void friendListRemoved(LinphoneCore arg1, LinphoneFriendList arg2) {
    }

    protected h getApiCallback() {
        return new h() {
            public void getConfigResult(SettingAndUpdate arg1, aa arg2, Object arg3, l arg4) {
                c.a(arg1.getSetting(), ApplicationLoader.this.getApplicationContext());
                org.telegram.customization.Activities.a.d();
            }

            public void getContentFilterResult(ArrayList arg1, aa arg2, Object arg3, l arg4) {
                if(arg1 != null && arg1.size() > 0) {
                    org.telegram.customization.util.j.a(ApplicationLoader.this.getApplicationContext(), new f().a(arg1));
                }
            }

            public void startCallFailure(Object arg1, aa arg2, Object arg3, l arg4) {
                try {
                    Log.d("slsCall startCall", "Failure" + arg1.toString());
                }
                catch(Exception v1) {
                    v1.printStackTrace();
                }
            }

            public void startCallResult(Void arg1, aa arg2, Object arg3, l arg4) {
                try {
                    Log.d("slsCall startCall", "Result" + arg4.a());
                }
                catch(Exception v1) {
                    v1.printStackTrace();
                }
            }
        };
    }

    public static org.telegram.customization.j.b.a getComponent() {
        return ApplicationLoader.component;
    }

    public static File getFilesDirFixed() {
        File v1;
        int v0;
        for(v0 = 0; v0 < 10; ++v0) {
            v1 = ApplicationLoader.applicationContext.getFilesDir();
            if(v1 != null) {
                return v1;
            }
        }

        try {
            v1 = new File(ApplicationLoader.applicationContext.getApplicationInfo().dataDir, "files");
            v1.mkdirs();
            return v1;
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
            return new File("/data/data/org.telegram.messenger/files");
        }
    }

    public void globalState(LinphoneCore arg1, GlobalState arg2, String arg3) {
    }

    public void infoReceived(LinphoneCore arg1, LinphoneCall arg2, LinphoneInfoMessage arg3) {
    }

    private void initPlayServices() {
        AndroidUtilities.runOnUIThread(new -$$Lambda$ApplicationLoader$QowVCFYDODIerZ4nNEu1Q_mQHho(this), 1000);
    }

    public void isComposingReceived(LinphoneCore arg1, LinphoneChatRoom arg2) {
    }

    public static void lambda$initPlayServices$1(ApplicationLoader arg3) {
        if(arg3.checkPlayServices()) {
            String v0 = SharedConfig.pushString;
            if(!TextUtils.isEmpty(((CharSequence)v0))) {
                if(BuildVars.LOGS_ENABLED) {
                    v0 = "GCM regId = " + v0;
                    goto label_13;
                }
            }
            else if(BuildVars.LOGS_ENABLED) {
                v0 = "GCM Registration not found.";
            label_13:
                FileLog.d(v0);
            }

            Utilities.globalQueue.postRunnable(-$$Lambda$ApplicationLoader$kkMcGKlOfnxJrf0M7VsBATqioYc.INSTANCE);
        }
        else {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.d("No valid Google Play Services APK found.");
        }
    }

    static void lambda$null$0() {
        try {
            String v0_1 = FirebaseInstanceId.a().d();
            if(TextUtils.isEmpty(((CharSequence)v0_1))) {
                return;
            }

            GcmInstanceIDListenerService.sendRegistrationToServer(v0_1);
        }
        catch(Throwable v0) {
            FileLog.e(v0);
        }
    }

    public void messageReceived(LinphoneCore arg1, LinphoneChatRoom arg2, LinphoneChatMessage arg3) {
    }

    public void newSubscriptionRequest(LinphoneCore arg1, LinphoneFriend arg2, String arg3) {
    }

    public void notifyPresenceReceived(LinphoneCore arg1, LinphoneFriend arg2) {
    }

    public void notifyReceived(LinphoneCore arg1, LinphoneCall arg2, LinphoneAddress arg3, byte[] arg4) {
    }

    public void notifyReceived(LinphoneCore arg1, LinphoneEvent arg2, String arg3, LinphoneContent arg4) {
    }

    public void onConfigurationChanged(Configuration arg2) {
        super.onConfigurationChanged(arg2);
        try {
            LocaleController.getInstance().onDeviceConfigurationChange(arg2);
            AndroidUtilities.checkDisplaySize(ApplicationLoader.applicationContext, arg2);
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public void onCreate() {
        String v1_2;
        super.onCreate();
        ApplicationLoader.applicationContext = this.getApplicationContext();
        NativeLoader.initNativeLibs(ApplicationLoader.applicationContext);
        ConnectionsManager.native_setJava(false);
        new ForegroundDetector(((Application)this));
        ApplicationLoader.applicationHandler = new Handler(ApplicationLoader.applicationContext.getMainLooper());
        AndroidUtilities.runOnUIThread(-$$Lambda$r7IJ1lCIETKiJtvF21QqYn99iv8.INSTANCE);
        LinphoneManager.destroy();
        ApplicationLoader.component = b.a().a(new org.telegram.customization.j.c.i()).a(new org.telegram.customization.j.c.a(((Application)this))).a(new k()).a();
        ApplicationLoader.getComponent().a(this);
        this.apiMapper = new i(this.getApiCallback());
        this.api.a(this.apiMapper);
        this.sipWrapper.addSipCallback(this.sipCallback);
        if(org.telegram.customization.util.j.e(this.getApplicationContext()).size() == 0) {
            this.api.a();
        }

        if("hotgram".contentEquals("gram")) {
            SharedPreferences$Editor v1 = ApplicationLoader.applicationContext.getSharedPreferences("plusconfig", 0).edit();
            v1.putBoolean("hideTabs", true);
            v1.apply();
        }

        e.a(this.getApplicationContext(), false);
        org.telegram.customization.work.a.a();
        this.sipPrefs.a(3000);
        if(org.telegram.customization.util.j.c(this.getApplicationContext())) {
            LocaleInfo v1_1 = new LocaleInfo();
            v1_1.name = "پارسی";
            v1_1.shortName = "fa";
            v1_1.nameEnglish = "Parsi";
            org.telegram.customization.util.j.a(this.getApplicationContext(), false);
            org.telegram.customization.util.j.a(this.getApplicationContext(), 0);
        }

        org.telegram.customization.i.c.a(this.api);
        if((org.telegram.customization.util.j.d(this.getApplicationContext())) || (org.telegram.customization.util.j.i(this.getApplicationContext()))) {
            if(("hotgram".contentEquals("hotgram")) || ("hotgram".contentEquals("vip"))) {
                v1_2 = "hotgram";
            }
            else if("hotgram".contentEquals("talagram")) {
                v1_2 = "talagram";
            }
            else if("hotgram".contentEquals("mowjgram")) {
                v1_2 = "mowjgram";
            }
            else if("hotgram".contentEquals("gram")) {
                v1_2 = "Default";
            }
            else {
                v1_2 = "Arabgram";
            }

            int v2 = 0;
            try {
                while(true) {
                    if(v2 >= Theme.themes.size()) {
                        break;
                    }
                    else if(Theme.themes.get(v2).name.contentEquals(((CharSequence)v1_2))) {
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    goto label_131;
                }

                v2 = 0;
            label_131:
                Theme.applyTheme(Theme.themes.get(v2));
                org.telegram.customization.util.j.b(this.getApplicationContext(), false);
                org.telegram.customization.util.j.d(this.getApplicationContext(), false);
            }
            catch(Exception v0) {
                v0.printStackTrace();
            }
        }

        this.removePreviousApkFile();
        ApplicationLoader.mFirebaseAnalytics = FirebaseAnalytics.getInstance(((Context)this));
    }

    public static void postInitApplication() {
        if(ApplicationLoader.applicationInited) {
            return;
        }

        ApplicationLoader.applicationInited = true;
        try {
            LocaleController.getInstance();
        }
        catch(Exception v1) {
            v1.printStackTrace();
        }

        try {
            IntentFilter v1_1 = new IntentFilter("android.intent.action.SCREEN_ON");
            v1_1.addAction("android.intent.action.SCREEN_OFF");
            ApplicationLoader.applicationContext.registerReceiver(new ScreenReceiver(), v1_1);
        }
        catch(Exception v1) {
            v1.printStackTrace();
        }

        try {
            ApplicationLoader.isScreenOn = ApplicationLoader.applicationContext.getSystemService("power").isScreenOn();
            if(!BuildVars.LOGS_ENABLED) {
                goto label_39;
            }

            FileLog.d("screen state = " + ApplicationLoader.isScreenOn);
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

    label_39:
        SharedConfig.loadConfig();
        int v1_3 = 0;
        int v2;
        for(v2 = 0; true; ++v2) {
            int v3 = 3;
            if(v2 >= v3) {
                break;
            }

            UserConfig.getInstance(v2).loadConfig();
            MessagesController.getInstance(v2);
            ConnectionsManager.getInstance(v2);
            User v3_1 = UserConfig.getInstance(v2).getCurrentUser();
            if(v3_1 != null) {
                MessagesController.getInstance(v2).putUser(v3_1, true);
                MessagesController.getInstance(v2).getBlockedUsers(true);
                SendMessagesHelper.getInstance(v2).checkUnsentMessages();
            }
        }

        ApplicationLoader.applicationContext.initPlayServices();
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("app initied");
        }

        MediaController.getInstance();
        while(v1_3 < v3) {
            ContactsController.getInstance(v1_3).checkAppAccount();
            DownloadController.getInstance(v1_3);
            ++v1_3;
        }

        WearDataLayerListenerService.updateWatchConnectionState();
    }

    public void publishStateChanged(LinphoneCore arg1, LinphoneEvent arg2, PublishState arg3) {
    }

    public void registrationState(LinphoneCore arg1, LinphoneProxyConfig arg2, RegistrationState arg3, String arg4) {
        Log.d("LINPHONE_TAG", "RegistertionSip " + arg3.toString());
        Log.d("slsCall registration", "RegistertionSip " + arg3.toString());
        if(arg3 == RegistrationState.RegistrationOk) {
            this.sipWrapper.registrationDone();
        }
    }

    private void removePreviousApkFile() {
        File v2;
        StringBuilder v3;
        int v0 = 2131623939;
        String v1 = null;
        try {
            v3 = new StringBuilder();
            v3.append(this.getApplicationContext().getExternalFilesDir(v1));
            v3.append(File.separator);
            v3.append(this.getString(v0));
            v2 = new File(v3.toString());
            if(v2.exists()) {
                v2.delete();
            }

            goto label_17;
        }
        catch(Exception ) {
            try {
            label_17:
                v3 = new StringBuilder();
                v3.append(utils.d.a());
                v3.append(File.separator);
                v3.append(this.getString(v0));
                v2 = new File(v3.toString());
                if(!v2.exists()) {
                    goto label_34;
                }

                v2.delete();
            }
            catch(Exception v0_1) {
                v0_1.printStackTrace();
            }

            try {
            label_34:
                File v0_2 = this.getApplicationContext().getExternalFilesDir(v1);
                if(!v0_2.isDirectory()) {
                    return;
                }

                String[] v1_1 = v0_2.list();
                int v2_1;
                for(v2_1 = 0; v2_1 < v1_1.length; ++v2_1) {
                    new File(v0_2, v1_1[v2_1]).delete();
                }
            }
            catch(Exception v0_1) {
                v0_1.printStackTrace();
            }

            return;
        }
    }

    public void show(LinphoneCore arg1) {
    }

    public static void startPushService() {
        if(MessagesController.getGlobalNotificationsSettings().getBoolean("pushService", true)) {
            try {
                ApplicationLoader.applicationContext.startService(new Intent(ApplicationLoader.applicationContext, NotificationsService.class));
            }
            catch(Throwable ) {
            }
        }
        else {
            ApplicationLoader.stopPushService();
        }
    }

    public static void stopPushService() {
        ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, NotificationsService.class));
        ApplicationLoader.applicationContext.getSystemService("alarm").cancel(PendingIntent.getService(ApplicationLoader.applicationContext, 0, new Intent(ApplicationLoader.applicationContext, NotificationsService.class), 0));
    }

    public void subscriptionStateChanged(LinphoneCore arg1, LinphoneEvent arg2, SubscriptionState arg3) {
    }

    public void transferState(LinphoneCore arg1, LinphoneCall arg2, State arg3) {
    }

    public void uploadProgressIndication(LinphoneCore arg1, int arg2, int arg3) {
    }

    public void uploadStateChanged(LinphoneCore arg1, LogCollectionUploadState arg2, String arg3) {
    }
}

