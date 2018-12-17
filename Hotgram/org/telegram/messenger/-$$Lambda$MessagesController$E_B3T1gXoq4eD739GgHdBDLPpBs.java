package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_config;

public final class -$$Lambda$MessagesController$E_B3T1gXoq4eD739GgHdBDLPpBs implements Runnable {
    public -$$Lambda$MessagesController$E_B3T1gXoq4eD739GgHdBDLPpBs(MessagesController arg1, TL_config arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesController.lambda$updateConfig$2(this.f$0, this.f$1);
    }
}

