package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$ChatActivity$16$BC8t44pPzeiGUytAIKRsBXYujF0 implements Runnable {
    public -$$Lambda$ChatActivity$16$BC8t44pPzeiGUytAIKRsBXYujF0(org.telegram.ui.ChatActivity$16 arg1, TLObject arg2, TL_error arg3, MessagesStorage arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        org.telegram.ui.ChatActivity$16.lambda$null$1(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

