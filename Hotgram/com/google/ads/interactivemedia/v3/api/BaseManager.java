package com.google.ads.interactivemedia.v3.api;

import com.google.ads.interactivemedia.v3.api.player.AdProgressProvider;

public interface BaseManager extends AdProgressProvider {
    void addAdErrorListener(AdErrorListener arg1);

    void addAdEventListener(AdEventListener arg1);

    void destroy();

    AdProgressInfo getAdProgressInfo();

    Ad getCurrentAd();

    void init();

    void init(AdsRenderingSettings arg1);

    boolean isCustomPlaybackUsed();

    void removeAdErrorListener(AdErrorListener arg1);

    void removeAdEventListener(AdEventListener arg1);
}

