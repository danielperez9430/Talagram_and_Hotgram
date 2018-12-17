package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_channels_createChannel;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$lnbu-l9eb726LihMu9bTxQeXuRI implements RequestDelegate {
    public -$$Lambda$MessagesController$lnbu-l9eb726LihMu9bTxQeXuRI(MessagesController arg1, BaseFragment arg2, TL_channels_createChannel arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$createChat$139(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

