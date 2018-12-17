package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$TL_account_updatePasswordSettings;

public final class -$$Lambda$TwoStepVerificationActivity$QY1yrfchi8y7D8u2cuEeQLe1jQY implements DialogInterface$OnClickListener {
    public -$$Lambda$TwoStepVerificationActivity$QY1yrfchi8y7D8u2cuEeQLe1jQY(TwoStepVerificationActivity arg1, byte[] arg2, TL_account_updatePasswordSettings arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        TwoStepVerificationActivity.lambda$null$16(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

