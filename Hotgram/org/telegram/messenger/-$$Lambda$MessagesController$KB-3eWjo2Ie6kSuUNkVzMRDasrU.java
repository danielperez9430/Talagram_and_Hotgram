package org.telegram.messenger;

import android.util.SparseArray;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$updates_ChannelDifference;

public final class -$$Lambda$MessagesController$KB-3eWjo2Ie6kSuUNkVzMRDasrU implements Runnable {
    public -$$Lambda$MessagesController$KB-3eWjo2Ie6kSuUNkVzMRDasrU(MessagesController arg1, updates_ChannelDifference arg2, int arg3, Chat arg4, SparseArray arg5, int arg6, long arg7) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
    }

    public final void run() {
        MessagesController.lambda$null$184(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

