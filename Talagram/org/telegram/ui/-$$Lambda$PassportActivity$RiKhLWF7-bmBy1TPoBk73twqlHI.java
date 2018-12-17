package org.telegram.ui;

import android.view.View$OnLongClickListener;
import android.view.View;
import org.telegram.messenger.SecureDocument;

public final class -$$Lambda$PassportActivity$RiKhLWF7-bmBy1TPoBk73twqlHI implements View$OnLongClickListener {
    public -$$Lambda$PassportActivity$RiKhLWF7-bmBy1TPoBk73twqlHI(PassportActivity arg1, int arg2, SecureDocument arg3, SecureDocumentCell arg4, String arg5) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
        this.f$4 = arg5;
    }

    public final boolean onLongClick(View arg7) {
        return PassportActivity.lambda$addDocumentView$57(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, arg7);
    }
}

