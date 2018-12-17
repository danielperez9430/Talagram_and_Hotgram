package org.linphone.core;

public enum PresenceBasicStatus {
    public static final enum PresenceBasicStatus Closed;
    public static final enum PresenceBasicStatus Invalid;
    public static final enum PresenceBasicStatus Open;
    protected final int mValue;

    static {
        PresenceBasicStatus.Open = new PresenceBasicStatus("Open", 0, 0);
        PresenceBasicStatus.Closed = new PresenceBasicStatus("Closed", 1, 1);
        PresenceBasicStatus.Invalid = new PresenceBasicStatus("Invalid", 2, 2);
        PresenceBasicStatus.$VALUES = new PresenceBasicStatus[]{PresenceBasicStatus.Open, PresenceBasicStatus.Closed, PresenceBasicStatus.Invalid};
    }

    private PresenceBasicStatus(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.mValue = arg3;
    }

    protected static PresenceBasicStatus fromInt(int arg0) {
        switch(arg0) {
            case 0: {
                goto label_5;
            }
            case 1: {
                goto label_3;
            }
        }

        return PresenceBasicStatus.Invalid;
    label_3:
        return PresenceBasicStatus.Closed;
    label_5:
        return PresenceBasicStatus.Open;
    }

    public int toInt() {
        return this.mValue;
    }

    public static PresenceBasicStatus valueOf(String arg1) {
        return Enum.valueOf(PresenceBasicStatus.class, arg1);
    }

    public static PresenceBasicStatus[] values() {
        // Method was not decompiled
    }
}

