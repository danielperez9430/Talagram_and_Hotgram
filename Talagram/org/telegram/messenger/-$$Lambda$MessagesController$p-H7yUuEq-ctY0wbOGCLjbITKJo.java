package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$p-H7yUuEq-ctY0wbOGCLjbITKJo implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$p-H7yUuEq-ctY0wbOGCLjbITKJo.INSTANCE = new -$$Lambda$MessagesController$p-H7yUuEq-ctY0wbOGCLjbITKJo();
    }

    private -$$Lambda$MessagesController$p-H7yUuEq-ctY0wbOGCLjbITKJo() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$unregistedPush$167(arg1, arg2);
    }
}

