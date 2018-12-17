package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLRPC$Chat;

public final class -$$Lambda$MessagesStorage$580dnN4e5LL-Iyx4pJ74fg-7XfY implements Runnable {
    public -$$Lambda$MessagesStorage$580dnN4e5LL-Iyx4pJ74fg-7XfY(MessagesStorage arg1, Chat[] arg2, int arg3, CountDownLatch arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$getChatSync$126(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

