package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateServiceNotification;

public final class -$$Lambda$MessagesController$tGftTSQfP7dI7-zUHb2xH8WrOn4 implements Runnable {
    public -$$Lambda$MessagesController$tGftTSQfP7dI7-zUHb2xH8WrOn4(MessagesController arg1, TL_updateServiceNotification arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesController.lambda$processUpdateArray$223(this.f$0, this.f$1);
    }
}

