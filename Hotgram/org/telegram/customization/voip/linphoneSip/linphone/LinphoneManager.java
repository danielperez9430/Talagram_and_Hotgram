package org.telegram.customization.voip.linphoneSip.linphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.res.Resources;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;
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
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreFactory;
import org.linphone.core.LinphoneCoreListener;
import org.linphone.core.LinphoneEvent;
import org.linphone.core.LinphoneFriend;
import org.linphone.core.LinphoneFriendList;
import org.linphone.core.LinphoneInfoMessage;
import org.linphone.core.LinphoneProxyConfig;
import org.linphone.core.PayloadType;
import org.linphone.core.PublishState;
import org.linphone.core.SubscriptionState;
import org.linphone.mediastream.Log;
import org.telegram.customization.util.a.g;
import org.telegram.customization.voip.linphoneSip.utils.LinphoneUtils;
import org.telegram.messenger.ApplicationLoader;

public class LinphoneManager implements LinphoneCoreListener {
    private static final String TAG = "LinphoneManager";
    private static LinphoneManager instance;
    public g linphonePrefs;
    private LinphoneCall mCall;
    private String mChatDatabaseFile;
    private BroadcastReceiver mKeepAliveReceiver;
    private String mLPConfigXsd;
    private LinphoneCore mLc;
    public String mLinphoneConfigFile;
    private String mLinphoneFactoryConfigFile;
    private String mLinphoneRootCaFile;
    private String mPauseSoundFile;
    private Resources mR;
    private String mRingBackSoundFile;
    private String mRingSoundFile;
    private Context mServiceContext;
    private Timer mTimer;
    private static boolean sExited;

    public LinphoneManager(Context arg4) {
        super();
        this.mLPConfigXsd = null;
        this.mLinphoneFactoryConfigFile = null;
        this.mLinphoneConfigFile = null;
        this.mLinphoneRootCaFile = null;
        this.mRingSoundFile = null;
        this.mRingBackSoundFile = null;
        this.mPauseSoundFile = null;
        this.mChatDatabaseFile = null;
        this.mKeepAliveReceiver = new KeepAliveReceiver();
        this.mServiceContext = arg4;
        LinphoneCoreFactory.instance().setDebugMode(true, "huanyutong");
        LinphoneManager.sExited = false;
        ApplicationLoader.getComponent().a(this);
        String v0 = this.mServiceContext.getFilesDir().getAbsolutePath();
        this.mLPConfigXsd = v0 + "/lpconfig.xsd";
        this.mLinphoneFactoryConfigFile = v0 + "/linphonerc";
        this.mLinphoneConfigFile = v0 + "/.linphonerc";
        this.mLinphoneRootCaFile = v0 + "/rootca.pem";
        this.mRingSoundFile = v0 + "/oldphone_mono.wav";
        this.mRingBackSoundFile = v0 + "/ringback.wav";
        this.mPauseSoundFile = v0 + "/toy_mono.wav";
        this.mChatDatabaseFile = v0 + "/linphone-history.db";
        this.mR = arg4.getResources();
    }

    static LinphoneCore access$000(LinphoneManager arg0) {
        return arg0.mLc;
    }

    public void authInfoRequested(LinphoneCore arg1, String arg2, String arg3, String arg4) {
    }

    public void authenticationRequested(LinphoneCore arg1, LinphoneAuthInfo arg2, AuthMethod arg3) {
    }

    public void callEncryptionChanged(LinphoneCore arg1, LinphoneCall arg2, boolean arg3, String arg4) {
    }

    public void callState(LinphoneCore arg1, LinphoneCall arg2, State arg3, String arg4) {
    }

    public void callStatsUpdated(LinphoneCore arg1, LinphoneCall arg2, LinphoneCallStats arg3) {
    }

    public void configuringStatus(LinphoneCore arg1, RemoteProvisioningState arg2, String arg3) {
    }

