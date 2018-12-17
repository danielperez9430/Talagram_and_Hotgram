package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$MessagesController$6NVpJ-TQRS_pHQpu5p4BLsUrGG8 implements Runnable {
    public -$$Lambda$MessagesController$6NVpJ-TQRS_pHQpu5p4BLsUrGG8(MessagesController arg1, Message arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesController.lambda$addToViewsQueue$118(this.f$0, this.f$1);
    }
}

