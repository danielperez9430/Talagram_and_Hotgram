package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.StreamManager;

public class ik implements AdsManagerLoadedEvent {
    private final AdsManager a;
    private final StreamManager b;
    private final Object c;

    ik(StreamManager arg2, Object arg3) {
        super();
        this.a = null;
        this.b = arg2;
        this.c = arg3;
    }

    ik(AdsManager arg1, Object arg2) {
        super();
        this.a = arg1;
        this.b = null;
        this.c = arg2;
    }

    public AdsManager getAdsManager() {
        return this.a;
    }

    public StreamManager getStreamManager() {
        return this.b;
    }

    public Object getUserRequestContext() {
        return this.c;
    }
}

