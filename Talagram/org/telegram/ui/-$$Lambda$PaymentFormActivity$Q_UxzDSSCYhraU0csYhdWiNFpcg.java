package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_getTmpPassword;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$PaymentFormActivity$Q_UxzDSSCYhraU0csYhdWiNFpcg implements Runnable {
    public -$$Lambda$PaymentFormActivity$Q_UxzDSSCYhraU0csYhdWiNFpcg(PaymentFormActivity arg1, TLObject arg2, TL_error arg3, TL_account_getTmpPassword arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        PaymentFormActivity.lambda$null$36(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

