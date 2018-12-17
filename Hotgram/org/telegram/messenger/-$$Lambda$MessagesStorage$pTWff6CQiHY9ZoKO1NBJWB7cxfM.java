package org.telegram.messenger;

import android.util.SparseIntArray;
import org.telegram.messenger.support.SparseLongArray;

public final class -$$Lambda$MessagesStorage$pTWff6CQiHY9ZoKO1NBJWB7cxfM implements Runnable {
    public -$$Lambda$MessagesStorage$pTWff6CQiHY9ZoKO1NBJWB7cxfM(MessagesStorage arg1, SparseLongArray arg2, SparseLongArray arg3, SparseIntArray arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$markMessagesAsRead$111(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

