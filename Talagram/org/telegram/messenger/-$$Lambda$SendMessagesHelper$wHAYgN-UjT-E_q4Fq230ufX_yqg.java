package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_sendMultiMedia;

public final class -$$Lambda$SendMessagesHelper$wHAYgN-UjT-E_q4Fq230ufX_yqg implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$wHAYgN-UjT-E_q4Fq230ufX_yqg(SendMessagesHelper arg1, ArrayList arg2, ArrayList arg3, TL_messages_sendMultiMedia arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        SendMessagesHelper.lambda$performSendMessageRequestMulti$28(this.f$0, this.f$1, this.f$2, this.f$3, arg7, arg8);
    }
}

