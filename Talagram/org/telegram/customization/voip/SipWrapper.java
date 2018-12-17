package org.telegram.customization.voip;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.util.Log;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.customization.Model.CallRequestModel;
import org.telegram.customization.Model.WhatsupNotif;

public class SipWrapper {
    class org.telegram.customization.voip.SipWrapper$2 extends SipAudioCall$Listener {
        org.telegram.customization.voip.SipWrapper$2(SipWrapper arg1) {
            SipWrapper.this = arg1;
            super();
        }

        public void onCallBusy(SipAudioCall arg2) {
            super.onCallBusy(arg2);
            SipWrapper.updateStatus(SipWrapper.this.ctx, "onBusy");
            SipWrapper.this.endCall(false);
            SipWrapper.this.updateState(17);
        }

        public void onCallEnded(SipAudioCall arg2) {
            SipWrapper.updateStatus(SipWrapper.this.ctx, "onCallEnded");
            SipWrapper.this.showCallBtn();
            SipWrapper.this.updateState(11);
        }

        public void onCallEstablished(SipAudioCall arg3) {
            SipWrapper.updateStatus(SipWrapper.this.ctx, "onCallEstablished");
            arg3.startAudio();
            arg3.setSpeakerMode(SipWrapper.this.isInSpeakerMode);
            if(arg3.isMuted()) {
                arg3.toggleMute();
            }

            SipWrapper.this.showEndCallBtn();
            SipWrapper.this.updateState(3);
        }

        public void onError(SipAudioCall arg3, int arg4, String arg5) {
            super.onError(arg3, arg4, arg5);
            Context v3 = SipWrapper.this.ctx;
            SipWrapper.updateStatus(v3, "onError : " + arg4 + " : " + arg5);
            SipWrapper.this.showCallBtn();
        }

        public void onRinging(SipAudioCall arg1, SipProfile arg2) {
            super.onRinging(arg1, arg2);
            SipWrapper.this.updateState(16);
        }
    }

    class org.telegram.customization.voip.SipWrapper$8 implements SipRegistrationListener {
        org.telegram.customization.voip.SipWrapper$8(SipWrapper arg1) {
            SipWrapper.this = arg1;
            super();
        }

        public void onRegistering(String arg2) {
            SipWrapper.updateStatus(SipWrapper.this.ctx, "onRegistering with SIP server.");
            SipWrapper.this.sipManagerState = SipManagerState.CONNECTING;
        }

        public void onRegistering(String arg1, int arg2, String arg3) {
            SipWrapper.updateStatus(SipWrapper.this.ctx, "onRegistering Registration failed!");
        }

