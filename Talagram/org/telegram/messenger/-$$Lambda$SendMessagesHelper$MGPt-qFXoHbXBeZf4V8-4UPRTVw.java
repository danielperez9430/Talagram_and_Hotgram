package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$SendMessagesHelper$MGPt-qFXoHbXBeZf4V8-4UPRTVw implements Runnable {
    public -$$Lambda$SendMessagesHelper$MGPt-qFXoHbXBeZf4V8-4UPRTVw(SendMessagesHelper arg1, Message arg2, long arg3, int arg5, Message arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg5;
        this.f$4 = arg6;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$5(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

