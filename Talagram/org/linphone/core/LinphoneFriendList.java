package org.linphone.core;

import java.util.Vector;

public interface LinphoneFriendList {
    public interface LinphoneFriendListListener {
        void onLinphoneFriendCreated(LinphoneFriendList arg1, LinphoneFriend arg2);

        void onLinphoneFriendDeleted(LinphoneFriendList arg1, LinphoneFriend arg2);

        void onLinphoneFriendSyncStatusChanged(LinphoneFriendList arg1, State arg2, String arg3);

        void onLinphoneFriendUpdated(LinphoneFriendList arg1, LinphoneFriend arg2, LinphoneFriend arg3);
    }

    public class State {
        public static final State SyncFailure;
        public static final State SyncStarted;
        public static final State SyncSuccessful;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            State.values = new Vector();
            State.SyncStarted = new State(0, "SyncStarted");
            State.SyncSuccessful = new State(1, "SyncSuccessful");
            State.SyncFailure = new State(2, "SyncFailure");
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

    void addFriend(LinphoneFriend arg1);

    void addLocalFriend(LinphoneFriend arg1);

    void enableSubscriptions(boolean arg1);

    void exportFriendsToVCardFile(String arg1);

    LinphoneFriend findFriendByUri(String arg1);

    LinphoneFriend[] getFriendList();

    long getNativePtr();

    int importFriendsFromVCardBuffer(String arg1);

    int importFriendsFromVCardFile(String arg1);

    void setListener(LinphoneFriendListListener arg1);

    void setRLSAddress(LinphoneAddress arg1);

    void setRLSUri(String arg1);

    void setUri(String arg1);

    void synchronizeFriendsFromServer();

    void updateSubscriptions();
}

