package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$TL_chatChannelParticipant;

public final class -$$Lambda$ChannelEditActivity$7EgoyoTv6M6kQ9_rvQc8D92hvWs implements DialogInterface$OnClickListener {
    public -$$Lambda$ChannelEditActivity$7EgoyoTv6M6kQ9_rvQc8D92hvWs(ChannelEditActivity arg1, ArrayList arg2, int arg3, ChannelParticipant arg4, TL_chatChannelParticipant arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void onClick(DialogInterface arg8, int arg9) {
        ChannelEditActivity.lambda$createMenuForParticipant$6(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

