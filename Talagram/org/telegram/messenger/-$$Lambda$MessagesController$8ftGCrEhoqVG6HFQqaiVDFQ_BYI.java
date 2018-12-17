package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$8ftGCrEhoqVG6HFQqaiVDFQ_BYI implements Runnable {
    public -$$Lambda$MessagesController$8ftGCrEhoqVG6HFQqaiVDFQ_BYI(MessagesController arg1, AlertDialog[] arg2, BaseFragment arg3, TL_error arg4, TLObject arg5, int arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        MessagesController.lambda$null$237(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

