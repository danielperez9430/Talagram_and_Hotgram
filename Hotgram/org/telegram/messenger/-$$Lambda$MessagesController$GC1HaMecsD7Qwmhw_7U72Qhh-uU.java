package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$GC1HaMecsD7Qwmhw_7U72Qhh-uU implements RequestDelegate {
    public -$$Lambda$MessagesController$GC1HaMecsD7Qwmhw_7U72Qhh-uU(MessagesController arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        MessagesController.lambda$loadDialogs$95(this.f$0, this.f$1, arg3, arg4);
    }
}

