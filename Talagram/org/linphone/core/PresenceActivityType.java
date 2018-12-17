package org.linphone.core;

public enum PresenceActivityType {
    public static final enum PresenceActivityType Appointment;
    public static final enum PresenceActivityType Away;
    public static final enum PresenceActivityType Breakfast;
    public static final enum PresenceActivityType Busy;
    public static final enum PresenceActivityType Dinner;
    public static final enum PresenceActivityType Holiday;
    public static final enum PresenceActivityType InTransit;
    public static final enum PresenceActivityType Invalid;
    public static final enum PresenceActivityType LookingForWork;
    public static final enum PresenceActivityType Lunch;
    public static final enum PresenceActivityType Meal;
    public static final enum PresenceActivityType Meeting;
    public static final enum PresenceActivityType Offline;
    public static final enum PresenceActivityType OnThePhone;
    public static final enum PresenceActivityType Online;
    public static final enum PresenceActivityType Other;
    public static final enum PresenceActivityType Performance;
    public static final enum PresenceActivityType PermanentAbsence;
    public static final enum PresenceActivityType Playing;
    public static final enum PresenceActivityType Presentation;
    public static final enum PresenceActivityType Shopping;
    public static final enum PresenceActivityType Sleeping;
    public static final enum PresenceActivityType Spectator;
    public static final enum PresenceActivityType Steering;
    public static final enum PresenceActivityType TV;
    public static final enum PresenceActivityType Travel;
    public static final enum PresenceActivityType Unknown;
    public static final enum PresenceActivityType Vacation;
    public static final enum PresenceActivityType Working;
    public static final enum PresenceActivityType Worship;
    protected final int mValue;

    static {
        PresenceActivityType.Offline = new PresenceActivityType("Offline", 0, 0);
        PresenceActivityType.Online = new PresenceActivityType("Online", 1, 1);
        PresenceActivityType.Appointment = new PresenceActivityType("Appointment", 2, 2);
        PresenceActivityType.Away = new PresenceActivityType("Away", 3, 3);
        PresenceActivityType.Breakfast = new PresenceActivityType("Breakfast", 4, 4);
        PresenceActivityType.Busy = new PresenceActivityType("Busy", 5, 5);
        PresenceActivityType.Dinner = new PresenceActivityType("Dinner", 6, 6);
        PresenceActivityType.Holiday = new PresenceActivityType("Holiday", 7, 7);
        PresenceActivityType.InTransit = new PresenceActivityType("InTransit", 8, 8);
        PresenceActivityType.LookingForWork = new PresenceActivityType("LookingForWork", 9, 9);
        PresenceActivityType.Lunch = new PresenceActivityType("Lunch", 10, 10);
        PresenceActivityType.Meal = new PresenceActivityType("Meal", 11, 11);
        PresenceActivityType.Meeting = new PresenceActivityType("Meeting", 12, 12);
        PresenceActivityType.OnThePhone = new PresenceActivityType("OnThePhone", 13, 13);
        PresenceActivityType.Other = new PresenceActivityType("Other", 14, 14);
        PresenceActivityType.Performance = new PresenceActivityType("Performance", 15, 15);
        PresenceActivityType.PermanentAbsence = new PresenceActivityType("PermanentAbsence", 16, 16);
        PresenceActivityType.Playing = new PresenceActivityType("Playing", 17, 17);
        PresenceActivityType.Presentation = new PresenceActivityType("Presentation", 18, 18);
        PresenceActivityType.Shopping = new PresenceActivityType("Shopping", 19, 19);
        PresenceActivityType.Sleeping = new PresenceActivityType("Sleeping", 20, 20);
        PresenceActivityType.Spectator = new PresenceActivityType("Spectator", 21, 21);
        PresenceActivityType.Steering = new PresenceActivityType("Steering", 22, 22);
        PresenceActivityType.Travel = new PresenceActivityType("Travel", 23, 23);
        PresenceActivityType.TV = new PresenceActivityType("TV", 24, 24);
        PresenceActivityType.Unknown = new PresenceActivityType("Unknown", 25, 25);
        PresenceActivityType.Vacation = new PresenceActivityType("Vacation", 26, 26);
        PresenceActivityType.Working = new PresenceActivityType("Working", 27, 27);
        PresenceActivityType.Worship = new PresenceActivityType("Worship", 28, 28);
        PresenceActivityType.Invalid = new PresenceActivityType("Invalid", 29, 29);
        PresenceActivityType.$VALUES = new PresenceActivityType[]{PresenceActivityType.Offline, PresenceActivityType.Online, PresenceActivityType.Appointment, PresenceActivityType.Away, PresenceActivityType.Breakfast, PresenceActivityType.Busy, PresenceActivityType.Dinner, PresenceActivityType.Holiday, PresenceActivityType.InTransit, PresenceActivityType.LookingForWork, PresenceActivityType.Lunch, PresenceActivityType.Meal, PresenceActivityType.Meeting, PresenceActivityType.OnThePhone, PresenceActivityType.Other, PresenceActivityType.Performance, PresenceActivityType.PermanentAbsence, PresenceActivityType.Playing, PresenceActivityType.Presentation, PresenceActivityType.Shopping, PresenceActivityType.Sleeping, PresenceActivityType.Spectator, PresenceActivityType.Steering, PresenceActivityType.Travel, PresenceActivityType.TV, PresenceActivityType.Unknown, PresenceActivityType.Vacation, PresenceActivityType.Working, PresenceActivityType.Worship, PresenceActivityType.Invalid};
    }

