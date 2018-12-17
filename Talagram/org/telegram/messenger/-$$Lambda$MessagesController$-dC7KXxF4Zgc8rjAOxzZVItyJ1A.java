package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$-dC7KXxF4Zgc8rjAOxzZVItyJ1A implements RequestDelegate {
    public -$$Lambda$MessagesController$-dC7KXxF4Zgc8rjAOxzZVItyJ1A(MessagesController arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        MessagesController.lambda$loadChannelAdmins$11(this.f$0, this.f$1, arg3, arg4);
    }
}

