package com.google.android.gms.wearable;

import java.io.IOException;

public class ChannelIOException extends IOException {
    private final int zzg;
    private final int zzh;

    public ChannelIOException(String arg1, int arg2, int arg3) {
        super(arg1);
        this.zzg = arg2;
        this.zzh = arg3;
    }

    public int getAppSpecificErrorCode() {
        return this.zzh;
    }

    public int getCloseReason() {
        return this.zzg;
    }
}

