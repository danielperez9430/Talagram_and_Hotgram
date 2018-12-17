package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MentionsAdapter$4$ie2Kd71f-OJP1CoMCJ5ZnpnLA2g implements Runnable {
    public -$$Lambda$MentionsAdapter$4$ie2Kd71f-OJP1CoMCJ5ZnpnLA2g(org.telegram.ui.Adapters.MentionsAdapter$4 arg1, String arg2, TL_error arg3, TLObject arg4, MessagesController arg5, MessagesStorage arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        org.telegram.ui.Adapters.MentionsAdapter$4.lambda$null$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

