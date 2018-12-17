package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_updatePasswordSettings;

public final class -$$Lambda$TwoStepVerificationActivity$UFide-t6HW-j9ofyyfe5H2IfY1E implements Runnable {
    public -$$Lambda$TwoStepVerificationActivity$UFide-t6HW-j9ofyyfe5H2IfY1E(TwoStepVerificationActivity arg1, TL_account_updatePasswordSettings arg2, boolean arg3, String arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        TwoStepVerificationActivity.lambda$setNewPassword$20(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

