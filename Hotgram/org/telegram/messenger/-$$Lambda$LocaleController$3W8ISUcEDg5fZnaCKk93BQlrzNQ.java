package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$LocaleController$3W8ISUcEDg5fZnaCKk93BQlrzNQ implements RequestDelegate {
    public -$$Lambda$LocaleController$3W8ISUcEDg5fZnaCKk93BQlrzNQ(LocaleController arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run(TLObject arg3, TL_error arg4) {
        LocaleController.lambda$applyRemoteLanguage$9(this.f$0, this.f$1, arg3, arg4);
    }
}

