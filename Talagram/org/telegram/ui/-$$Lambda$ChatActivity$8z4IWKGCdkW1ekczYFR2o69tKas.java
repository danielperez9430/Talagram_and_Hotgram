package org.telegram.ui;

import org.telegram.messenger.MessageObject$GroupedMessages;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;

public final class -$$Lambda$ChatActivity$8z4IWKGCdkW1ekczYFR2o69tKas implements Runnable {
    public -$$Lambda$ChatActivity$8z4IWKGCdkW1ekczYFR2o69tKas(ChatActivity arg1, AlertDialog[] arg2, TLObject arg3, MessageObject arg4, GroupedMessages arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        ChatActivity.lambda$null$58(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

