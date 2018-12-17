package org.telegram.messenger;

import android.util.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$TL_messages_dialogs;
import org.telegram.tgnet.TLRPC$TL_messages_peerDialogs;

public final class -$$Lambda$MessagesController$DIRUqEAwQudzmh5nC2UkNLmDJIo implements Runnable {
    public -$$Lambda$MessagesController$DIRUqEAwQudzmh5nC2UkNLmDJIo(MessagesController arg1, TL_messages_peerDialogs arg2, ArrayList arg3, ArrayList arg4, long arg5, LongSparseArray arg7, TL_messages_dialogs arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg7;
        this.f$6 = arg8;
    }

    public final void run() {
        MessagesController.lambda$null$201(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

