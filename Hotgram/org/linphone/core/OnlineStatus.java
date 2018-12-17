package org.linphone.core;

import java.util.Vector;

@Deprecated public class OnlineStatus {
    public static OnlineStatus Away;
    public static OnlineStatus BeRightBack;
    public static OnlineStatus Busy;
    public static OnlineStatus DoNotDisturb;
    public static OnlineStatus Offline;
    public static OnlineStatus OnThePhone;
    public static OnlineStatus Online;
    public static OnlineStatus OutToLunch;
    public static OnlineStatus Pending;
    public static OnlineStatus StatusAltService;
    public static OnlineStatus StatusMoved;
    private final String mStringValue;
    protected final int mValue;
    private static Vector values;

    static {
        OnlineStatus.values = new Vector();
        OnlineStatus.Offline = new OnlineStatus(0, "Offline");
        OnlineStatus.Online = new OnlineStatus(1, "Online");
        OnlineStatus.Busy = new OnlineStatus(2, "Busy");
        OnlineStatus.BeRightBack = new OnlineStatus(3, "BeRightBack");
        OnlineStatus.Away = new OnlineStatus(4, "Away");
        OnlineStatus.OnThePhone = new OnlineStatus(5, "OnThePhone");
        OnlineStatus.OutToLunch = new OnlineStatus(6, "OutToLunch ");
        OnlineStatus.DoNotDisturb = new OnlineStatus(7, "DoNotDisturb");
        OnlineStatus.StatusMoved = new OnlineStatus(8, "StatusMoved");
        OnlineStatus.StatusAltService = new OnlineStatus(9, "StatusAltService");
        OnlineStatus.Pending = new OnlineStatus(10, "Pending");
    }

    private OnlineStatus(int arg1, String arg2) {
        super();
        this.mValue = arg1;
        OnlineStatus.values.addElement(this);
        this.mStringValue = arg2;
    }

    public static OnlineStatus fromInt(int arg3) {
        int v0;
        for(v0 = 0; v0 < OnlineStatus.values.size(); ++v0) {
            Object v1 = OnlineStatus.values.elementAt(v0);
            if(((OnlineStatus)v1).mValue == arg3) {
                return ((OnlineStatus)v1);
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
}

