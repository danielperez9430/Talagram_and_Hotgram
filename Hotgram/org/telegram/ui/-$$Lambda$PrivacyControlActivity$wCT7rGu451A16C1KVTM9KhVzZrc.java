package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$PrivacyControlActivity$wCT7rGu451A16C1KVTM9KhVzZrc implements Runnable {
    public -$$Lambda$PrivacyControlActivity$wCT7rGu451A16C1KVTM9KhVzZrc(PrivacyControlActivity arg1, AlertDialog arg2, TL_error arg3, TLObject arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        PrivacyControlActivity.lambda$null$3(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

