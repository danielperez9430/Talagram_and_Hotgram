package org.telegram.messenger;

import java.util.ArrayList;

public final class -$$Lambda$MessagesStorage$oL5twKadpe9UtUnyeA6t0aXhJOI implements Runnable {
    public -$$Lambda$MessagesStorage$oL5twKadpe9UtUnyeA6t0aXhJOI(MessagesStorage arg1, ArrayList arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$putWebRecent$26(this.f$0, this.f$1);
    }
}

