package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$MessagesController$rx3tP18YKTCdcENU9_EQ1ZEJohY implements RequestDelegate {
    public -$$Lambda$MessagesController$rx3tP18YKTCdcENU9_EQ1ZEJohY(MessagesController arg1, User arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        MessagesController.lambda$blockUser$37(this.f$0, this.f$1, arg3, arg4);
    }
}

