package org.telegram.messenger;

import java.io.File;

public final class -$$Lambda$SendMessagesHelper$1kOX99gMEbip9sYs_E7UQv-97eY implements Runnable {
    public -$$Lambda$SendMessagesHelper$1kOX99gMEbip9sYs_E7UQv-97eY(SendMessagesHelper arg1, File arg2, MessageObject arg3, DelayedMessage arg4, String arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        SendMessagesHelper.lambda$didReceivedNotification$2(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

