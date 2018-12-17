package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_document;

public final class -$$Lambda$SendMessagesHelper$UjZuu4mGt36dwBa1SyqOFMB9d5A implements Runnable {
    public -$$Lambda$SendMessagesHelper$UjZuu4mGt36dwBa1SyqOFMB9d5A(MessageObject arg1, int arg2, TL_document arg3, MessageObject arg4, HashMap arg5, long arg6, MessageObject arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg8;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$44(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

