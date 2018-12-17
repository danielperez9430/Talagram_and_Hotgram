package com.google.android.exoplayer2.source;

public final class -$$Lambda$MediaSourceEventListener$EventDispatcher$ES4FdQzWtupQEe6zuV_1M9-f9xU implements Runnable {
    public -$$Lambda$MediaSourceEventListener$EventDispatcher$ES4FdQzWtupQEe6zuV_1M9-f9xU(EventDispatcher arg1, MediaSourceEventListener arg2, MediaLoadData arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        EventDispatcher.lambda$downstreamFormatChanged$8(this.f$0, this.f$1, this.f$2);
    }
}

