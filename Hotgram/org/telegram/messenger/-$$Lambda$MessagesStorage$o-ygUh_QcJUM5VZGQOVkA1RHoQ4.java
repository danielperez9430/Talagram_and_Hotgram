package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;

public final class -$$Lambda$MessagesStorage$o-ygUh_QcJUM5VZGQOVkA1RHoQ4 implements Runnable {
    public -$$Lambda$MessagesStorage$o-ygUh_QcJUM5VZGQOVkA1RHoQ4(MessagesStorage arg1, EncryptedChat arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$updateEncryptedChat$89(this.f$0, this.f$1);
    }
}

