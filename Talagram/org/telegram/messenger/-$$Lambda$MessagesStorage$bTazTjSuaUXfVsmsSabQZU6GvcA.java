package org.telegram.messenger;

import android.util.LongSparseArray;

public final class -$$Lambda$MessagesStorage$bTazTjSuaUXfVsmsSabQZU6GvcA implements Runnable {
    public -$$Lambda$MessagesStorage$bTazTjSuaUXfVsmsSabQZU6GvcA(MessagesStorage arg1, LongSparseArray arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$putWebPages$100(this.f$0, this.f$1);
    }
}

