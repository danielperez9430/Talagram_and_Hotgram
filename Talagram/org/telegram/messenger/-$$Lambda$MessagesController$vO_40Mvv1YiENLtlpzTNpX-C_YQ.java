package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Updates;

public final class -$$Lambda$MessagesController$vO_40Mvv1YiENLtlpzTNpX-C_YQ implements Comparator {
    static {
        -$$Lambda$MessagesController$vO_40Mvv1YiENLtlpzTNpX-C_YQ.INSTANCE = new -$$Lambda$MessagesController$vO_40Mvv1YiENLtlpzTNpX-C_YQ();
    }

    private -$$Lambda$MessagesController$vO_40Mvv1YiENLtlpzTNpX-C_YQ() {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return MessagesController.lambda$processUpdatesQueue$176(((Updates)arg1), ((Updates)arg2));
    }
}

