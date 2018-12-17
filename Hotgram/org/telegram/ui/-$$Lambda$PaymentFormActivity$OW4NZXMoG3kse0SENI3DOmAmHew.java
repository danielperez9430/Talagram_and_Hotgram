package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_payments_sendPaymentForm;

public final class -$$Lambda$PaymentFormActivity$OW4NZXMoG3kse0SENI3DOmAmHew implements Runnable {
    public -$$Lambda$PaymentFormActivity$OW4NZXMoG3kse0SENI3DOmAmHew(PaymentFormActivity arg1, TL_error arg2, TL_payments_sendPaymentForm arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        PaymentFormActivity.lambda$null$34(this.f$0, this.f$1, this.f$2);
    }
}

