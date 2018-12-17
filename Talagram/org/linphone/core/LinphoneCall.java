package org.linphone.core;

import java.util.Vector;

public interface LinphoneCall {
    public interface LinphoneCallListener {
        void onNextVideoFrameDecoded(LinphoneCall arg1);
    }

    public class State {
        public static final State CallEarlyUpdatedByRemote;
        public static final State CallEarlyUpdating;
        public static final State CallEnd;
        public static final State CallIncomingEarlyMedia;
        public static final State CallReleased;
        public static final State CallUpdatedByRemote;
        public static final State CallUpdating;
        public static final State Connected;
        public static final State Error;
        public static final State Idle;
        public static final State IncomingReceived;
        public static final State OutgoingEarlyMedia;
        public static final State OutgoingInit;
        public static final State OutgoingProgress;
        public static final State OutgoingRinging;
        public static final State Paused;
        public static final State PausedByRemote;
        public static final State Pausing;
        public static final State Refered;
        public static final State Resuming;
        public static final State StreamsRunning;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            State.values = new Vector();
            State.Idle = new State(0, "Idle");
            State.IncomingReceived = new State(1, "IncomingReceived");
            State.OutgoingInit = new State(2, "OutgoingInit");
            State.OutgoingProgress = new State(3, "OutgoingProgress");
            State.OutgoingRinging = new State(4, "OutgoingRinging");
            State.OutgoingEarlyMedia = new State(5, "OutgoingEarlyMedia");
            State.Connected = new State(6, "Connected");
            State.StreamsRunning = new State(7, "StreamsRunning");
            State.Pausing = new State(8, "Pausing");
            State.Paused = new State(9, "Paused");
            State.Resuming = new State(10, "Resuming");
            State.Refered = new State(11, "Refered");
            State.Error = new State(12, "Error");
            State.CallEnd = new State(13, "CallEnd");
            State.PausedByRemote = new State(14, "PausedByRemote");
            State.CallUpdatedByRemote = new State(15, "UpdatedByRemote");
            State.CallIncomingEarlyMedia = new State(16, "IncomingEarlyMedia");
            State.CallUpdating = new State(17, "Updating");
            State.CallReleased = new State(18, "Released");
            State.CallEarlyUpdatedByRemote = new State(19, "EarlyUpdatedByRemote");
            State.CallEarlyUpdating = new State(20, "EarlyUpdating");
        }

        private State(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            State.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static State fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < State.values.size(); ++v0) {
                Object v1 = State.values.elementAt(v0);
                if(((State)v1).mValue == arg3) {
                    return ((State)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("state not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }

        public final int value() {
            return this.mValue;
        }
    }

    boolean cameraEnabled();

    void enableCamera(boolean arg1);

    void enableEchoCancellation(boolean arg1);

    void enableEchoLimiter(boolean arg1);

    LinphoneCallStats getAudioStats();

    String getAuthenticationToken();

    float getAverageQuality();

    LinphoneCallLog getCallLog();

    LinphoneChatRoom getChatRoom();

    LinphoneConference getConference();

    LinphoneCallParams getCurrentParamsCopy();

    float getCurrentQuality();

    CallDirection getDirection();

    LinphoneAddress getDiversionAddress();

    int getDuration();

    ErrorInfo getErrorInfo();

    float getPlayVolume();

    LinphonePlayer getPlayer();

    Reason getReason();

    LinphoneAddress getRemoteAddress();

    String getRemoteContact();

    LinphoneCallParams getRemoteParams();

    String getRemoteUserAgent();

    LinphoneCall getReplacedCall();

    State getState();

    State getTransferState();

    LinphoneCall getTransferTargetCall();

    LinphoneCall getTransfererCall();

    Object getUserData();

    LinphoneCallStats getVideoStats();

    boolean isAuthenticationTokenVerified();

    boolean isEchoCancellationEnabled();

    boolean isEchoLimiterEnabled();

    boolean isInConference();

    boolean mediaInProgress();

    void sendInfoMessage(LinphoneInfoMessage arg1);

    void setAuthenticationTokenVerified(boolean arg1);

    void setListener(LinphoneCallListener arg1);

    void setUserData(Object arg1);

    void startRecording();

    void stopRecording();

    void takeSnapshot(String arg1);

    void zoomVideo(float arg1, float arg2, float arg3);
}

