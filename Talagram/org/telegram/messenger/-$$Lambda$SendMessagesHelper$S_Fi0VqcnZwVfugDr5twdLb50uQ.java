package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_sendMultiMedia;

public final class -$$Lambda$SendMessagesHelper$S_Fi0VqcnZwVfugDr5twdLb50uQ implements Runnable {
    public -$$Lambda$SendMessagesHelper$S_Fi0VqcnZwVfugDr5twdLb50uQ(SendMessagesHelper arg1, TL_error arg2, TLObject arg3, ArrayList arg4, ArrayList arg5, TL_messages_sendMultiMedia arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$27(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

