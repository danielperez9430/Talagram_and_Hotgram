package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChatFull;

public final class -$$Lambda$MessagesController$JvNgW-66JZWqmDhoB0SyKTnmbs0 implements Runnable {
    public -$$Lambda$MessagesController$JvNgW-66JZWqmDhoB0SyKTnmbs0(MessagesController arg1, ArrayList arg2, boolean arg3, ChatFull arg4, boolean arg5, MessageObject arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
    }

    public final void run() {
        MessagesController.lambda$processChatInfo$64(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}

