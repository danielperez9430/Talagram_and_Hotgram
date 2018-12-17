package org.linphone.core;

import java.util.Vector;

public class Reason {
    public static Reason AddressIncomplete;
    public static Reason BadCredentials;
    public static Reason BadGateway;
    public static Reason Busy;
    public static Reason Declined;
    public static Reason DoNotDisturb;
    public static Reason Gone;
    public static Reason IOError;
    public static Reason Media;
    public static Reason MovedPermanently;
    public static Reason NoMatch;
    public static Reason NoResponse;
    public static Reason None;
    public static Reason NotAcceptable;
    public static Reason NotAnswered;
    public static Reason NotFound;
    public static Reason NotImplemented;
    public static Reason ServerTimeout;
    public static Reason TemporarilyUnavailable;
    public static Reason Unauthorized;
    public static Reason Unknown;
    private final String mStringValue;
    protected final int mValue;
    private static Vector values;

    static {
        Reason.values = new Vector();
        Reason.None = new Reason(0, "None");
        Reason.NoResponse = new Reason(1, "NoResponse");
        Reason.BadCredentials = new Reason(2, "BadCredentials");
        Reason.Declined = new Reason(3, "Declined");
        Reason.NotFound = new Reason(4, "NotFound");
        Reason.NotAnswered = new Reason(5, "NotAnswered");
        Reason.Busy = new Reason(6, "Busy");
        Reason.Media = new Reason(7, "Media");
        Reason.IOError = new Reason(8, "IOError");
        Reason.DoNotDisturb = new Reason(9, "DoNotDisturb");
        Reason.Unauthorized = new Reason(10, "Unauthorized");
        Reason.NotAcceptable = new Reason(11, "NotAcceptable");
        Reason.NoMatch = new Reason(12, "NoMatch");
        Reason.MovedPermanently = new Reason(13, "MovedPermanently");
        Reason.Gone = new Reason(14, "Gone");
        Reason.TemporarilyUnavailable = new Reason(15, "TemporarilyUnavailable");
        Reason.AddressIncomplete = new Reason(16, "AddressIncomplete");
        Reason.NotImplemented = new Reason(17, "NotImplemented");
        Reason.BadGateway = new Reason(18, "BadGateway");
        Reason.ServerTimeout = new Reason(19, "ServerTimeout");
        Reason.Unknown = new Reason(20, "Unknown");
    }

    private Reason(int arg1, String arg2) {
        super();
        this.mValue = arg1;
        Reason.values.addElement(this);
        this.mStringValue = arg2;
    }

    public static Reason fromInt(int arg3) {
        int v0;
        for(v0 = 0; v0 < Reason.values.size(); ++v0) {
            Object v1 = Reason.values.elementAt(v0);
            if(((Reason)v1).mValue == arg3) {
                return ((Reason)v1);
            }
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Reason not found [");
        v1_1.append(arg3);
        v1_1.append("]");
        throw new RuntimeException(v1_1.toString());
    }

    public String toString() {
        return this.mStringValue;
    }
}

