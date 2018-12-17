package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;

public class id implements AdErrorEvent {
    private final AdError a;
    private final Object b;

    id(AdError arg1) {
        super();
        this.a = arg1;
        this.b = null;
    }

    id(AdError arg1, Object arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public AdError getError() {
        return this.a;
    }

    public Object getUserRequestContext() {
        return this.b;
    }
}

