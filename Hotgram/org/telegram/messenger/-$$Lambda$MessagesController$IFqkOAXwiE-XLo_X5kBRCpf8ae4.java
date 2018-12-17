package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Updates;

public final class -$$Lambda$MessagesController$IFqkOAXwiE-XLo_X5kBRCpf8ae4 implements Comparator {
    static {
        -$$Lambda$MessagesController$IFqkOAXwiE-XLo_X5kBRCpf8ae4.INSTANCE = new -$$Lambda$MessagesController$IFqkOAXwiE-XLo_X5kBRCpf8ae4();
    }

    private -$$Lambda$MessagesController$IFqkOAXwiE-XLo_X5kBRCpf8ae4() {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return MessagesController.lambda$processChannelsUpdatesQueue$173(((Updates)arg1), ((Updates)arg2));
    }
}

