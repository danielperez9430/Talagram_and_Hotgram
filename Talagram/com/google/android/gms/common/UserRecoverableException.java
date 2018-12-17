package com.google.android.gms.common;

import android.content.Intent;

public class UserRecoverableException extends Exception {
    private final Intent mIntent;

    public UserRecoverableException(String arg1, Intent arg2) {
        super(arg1);
        this.mIntent = arg2;
    }

    public Intent getIntent() {
        return new Intent(this.mIntent);
    }
}

