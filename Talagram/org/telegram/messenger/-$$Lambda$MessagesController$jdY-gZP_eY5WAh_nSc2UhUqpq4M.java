package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Update;

public final class -$$Lambda$MessagesController$jdY-gZP_eY5WAh_nSc2UhUqpq4M implements Comparator {
    public -$$Lambda$MessagesController$jdY-gZP_eY5WAh_nSc2UhUqpq4M(MessagesController arg1) {
        super();
        this.f$0 = arg1;
    }

    public final int compare(Object arg2, Object arg3) {
        return MessagesController.lambda$new$0(this.f$0, ((Update)arg2), ((Update)arg3));
    }
}

