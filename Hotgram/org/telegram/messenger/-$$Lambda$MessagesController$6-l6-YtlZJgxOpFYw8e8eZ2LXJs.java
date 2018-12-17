package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$6-l6-YtlZJgxOpFYw8e8eZ2LXJs implements RequestDelegate {
    public -$$Lambda$MessagesController$6-l6-YtlZJgxOpFYw8e8eZ2LXJs(MessagesController arg1, int arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        MessagesController.lambda$sendTyping$85(this.f$0, this.f$1, this.f$2, arg7, arg8);
    }
}

