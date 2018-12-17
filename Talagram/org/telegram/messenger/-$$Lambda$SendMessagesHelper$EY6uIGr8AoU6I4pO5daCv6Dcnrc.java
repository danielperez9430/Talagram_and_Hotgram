package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$SendMessagesHelper$EY6uIGr8AoU6I4pO5daCv6Dcnrc implements Runnable {
    public -$$Lambda$SendMessagesHelper$EY6uIGr8AoU6I4pO5daCv6Dcnrc(SendMessagesHelper arg1, Message arg2, int arg3, ArrayList arg4, long arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$25(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

