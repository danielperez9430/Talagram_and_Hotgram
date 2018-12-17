package org.telegram.messenger;

import android.util.LongSparseArray;
import org.telegram.tgnet.TLRPC$messages_Dialogs;

public final class -$$Lambda$MessagesController$WcHzBnwq2JmDMGp4H26fmKnB7L4 implements Runnable {
    public -$$Lambda$MessagesController$WcHzBnwq2JmDMGp4H26fmKnB7L4(MessagesController arg1, messages_Dialogs arg2, LongSparseArray arg3, LongSparseArray arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesController.lambda$null$100(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

