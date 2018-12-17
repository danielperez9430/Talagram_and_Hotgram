package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$Peer;

public final class -$$Lambda$SendMessagesHelper$lUrJ94YxNDkiev73TI2IYe5flE8 implements Runnable {
    public -$$Lambda$SendMessagesHelper$lUrJ94YxNDkiev73TI2IYe5flE8(SendMessagesHelper arg1, Message arg2, int arg3, Peer arg4, ArrayList arg5, long arg6, Message arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg8;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$6(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

