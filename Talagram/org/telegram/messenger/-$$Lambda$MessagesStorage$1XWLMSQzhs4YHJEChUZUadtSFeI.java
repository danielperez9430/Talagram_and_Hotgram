package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$1XWLMSQzhs4YHJEChUZUadtSFeI implements Runnable {
    public -$$Lambda$MessagesStorage$1XWLMSQzhs4YHJEChUZUadtSFeI(MessagesStorage arg1, int arg2, CountDownLatch arg3, boolean arg4, boolean arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        MessagesStorage.lambda$loadChatInfo$68(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

