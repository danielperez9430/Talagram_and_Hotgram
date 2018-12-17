package org.telegram.messenger;

import android.util.LongSparseArray;
import org.telegram.tgnet.TLRPC$Message;
import org.telegram.tgnet.TLRPC$messages_Dialogs;

public final class -$$Lambda$MessagesStorage$SwVNl8FTr_qF_rghO9rjK7Rj1-Y implements Runnable {
    public -$$Lambda$MessagesStorage$SwVNl8FTr_qF_rghO9rjK7Rj1-Y(MessagesStorage arg1, messages_Dialogs arg2, int arg3, int arg4, int arg5, int arg6, int arg7, Message arg8, int arg9, LongSparseArray arg10, LongSparseArray arg11) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg7;
        this.f$7 = arg8;
        this.f$8 = arg9;
        this.f$9 = arg10;
        this.f$10 = arg11;
    }

    public final void run() {
        MessagesStorage.lambda$resetDialogs$42(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10);
    }
}

