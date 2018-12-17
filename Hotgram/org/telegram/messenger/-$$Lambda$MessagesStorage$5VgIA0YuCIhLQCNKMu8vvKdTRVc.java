package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$5VgIA0YuCIhLQCNKMu8vvKdTRVc implements Runnable {
    public -$$Lambda$MessagesStorage$5VgIA0YuCIhLQCNKMu8vvKdTRVc(MessagesStorage arg1, int arg2, boolean[] arg3, CountDownLatch arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$isMigratedChat$67(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

