package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$ChatParticipants;

public final class -$$Lambda$MessagesStorage$mfb9Lk3A8pingtAxnDjjiIRIJys implements Runnable {
    public -$$Lambda$MessagesStorage$mfb9Lk3A8pingtAxnDjjiIRIJys(MessagesStorage arg1, ChatParticipants arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$updateChatParticipants$56(this.f$0, this.f$1);
    }
}

