package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_account_setAccountTTL;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$PrivacySettingsActivity$LsF3KI7j3KNxTTiwgalmqj7HqKo implements Runnable {
    public -$$Lambda$PrivacySettingsActivity$LsF3KI7j3KNxTTiwgalmqj7HqKo(PrivacySettingsActivity arg1, AlertDialog arg2, TLObject arg3, TL_account_setAccountTTL arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        PrivacySettingsActivity.lambda$null$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

