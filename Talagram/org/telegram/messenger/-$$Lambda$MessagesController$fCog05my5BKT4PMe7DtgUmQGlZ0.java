package org.telegram.messenger;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$MessagesController$fCog05my5BKT4PMe7DtgUmQGlZ0 implements RequestDelegate {
    public -$$Lambda$MessagesController$fCog05my5BKT4PMe7DtgUmQGlZ0(MessagesController arg1, Context arg2, AlertDialog arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$convertToMegaGroup$142(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

