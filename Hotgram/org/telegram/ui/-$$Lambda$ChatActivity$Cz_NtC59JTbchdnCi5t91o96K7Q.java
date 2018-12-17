package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC$TL_game;

public final class -$$Lambda$ChatActivity$Cz_NtC59JTbchdnCi5t91o96K7Q implements DialogInterface$OnClickListener {
    public -$$Lambda$ChatActivity$Cz_NtC59JTbchdnCi5t91o96K7Q(ChatActivity arg1, TL_game arg2, MessageObject arg3, String arg4, int arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void onClick(DialogInterface arg8, int arg9) {
        ChatActivity.lambda$showOpenGameAlert$76(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

