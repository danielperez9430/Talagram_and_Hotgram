package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzdr;

    public UnsupportedApiCallException(Feature arg1) {
        super();
        this.zzdr = arg1;
    }

    public final String getMessage() {
        String v0 = String.valueOf(this.zzdr);
        StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 8);
        v2.append("Missing ");
        v2.append(v0);
        return v2.toString();
    }
}

