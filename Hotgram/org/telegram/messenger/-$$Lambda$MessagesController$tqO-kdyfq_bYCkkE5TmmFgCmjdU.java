package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$tqO-kdyfq_bYCkkE5TmmFgCmjdU implements RequestDelegate {
    public -$$Lambda$MessagesController$tqO-kdyfq_bYCkkE5TmmFgCmjdU(MessagesController arg1, int arg2, int arg3, int arg4, int arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        MessagesController.lambda$resetDialogs$99(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

