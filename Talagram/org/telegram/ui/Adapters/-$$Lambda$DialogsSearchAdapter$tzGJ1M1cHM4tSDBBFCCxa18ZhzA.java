package org.telegram.ui.Adapters;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_messages_searchGlobal;

public final class -$$Lambda$DialogsSearchAdapter$tzGJ1M1cHM4tSDBBFCCxa18ZhzA implements Runnable {
    public -$$Lambda$DialogsSearchAdapter$tzGJ1M1cHM4tSDBBFCCxa18ZhzA(DialogsSearchAdapter arg1, int arg2, TL_error arg3, TLObject arg4, TL_messages_searchGlobal arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run() {
        DialogsSearchAdapter.lambda$null$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

