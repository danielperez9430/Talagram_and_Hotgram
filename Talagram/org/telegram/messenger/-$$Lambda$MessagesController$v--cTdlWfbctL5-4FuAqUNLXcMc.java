package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$updates_Difference;

public final class -$$Lambda$MessagesController$v--cTdlWfbctL5-4FuAqUNLXcMc implements Runnable {
    public -$$Lambda$MessagesController$v--cTdlWfbctL5-4FuAqUNLXcMc(MessagesController arg1, ArrayList arg2, updates_Difference arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesController.lambda$null$192(this.f$0, this.f$1, this.f$2);
    }
}

