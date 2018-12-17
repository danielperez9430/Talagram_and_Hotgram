package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$ush3JJi2Yw3D4PDyCWbMK0GKFcs implements Runnable {
    public -$$Lambda$MessagesStorage$ush3JJi2Yw3D4PDyCWbMK0GKFcs(MessagesStorage arg1, int arg2, boolean[] arg3, CountDownLatch arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$hasAuthMessage$91(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

