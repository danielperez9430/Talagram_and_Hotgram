package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$CdfNy-4vWadAwS2mlkIXB5Nk9t4 implements RequestDelegate {
    static {
        -$$Lambda$MessagesController$CdfNy-4vWadAwS2mlkIXB5Nk9t4.INSTANCE = new -$$Lambda$MessagesController$CdfNy-4vWadAwS2mlkIXB5Nk9t4();
    }

    private -$$Lambda$MessagesController$CdfNy-4vWadAwS2mlkIXB5Nk9t4() {
        super();
    }

    public final void run(TLObject arg1, TL_error arg2) {
        MessagesController.lambda$hideReportSpam$21(arg1, arg2);
    }
}

