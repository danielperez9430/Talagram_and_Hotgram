package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$aV_vmaGmocUzJMowFb97qB2ZcfM implements Runnable {
    public -$$Lambda$MessagesStorage$aV_vmaGmocUzJMowFb97qB2ZcfM(MessagesStorage arg1, long arg2, boolean arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$setDialogUnread$120(this.f$0, this.f$1, this.f$2);
    }
}

