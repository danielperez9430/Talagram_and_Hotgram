package com.google.android.gms.common.api;

public class ApiException extends Exception {
    protected final Status mStatus;

    public ApiException(Status arg5) {
        int v0 = arg5.getStatusCode();
        String v1 = arg5.getStatusMessage() != null ? arg5.getStatusMessage() : "";
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 13);
        v3.append(v0);
        v3.append(": ");
        v3.append(v1);
        super(v3.toString());
        this.mStatus = arg5;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    @Deprecated public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}

