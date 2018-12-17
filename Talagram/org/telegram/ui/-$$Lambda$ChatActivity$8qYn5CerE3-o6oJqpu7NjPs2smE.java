package org.telegram.ui;

import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.MessagesStorage;

public final class -$$Lambda$ChatActivity$8qYn5CerE3-o6oJqpu7NjPs2smE implements Runnable {
    public -$$Lambda$ChatActivity$8qYn5CerE3-o6oJqpu7NjPs2smE(ChatActivity arg1, MessagesStorage arg2, int arg3, CountDownLatch arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        ChatActivity.lambda$onFragmentCreate$2(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

