package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.CuePoint;

public final class iw implements CuePoint {
    private final double a;
    private final double b;
    private final boolean c;

    iw(double arg1, double arg3, boolean arg5) {
        super();
        this.a = arg1;
        this.b = arg3;
        this.c = arg5;
    }

    public double getEndTime() {
        return this.b;
    }

    public double getStartTime() {
        return this.a;
    }

    public boolean isPlayed() {
        return this.c;
    }
}

