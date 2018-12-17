package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$TSOpBoxMCmuq5v1q_3xSBBCwzzI implements RequestDelegate {
    public -$$Lambda$MessagesController$TSOpBoxMCmuq5v1q_3xSBBCwzzI(MessagesController arg1, Chat arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$null$211(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

