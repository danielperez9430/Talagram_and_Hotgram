package org.linphone.core;

public class CallDirection {
    public static CallDirection Incoming;
    public static CallDirection Outgoing;
    private String mStringValue;

    static {
        CallDirection.Outgoing = new CallDirection("CallOutgoing");
        CallDirection.Incoming = new CallDirection("Callincoming");
    }

    private CallDirection(String arg1) {
        super();
        this.mStringValue = arg1;
    }

    public String toString() {
        return this.mStringValue;
    }
}

