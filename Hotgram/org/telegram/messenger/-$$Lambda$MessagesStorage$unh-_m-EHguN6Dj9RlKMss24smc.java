package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$unh-_m-EHguN6Dj9RlKMss24smc implements Runnable {
    public -$$Lambda$MessagesStorage$unh-_m-EHguN6Dj9RlKMss24smc(MessagesStorage arg1, long arg2, long arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$setDialogFlags$18(this.f$0, this.f$1, this.f$2);
    }
}

