package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.ui.ChatActivity;

public final class -$$Lambda$SendMessagesHelper$5zZ6Shakv_Fag_4_-ZEueYXewKM implements Runnable {
    public -$$Lambda$SendMessagesHelper$5zZ6Shakv_Fag_4_-ZEueYXewKM(SendMessagesHelper arg1, String arg2, boolean arg3, TLObject arg4, MessageObject arg5, KeyboardButton arg6, ChatActivity arg7) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$15(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

