package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateUserBlocked;

public final class -$$Lambda$MessagesController$dOHdyLwXSeuOZtbvr3Hqwz5Bi-g implements Runnable {
    public -$$Lambda$MessagesController$dOHdyLwXSeuOZtbvr3Hqwz5Bi-g(MessagesController arg1, TL_updateUserBlocked arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesController.lambda$processUpdateArray$222(this.f$0, this.f$1);
    }
}

