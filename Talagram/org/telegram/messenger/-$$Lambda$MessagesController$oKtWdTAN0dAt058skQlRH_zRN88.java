package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$InputUser;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$MessagesController$oKtWdTAN0dAt058skQlRH_zRN88 implements RequestDelegate {
    public -$$Lambda$MessagesController$oKtWdTAN0dAt058skQlRH_zRN88(MessagesController arg1, User arg2, int arg3, boolean arg4, InputUser arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        MessagesController.lambda$deleteUserFromChat$164(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

