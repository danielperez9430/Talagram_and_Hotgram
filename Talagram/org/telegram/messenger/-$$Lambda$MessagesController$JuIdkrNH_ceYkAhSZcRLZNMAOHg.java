package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_getMessagesViews;

public final class -$$Lambda$MessagesController$JuIdkrNH_ceYkAhSZcRLZNMAOHg implements RequestDelegate {
    public -$$Lambda$MessagesController$JuIdkrNH_ceYkAhSZcRLZNMAOHg(MessagesController arg1, int arg2, TL_messages_getMessagesViews arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$updateTimerProc$68(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

