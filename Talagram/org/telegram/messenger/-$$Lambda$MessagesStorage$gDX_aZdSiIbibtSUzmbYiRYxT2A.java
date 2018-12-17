package org.telegram.messenger;

import java.util.ArrayList;

public final class -$$Lambda$MessagesStorage$gDX_aZdSiIbibtSUzmbYiRYxT2A implements Runnable {
    public -$$Lambda$MessagesStorage$gDX_aZdSiIbibtSUzmbYiRYxT2A(MessagesStorage arg1, ArrayList arg2, ArrayList arg3, boolean arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesStorage.lambda$putUsersAndChats$94(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

