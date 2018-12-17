package com.google.firebase.iid;

import com.google.firebase.components.b;
import com.google.firebase.components.d;

final class r implements d {
    static final d a;

    static {
        r.a = new r();
    }

    private r() {
        super();
    }

    public final Object a(b arg3) {
        return new a(arg3.a(FirebaseInstanceId.class));
    }
}

