package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.MessageObject;

public final class -$$Lambda$ChatActivity$csGRqCnkGG8nILNpgYUDLb8ioEQ implements DialogInterface$OnClickListener {
    public -$$Lambda$ChatActivity$csGRqCnkGG8nILNpgYUDLb8ioEQ(ChatActivity arg1, MessageObject arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        ChatActivity.lambda$shareMyContact$37(this.f$0, this.f$1, arg3, arg4);
    }
}

