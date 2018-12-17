package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$M-FwDWj6LmGja7eJuhjyNZwcUrQ implements Runnable {
    public -$$Lambda$MessagesStorage$M-FwDWj6LmGja7eJuhjyNZwcUrQ(MessagesStorage arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$deleteBlockedUser$30(this.f$0, this.f$1);
    }
}

