package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Lfkfm_TXCo-YKrvyXq7RFkvhSqw implements RequestDelegate {
    public -$$Lambda$MessagesController$Lfkfm_TXCo-YKrvyXq7RFkvhSqw(MessagesController arg1, int arg2, String arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$updateChannelUserName$154(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

