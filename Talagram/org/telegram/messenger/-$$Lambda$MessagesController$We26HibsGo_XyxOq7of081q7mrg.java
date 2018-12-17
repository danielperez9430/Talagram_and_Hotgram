package org.telegram.messenger;

import android.util.SparseIntArray;
import java.util.ArrayList;

public final class -$$Lambda$MessagesController$We26HibsGo_XyxOq7of081q7mrg implements Runnable {
    public -$$Lambda$MessagesController$We26HibsGo_XyxOq7of081q7mrg(MessagesController arg1, ArrayList arg2, boolean arg3, SparseIntArray arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesController.lambda$processLoadedBlockedUsers$46(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

