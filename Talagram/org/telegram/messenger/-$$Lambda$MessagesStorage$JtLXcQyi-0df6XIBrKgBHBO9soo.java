package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$JtLXcQyi-0df6XIBrKgBHBO9soo implements Runnable {
    public -$$Lambda$MessagesStorage$JtLXcQyi-0df6XIBrKgBHBO9soo(MessagesStorage arg1, int arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$markMessagesAsDeleted$116(this.f$0, this.f$1, this.f$2);
    }
}

