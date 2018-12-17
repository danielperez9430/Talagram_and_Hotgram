package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_error;

public final class -$$Lambda$MessagesController$YPLgK-AlDGBTb9KPgcApuxn0__c implements RequestDelegate {
    public -$$Lambda$MessagesController$YPLgK-AlDGBTb9KPgcApuxn0__c(MessagesController arg1, int arg2, int arg3, long arg4, int arg6) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg6;
    }

    public final void run(TLObject arg9, TL_error arg10) {
        MessagesController.lambda$loadDialogPhotos$36(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg9, arg10);
    }
}

