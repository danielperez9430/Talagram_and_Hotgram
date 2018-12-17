package com.google.android.exoplayer2.source;

import java.io.IOException;

public final class -$$Lambda$MediaSourceEventListener$EventDispatcher$0X-TAsNqR4TUW1yA_ZD1_p3oT84 implements Runnable {
    public -$$Lambda$MediaSourceEventListener$EventDispatcher$0X-TAsNqR4TUW1yA_ZD1_p3oT84(EventDispatcher arg1, MediaSourceEventListener arg2, LoadEventInfo arg3, MediaLoadData arg4, IOException arg5, boolean arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        EventDispatcher.lambda$loadError$5(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

