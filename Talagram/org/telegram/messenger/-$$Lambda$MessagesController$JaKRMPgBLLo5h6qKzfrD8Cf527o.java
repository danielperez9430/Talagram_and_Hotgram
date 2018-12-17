package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$JaKRMPgBLLo5h6qKzfrD8Cf527o implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$JaKRMPgBLLo5h6qKzfrD8Cf527o.INSTANCE = new -$$Lambda$MessagesController$JaKRMPgBLLo5h6qKzfrD8Cf527o();
    }

    private -$$Lambda$MessagesController$JaKRMPgBLLo5h6qKzfrD8Cf527o() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$markMentionsAsRead$128(arg1, arg2);
    }
}

