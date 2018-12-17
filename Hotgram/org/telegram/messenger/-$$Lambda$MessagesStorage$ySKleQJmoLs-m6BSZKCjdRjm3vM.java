package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$MessagesStorage$ySKleQJmoLs-m6BSZKCjdRjm3vM implements Runnable {
    public -$$Lambda$MessagesStorage$ySKleQJmoLs-m6BSZKCjdRjm3vM(MessagesStorage arg1, EncryptedChat arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$updateEncryptedChatTTL$87(this.f$0, this.f$1);
    }
}

