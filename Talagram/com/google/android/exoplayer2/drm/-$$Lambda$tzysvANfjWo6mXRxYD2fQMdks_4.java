package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher$Event;

public final class -$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4 implements Event {
    static {
        -$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4.INSTANCE = new -$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4();
    }

    private -$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4() {
        super();
    }

    public final void sendTo(Object arg1) {
        ((DefaultDrmSessionEventListener)arg1).onDrmKeysRestored();
    }
}

