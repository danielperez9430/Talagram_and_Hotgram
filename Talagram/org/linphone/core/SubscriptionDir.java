package org.linphone.core;

public enum SubscriptionDir {
    public static final enum SubscriptionDir Incoming;
    public static final enum SubscriptionDir Invalid;
    public static final enum SubscriptionDir Outgoing;
    protected final int mValue;

    static {
        SubscriptionDir.Incoming = new SubscriptionDir("Incoming", 0, 0);
        SubscriptionDir.Outgoing = new SubscriptionDir("Outgoing", 1, 1);
        SubscriptionDir.Invalid = new SubscriptionDir("Invalid", 2, 2);
        SubscriptionDir.$VALUES = new SubscriptionDir[]{SubscriptionDir.Incoming, SubscriptionDir.Outgoing, SubscriptionDir.Invalid};
    }

    private SubscriptionDir(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.mValue = arg3;
    }

    protected static SubscriptionDir fromInt(int arg0) {
        switch(arg0) {
            case 0: {
                goto label_5;
            }
            case 1: {
                goto label_3;
            }
        }

        return SubscriptionDir.Invalid;
    label_3:
        return SubscriptionDir.Outgoing;
    label_5:
        return SubscriptionDir.Incoming;
    }

    public static SubscriptionDir valueOf(String arg1) {
        return Enum.valueOf(SubscriptionDir.class, arg1);
    }

    public static SubscriptionDir[] values() {
        // Method was not decompiled
    }
}

