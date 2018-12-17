package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$FKwQvrkSkB5mdWhvrErop72UEK4 implements Runnable {
    public -$$Lambda$MessagesStorage$FKwQvrkSkB5mdWhvrErop72UEK4(MessagesStorage arg1, boolean arg2, int arg3, long arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$removeFromDownloadQueue$95(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

