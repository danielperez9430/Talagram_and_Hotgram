package org.telegram.messenger;

import java.util.ArrayList;

public final class -$$Lambda$MessagesStorage$d20O925KVct7cy8xTlpSbuQfBus implements Runnable {
    public -$$Lambda$MessagesStorage$d20O925KVct7cy8xTlpSbuQfBus(MessagesStorage arg1, ArrayList arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$unpinAllDialogsExceptNew$119(this.f$0, this.f$1);
    }
}

