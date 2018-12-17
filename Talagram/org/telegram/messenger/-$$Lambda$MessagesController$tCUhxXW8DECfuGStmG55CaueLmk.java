package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$tCUhxXW8DECfuGStmG55CaueLmk implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$tCUhxXW8DECfuGStmG55CaueLmk.INSTANCE = new -$$Lambda$MessagesController$tCUhxXW8DECfuGStmG55CaueLmk();
    }

    private -$$Lambda$MessagesController$tCUhxXW8DECfuGStmG55CaueLmk() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$saveGif$60(arg1, arg2);
    }
}

