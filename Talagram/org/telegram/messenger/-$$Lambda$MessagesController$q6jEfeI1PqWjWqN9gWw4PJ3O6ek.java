package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$TL_channels_editAdmin;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.ui.ActionBar.BaseFragment;

public final class -$$Lambda$MessagesController$q6jEfeI1PqWjWqN9gWw4PJ3O6ek implements RequestDelegate {
    public -$$Lambda$MessagesController$q6jEfeI1PqWjWqN9gWw4PJ3O6ek(MessagesController arg1, int arg2, BaseFragment arg3, TL_channels_editAdmin arg4, boolean arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final void run(TLObject arg8, TL_error arg9) {
        MessagesController.lambda$setUserAdminRole$43(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg8, arg9);
    }
}

