package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$MessagesStorage$Uxf5CTpTh-viaedRWhfEzJuOaAw implements Runnable {
    public -$$Lambda$MessagesStorage$Uxf5CTpTh-viaedRWhfEzJuOaAw(MessagesStorage arg1, EncryptedChat arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$updateEncryptedChatLayer$88(this.f$0, this.f$1);
    }
}

