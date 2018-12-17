package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_verifyPhone;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$PassportActivity$PhoneConfirmationView$WoBNmSsV7pgLU685isRmKdlPJ0M implements Runnable {
    public -$$Lambda$PassportActivity$PhoneConfirmationView$WoBNmSsV7pgLU685isRmKdlPJ0M(PhoneConfirmationView arg1, TL_error arg2, TL_account_verifyPhone arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        PhoneConfirmationView.lambda$null$5(this.f$0, this.f$1, this.f$2);
    }
}

