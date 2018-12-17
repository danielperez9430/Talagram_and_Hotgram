package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.support.SparseLongArray;

public final class -$$Lambda$MessagesStorage$6ge5n8OwG6nnwnfOZsNtBU_Nc68 implements Runnable {
    public -$$Lambda$MessagesStorage$6ge5n8OwG6nnwnfOZsNtBU_Nc68(MessagesStorage arg1, SparseLongArray arg2, SparseLongArray arg3, ArrayList arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$updateDialogsWithReadMessages$54(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

