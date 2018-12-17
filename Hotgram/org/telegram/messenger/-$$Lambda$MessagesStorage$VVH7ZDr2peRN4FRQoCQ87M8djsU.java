package org.telegram.messenger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$VVH7ZDr2peRN4FRQoCQ87M8djsU implements Runnable {
    public -$$Lambda$MessagesStorage$VVH7ZDr2peRN4FRQoCQ87M8djsU(MessagesStorage arg1, String arg2, int arg3, ArrayList arg4, CountDownLatch arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        MessagesStorage.lambda$getSentFile$84(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

