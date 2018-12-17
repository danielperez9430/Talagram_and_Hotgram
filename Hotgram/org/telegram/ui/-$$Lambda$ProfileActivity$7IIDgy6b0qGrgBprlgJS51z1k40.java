package org.telegram.ui;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$ChannelParticipant;
import org.telegram.tgnet.TLRPC$ChatParticipant;

public final class -$$Lambda$ProfileActivity$7IIDgy6b0qGrgBprlgJS51z1k40 implements DialogInterface$OnClickListener {
    public -$$Lambda$ProfileActivity$7IIDgy6b0qGrgBprlgJS51z1k40(ProfileActivity arg1, ArrayList arg2, ChatParticipant arg3, ChannelParticipant arg4) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
        this.f$3 = arg4;
    }

    public final void onClick(DialogInterface arg7, int arg8) {
        ProfileActivity.lambda$null$7(this.f$0, this.f$1, this.f$2, this.f$3, arg7, arg8);
    }
}

