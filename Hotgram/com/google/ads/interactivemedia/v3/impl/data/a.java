package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a=d.class) public abstract class a {
    public interface com.google.ads.interactivemedia.v3.impl.data.a$a {
        com.google.ads.interactivemedia.v3.impl.data.a$a appState(String arg1);

        a build();

        com.google.ads.interactivemedia.v3.impl.data.a$a eventId(String arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a nativeTime(long arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewAttached(boolean arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewBounds(m arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewHidden(boolean arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewVisibleBounds(m arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a nativeVolume(double arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a queryId(String arg1);

        com.google.ads.interactivemedia.v3.impl.data.a$a vastEvent(String arg1);
    }

    public a() {
        super();
    }

    public abstract String appState();

    public static com.google.ads.interactivemedia.v3.impl.data.a$a builder() {
        return new com.google.ads.interactivemedia.v3.impl.data.d$a();
    }

    public abstract String eventId();

    public abstract long nativeTime();

    public abstract boolean nativeViewAttached();

    public abstract m nativeViewBounds();

    public abstract boolean nativeViewHidden();

    public abstract m nativeViewVisibleBounds();

    public abstract double nativeVolume();

    public abstract String queryId();

    public abstract String vastEvent();
}

