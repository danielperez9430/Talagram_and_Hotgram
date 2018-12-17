package org.linphone.core;

import java.util.Vector;

public interface LinphoneFriend {
    public class SubscribePolicy {
        public static final SubscribePolicy SPAccept;
        public static final SubscribePolicy SPDeny;
        public static final SubscribePolicy SPWait;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            SubscribePolicy.values = new Vector();
            SubscribePolicy.SPWait = new SubscribePolicy(0, "SPWait");
            SubscribePolicy.SPDeny = new SubscribePolicy(1, "SPDeny");
            SubscribePolicy.SPAccept = new SubscribePolicy(2, "SPAccept");
        }

        private SubscribePolicy(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            SubscribePolicy.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static SubscribePolicy fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < SubscribePolicy.values.size(); ++v0) {
                Object v1 = SubscribePolicy.values.elementAt(v0);
                if(((SubscribePolicy)v1).mValue == arg3) {
                    return ((SubscribePolicy)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Policy not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    void addAddress(LinphoneAddress arg1);

    void addPhoneNumber(String arg1);

    void done();

    void edit();

    void enableSubscribes(boolean arg1);

    LinphoneAddress getAddress();

    LinphoneAddress[] getAddresses();

    String getFamilyName();

    String getGivenName();

    SubscribePolicy getIncSubscribePolicy();

    String getName();

    long getNativePtr();

    String getOrganization();

    String[] getPhoneNumbers();

    @Deprecated PresenceModel getPresenceModel();

    PresenceModel getPresenceModelForUri(String arg1);

    String getRefKey();

    @Deprecated OnlineStatus getStatus();

    boolean isAlreadyPresentInFriendList();

    boolean isPresenceReceived();

    boolean isSubscribesEnabled();

    void removeAddress(LinphoneAddress arg1);

    void removePhoneNumber(String arg1);

    void setAddress(LinphoneAddress arg1);

    void setFamilyName(String arg1);

    void setGivenName(String arg1);

    void setIncSubscribePolicy(SubscribePolicy arg1);

    void setName(String arg1);

    void setOrganization(String arg1);

    void setPresenceModel(PresenceModel arg1);

    void setRefKey(String arg1);

    String toString();
}

