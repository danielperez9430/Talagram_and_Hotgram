package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.support.SparseLongArray;

public final class -$$Lambda$NotificationsController$bn_qy54k0GHNymLhNYsBBa6g2mw implements Runnable {
    public -$$Lambda$NotificationsController$bn_qy54k0GHNymLhNYsBBa6g2mw(NotificationsController arg1, SparseLongArray arg2, ArrayList arg3, long arg4, int arg6, int arg7, boolean arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg6;
        this.f$5 = arg7;
        this.f$6 = arg8;
    }

    public final void run() {
        NotificationsController.lambda$processReadMessages$13(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

