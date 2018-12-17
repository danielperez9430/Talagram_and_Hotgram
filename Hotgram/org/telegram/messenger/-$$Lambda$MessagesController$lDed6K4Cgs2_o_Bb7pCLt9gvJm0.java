package org.telegram.messenger;

import android.os.Bundle;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$lDed6K4Cgs2_o_Bb7pCLt9gvJm0 implements RequestDelegate {
    public -$$Lambda$MessagesController$lDed6K4Cgs2_o_Bb7pCLt9gvJm0(MessagesController arg1, AlertDialog arg2, BaseFragment arg3, Bundle arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run(TLObject arg7, TL_error arg8) {
        MessagesController.lambda$checkCanOpenChat$235(this.f$0, this.f$1, this.f$2, this.f$3, arg7, arg8);
    }
}

