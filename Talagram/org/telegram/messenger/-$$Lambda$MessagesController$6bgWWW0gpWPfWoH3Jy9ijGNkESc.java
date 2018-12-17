package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Updates;

public final class -$$Lambda$MessagesController$6bgWWW0gpWPfWoH3Jy9ijGNkESc implements Runnable {
    public -$$Lambda$MessagesController$6bgWWW0gpWPfWoH3Jy9ijGNkESc(MessagesController arg1, boolean arg2, Updates arg3, ArrayList arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesController.lambda$processUpdates$214(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

