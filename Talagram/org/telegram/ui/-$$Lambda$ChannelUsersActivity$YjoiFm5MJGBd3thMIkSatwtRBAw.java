package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC$ChannelParticipant;

public final class -$$Lambda$ChannelUsersActivity$YjoiFm5MJGBd3thMIkSatwtRBAw implements DialogInterface$OnClickListener {
    public -$$Lambda$ChannelUsersActivity$YjoiFm5MJGBd3thMIkSatwtRBAw(ChannelUsersActivity arg1, ChannelParticipant arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onClick(DialogInterface arg3, int arg4) {
        ChannelUsersActivity.lambda$createMenuForParticipant$9(this.f$0, this.f$1, arg3, arg4);
    }
}

