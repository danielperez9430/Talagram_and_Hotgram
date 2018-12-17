package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updates_channelDifferenceTooLong;

public final class -$$Lambda$MessagesStorage$AVrdlSkNOxs49SB-egZcOtCsb8M implements Runnable {
    public -$$Lambda$MessagesStorage$AVrdlSkNOxs49SB-egZcOtCsb8M(MessagesStorage arg1, int arg2, int arg3, TL_updates_channelDifferenceTooLong arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$overwriteChannel$102(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

