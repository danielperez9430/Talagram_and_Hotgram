package org.telegram.messenger;

import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.ArrayList;
import org.telegram.messenger.support.SparseLongArray;

public final class -$$Lambda$MessagesController$K6DdrONvqlmB6WvExeluYfkjOyA implements Runnable {
    public -$$Lambda$MessagesController$K6DdrONvqlmB6WvExeluYfkjOyA(MessagesController arg1, SparseLongArray arg2, SparseLongArray arg3, SparseIntArray arg4, ArrayList arg5, SparseArray arg6, SparseIntArray arg7) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
    }

    public final void run() {
        MessagesController.lambda$processUpdateArray$231(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

