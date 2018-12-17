package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$SendMessagesHelper$_VEctUoqz2Ju1DWusW06IpqoYCM implements Runnable {
    public -$$Lambda$SendMessagesHelper$_VEctUoqz2Ju1DWusW06IpqoYCM(SendMessagesHelper arg1, TL_error arg2, Message arg3, TLObject arg4, MessageObject arg5, String arg6, TLObject arg7) {
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
        SendMessagesHelper.lambda$null$31(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

