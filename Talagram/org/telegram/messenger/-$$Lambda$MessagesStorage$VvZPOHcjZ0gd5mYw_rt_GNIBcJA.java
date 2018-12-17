package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$MessagesStorage$VvZPOHcjZ0gd5mYw_rt_GNIBcJA implements Runnable {
    public -$$Lambda$MessagesStorage$VvZPOHcjZ0gd5mYw_rt_GNIBcJA(MessagesStorage arg1, EncryptedChat arg2, boolean arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$updateEncryptedChatSeq$86(this.f$0, this.f$1, this.f$2);
    }
}

