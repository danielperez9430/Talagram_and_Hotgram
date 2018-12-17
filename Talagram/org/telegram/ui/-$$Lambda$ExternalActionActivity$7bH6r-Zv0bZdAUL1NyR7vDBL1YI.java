package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_authorizationForm;
import org.telegram.tgnet.TLRPC$TL_account_getAuthorizationForm;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$ExternalActionActivity$7bH6r-Zv0bZdAUL1NyR7vDBL1YI implements Runnable {
    public -$$Lambda$ExternalActionActivity$7bH6r-Zv0bZdAUL1NyR7vDBL1YI(ExternalActionActivity arg1, AlertDialog arg2, TLObject arg3, int arg4, TL_account_authorizationForm arg5, TL_account_getAuthorizationForm arg6, String arg7, String arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
        this.f$7 = arg8;
    }

    public final void run() {
        ExternalActionActivity.lambda$null$5(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}

