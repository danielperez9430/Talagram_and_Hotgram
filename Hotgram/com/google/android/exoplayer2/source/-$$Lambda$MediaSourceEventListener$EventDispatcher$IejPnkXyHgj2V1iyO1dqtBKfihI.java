package com.google.android.exoplayer2.source;

public final class -$$Lambda$MediaSourceEventListener$EventDispatcher$IejPnkXyHgj2V1iyO1dqtBKfihI implements Runnable {
    public -$$Lambda$MediaSourceEventListener$EventDispatcher$IejPnkXyHgj2V1iyO1dqtBKfihI(EventDispatcher arg1, MediaSourceEventListener arg2, LoadEventInfo arg3, MediaLoadData arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        EventDispatcher.lambda$loadCompleted$3(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

