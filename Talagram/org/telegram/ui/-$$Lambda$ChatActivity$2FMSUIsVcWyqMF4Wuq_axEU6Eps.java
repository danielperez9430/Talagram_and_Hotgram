package org.telegram.ui;

import android.os.Bundle;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$ChatActivity$2FMSUIsVcWyqMF4Wuq_axEU6Eps implements Runnable {
    public -$$Lambda$ChatActivity$2FMSUIsVcWyqMF4Wuq_axEU6Eps(ChatActivity arg1, BaseFragment arg2, Bundle arg3, int arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        ChatActivity.lambda$didReceivedNotification$51(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

