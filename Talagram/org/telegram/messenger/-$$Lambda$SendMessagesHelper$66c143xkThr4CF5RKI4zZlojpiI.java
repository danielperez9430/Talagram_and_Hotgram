package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ChatActivity;

public final class -$$Lambda$SendMessagesHelper$66c143xkThr4CF5RKI4zZlojpiI implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$66c143xkThr4CF5RKI4zZlojpiI(SendMessagesHelper arg1, String arg2, boolean arg3, MessageObject arg4, KeyboardButton arg5, ChatActivity arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run(TLObject arg9, TL_error arg10) {
        SendMessagesHelper.lambda$sendCallback$16(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, arg9, arg10);
    }
}

