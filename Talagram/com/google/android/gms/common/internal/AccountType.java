package com.google.android.gms.common.internal;

public final class AccountType {
    public static final String[] ACCEPTABLE_ACCOUNT_TYPES = null;
    public static final String GOOGLE = "com.google";
    public static final String SIDEWINDER = "cn.google";
    public static final String WORK = "com.google.work";

    static {
        AccountType.ACCEPTABLE_ACCOUNT_TYPES = new String[]{"com.google", "com.google.work", "cn.google"};
    }

    private AccountType() {
        super();
    }
}

