package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$JWiWbN0BUKrnUUULW9pjsU0EcFs implements Runnable {
    public -$$Lambda$MessagesStorage$JWiWbN0BUKrnUUULW9pjsU0EcFs(MessagesStorage arg1, int arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$deleteUserChannelHistory$34(this.f$0, this.f$1, this.f$2);
    }
}

