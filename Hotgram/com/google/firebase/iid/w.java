package com.google.firebase.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class w extends Handler {
    w(v arg1, Looper arg2) {
        this.a = arg1;
        super(arg2);
    }

    public final void handleMessage(Message arg2) {
        v.a(this.a, arg2);
    }
}

