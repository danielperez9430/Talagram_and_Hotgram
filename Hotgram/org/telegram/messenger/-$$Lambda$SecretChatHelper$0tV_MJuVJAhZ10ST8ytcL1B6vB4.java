package org.telegram.messenger;

import android.content.Context;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$SecretChatHelper$0tV_MJuVJAhZ10ST8ytcL1B6vB4 implements Runnable {
    public -$$Lambda$SecretChatHelper$0tV_MJuVJAhZ10ST8ytcL1B6vB4(SecretChatHelper arg1, Context arg2, AlertDialog arg3, TLObject arg4, byte[] arg5, User arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        SecretChatHelper.lambda$null$25(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

