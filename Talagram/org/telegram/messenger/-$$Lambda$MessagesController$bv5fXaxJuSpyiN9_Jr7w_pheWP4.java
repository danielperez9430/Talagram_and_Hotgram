package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_createChat;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$bv5fXaxJuSpyiN9_Jr7w_pheWP4 implements Runnable {
    public -$$Lambda$MessagesController$bv5fXaxJuSpyiN9_Jr7w_pheWP4(MessagesController arg1, TL_error arg2, BaseFragment arg3, TL_messages_createChat arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesController.lambda$null$134(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

