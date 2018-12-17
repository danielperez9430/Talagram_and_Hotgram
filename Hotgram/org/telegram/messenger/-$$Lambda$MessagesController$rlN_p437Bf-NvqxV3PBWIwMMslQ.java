package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$updates_ChannelDifference;

public final class -$$Lambda$MessagesController$rlN_p437Bf-NvqxV3PBWIwMMslQ implements Runnable {
    public -$$Lambda$MessagesController$rlN_p437Bf-NvqxV3PBWIwMMslQ(MessagesController arg1, ArrayList arg2, int arg3, updates_ChannelDifference arg4, Chat arg5, SparseArray arg6, int arg7, long arg8) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
        this.f$7 = arg8;
    }

    public final void run() {
        MessagesController.lambda$null$185(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}