    private void copyAssetsFromPackage() {
        LinphoneUtils.copyIfNotExist(this.mServiceContext, 2131558404, this.mRingSoundFile);
        LinphoneUtils.copyIfNotExist(this.mServiceContext, 2131558405, this.mRingBackSoundFile);
        LinphoneUtils.copyIfNotExist(this.mServiceContext, 2131558409, this.mPauseSoundFile);
        LinphoneUtils.copyIfNotExist(this.mServiceContext, 2131558401, this.mLinphoneConfigFile);
        LinphoneUtils.copyIfNotExist(this.mServiceContext, 2131558402, new File(this.mLinphoneFactoryConfigFile).getName());
        LinphoneUtils.copyIfNotExist(this.mServiceContext, 2131558403, this.mLPConfigXsd);
        LinphoneUtils.copyIfNotExist(this.mServiceContext, 2131558406, this.mLinphoneRootCaFile);
    }

    public static final LinphoneManager createAndStart(Context arg2) {
        LinphoneManager v2_1;
        Class v0 = LinphoneManager.class;
        __monitor_enter(v0);
        try {
            if(LinphoneManager.instance != null) {
                goto label_12;
            }

            LinphoneManager.instance = new LinphoneManager(arg2);
            LinphoneManager.instance.startLibLinphone(arg2);
            v2_1 = LinphoneManager.instance;
        }
        catch(Throwable v2) {
            goto label_17;
        }

        __monitor_exit(v0);
        return v2_1;
        try {
        label_12:
            throw new RuntimeException("Linphone Manager is already initialized");
        }
        catch(Throwable v2) {
        label_17:
            __monitor_exit(v0);
            throw v2;
        }
    }

