package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$ProfileActivity$UJrLqVewDIP3ANEi7JxfdWV60C4 implements DialogInterface$OnClickListener {
    public -$$Lambda$ProfileActivity$UJrLqVewDIP3ANEi7JxfdWV60C4(ProfileActivity arg1, ArrayList arg2, User arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void onClick(DialogInterface arg4, int arg5) {
        ProfileActivity.lambda$processOnClickOrPress$17(this.f$0, this.f$1, this.f$2, arg4, arg5);
    }
}

