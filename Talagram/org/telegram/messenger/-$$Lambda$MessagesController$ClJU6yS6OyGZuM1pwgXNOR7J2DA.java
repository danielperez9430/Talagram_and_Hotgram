package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_channels_inviteToChannel;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$ClJU6yS6OyGZuM1pwgXNOR7J2DA implements RequestDelegate {
    public -$$Lambda$MessagesController$ClJU6yS6OyGZuM1pwgXNOR7J2DA(MessagesController arg1, BaseFragment arg2, TL_channels_inviteToChannel arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run(TLObject arg4, TL_error arg5) {
        MessagesController.lambda$addUsersToChannel$145(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

