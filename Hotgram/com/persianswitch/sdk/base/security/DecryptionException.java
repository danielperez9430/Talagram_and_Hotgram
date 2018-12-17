package com.persianswitch.sdk.base.security;

public class DecryptionException extends SecurityException {
    private final boolean a;

    public DecryptionException(String arg1, boolean arg2) {
        super(arg1);
        this.a = arg2;
    }

    public boolean a() {
        return this.a;
    }
}

