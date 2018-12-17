package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$9IdFW72D1R13fo8J0GAaOH-Bj7Y implements Runnable {
    public -$$Lambda$MessagesStorage$9IdFW72D1R13fo8J0GAaOH-Bj7Y(MessagesStorage arg1, long arg2, IntCallback arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$getUnreadMention$80(this.f$0, this.f$1, this.f$2);
    }
}

