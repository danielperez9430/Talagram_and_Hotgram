package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$SendMessagesHelper$Hz_E06bUdMZsW84eZSY_8HIZh4k implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$Hz_E06bUdMZsW84eZSY_8HIZh4k(SendMessagesHelper arg1, TLObject arg2, Message arg3, MessageObject arg4, String arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        SendMessagesHelper.lambda$performSendMessageRequest$39(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

