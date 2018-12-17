package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$photos_Photos;

public final class -$$Lambda$MessagesStorage$SOag2s8e-ohyNNQp418oPEBvh-M implements Runnable {
    public -$$Lambda$MessagesStorage$SOag2s8e-ohyNNQp418oPEBvh-M(MessagesStorage arg1, int arg2, photos_Photos arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$putDialogPhotos$43(this.f$0, this.f$1, this.f$2);
    }
}

