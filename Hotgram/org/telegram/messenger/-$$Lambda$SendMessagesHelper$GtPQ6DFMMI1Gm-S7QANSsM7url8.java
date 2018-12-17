package org.telegram.messenger;

import android.util.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Peer;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_forwardMessages;

public final class -$$Lambda$SendMessagesHelper$GtPQ6DFMMI1Gm-S7QANSsM7url8 implements RequestDelegate {
    public -$$Lambda$SendMessagesHelper$GtPQ6DFMMI1Gm-S7QANSsM7url8(SendMessagesHelper arg1, long arg2, boolean arg4, boolean arg5, LongSparseArray arg6, ArrayList arg7, ArrayList arg8, Peer arg9, TL_messages_forwardMessages arg10) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg4;
        this.f$3 = arg5;
        this.f$4 = arg6;
        this.f$5 = arg7;
        this.f$6 = arg8;
        this.f$7 = arg9;
        this.f$8 = arg10;
    }

    public final void run(TLObject arg13, TL_error arg14) {
        SendMessagesHelper.lambda$sendMessage$9(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, arg13, arg14);
    }
}

