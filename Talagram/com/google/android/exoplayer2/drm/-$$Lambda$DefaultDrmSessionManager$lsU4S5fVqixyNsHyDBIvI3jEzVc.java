package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher$Event;

public final class -$$Lambda$DefaultDrmSessionManager$lsU4S5fVqixyNsHyDBIvI3jEzVc implements Event {
    public -$$Lambda$DefaultDrmSessionManager$lsU4S5fVqixyNsHyDBIvI3jEzVc(MissingSchemeDataException arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void sendTo(Object arg2) {
        DefaultDrmSessionManager.lambda$acquireSession$0(this.f$0, ((DefaultDrmSessionEventListener)arg2));
    }
}

