package org.telegram.ui;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$ChannelParticipant;

public final class -$$Lambda$ChannelUsersActivity$NdVlm3vlg2Uqf8RbbAF0IVC0xGE implements Comparator {
    public -$$Lambda$ChannelUsersActivity$NdVlm3vlg2Uqf8RbbAF0IVC0xGE(ChannelUsersActivity arg1) {
        super();
        this.f$0 = arg1;
    }

    public final int compare(Object arg2, Object arg3) {
        return ChannelUsersActivity.lambda$null$11(this.f$0, ((ChannelParticipant)arg2), ((ChannelParticipant)arg3));
    }
}

