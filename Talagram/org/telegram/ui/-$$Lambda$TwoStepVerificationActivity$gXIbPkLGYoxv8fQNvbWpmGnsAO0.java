package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_updatePasswordSettings;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$TwoStepVerificationActivity$gXIbPkLGYoxv8fQNvbWpmGnsAO0 implements Runnable {
    public -$$Lambda$TwoStepVerificationActivity$gXIbPkLGYoxv8fQNvbWpmGnsAO0(TwoStepVerificationActivity arg1, TL_error arg2, boolean arg3, TLObject arg4, byte[] arg5, TL_account_updatePasswordSettings arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        TwoStepVerificationActivity.lambda$null$18(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

