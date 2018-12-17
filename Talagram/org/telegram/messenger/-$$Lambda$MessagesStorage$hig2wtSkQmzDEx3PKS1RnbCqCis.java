package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$hig2wtSkQmzDEx3PKS1RnbCqCis implements Runnable {
    public -$$Lambda$MessagesStorage$hig2wtSkQmzDEx3PKS1RnbCqCis(MessagesStorage arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$clearDownloadQueue$96(this.f$0, this.f$1);
    }
}

