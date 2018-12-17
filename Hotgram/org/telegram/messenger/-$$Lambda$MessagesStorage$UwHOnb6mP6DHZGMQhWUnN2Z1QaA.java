package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$messages_Dialogs;

public final class -$$Lambda$MessagesStorage$UwHOnb6mP6DHZGMQhWUnN2Z1QaA implements Runnable {
    public -$$Lambda$MessagesStorage$UwHOnb6mP6DHZGMQhWUnN2Z1QaA(MessagesStorage arg1, messages_Dialogs arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$putDialogs$122(this.f$0, this.f$1, this.f$2);
    }
}

