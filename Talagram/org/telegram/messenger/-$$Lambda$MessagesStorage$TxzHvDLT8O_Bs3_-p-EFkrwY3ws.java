package org.telegram.messenger;

import org.telegram.tgnet.NativeByteBuffer;

public final class -$$Lambda$MessagesStorage$TxzHvDLT8O_Bs3_-p-EFkrwY3ws implements Runnable {
    public -$$Lambda$MessagesStorage$TxzHvDLT8O_Bs3_-p-EFkrwY3ws(MessagesStorage arg1, long arg2, NativeByteBuffer arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$createPendingTask$6(this.f$0, this.f$1, this.f$2);
    }
}

