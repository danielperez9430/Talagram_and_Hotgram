package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$7ks0cC3Ml308HXyjulB1Cd3JA4k implements Runnable {
    public -$$Lambda$MessagesStorage$7ks0cC3Ml308HXyjulB1Cd3JA4k(MessagesStorage arg1, int arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$setDialogPinned$121(this.f$0, this.f$1, this.f$2);
    }
}

