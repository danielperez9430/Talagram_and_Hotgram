package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Y_AYhAAx7hiH5UjmhU0B2CdZQkM implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$Y_AYhAAx7hiH5UjmhU0B2CdZQkM.INSTANCE = new -$$Lambda$MessagesController$Y_AYhAAx7hiH5UjmhU0B2CdZQkM();
    }

    private -$$Lambda$MessagesController$Y_AYhAAx7hiH5UjmhU0B2CdZQkM() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$deleteDialog$58(arg1, arg2);
    }
}

