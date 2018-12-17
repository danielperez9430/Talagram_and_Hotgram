package org.telegram.messenger;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_document;

public final class -$$Lambda$SendMessagesHelper$tZNUu9eAmtsHIf9IiaCFIGV2KtM implements Runnable {
    public -$$Lambda$SendMessagesHelper$tZNUu9eAmtsHIf9IiaCFIGV2KtM(MessageObject arg1, int arg2, TL_document arg3, String arg4, HashMap arg5, long arg6, MessageObject arg8, String arg9, ArrayList arg10) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
        this.f$5 = arg6;
        this.f$6 = arg8;
        this.f$7 = arg9;
        this.f$8 = arg10;
    }

    public final void run() {
        SendMessagesHelper.lambda$prepareSendingDocumentInternal$43(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}

