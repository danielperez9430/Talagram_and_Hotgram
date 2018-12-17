package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$poCHi3ajJ1OgG00CEx7rxwPUA6I implements RequestDelegate {
    public -$$Lambda$MessagesController$poCHi3ajJ1OgG00CEx7rxwPUA6I(MessagesController arg1) {
        super();
        this.f$0 = arg1;
    }

    public final void run(TLObject arg2, TL_error arg3) {
        MessagesController.lambda$reloadDialogsReadValue$10(this.f$0, arg2, arg3);
    }
}