    public static void destroy() {
        Class v0 = LinphoneManager.class;
        __monitor_enter(v0);
        try {
            if(LinphoneManager.instance != null) {
                goto label_7;
            }
        }
        catch(Throwable v1) {
            goto label_13;
        }

        __monitor_exit(v0);
        return;
        try {
        label_7:
            LinphoneManager.sExited = true;
            LinphoneManager.instance.doDestroy();
        }
        catch(Throwable v1) {
        label_13:
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
    }

    public void displayMessage(LinphoneCore arg1, String arg2) {
    }

    public void displayStatus(LinphoneCore arg1, String arg2) {
    }

    public void displayWarning(LinphoneCore arg1, String arg2) {
    }

    private void doDestroy() {
        LinphoneCore v0 = null;
        try {
            this.mTimer.cancel();
            this.mLc.destroy();
        }
        catch(Throwable v1) {
        }
        catch(RuntimeException v1_1) {
            try {
                v1_1.printStackTrace();
            }
            catch(Throwable v1) {
                this.mLc = v0;
                LinphoneManager.instance = ((LinphoneManager)v0);
                throw v1;
            }
        }

        this.mLc = v0;
        LinphoneManager.instance = ((LinphoneManager)v0);
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

    public static final LinphoneManager getInstance() {
        LinphoneManager v1_1;
        Class v0 = LinphoneManager.class;
        __monitor_enter(v0);
        try {
            if(LinphoneManager.instance == null) {
                goto label_7;
            }

            v1_1 = LinphoneManager.instance;
        }
        catch(Throwable v1) {
            goto label_18;
        }

        __monitor_exit(v0);
        return v1_1;
        try {
        label_7:
            if(LinphoneManager.sExited) {
                throw new RuntimeException("Linphone Manager was already destroyed. Better use getLcIfManagerNotDestroyed and check returned value");
            }

            throw new RuntimeException("Linphone Manager should be created before accessed");
        }
        catch(Throwable v1) {
        label_18:
            __monitor_exit(v0);
            throw v1;
        }
    }

    public static final LinphoneCore getLc() {
        LinphoneCore v1_1;
        Class v0 = LinphoneManager.class;
        __monitor_enter(v0);
        try {
            v1_1 = LinphoneManager.getInstance().mLc;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public static LinphoneCore getLcIfManagerNotDestroyOrNull() {
        LinphoneCore v1_1;
        Class v0 = LinphoneManager.class;
        __monitor_enter(v0);
        try {
            if(!LinphoneManager.sExited) {
                if(LinphoneManager.instance == null) {
                }
                else {
                    v1_1 = LinphoneManager.getLc();
                    goto label_8;
                }
            }

            goto label_11;
        }
        catch(Throwable v1) {
            goto label_20;
        }

    label_8:
        __monitor_exit(v0);
        return v1_1;
        try {
        label_11:
            Log.e(new Object[]{"Trying to get linphone core while LinphoneManager already destroyed or not created"});
        }
        catch(Throwable v1) {
        label_20:
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return null;
    }

    public void globalState(LinphoneCore arg1, GlobalState arg2, String arg3) {
    }

    public void infoReceived(LinphoneCore arg1, LinphoneCall arg2, LinphoneInfoMessage arg3) {
    }

    private void initLibLinphone() {
        __monitor_enter(this);
        try {
            this.mLc.setContext(this.mServiceContext);
            this.setUserAgent();
            this.mLc.setRootCA(this.mLinphoneRootCaFile);
            this.mLc.setPlayFile(this.mPauseSoundFile);
            this.mLc.setChatDatabasePath(this.mChatDatabaseFile);
            int v0_1 = Runtime.getRuntime().availableProcessors();
            Object[] v2 = new Object[2];
            v2[0] = "LinphoneManager";
            v2[1] = "MediaStreamer : " + v0_1 + " cores detected and configured";
            Log.w(v2);
            this.mLc.setCpuCount(v0_1);
            v0_1 = LinphoneManager.getLc().migrateToMultiTransport();
            Object[] v1 = new Object[2];
            v1[0] = "LinphoneManager";
            v1[1] = "Migration to multi transport result = " + v0_1;
            Log.d(v1);
            this.mLc.setNetworkReachable(true);
            this.mLc.enableEchoCancellation(this.linphonePrefs.a());
            this.mLc.enableAdaptiveRateControl(this.linphonePrefs.b());
            LinphoneUtils.getConfig(this.mServiceContext).setInt("audio", "codec_bitrate_limit", 128);
            this.mLc.setPreferredVideoSizeByName("720p");
            this.mLc.setUploadBandwidth(1536);
            this.mLc.setDownloadBandwidth(1536);
            this.mLc.setVideoPolicy(this.mLc.getVideoAutoInitiatePolicy(), true);
            this.mLc.setVideoPolicy(true, this.mLc.getVideoAutoAcceptPolicy());
            this.mLc.enableVideo(true, true);
            this.setCodecMime();
            IntentFilter v0_2 = new IntentFilter("android.intent.action.SCREEN_ON");
            v0_2.addAction("android.intent.action.SCREEN_OFF");
            this.mServiceContext.registerReceiver(this.mKeepAliveReceiver, v0_2);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public void isComposingReceived(LinphoneCore arg1, LinphoneChatRoom arg2) {
    }

    public static final boolean isInstanceiated() {
        boolean v0 = LinphoneManager.instance != null ? true : false;
        return v0;
    }

    public void messageReceived(LinphoneCore arg1, LinphoneChatRoom arg2, LinphoneChatMessage arg3) {
    }

    public void newOutgoingCall(String arg1, String arg2) {
    }

    public void newSubscriptionRequest(LinphoneCore arg1, LinphoneFriend arg2, String arg3) {
    }

    public void notifyPresenceReceived(LinphoneCore arg1, LinphoneFriend arg2) {
    }

    public void notifyReceived(LinphoneCore arg1, LinphoneCall arg2, LinphoneAddress arg3, byte[] arg4) {
    }

    public void notifyReceived(LinphoneCore arg1, LinphoneEvent arg2, String arg3, LinphoneContent arg4) {
    }

    public void publishStateChanged(LinphoneCore arg1, LinphoneEvent arg2, PublishState arg3) {
    }

    public void registrationState(LinphoneCore arg1, LinphoneProxyConfig arg2, RegistrationState arg3, String arg4) {
    }

    private void setCodecMime() {
        PayloadType[] v0 = this.mLc.getVideoCodecs();
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            PayloadType v4 = v0[v3];
            android.util.Log.i("LinphoneManager", "setCodecMime = " + v4.getMime());
            if(!v4.getMime().equals("VP8")) {
                try {
                    android.util.Log.i("LinphoneManager", "disable codec " + v4.getMime());
                    this.mLc.enablePayloadType(v4, false);
                }
                catch(LinphoneCoreException v4_1) {
                    Log.e(new Object[]{v4_1});
                }
            }
        }
    }

    private void setUserAgent() {
        try {
            String v0_1 = this.mServiceContext.getPackageManager().getPackageInfo(this.mServiceContext.getPackageName(), 0).versionName;
            if(v0_1 == null) {
                v0_1 = String.valueOf(this.mServiceContext.getPackageManager().getPackageInfo(this.mServiceContext.getPackageName(), 0).versionCode);
            }

            this.mLc.setUserAgent("Hunayutong", v0_1);
        }
        catch(PackageManager$NameNotFoundException v0) {
            v0.printStackTrace();
        }
    }

    public void show(LinphoneCore arg1) {
    }

    private void startLibLinphone(Context arg10) {
        __monitor_enter(this);
        try {
            this.copyAssetsFromPackage();
            this.mLc = LinphoneCoreFactory.instance().createLinphoneCore(this, this.mLinphoneConfigFile, this.mLinphoneFactoryConfigFile, null, arg10);
            this.mLc.addListener(((LinphoneCoreListener)arg10));
            try {
                this.initLibLinphone();
                goto label_20;
            }
            catch(LinphoneCoreException v10_2) {
                try {
                    Log.e(new Object[]{v10_2});
                label_20:
                    org.telegram.customization.voip.linphoneSip.linphone.LinphoneManager$1 v4 = new TimerTask() {
                        public void run() {
                            UIThreadDispatcher.dispatch(new Runnable() {
                                public void run() {
                                    if(this.this$1.this$0.mLc != null) {
                                        this.this$1.this$0.mLc.iterate();
                                    }
                                }
                            });
                        }
                    };
                    this.mTimer = new Timer("Linphone Scheduler");
                    this.mTimer.schedule(((TimerTask)v4), 0, 20);
                }
                catch(Throwable v10_1) {
                }
                catch(Exception v10) {
                    try {
                    label_34:
                        v10.printStackTrace();
                        Log.e(new Object[]{"LinphoneManager", "startLibLinphone: cannot start linphone"});
                    }
                    catch(Throwable v10_1) {
                    label_44:
                        __monitor_exit(this);
                        throw v10_1;
                    }
                }
            }
        }
        catch(Exception v10) {
            goto label_34;
        }
        catch(Throwable v10_1) {
            goto label_44;
        }

        __monitor_exit(this);
    }

    public void subscriptionStateChanged(LinphoneCore arg1, LinphoneEvent arg2, SubscriptionState arg3) {
    }

    public void transferState(LinphoneCore arg1, LinphoneCall arg2, State arg3) {
    }

    public static void unRegisterSIP() {
        LinphoneManager.getLc().clearAuthInfos();
        LinphoneManager.getLc().getDefaultProxyConfig().edit();
        LinphoneManager.getLc().getDefaultProxyConfig().setExpires(0);
        LinphoneManager.getLc().getDefaultProxyConfig().done();
    }

    public void uploadProgressIndication(LinphoneCore arg1, int arg2, int arg3) {
    }

    public void uploadStateChanged(LinphoneCore arg1, LogCollectionUploadState arg2, String arg3) {
    }
}

