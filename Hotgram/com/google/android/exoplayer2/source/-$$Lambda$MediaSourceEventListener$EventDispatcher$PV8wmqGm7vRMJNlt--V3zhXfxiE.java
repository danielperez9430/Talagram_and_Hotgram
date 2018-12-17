package com.google.android.exoplayer2.source;

public final class -$$Lambda$MediaSourceEventListener$EventDispatcher$PV8wmqGm7vRMJNlt--V3zhXfxiE implements Runnable {
    public -$$Lambda$MediaSourceEventListener$EventDispatcher$PV8wmqGm7vRMJNlt--V3zhXfxiE(EventDispatcher arg1, MediaSourceEventListener arg2, MediaPeriodId arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        EventDispatcher.lambda$readingStarted$6(this.f$0, this.f$1, this.f$2);
    }
}

