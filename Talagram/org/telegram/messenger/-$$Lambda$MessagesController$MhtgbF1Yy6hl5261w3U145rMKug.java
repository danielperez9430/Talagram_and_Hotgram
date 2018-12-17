package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$ChatFull;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$MhtgbF1Yy6hl5261w3U145rMKug implements RequestDelegate {
    public -$$Lambda$MessagesController$MhtgbF1Yy6hl5261w3U145rMKug(MessagesController arg1, ChatFull arg2, String arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$updateChannelAbout$152(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

