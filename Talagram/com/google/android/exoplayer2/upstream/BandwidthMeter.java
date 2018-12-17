package com.google.android.exoplayer2.upstream;

import android.os.Handler;

public interface BandwidthMeter {
    public interface EventListener {
        void onBandwidthSample(int arg1, long arg2, long arg3);
    }

    void addEventListener(Handler arg1, EventListener arg2);

    long getBitrateEstimate();

    TransferListener getTransferListener();

    void removeEventListener(EventListener arg1);
}

