package com.google.firebase.iid;

import com.google.firebase.b.b;

final class aq implements b {
    private final a a;

    aq(a arg1) {
        super();
        this.a = arg1;
    }

    public final void a(com.google.firebase.b.a arg2) {
        a v2 = this.a;
        __monitor_enter(v2);
        try {
            if(v2.a()) {
                FirebaseInstanceId.b(v2.a);
            }

            __monitor_exit(v2);
            return;
        label_9:
            __monitor_exit(v2);
        }
        catch(Throwable v0) {
            goto label_9;
        }

        throw v0;
    }
}

