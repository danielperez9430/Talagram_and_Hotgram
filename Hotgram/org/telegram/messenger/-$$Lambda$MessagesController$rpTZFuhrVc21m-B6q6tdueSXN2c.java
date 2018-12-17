package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$rpTZFuhrVc21m-B6q6tdueSXN2c implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$rpTZFuhrVc21m-B6q6tdueSXN2c.INSTANCE = new -$$Lambda$MessagesController$rpTZFuhrVc21m-B6q6tdueSXN2c();
    }

    private -$$Lambda$MessagesController$rpTZFuhrVc21m-B6q6tdueSXN2c() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$processUpdates$217(arg1, arg2);
    }
}

