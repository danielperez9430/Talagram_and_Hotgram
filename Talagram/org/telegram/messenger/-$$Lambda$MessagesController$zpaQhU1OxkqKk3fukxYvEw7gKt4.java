package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Updates;

public final class -$$Lambda$MessagesController$zpaQhU1OxkqKk3fukxYvEw7gKt4 implements Comparator {
    public -$$Lambda$MessagesController$zpaQhU1OxkqKk3fukxYvEw7gKt4(MessagesController arg1) {
        super();
        this.f$0 = arg1;
    }

    public final int compare(Object arg2, Object arg3) {
        return MessagesController.lambda$processUpdatesQueue$174(this.f$0, ((Updates)arg2), ((Updates)arg3));
    }
}

