package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Q3c_jkH9qRjp4L8dI5BwzaSr1HA implements RequestDelegate {
    public -$$Lambda$MessagesController$Q3c_jkH9qRjp4L8dI5BwzaSr1HA(MessagesController arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void run(TLObject arg2, TL_error arg3) {
        MessagesController.lambda$loadUnreadDialogs$199(this.f$0, arg2, arg3);
    }
}

