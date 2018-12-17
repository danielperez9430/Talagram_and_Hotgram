package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$TL_dialog;

public class DialogObject {
    public DialogObject() {
        super();
    }

    public static boolean isChannel(TL_dialog arg1) {
        boolean v0 = true;
        if(arg1 == null || (arg1.flags & 1) == 0) {
            v0 = false;
        }
        else {
        }

        return v0;
    }
}

