package org.telegram.messenger;

import android.util.SparseIntArray;
import java.util.ArrayList;

public final class -$$Lambda$NotificationsController$4ZPSiSXCXkKfxVPcPpmsFy8foEU implements Runnable {
    public -$$Lambda$NotificationsController$4ZPSiSXCXkKfxVPcPpmsFy8foEU(NotificationsController arg1, SparseIntArray arg2, ArrayList arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        NotificationsController.lambda$removeDeletedHisoryFromNotifications$11(this.f$0, this.f$1, this.f$2);
    }
}

