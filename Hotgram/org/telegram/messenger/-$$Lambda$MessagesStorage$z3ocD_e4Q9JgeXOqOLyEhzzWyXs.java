package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$z3ocD_e4Q9JgeXOqOLyEhzzWyXs implements Runnable {
    public -$$Lambda$MessagesStorage$z3ocD_e4Q9JgeXOqOLyEhzzWyXs(MessagesStorage arg1, long arg2, int arg4, boolean[] arg5, CountDownLatch arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
        this.f$4 = arg6;
    }

    public final void run() {
        MessagesStorage.lambda$checkMessageId$78(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

