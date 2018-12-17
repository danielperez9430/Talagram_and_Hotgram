package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$InputPeer;

public final class -$$Lambda$MessagesStorage$AuH1gRs2iXmoSCC0c3xvFDAEwcM implements Runnable {
    public -$$Lambda$MessagesStorage$AuH1gRs2iXmoSCC0c3xvFDAEwcM(MessagesStorage arg1, long arg2, boolean arg4, InputPeer arg5, long arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
        this.f$4 = arg6;
    }

    public final void run() {
        MessagesStorage.lambda$null$11(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

