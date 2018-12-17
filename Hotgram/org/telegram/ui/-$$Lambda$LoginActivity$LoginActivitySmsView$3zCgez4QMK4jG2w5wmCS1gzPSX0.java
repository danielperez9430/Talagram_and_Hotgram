package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_auth_signIn;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$LoginActivity$LoginActivitySmsView$3zCgez4QMK4jG2w5wmCS1gzPSX0 implements Runnable {
    public -$$Lambda$LoginActivity$LoginActivitySmsView$3zCgez4QMK4jG2w5wmCS1gzPSX0(LoginActivitySmsView arg1, TL_error arg2, TLObject arg3, TL_auth_signIn arg4, String arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        LoginActivitySmsView.lambda$null$8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

