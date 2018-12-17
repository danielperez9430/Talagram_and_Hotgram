package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;

public final class -$$Lambda$PassportActivity$3$hBvwZ-d4QGDnNuXdFnmSB9952Bs implements DialogInterface$OnClickListener {
    public -$$Lambda$PassportActivity$3$hBvwZ-d4QGDnNuXdFnmSB9952Bs(org.telegram.ui.PassportActivity$3 arg1, String arg2, String arg3, String arg4, Runnable arg5, ErrorRunnable arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void onClick(DialogInterface arg9, int arg10) {
        org.telegram.ui.PassportActivity$3.lambda$onIdentityDone$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, arg9, arg10);
    }
}

