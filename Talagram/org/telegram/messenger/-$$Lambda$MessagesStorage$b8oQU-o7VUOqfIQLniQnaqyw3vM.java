package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$messages_Messages;

public final class -$$Lambda$MessagesStorage$b8oQU-o7VUOqfIQLniQnaqyw3vM implements Runnable {
    public -$$Lambda$MessagesStorage$b8oQU-o7VUOqfIQLniQnaqyw3vM(MessagesStorage arg1, messages_Messages arg2, int arg3, long arg4, int arg6, boolean arg7) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg6;
        this.f$5 = arg7;
    }

    public final void run() {
        MessagesStorage.lambda$putMessages$117(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

