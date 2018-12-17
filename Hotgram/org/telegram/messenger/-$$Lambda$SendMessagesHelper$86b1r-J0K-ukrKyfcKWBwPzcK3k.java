package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateShortSentMessage;

public final class -$$Lambda$SendMessagesHelper$86b1r-J0K-ukrKyfcKWBwPzcK3k implements Runnable {
    public -$$Lambda$SendMessagesHelper$86b1r-J0K-ukrKyfcKWBwPzcK3k(SendMessagesHelper arg1, TL_updateShortSentMessage arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$32(this.f$0, this.f$1);
    }
}

