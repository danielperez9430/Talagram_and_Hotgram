package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$MessagesStorage$plcV57VZpB9ChVjU0VxBIQwC7_E implements Runnable {
    public -$$Lambda$MessagesStorage$plcV57VZpB9ChVjU0VxBIQwC7_E(MessagesStorage arg1, User[] arg2, int arg3, CountDownLatch arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$getUserSync$125(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

