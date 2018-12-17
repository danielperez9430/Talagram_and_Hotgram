package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_photo;

public final class -$$Lambda$SendMessagesHelper$PSxcRQwoLdCLt6n3OfpykwY_9LY implements Runnable {
    public -$$Lambda$SendMessagesHelper$PSxcRQwoLdCLt6n3OfpykwY_9LY(MessageObject arg1, int arg2, TL_photo arg3, HashMap arg4, long arg5, MessageObject arg7, SendingMediaInfo arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg7;
        this.f$6 = arg8;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$57(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

