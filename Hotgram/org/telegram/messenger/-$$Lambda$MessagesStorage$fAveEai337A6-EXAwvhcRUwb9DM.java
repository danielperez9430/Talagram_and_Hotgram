package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$InputPeer;
import org.telegram.tgnet.TLRPC$TL_dialog;

public final class -$$Lambda$MessagesStorage$fAveEai337A6-EXAwvhcRUwb9DM implements Runnable {
    public -$$Lambda$MessagesStorage$fAveEai337A6-EXAwvhcRUwb9DM(MessagesStorage arg1, TL_dialog arg2, InputPeer arg3, long arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$null$10(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

