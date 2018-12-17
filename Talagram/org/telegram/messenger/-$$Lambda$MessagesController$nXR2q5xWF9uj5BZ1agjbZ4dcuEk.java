package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$nXR2q5xWF9uj5BZ1agjbZ4dcuEk implements RequestDelegate {
    public -$$Lambda$MessagesController$nXR2q5xWF9uj5BZ1agjbZ4dcuEk(MessagesController arg1, ArrayList arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        MessagesController.lambda$loadPinnedDialogs$203(this.f$0, this.f$1, this.f$2, arg7, arg8);
    }
}

