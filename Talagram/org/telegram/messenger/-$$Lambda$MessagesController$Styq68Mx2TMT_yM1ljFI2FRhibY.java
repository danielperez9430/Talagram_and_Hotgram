package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_createChat;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$Styq68Mx2TMT_yM1ljFI2FRhibY implements RequestDelegate {
    public -$$Lambda$MessagesController$Styq68Mx2TMT_yM1ljFI2FRhibY(MessagesController arg1, BaseFragment arg2, TL_messages_createChat arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$createChat$136(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

