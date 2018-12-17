package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Document;

public final class -$$Lambda$MessagesStorage$uI3pGn2hMrPD4GgXGpEcOb1k3J0 implements Runnable {
    public -$$Lambda$MessagesStorage$uI3pGn2hMrPD4GgXGpEcOb1k3J0(MessagesStorage arg1, Document arg2, String arg3, String arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$addRecentLocalFile$24(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

