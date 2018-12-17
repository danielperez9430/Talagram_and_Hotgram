package com.google.ads.interactivemedia.v3.api;

public interface AdsLoader {
    public interface AdsLoadedListener {
        void onAdsManagerLoaded(AdsManagerLoadedEvent arg1);
    }

    void addAdErrorListener(AdErrorListener arg1);

    void addAdsLoadedListener(AdsLoadedListener arg1);

    void contentComplete();

    ImaSdkSettings getSettings();

    void removeAdErrorListener(AdErrorListener arg1);

    void removeAdsLoadedListener(AdsLoadedListener arg1);

    void requestAds(AdsRequest arg1);

    String requestStream(StreamRequest arg1);
}

