package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$Chat;

public final class -$$Lambda$DialogsActivity$4$HFtkpLAhZxPwNgo8LEXNl1noZtc implements DialogInterface$OnClickListener {
    public -$$Lambda$DialogsActivity$4$HFtkpLAhZxPwNgo8LEXNl1noZtc(org.telegram.ui.DialogsActivity$4 arg1, Chat arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        org.telegram.ui.DialogsActivity$4.lambda$null$1(this.f$0, this.f$1, arg3, arg4);
    }
}

