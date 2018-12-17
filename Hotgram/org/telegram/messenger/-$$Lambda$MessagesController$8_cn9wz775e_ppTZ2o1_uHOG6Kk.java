package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$8_cn9wz775e_ppTZ2o1_uHOG6Kk implements RequestDelegate {
    public -$$Lambda$MessagesController$8_cn9wz775e_ppTZ2o1_uHOG6Kk(MessagesController arg1, int arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        MessagesController.lambda$deleteMessages$51(this.f$0, this.f$1, this.f$2, arg7, arg8);
    }
}

