package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Fij5djSH_vsnr6kSX29dQd1wFbA implements RequestDelegate {
    public -$$Lambda$MessagesController$Fij5djSH_vsnr6kSX29dQd1wFbA(MessagesController arg1, String arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        MessagesController.lambda$reloadWebPages$89(this.f$0, this.f$1, this.f$2, arg7, arg8);
    }
}

