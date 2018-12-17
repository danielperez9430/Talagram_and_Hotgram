package com.google.android.gms.common.internal;

public class GmsServiceEndpoint {
    private final String mPackageName;
    private final int zztq;
    private final String zzue;
    private final boolean zzuf;

    public GmsServiceEndpoint(String arg1, String arg2, boolean arg3, int arg4) {
        super();
        this.mPackageName = arg1;
        this.zzue = arg2;
        this.zzuf = arg3;
        this.zztq = arg4;
    }

    final int getBindFlags() {
        return this.zztq;
    }

    final String getPackageName() {
        return this.mPackageName;
    }

    final String zzcw() {
        return this.zzue;
    }
}

