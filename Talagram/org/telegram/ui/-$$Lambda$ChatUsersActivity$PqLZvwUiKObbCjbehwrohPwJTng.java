package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChatParticipant;

public final class -$$Lambda$ChatUsersActivity$PqLZvwUiKObbCjbehwrohPwJTng implements DialogInterface$OnClickListener {
    public -$$Lambda$ChatUsersActivity$PqLZvwUiKObbCjbehwrohPwJTng(ChatUsersActivity arg1, ArrayList arg2, ChatParticipant arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        ChatUsersActivity.lambda$createMenuForParticipant$2(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

