package org.telegram.messenger;

import android.util.SparseArray;

public final class -$$Lambda$MessagesStorage$c3Fi-zRSwutpwhqN7B95bo5f4HI implements Runnable {
    public -$$Lambda$MessagesStorage$c3Fi-zRSwutpwhqN7B95bo5f4HI(MessagesStorage arg1, SparseArray arg2, boolean arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$putChannelViews$103(this.f$0, this.f$1, this.f$2);
    }
}

