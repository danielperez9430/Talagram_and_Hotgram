package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_getWebPagePreview;

public final class -$$Lambda$ChatActivity$pjobQSMnVqEE4cfhciMVuPhS4UM implements Runnable {
    public -$$Lambda$ChatActivity$pjobQSMnVqEE4cfhciMVuPhS4UM(ChatActivity arg1, TL_error arg2, TLObject arg3, TL_messages_getWebPagePreview arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void run() {
        ChatActivity.lambda$null$44(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

