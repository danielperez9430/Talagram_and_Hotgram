package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.MessageObject$GroupedMessages;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$ChatActivity$KCka37XILHjXmOBucNPvmQKedHs implements DialogInterface$OnClickListener {
    public -$$Lambda$ChatActivity$KCka37XILHjXmOBucNPvmQKedHs(ChatActivity arg1, MessageObject arg2, GroupedMessages arg3, boolean[] arg4, User arg5, boolean[] arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void onClick(DialogInterface arg9, int arg10) {
        ChatActivity.lambda$createDeleteMessagesAlert$66(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, arg9, arg10);
    }
}

