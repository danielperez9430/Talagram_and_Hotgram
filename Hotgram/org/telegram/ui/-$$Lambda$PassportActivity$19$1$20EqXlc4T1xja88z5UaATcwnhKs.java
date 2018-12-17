package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_secureRequiredType;

public final class -$$Lambda$PassportActivity$19$1$20EqXlc4T1xja88z5UaATcwnhKs implements Runnable {
    public -$$Lambda$PassportActivity$19$1$20EqXlc4T1xja88z5UaATcwnhKs(org.telegram.ui.PassportActivity$19$1 arg1, TLObject arg2, String arg3, TL_secureRequiredType arg4, PassportActivityDelegate arg5, TL_error arg6, ErrorRunnable arg7) {
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
        org.telegram.ui.PassportActivity$19$1.lambda$null$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

