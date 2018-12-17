package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_updatePasswordSettings;

public final class -$$Lambda$PaymentFormActivity$pZGFceay_z-PIzgJ2hdp5VPxlI0 implements Runnable {
    public -$$Lambda$PaymentFormActivity$pZGFceay_z-PIzgJ2hdp5VPxlI0(PaymentFormActivity arg1, boolean arg2, String arg3, String arg4, TL_account_updatePasswordSettings arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        PaymentFormActivity.lambda$sendSavePassword$28(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

