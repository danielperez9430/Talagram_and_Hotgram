package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$ksfYK0TxFmI6VuKZBubqsw81CUU implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$ksfYK0TxFmI6VuKZBubqsw81CUU.INSTANCE = new -$$Lambda$MessagesController$ksfYK0TxFmI6VuKZBubqsw81CUU();
    }

    private -$$Lambda$MessagesController$ksfYK0TxFmI6VuKZBubqsw81CUU() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$markMessageAsRead$123(arg1, arg2);
    }
}

