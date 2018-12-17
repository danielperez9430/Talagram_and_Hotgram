package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$2W4Bem3rvAJ59eqKeWI8Ni-x-0o implements RequestDelegate {
    public -$$Lambda$MessagesController$2W4Bem3rvAJ59eqKeWI8Ni-x-0o(MessagesController arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void run(TLObject arg2, TL_error arg3) {
        MessagesController.lambda$performLogout$168(this.f$0, arg2, arg3);
    }
}

