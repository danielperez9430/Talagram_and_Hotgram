package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_help_proxyDataPromo;

public final class -$$Lambda$MessagesController$zUbvy1PC8LZv3dggfm853LrzbHo implements RequestDelegate {
    public -$$Lambda$MessagesController$zUbvy1PC8LZv3dggfm853LrzbHo(MessagesController arg1, TL_help_proxyDataPromo arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        MessagesController.lambda$null$76(this.f$0, this.f$1, this.f$2, arg7, arg8);
    }
}

