package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.TLRPC$Document;

public final class -$$Lambda$SendMessagesHelper$ivsY9c3O0F76RgXSqAIraHVU0Fk implements Runnable {
    public -$$Lambda$SendMessagesHelper$ivsY9c3O0F76RgXSqAIraHVU0Fk(SendMessagesHelper arg1, DelayedMessage arg2, File arg3, Document arg4, MessageObject arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$3(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

