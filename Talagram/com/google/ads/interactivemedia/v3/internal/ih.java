package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.player.AdProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;

public class ih extends ji {
    protected final AdProgressProvider a;

    public ih(AdProgressProvider arg1, long arg2) {
        super(arg2);
        this.a = arg1;
    }

    public VideoProgressUpdate a() {
        return this.a.getAdProgress();
    }
}

