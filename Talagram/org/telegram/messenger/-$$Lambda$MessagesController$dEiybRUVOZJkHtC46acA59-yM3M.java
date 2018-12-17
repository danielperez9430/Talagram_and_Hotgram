package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Chat;

public final class -$$Lambda$MessagesController$dEiybRUVOZJkHtC46acA59-yM3M implements Runnable {
    public -$$Lambda$MessagesController$dEiybRUVOZJkHtC46acA59-yM3M(MessagesController arg1, Chat arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesController.lambda$putChat$9(this.f$0, this.f$1);
    }
}

