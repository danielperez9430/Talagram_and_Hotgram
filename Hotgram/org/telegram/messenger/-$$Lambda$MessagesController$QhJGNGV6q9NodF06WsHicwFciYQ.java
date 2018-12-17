package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$QhJGNGV6q9NodF06WsHicwFciYQ implements RequestDelegate {
    public -$$Lambda$MessagesController$QhJGNGV6q9NodF06WsHicwFciYQ(MessagesController arg1, long arg2, Chat arg4, ArrayList arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        MessagesController.lambda$reloadMessages$20(this.f$0, this.f$1, this.f$2, this.f$3, arg8, arg9);
    }
}

