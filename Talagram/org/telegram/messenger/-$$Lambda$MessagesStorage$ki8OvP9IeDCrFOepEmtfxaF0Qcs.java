package org.telegram.messenger;

import java.util.ArrayList;

public final class -$$Lambda$MessagesStorage$ki8OvP9IeDCrFOepEmtfxaF0Qcs implements Runnable {
    public -$$Lambda$MessagesStorage$ki8OvP9IeDCrFOepEmtfxaF0Qcs(MessagesStorage arg1, ArrayList arg2, boolean arg3, boolean arg4, int arg5, boolean arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        MessagesStorage.lambda$putMessages$105(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

