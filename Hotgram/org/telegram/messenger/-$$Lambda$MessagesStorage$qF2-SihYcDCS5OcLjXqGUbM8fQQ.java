package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$qF2-SihYcDCS5OcLjXqGUbM8fQQ implements Runnable {
    public -$$Lambda$MessagesStorage$qF2-SihYcDCS5OcLjXqGUbM8fQQ(MessagesStorage arg1, int arg2, int arg3, int arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$setMessageSeq$107(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

