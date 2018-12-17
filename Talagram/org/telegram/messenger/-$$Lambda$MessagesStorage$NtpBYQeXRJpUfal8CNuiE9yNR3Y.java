package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$NtpBYQeXRJpUfal8CNuiE9yNR3Y implements Runnable {
    public -$$Lambda$MessagesStorage$NtpBYQeXRJpUfal8CNuiE9yNR3Y(MessagesStorage arg1, int arg2, int arg3, byte[] arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$saveSecretParams$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

