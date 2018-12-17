package org.telegram.messenger;

import android.util.LongSparseArray;
import org.telegram.tgnet.TLRPC$messages_Dialogs;

public final class -$$Lambda$MessagesController$m9DTYjQC4xUG7iI1XktkyjEo_2A implements Runnable {
    public -$$Lambda$MessagesController$m9DTYjQC4xUG7iI1XktkyjEo_2A(MessagesController arg1, int arg2, int arg3, int arg4, messages_Dialogs arg5, LongSparseArray arg6, LongSparseArray arg7) {
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
        MessagesController.lambda$completeDialogsReset$101(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

