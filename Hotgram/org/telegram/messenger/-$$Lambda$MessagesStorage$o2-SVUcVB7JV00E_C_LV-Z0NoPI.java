package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$o2-SVUcVB7JV00E_C_LV-Z0NoPI implements Runnable {
    public -$$Lambda$MessagesStorage$o2-SVUcVB7JV00E_C_LV-Z0NoPI(MessagesStorage arg1, long arg2, boolean[] arg4, CountDownLatch arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
    }

    public final void run() {
        MessagesStorage.lambda$checkMessageByRandomId$77(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

