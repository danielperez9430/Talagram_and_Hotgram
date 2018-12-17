package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$kRqzlsQSI5t91XE6mM-NMTAz66o implements RequestDelegate {
    public -$$Lambda$MessagesController$kRqzlsQSI5t91XE6mM-NMTAz66o(MessagesController arg1, int arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$getDifference$196(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

