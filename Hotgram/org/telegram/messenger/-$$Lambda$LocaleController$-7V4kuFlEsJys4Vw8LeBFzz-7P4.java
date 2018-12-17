package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$LocaleController$-7V4kuFlEsJys4Vw8LeBFzz-7P4 implements RequestDelegate {
    public -$$Lambda$LocaleController$-7V4kuFlEsJys4Vw8LeBFzz-7P4(LocaleController arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        LocaleController.lambda$applyRemoteLanguage$7(this.f$0, this.f$1, arg3, arg4);
    }
}

