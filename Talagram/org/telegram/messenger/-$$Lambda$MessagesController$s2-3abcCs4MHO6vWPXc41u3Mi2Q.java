package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$s2-3abcCs4MHO6vWPXc41u3Mi2Q implements RequestDelegate {
    public -$$Lambda$MessagesController$s2-3abcCs4MHO6vWPXc41u3Mi2Q(MessagesController arg1, long arg2, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, boolean arg10, int arg11, int arg12, int arg13, boolean arg14) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
        this.f$4 = arg6;
        this.f$5 = arg7;
        this.f$6 = arg8;
        this.f$7 = arg9;
        this.f$8 = arg10;
        this.f$9 = arg11;
        this.f$10 = arg12;
        this.f$11 = arg13;
        this.f$12 = arg14;
    }

    public final void run(TLObject arg18, TL_error arg19) {
        MessagesController.lambda$loadMessagesInternal$86(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, arg18, arg19);
    }
}

