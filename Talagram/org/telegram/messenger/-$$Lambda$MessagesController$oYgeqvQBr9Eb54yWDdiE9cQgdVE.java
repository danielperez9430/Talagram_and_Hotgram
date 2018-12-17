package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$photos_Photos;

public final class -$$Lambda$MessagesController$oYgeqvQBr9Eb54yWDdiE9cQgdVE implements Runnable {
    public -$$Lambda$MessagesController$oYgeqvQBr9Eb54yWDdiE9cQgdVE(MessagesController arg1, photos_Photos arg2, boolean arg3, int arg4, int arg5, int arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        MessagesController.lambda$processLoadedUserPhotos$50(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

