package org.telegram.ui;

import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$ExternalActionActivity$nedkJY28xiiP1o1eFsvU2LbFQxc implements DialogInterface$OnDismissListener {
    public -$$Lambda$ExternalActionActivity$nedkJY28xiiP1o1eFsvU2LbFQxc(ExternalActionActivity arg1, TL_error arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onDismiss(DialogInterface arg3) {
        ExternalActionActivity.lambda$null$7(this.f$0, this.f$1, arg3);
    }
}

