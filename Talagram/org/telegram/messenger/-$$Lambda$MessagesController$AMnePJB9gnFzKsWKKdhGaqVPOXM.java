package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$AMnePJB9gnFzKsWKKdhGaqVPOXM implements RequestDelegate {
    public -$$Lambda$MessagesController$AMnePJB9gnFzKsWKKdhGaqVPOXM(MessagesController arg1, long arg2, Chat arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        MessagesController.lambda$loadUnknownChannel$177(this.f$0, this.f$1, this.f$2, arg7, arg8);
    }
}

