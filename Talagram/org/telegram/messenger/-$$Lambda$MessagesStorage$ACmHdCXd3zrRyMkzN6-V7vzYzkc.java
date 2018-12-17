package org.telegram.messenger;

import org.telegram.tgnet.TLObject;

public final class -$$Lambda$MessagesStorage$ACmHdCXd3zrRyMkzN6-V7vzYzkc implements Runnable {
    public -$$Lambda$MessagesStorage$ACmHdCXd3zrRyMkzN6-V7vzYzkc(MessagesStorage arg1, String arg2, TLObject arg3, int arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$putSentFile$85(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

