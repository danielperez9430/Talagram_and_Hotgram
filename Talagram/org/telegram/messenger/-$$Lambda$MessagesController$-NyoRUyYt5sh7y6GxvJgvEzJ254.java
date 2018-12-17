package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$-NyoRUyYt5sh7y6GxvJgvEzJ254 implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$-NyoRUyYt5sh7y6GxvJgvEzJ254.INSTANCE = new -$$Lambda$MessagesController$-NyoRUyYt5sh7y6GxvJgvEzJ254();
    }

    private -$$Lambda$MessagesController$-NyoRUyYt5sh7y6GxvJgvEzJ254() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$deleteUserPhoto$49(arg1, arg2);
    }
}

