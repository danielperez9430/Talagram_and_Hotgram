package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Updates;

public final class -$$Lambda$MessagesController$8fBZ1tBTHag-UMD1XqT63zouO5A implements Comparator {
    static {
        -$$Lambda$MessagesController$8fBZ1tBTHag-UMD1XqT63zouO5A.INSTANCE = new -$$Lambda$MessagesController$8fBZ1tBTHag-UMD1XqT63zouO5A();
    }

    private -$$Lambda$MessagesController$8fBZ1tBTHag-UMD1XqT63zouO5A() {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return MessagesController.lambda$processUpdatesQueue$175(((Updates)arg1), ((Updates)arg2));
    }
}

