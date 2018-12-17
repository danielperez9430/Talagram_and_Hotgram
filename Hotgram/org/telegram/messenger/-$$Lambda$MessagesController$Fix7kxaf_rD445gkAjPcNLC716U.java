package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$Fix7kxaf_rD445gkAjPcNLC716U implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$Fix7kxaf_rD445gkAjPcNLC716U.INSTANCE = new -$$Lambda$MessagesController$Fix7kxaf_rD445gkAjPcNLC716U();
    }

    private -$$Lambda$MessagesController$Fix7kxaf_rD445gkAjPcNLC716U() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$markMessageContentAsRead$119(arg1, arg2);
    }
}

