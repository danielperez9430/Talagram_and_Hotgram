package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$lPbEN3OTQcQy84iTY_Z8ie7xtjU implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$lPbEN3OTQcQy84iTY_Z8ie7xtjU.INSTANCE = new -$$Lambda$MessagesController$lPbEN3OTQcQy84iTY_Z8ie7xtjU();
    }

    private -$$Lambda$MessagesController$lPbEN3OTQcQy84iTY_Z8ie7xtjU() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$toggleUserAdmin$157(arg1, arg2);
    }
}

