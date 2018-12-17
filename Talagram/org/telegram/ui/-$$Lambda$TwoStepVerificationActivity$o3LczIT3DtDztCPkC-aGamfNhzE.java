package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$TL_auth_passwordRecovery;

public final class -$$Lambda$TwoStepVerificationActivity$o3LczIT3DtDztCPkC-aGamfNhzE implements DialogInterface$OnClickListener {
    public -$$Lambda$TwoStepVerificationActivity$o3LczIT3DtDztCPkC-aGamfNhzE(TwoStepVerificationActivity arg1, TL_auth_passwordRecovery arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        TwoStepVerificationActivity.lambda$null$1(this.f$0, this.f$1, arg3, arg4);
    }
}

