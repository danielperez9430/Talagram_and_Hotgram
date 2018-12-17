package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$TL_dialog;

public final class -$$Lambda$DialogsActivity$4$oYXqIxenwjkoWXbI4BFEsY6kRXM implements DialogInterface$OnClickListener {
    public -$$Lambda$DialogsActivity$4$oYXqIxenwjkoWXbI4BFEsY6kRXM(org.telegram.ui.DialogsActivity$4 arg1, boolean arg2, boolean arg3, TL_dialog arg4, Context arg5, Chat arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void onClick(DialogInterface arg9, int arg10) {
        org.telegram.ui.DialogsActivity$4.lambda$onItemClick$3(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, arg9, arg10);
    }
}

