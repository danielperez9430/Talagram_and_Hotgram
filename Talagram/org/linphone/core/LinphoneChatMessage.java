package org.linphone.core;

import java.util.Vector;

public interface LinphoneChatMessage {
    public interface LinphoneChatMessageListener {
        void onLinphoneChatMessageFileTransferProgressChanged(LinphoneChatMessage arg1, LinphoneContent arg2, int arg3, int arg4);

        void onLinphoneChatMessageFileTransferReceived(LinphoneChatMessage arg1, LinphoneContent arg2, LinphoneBuffer arg3);

        void onLinphoneChatMessageFileTransferSent(LinphoneChatMessage arg1, LinphoneContent arg2, int arg3, int arg4, LinphoneBuffer arg5);

        void onLinphoneChatMessageStateChanged(LinphoneChatMessage arg1, State arg2);
    }

    public class State {
        public static final State Delivered;
        public static final State FileTransferDone;
        public static final State FileTransferError;
        public static final State Idle;
        public static final State InProgress;
        public static final State NotDelivered;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            State.values = new Vector();
            State.Idle = new State(0, "Idle");
            State.InProgress = new State(1, "InProgress");
            State.Delivered = new State(2, "Delivered");
            State.NotDelivered = new State(3, "NotDelivered");
            State.FileTransferError = new State(4, "FileTransferError");
            State.FileTransferDone = new State(5, "FileTransferDone");
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

        public int toInt() {
            return this.mValue;
        }

        public String toString() {
            return this.mStringValue;
        }

        public final int value() {
            return this.mValue;
        }
    }

    @Deprecated public interface StateListener {
        void onLinphoneChatMessageStateChanged(LinphoneChatMessage arg1, State arg2);
    }

    void addCustomHeader(String arg1, String arg2);

    void cancelFileTransfer();

    void destroy();

    int downloadFile();

    String getAppData();

    String getCustomHeader(String arg1);

    ErrorInfo getErrorInfo();

    String getExternalBodyUrl();

    LinphoneContent getFileTransferInformation();

    LinphoneAddress getFrom();

    LinphoneAddress getPeerAddress();

    Reason getReason();

    State getStatus();

    int getStorageId();

    String getText();

    long getTime();

    LinphoneAddress getTo();

    boolean isOutgoing();

    boolean isRead();

    void putChar(long arg1);

    void setAppData(String arg1);

    void setExternalBodyUrl(String arg1);

    void setFileTransferFilepath(String arg1);

    void setListener(LinphoneChatMessageListener arg1);

    void store();
}

