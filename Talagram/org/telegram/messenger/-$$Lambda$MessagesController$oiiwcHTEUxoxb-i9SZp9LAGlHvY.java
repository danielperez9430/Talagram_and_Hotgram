package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_channels_editAdmin;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$oiiwcHTEUxoxb-i9SZp9LAGlHvY implements Runnable {
    public -$$Lambda$MessagesController$oiiwcHTEUxoxb-i9SZp9LAGlHvY(MessagesController arg1, TL_error arg2, BaseFragment arg3, TL_channels_editAdmin arg4, boolean arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        MessagesController.lambda$null$42(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

