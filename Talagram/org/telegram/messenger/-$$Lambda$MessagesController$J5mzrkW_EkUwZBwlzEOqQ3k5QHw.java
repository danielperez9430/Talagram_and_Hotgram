package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputUser;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$J5mzrkW_EkUwZBwlzEOqQ3k5QHw implements RequestDelegate {
    public -$$Lambda$MessagesController$J5mzrkW_EkUwZBwlzEOqQ3k5QHw(MessagesController arg1, boolean arg2, InputUser arg3, int arg4, BaseFragment arg5, TLObject arg6, boolean arg7) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
    }

    public final void run(TLObject arg10, TL_error arg11) {
        MessagesController.lambda$addUserToChat$161(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, arg10, arg11);
    }
}

