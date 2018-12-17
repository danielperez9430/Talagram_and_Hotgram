package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_sendVerifyPhoneCode;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$PassportActivity$hLGy5GY7nr8K5LZQ5mUFeMfHUjQ implements Runnable {
    public -$$Lambda$PassportActivity$hLGy5GY7nr8K5LZQ5mUFeMfHUjQ(PassportActivity arg1, TL_error arg2, String arg3, PassportActivityDelegate arg4, TLObject arg5, TL_account_sendVerifyPhoneCode arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        PassportActivity.lambda$null$65(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

