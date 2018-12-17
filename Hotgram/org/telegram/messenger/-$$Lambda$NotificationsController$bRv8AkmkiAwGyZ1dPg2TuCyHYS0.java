package org.telegram.messenger;

import android.util.LongSparseArray;
import java.util.ArrayList;

public final class -$$Lambda$NotificationsController$bRv8AkmkiAwGyZ1dPg2TuCyHYS0 implements Runnable {
    public -$$Lambda$NotificationsController$bRv8AkmkiAwGyZ1dPg2TuCyHYS0(NotificationsController arg1, LongSparseArray arg2, ArrayList arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        NotificationsController.lambda$processDialogsUpdateRead$19(this.f$0, this.f$1, this.f$2);
    }
}

