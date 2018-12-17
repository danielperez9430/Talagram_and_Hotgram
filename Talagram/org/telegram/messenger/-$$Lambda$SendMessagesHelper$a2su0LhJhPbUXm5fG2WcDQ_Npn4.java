package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.TLRPC$TL_photo;

public final class -$$Lambda$SendMessagesHelper$a2su0LhJhPbUXm5fG2WcDQ_Npn4 implements Runnable {
    public -$$Lambda$SendMessagesHelper$a2su0LhJhPbUXm5fG2WcDQ_Npn4(SendMessagesHelper arg1, TL_photo arg2, MessageObject arg3, File arg4, DelayedMessage arg5, String arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

