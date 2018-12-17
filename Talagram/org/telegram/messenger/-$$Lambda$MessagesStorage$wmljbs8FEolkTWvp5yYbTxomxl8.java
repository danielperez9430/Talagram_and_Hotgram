package org.telegram.messenger;

import java.util.HashMap;

public final class -$$Lambda$MessagesStorage$wmljbs8FEolkTWvp5yYbTxomxl8 implements Runnable {
    public -$$Lambda$MessagesStorage$wmljbs8FEolkTWvp5yYbTxomxl8(MessagesStorage arg1, HashMap arg2, boolean arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$putCachedPhoneBook$73(this.f$0, this.f$1, this.f$2);
    }
}

