package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$LocaleController$wb1-rMc5tYwIL_0Eu4mT1HDm3pw implements RequestDelegate {
    public -$$Lambda$LocaleController$wb1-rMc5tYwIL_0Eu4mT1HDm3pw(LocaleController arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        LocaleController.lambda$loadRemoteLanguages$5(this.f$0, this.f$1, arg3, arg4);
    }
}

