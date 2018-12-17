package org.telegram.messenger;

import android.util.SparseIntArray;

public final class -$$Lambda$MessagesStorage$CLYSzoLH5ZYxvD-30805gFmJk0c implements Runnable {
    public -$$Lambda$MessagesStorage$CLYSzoLH5ZYxvD-30805gFmJk0c(MessagesStorage arg1, boolean arg2, SparseIntArray arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$putBlockedUsers$31(this.f$0, this.f$1, this.f$2);
    }
}

