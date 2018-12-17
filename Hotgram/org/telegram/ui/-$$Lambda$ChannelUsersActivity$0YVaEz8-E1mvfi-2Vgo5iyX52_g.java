package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$ChannelUsersActivity$0YVaEz8-E1mvfi-2Vgo5iyX52_g implements DialogInterface$OnClickListener {
    public -$$Lambda$ChannelUsersActivity$0YVaEz8-E1mvfi-2Vgo5iyX52_g(ChannelUsersActivity arg1, ArrayList arg2, User arg3, ChannelParticipant arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void onClick(DialogInterface arg7, int arg8) {
        ChannelUsersActivity.lambda$createMenuForParticipant$6(this.f$0, this.f$1, this.f$2, this.f$3, arg7, arg8);
    }
}

