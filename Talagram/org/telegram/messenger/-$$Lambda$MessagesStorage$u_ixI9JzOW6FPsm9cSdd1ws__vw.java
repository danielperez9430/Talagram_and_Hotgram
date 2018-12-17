package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$u_ixI9JzOW6FPsm9cSdd1ws__vw implements Runnable {
    public -$$Lambda$MessagesStorage$u_ixI9JzOW6FPsm9cSdd1ws__vw(MessagesStorage arg1, long arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$markMessageAsMention$48(this.f$0, this.f$1);
    }
}

