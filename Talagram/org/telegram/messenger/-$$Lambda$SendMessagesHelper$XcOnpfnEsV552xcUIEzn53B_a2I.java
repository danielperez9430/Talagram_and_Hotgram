package org.telegram.messenger;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.tgnet.TLRPC$TL_document;

public final class -$$Lambda$SendMessagesHelper$XcOnpfnEsV552xcUIEzn53B_a2I implements Runnable {
    public -$$Lambda$SendMessagesHelper$XcOnpfnEsV552xcUIEzn53B_a2I(Bitmap arg1, String arg2, MessageObject arg3, int arg4, VideoEditedInfo arg5, TL_document arg6, String arg7, HashMap arg8, long arg9, MessageObject arg11, String arg12, ArrayList arg13, int arg14) {
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
        this.f$9 = arg11;
        this.f$10 = arg12;
        this.f$11 = arg13;
        this.f$12 = arg14;
    }

    public final void run() {
        SendMessagesHelper.lambda$null$60(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12);
    }
}

