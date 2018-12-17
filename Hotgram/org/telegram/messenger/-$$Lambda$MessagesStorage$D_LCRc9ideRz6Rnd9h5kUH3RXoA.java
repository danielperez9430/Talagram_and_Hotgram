package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$MessagesStorage$D_LCRc9ideRz6Rnd9h5kUH3RXoA implements Runnable {
    public -$$Lambda$MessagesStorage$D_LCRc9ideRz6Rnd9h5kUH3RXoA(MessagesStorage arg1, EncryptedChat arg2, User arg3, TL_dialog arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$putEncryptedChat$93(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

