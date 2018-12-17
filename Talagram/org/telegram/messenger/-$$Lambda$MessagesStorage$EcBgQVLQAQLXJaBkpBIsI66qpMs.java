package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;

public final class -$$Lambda$MessagesStorage$EcBgQVLQAQLXJaBkpBIsI66qpMs implements Runnable {
    public -$$Lambda$MessagesStorage$EcBgQVLQAQLXJaBkpBIsI66qpMs(MessagesStorage arg1, int arg2, String arg3, RequestDelegate arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$getBotCache$61(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

