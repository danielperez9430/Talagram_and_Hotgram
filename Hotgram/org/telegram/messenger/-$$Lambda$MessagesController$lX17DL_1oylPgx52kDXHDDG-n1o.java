package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$lX17DL_1oylPgx52kDXHDDG-n1o implements RequestDelegate {
    public -$$Lambda$MessagesController$lX17DL_1oylPgx52kDXHDDG-n1o(MessagesController arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void run(TLObject arg2, TL_error arg3) {
        MessagesController.lambda$toogleChannelInvitesHistory$150(this.f$0, arg2, arg3);
    }
}

