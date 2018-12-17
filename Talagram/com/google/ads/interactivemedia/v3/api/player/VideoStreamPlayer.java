package com.google.ads.interactivemedia.v3.api.player;

import java.util.List;

public interface VideoStreamPlayer extends ContentProgressProvider, VolumeProvider {
    public interface VideoStreamPlayerCallback {
        void onUserTextReceived(String arg1);

        void onVolumeChanged(int arg1);
    }

    void addCallback(VideoStreamPlayerCallback arg1);

    int getVolume();

    void loadUrl(String arg1, List arg2);

    void onAdBreakEnded();

    void onAdBreakStarted();

    void removeCallback(VideoStreamPlayerCallback arg1);
}

