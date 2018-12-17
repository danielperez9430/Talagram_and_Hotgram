package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;

public final class -$$Lambda$NotificationsController$8lQbr5XMNBt__wC6arYqfGdfeMk implements Runnable {
    public -$$Lambda$NotificationsController$8lQbr5XMNBt__wC6arYqfGdfeMk(NotificationsController arg1, SparseArray arg2, ArrayList arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        NotificationsController.lambda$removeDeletedMessagesFromNotifications$8(this.f$0, this.f$1, this.f$2);
    }
}

