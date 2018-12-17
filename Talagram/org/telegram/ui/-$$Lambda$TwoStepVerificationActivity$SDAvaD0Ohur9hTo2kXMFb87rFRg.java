package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$TL_account_updatePasswordSettings;

public final class -$$Lambda$TwoStepVerificationActivity$SDAvaD0Ohur9hTo2kXMFb87rFRg implements DialogInterface$OnClickListener {
    public -$$Lambda$TwoStepVerificationActivity$SDAvaD0Ohur9hTo2kXMFb87rFRg(TwoStepVerificationActivity arg1, TL_account_updatePasswordSettings arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        TwoStepVerificationActivity.lambda$null$17(this.f$0, this.f$1, arg3, arg4);
    }
}

