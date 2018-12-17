package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$MentionsAdapter$HVDPv79fAM1RehGsWWysAxv-W1Q implements Runnable {
    public -$$Lambda$MentionsAdapter$HVDPv79fAM1RehGsWWysAxv-W1Q(MentionsAdapter arg1, String arg2, boolean arg3, TLObject arg4, User arg5, String arg6, MessagesStorage arg7, String arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
        this.f$7 = arg8;
    }

    public final void run() {
        MentionsAdapter.lambda$null$3(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}

