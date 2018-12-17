package org.linphone.core;

import java.util.Vector;

public interface LinphoneCallLog {
    public class CallStatus {
        public static final CallStatus Aborted;
        public static final CallStatus Declined;
        public static final CallStatus Missed;
        public static final CallStatus Success;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            CallStatus.values = new Vector();
            CallStatus.Success = new CallStatus(0, "Sucess");
            CallStatus.Aborted = new CallStatus(1, "Aborted");
            CallStatus.Missed = new CallStatus(2, "Missed");
            CallStatus.Declined = new CallStatus(3, "Declined");
        }

        private CallStatus(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            CallStatus.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static CallStatus fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < CallStatus.values.size(); ++v0) {
                Object v1 = CallStatus.values.elementAt(v0);
                if(((CallStatus)v1).mValue == arg3) {
                    return ((CallStatus)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("CallStatus not found [");
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

    int getCallDuration();

    String getCallId();

    CallDirection getDirection();

    LinphoneAddress getFrom();

    String getStartDate();

    CallStatus getStatus();

    long getTimestamp();

    LinphoneAddress getTo();

    boolean wasConference();
}

