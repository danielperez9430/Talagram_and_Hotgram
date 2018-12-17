package org.telegram.ui;

import android.view.KeyEvent;
import android.widget.TextView$OnEditorActionListener;
import android.widget.TextView;

public final class -$$Lambda$PassportActivity$z1QQrtghbex8oLf43ona_95yF_0 implements TextView$OnEditorActionListener {
    public -$$Lambda$PassportActivity$z1QQrtghbex8oLf43ona_95yF_0(PassportActivity arg1) {
        super();
        this.f$0 = arg1;
    }

    public final boolean onEditorAction(TextView arg2, int arg3, KeyEvent arg4) {
        return PassportActivity.lambda$createEmailVerificationInterface$5(this.f$0, arg2, arg3, arg4);
    }
}

