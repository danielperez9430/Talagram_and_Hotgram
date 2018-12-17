package org.telegram.messenger;

import org.telegram.tgnet.TLObject;

public final class -$$Lambda$MessagesStorage$AV8JUpwRZ9sYJ8stAHUdk__a2Sc implements Runnable {
    public -$$Lambda$MessagesStorage$AV8JUpwRZ9sYJ8stAHUdk__a2Sc(MessagesStorage arg1, TLObject arg2, String arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$saveBotCache$60(this.f$0, this.f$1, this.f$2);
    }
}

