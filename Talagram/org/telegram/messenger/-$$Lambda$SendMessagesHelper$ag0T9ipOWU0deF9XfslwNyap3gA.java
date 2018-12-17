package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_editMessage;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$SendMessagesHelper$ag0T9ipOWU0deF9XfslwNyap3gA implements Runnable {
    public -$$Lambda$SendMessagesHelper$ag0T9ipOWU0deF9XfslwNyap3gA(SendMessagesHelper arg1, TL_error arg2, BaseFragment arg3, TL_messages_editMessage arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$10(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

