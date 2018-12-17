package org.telegram.messenger;

import java.util.ArrayList;

public final class -$$Lambda$MessagesStorage$ZqYAZfhXzKliAp4oMeYGPdUI07E implements Runnable {
    public -$$Lambda$MessagesStorage$ZqYAZfhXzKliAp4oMeYGPdUI07E(MessagesStorage arg1, boolean arg2, ArrayList arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$putContacts$70(this.f$0, this.f$1, this.f$2);
    }
}

