package org.telegram.messenger;

import java.util.ArrayList;

public final class -$$Lambda$MessagesStorage$diOgM3ivUd_j99NEcIOcv2QZWik implements Runnable {
    public -$$Lambda$MessagesStorage$diOgM3ivUd_j99NEcIOcv2QZWik(MessagesStorage arg1, ArrayList arg2, ArrayList arg3, int arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$updateDialogsWithDeletedMessages$114(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

