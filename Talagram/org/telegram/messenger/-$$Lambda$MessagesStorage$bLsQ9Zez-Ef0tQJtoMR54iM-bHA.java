package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$bLsQ9Zez-Ef0tQJtoMR54iM-bHA implements Runnable {
    public -$$Lambda$MessagesStorage$bLsQ9Zez-Ef0tQJtoMR54iM-bHA(MessagesStorage arg1, long arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$removePendingTask$7(this.f$0, this.f$1);
    }
}

