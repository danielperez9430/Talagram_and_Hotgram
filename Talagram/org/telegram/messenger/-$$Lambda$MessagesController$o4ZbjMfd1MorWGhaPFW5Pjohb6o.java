package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$updates_Difference;

public final class -$$Lambda$MessagesController$o4ZbjMfd1MorWGhaPFW5Pjohb6o implements Runnable {
    public -$$Lambda$MessagesController$o4ZbjMfd1MorWGhaPFW5Pjohb6o(MessagesController arg1, updates_Difference arg2, ArrayList arg3, SparseArray arg4, SparseArray arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        MessagesController.lambda$null$195(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