    private PresenceActivityType(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.mValue = arg3;
    }

    protected static PresenceActivityType fromInt(int arg0) {
        switch(arg0) {
            case 0: {
                goto label_59;
            }
            case 1: {
                goto label_57;
            }
            case 2: {
                goto label_55;
            }
            case 3: {
                goto label_53;
            }
            case 4: {
                goto label_51;
            }
            case 5: {
                goto label_49;
            }
            case 6: {
                goto label_47;
            }
            case 7: {
                goto label_45;
            }
            case 8: {
                goto label_43;
            }
            case 9: {
                goto label_41;
            }
            case 10: {
                goto label_39;
            }
            case 11: {
                goto label_37;
            }
            case 12: {
                goto label_35;
            }
            case 13: {
                goto label_33;
            }
            case 14: {
                goto label_31;
            }
            case 15: {
                goto label_29;
            }
            case 16: {
                goto label_27;
            }
            case 17: {
                goto label_25;
            }
            case 18: {
                goto label_23;
            }
            case 19: {
                goto label_21;
            }
            case 20: {
                goto label_19;
            }
            case 21: {
                goto label_17;
            }
            case 22: {
                goto label_15;
            }
            case 23: {
                goto label_13;
            }
            case 24: {
                goto label_11;
            }
            case 25: {
                goto label_9;
            }
            case 26: {
                goto label_7;
            }
            case 27: {
                goto label_5;
            }
            case 28: {
                goto label_3;
            }
        }

        return PresenceActivityType.Invalid;
    label_33:
        return PresenceActivityType.OnThePhone;
    label_35:
        return PresenceActivityType.Meeting;
    label_37:
        return PresenceActivityType.Meal;
    label_39:
        return PresenceActivityType.Lunch;
    label_41:
        return PresenceActivityType.LookingForWork;
    label_43:
        return PresenceActivityType.InTransit;
    label_45:
        return PresenceActivityType.Holiday;
    label_47:
        return PresenceActivityType.Dinner;
    label_49:
        return PresenceActivityType.Busy;
    label_51:
        return PresenceActivityType.Breakfast;
    label_53:
        return PresenceActivityType.Away;
    label_55:
        return PresenceActivityType.Appointment;
    label_57:
        return PresenceActivityType.Online;
    label_59:
        return PresenceActivityType.Offline;
    label_3:
        return PresenceActivityType.Worship;
    label_5:
        return PresenceActivityType.Working;
    label_7:
        return PresenceActivityType.Vacation;
    label_9:
        return PresenceActivityType.Unknown;
    label_11:
        return PresenceActivityType.TV;
    label_13:
        return PresenceActivityType.Travel;
    label_15:
        return PresenceActivityType.Steering;
    label_17:
        return PresenceActivityType.Spectator;
    label_19:
        return PresenceActivityType.Sleeping;
    label_21:
        return PresenceActivityType.Shopping;
    label_23:
        return PresenceActivityType.Presentation;
    label_25:
        return PresenceActivityType.Playing;
    label_27:
        return PresenceActivityType.PermanentAbsence;
    label_29:
        return PresenceActivityType.Performance;
    label_31:
        return PresenceActivityType.Other;
    }

    public int toInt() {
        return this.mValue;
    }

    public static PresenceActivityType valueOf(String arg1) {
        return Enum.valueOf(PresenceActivityType.class, arg1);
    }

    public static PresenceActivityType[] values() {
        // Method was not decompiled
    }
}

