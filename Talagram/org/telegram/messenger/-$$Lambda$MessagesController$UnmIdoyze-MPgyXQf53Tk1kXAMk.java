package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$UnmIdoyze-MPgyXQf53Tk1kXAMk implements Runnable {
    public -$$Lambda$MessagesController$UnmIdoyze-MPgyXQf53Tk1kXAMk(MessagesController arg1, TL_error arg2, BaseFragment arg3, TLObject arg4, boolean arg5, boolean arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        MessagesController.lambda$null$159(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

