package org.telegram.ui;

import org.telegram.tgnet.TLRPC$TL_account_verifyEmail;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$PassportActivity$3$H95RgqCbEw0JF2FdjyTyxP1Zzp8 implements Runnable {
    public -$$Lambda$PassportActivity$3$H95RgqCbEw0JF2FdjyTyxP1Zzp8(org.telegram.ui.PassportActivity$3 arg1, TL_error arg2, Runnable arg3, ErrorRunnable arg4, TL_account_verifyEmail arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        org.telegram.ui.PassportActivity$3.lambda$null$5(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

