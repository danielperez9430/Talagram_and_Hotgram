package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Mk6jHGSt4O4ay9JrE9e3NnwIk-U implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$Mk6jHGSt4O4ay9JrE9e3NnwIk-U.INSTANCE = new -$$Lambda$MessagesController$Mk6jHGSt4O4ay9JrE9e3NnwIk-U();
    }

    private -$$Lambda$MessagesController$Mk6jHGSt4O4ay9JrE9e3NnwIk-U() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$reportSpam$23(arg1, arg2);
    }
}

