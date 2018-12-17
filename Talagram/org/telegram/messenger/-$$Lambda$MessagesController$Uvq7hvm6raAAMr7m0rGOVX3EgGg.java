package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Uvq7hvm6raAAMr7m0rGOVX3EgGg implements RequestDelegate {
    public -$$Lambda$MessagesController$Uvq7hvm6raAAMr7m0rGOVX3EgGg(MessagesController arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void run(TLObject arg2, TL_error arg3) {
        MessagesController.lambda$markMentionMessageAsRead$122(this.f$0, arg2, arg3);
    }
}

