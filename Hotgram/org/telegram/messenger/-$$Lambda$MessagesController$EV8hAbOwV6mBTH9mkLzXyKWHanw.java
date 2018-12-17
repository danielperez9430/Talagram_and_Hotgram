package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$EV8hAbOwV6mBTH9mkLzXyKWHanw implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$EV8hAbOwV6mBTH9mkLzXyKWHanw.INSTANCE = new -$$Lambda$MessagesController$EV8hAbOwV6mBTH9mkLzXyKWHanw();
    }

    private -$$Lambda$MessagesController$EV8hAbOwV6mBTH9mkLzXyKWHanw() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$reportSpam$22(arg1, arg2);
    }
}

