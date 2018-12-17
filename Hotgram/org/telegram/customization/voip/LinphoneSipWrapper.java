package org.telegram.customization.voip;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.sip.SipAudioCall$Listener;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile$Builder;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore$RegistrationState;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreFactoryImpl;
import org.telegram.customization.Model.CallRequestModel;
import org.telegram.customization.Model.WhatsupNotif;
import org.telegram.customization.voip.linphoneSip.linphone.LinphoneManager;
import org.telegram.customization.voip.linphoneSip.linphone.PhoneBean;
import org.telegram.customization.voip.linphoneSip.linphone.PhoneServiceCallback;
import org.telegram.customization.voip.linphoneSip.linphone.PhoneVoiceUtils;
import org.telegram.customization.voip.linphoneSip.utils.LinphoneUtils;
import utils.a.b;

public class LinphoneSipWrapper {
    class org.telegram.customization.voip.LinphoneSipWrapper$3 extends SipAudioCall$Listener {
        org.telegram.customization.voip.LinphoneSipWrapper$3(LinphoneSipWrapper arg1) {
            LinphoneSipWrapper.this = arg1;
            super();
        }

        public void onCallBusy(SipAudioCall arg3) {
            super.onCallBusy(arg3);
            LinphoneSipWrapper.updateStatus(LinphoneSipWrapper.this.ctx, "onBusy");
            LinphoneSipWrapper.this.endCall(false, true);
            LinphoneSipWrapper.this.updateState(17);
        }

        public void onCallEnded(SipAudioCall arg2) {
            LinphoneSipWrapper.updateStatus(LinphoneSipWrapper.this.ctx, "onCallEnded");
            LinphoneSipWrapper.this.showCallBtn();
            LinphoneSipWrapper.this.updateState(11);
        }

        public void onCallEstablished(SipAudioCall arg3) {
            LinphoneSipWrapper.updateStatus(LinphoneSipWrapper.this.ctx, "onCallEstablished");
            arg3.startAudio();
            arg3.setSpeakerMode(LinphoneSipWrapper.this.isInSpeakerMode);
            if(arg3.isMuted()) {
                arg3.toggleMute();
            }

            LinphoneSipWrapper.this.showEndCallBtn();
            LinphoneSipWrapper.this.updateState(3);
        }

        public void onError(SipAudioCall arg3, int arg4, String arg5) {
            super.onError(arg3, arg4, arg5);
            Context v3 = LinphoneSipWrapper.this.ctx;
            LinphoneSipWrapper.updateStatus(v3, "onError : " + arg4 + " : " + arg5);
            LinphoneSipWrapper.this.showCallBtn();
        }

        public void onRinging(SipAudioCall arg1, SipProfile arg2) {
            super.onRinging(arg1, arg2);
            LinphoneSipWrapper.this.updateState(16);
        }
    }

    class org.telegram.customization.voip.LinphoneSipWrapper$9 implements SipRegistrationListener {
        org.telegram.customization.voip.LinphoneSipWrapper$9(LinphoneSipWrapper arg1) {
            LinphoneSipWrapper.this = arg1;
            super();
        }

        public void onRegistering(String arg2) {
            LinphoneSipWrapper.updateStatus(LinphoneSipWrapper.this.ctx, "onRegistering with SIP server.");
            LinphoneSipWrapper.this.sipManagerState = SipManagerState.CONNECTING;
        }

        public void onRegistering(String arg1, int arg2, String arg3) {
            LinphoneSipWrapper.updateStatus(LinphoneSipWrapper.this.ctx, "onRegistering Registration failed!");
        }

        public void onRegistrationDone(String arg1, long arg2) {
            LinphoneSipWrapper.this.registrationDone();
        }

        public void onRegistrationFailed(String arg3, int arg4, String arg5) {
            Context v4 = LinphoneSipWrapper.this.ctx;
            LinphoneSipWrapper.updateStatus(v4, "onRegistrationFailed " + arg3 + " : " + arg5);
            LinphoneSipWrapper.this.sipManagerState = SipManagerState.NOT_CONNECTED;
            LinphoneSipWrapper.this.closeLocalProfile();
        }
    }

