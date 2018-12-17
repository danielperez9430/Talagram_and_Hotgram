package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_channels_getParticipants;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$ProfileActivity$aJl3mEUeI8MyKvh1zhNG8gz9XDA implements Runnable {
    public -$$Lambda$ProfileActivity$aJl3mEUeI8MyKvh1zhNG8gz9XDA(ProfileActivity arg1, TL_error arg2, TLObject arg3, TL_channels_getParticipants arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        ProfileActivity.lambda$null$20(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

