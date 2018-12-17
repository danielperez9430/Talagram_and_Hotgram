package org.telegram.ui;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$ChannelParticipant;

public final class -$$Lambda$ChannelUsersActivity$qXKCfjZtcbzICNG8QUuysAKZh64 implements Comparator {
    public -$$Lambda$ChannelUsersActivity$qXKCfjZtcbzICNG8QUuysAKZh64(ChannelUsersActivity arg1) {
        super();
        this.f$0 = arg1;
    }

    public final int compare(Object arg2, Object arg3) {
        return ChannelUsersActivity.lambda$null$12(this.f$0, ((ChannelParticipant)arg2), ((ChannelParticipant)arg3));
    }
}

