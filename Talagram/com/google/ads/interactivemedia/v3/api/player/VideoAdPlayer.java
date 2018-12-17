package com.google.ads.interactivemedia.v3.api.player;

public interface VideoAdPlayer extends AdProgressProvider, VolumeProvider {
    public interface VideoAdPlayerCallback {
        void onEnded();

        void onError();

        void onLoaded();

        void onPause();

        void onPlay();

        void onResume();

        void onVolumeChanged(int arg1);
    }

    void addCallback(VideoAdPlayerCallback arg1);

    void loadAd(String arg1);

    void pauseAd();

    void playAd();

    void removeCallback(VideoAdPlayerCallback arg1);

    @Deprecated void resumeAd();

    void stopAd();
}

