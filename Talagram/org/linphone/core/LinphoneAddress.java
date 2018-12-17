package org.linphone.core;

import java.util.Vector;

public interface LinphoneAddress {
    public class TransportType {
        public static TransportType LinphoneTransportTcp;
        public static TransportType LinphoneTransportTls;
        public static TransportType LinphoneTransportUdp;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            TransportType.values = new Vector();
            TransportType.LinphoneTransportUdp = new TransportType(0, "LinphoneTransportUdp");
            TransportType.LinphoneTransportTcp = new TransportType(1, "LinphoneTransportTcp");
            TransportType.LinphoneTransportTls = new TransportType(2, "LinphoneTransportTls");
        }

        private TransportType(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            TransportType.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static TransportType fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < TransportType.values.size(); ++v0) {
                Object v1 = TransportType.values.elementAt(v0);
                if(((TransportType)v1).mValue == arg3) {
                    return ((TransportType)v1);
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
    }

    String asString();

    String asStringUriOnly();

    void clean();

    String getDisplayName();

    String getDomain();

    int getPort();

    TransportType getTransport();

    String getUserName();

    void setDisplayName(String arg1);

    void setDomain(String arg1);

    void setPort(int arg1);

    void setTransport(TransportType arg1);

    void setUserName(String arg1);

    String toString();
}

