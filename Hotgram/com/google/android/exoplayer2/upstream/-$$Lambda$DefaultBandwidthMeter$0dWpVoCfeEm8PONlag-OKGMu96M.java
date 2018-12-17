package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.EventDispatcher$Event;

public final class -$$Lambda$DefaultBandwidthMeter$0dWpVoCfeEm8PONlag-OKGMu96M implements Event {
    public -$$Lambda$DefaultBandwidthMeter$0dWpVoCfeEm8PONlag-OKGMu96M(int arg1, long arg2, long arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
    }

    public final void sendTo(Object arg7) {
        DefaultBandwidthMeter.lambda$notifyBandwidthSample$0(this.f$0, this.f$1, this.f$2, arg7);
    }
}

