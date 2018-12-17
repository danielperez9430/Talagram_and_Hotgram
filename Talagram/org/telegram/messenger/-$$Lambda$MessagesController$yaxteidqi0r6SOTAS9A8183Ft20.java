package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$yaxteidqi0r6SOTAS9A8183Ft20 implements RequestDelegate {
    public -$$Lambda$MessagesController$yaxteidqi0r6SOTAS9A8183Ft20(MessagesController arg1, TL_dialog arg2, long arg3, int arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg5;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        MessagesController.lambda$checkLastDialogMessage$115(this.f$0, this.f$1, this.f$2, this.f$3, arg8, arg9);
    }
}

