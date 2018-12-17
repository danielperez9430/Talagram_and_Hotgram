package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Lsua2O97Z1bK-SGh1OPmaeXK_Ts implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$Lsua2O97Z1bK-SGh1OPmaeXK_Ts.INSTANCE = new -$$Lambda$MessagesController$Lsua2O97Z1bK-SGh1OPmaeXK_Ts();
    }

    private -$$Lambda$MessagesController$Lsua2O97Z1bK-SGh1OPmaeXK_Ts() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$markMentionMessageAsRead$121(arg1, arg2);
    }
}

