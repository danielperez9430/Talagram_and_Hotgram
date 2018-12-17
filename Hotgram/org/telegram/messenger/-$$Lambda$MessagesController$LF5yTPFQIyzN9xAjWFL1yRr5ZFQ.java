package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$LF5yTPFQIyzN9xAjWFL1yRr5ZFQ implements RequestDelegate {
    public -$$Lambda$MessagesController$LF5yTPFQIyzN9xAjWFL1yRr5ZFQ(MessagesController arg4, int arg5, int arg6, int arg7, long arg8, int arg10, int arg11, int arg12, int arg13, int arg14, int arg15, boolean arg16, int arg17, boolean arg18, int arg19) {
        super();
        this.f$0 = arg4;
        this.f$1 = arg5;
        this.f$2 = arg6;
        this.f$3 = arg7;
        this.f$4 = arg8;
        this.f$5 = arg10;
        this.f$6 = arg11;
        this.f$7 = arg12;
        this.f$8 = arg13;
        this.f$9 = arg14;
        this.f$10 = arg15;
        this.f$11 = arg16;
        this.f$12 = arg17;
        this.f$13 = arg18;
        this.f$14 = arg19;
    }

    public final void run(TLObject arg21, TL_error arg22) {
        MessagesController.lambda$loadMessagesInternal$87(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, arg21, arg22);
    }
}

