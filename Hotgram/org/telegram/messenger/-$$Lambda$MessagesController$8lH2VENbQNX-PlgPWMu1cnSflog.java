package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$8lH2VENbQNX-PlgPWMu1cnSflog implements RequestDelegate {
    public -$$Lambda$MessagesController$8lH2VENbQNX-PlgPWMu1cnSflog(MessagesController arg1, Integer arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        MessagesController.lambda$loadChannelParticipants$63(this.f$0, this.f$1, arg3, arg4);
    }
}

