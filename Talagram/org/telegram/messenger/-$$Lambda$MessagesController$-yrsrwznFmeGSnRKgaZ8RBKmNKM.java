package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$messages_Dialogs;

public final class -$$Lambda$MessagesController$-yrsrwznFmeGSnRKgaZ8RBKmNKM implements Runnable {
    public -$$Lambda$MessagesController$-yrsrwznFmeGSnRKgaZ8RBKmNKM(MessagesController arg1, messages_Dialogs arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesController.lambda$processDialogsUpdate$117(this.f$0, this.f$1);
    }
}

