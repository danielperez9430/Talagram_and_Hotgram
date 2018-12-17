package org.telegram.messenger;

import android.util.LongSparseArray;

public final class -$$Lambda$MessagesController$io4Lfe51JqaE7nFDCfTltluA7ho implements Runnable {
    public -$$Lambda$MessagesController$io4Lfe51JqaE7nFDCfTltluA7ho(MessagesController arg1, LongSparseArray arg2, LongSparseArray arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesController.lambda$processDialogsUpdateRead$112(this.f$0, this.f$1, this.f$2);
    }
}

