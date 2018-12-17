package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$pXp2fbY7dK2jKlm5Yew-7uBFY8U implements RequestDelegate {
    public -$$Lambda$MessagesController$pXp2fbY7dK2jKlm5Yew-7uBFY8U(MessagesController arg1, int arg2, int arg3, long arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        MessagesController.lambda$getChannelDifference$187(this.f$0, this.f$1, this.f$2, this.f$3, arg8, arg9);
    }
}

