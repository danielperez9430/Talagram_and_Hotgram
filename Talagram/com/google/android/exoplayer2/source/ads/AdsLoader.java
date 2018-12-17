package com.google.android.exoplayer2.source.ads;

import android.view.ViewGroup;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.upstream.DataSpec;
import java.io.IOException;

public interface AdsLoader {
    public interface EventListener {
        void onAdClicked();

        void onAdLoadError(AdLoadException arg1, DataSpec arg2);

        void onAdPlaybackState(AdPlaybackState arg1);

        void onAdTapped();
    }

    void attachPlayer(ExoPlayer arg1, EventListener arg2, ViewGroup arg3);

    void detachPlayer();

    void handlePrepareError(int arg1, int arg2, IOException arg3);

    void release();

    void setSupportedContentTypes(int[] arg1);
}

