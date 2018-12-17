package com.crashlytics.android.a;

final class ae {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final Boolean f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    private String m;

    public ae(String arg1, String arg2, String arg3, String arg4, String arg5, Boolean arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
        this.g = arg7;
        this.h = arg8;
        this.i = arg9;
        this.j = arg10;
        this.k = arg11;
        this.l = arg12;
    }

    public String toString() {
        if(this.m == null) {
            this.m = "appBundleId=" + this.a + ", executionId=" + this.b + ", installationId=" + this.c + ", androidId=" + this.d + ", advertisingId=" + this.e + ", limitAdTrackingEnabled=" + this.f + ", betaDeviceToken=" + this.g + ", buildId=" + this.h + ", osVersion=" + this.i + ", deviceModel=" + this.j + ", appVersionCode=" + this.k + ", appVersionName=" + this.l;
        }

        return this.m;
    }
}

