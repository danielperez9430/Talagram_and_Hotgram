package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$messages_Dialogs;

public final class -$$Lambda$MessagesController$xbWXXmtbkSNLW0cnO_Z6k_DgWPg implements Runnable {
    public -$$Lambda$MessagesController$xbWXXmtbkSNLW0cnO_Z6k_DgWPg(MessagesController arg1, int arg2, messages_Dialogs arg3, boolean arg4, int arg5, int arg6, boolean arg7, boolean arg8, ArrayList arg9) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
        this.f$7 = arg8;
        this.f$8 = arg9;
    }

    public final void run() {
        MessagesController.lambda$processLoadedDialogs$108(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}

