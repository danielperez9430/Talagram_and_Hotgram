package org.telegram.ui;

import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.MessagesStorage;

public final class -$$Lambda$ChatActivity$zbo6FUWEskAD8BCSLY3b_JqK_JY implements Runnable {
    public -$$Lambda$ChatActivity$zbo6FUWEskAD8BCSLY3b_JqK_JY(ChatActivity arg1, MessagesStorage arg2, CountDownLatch arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        ChatActivity.lambda$onFragmentCreate$3(this.f$0, this.f$1, this.f$2);
    }
}

