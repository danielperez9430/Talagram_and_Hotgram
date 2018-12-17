package org.telegram.messenger;

import java.io.File;

public final class -$$Lambda$SendMessagesHelper$pp0U4GJ1r75dDYF4YGnbf9kI6EU implements Runnable {
    public -$$Lambda$SendMessagesHelper$pp0U4GJ1r75dDYF4YGnbf9kI6EU(SendMessagesHelper arg1, DelayedMessage arg2, File arg3, MessageObject arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        SendMessagesHelper.lambda$didReceivedNotification$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

