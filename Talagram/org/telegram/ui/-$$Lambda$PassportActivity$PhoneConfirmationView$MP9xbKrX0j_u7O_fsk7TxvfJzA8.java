package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_auth_resendCode;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$PassportActivity$PhoneConfirmationView$MP9xbKrX0j_u7O_fsk7TxvfJzA8 implements Runnable {
    public -$$Lambda$PassportActivity$PhoneConfirmationView$MP9xbKrX0j_u7O_fsk7TxvfJzA8(PhoneConfirmationView arg1, TL_error arg2, Bundle arg3, TLObject arg4, TL_auth_resendCode arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        PhoneConfirmationView.lambda$null$3(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

