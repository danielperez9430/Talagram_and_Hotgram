package org.telegram.messenger;

import java.util.ArrayList;

public final class -$$Lambda$MessagesStorage$3JoU5tI4DQfgMF-XdDHitQLjIWE implements Runnable {
    public -$$Lambda$MessagesStorage$3JoU5tI4DQfgMF-XdDHitQLjIWE(MessagesStorage arg1, ArrayList arg2, int arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$markMessagesAsDeleted$115(this.f$0, this.f$1, this.f$2);
    }
}

