package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$-Us8QcaUHG3-M-b6YH2H3AEIwPU implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$-Us8QcaUHG3-M-b6YH2H3AEIwPU.INSTANCE = new -$$Lambda$MessagesController$-Us8QcaUHG3-M-b6YH2H3AEIwPU();
    }

    private -$$Lambda$MessagesController$-Us8QcaUHG3-M-b6YH2H3AEIwPU() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$completeReadTask$126(arg1, arg2);
    }
}

