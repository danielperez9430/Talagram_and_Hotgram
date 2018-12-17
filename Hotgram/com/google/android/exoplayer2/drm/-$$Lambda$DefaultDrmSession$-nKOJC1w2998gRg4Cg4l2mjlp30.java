package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher$Event;

public final class -$$Lambda$DefaultDrmSession$-nKOJC1w2998gRg4Cg4l2mjlp30 implements Event {
    public -$$Lambda$DefaultDrmSession$-nKOJC1w2998gRg4Cg4l2mjlp30(Exception arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void sendTo(Object arg2) {
        DefaultDrmSession.lambda$onError$0(this.f$0, ((DefaultDrmSessionEventListener)arg2));
    }
}