        public void onRegistrationDone(String arg1, long arg2) {
            SipWrapper.updateStatus(SipWrapper.this.ctx, "onRegistrationDone.");
            SipWrapper.this.sipManagerState = SipManagerState.CONNECTED;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Iterator v0 = this.this$1.this$0.sipCallbacks.iterator();
                    while(v0.hasNext()) {
                        v0.next().onSipManagerStateChanged(this.this$1.this$0.sipManagerState);
                    }
                }
            });
        }

        public void onRegistrationFailed(String arg3, int arg4, String arg5) {
            Context v4 = SipWrapper.this.ctx;
            SipWrapper.updateStatus(v4, "onRegistrationFailed " + arg3 + " : " + arg5);
            SipWrapper.this.sipManagerState = SipManagerState.NOT_CONNECTED;
            SipWrapper.this.closeLocalProfile();
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
    public SipAudioCall call;
    volatile CallRequestModel callRequestModel;
    long callStartTime;
    Context ctx;
    public SipAudioCall incomingCall;
    boolean isInSpeakerMode;
    SipAudioCall$Listener listener;
    private SipRegistrationListener listener2;
    public SipManager mSipManager;
    public SipProfile mSipProfile;
    MediaPlayer mp;
    ArrayList sipCallbacks;
    SipManagerState sipManagerState;
    int state;
    volatile WhatsupNotif whatsupNotif;

    public SipWrapper(Context arg3) {
        super();
        this.mSipManager = null;
        this.mSipProfile = null;
        this.isInSpeakerMode = false;
        this.sipManagerState = SipManagerState.NOT_CONNECTED;
        this.callStartTime = 0;
        this.listener = new org.telegram.customization.voip.SipWrapper$2(this);
        this.listener2 = new org.telegram.customization.voip.SipWrapper$8(this);
        this.ctx = arg3;
        this.sipCallbacks = new ArrayList();
        this.mSipManager = SipManager.newInstance(this.ctx);
        new IntentFilter().addAction("org.telegram.customization.voip.INCOMING_CALL");
        this.updateState(1);
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
        if(this.incomingCall == null) {
            return 0;
        }

        try {
            this.incomingCall.answerCall(30);
            this.incomingCall.startAudio();
            this.incomingCall.setSpeakerMode(this.isInSpeakerMode);
            if(this.incomingCall.isMuted()) {
                this.incomingCall.toggleMute();
            }

            this.call = this.incomingCall;
            this.incomingCall = null;
            this.stopRingRing();
            this.updateState(3);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Iterator v0 = SipWrapper.this.sipCallbacks.iterator();
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

    public void call(String arg3, String arg4, int arg5) {
        try {
            SipWrapper.updateStatus(this.ctx, "call called :D");
            this.endCall(false);
            this.endIncomingCall(false);
            SipProfile$Builder v0 = new SipProfile$Builder(arg3, arg4);
            v0.setPort(arg5);
            this.call = this.mSipManager.makeAudioCall(this.mSipProfile, v0.build(), this.listener, 30);
            this.showEndCallBtn();
            this.updateState(13);
        }
        catch(Exception v3) {
            v3.printStackTrace();
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

    public void endCall(boolean arg2) {
        try {
            if(this.call != null) {
                this.call.endCall();
            }

            this.call = null;
            this.incomingCall = null;
            this.showCallBtn();
            if(!arg2) {
                return;
            }

            this.updateState(11);
        }
        catch(Exception v2) {
            v2.printStackTrace();
        }
    }

    public void endCall() {
        this.endCall(true);
    }

    public void endIncomingCall(boolean arg2) {
        try {
            if(this.incomingCall != null) {
                this.incomingCall.endCall();
            }

            this.incomingCall = null;
            this.endCall(false);
            if(!arg2) {
                return;
            }

            this.updateState(10);
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

    public WhatsupNotif getWhatsupNotif() {
        return this.whatsupNotif;
    }

    public boolean isInCall() {
        boolean v0 = this.call != null || this.incomingCall != null ? true : false;
        return v0;
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
        Log.d("LEE", "SIP DATA:" + arg4 + " " + arg5 + " " + arg6 + " " + arg7);
        SipWrapper.updateStatus(this.ctx, "registerCalled");
        try {
            SipProfile$Builder v0 = new SipProfile$Builder(arg6, arg4);
            v0.setPassword(arg7);
            v0.setPort(arg5);
            this.mSipProfile = v0.build();
            this.prepare();
            try {
                this.mSipManager.setRegistrationListener(this.mSipProfile.getUriString(), this.listener2);
                this.updateState(1);
            }
            catch(SipException v4_1) {
                try {
                    v4_1.printStackTrace();
                }
                catch(ParseException v4) {
                label_39:
                    v4.printStackTrace();
                }
            }
        }
        catch(ParseException v4) {
            goto label_39;
        }
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

    public void setIncomingCall(Intent arg3) {
        try {
            this.incomingCall = this.mSipManager.takeAudioCall(arg3, new SipAudioCall$Listener() {
                public void onCallEnded(SipAudioCall arg2) {
                    super.onCallEnded(arg2);
                    SipWrapper.this.showCallBtn();
                    SipWrapper.this.updateState(11);
                }

                public void onCallEstablished(SipAudioCall arg2) {
                    super.onCallEstablished(arg2);
                    SipWrapper.updateStatus(SipWrapper.this.ctx, "rec, onCallEstablished");
                    SipWrapper.this.showEndCallBtn();
                    SipWrapper.this.updateState(3);
                }

                public void onChanged(SipAudioCall arg2) {
                    super.onChanged(arg2);
                    SipWrapper.updateStatus(SipWrapper.this.ctx, "rec, onChanged");
                }

                public void onError(SipAudioCall arg2, int arg3, String arg4) {
                    super.onError(arg2, arg3, arg4);
                    Context v2 = SipWrapper.this.ctx;
                    SipWrapper.updateStatus(v2, "rec, onError " + arg4);
                    SipWrapper.this.showCallBtn();
                }

                public void onRinging(SipAudioCall arg1, SipProfile arg2) {
                    super.onRinging(arg1, arg2);
                    SipWrapper.updateStatus(SipWrapper.this.ctx, "rec, onRinging");
                    SipWrapper.this.updateState(15);
                }
            });
            this.playRingRing(this.incomingCall.getPeerProfile());
            SipWrapper.updateStatus(this.ctx, "rec, start call");
            this.showEndCallBtn();
            this.updateState(15);
        }
        catch(Exception ) {
            if(this.incomingCall != null) {
                this.incomingCall.close();
            }

            this.incomingCall = null;
        }
    }

    public void setWhatsupNotif(WhatsupNotif arg1) {
        this.whatsupNotif = arg1;
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

    void showCallBtn() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Iterator v0 = SipWrapper.this.sipCallbacks.iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    ((SipCallback)v1).showCallBtn();
                    ((SipCallback)v1).hideAnswer();
                }

                SipWrapper.this.stopRingRing();
            }
        });
    }

    void showEndCallBtn() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                Iterator v0 = SipWrapper.this.sipCallbacks.iterator();
                while(v0.hasNext()) {
                    v0.next().showEndCallBtn();
                }
            }
        });
    }

    public void speakerToggle() {
        this.isInSpeakerMode ^= 1;
        if(this.call != null) {
            this.call.setSpeakerMode(this.isInSpeakerMode);
        }

        if(this.incomingCall != null) {
            this.incomingCall.setSpeakerMode(this.isInSpeakerMode);
        }
    }

    void stopRingRing() {
    }

    public void toggleMicrophone() {
        if(this.call != null) {
            this.call.toggleMute();
        }

        if(this.incomingCall != null) {
            this.incomingCall.toggleMute();
        }
    }

    void updateState(int arg3) {
        __monitor_enter(this);
        if(arg3 == 3) {
            try {
                this.callStartTime = SystemClock.elapsedRealtime();
            label_8:
                int v0 = this.state;
                this.state = arg3;
                new Handler(Looper.getMainLooper()).post(new Runnable(v0) {
                    public void run() {
                        Iterator v0 = SipWrapper.this.sipCallbacks.iterator();
                        while(v0.hasNext()) {
                            v0.next().onSipStateChanged(this.val$oldState, SipWrapper.this.state);
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

