package org.telegram.messenger;

import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$ocf3iu5hnKitc3I0hlNejWZnKEo implements Runnable {
    public -$$Lambda$MessagesController$ocf3iu5hnKitc3I0hlNejWZnKEo(MessagesController arg1, AlertDialog[] arg2, int arg3, BaseFragment arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        MessagesController.lambda$openByUserName$240(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

