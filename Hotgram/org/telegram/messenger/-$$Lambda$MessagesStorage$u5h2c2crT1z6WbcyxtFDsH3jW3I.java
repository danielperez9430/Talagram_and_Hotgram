package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$u5h2c2crT1z6WbcyxtFDsH3jW3I implements Runnable {
    public -$$Lambda$MessagesStorage$u5h2c2crT1z6WbcyxtFDsH3jW3I(MessagesStorage arg1, long arg2, boolean[] arg4, CountDownLatch arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
    }

    public final void run() {
        MessagesStorage.lambda$isDialogHasMessages$90(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

