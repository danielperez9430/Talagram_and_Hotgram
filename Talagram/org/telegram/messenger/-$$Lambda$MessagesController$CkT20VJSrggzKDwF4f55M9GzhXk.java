package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$CkT20VJSrggzKDwF4f55M9GzhXk implements RequestDelegate {
    public -$$Lambda$MessagesController$CkT20VJSrggzKDwF4f55M9GzhXk(MessagesController arg1, long arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$deleteMessages$52(this.f$0, this.f$1, arg4, arg5);
    }
}

