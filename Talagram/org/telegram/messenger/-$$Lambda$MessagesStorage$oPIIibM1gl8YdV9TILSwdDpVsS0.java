package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

public final class -$$Lambda$MessagesStorage$oPIIibM1gl8YdV9TILSwdDpVsS0 implements Runnable {
    public -$$Lambda$MessagesStorage$oPIIibM1gl8YdV9TILSwdDpVsS0(MessagesStorage arg1, int arg2, Integer[] arg3, CountDownLatch arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$getChannelPtsSync$124(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

