package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MentionsAdapter$5$1XYBdNxnlywSfagmmFhv7YzEVUE implements Runnable {
    public -$$Lambda$MentionsAdapter$5$1XYBdNxnlywSfagmmFhv7YzEVUE(org.telegram.ui.Adapters.MentionsAdapter$5 arg1, int arg2, TL_error arg3, TLObject arg4, MessagesController arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        org.telegram.ui.Adapters.MentionsAdapter$5.lambda$null$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

