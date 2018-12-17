package org.linphone.core;

public enum PublishState {
    public static final enum PublishState Cleared;
    public static final enum PublishState Error;
    public static final enum PublishState Expiring;
    public static final enum PublishState None;
    public static final enum PublishState Ok;
    public static final enum PublishState Progress;
    protected final int mValue;

    static {
        PublishState.None = new PublishState("None", 0, 0);
        PublishState.Progress = new PublishState("Progress", 1, 1);
        PublishState.Ok = new PublishState("Ok", 2, 2);
        PublishState.Error = new PublishState("Error", 3, 3);
        PublishState.Expiring = new PublishState("Expiring", 4, 4);
        PublishState.Cleared = new PublishState("Cleared", 5, 5);
        PublishState.$VALUES = new PublishState[]{PublishState.None, PublishState.Progress, PublishState.Ok, PublishState.Error, PublishState.Expiring, PublishState.Cleared};
    }

    private PublishState(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.mValue = arg3;
    }

    protected static PublishState fromInt(int arg3) {
        switch(arg3) {
            case 0: {
                goto label_22;
            }
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_18;
            }
            case 3: {
                goto label_16;
            }
            case 4: {
                goto label_14;
            }
            case 5: {
                goto label_12;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unhandled enum value ");
        v1.append(arg3);
        v1.append(" for PublishState");
        throw new LinphoneCoreException(v1.toString());
    label_18:
        return PublishState.Ok;
    label_20:
        return PublishState.Progress;
    label_22:
        return PublishState.None;
    label_12:
        return PublishState.Cleared;
    label_14:
        return PublishState.Expiring;
    label_16:
        return PublishState.Error;
    }

    public static PublishState valueOf(String arg1) {
        return Enum.valueOf(PublishState.class, arg1);
    }

    public static PublishState[] values() {
        // Method was not decompiled
    }
}

