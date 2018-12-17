package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Chat;

public final class -$$Lambda$MessagesStorage$X8wmcVmkWlOOmT7hyCwq34C_3HA implements Runnable {
    public -$$Lambda$MessagesStorage$X8wmcVmkWlOOmT7hyCwq34C_3HA(MessagesStorage arg1, Chat arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$null$8(this.f$0, this.f$1, this.f$2);
    }
}

