package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zzbq;

    public GooglePlayServicesRepairableException(int arg1, String arg2, Intent arg3) {
        super(arg2, arg3);
        this.zzbq = arg1;
    }

    public int getConnectionStatusCode() {
        return this.zzbq;
    }
}

