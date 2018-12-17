package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_channels_createChannel;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$h23eEOIYKX1ARJ3iKezV603ObW8 implements Runnable {
    public -$$Lambda$MessagesController$h23eEOIYKX1ARJ3iKezV603ObW8(MessagesController arg1, TL_error arg2, BaseFragment arg3, TL_channels_createChannel arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesController.lambda$null$137(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

