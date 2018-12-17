package org.telegram.messenger;

import android.support.g.b.a.c;
import java.util.ArrayList;

public final class -$$Lambda$SendMessagesHelper$o6KY0OhfMVgu4xHccYtz2SZbo4c implements Runnable {
    public -$$Lambda$SendMessagesHelper$o6KY0OhfMVgu4xHccYtz2SZbo4c(ArrayList arg1, int arg2, ArrayList arg3, String arg4, long arg5, MessageObject arg7, MessageObject arg8, ArrayList arg9, c arg10) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg7;
        this.f$6 = arg8;
        this.f$7 = arg9;
        this.f$8 = arg10;
    }

    public final void run() {
        SendMessagesHelper.lambda$prepareSendingDocuments$47(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}

