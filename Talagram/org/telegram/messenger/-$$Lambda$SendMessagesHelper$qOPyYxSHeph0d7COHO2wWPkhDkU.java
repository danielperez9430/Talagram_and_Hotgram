package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateNewMessage;

public final class -$$Lambda$SendMessagesHelper$qOPyYxSHeph0d7COHO2wWPkhDkU implements Runnable {
    public -$$Lambda$SendMessagesHelper$qOPyYxSHeph0d7COHO2wWPkhDkU(SendMessagesHelper arg1, TL_updateNewMessage arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$22(this.f$0, this.f$1);
    }
}

