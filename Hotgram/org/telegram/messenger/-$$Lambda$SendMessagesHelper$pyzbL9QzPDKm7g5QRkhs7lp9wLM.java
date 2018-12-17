package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_document;

public final class -$$Lambda$SendMessagesHelper$pyzbL9QzPDKm7g5QRkhs7lp9wLM implements Runnable {
    public -$$Lambda$SendMessagesHelper$pyzbL9QzPDKm7g5QRkhs7lp9wLM(MessageObject arg1, int arg2, TL_document arg3, String arg4, HashMap arg5, long arg6, MessageObject arg8, SendingMediaInfo arg9) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg8;
        this.f$7 = arg9;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$54(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}

