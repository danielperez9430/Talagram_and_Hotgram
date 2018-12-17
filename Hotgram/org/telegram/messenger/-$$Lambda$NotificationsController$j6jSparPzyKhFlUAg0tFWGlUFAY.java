package org.telegram.messenger;

import android.util.LongSparseArray;
import java.util.ArrayList;

public final class -$$Lambda$NotificationsController$j6jSparPzyKhFlUAg0tFWGlUFAY implements Runnable {
    public -$$Lambda$NotificationsController$j6jSparPzyKhFlUAg0tFWGlUFAY(NotificationsController arg1, ArrayList arg2, LongSparseArray arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        NotificationsController.lambda$processLoadedUnreadMessages$21(this.f$0, this.f$1, this.f$2);
    }
}

