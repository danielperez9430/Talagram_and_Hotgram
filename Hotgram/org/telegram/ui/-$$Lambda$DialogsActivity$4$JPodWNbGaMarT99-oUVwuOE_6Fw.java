package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$TL_dialog;

public final class -$$Lambda$DialogsActivity$4$JPodWNbGaMarT99-oUVwuOE_6Fw implements DialogInterface$OnClickListener {
    public -$$Lambda$DialogsActivity$4$JPodWNbGaMarT99-oUVwuOE_6Fw(org.telegram.ui.DialogsActivity$4 arg1, boolean arg2, boolean arg3, TL_dialog arg4, Context arg5, boolean arg6, boolean arg7) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
    }

    public final void onClick(DialogInterface arg10, int arg11) {
        org.telegram.ui.DialogsActivity$4.lambda$onItemClick$5(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, arg10, arg11);
    }
}

