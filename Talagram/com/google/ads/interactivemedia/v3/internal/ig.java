package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdProgressInfo;

public class ig implements AdProgressInfo {
    private final double a;
    private final double b;
    private final int c;
    private final int d;
    private final double e;

    ig(double arg1, double arg3, int arg5, int arg6, double arg7) {
        super();
        this.a = arg1;
        this.b = arg3;
        this.c = arg5;
        this.d = arg6;
        this.e = arg7;
    }

    public double getAdBreakDuration() {
        return this.e;
    }

    public int getAdPosition() {
        return this.c;
    }

    public double getCurrentTime() {
        return this.a;
    }

    public double getDuration() {
        return this.b;
    }

    public int getTotalAds() {
        return this.d;
    }
}