    public interface SipCallback {
        void hideAnswer();

        void onSipManagerStateChanged(SipManagerState arg1);

        void onSipStateChanged(int arg1, int arg2);

        void showAnswer(SipProfile arg1);

        void showCallBtn();

        void showEndCallBtn();
    }

    public enum SipManagerState {
        public static final enum SipManagerState CONNECTED;
        public static final enum SipManagerState CONNECTING;
        public static final enum SipManagerState NOT_CONNECTED;

        static {
            SipManagerState.NOT_CONNECTED = new SipManagerState("NOT_CONNECTED", 0);
            SipManagerState.CONNECTING = new SipManagerState("CONNECTING", 1);
            SipManagerState.CONNECTED = new SipManagerState("CONNECTED", 2);
            SipManagerState.$VALUES = new SipManagerState[]{SipManagerState.NOT_CONNECTED, SipManagerState.CONNECTING, SipManagerState.CONNECTED};
        }

        private SipManagerState(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static SipManagerState valueOf(String arg1) {
            return Enum.valueOf(SipManagerState.class, arg1);
        }

        public static SipManagerState[] values() {
            // Method was not decompiled
        }
    }

    public static final String IncomingCallAction = "org.telegram.customization.voip.INCOMING_CALL";
    public static final String LINPHONE_TAG = "LINPHONE_TAG";
    public static String callId;
    volatile CallRequestModel callRequestModel;
    long callStartTime;
    Context ctx;
    boolean isInSpeakerMode;
    private LinphoneCall linphoneCall;
    private LinphoneCore linphoneCore;
    SipAudioCall$Listener listener;
    private SipRegistrationListener listener2;
    public SipManager mSipManager;
    public SipProfile mSipProfile;
    MediaPlayer mp;
    private static PhoneServiceCallback sPhoneServiceCallback;
    ArrayList sipCallbacks;
    SipManagerState sipManagerState;
    int state;
    volatile WhatsupNotif whatsupNotif;

    public LinphoneSipWrapper(Context arg3) {
        super();
        this.mSipManager = null;
        this.mSipProfile = null;
        this.isInSpeakerMode = false;
        this.sipManagerState = SipManagerState.NOT_CONNECTED;
        this.callStartTime = 0;
        this.listener = new org.telegram.customization.voip.LinphoneSipWrapper$3(this);
        this.listener2 = new org.telegram.customization.voip.LinphoneSipWrapper$9(this);
        this.ctx = arg3;
        this.sipCallbacks = new ArrayList();
        if(b.c()) {
            LinphoneCoreFactoryImpl.instance();
            LinphoneManager.createAndStart(this.ctx);
            this.updateState(1);
        }
    }

    public static void addCallback(PhoneServiceCallback arg0) {
        LinphoneSipWrapper.sPhoneServiceCallback = arg0;
    }

    public void addSipCallback(SipCallback arg2) {
        __monitor_enter(this);
        try {
            this.sipCallbacks.add(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public boolean answerIncomingCall() {
        if(this.linphoneCall == null) {
            return 0;
        }

        try {
            LinphoneManager.getLc().acceptCall(LinphoneManager.getLc().getCurrentCall());
        }
        catch(LinphoneCoreException v0) {
            v0.printStackTrace();
        }

        try {
            if(PhoneVoiceUtils.getInstance().isMuted()) {
                PhoneVoiceUtils.getInstance().toggleMicro(true);
            }

            this.stopRingRing();
            this.updateState(3);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Iterator v0 = LinphoneSipWrapper.this.sipCallbacks.iterator();
                    while(v0.hasNext()) {
                        v0.next().hideAnswer();
                    }
                }
            });
            return 1;
        }
        catch(Exception ) {
            return 1;
        }
    }

