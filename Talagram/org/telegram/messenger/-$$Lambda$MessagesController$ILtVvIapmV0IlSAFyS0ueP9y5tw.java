package org.telegram.messenger;

import android.util.SparseArray;
import org.telegram.tgnet.TLRPC$updates_Difference;

public final class -$$Lambda$MessagesController$ILtVvIapmV0IlSAFyS0ueP9y5tw implements Runnable {
    public -$$Lambda$MessagesController$ILtVvIapmV0IlSAFyS0ueP9y5tw(MessagesController arg1, updates_Difference arg2, SparseArray arg3, SparseArray arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesController.lambda$null$194(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

