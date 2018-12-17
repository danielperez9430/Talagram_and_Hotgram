package org.telegram.messenger;

import android.util.LongSparseArray;
import org.telegram.tgnet.TLRPC$messages_Dialogs;

public final class -$$Lambda$MessagesController$6_IxPIEedPfMhN-27iZsoT7iR4A implements Runnable {
    public -$$Lambda$MessagesController$6_IxPIEedPfMhN-27iZsoT7iR4A(MessagesController arg1, messages_Dialogs arg2, LongSparseArray arg3, LongSparseArray arg4, LongSparseArray arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        MessagesController.lambda$null$116(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

