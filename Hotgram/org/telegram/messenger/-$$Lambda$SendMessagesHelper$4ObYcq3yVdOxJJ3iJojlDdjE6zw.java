package org.telegram.messenger;

import org.telegram.tgnet.QuickAckDelegate;
import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$SendMessagesHelper$4ObYcq3yVdOxJJ3iJojlDdjE6zw implements QuickAckDelegate {
    public -$$Lambda$SendMessagesHelper$4ObYcq3yVdOxJJ3iJojlDdjE6zw(SendMessagesHelper arg1, Message arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SendMessagesHelper.lambda$performSendMessageRequest$41(this.f$0, this.f$1);
    }
}

