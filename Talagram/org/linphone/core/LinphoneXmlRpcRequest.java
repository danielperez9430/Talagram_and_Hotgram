package org.linphone.core;

import java.util.Vector;

public interface LinphoneXmlRpcRequest {
    public class ArgType {
        public static final ArgType Int;
        public static final ArgType None;
        public static final ArgType String;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            ArgType.values = new Vector();
            ArgType.None = new ArgType(0, "None");
            ArgType.Int = new ArgType(1, "Int");
            ArgType.String = new ArgType(2, "String");
        }

        private ArgType(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            ArgType.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static ArgType fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < ArgType.values.size(); ++v0) {
                Object v1 = ArgType.values.elementAt(v0);
                if(((ArgType)v1).mValue == arg3) {
                    return ((ArgType)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("ArgType not found [");
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

    public interface LinphoneXmlRpcRequestListener {
        void onXmlRpcRequestResponse(LinphoneXmlRpcRequest arg1);
    }

    public class Status {
        public static final Status Failed;
        public static final Status Ok;
        public static final Status Pending;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            Status.values = new Vector();
            Status.Pending = new Status(0, "Pending");
            Status.Ok = new Status(1, "Ok");
            Status.Failed = new Status(2, "Failed");
        }

        private Status(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            Status.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static Status fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < Status.values.size(); ++v0) {
                Object v1 = Status.values.elementAt(v0);
                if(((Status)v1).mValue == arg3) {
                    return ((Status)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Status not found [");
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

    void addIntArg(int arg1);

    void addStringArg(String arg1);

    String getContent();

    int getIntResponse();

    Status getStatus();

    String getStringResponse();

    void setListener(LinphoneXmlRpcRequestListener arg1);
}

