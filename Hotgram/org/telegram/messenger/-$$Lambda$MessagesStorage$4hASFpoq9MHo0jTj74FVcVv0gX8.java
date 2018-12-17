package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$4hASFpoq9MHo0jTj74FVcVv0gX8 implements Runnable {
    public -$$Lambda$MessagesStorage$4hASFpoq9MHo0jTj74FVcVv0gX8(MessagesStorage arg1, boolean arg2, long arg3, Integer[] arg5, CountDownLatch arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg5;
        this.f$4 = arg6;
    }

    public final void run() {
        MessagesStorage.lambda$getDialogReadMax$123(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

