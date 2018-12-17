package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputMedia;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$SendMessagesHelper$6rG3L4brQuIUzjg1gn78nAV6RBU implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$6rG3L4brQuIUzjg1gn78nAV6RBU(SendMessagesHelper arg1, InputMedia arg2, DelayedMessage arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        SendMessagesHelper.lambda$uploadMultiMedia$19(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

