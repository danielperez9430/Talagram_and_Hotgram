package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$SendMessagesHelper$t77TIdvR5oVS88nA5fB-WrU7owc implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$t77TIdvR5oVS88nA5fB-WrU7owc(SendMessagesHelper arg1, String arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        SendMessagesHelper.lambda$null$13(this.f$0, this.f$1, arg3, arg4);
    }
}

