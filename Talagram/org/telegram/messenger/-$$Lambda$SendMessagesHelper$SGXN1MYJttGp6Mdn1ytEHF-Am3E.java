package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputMedia;

public final class -$$Lambda$SendMessagesHelper$SGXN1MYJttGp6Mdn1ytEHF-Am3E implements Runnable {
    public -$$Lambda$SendMessagesHelper$SGXN1MYJttGp6Mdn1ytEHF-Am3E(SendMessagesHelper arg1, TLObject arg2, InputMedia arg3, DelayedMessage arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$18(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

