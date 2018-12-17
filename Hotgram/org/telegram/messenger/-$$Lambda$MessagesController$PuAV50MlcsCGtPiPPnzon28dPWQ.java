package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$PuAV50MlcsCGtPiPPnzon28dPWQ implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$PuAV50MlcsCGtPiPPnzon28dPWQ.INSTANCE = new -$$Lambda$MessagesController$PuAV50MlcsCGtPiPPnzon28dPWQ();
    }

    private -$$Lambda$MessagesController$PuAV50MlcsCGtPiPPnzon28dPWQ() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$saveRecentSticker$61(arg1, arg2);
    }
}

