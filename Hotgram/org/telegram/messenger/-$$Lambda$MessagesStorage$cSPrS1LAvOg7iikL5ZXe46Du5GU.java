package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$ChatFull;

public final class -$$Lambda$MessagesStorage$cSPrS1LAvOg7iikL5ZXe46Du5GU implements Runnable {
    public -$$Lambda$MessagesStorage$cSPrS1LAvOg7iikL5ZXe46Du5GU(MessagesStorage arg1, boolean arg2, ChatFull arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$updateChatInfo$62(this.f$0, this.f$1, this.f$2);
    }
}

