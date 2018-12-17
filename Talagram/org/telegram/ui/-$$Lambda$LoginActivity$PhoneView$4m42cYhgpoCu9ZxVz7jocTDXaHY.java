package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_auth_sendCode;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$LoginActivity$PhoneView$4m42cYhgpoCu9ZxVz7jocTDXaHY implements Runnable {
    public -$$Lambda$LoginActivity$PhoneView$4m42cYhgpoCu9ZxVz7jocTDXaHY(PhoneView arg1, TL_error arg2, Bundle arg3, TLObject arg4, TL_auth_sendCode arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        PhoneView.lambda$null$6(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

