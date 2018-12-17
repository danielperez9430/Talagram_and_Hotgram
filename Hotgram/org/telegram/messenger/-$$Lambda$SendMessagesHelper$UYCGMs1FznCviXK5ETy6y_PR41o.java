package org.telegram.messenger;

import android.support.g.b.a.c;
import java.util.ArrayList;

public final class -$$Lambda$SendMessagesHelper$UYCGMs1FznCviXK5ETy6y_PR41o implements Runnable {
    public -$$Lambda$SendMessagesHelper$UYCGMs1FznCviXK5ETy6y_PR41o(ArrayList arg1, long arg2, int arg4, boolean arg5, boolean arg6, MessageObject arg7, MessageObject arg8, c arg9) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
        this.f$4 = arg6;
        this.f$5 = arg7;
        this.f$6 = arg8;
        this.f$7 = arg9;
    }

    public final void run() {
        SendMessagesHelper.lambda$prepareSendingMedia$59(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}