    public void call(String arg2, String arg3, int arg4) {
        try {
            LinphoneSipWrapper.updateStatus(this.ctx, "call called :D");
            this.endCall(false, false);
            this.endIncomingCall(false);
            PhoneBean v4 = new PhoneBean();
            v4.setUserName(arg2);
            v4.setHost(arg3);
            PhoneVoiceUtils.getInstance().startSingleCallingTo(v4);
            this.showEndCallBtn();
            this.updateState(13);
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public void closeLocalProfile() {
        this.showCallBtn();
        if(this.mSipManager == null) {
            return;
        }

        try {
            if(this.mSipProfile != null) {
                this.mSipManager.close(this.mSipProfile.getUriString());
                this.mSipManager.unregister(this.mSipProfile, this.listener2);
            }

            this.updateState(4);
        }
        catch(Exception ) {
            Log.d("TAG", "Failed to close local profile.");
        }
    }

    public void endCall(boolean arg1, boolean arg2) {
        try {
            if(this.linphoneCall != null) {
                PhoneVoiceUtils.getInstance().hangUp();
                PhoneVoiceUtils.getInstance().clearAccounts();
            }

            this.linphoneCall = null;
            this.showCallBtn();
            if(!arg1) {
                return;
            }

            this.updateState(11);
        }
        catch(Exception v1) {
            v1.printStackTrace();
        }
    }

    public void endCall() {
        this.endCall(true, false);
    }

    public void endIncomingCall(boolean arg2) {
        try {
            PhoneVoiceUtils.getInstance().hangUp();
            if(arg2) {
                this.updateState(10);
            }

            this.endCall(arg2, true);
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public long getCallDuration() {
        return SystemClock.elapsedRealtime() - this.callStartTime;
    }

    public CallRequestModel getCallRequestModel() {
        return this.callRequestModel;
    }

    public LinphoneCore getLinphoneCore() {
        return this.linphoneCore;
    }

    public WhatsupNotif getWhatsupNotif() {
        return this.whatsupNotif;
    }

    public LinphoneCall getlinphoneCall() {
        return this.linphoneCall;
    }

    public static PhoneServiceCallback getsPhoneServiceCallback() {
        return LinphoneSipWrapper.sPhoneServiceCallback;
    }

    public boolean isInCall() {
        return LinphoneUtils.isCallRunning(this.linphoneCall);
    }

    public void notifyCurrentState() {
        Iterator v0 = this.sipCallbacks.iterator();
        while(v0.hasNext()) {
            v0.next().onSipStateChanged(this.state, this.state);
        }
    }

    void playRingRing(SipProfile arg1) {
    }

    private void prepare() {
        Intent v0 = new Intent();
        v0.setAction("org.telegram.customization.voip.INCOMING_CALL");
        PendingIntent v0_1 = PendingIntent.getBroadcast(this.ctx, 0, v0, 2);
        try {
            this.mSipManager.open(this.mSipProfile, v0_1, null);
        }
        catch(SipException v0_2) {
            v0_2.printStackTrace();
        }
    }

    public void register(String arg4, int arg5, String arg6, String arg7) {
        if(!TextUtils.isEmpty(((CharSequence)arg6)) && !TextUtils.isEmpty(((CharSequence)arg7)) && !TextUtils.isEmpty(((CharSequence)arg4))) {
            Log.d("LEE", "SIP DATA:" + arg4 + " " + arg5 + " " + arg6 + " " + arg7);
            LinphoneSipWrapper.addCallback(new PhoneServiceCallback() {
                public void registrationState(RegistrationState arg2) {
                    String v0;
                    String v2;
                    if("RegistrationOk".equals(arg2.toString())) {
                        v2 = "LINPHONE_TAG";
                        v0 = "RegistrationOk";
                    }
                    else {
                        v2 = "LINPHONE_TAG";
                        v0 = "RegistrationFailed";
                    }

                    Log.d(v2, v0);
                }
            });
            try {
                PhoneVoiceUtils.getInstance().registerUserAuth(arg6, arg7, arg4);
                this.updateState(1);
            }
            catch(LinphoneCoreException v4) {
                v4.printStackTrace();
            }
        }
    }

    public void registrationDone() {
        LinphoneSipWrapper.updateStatus(this.ctx, "onRegistrationDone.");
        this.sipManagerState = SipManagerState.CONNECTED;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Iterator v0 = LinphoneSipWrapper.this.sipCallbacks.iterator();
                while(v0.hasNext()) {
                    v0.next().onSipManagerStateChanged(LinphoneSipWrapper.this.sipManagerState);
                }
            }
        });
    }

    public void removeSipCallback(SipCallback arg2) {
        __monitor_enter(this);
        try {
            this.sipCallbacks.remove(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public void setCallRequestModel(CallRequestModel arg1) {
        this.callRequestModel = arg1;
    }

    public void setIncomingCall(Intent arg2) {
        try {
            LinphoneSipWrapper.updateStatus(this.ctx, "rec, start call");
            this.showEndCallBtn();
            this.updateState(15);
        }
        catch(Exception ) {
            this.linphoneCall = null;
        }
    }

    public void setLinphoneCore(LinphoneCore arg1) {
        this.linphoneCore = arg1;
    }

    public void setWhatsupNotif(WhatsupNotif arg1) {
        this.whatsupNotif = arg1;
    }

    public void setlinphoneCall(LinphoneCall arg1) {
        this.linphoneCall = arg1;
    }

    public void setmProfiler(CallRequestModel arg4) {
        try {
            SipProfile$Builder v0 = new SipProfile$Builder(arg4.getCallerUser(), arg4.getDomain());
            v0.setPassword(arg4.getCallerPassword());
            v0.setPort(arg4.getPort());
            this.mSipProfile = v0.build();
        }
        catch(ParseException v4) {
            v4.printStackTrace();
        }
    }

    public void showCallBtn() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Iterator v0 = LinphoneSipWrapper.this.sipCallbacks.iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    ((SipCallback)v1).showCallBtn();
                    ((SipCallback)v1).hideAnswer();
                }

                LinphoneSipWrapper.this.stopRingRing();
            }
        });
    }

