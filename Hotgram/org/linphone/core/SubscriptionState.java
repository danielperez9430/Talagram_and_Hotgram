package org.linphone.core;

public enum SubscriptionState {
    public static final enum SubscriptionState Active;
    public static final enum SubscriptionState Error;
    public static final enum SubscriptionState Expiring;
    public static final enum SubscriptionState IncomingReceived;
    public static final enum SubscriptionState None;
    public static final enum SubscriptionState OutoingProgress;
    public static final enum SubscriptionState Pending;
    public static final enum SubscriptionState Terminated;
    protected final int mValue;

    static {
        SubscriptionState.None = new SubscriptionState("None", 0, 0);
        SubscriptionState.OutoingProgress = new SubscriptionState("OutoingProgress", 1, 1);
        SubscriptionState.IncomingReceived = new SubscriptionState("IncomingReceived", 2, 2);
        SubscriptionState.Pending = new SubscriptionState("Pending", 3, 3);
        SubscriptionState.Active = new SubscriptionState("Active", 4, 4);
        SubscriptionState.Terminated = new SubscriptionState("Terminated", 5, 5);
        SubscriptionState.Error = new SubscriptionState("Error", 6, 6);
        SubscriptionState.Expiring = new SubscriptionState("Expiring", 7, 7);
        SubscriptionState.$VALUES = new SubscriptionState[]{SubscriptionState.None, SubscriptionState.OutoingProgress, SubscriptionState.IncomingReceived, SubscriptionState.Pending, SubscriptionState.Active, SubscriptionState.Terminated, SubscriptionState.Error, SubscriptionState.Expiring};
    }

    private SubscriptionState(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.mValue = arg3;
    }

    protected static SubscriptionState fromInt(int arg3) {
        switch(arg3) {
            case 0: {
                goto label_26;
            }
            case 1: {
                goto label_24;
            }
            case 2: {
                goto label_22;
            }
            case 3: {
                goto label_20;
            }
            case 4: {
                goto label_18;
            }
            case 5: {
                goto label_16;
            }
            case 6: {
                goto label_14;
            }
            case 7: {
                goto label_12;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unhandled enum value ");
        v1.append(arg3);
        v1.append(" for SubscriptionState");
        throw new LinphoneCoreException(v1.toString());
    label_18:
        return SubscriptionState.Active;
    label_20:
        return SubscriptionState.Pending;
    label_22:
        return SubscriptionState.IncomingReceived;
    label_24:
        return SubscriptionState.OutoingProgress;
    label_26:
        return SubscriptionState.None;
    label_12:
        return SubscriptionState.Expiring;
    label_14:
        return SubscriptionState.Error;
    label_16:
        return SubscriptionState.Terminated;
    }

    public static SubscriptionState valueOf(String arg1) {
        return Enum.valueOf(SubscriptionState.class, arg1);
    }

    public static SubscriptionState[] values() {
        // Method was not decompiled
    }
}

