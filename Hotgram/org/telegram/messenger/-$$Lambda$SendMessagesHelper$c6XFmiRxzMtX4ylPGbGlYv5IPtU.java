package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Message;

public final class -$$Lambda$SendMessagesHelper$c6XFmiRxzMtX4ylPGbGlYv5IPtU implements Runnable {
    public -$$Lambda$SendMessagesHelper$c6XFmiRxzMtX4ylPGbGlYv5IPtU(SendMessagesHelper arg1, Message arg2, int arg3, boolean arg4, ArrayList arg5, String arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$37(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

