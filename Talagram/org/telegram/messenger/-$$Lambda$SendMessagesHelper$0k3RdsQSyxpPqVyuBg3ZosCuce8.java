package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_editMessage;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$SendMessagesHelper$0k3RdsQSyxpPqVyuBg3ZosCuce8 implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$0k3RdsQSyxpPqVyuBg3ZosCuce8(SendMessagesHelper arg1, BaseFragment arg2, TL_messages_editMessage arg3, Runnable arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        SendMessagesHelper.lambda$editMessage$11(this.f$0, this.f$1, this.f$2, this.f$3, arg7, arg8);
    }
}

