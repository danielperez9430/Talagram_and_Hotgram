package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$MessagesStorage$gv96BZNjlUiU0dyzUZaDuCT0pXY implements Runnable {
    public -$$Lambda$MessagesStorage$gv96BZNjlUiU0dyzUZaDuCT0pXY(MessagesStorage arg1, Message arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$markMessageAsSendError$106(this.f$0, this.f$1);
    }
}

