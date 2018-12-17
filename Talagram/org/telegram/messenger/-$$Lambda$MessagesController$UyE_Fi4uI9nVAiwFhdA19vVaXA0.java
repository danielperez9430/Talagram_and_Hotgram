package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_channels_editBanned;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$UyE_Fi4uI9nVAiwFhdA19vVaXA0 implements RequestDelegate {
    public -$$Lambda$MessagesController$UyE_Fi4uI9nVAiwFhdA19vVaXA0(MessagesController arg1, int arg2, BaseFragment arg3, TL_channels_editBanned arg4, boolean arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        MessagesController.lambda$setUserBannedRole$40(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

