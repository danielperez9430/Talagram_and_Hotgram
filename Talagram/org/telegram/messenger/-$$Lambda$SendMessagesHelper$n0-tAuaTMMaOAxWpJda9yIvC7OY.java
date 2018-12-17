package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_updateNewChannelMessage;

public final class -$$Lambda$SendMessagesHelper$n0-tAuaTMMaOAxWpJda9yIvC7OY implements Runnable {
    public -$$Lambda$SendMessagesHelper$n0-tAuaTMMaOAxWpJda9yIvC7OY(SendMessagesHelper arg1, TL_updateNewChannelMessage arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$23(this.f$0, this.f$1);
    }
}

