package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher$Event;

public final class -$$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M implements Event {
    static {
        -$$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M.INSTANCE = new -$$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M();
    }

    private -$$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M() {
        super();
    }

    public final void sendTo(Object arg1) {
        ((DefaultDrmSessionEventListener)arg1).onDrmKeysLoaded();
    }
}

