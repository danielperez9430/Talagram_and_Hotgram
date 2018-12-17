package com.google.firebase.iid;

import android.os.Handler$Callback;
import android.os.Message;

final class g implements Handler$Callback {
    private final f a;

    g(f arg1) {
        super();
        this.a = arg1;
    }

    public final boolean handleMessage(Message arg2) {
        return this.a.a(arg2);
    }
}

