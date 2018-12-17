package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.internal.lx;
import com.google.ads.interactivemedia.v3.internal.lz;

public class c implements AdPodInfo {
    public int adPosition;
    public boolean isBumper;
    public double maxDuration;
    public int podIndex;
    public double timeOffset;
    public int totalAds;

    public c() {
        super();
        this.totalAds = 1;
        this.adPosition = 1;
        this.isBumper = false;
        this.maxDuration = -1;
    }

    public boolean equals(Object arg2) {
        return lx.a(this, arg2, new String[0]);
    }

    public int getAdPosition() {
        return this.adPosition;
    }

    public double getMaxDuration() {
        return this.maxDuration;
    }

    public int getPodIndex() {
        return this.podIndex;
    }

    public double getTimeOffset() {
        return this.timeOffset;
    }

    public int getTotalAds() {
        return this.totalAds;
    }

    public int hashCode() {
        return lz.a(this, new String[0]);
    }

    public boolean isBumper() {
        return this.isBumper;
    }

    public String toString() {
        int v0 = this.totalAds;
        int v1 = this.adPosition;
        boolean v2 = this.isBumper;
        double v3 = this.maxDuration;
        int v5 = this.podIndex;
        double v6 = this.timeOffset;
        StringBuilder v8 = new StringBuilder(169);
        v8.append("AdPodInfo [totalAds=");
        v8.append(v0);
        v8.append(", adPosition=");
        v8.append(v1);
        v8.append(", isBumper=");
        v8.append(v2);
        v8.append(", maxDuration=");
        v8.append(v3);
        v8.append(", podIndex=");
        v8.append(v5);
        v8.append(", timeOffset=");
        v8.append(v6);
        v8.append("]");
        return v8.toString();
    }
}

