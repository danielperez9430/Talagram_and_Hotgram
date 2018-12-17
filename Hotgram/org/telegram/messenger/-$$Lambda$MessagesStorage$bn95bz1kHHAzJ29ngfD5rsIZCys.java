package org.telegram.messenger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$bn95bz1kHHAzJ29ngfD5rsIZCys implements Runnable {
    public -$$Lambda$MessagesStorage$bn95bz1kHHAzJ29ngfD5rsIZCys(MessagesStorage arg1, int arg2, ArrayList arg3, CountDownLatch arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$getEncryptedChat$92(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