    public void showEndCallBtn() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Iterator v0 = LinphoneSipWrapper.this.sipCallbacks.iterator();
                while(v0.hasNext()) {
                    v0.next().showEndCallBtn();
                }
            }
        });
    }

    public void speakerToggle(boolean arg2) {
        if(this.linphoneCall != null) {
            PhoneVoiceUtils.getInstance().toggleSpeaker(arg2);
        }
    }

    void stopRingRing() {
    }

    public void toggleMicrophone(boolean arg2) {
        if(this.linphoneCall != null) {
            PhoneVoiceUtils.getInstance().toggleMicro(arg2);
        }
    }

    public void updateState(int arg3) {
        __monitor_enter(this);
        if(arg3 == 3) {
            try {
                this.callStartTime = SystemClock.elapsedRealtime();
            label_8:
                int v0 = this.state;
                this.state = arg3;
                new Handler(Looper.getMainLooper()).post(new Runnable(v0) {
                    public void run() {
                        Iterator v0 = LinphoneSipWrapper.this.sipCallbacks.iterator();
                        while(v0.hasNext()) {
                            v0.next().onSipStateChanged(this.val$oldState, LinphoneSipWrapper.this.state);
                        }
                    }
                });
                goto label_16;
            label_7:
                goto label_18;
            }
            catch(Throwable v3) {
                goto label_7;
            }
        }

        goto label_8;
    label_18:
        __monitor_exit(this);
        throw v3;
    label_16:
        __monitor_exit(this);
    }

    public static void updateStatus(Context arg3, String arg4) {
        Log.d("LEESIP", "TID: " + Thread.currentThread().getId() + " #" + arg4);
    }
}

