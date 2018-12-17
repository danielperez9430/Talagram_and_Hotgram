package org.linphone.core;

public enum ToneID {
    public static final enum ToneID Busy;
    public static final enum ToneID CallLost;
    public static final enum ToneID CallOnHold;
    public static final enum ToneID CallWaiting;
    public static final enum ToneID Undefined;
    protected final int mValue;

    static {
        ToneID.Undefined = new ToneID("Undefined", 0, 0);
        ToneID.Busy = new ToneID("Busy", 1, 1);
        ToneID.CallWaiting = new ToneID("CallWaiting", 2, 2);
        ToneID.CallOnHold = new ToneID("CallOnHold", 3, 3);
        ToneID.CallLost = new ToneID("CallLost", 4, 4);
        ToneID.$VALUES = new ToneID[]{ToneID.Undefined, ToneID.Busy, ToneID.CallWaiting, ToneID.CallOnHold, ToneID.CallLost};
    }

    private ToneID(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.mValue = arg3;
    }

    protected static ToneID fromInt(int arg3) {
        switch(arg3) {
            case 0: {
                goto label_20;
            }
            case 1: {
                goto label_18;
            }
            case 2: {
                goto label_16;
            }
            case 3: {
                goto label_14;
            }
            case 4: {
                goto label_12;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unhandled enum value ");
        v1.append(arg3);
        v1.append(" for LinphoneToneID");
        throw new LinphoneCoreException(v1.toString());
    label_18:
        return ToneID.Busy;
    label_20:
        return ToneID.Undefined;
    label_12:
        return ToneID.CallLost;
    label_14:
        return ToneID.CallOnHold;
    label_16:
        return ToneID.CallWaiting;
    }

    public static ToneID valueOf(String arg1) {
        return Enum.valueOf(ToneID.class, arg1);
    }

    public static ToneID[] values() {
        // Method was not decompiled
    }
}

