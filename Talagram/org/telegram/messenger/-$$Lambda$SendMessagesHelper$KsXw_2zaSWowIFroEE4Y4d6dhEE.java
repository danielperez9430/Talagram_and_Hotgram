package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_photo;

public final class -$$Lambda$SendMessagesHelper$KsXw_2zaSWowIFroEE4Y4d6dhEE implements Runnable {
    public -$$Lambda$SendMessagesHelper$KsXw_2zaSWowIFroEE4Y4d6dhEE(MessageObject arg1, int arg2, TL_photo arg3, boolean arg4, SendingMediaInfo arg5, HashMap arg6, long arg7, MessageObject arg9) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
        this.f$7 = arg9;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$55(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}

