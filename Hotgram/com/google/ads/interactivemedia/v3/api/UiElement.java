package com.google.ads.interactivemedia.v3.api;

import com.google.ads.interactivemedia.v3.impl.data.r;

public interface UiElement {
    public static final UiElement AD_ATTRIBUTION;
    public static final UiElement COUNTDOWN;

    static {
        UiElement.AD_ATTRIBUTION = new r("adAttribution");
        UiElement.COUNTDOWN = new r("countdown");
    }

    String getName();
}

