package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$SendMessagesHelper$y5J0pDdfbi9jqC3Ft0cps5ryQDM implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$y5J0pDdfbi9jqC3Ft0cps5ryQDM(SendMessagesHelper arg1, long arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        SendMessagesHelper.lambda$sendGame$17(this.f$0, this.f$1, arg4, arg5);
    }
}

