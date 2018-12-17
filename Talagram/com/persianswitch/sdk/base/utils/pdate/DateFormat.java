package com.persianswitch.sdk.base.utils.pdate;

public enum DateFormat {
    public static final enum DateFormat a;
    public static final enum DateFormat b;
    public static final enum DateFormat c;

    static {
        DateFormat.a = new DateFormat("NOT_SPECIFY", 0);
        DateFormat.b = new DateFormat("PERSIAN", 1);
        DateFormat.c = new DateFormat("GREGORIAN", 2);
        DateFormat.d = new DateFormat[]{DateFormat.a, DateFormat.b, DateFormat.c};
    }

    private DateFormat(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static DateFormat valueOf(String arg1) {
        return Enum.valueOf(DateFormat.class, arg1);
    }

    public static DateFormat[] values() {
        // Method was not decompiled
    }
}

