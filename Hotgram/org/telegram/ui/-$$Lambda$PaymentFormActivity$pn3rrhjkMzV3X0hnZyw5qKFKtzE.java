package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_getPassword;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$PaymentFormActivity$pn3rrhjkMzV3X0hnZyw5qKFKtzE implements Runnable {
    public -$$Lambda$PaymentFormActivity$pn3rrhjkMzV3X0hnZyw5qKFKtzE(PaymentFormActivity arg1, TL_error arg2, TLObject arg3, String arg4, TL_account_getPassword arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        PaymentFormActivity.lambda$null$39(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

