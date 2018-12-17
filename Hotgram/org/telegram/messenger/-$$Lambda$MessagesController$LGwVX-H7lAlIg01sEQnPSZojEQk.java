package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$LGwVX-H7lAlIg01sEQnPSZojEQk implements RequestDelegate {
    public -$$Lambda$MessagesController$LGwVX-H7lAlIg01sEQnPSZojEQk(MessagesController arg1, Chat arg2, long arg3, int arg5, int arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg5;
        this.f$4 = arg6;
    }

    public final void run(TLObject arg9, TL_error arg10) {
        MessagesController.lambda$loadFullChat$15(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg9, arg10);
    }
}

