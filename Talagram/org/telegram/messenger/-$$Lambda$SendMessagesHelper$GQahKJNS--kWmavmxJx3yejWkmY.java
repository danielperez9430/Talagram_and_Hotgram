package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$SendMessagesHelper$GQahKJNS--kWmavmxJx3yejWkmY implements Runnable {
    public -$$Lambda$SendMessagesHelper$GQahKJNS--kWmavmxJx3yejWkmY(SendMessagesHelper arg1, boolean arg2, ArrayList arg3, Message arg4, int arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$36(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

