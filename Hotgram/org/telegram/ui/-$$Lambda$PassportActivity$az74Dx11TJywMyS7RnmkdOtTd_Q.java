package org.telegram.ui;

import android.view.View$OnClickListener;
import android.view.View;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$TL_secureRequiredType;

public final class -$$Lambda$PassportActivity$az74Dx11TJywMyS7RnmkdOtTd_Q implements View$OnClickListener {
    public -$$Lambda$PassportActivity$az74Dx11TJywMyS7RnmkdOtTd_Q(PassportActivity arg1, ArrayList arg2, TL_secureRequiredType arg3, boolean arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void onClick(View arg5) {
        PassportActivity.lambda$addField$64(this.f$0, this.f$1, this.f$2, this.f$3, arg5);
    }
}

